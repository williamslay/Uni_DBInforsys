<template>
   <div style="text-align:center">
       <el-row justify="center" type="flex" >
          <el-col span="3"></el-col>
          <el-col span="18">
      <el-row justify="center" type="flex">
          <h1>账号信息</h1>
      </el-row>
      <el-row justify="center" type="flex">
          <el-form ref="form"  size="medium" >
              <el-col span="11">
              <el-form-item label="用户名">
                  <el-input
                  :readonly="true"
                  v-model="form.username">
                  </el-input>
              </el-form-item>
              </el-col>
               <el-col span="2" style="height:20px">
              </el-col>
              <el-col span="11">
              <el-form-item label="姓名">
                  <el-input
                  v-model="form.name"
                  >
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span="11">
              <el-form-item label="手机号">
                  <el-input
                  v-model="form.phone">
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span="2" style="height:20px">
              </el-col>
              <el-col span="11">
              <el-form-item label="邮箱">
                  <el-input
                  v-model="form.email">
                  </el-input>
              </el-form-item>
              </el-col>
          </el-form>
      </el-row>
     <el-row style="height:50px">
      </el-row>
      <el-row>
         <el-col span="24" >
            <el-button  type="primary"  @click="Change" size="medium">
            修改信息
            </el-button>
            <el-button  type="danger"  @click="ChangePassword" size="medium">
            修改密码
            </el-button>
         </el-col>
      </el-row>
      <el-row style="height:30px">
      </el-row>
        </el-col>
      <el-col span="3"></el-col>
       </el-row> 
    <page ref="page"></page>
   </div>
</template>

<script>
import { ChangeInfo }  from '@/api/user'
import page from './changepw'
export default {
 name:"returnForm",
 components:{
      page
    },
data() {
 return {
        form:{
          id:null,
          username:null,
          name:null,
          email:null,
          phone:null,
        },
        action:null
    }
},
methods: {
    Change(){
        this.form.id=this.$store.getters.user.id
        ChangeInfo(this.form)
        this.$store.getters.user.name=this.form.name
        this.$store.getters.user.email=this.form.email
        this.$store.getters.user.phone=this.form.phone
        this.$message({
            type: 'success',
            message: '修改成功!'
        });
    },
    ChangePassword()
    {
        this.$refs.page.show()
    }
},
created(){
    const { username,name,email,phone}=this.$store.getters.user
    this.form= {username,name,email,phone}
}
}
</script>

<style>

</style>