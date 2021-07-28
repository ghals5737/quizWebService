import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router/index'
import { instance, instanceWithAuth } from '../common/createInstance';
import swal from 'sweetalert';
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
        room:null,
        roomList:null,
        answerList:null,
        encycList:null, 
        encycDetail:null,    
        photoList:null,   
        userList:null,
        userNameList:null,
    },
    getters: {
        USER: state => state.userInfo,        
        QUIZLIST: state => state.quizList,
        TOTAL: state => state.total,
        QUIZ: state => state.quiz,
        ROOM: state => state.room,
        ROOMLIST: state => state.roomList, 
        ANSWERLIST: state => state.answerList,   
        ENCYCLIST: state=> state.encycList,
        ENCYCDETAIL:state=>state.encycDetail,
        PHOTOLIST:state=>state.photoList,
        USERLIST:state=>state.userList,
        USERNAMELIST:state=>state.userNameList,
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
        addRoom: (state,{room})=>{
            state.room=room
        },
        addRoomList: (state,{roomList})=>{
            state.roomList=roomList
        },   
        addAnswerList: (state,{answerList})=>{
            state.answerList=answerList
        },
        addEncycList: (state,{encycList})=>{
            state.encycList=encycList
        },
        addEncycDetail: (state,{encycDetail})=>{
            state.encycDetail=encycDetail
        },
        addPhotoList: (state,{photoList})=>{
            state.photoList=photoList
        },
        addUserList:(state,{userList})=>{
            state.userList=userList
        },
        addUserNameList:(state,{userNameList})=>{
            state.userNameList=userNameList
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
                    if(res.data===""){
                        swal('Warning', '잘못된 유저 정보 입니다.', 'warning')
                    }else{
                        store.commit('addUser',{user:res.data})                    
                        store.commit('addToken',{token:res.data.token})
                        sessionStorage.setItem("userId",res.data.userId)                    
                        sessionStorage.setItem("userNo",res.data.userNo)
                        sessionStorage.setItem("accessToken",res.data.token)    
                        router.push('/createquiz')
                    }        
                })
                .catch(()=>{

                });
        },
        logout:()=>{
            sessionStorage.clear()
            router.push('/')
        },
        goCreateQuiz: (store,{userId,userNo})=>{
            store.commit('updateUser',{userId,userNo})
            router.push('/createquiz')
        },
        goSearchQuiz: (store,{userId,userNo})=>{
            store.commit('updateUser',{userId,userNo})
            router.push('/searchquiz')
        },
        makeQuiz: (store,{quiz})=>{            
            try{
                console.log("quiz",quiz)
                return instanceWithAuth.post('/quiz',quiz)
                .then(res =>{
                    console.log(res.data)
                    swal('Success', '퀴즈가 등록되었습니다.', 'success')                                               
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
        goCreateRoom:()=>{
            router.push('/createroom')
        },
        goRoomDetail:(store,{room})=>{
            return instanceWithAuth.post("/room",room)
            .then(res =>{                
                store.commit('addRoom',{room:res.data})
                router.push({name: 'RoomDetailTest', query: {roomNo: res.data.roomNo}})
            })
            .catch(()=>{
                alert("실패")
            });           
        },
        searchRoom:(store,{page,size})=>{
            return instanceWithAuth.get(`room/allroom?page=${page}&size=${size}`)
            .then(res =>{
                console.log(res.data)
                store.commit('addRoomList',{roomList:res.data})                                                           
            })
            .catch(()=>{
                alert("실패")
            });
        },
        initRoom: (store)=>{
            return instanceWithAuth.get(`room/total`)
            .then(res=>{
                store.commit('addTotal',{total:res.data})
            }).catch(()=>{
                alert("실패")
            });
        },
        addRoomQuiz: (store,{quizNoList,roomNo})=>{
            return instanceWithAuth.put("/room/quizs",{
                quizNoList: quizNoList,
                roomNo: roomNo
            }).then(()=>{
                swal('Success', '방이 등록되었습니다.', 'success')
            });
        },
        goSearchRoom:()=>{
            router.push('/searchroom')
        },
        searchQuizByRoomNo: (store,{roomNo})=>{            
            return instanceWithAuth.get(`/quiz/room-quizs?roomNo=${roomNo}`)
            .then((res)=>{
                console.log(res.data)
                store.commit('addQuizList',{quizList:res.data})
            })
        },
        submitAnswerList: (store,{answer,roomNo})=>{
            return instanceWithAuth.post("/answer",answer)
            .then(()=>{
                router.push({name: 'QuizResult', query: {roomNo: roomNo}})
            })
        },        
        submitAnswer: (store,{answer})=>{
            return instanceWithAuth.post("/answer",answer)
            .then(()=>{
                //router.push({name: 'QuizResult', query: {roomNo: roomNo}})
            })
        },        
        searchAnswerByRoomNo: (store,{roomNo,userNo})=>{
            return instanceWithAuth.get(`/answer?roomNo=${roomNo}&userNo=${userNo}`)
            .then((res)=>{
                console.log(res.data)
                store.commit('addAnswerList',{answerList:res.data})
            })
        },
        getEncyc:(store,{searchWord})=>{
            return instanceWithAuth.get(`wjtb/search?keyWord=${searchWord}`)
            .then((res)=>{
                store.commit('addEncycList',{encycList:res.data})
            })
        },
        getEncycDetail:(store,{dictSeq})=>{            
            return instanceWithAuth.get(`wjtb/searchdetail?dictSeq=${dictSeq}`)
            .then((res)=>{
                store.commit("addEncycDetail",{encycDetail:res.data})
                return instanceWithAuth.get(`wjtb/searchphoto?keyWord=${res.data.headwd}`).then((res1)=>{
                    store.commit("addPhotoList",{photoList:res1.data})
                })
            })
        },
        searchRoomByRoomNo:(store,{roomNo})=>{
            return instanceWithAuth.get(`/room/search?roomNo=${roomNo}`)
            .then((res)=>{
                store.commit('addRoom',{room:res.data})
            })
        },
        getUserListByRoom:(store,{roomNo})=>{
            return instanceWithAuth.get(`/users/inside-room?roomNo=${roomNo}`)
            .then((res)=>{
                store.commit('addUserList',{userList:res.data})
            })
        },
        getRank:(store,{roomNo})=>{
            return instanceWithAuth.get(`/answer/rank?roomNo=${roomNo}`)
            .then((res)=>{
                console.log("rank",res.data)
                store.commit('addUserList',{userList:res.data})
            })
        },
        getUserNameByUserNo:(store,{userNoList})=>{
            return instanceWithAuth.post("/users/userNames",userNoList)
            .then((res)=>{
                store.commit('addUserNameList',{userNameList:res.data})
            })
        },
    },
})

export default store;