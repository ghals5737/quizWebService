<template>
    <div class="flex h-screen">
    <div class="flex-1 flex flex-col overflow-hidden">
        <header class="flex justify-between items-center bg-blue-300 p-4">
            <nav-bar></nav-bar>        
        </header>
        <div class="flex h-full" id="bg">
        <nav class="flex w-72 h-full">
            <div class="w-full flex mx-auto px-6 py-8">
            <div class="w-full h-full flex items-center justify-center text-gray-900 text-xl"></div>
            </div>
        </nav>
        <main class="flex flex-col w-full overflow-x-hidden overflow-y-auto mb-14">
            <div class="flex w-full mx-auto px-6 py-8">
            <div class="flex flex-col w-full h-full text-gray-900 text-xl">
                <div id="ranktitle">
                    <p>랭킹</p>
                    <div id="rankwrap">
                        <div id="answerankcontent">
                            <div id="answerankcontentwrap">
                                <ul>
                                    <li id="" v-for="(item,index) in userList" :key="index">
                                        <div class="flex justify-center items-center m-1 font-medium py-1 px-4 bg-white rounded-full text-black bg-purple-100 border border-purple-300 ">
                                                <div class="px-4" id="userChip">
                                                    <div 
                                                    class="flex relative w-9 h-9 bg-orange-500 justify-center items-center m-1 mr-2 ml-0 my-0 rounded-full">
                                                    <img v-if="index<3" class="rounded-full mr-2" alt="A" :src="medalUrl[index]">
                                                    <p class="text-3xl" v-if="index>=3">{{index+1}}</p>
                                                    </div>
                                                </div>
                                                <div class="pr-4" id="userChip">
                                                    <div 
                                                    class="flex relative w-9 h-9 bg-orange-500 justify-center items-center m-1 mr-2 ml-0 my-0 text-xs rounded-full">
                                                    <img class="rounded-full mr-2" alt="A" :src="imgUrl[index%5]">
                                                    </div>
                                                </div>
                                                {{item.userId}}
                                                <p class="ml-auto">{{item.total}}</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="mt-4">
                                <p>
                                    당신은
                                    <span id="myRank">{{myRank}}위</span>
                                    입니다.
                                </p>
                                [ {{rightScore}} ]
                            </div>
                            <div id="myRankInfo" class="text-4xl">
                                <ul id="myRankUl" class="inline-block">
                                    <li id="myRankTotal" class="text-left float-left">
                                        총 참가자: 
                                        <span>{{userList.length}}명</span>
                                    </li>
                                    <li id="myRankDetail" class="float-right">
                                        {{answerList.length}}문제 중, 
                                        <div>
                                            <span id="right">{{rightCnt}}문제</span>
                                            정답
                                            <span>/</span>
                                            <span id="wrong">{{answerList.length-rightCnt}}문제</span>
                                            오답
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </main>
        <nav class="flex w-72 h-full">
            <div class="w-full flex mx-auto px-6 py-8">
            <div class="w-full h-full flex items-center justify-center text-gray-900 text-xl "></div>
            </div>
        </nav>
        </div>
    </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import NavBar from '../components/NavBar.vue'
import confetti from "canvas-confetti";
//import VueConfetti from 'vue-confetti'
//import router from '../router/index'

