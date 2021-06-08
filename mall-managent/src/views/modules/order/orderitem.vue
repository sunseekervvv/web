<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="Param" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">Select</el-button>
<!--        <el-button v-if="isAuth('order:orderitem:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>-->
        <el-button v-if="isAuth('order:orderitem:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">Batch delete</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="id">
      </el-table-column>
      <el-table-column
        prop="orderSn"
        header-align="center"
        align="center"
        label="order_sn">
      </el-table-column>
      <el-table-column prop="status" header-align="center" align="center" label="status">
        <template slot-scope="scope">
          {{scope.row.status === 0? 'wait pay' : (scope.row.status === 1 ? 'Wait to send': (scope.row.status === 2? 'Sent':(scope.row.status===3?'Done':'Timeout')))}}
        </template>
      </el-table-column>
      <el-table-column
        prop="spuId"
        header-align="center"
        align="center"
        label="spu_id">
      </el-table-column>
      <el-table-column
        prop="spuName"
        header-align="center"
        align="center"
        label="spu_name">
      </el-table-column>
<!--      <el-table-column prop="skuDefaultImg" header-align="center" align="center" label="DefaultImg">-->
<!--        <template slot-scope="scope">-->
<!--          <img :src="scope.row.skuDefaultImg" style="width:80px;height:80px;" />-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column
        prop="spuBrand"
        header-align="center"
        align="center"
        label="Brand">
      </el-table-column>
      <el-table-column
        prop="categoryId"
        header-align="center"
        align="center"
        label="CatelogId">
      </el-table-column>
      <el-table-column
        prop="skuId"
        header-align="center"
        align="center"
        label="SkuId">
      </el-table-column>
      <el-table-column
        prop="skuName"
        header-align="center"
        align="center"
        label="SkuName">
      </el-table-column>
      <el-table-column prop="skuPic" header-align="center" align="center" label="SkuImg">
        <template slot-scope="scope">
            <img :src="scope.row.skuPic" style="width:60px;height:60px;" />
        </template>
      </el-table-column>
      <el-table-column
        prop="skuPrice"
        header-align="center"
        align="center"
        label="Price">
      </el-table-column>
      <el-table-column
        prop="skuQuantity"
        header-align="center"
        align="center"
        label="Num">
      </el-table-column>
      <el-table-column
        prop="skuAttrsVals"
        header-align="center"
        align="center"
        label="Attrs">
      </el-table-column>
      <el-table-column
        prop="couponAmount"
        header-align="center"
        align="center"
        label="Coupon Reduction">
      </el-table-column>
      <el-table-column
        prop="integrationAmount"
        header-align="center"
        align="center"
        label="Integral Reduction">
      </el-table-column>
      <el-table-column
        prop="realAmount"
        header-align="center"
        align="center"
        label="Real Pay">
      </el-table-column>
      <el-table-column
        prop="giftIntegration"
        header-align="center"
        align="center"
        label="Give Integral">
      </el-table-column>
      <el-table-column
        prop="giftGrowth"
        header-align="center"
        align="center"
        label="Give growth">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="Operation">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status === 1" type="text" size="small" @click="sendHandle(scope.row.id)">Send</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">Delete</el-button>
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
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './orderitem-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/order/orderitem/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
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
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },

      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/order/orderitem/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },

      sendHandle (id) {
        let orderItem = {
          id: id,
          status: 2
        }
        this.$confirm(`Confirm to Send id=${id}?`, 'Tips', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/order/orderitem/update'),
            method: 'post',
            data: this.$http.adornData(orderItem, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: 'Success',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
