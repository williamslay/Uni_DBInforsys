<template>
  <div class="article">
    <el-button class="back" type="primary" icon="el-icon-back" @click="back" circle></el-button>
    <vue-svg-like class="like" :clicked="clicked" color="red" subColor="#E6A23C" :size="1.5" :duration="1" @click="like"/>
    <div class="info">
      <h1 class="article-title" >{{ article.title }}</h1>
      <div class="article-author">{{ article.author+"  "+article.time }}</div>
      <div class="article-time"></div>
      <div class="article-content " v-html="article.content"></div>
      <el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>
    </div>
  </div>
</template>
<script>
import VueSvgLike from 'vue-svg-like'
import {messageLike} from'@/api/article'
export default {
  name: 'articleInfor',
  components:{
    VueSvgLike
  },
  methods:{
   back(){
    this.$router.push({
      name: "index",
    });
   },
   like(){
    this.clicked = !this.clicked 
    messageLike({mid:this.article.id,uid:this.$store.getters.user.id});
   }
  },
  data(){
    return {
        clicked: false,
        article:{
          id:null,
          title: null,
          type:null,
          author:null,
          time: null,
          content:null,
          likes:0,
        }
    }
  },
  created(){
    this.article=this.$route.params.item;
    if(this.article.likes==1)
     this.clicked=true
  }
}
</script>

<style lang="scss" scoped>
.info{
  width: 95%;
  border-bottom: 1px solid #dbdbdb;
  .article-title{
    text-align: center;
  }
}
.back{
  position:fixed;
    bottom:5rem;
    right:4rem;
}
.like{
  position:fixed;
  bottom:2rem;
  right:4rem;
}

</style>