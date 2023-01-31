<template>
  <app-container>
    <el-form :inline="true"  class="user-search">
      <el-form-item label="搜索:">
        <el-input v-model="name" size="small" clearable filterable placeholder="请输入用户名" @clear="flushList">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="add">增加</el-button>
      </el-form-item>
    </el-form>
    <ts-table border ref="table" :data="listTable">
      <el-table-column align="center" prop="username" label="用户名"></el-table-column>
      <el-table-column align="center" prop="name" label="姓名"></el-table-column>
      <el-table-column align="center" prop="email" label="邮箱"></el-table-column>
      <el-table-column align="center" prop="phone" label="电话"></el-table-column> 
      <el-table-column align="center" prop="roleId" label="角色">
        <template scope="scope">
        <span>
          {{[
          "系统管理员",
          "部门管理员",
          "学生",
          "家长",
          ][scope.row.roleId-1]|| '未知' 
          }}</span> 
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="text" @click="eit(scope.row)">编辑</el-button>
          <el-button type="text" style="color:red" @click="dele(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </ts-table>
    <page ref="page" @Infochanged='Infochanged'></page>
  </app-container>
</template>

<script>
  import { getInfo,DeletInfo }  from '@/api/user'
  import page from './page'
  export default {
    name: 'index',
    components:{
      page
    },
    updated() {
    },
    data(){
      return{
        name:'',
        listTable:[],
      }
    },
    methods:{
      search(){
         if(this.name=="")
         {
          this.$message({
             type: 'warning',
             message: '搜索值不能为空!'
              });
         }else
         {
          this.listTable=[];
          getInfo({username:null}).then(response => {
          const { data } = response
          let res=data.userList.find((item) => item.username==this.name )
          this.listTable.push(res)
         })
         }
      },
      add(){
        this.$refs.page.show(null,'0')
      },
      eit(item){
        this.$refs.page.show(item,'1')
      },
      flushList(){
        this.listTable=[];
          getInfo({username:null}).then(response => {
          const { data } = response
          this.listTable=data.userList.filter(item => item.username!=this.$store.getters.name)
       })
      },
      dele(item){
        this.$confirm('是否确认删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          DeletInfo(item.id).then(
            res =>{
            if (res.code==200){
             setTimeout(this.flushList, 300)
             this.$message({
             type: 'success',
             message: '删除成功!'
              });
              }
            }
          );
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
      },
      Infochanged(){
        setTimeout(this.flushList, 300)
      }
    },
    mounted(){
      this.flushList();
    }
  }
</script>

<style scoped>

</style>
