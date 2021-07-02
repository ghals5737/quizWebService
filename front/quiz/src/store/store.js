import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router/index'
import { instance, instanceWithAuth } from '../common/createInstance';
Vue.use(Vuex);

// let https = require('https')
// const instance = axios.create({
//   baseURL: 'http://localhost:8700/api',
//   timeout: 120000,
//   httpsAgent: new https.Agent({
//     rejectUnauthorized: false
//   })
// });


const store = new Vuex.Store({
    state: {
        userInfo: null,
        quizList: null,
        total:null,
        quiz:null,
        token:null,
    },
    getters: {
        USER: state => state.userInfo,        
        QUIZLIST: state => state.quizList,
        TOTAL: state => state.total,
        QUIZ:state => state.quiz,
    },
    mutations: {
        addUser: (state,{user})=>{
            state.userInfo=user
        },
        updateUser: (state,{userId,userNo})=>{
            state.userInfo={userId,userNo}
        },
        addQuizList: (state,{quizList})=>{
            state.quizList=quizList
        },
        addTotal: (state,{total})=>{
            state.total=total
        },
        addQuiz: (state,{quiz})=>{
            state.quiz=quiz            
        },
        addToken: (state,{token})=>{
            state.token=token
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
            return instance.post('/users/authenticate',
                {
                    userId: userId,
                    userPw: pw
                })
                .then(res =>{                   
                    store.commit('addUser',{user:res.data})                    
                    store.commit('addToken',{token:res.data.token})
                    sessionStorage.setItem("userId",res.data.userId)                    
                    sessionStorage.setItem("userNo",res.data.userNo)
                    sessionStorage.setItem("accessToken",res.data.token)    
                    router.push('/main')
                })
                .catch(()=>{

                });
        },
        goCreateQuiz: (store,{userId,userNo})=>{
            store.commit('updateUser',{userId,userNo})
            router.push('/createquiz')
        },
        goSearchQuiz: (store,{userId,userNo})=>{
            store.commit('updateUser',{userId,userNo})
            router.push('/searchquiz')
        },
        makeQuiz: async(store,{quiz})=>{            
            try{
                console.log(quiz)
                return await instanceWithAuth.post('/quiz',quiz)
                .then(res =>{
                    console.log(res.data)
                    alert("성공!!")                                               
                }) 
            }catch(err){
                console.log(err)
            }                       
        },
        searchQuiz: (store,{page,size})=>{
            return instanceWithAuth.get(`quiz/allquiz?page=${page}&size=${size}`)
            .then(res =>{
                //console.log(res.data)
                store.commit('addQuizList',{quizList:res.data})                                                           
            })
            .catch(()=>{
                alert("실패")
            });
        },
        initQuiz: (store)=>{
            return instanceWithAuth.get(`quiz/total`)
            .then(res=>{
                store.commit('addTotal',{total:res.data})
            }).catch(()=>{
                alert("실패")
            });
        },
        searchQuizByquizNo:(store,{quizNo})=>{
            return instanceWithAuth.get(`quiz?quizNo=${quizNo}`)
            .then(res =>{
                //console.log(res.data)
                store.commit('addQuiz',{quiz:res.data})                
            })
            .catch(()=>{
                alert("실패")
            });
        },
    },
})

export default store;