export default {
    name: 'Result',
    components: { 
        NavBar,     
    },
    computed: {
        ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ","ANSWERLIST","USERLIST"]),
    },
    data(){      
        return { 
            roomNo:0,
            length:0,
            totalScore:0,
            rightScore:0,
            rightCnt:0,
            myRank:0,
            answerList:[],  
            userList:[],
            userId:'',
            userNo:'',  
            imgUrl:[
                require("../assets/bf_change_apple_s.png"),
                require("../assets/bf_change_bana_s.png"),
                require("../assets/bf_change_berry_s.png"),
                require("../assets/bf_change_choco_s.png"),
                require("../assets/bf_change_kiwi_s.png"),
            ],
            medalUrl:[
                require("../assets/medal_1.png"),
                require("../assets/medal_2.png"),
                require("../assets/medal_3.png"),
            ],   
        }
    },
    methods:{
		connect() {
			const serverURL = "https://project-intern08.wjthinkbig.com/socket"
			let socket = new SockJS(serverURL,{});
			console.log(sessionStorage.getItem("accessToken"))
			this.stompClient = Stomp.over(socket);
			console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
			this.stompClient.connect(
				{},
				frame => {
					// 소켓 연결 성공
					this.connected = true;
					console.log('소켓 연결 성공', frame)
					// 서버의 메시지 전송 endpoint를 구독합니다.               
					this.stompClient.subscribe("/sub/answer/"+this.$route.query.roomNo, res => {
                        console.log("enter",typeof res.body)
						let result=JSON.parse(res.body)
                        this.userList=result
                        this.length=this.userList.length>5?5:this.userList.length
                        this.myRank=this.userList.findIndex((el)=>el.userNo==this.userNo)+1
                    });   
					this.isConnected=true
				},
				error => {
					// 소켓 연결 실패
					console.log('소켓 연결 실패', error)
					this.isConnected = false
					}
				);        
        },
        searchAnswer(){
            this.$store.dispatch("searchAnswerByRoomNo",{
                roomNo:this.$route.query.roomNo,
                userNo:sessionStorage.getItem('userNo')
            }).then(()=>{  
                this.answerList=this.ANSWERLIST
                this.answerList.forEach(el=>{
                    this.totalScore+=el.score
                    if(el.isAnswer){
                        this.rightScore+=el.score                        
                    }
                })              
            })            
        },
        celebrate() {
            let count = 200;
            let defaults = {
                origin: {
                x: Math.random(),
                // since they fall down, start a bit higher than random
                y: Math.max(0, Math.random() - 0.2)
                }
            };

            function fire(particleRatio, opts) {
                confetti(
                Object.assign({}, defaults, opts, {
                    particleCount: Math.floor(count * particleRatio)
                })
                );
            }

            fire(0.25, {
                spread: 26,
                startVelocity: 55
            });
            fire(0.2, {
                spread: 60
            });
            fire(0.35, {
                spread: 100,
                decay: 0.91,
                scalar: 0.8
            });
            fire(0.1, {
                spread: 120,
                startVelocity: 25,
                decay: 0.92,
                scalar: 1.2
            });
            fire(0.1, {
                spread: 120,
                startVelocity: 45
            });
        }
    },
    created(){
        this.connect()
        this.userId=sessionStorage.getItem("userId")        
		this.userNo=sessionStorage.getItem("userNo")  
        this.$store.dispatch("searchAnswerByRoomNo",{
            roomNo:this.$route.query.roomNo,
            userNo:sessionStorage.getItem('userNo')
        }).then(()=>{  
            this.answerList=this.ANSWERLIST
            this.answerList.forEach(el=>{
                this.totalScore+=el.score
                if(el.isAnswer){
                    this.rightScore+=el.score
                    this.rightCnt++
                }
            })              
        })
        this.$store.dispatch("getRank",{
            roomNo:this.$route.query.roomNo,
        }).then(()=>{
            this.userList=this.USERLIST
            this.length=this.userList.length>5?5:this.userList.length
            this.myRank=this.userList.findIndex((el)=>el.userNo==this.userNo)+1
        })
        this.celebrate();
        window.setInterval(this.celebrate, 3000);        
    },
    watch:{
        
    }
}
</script>

<style>
#bg{
    background: url("https://www.quizn.show/webdata/images/show_rank_bg.png") no-repeat center center;
}
#ranktitle{
    margin-top: 10px;
    position: relative;
    padding-bottom: 30px;
    font-size: 50px;
    line-height: 1.2;
    text-align: center;
    padding-bottom: 0 !important;
}
#rankwrap{
    display: block;
    min-height: 60vh;
    margin: 0 auto;
    min-height: 550px;
    width: 80%;
    height: 100%;
}
#answerankcontent{
    width: 100%;
    margin: 0 auto;
    height: auto;
    margin-bottom: 35px;
    transition: all .3s ease;
}
#answerankcontentwrap{
    width: 70%;
    height: 100%;
    margin: 0 auto;
}
#myRank{
    color: #ff5f4a;
    box-shadow: inset 0 -2.5px 0 white, inset 0 -4.5px 0 #ff5f4a;
    font-family: 'HGGGothicssi 80g';
}
#myRankInfo{
    background: #eee;
    width: 85%;
    margin: 0 auto;
    max-width: 1260px;
    min-width: 1140px;
    top: 16px;
    position: relative;
}
#myRankUl{
    display: block;
    padding: 3% 6% 6% 6%;
    position: relative;
}
#myRankTotal{
    background: url("https://www.quizn.show/webdata/images/total_p.png") no-repeat left top 1px;
    padding-left: 3rem;
    background-position-x: 0px;
}
#myRankDetail{
    background: url("https://www.quizn.show/webdata/images/q_img.png") no-repeat left top 3px;
    padding-left: 3.2rem;
    background-position-x: 0px;
    float: right;
    text-align: left;
    display: inline-flex;
}
#right{
    padding-left: 10px;
    color: #4388ee;
}
#wrong{
    color: #ff5f4a;
    padding-left:10px;
}
</style>