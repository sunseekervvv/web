<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form :inline="true" :model="dataForm">
        <el-form-item label="Category">
          <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
        </el-form-item>
        <el-form-item label="Brand">
          <brand-select style="width:160px"></brand-select>
        </el-form-item>
        <el-form-item label="Price">
          <el-input-number style="width:160px" v-model="dataForm.price.min" :min="0"></el-input-number>-
          <el-input-number style="width:160px" v-model="dataForm.price.max" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="Search">
          <el-input style="width:160px" v-model="dataForm.key" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="searchSkuInfo">Search</el-button>
        </el-form-item>
      </el-form>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;"
      @expand-change="getSkuDetails"
    >
      <el-table-column type="expand">
        <template slot-scope="scope">
          Product title：{{scope.row.skuTitle}}
          <br />
          Product subtitle：{{scope.row.skuSubtitle}}
          <br />
          Product description：{{scope.row.skuDesc}}
          <br />
          CategoryID：{{scope.row.catalogId}}
          <br />
          SpuID：{{scope.row.spuId}}
          <br />
          BrandID：{{scope.row.brandId}}
          <br />
        </template>
      </el-table-column>
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="skuId" header-align="center" align="center" label="skuId"></el-table-column>
      <el-table-column prop="skuName" header-align="center" align="center" label="skuName"></el-table-column>
      <el-table-column prop="skuDefaultImg" header-align="center" align="center" label="DefaultImg">
        <template slot-scope="scope">
          <img :src="scope.row.skuDefaultImg" style="width:80px;height:80px;" />
        </template>
      </el-table-column>
      <el-table-column prop="price" header-align="center" align="center" label="Price"></el-table-column>
      <el-table-column prop="saleCount" header-align="center" align="center" label="SaleCount"></el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="Operation">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="toStock(scope.row.skuId)">To Stock</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
  </div>
</template>

<script>
import CategoryCascader from '../common/category-cascader'
import BrandSelect from '../common/brand-select'
export default {
  data () {
    return {
      catPathSub: null,
      brandIdSub: null,
      dataForm: {
        key: '',
        brandId: 0,
        catelogId: 0,
        price: {
          min: 0,
          max: 0
        }
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      catelogPath: []
    }
  },
  components: {
    CategoryCascader,
    BrandSelect
  },
  activated () {
    this.getDataList()
  },
  methods: {
    getSkuDetails (row, expand) {
      // sku详情查询
      // console.log('展开某行...', row, expand)
    },
    toStock(skuId){
      this.$router.push({ path: '/ware-sku', query: { skuId: skuId } })
    },
    // 处理更多指令
    searchSkuInfo () {
      this.getDataList()
    },
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/product/skuinfo/list'),
        method: 'get',
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          key: this.dataForm.key,
          catelogId: this.dataForm.catelogId,
          brandId: this.dataForm.brandId,
          min: this.dataForm.price.min,
          max: this.dataForm.price.max
          // seller: this.$store.state.user.name
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list.filter(item => item.seller === this.$store.state.user.name)
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle (val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    }
  },
  mounted () {
    this.catPathSub = PubSub.subscribe('catPath', (msg, val) => {
      this.dataForm.catelogId = val[val.length - 1]
    })
    this.brandIdSub = PubSub.subscribe('brandId', (msg, val) => {
      this.dataForm.brandId = val
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.catPathSub)
    PubSub.unsubscribe(this.brandIdSub)
  } // 生命周期 - 销毁之前
}
</script>
