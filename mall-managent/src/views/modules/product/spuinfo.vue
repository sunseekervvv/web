<template>
  <div class="mod-config">
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;"
    >
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="id" header-align="center" align="center" label="id"></el-table-column>
      <el-table-column prop="spuName" header-align="center" align="center" label="Title"></el-table-column>
      <el-table-column prop="spuDescription" header-align="center" align="center" label="Description"></el-table-column>
      <el-table-column prop="catalogId" header-align="center" align="center" label="Category"></el-table-column>
      <el-table-column prop="brandId" header-align="center" align="center" label="Brand"></el-table-column>
      <el-table-column prop="weight" header-align="center" align="center" label="Weight"></el-table-column>
      <el-table-column prop="publishStatus" header-align="center" align="center" label="Status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.publishStatus === 0">New</el-tag>
          <el-tag v-if="scope.row.publishStatus === 1">Up</el-tag>
          <el-tag v-if="scope.row.publishStatus === 2">Off</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" header-align="center" align="center" label="Create time"></el-table-column>
      <el-table-column prop="updateTime" header-align="center" align="center" label="Update time"></el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="Options">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.publishStatus === 0"
            type="text"
            size="small"
            @click="productUp(scope.row.id)"
          >Up</el-button>
          <el-button type="text" size="small" @click="attrUpdateShow(scope.row)">Update</el-button>
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
export default {
  data () {
    return {
      dataSub: null,
      dataForm: {},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  props: {
    catId: {
      type: Number,
      default: 0
    }
  },
  components: {},
  activated () {
    this.getDataList()
  },
  methods: {
    productUp (id) {
      this.$http({
        url: this.$http.adornUrl('/product/spuinfo/' + id + '/up'),
        method: 'post'
      }).then(({ data }) => {
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
    },
    attrUpdateShow (row) {
      console.log(row)
      this.$router.push({
        path: '/product-attrupdate',
        query: { spuId: row.id, catalogId: row.catalogId }
      })
    },
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      let param = {}
      Object.assign(param, this.dataForm, {
        page: this.pageIndex,
        limit: this.pageSize
      })
      this.$http({
        url: this.$http.adornUrl('/product/spuinfo/list'),
        method: 'get',
        params: this.$http.adornParams(param)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list
          this.dataList = this.dataList.filter(item => item.seller === this.$store.state.user.name)
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
    addOrUpdateHandle (id) {}
  },
  mounted () {
    this.dataSub = PubSub.subscribe('dataForm', (msg, val) => {
      console.log('~~~~~', val)
      this.dataForm = val
      this.getDataList()
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.dataSub)
  }
}
</script>
