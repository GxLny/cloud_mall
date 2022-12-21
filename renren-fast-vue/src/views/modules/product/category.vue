<!--  -->
<template>
  <div class="">
    <el-tree
      :data="treeData"
      :props="defaultProps"
      show-checkbox
      node-key="catId"
      :expand-on-click-node="false"
      :default-expanded-keys="expandedKeys"
      @node-click="handleNodeClick"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >
            添加
          </el-button>
          <el-button
            v-if="node.childNodes.length == 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >
            删除
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog title="添加" :visible.sync="dialogTableVisible">
      <el-form :model="form">
        <el-form-item label="分类名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  data() {
    //这里存放数据
    return {
      treeData: [], //树形数据
      expandedKeys: [], //需要展开的节点
      dialogTableVisible: false, //显示对话框
      //表单
      form: { name: "", parentCid: 0, catLevel: 0, showStatus: 1, sort: 0 },
      url: {
        treeList: "/product/category/list/tree",
        delete: "/product/category/delete",
        add: "/product/category/save",
      },
      defaultProps: {
        children: "children",
        label: "name",
      },
    };
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.getTreeData();
  },
  //方法集合
  methods: {
    //提交数据
    add() {
      console.log("addData::", this.form);
      this.$http({
        url: this.$http.adornUrl(this.url.add),
        method: "post",
        data: this.$http.adornData(this.form, false),
      }).then(({ data }) => {
        this.expandedKeys = [data];
        this.$message({
          type: "success",
          message: "添加成功!",
        });
        //关闭对话框
        this.dialogTableVisible = false;
        this.getTreeData();
        //添加成功后重新展开节点
        this.expandedKeys = [this.form.parentCid];
      });
    },
    //打开对话框
    append(data) {
      this.dialogTableVisible = true;
      //当点击某个节点添加时，会得到当前节点的信息
      //获取到当前节点的id作为新增节点父id
      this.form.parentCid = data.catId;
      //设置当前节点的等级，获取父级节点等级，加1即可得到当前的等级
      //*1+1 字符转int
      this.form.catLevel = data.catLevel * 1 + 1;
    },
    //删除节点
    remove(node, data) {
      this.$confirm(`是否确认删除【${data.name}】?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let ids = [data.catId];
          this.$http({
            url: this.$http.adornUrl(this.url.delete),
            method: "post",
            data: this.$http.adornData(ids, false),
          }).then(({ data }) => {
            this.expandedKeys = [data];
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            this.getTreeData();
            //删除成功后重新展开节点
            this.expandedKeys = [node.parent.data.catId];
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    handleNodeClick(data) {
      console.log(data);
    },
    getTreeData() {
      this.$http({
        url: this.$http.adornUrl(this.url.treeList),
        method: "get",
      }).then((res) => {
        console.log("res:", res);
        this.treeData = res.data.data;
      });
    },
  },

  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css类
</style>