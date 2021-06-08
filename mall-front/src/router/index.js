import Vue from 'vue'
import VueRouter from 'vue-router'
import MallHome from "@/views/MallHome";
import MallSearch from "@/views/MallSearch"
import MallItem from "@/views/MallItem";
import MallLogin from "@/views/MallLogin";
import MallRegister from "@/views/MallRegister";
import MallRegisterSeller from "@/views/MallRegisterSeller";
import MallCart from "@/views/MallCart";
import MallCartSuccess from "@/views/MallCartSuccess";
import MallOrder from "@/views/MallOrder"
import MallSpace from "@/views/MallSpace";
import MallComment from "@/views/MallComment";

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MallHome',
    component: MallHome
  },
  {
    path: '/login',
    name: 'MallLogin',
    component: MallLogin
  },
  {
    path: '/register',
    name: 'MallRegister',
    component: MallRegister
  },
  {
    path: '/registerSeller',
    name: 'MallRegisterSeller',
    component: MallRegisterSeller
  },
  {
    path: '/search',
    name: 'MallSearch',
    component: MallSearch
  },
  {
    path: '/item/:product_id',
    name: 'MallItem',
    component: MallItem
  },
  {
    path: '/cart',
    name: 'MallCart',
    component: MallCart
  },
  {
    path: '/addCart',
    name: 'MallCartSuccess',
    component: MallCartSuccess
  },
  {
    path: '/order',
    name: 'MallOrder',
    component: MallOrder
  },
  {
    path: '/space',
    name: 'MallSpace',
    component: MallSpace
  },
  {
    path: '/comment/:product_id',
    name: 'MallComment',
    component: MallComment
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
