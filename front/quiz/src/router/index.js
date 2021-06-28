import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Main from '../views/Main.vue'
import CreateQuiz from '../views/CreateQuiz.vue'
import SearchQuiz from '../views/SearchQuiz.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/main',
    name: 'Main',
    component: Main
  },    
  {
    path: '/createquiz',
    name: 'CreateQuiz',
    component: CreateQuiz
  },    
  {
    path: '/SearchQuiz',
    name: 'SearchQuiz',
    component: SearchQuiz
  },    
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router