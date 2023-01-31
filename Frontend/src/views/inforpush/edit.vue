<template>
   <div style="text-align:center">
       <el-row justify="center" type="flex" >
         <el-col span=3></el-col>
          <el-col span=18>
      <el-row justify="center" type="flex">
          <h1>南京邮电大学信息推送申请表</h1>
      </el-row>
      <el-row justify="center" type="flex">
          <el-form ref="form" :model="Form" size="medium">
              <el-col span=11>
              <el-form-item label="申请人用户名">
                  <el-input
                  v-model="Form.username"
                   :readonly="true">
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span=2 style="height:20px">
              </el-col>
              <el-col span=11>
              <el-form-item label="申请人姓名">
                  <el-input
                  v-model="Form.author"
                  :readonly="true"
                  >
                  </el-input>
              </el-form-item>
              </el-col>
               <el-col span=11>
              <el-form-item label="发布消息类型" prop="type" >
                <el-input v-model="Form.type" maxlength="2" show-word-limit>
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span=2 style="height:20px">
              </el-col>
               <el-col span=11>
              <el-form-item label="发布消息标题" prop="title">
                <el-input v-model="Form.title">
                  </el-input>
              </el-form-item>
              </el-col>
              <el-col span=24>
                  <el-form-item  label="发送对象">
                    <el-cascader-panel :options="mode" @change="handleChange" :props="props"></el-cascader-panel>
                  </el-form-item>
                  </el-col>
               <el-col span=24>
                  <el-form-item  label="消息内容">
                  <quill-editor ref="text" v-model="Form.content" style="height: 140px" class="myQuillEditor" :options="editorOption" />
                  </el-form-item>
                  </el-col>
              <el-col span=24>
              </el-col>
          </el-form>
      </el-row>
     <el-row style="height:90px">
      </el-row>
      <el-row>
         <el-col span=24 >
           <el-button  type="danger"  @click="Save" size="medium">
            保存
            </el-button>
            <el-button  type="primary"  @click="Submit" size="medium">
            提交
            </el-button>
         </el-col>
      </el-row>
          </el-col>
        <el-col span=3></el-col> 
      </el-row> 
   </div>
</template>

<script>
import {quillEditor} from 'vue-quill-editor'
import {getInfo}  from '@/api/user'
import { messageChange }  from '@/api/audit'
export default {
  name:"Form",
   components:{
        quillEditor
      },
computed:{
  mode(){
    return this.setOptions()
  }
},
data() {
 return {
        Form:{
          id:null,//订单流水号
          author:null,
          username:null,
          title:null,
          content:null,
          mode:null,
          time:null
        },
        props:{

        },
        opthion:[{
          value: 0,
          label: '单发',
          children: [{
            value: 1,
            label: '系统管理员',
            children: []
          }, {
            value: 2,
            label: '部门管理员',
            children: []
          },{
            value: 3,
            label: '学生',
            children: []
          },{
            value: 4,
            label: '家长',
            children: []
          }]
        }, {
          value: 1,
          label: '群发',
        }]
        }
    },
methods: {
    handleChange(value){
        if(value[0]==1)
        {
            this.Form.mode=1;
            this.Form.object='';
        }
         else{
            this.Form.mode=0;
            this.Form.object=value[value.length-1];
         }
    },
    Save(){
      this.Form.time= this.getCurrentTime()
      messageChange(this.Form).then(res=>{
        if (res.code==200)
        {
         this.$emit("Submit");
         this.$router.push(
          {
            name: "inforState",
          })
        }
      })
      this.$emit("Save",0);
    },
    Submit(){
      this.Form.time= this.getCurrentTime() 
      this.Form.state= 1
      messageChange(this.Form).then(res=>{
        if (res.code==200)
        {
         this.$emit("Submit");
         this.$router.push(
          {
            name: "inforState",
          })
        }
      })
    },
    setOptions()
    {
      getInfo().then(response => {
          const { data } = response
          data.userList.map(item => {
            let obj = {
            value:item.id,
            label:item.username+item.name
          }
          switch(item.roleId){
            case 1:
             this.opthion[0].children[0].children.push(obj)
             break;
            case 2:
             this.opthion[0].children[1].children.push(obj)
            break;
            case 3:
             this.opthion[0].children[2].children.push(obj)
            break;
            case 4:
             this.opthion[0].children[3].children.push(obj)
            break;
          }
       })
       })
       return this.opthion
    },
    getCurrentTime() {
    let date = new Date();//当前时间
    let month = this.zeroFill(date.getMonth() + 1);//月
    let day = this.zeroFill(date.getDate());//日
    let hour = this.zeroFill(date.getHours());//时
    let minute = this.zeroFill(date.getMinutes());//分
    let curTime = date.getFullYear() + "-" + month + "-" + day
            + " " + hour + ":" + minute;
    return curTime;
    },
    zeroFill(i){
    if (i >= 0 && i <= 9) {
        return "0" + i;
    } else {
        return i;
    }
}
},
created(){
    if(this.$route.params.item)
      this.Form=this.$route.params.item;
    else{
      this.Form={username:this.$store.getters.name,author:this.$store.getters.user.name}
    }
    
}
}
</script>

<style>
.none .el-form-item__label{
    color: white;
}
.Submit
{
    width: 20%;
}
</style>