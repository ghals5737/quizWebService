import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Main from '../views/Main.vue'
import CreateQuiz from '../views/CreateQuiz.vue'
import SearchQuiz from '../views/SearchQuiz.vue'
import QuizInfo from '../views/QuizInfo.vue'
import CreateRoom from'../views/CreateRoom.vue'
import RoomDetail from '../views/RoomDetail.vue'
import SearchRoom from '../views/SearchRoom.vue'

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
    path: '/searchquiz',
    name: 'SearchQuiz',
    component: SearchQuiz
  },
  {
    path: '/quizinfo',
    name: 'QuizInfo',
    component: QuizInfo
  },  
  {
    path: '/createroom',
    name: 'CreateRoom',
    component: CreateRoom
  },
  {
    path: '/roomdetail',
    name: 'RoomDetail',
    component: RoomDetail
  },   
  {
    path: '/searchroom',
    name: 'SearchRoom',
    component: SearchRoom
  },     
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router