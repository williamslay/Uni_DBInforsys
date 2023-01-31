const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  name: state => state.user.name,
  user: state => state.user.user,
  role:state => state.user.role,
  addRouters: state => state.permission.routes,
  permission_routes:state => state.permission.routes,
}
export default getters
