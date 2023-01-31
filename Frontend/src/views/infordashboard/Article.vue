<template>
  <div class="article" @click="view(article)">
    <div class="article-cover">
    </div>
    <div class="info">
      <div class="head">
        <div class="article-type">{{ article.type }}</div>
        <div class="article-title">{{ article.title }}</div>
        <div v-if="article.important">{{ "🔥🔥🔥"}}</div>
      </div>
      <div class="nest">
        <div class="article-author">{{"发布方："+article.author }}</div>
        <div class="article-time">{{ formatTime }}</div>
      </div>
      <div class="article-content">{{ brief }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Article',
  props: {
    article: Object
  },
  methods:{
    view(item){
         this.$router.push({
            name: "articleInfor",
            params: {
             item:item
            },
          });
    }
  },
  computed: {
    // 对时间进行格式化
    formatTime: function() {
      if (this.article) {
        const dt = new Date(this.article.time)
        const month = dt.getMonth()
        const date = dt.getDate()
        return `${month}月${date}日`
      }
      return '';
    },
    brief: function() {
      let re1 = new RegExp('<.+?>', 'g')
      let msg = this.article.content.replace(re1, '')
      msg = msg.replace('&nbsp;', '')
      return msg.substr(0,90) + '...'
    }
  }
}
</script>

<style lang="scss" scoped>
.article {
  width: 100%;
  display: flex;
  padding: 10px 0;
  border-bottom: 1px solid #dbdbdb;
  &:last-child {
    border: none;
  }
  .head,.nest{
    flex: 1 2 auto;
    display: flex;
  }
  .article-type {
    border: 2px solid #409EFF;
    border-radius: 5px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
    width: 40px;
    background-color: #409EFF;
  }
  .article-title {
    border: 2px solid white;
    font-size: 16px;
    font-weight: 500;
    color: #42b983;
    text-align: center;
    display: flex;
  }
  .article-cover {
    flex: none;
    width: 10px;
    margin-right: 10px;
  }
  .article-content, .article-time {
    font-size: 14px;
  }
}
.article-time{
    margin: 10px 10px;
    color: #c5c5c5;
  }
.article-author{
  margin: 10px 0;
  font-size: 1rpem;
}
</style>