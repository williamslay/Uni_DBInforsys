<template>
  <el-row>
    <Article v-for="article in articleList" :key="article.title" :article="article"></Article>
  </el-row>
</template>

<script>
// 引入组件
import Article from './Article.vue';
import { getArticleList } from '@/api/article'
export default {
  name: 'nforindex',
  // 注册组件
  components: {
    Article
  },
  data: function() {
    return {
      articleList: [
        {
          id:'',
          title: '',
          type:'',
          time: '',
          author:"",
          important:0,
          content:'',
          likes:0,
        },
      ]
    };
  },
  mounted(){
    getArticleList(this.$store.getters.user.id).then(res=>{
      const{data}=res
      this.articleList=data
    })
   
  }
};
</script>

<style lang="scss">

</style>