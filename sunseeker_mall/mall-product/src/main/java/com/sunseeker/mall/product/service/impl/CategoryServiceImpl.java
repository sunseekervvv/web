package com.sunseeker.mall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.product.dao.CategoryDao;
import com.sunseeker.mall.product.entity.CategoryEntity;
import com.sunseeker.mall.product.service.CategoryBrandRelationService;
import com.sunseeker.mall.product.service.CategoryService;
import com.sunseeker.mall.product.vo.Catalog2Vo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

//    @Autowired
//    CategoryDao categoryDao;

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);

        //2、组装成父子的树形结构

        //2.1）、找到所有的一级分类
        List<CategoryEntity> level1Menus = entities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .peek((menu)-> menu.setChildren(getChildrens(menu,entities)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO  1、检查当前删除的菜单，是否被别的地方引用

        //逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    @Cacheable(value = {"category"},key = "#root.method.name")
    @Override
    public List<CategoryEntity> getLevel1Categories() {
        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid",0));
    }


    //public Map<String,List<Catalog2Vo>> getCatalogJson(){
    //    /*
    //     * 1、空结果缓存：解决缓存穿透
    //     * 2、设置过期时间（加随机值）：解决缓存雪崩
    //     * 3、加锁：解决缓存击穿
    //     */
    //    // 加入缓存
    //    String catalogJSON = redisTemplate.opsForValue().get("catalogJSON");
    //    if(StringUtils.isEmpty(catalogJSON)){
    //         // 缓存中没有，查询数据库，放入缓存
    //        Map<String, List<Catalog2Vo>> catalogJsonFromDb = getCatalogJsonWithRedisson();
    //
    //        return catalogJsonFromDb;
    //    }
    //
    //    Map<String,List<Catalog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<>() {});
    //    return result;
    //}
    @Cacheable(value = {"category"},key = "#root.method.name")
    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        Map<String, List<Catalog2Vo>> categoryMap=null;
        RLock lock = redissonClient.getLock("CatalogJson-Lock");
        lock.lock();
        try {
            categoryMap = getCatalogJsonFromDb();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return categoryMap;
    }


    public Map<String,List<Catalog2Vo>> getCatalogJsonFromDb(){
        System.out.println("查询了数据库");
        List<CategoryEntity> selectList = baseMapper.selectList(null);
        // 查出所有一级分类
        List<CategoryEntity> level1Categories = getParentCid(selectList,0L);
        Map<String, List<Catalog2Vo>> res = level1Categories.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 查询一级分类中的二级分类
            List<CategoryEntity> categoryEntities = getParentCid(selectList,v.getCatId());
            // 封装结果
            List<Catalog2Vo> catalog2Vos = null;
            if (categoryEntities != null) {
                catalog2Vos = categoryEntities.stream().map(l2 -> {
                    Catalog2Vo catalog2Vo = new Catalog2Vo(v.getCatId().toString(),null , l2.getCatId().toString(), l2.getName());
                    List<CategoryEntity> level3Catelog = getParentCid(selectList,l2.getCatId());
                    if(level3Catelog != null){
                        List<Catalog2Vo.Catalog3Vo> collect = level3Catelog.stream().map(l3 -> new Catalog2Vo.Catalog3Vo(l2.getCatId().toString(),l3.getCatId().toString(),l3.getName())).collect(Collectors.toList());
                        catalog2Vo.setCatalog3List(collect);
                    }
                    return catalog2Vo;
                }).collect(Collectors.toList());
            }else {
                catalog2Vos = new ArrayList<>();
            }

            return catalog2Vos;
        }));
        return res;
    }

    private List<CategoryEntity> getParentCid(List<CategoryEntity> selectList, Long parent_cid) {
        return selectList.stream().filter(item -> item.getParentCid().equals(parent_cid)).collect(Collectors.toList());
    }

    //[2,25,225]
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);


        return parentPath.toArray(new Long[parentPath.size()]);
    }

    /**
     * 级联更新所有关联的数据
     * @param category
     */
    @CacheEvict(value = {"category"},allEntries = true)
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    //225,25,2
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        //1、收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;

    }


    //递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root,List<CategoryEntity> all){

        List<CategoryEntity> children = all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
                .peek(categoryEntity -> categoryEntity.setChildren(getChildrens(categoryEntity,all)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());

        return children;
    }



}