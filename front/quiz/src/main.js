import Vue from 'vue'
import App from './App.vue'
import store from './store/store'
import router from './router'
import vuetify from './plugins/vuetify'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Gravatar from 'vue-gravatar'

Vue.use(BootstrapVue)
Vue.component('v-gravatar', Gravatar)

Vue.config.productionTip = false

new Vue({
  store,
  router,
  vuetify,
  render: h => h(App),
}).$mount('#app')
