import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import '@/assets/js/swiper-3.4.2.min'
import '@/assets/js/swiper-3.4.2.jquery.min'
import '@/assets/js/iconfont'
import store from './store'
import './plugins/element.js'
import VueSession from 'vue-session'
Vue.use(VueSession)

Vue.config.productionTip = false
Vue.config.devtools = true;
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
