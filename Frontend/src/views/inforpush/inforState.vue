<template>
  <app-container>
    <el-form :inline="true" :model="form" class="user-search">
      <el-form-item label="搜索:">
        <el-input placeholder="请输入消息类型" clearable @clear="flushList" v-model="type">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search" >搜索</el-button>
      </el-form-item>
    </el-form>
    <ts-table border ref="table" :data="listTable">
      <el-table-column align="center" prop="id" label="信息流水号"></el-table-column>
      <el-table-column sortable prop="time" label="信息编辑时间"  align="center"  show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="type" label="信息类型"></el-table-column>
      <el-table-column align="center" prop="title" label="信息标题" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" prop="state" label="信息审核状态">
        <template slot-scope="{ row }">
        <span
        :class="{waitS:row.state ===0,waitE:row.state ===1 ,fail:row.state===3,sucess:row.state===2}">
          {{[
          "待提交",
          "待审核",
          "审核通过已发布",
          "审核驳回",
          ][row.state]
          }}</span> 
        </template>
      </el-table-column>
      <el-table-column align="center" prop="audit" label="审核理由" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="text" v-if="scope.row.state!=1&&scope.row.state!=2" @click="edit(scope.row)">编辑</el-button>
          <el-button type="text" v-if="scope.row.state!=1" style="color:red" @click="dele(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </ts-table>
  </app-container>
</template>

<script>
  import { getMessagState,messageDelet}  from '@/api/audit'
  export default {
    name: 'index',
    components:{
    },
    data(){
      return{
        type:'',
        options:[],
        listTable:[]
      }
    },
    methods:{
      search(){
          if(this.type=="")
         {
          this.$message({
             type: 'warning',
             message: '搜索值不能为空!'
              });
         }else
         {
          this.listTable=[];
          getMessagState({username:this.$store.getters.name}).then(response => {
          const { data } = response
          this.listTable=data.filter(item => item.type==this.type )
         })
         }
      },
      edit(item){
        this.$router.push({
            name: "edit",
            params: {
             item:item
            },
          });
      },
      flushList(){
        this.listTable=[];
          getMessagState({username:this.$store.getters.name}).then(response => {
          console.log(response)
          const { data } = response
          this.listTable=data.map(item=>
          {
            if(item.audit==null)
              item.audit="未提交或待审核"
            return item
          }
          )
       })
      },
      dele(item){
        this.$confirm('是否确认删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          messageDelet(item.id).then(res=>{
            if(res.code==200)
            {
              this.$message({
              type: 'success',
               message: '删除成功!'
              });
              setTimeout(this.flushList, 300)
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
      }
    },
    mounted(){
      this.flushList()
    }
  }
</script>

<style scoped>
.waitS{
  color:#409EFF;
}
.waitE{
  color:#E6A23C;
}
.fail{
  color: red
}
.sucess{
  color: #429e73
}
</style>