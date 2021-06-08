package com.sunseeker.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.ware.entity.PurchaseEntity;
import com.sunseeker.mall.ware.vo.MergeVo;
import com.sunseeker.mall.ware.vo.PurchaseDoneVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 23:06:41
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceivePurchase(Map<String, Object> params);


    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);

    void done(PurchaseDoneVo doneVo);
    //PageUtils queryPage(Map<String, Object> params);
    //
    //PageUtils listUnreceive(Map<String, Object> params);
    //
    //void mergePurchaseDetail(MergeVo mergeVo);
    //
    //void ReceivedPurchase(List<Long> ids);
    //
    //void finishPurchase(PurchaseDoneVo purchaseDoneVo);
}

