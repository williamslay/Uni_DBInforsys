<template>
  <el-dialog title="" :visible.sync="dialogVisible" :close-on-press-escape="false" :close-on-click-modal="false" width="500px" height="600px">
    <el-form ref="myForm" :model="form" label-position="right" label-width="80px" :rules="rules">
      <el-form-item label="用户名" prop="username">
        <el-input size="small" :disabled="this.action=='1'" v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" v-if="this.action=='0'" prop="password">
        <el-input size="small"  v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input size="small"  v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input size="small" v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input size="small" v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="角色">
          <el-select v-model="templateValue"
            style="width:100%"
            placeholder="请选择" 
            @change="selectRo">
               <el-option
               v-for="item in RoleOpthions"
               :key="item.value"
               :label="item.label"
               :value="item.value">
               </el-option>
          </el-select>
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
 import { ChangeInfo }  from '@/api/user'
  export default {
    data(){
      return{
        dialogVisible:false,
        templateValue:1,
        RoleOpthions: [{
          value: 1,
          label: '系统管理员'
        }, {
          value: 2,
          label: '部门管理员'
        }, {
          value: 3,
          label: '学生'
        }, {
          value: 4,
          label: '家长'
        }],
        form:{
          username:null,
          password:null,
          name:null,
          email:null,
          phone:null,
          roleId:null,
          id:null
        },
        action:null,
        rules: {
          username: [
            { required: true, message: '必填项',trigger: 'blur' },
          ],
          password: [
            { required: true,message: '必填项',trigger: 'blur' }
          ]
        }
      }
    },
    methods:{
      show(item,action){
        if(action=='1'){
          this.form.username=item.username
          this.form.name=item.name
          this.form.email=item.email
          this.form.phone=item.phone
          this.form.roleId=item.role
          this.form.id=item.id
        }else{
          this.form.username=null
          this.form.name=null
          this.form.email=null
          this.form.phone=null
          this.form.password=null
          this.form.roleId=null
          this.form.id=null
        }
        this.templateValue=this.form.roleId
        this.action=action
        this.dialogVisible=true
      },
      onSubMint(){
        ChangeInfo(this.form)
        this.$emit("Infochanged")
        this.dialogVisible=false
      },
      selectRo(currentValue){
        this.form.roleId=currentValue
      },
      },
     
    }
</script>

<style scoped>

</style>