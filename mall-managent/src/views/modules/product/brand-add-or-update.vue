<template>
  <el-dialog
    :title="!dataForm.id ? 'New' : 'Update'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="140px"
    >
      <el-form-item label="Brand name" prop="name">
        <el-input v-model="dataForm.name" placeholder="Brand name"></el-input>
      </el-form-item>
      <el-form-item label="Brand logo URL" prop="logo">
        <!-- <el-input v-model="dataForm.logo" placeholder="品牌logo地址"></el-input> -->
        <single-upload v-model="dataForm.logo"></single-upload>
      </el-form-item>
      <el-form-item label="Description" prop="descript">
        <el-input v-model="dataForm.descript" placeholder="Description"></el-input>
      </el-form-item>
      <el-form-item label="ShowStatus" prop="showStatus">
        <el-switch
          v-model="dataForm.showStatus"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
        ></el-switch>
      </el-form-item>
      <el-form-item label="FirstLetter" prop="firstLetter">
        <el-input v-model="dataForm.firstLetter" placeholder="FirstLetter"></el-input>
      </el-form-item>
      <el-form-item label="Sort" prop="sort">
        <el-input v-model.number="dataForm.sort" placeholder="Sort"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">Cancel</el-button>
      <el-button type="primary" @click="dataFormSubmit()">Confirm</el-button>
    </span>
  </el-dialog>
</template>

<script>
import SingleUpload from '@/components/upload/singleUpload'
export default {
  components: { SingleUpload },
  data () {
    return {
      visible: false,
      dataForm: {
        brandId: 0,
        name: '',
        logo: '',
        descript: '',
        showStatus: 1,
        firstLetter: '',
        sort: 0
      },
      dataRule: {
        name: [{ required: true, message: 'Brand name NULL', trigger: 'blur' }],
        logo: [
          { required: true, message: 'Brand logo URL NULL', trigger: 'blur' }
        ],
        descript: [
          { required: true, message: 'Description NULL', trigger: 'blur' }
        ],
        showStatus: [
          {
            required: true,
            message: 'ShowStatus[0-Not show；1-Show] NULL',
            trigger: 'blur'
          }
        ],
        firstLetter: [
          {
            validator: (rule, value, callback) => {
              if (value === '') {
                callback(new Error('FirstLetter NULL'))
              } else if (!/^[a-zA-Z]$/.test(value)) {
                callback(new Error('FirstLetter must be a letter'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        sort: [
          {
            validator: (rule, value, callback) => {
              if (value === '') {
                callback(new Error('Sort NULL'))
              } else if (!Number.isInteger(value) || value < 0) {
                callback(new Error('Sort must > 0'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    init (id) {
      this.dataForm.brandId = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.brandId) {
          this.$http({
            url: this.$http.adornUrl(
              `/product/brand/info/${this.dataForm.brandId}`
            ),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.name = data.brand.name
              this.dataForm.logo = data.brand.logo
              this.dataForm.descript = data.brand.descript
              this.dataForm.showStatus = data.brand.showStatus
              this.dataForm.firstLetter = data.brand.firstLetter
              this.dataForm.sort = data.brand.sort
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/product/brand/${!this.dataForm.brandId ? 'save' : 'update'}`
            ),
            method: 'post',
            data: this.$http.adornData({
              brandId: this.dataForm.brandId || undefined,
              name: this.dataForm.name,
              logo: this.dataForm.logo,
              descript: this.dataForm.descript,
              showStatus: this.dataForm.showStatus,
              firstLetter: this.dataForm.firstLetter,
              sort: this.dataForm.sort
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    }
  }
}
</script>
