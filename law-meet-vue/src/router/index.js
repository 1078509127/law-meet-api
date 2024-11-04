import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/Layout'

/** note: Submenu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    perms: ['GET /aaa','POST /bbb']     will control the page perms (you can set multiple perms)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/
export const constantRouterMap = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/authredirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', noCache: true }
      }
    ]
  }
]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '/cert',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'userManage',
    meta: {
      title: '用户管理',
      icon: 'peoples'
    },
    children: [
      {
        path: 'cert',
        component: () => import('@/views/cert/cert'),
        name: 'cert',
        meta: {
          perms: ['GET /admin/user/list'],
          title: '律师信息',
          icon: 'user',
          noCache: true
        }
      },
      {
        path: 'meet',
        component: () => import('@/views/cert/meet'),
        name: 'meet',
        meta: {
          perms: ['GET /admin/account/list'],
          title: '律师预约',
          icon: 'money',
          noCache: true
        }
      },
    ]
  },
  {
    path: '/export',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'mallManage',
    meta: {
      title: '数据导出',
      icon: 'shopping'
    },
    children: [
      {
        path: 'cert',
        component: () => import('@/views/export/cert'),
        name: 'cert',
        meta: {
          title: '导出律师信息',
          icon: 'international',
          noCache: true
        }
      },
      {
        path: 'meet',
        component: () => import('@/views/export/meet'),
        name: 'meet',
        meta: {
          perms: ['GET /admin/brand/list', 'POST /admin/brand/create', 'GET /admin/brand/read', 'POST /admin/brand/update', 'POST /admin/brand/delete'],
          title: '导出预约信息',
          icon: 'example',
          noCache: true
        }
      },
      
    ]
  },

  // {
  //   path: '/goods',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   alwaysShow: true,
  //   name: 'goodsManage',
  //   meta: {
  //     title: '审核记录日志',
  //     icon: 'tab'
  //   },
  //   children: [
  //     {
  //       path: 'list',
  //       component: () => import('@/views/goods/list'),
  //       name: 'goodsList',
  //       meta: {
  //         perms: ['GET /admin/goods/list', 'POST /admin/goods/delete'],
  //         title: '律师信息审核',
  //         icon: 'list',
  //         noCache: true
  //       }
  //     },
  //     {
  //       path: 'create',
  //       component: () => import('@/views/goods/create'),
  //       name: 'goodsCreate',
  //       meta: {
  //         perms: ['POST /admin/goods/create'],
  //         title: '预约信息审核',
  //         icon: 'link',
  //         noCache: true
  //       }
  //     },
     
  //   ]
  // },
 

  // {
  //   path: '/sys',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   alwaysShow: true,
  //   name: 'sysManage',
  //   meta: {
  //     title: '系统管理',
  //     icon: 'component'
  //   },
  //   children: [
  //     {
  //       path: 'admin',
  //       component: () => import('@/views/sys/admin'),
  //       name: 'admin',
  //       meta: {
  //         perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
  //         title: '预约通知',
  //         icon: 'people',
  //         noCache: true
  //       }
  //     },
  //     {
  //       path: 'role',
  //       component: () => import('@/views/sys/role'),
  //       name: 'role',
  //       meta: {
  //         perms: ['GET /admin/role/list', 'POST /admin/role/create', 'POST /admin/role/update', 'POST /admin/role/delete', 'GET /admin/role/permissions', 'POST /admin/role/permissions'],
  //         title: '用户权限',
  //         icon: 'qq',
  //         noCache: true
  //       }
  //     },
  //   ]
  // },

  // {
  //   path: '/stat',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   alwaysShow: true,
  //   name: 'statManage',
  //   meta: {
  //     title: '统计',
  //     icon: 'chart'
  //   },
  //   children: [
  //     {
  //       path: 'user',
  //       component: () => import('@/views/stat/user'),
  //       name: 'statUser',
  //       meta: {
  //         perms: ['GET /admin/stat/user'],
  //         title: '用户统计',
  //         icon: 'user',
  //         noCache: true
  //       }
  //     },
  //     {
  //       path: 'order',
  //       component: () => import('@/views/stat/order'),
  //       name: 'statOrder',
  //       meta: {
  //         perms: ['GET /admin/stat/order'],
  //         title: '预约统计',
  //         icon: 'shopping',
  //         noCache: true
  //       }
  //     },
  //     {
  //       path: 'goods',
  //       component: () => import('@/views/stat/goods'),
  //       name: 'statGoods',
  //       meta: {
  //         perms: ['GET /admin/stat/goods'],
  //         title: '使用趋势',
  //         icon: 'table',
  //         noCache: true
  //       }
  //     }
  //   ]
  // },
  
  {
    path: '/profile',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    children: [
      {
        path: 'password',
        component: () => import('@/views/profile/password'),
        name: 'password',
        meta: { title: '修改密码', noCache: true }
      }
    ],
    hidden: true
  },

  { path: '*', redirect: '/404', hidden: true }
]
