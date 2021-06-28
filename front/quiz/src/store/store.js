import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router/index'
import axios from 'axios'

Vue.use(Vuex);

let https = require('https')
const instance = axios.create({
  baseURL: 'http://localhost:8700/api',
  timeout: 120000,
  httpsAgent: new https.Agent({
    rejectUnauthorized: false
  })
});


const store = new Vuex.Store({
    state: {
        userInfo: null,
    },
    getters: {
        USER: state => state.userInfo,        
    },
    mutations: {
        addUser: (state,{user})=>{
            state.userInfo=user
        },
        updateUser: (state,{userId,userNo})=>{
            state.userInfo={userId,userNo}
        },
    },
    actions: {
        signUp: (store,{userId,pw})=>{
            return instance.post('/users',
                {
                    userId: userId,
                    userPw: pw
                })
                .then(res =>{
                    console.log(res.data)
                    //store.commit('ADD_USER',)
                })
                .catch(()=>{

                });
        },
        logIn: (store,{userId,pw})=>{
            return instance.post('/users/login',
                {
                    userId: userId,
                    userPw: pw
                })
                .then(res =>{
                    console.log(res.data)
                    store.commit('addUser',{user:res.data})
                    sessionStorage.setItem("userId",res.data.userId)                    
                    router.push('/main')
                })
                .catch(()=>{

                });
        },
        goCreateQuiz: (store,{userId,userNo})=>{
            store.commit('updateUser',{userId,userNo})
            router.push('/createquiz')
        },
    },
})

export default store;