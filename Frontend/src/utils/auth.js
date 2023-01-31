import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'
const User = {username:'',email:'',phone:'',name:'',id:'',password:'',roleId:''}

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getUser() {
  return Cookies.get(User)
}

export function setUser(UserInfor) {
  return Cookies.set(User, UserInfor)
}

export function removeUser() {
  return Cookies.remove(User)
}