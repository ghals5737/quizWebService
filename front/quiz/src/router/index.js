import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Main from '../views/Main.vue'
//import CreateQuiz from '../views/CreateQuiz.vue'
import SearchQuiz from '../views/SearchQuiz.vue'
import QuizInfo from '../views/QuizInfo.vue'
import CreateRoom from'../views/CreateRoom.vue'
import RoomDetail from '../views/RoomDetail.vue'
//import SearchRoom from '../views/SearchRoom.vue'
//import PlayQuiz from '../views/PlayQuiz.vue'
import QuizResult from '../views/QuizResult.vue'
import MainTest from '../views/MainTest.vue'
//import SocketTest from '../views/SocketTest.vue'
import TestPlayQuiz from '../views/TestPlayQuiz.vue'
import TailTest from '../components/TailTest.vue'
import WaitingRoom from '../components/WaitingRoom.vue'
import PlayTest from '../components/PlayTest.vue'
import Result from '../components/Result.vue'
import RoomDetailTest from '../components/RoomDetailTest.vue'
import SearchRoomTest from '../components/SearchRoomTest.vue'
//import SearchQuizTest from '../views/SearchQuizTest.vue'

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
    name: 'TailTest',
    component: TailTest
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
    name: 'SearchRoomTest',
    component: SearchRoomTest
  },
  {
    path: '/testplayquiz',
    name: 'TestPlayQuiz',
    component: TestPlayQuiz
  }, 
  {
    path: '/playquiz',
    name: 'PlayTest',
    component: PlayTest
  }, 
  {
    path: '/quizresult',
    name: 'QuizResult',
    component: QuizResult
  },  
  {
    path: '/maintest',
    name: 'MainTest',
    component: MainTest
  },   
  {
    path: '/socket',
    name: 'WaitingRoom',
    component: WaitingRoom
  }, 
  {
    path: '/tail',
    name: 'TailTest',
    component: TailTest
  }, 
  {
    path: '/waiting',
    name: 'WaitingRoom',
    component: WaitingRoom
  }, 
  {
    path: '/play',
    name: 'PlayTest',
    component: PlayTest
  }, 
  {
    path: '/result',
    name: 'Result',
    component: Result
  },
  {
    path: '/detail',
    name: 'RoomDetailTest',
    component: RoomDetailTest
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router