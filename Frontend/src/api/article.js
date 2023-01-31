import request from '@/utils/request'


export function getArticleList(id) {
    return request({
      url: '/message',
      method: 'get',
      params:{id}
    })
  }

export function messageLike(params) {
    return request({
      url: '/message',
      method: 'post',
      params
    })
  }