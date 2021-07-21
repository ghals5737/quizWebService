import Vue from 'vue'
import App from './App.vue'
import store from './store/store'
import router from './router'
import vuetify from './plugins/vuetify'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Gravatar from 'vue-gravatar'
import "./index.css";
import VModal from 'vue-js-modal'

Vue.use(VModal, { dynamic: true })
Vue.use(BootstrapVue)
Vue.component('v-gravatar', Gravatar)

Vue.config.productionTip = false

new Vue({
  store,
  router,
  vuetify,
  render: h => h(App),
}).$mount('#app')
