<template>
  <div>
    <el-switch v-model="draggable" active-text="Draggable" inactive-text="Not Draggable"></el-switch>
    <el-button v-if="draggable" @click="batchSave">Batch save</el-button>
    <el-button type="danger" @click="batchDelete">Batch delete</el-button>
    <el-tree :data="menus"
             :props="defaultProps"
             :expand-on-click-node="false"
             show-checkbox node-key="catId"
             :default-expanded-keys="expandedKey"
             draggable
             :allow-drop="allowDrop"
             @node-drop="handleDrop"
    >
     <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button type="text" size="mini" @click="() => edit(data)">Edit</el-button>
          <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">Append</el-button>
          <el-button v-if="node.childNodes.length===0" type="text" size="mini" @click="() => remove(node, data)">Delete</el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <el-form :model="category">
        <el-form-item label="Category name">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Category icon">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="ProductUnit">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitData">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'category',
  data () {
    return {
      draggable: false,
      pCid: [],
      updateNodes: [],
      maxLevel: 0,
      dialogFormVisible: false,
      dialogTitle: '',
      dialogType: '',
      category: {
        name: '',
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        catId: null,
        icon: '',
        productUnit: ''
      },
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      expandedKey: []
    }
  },
  methods: {
    getMenus () {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get'
      }).then(({data}) => {
        this.menus = data.data
      })
    },
    append (data) {
      this.dialogType = 'add'
      this.dialogTitle = 'Add Category'
      this.dialogFormVisible = true
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel * 1 + 1
      this.category.sort = 0
      this.category.showStatus = 1
      this.category.catId = null
      this.category.icon = ''
      this.category.productUnit = ''
      this.category.name = ''
    },
    edit (data) {
      this.dialogType = 'edit'
      this.dialogTitle = 'Update Category'
      this.dialogFormVisible = true
      // 发送请求获取当前节点最新数据
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get'
      }).then(({data}) => {
        this.category.name = data.data.name
        this.category.catId = data.data.catId
        this.category.icon = data.data.icon
        this.category.productUnit = data.data.productUnit
        this.category.parentCid = data.data.parentCid
      })
    },
    remove (node, data) {
      const ids = [data.catId]
      this.$confirm(`Confirm delete ${data.name}?`, 'Tips', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          this.$message({
            type: 'success',
            message: 'Menu delete success!'
          })
          // 刷新出新的菜单
          this.getMenus()
          // 默认展开的菜单
          this.expandedKey = [node.parent.data.catId]
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'delete canceled'
        })
      })
    },
    submitData () {
      if (this.dialogType === 'add') {
        this.addCategory()
      } else if (this.dialogType === 'edit') {
        this.editCategory()
      }
    },
    batchDelete () {
      let catIds = []
      let checkedNodes = this.$refs.menuTree.getCheckedNodes()
      // console.log('被选中的元素', checkedNodes)
      for (let i = 0; i < checkedNodes.length; i++) {
        catIds.push(checkedNodes[i].catId)
      }
      this.$confirm(`Confirm to batch delete 【${catIds}】 menu?`, 'Tips', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl('/product/category/delete'),
            method: 'post',
            data: this.$http.adornData(catIds, false)
          }).then(({ data }) => {
            this.$message({
              message: 'Category batch delete success',
              type: 'success'
            })
            this.getMenus()
          })
        })
        .catch(() => {})
    },
    batchSave () {
      this.$http({
        url: this.$http.adornUrl('/product/category/update/sort'),
        method: 'post',
        data: this.$http.adornData(this.updateNodes, false)
      }).then(({ data }) => {
        this.$message({
          message: 'Menu order changed successfully',
          type: 'success'
        })
        // 刷新出新的菜单
        this.getMenus()
        // 设置需要默认展开的菜单
        this.expandedKey = this.pCid
        this.updateNodes = []
        this.maxLevel = 0
        // this.pCid = []
      })
    },
    // 添加三级分类
    addCategory () {
      console.log(this.category)
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        this.$message({
          type: 'success',
          message: 'Menu saved successfully!'
        })
        // 关闭对话框
        this.dialogFormVisible = false
        // 刷新菜单
        this.getMenus()
        // 默认展开菜单
        this.expandedKey = [this.category.parentCid]
      })
    },
    // 修改三级分类
    editCategory () {
      const {catId, name, icon, productUnit} = this.category
      this.$http({
        url: this.$http.adornUrl('/product/category/update'),
        method: 'post',
        data: this.$http.adornData({catId, name, icon, productUnit}, false)
      }).then(({data}) => {
        this.$message({
          type: 'success',
          message: 'Menu update successfully!'
        })
        // 关闭对话框
        this.dialogFormVisible = false
        // 刷新菜单
        this.getMenus()
        // 默认展开菜单
        this.expandedKey = [this.category.parentCid]
      })
    },
    allowDrop (draggingNode, dropNode, type) {
      // 被拖动的当前节点及所在的父节点总层数不能大于3
      this.countNodeLevel(draggingNode)
      let deep = Math.abs(this.maxLevel - draggingNode.level) + 1
      if (type === 'inner') {
        return deep + dropNode.level <= 3
      } else {
        return deep + dropNode.parent.level <= 3
      }
    },
    countNodeLevel (node) {
      // 找到所有子节点,求出最大深度
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.children.length; i++) {
          if (node.childNodes[i].level > this.maxLevel) {
            this.maxLevel = node.childNodes[i].level
          }
          this.countNodeLevel(node.childNodes[i])
        }
      }
    },
    handleDrop (draggingNode, dropNode, dropType, ev) {
      // 1、当前节点最新的父节点id
      let pCid = 0
      let siblings = null
      if (dropType === 'before' || dropType === 'after') {
        pCid = dropNode.parent.data.catId === undefined ? 0 : dropNode.parent.data.catId
        siblings = dropNode.parent.childNodes
      } else {
        pCid = dropNode.data.catId
        siblings = dropNode.childNodes
      }
      this.pCid.push(pCid)

      // 2、当前拖拽节点的最新顺序，
      for (let i = 0; i < siblings.length; i++) {
        if (siblings[i].data.catId === draggingNode.data.catId) {
          // 如果遍历的是当前正在拖拽的节点
          let catLevel = draggingNode.level
          if (siblings[i].level !== draggingNode.level) {
            // 当前节点的层级发生变化
            catLevel = siblings[i].level
            // 修改他子节点的层级
            this.updateChildNodeLevel(siblings[i])
          }
          this.updateNodes.push({
            catId: siblings[i].data.catId,
            sort: i,
            parentCid: pCid,
            catLevel: catLevel
          })
        } else {
          this.updateNodes.push({ catId: siblings[i].data.catId, sort: i })
        }
      }
    }
  },
  updateChildNodeLevel (node) {
    if (node.childNodes.length > 0) {
      for (let i = 0; i < node.childNodes.length; i++) {
        const cNode = node.childNodes[i].data
        this.updateNodes.push({
          catId: cNode.catId,
          catLevel: node.childNodes[i].level
        })
        this.updateChildNodeLevel(node.childNodes[i])
      }
    }
  },
  created () {
    this.getMenus()
  }
}
</script>

<style scoped>

</style>
