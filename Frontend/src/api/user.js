import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function getInfo(data) {
  return request({
    url: '/admin/userInfo',
    method: 'get',
    params: { data }
  })
}

export function ChangeInfo(data) {
  return request({
    url: '/admin/userInfo',
    method: 'post',
    data
  })
}

export function DeletInfo(id) {
  return request({
    url: '/admin/userInfo',
    method: 'delete',
    params: { id }
  })
}

export function changePassword(params) {
  return request({
    url: '/changePassword',
    method: 'post',
    params 
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
