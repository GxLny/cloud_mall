<!--  -->
<template>
  <div class="">
    <div>
      <el-row>
        <el-button type="danger" @click="batchDelete">批量删除</el-button>
      </el-row>
    </div>
    <el-tree
      :data="treeData"
      :props="defaultProps"
      show-checkbox
      node-key="catId"
      :expand-on-click-node="false"
      :default-expanded-keys="expandedKeys"
      ref="treeMenus"
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
          <el-button type="text" size="mini" @click="() => edit(data)">
            编辑
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
    <el-dialog
      :title="title"
      :visible.sync="dialogTableVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="form" :rules="rules" ref="ruleForm">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="图标">
          <el-input v-model="form.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="form.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData()">确 定</el-button>
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
      title: "", //对话框标题
      treeData: [], //树形数据
      expandedKeys: [], //需要展开的节点
      dialogTableVisible: false, //显示对话框
      //表单初始化内容
      form: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        productUnit: "",
        icon: "",
        catId: null,
      },
      url: {
        treeList: "/product/category/list/tree",
        delete: "/product/category/delete",
        add: "/product/category/save",
        queryById: "/product/category/info/",
        update: "/product/category/update",
      },
      //校验规则
      rules: {
        name: [
          { required: true, message: "请输入分类名称", trigger: "blur" },
          {
            min: 2,
            max: 10,
            message: "长度在 2 到 10 个字符",
            trigger: "blur",
          },
        ],
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
    batchDelete() {
      let catIds = [];
      let menusList = this.$refs.treeMenus.getCheckedNodes();
      console.log("menus:", menusList);
      for (let i = 0; i < menusList.length; i++) {
        catIds.push(menusList[i].catId);
      }

      this.$confirm(`是否确认批量删除选中数据?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl(this.url.delete),
            method: "post",
            data: this.$http.adornData(catIds, false),
          }).then(({ data }) => {
            this.expandedKeys = [data];
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            this.getTreeData();
          });
        })
        .catch(() => {});




    },
    //提交表单
    submitData() {
      let { catId, name, icon, productUnit } = this.form;
      //规则判断
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          if (catId == null) {
            //新增表单
            this.add();
          } else {
            //修改表单
            this.$http({
              url: this.$http.adornUrl(this.url.update),
              method: "post",
              data: this.$http.adornData(
                { catId, name, icon, productUnit },
                false
              ),
            }).then(({ data }) => {
              this.$message({
                type: "success",
                message: "修改成功!",
              });
              //关闭对话框
              this.dialogTableVisible = false;
              //重新更新数据
              this.getTreeData();
              //添加成功后重新展开节点
              this.expandedKeys = [this.form.parentCid];
            });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //编辑
    edit(data) {
      this.title = "编辑分类";
      this.dialogTableVisible = true;
      this.$http({
        url: this.$http.adornUrl(this.url.queryById + data.catId),
        method: "get",
      }).then(({ data }) => {
        console.log("data::", data);
        console.log("form::", this.form);
        this.form.name = data.data.name;
        this.form.catId = data.data.catId;
        this.form.icon = data.data.icon;
        this.form.catLevel = data.data.catLevel;
        this.form.parentCid = data.data.parentCid;
        this.form.productUnit = data.data.productUnit;
      });
    },
    //新增数据
    add() {
      console.log("addData::", this.form);
      //初始化数据
      this.title = "新增分类";
      this.$http({
        url: this.$http.adornUrl(this.url.add),
        method: "post",
        data: this.$http.adornData(this.form, false),
      }).then(({ data }) => {
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
      //初始化数据
      this.form.name = "";
      this.form.catId = null;
      this.form.icon = "";
      this.form.catLevel = 0;
      this.form.parentCid = "";
      this.form.productUnit = "";

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
        .catch(() => {});
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