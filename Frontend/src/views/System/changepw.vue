<template>
  <el-dialog title="" :visible.sync="dialogVisible" :close-on-press-escape="false" :close-on-click-modal="false" width="500px" height="600px">
    <el-form   label-position="right" label-width="80px" :rules="rules" :model="ruleForm">
      <el-form-item label="原密码" prop="oldpassword">
        <el-input size="small"  v-model=" ruleForm.oldpassword" ></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newpassword">
        <el-input size="small"  v-model="ruleForm.newpassword"></el-input>
      </el-form-item>
    </el-form>
    <template>
      <span slot="footer" class="dialog-footer" >
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary"  @click="onSubMint">确 定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
  import { changePassword }  from '@/api/user'

  export default {
    data(){
      return{
        dialogVisible:false,
        ruleForm: {
          oldpassword:'',
          newpassword:'',
        },
        rules:{
          oldpassword: [
            { required: true, message: '不能为空！',trigger: 'blur' },
          ],
          newpassword: [
            { required: true, message: '不能为空！',trigger: 'blur' },
          ],
        }
      }
    },
    methods:{
      show(){
        this.dialogVisible=true
      },
      onSubMint(){
        changePassword({id:this.$store.getters.user.id,oldPassword:this.ruleForm.oldpassword,newPassword:this.ruleForm.newpassword}).then(res =>{
          if (res.code==200)
          {
            this.$message({
             type: 'success',
             message: '修改成功!'
              });
            this.dialogVisible=false
          }
        })
       
      },
      },
     
    }
</script>

<style scoped>

</style>