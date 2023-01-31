<template>
   <div style="text-align:center">
       <el-row justify="center" type="flex" >
         <el-col span="3"></el-col>
          <el-col span="18">
      <el-row justify="center" type="flex">
          <h1>南京邮电大学信息推送申请表</h1>
      </el-row>
      <el-row justify="center" type="flex">
          <el-form ref="form" :model="Form" size="medium" :rules="rules">
              <el-col span="11">
              <el-form-item label="账户信息">
                  <el-input
                  v-model="Form.username"
                   :readonly="true">
                  </el-input>
              </el-form-item>
              </el-col>
               <el-col span="2" style="height:20px">
              </el-col>
              <el-col span="11">
              <el-form-item label="作者">
                  <el-input
                  v-model="Form.author"
                  :readonly="true"
                  >
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span="11">
              <el-form-item label="编辑时间">
                  <el-input
                  v-model="Form.time"
                  :readonly="true"
                  >
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span="2" style="height:20px">
              </el-col>
               <el-col span="11">
              <el-form-item label="发布消息类型" prop="type">
                <el-input
                  v-model="Form.type"
                  :readonly="true"
                  >
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span="11">
              <el-form-item label="发布消息方式" prop="type">
                <el-input
                  v-model="Form.mode"
                  :readonly="true">
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span="2" style="height:20px">
              </el-col>
               <el-col span=11>
              <el-form-item label="发布消息标题" prop="title">
                <el-input v-model="Form.title">
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span="24">
                  <el-form-item>
                    <div class="title">消息内容</div>
                     <div  class="content" v-html="Form.content"></div>
                  </el-form-item>
                  </el-col>
              <el-col span="24">
              </el-col>
               <el-col span="24">
                  <el-form-item  label="审核理由" prop="audit">
                    <el-input
                  v-model="Form.audit"
                  type="textarea"
                  :autosize="{ minRows: 3, maxRows: 6}"
                  >
                  </el-input>
                  </el-form-item>
                  </el-col>
              <el-col span="24">
              </el-col>
          </el-form>
      </el-row>
      <el-row>
         <el-col span="24" >
           <el-button  type="danger"  @click="Disapprove()" size="medium">
            驳回
            </el-button>
            <el-button  type="primary"  @click="Approve()" size="medium">
            通过
            </el-button>
         </el-col>
      </el-row>
          </el-col>
        <el-col span="3"></el-col> 
      </el-row> 
   </div>
</template>

<script>
import { auditMessage } from '@/api/audit'
export default {
  name: "audit",
   components:{
      },
data() {
 return {
        Form:{
          id:null,//订单流水号
          username:null,
          author:null,
          title:null,
          content:'',
          mode:1,
          time:null,
          audit:null,
        },
        }
    },
methods: {
    Disapprove(item){
      auditMessage({id:this.Form.id,audit:this.Form.audit,state:3}).then(res=>{
       this.$router.push(
          {
            name: "SysAdminaudit",
          }) 
      })
      this.$emit("Disapprove",0);
    },
    Approve(){
      auditMessage({id:this.Form.id,audit:this.Form.audit,state:2}).then(res=>{
       this.$router.push(
          {
            name: "SysAdminaudit",
          }) 
      })
    },
},
created(){
  this.Form=this.$route.params.message
  this.Form.mode=["单发","群发",][this.Form.mode]
}
}
</script>

<style>
.none .el-form-item__label{
    color: white;
}
.Approve
{
    width: 20%;
}
.title{
  font-size: 14px;
  color: #606266;
  font-weight: 700;
}
.content{
  border: 1px solid #ccc;
  height:15rem;
  overflow:scroll;
}
</style>