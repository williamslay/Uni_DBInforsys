import request from '@/utils/request'

export function getMessageList(params) {
    return request({
      url: '/sysadmin/message',
      method: 'get',
      params
    })
  }

  export function auditMessage(params) {
    return request({
      url: '/admin/audit',
      method: 'post',
      params
    })
  }

  export function getMessagState(params) {
    return request({
      url: '/admin/message',
      method: 'get',
      params
    })
  }


  export function messageChange(data) {
    return request({
      url: '/admin/message',
      method: 'post',
      data
    })
  }

  export function messageDelet(id) {
    return request({
      url: '/admin/message',
      method: 'delete',
      params: { id }
    })
  }