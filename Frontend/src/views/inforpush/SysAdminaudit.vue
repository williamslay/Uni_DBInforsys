<template>
  <app-container>
    <el-form :inline="true"  class="user-search">
      <el-form-item label="搜索：">
        <el-input style="margin-left: 10px" size="small" v-model="type"  placeholder="消息类型" clearable @clear="flushList">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" @click="AllPass">批量通过</el-button>
      </el-form-item>
    </el-form>
    <ts-table size="small" ref="table" :data="listTable" highlight-current-row  @selection-change="handleSelectionChange" border>
      <el-table-column align="center" type="selection"  width="55">
      </el-table-column>
      <el-table-column  prop="id" label="信息流水号" align="center">
      </el-table-column>
      <el-table-column sortable prop="time" label="申请时间"  align="center"  show-overflow-tooltip>
      </el-table-column>
      <el-table-column  prop="type" label="信息类型" align="center" >
      </el-table-column>
      <el-table-column  prop="author" label="申请人" align="center"  show-overflow-tooltip>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <template>
            <el-button type="primary" size="mini" @click="audit(scope.row)">审核</el-button>
          </template>
        </template>
      </el-table-column>
    </ts-table>
  </app-container>
</template>

<script>
import { getMessageList,auditMessage} from '@/api/audit'
  export default {
    name: 'SysAdminaudit',
      components:{
      },
      data(){
        return{
          type:'',
          selectlist:[],
          listTable:[],
        }
      },
      methods:{
        handleSelectionChange(val){
          this.selectlist=val
        },
        AllPass(){
          if(this.selectlist.length==0)
          {
            this.$message({
             type: 'warning',
             message: '请选中审核通过的信息！'
              });
          }
          else{
            this.selectlist.forEach(item=>{
               auditMessage({id:item.id,audit:"无",state:2})
            })
            setTimeout(this.flushList,300)
          }
        },
        audit(item)
        {
          this.$router.push({
            name: "audit",
            params: {
             message:item
            },
          });
        },
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
           getMessageList({type:this.type}).then(response => {
          const { data } = response
          this.listTable=data.filter(item => item.type==this.type )
         })
         }
        },
        flushList(){
        this.listTable=[];
          getMessageList().then(response => {
          const { data } = response
          this.listTable=data
       })
      },
      },
      mounted() {
        this.flushList()
      }
  }
</script>

<style scoped>

</style>
