import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    meta: { title: '消息面板', icon: 'link' },
    children: [
      {
        path: '/index',
        component: () => import('@/views/infordashboard/index'),
        name: 'index',
        meta: { title: '消息面板' },
      },
      {
        path: 'articleInfor',
        component: () => import('@/views/infordashboard/infor'),
        name: 'articleInfor',
        meta: { title: '消息详情' },
        hidden:true,
      }
    ]
  },
]


export const asyncRoutes = [
  {
    path: '/inforpush',
    component: Layout,
    redirect: '/inforpush/inforState',
    name: 'inforpush',
    meta: {
      title: '信息推送管理',
      icon: 'nested',
      roles:[1,2]
    },
    children: [
      {
        path: 'edit',
        component: () => import('@/views/inforpush/edit'),
        name: 'edit',
        meta: { title: '推送信息编辑',roles:[1,2] }
      },
      {
        path: 'inforState',
        component: () => import('@/views/inforpush/inforState'),
        name: 'inforState',
        meta: { title: '推送信息状态',roles:[1,2] }
      },
      {
        path: 'SysAdminaudit',
        component: () => import('@/views/inforpush/SysAdminaudit'),
        name: 'SysAdminaudit',
        meta: { title: '推送消息审核',roles:[1] }
      },
      {
        path: 'audit',
        component: () => import('@/views/inforpush/audit'),
        name: 'audit',
        hidden:true,
        meta: { title: '审核',roles:[1] }
      }
    ]
  },
  {
    path: '/System',
    component: Layout,
    redirect: '/System/account',
    name: 'System',
    meta: {
      title: '系统管理',
      icon: 'el-icon-menu',
      roles:[1,2,3,4]
    },
    children: [
      {
        path: 'account',
        component: () => import('@/views/System/account'), // Parent router-view
        name: 'account',
        meta: { title: '账号管理',roles:[1,2]},
      },
      {
        path: 'infor',
        component: () => import('@/views/System/infor'), 
        name: 'infor',
        meta: { title: '账号信息管理',roles:[1,2,3,4] },
      },
    ]
  },
  { path: '*', redirect: '/404', hidden: true },
]


const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
