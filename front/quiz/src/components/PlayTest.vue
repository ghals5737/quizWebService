<template>
    <div class="flex h-screen bg">
        <div class="flex-1 flex flex-col overflow-hidden">
            <header class="flex justify-between items-center bg-blue-300 p-4">
                <nav-bar></nav-bar>
            </header>
            <div class="flex h-full ">
                <nav class="flex w-72 h-full ">
                <div class="w-full flex mx-auto px-6 py-8">
                    <div class="w-full h-full text-gray-900 text-xl">
                        <p class="mt-2 mb-4 text-center font-extrabold text-xl">유저 순위</p>
                        <ul>
                            <li id="userli" v-for="(item,index) in userList" :key="index">
                                <div class="flex justify-center items-center m-1 font-medium py-1 px-4 bg-white rounded-full text-black bg-purple-100 border border-purple-300 ">
                                        <div class="" id="userChip">
                                            <div 
                                            class="flex relative w-9 h-9 bg-orange-500 justify-center items-center m-1 mr-2 ml-0 my-0 rounded-full">
                                            <img v-if="index<3" class="rounded-full mr-2" alt="A" :src="medalUrl[index]">
                                            <p v-if="index>=3">{{index+1}}</p>
                                            </div>
                                        </div>
                                        <div class="" id="userChip">
                                            <div 
                                            class="flex relative w-9 h-9 bg-orange-500 justify-center items-center m-1 mr-2 ml-0 my-0 text-xs rounded-full">
                                            <img class="rounded-full mr-2" alt="A" :src="imgUrl[index%5]">
                                            </div>
                                        </div>
                                        {{item.userId}}
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                </nav>
                <main class="flex flex-col w-full  overflow-x-hidden overflow-y-auto mb-14">
                <div class="flex w-full mx-auto px-6 py-8">
                    <div class="flex flex-col w-full h-full text-xl">
                        <div class="w-full relative" id="quizheader">
                            <p id="roomName">room1</p>
                            <div id="progressBar">
                                <div id="qNumber">
                                    {{currentIndex+1}}/{{problemList.length}}
                                </div>
                                <div id="qTime">
                                    {{ 20-Math.ceil(prog/5.5) }}
                                </div>
                                <div class="" id="progress">
                                    <div class="mt-2 bg-gray-400 rounded-full">
                                        <div class="mt-2 bg-red-500 py-2 rounded-full" :style="{ width: prog+'%' }"> </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mt-4" id="quizbody">
                            <div id="quizPreview">
                                <carousel 
                                    :per-page="1"
                                    :mouse-drag="false"
                                    :paginationEnabled="false"
                                    ref="carousel"
                                >
                                    <slide
                                        v-for="(problem, index) in problemList"
                                        :key="index"
                                    >
                                        <div id="qcontent">
                                            <ul class="w-full h-full relative inline-block">
                                                <li id="qdes">
                                                    <p id="qcontentdes">{{problem.title}}</p>                                                                                    
                                                </li>
                                                <li id="qimage">
                                                    <div id="qimgForm">
                                                        <div id="qimg"></div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="my-5" id="acontent">
                                            <ul v-if="problem.quizType==0" id="acontentUl">
                                                <li 
                                                    v-for="(item,n) in problem.exampleList"
                                                    :key="n"
                                                    id="acontentli"
                                                    :style="{background:color[n]}" 
                                                    @click="goNextPrb(n)"
                                                >
                                                    <span id="aNumber">{{n+1}}</span>
                                                    <div id="aText"></div>
                                                    <p id="aDes">{{item.des}}</p>
                                                </li>
                                            </ul>
                                            <ul v-if="problem.quizType==1" id="acontentUlOX">
                                                <li id="liO" @click="goNextPrb(0)">
                                                    <p id="imgO"></p>
                                                </li>
                                                <li id="liX" @click="goNextPrb(1)">
                                                    <p id="imgX"></p>
                                                </li>
                                            </ul>
                                        </div> 
                                    </slide>
                                </carousel>  
                            </div>
                        </div>
                    </div>
                </div>
                </main>
                <nav class="flex w-60 h-full ">
                <div class="w-full flex mx-auto px-6 py-8">
                    <div class="w-full h-full flex items-center justify-center text-xl"></div>
                </div>
                </nav>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
//import SideBar from '../components/SideBar.vue'
import NavBar from '../components/NavBar.vue'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import router from '../router/index'
import { Carousel, Slide } from 'vue-carousel';
//import Gravatar from 'vue-gravatar'

export default {
    name: 'TestPlayQuiz',
    components: {          
        NavBar,
        Carousel,
        Slide,
        //Gravatar,
    },
    computed: {
        ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ"]),
        columns() {
            if (this.$vuetify.breakpoint.xl) {
                return 1;
            }

            if (this.$vuetify.breakpoint.lg) {
                return 1;
            }

            if (this.$vuetify.breakpoint.md) {
                return 1;
            }

            return 1;
        },  
    },
    data(){
        return{
            msg:'',
			room:'',
			userId:'',
            userNo:'',
            length:0,
            isConnected:false,
            color:['#02abb0','#318cff','#f8ac59','#ed5565'],
			userList:[],
            angle: '50',
            color1: 'red',
            color2: 'blue',
            model: null,
            flag:false,
            exampleIndex:-1,
            aa:1,
            value: 0,
            max: 100,
            prog: 0,
            interval:'',
            currentIndex:0,
            problemList:[],
            userAnswerList:[],
            quizList:[],  
            answerList:[],                 
            answer:{
                userAnswerList: [],
                userNo: sessionStorage.getItem('userNo'),
                userId: sessionStorage.getItem('userId'),
                roomNo: this.$route.query.roomNo
            },
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
        sendAnswer() {
			if (this.stompClient && this.stompClient.connected) {
				// let msg = { 
				// 	'answer':this.answer,
				// }              
				this.stompClient.send("/pub/answer", JSON.stringify(this.answer),{} )     
			}else{
				alert('Send Fail')
				console.log("Send Fail")
			}
		},
		connect() {
			const serverURL = "http://localhost:8700/socket"
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
                        console.log("answer",res.body)
						let result=JSON.parse(res.body)
                        this.userList=result
                        this.length=this.userList.length>5?5:this.userList.length
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
        goNextPrb(n){            
            window.clearInterval(this.interval)
            
            this.sleep(700).then(() => {
                    this.prog=-3
                    this.userAnswerList[this.currentIndex].submitAnswer.push(n)  
                    this.answer.userAnswerList=[]
                    this.answer.userAnswerList.push(this.userAnswerList[this.currentIndex])
                    this.sendAnswer()
                    this.$refs.carousel.goToPage(this.$refs.carousel.getNextPage());                
                    this.currentIndex++
                    this.interval=window.setInterval(function(){
                        this.prog += 0.1            
                    }.bind(this), 20)
            });  
        },     
        submitAnswer(){
            // this.answer.userAnswerList=this.userAnswerList
            // this.$store.dispatch("submitAnswer",{                
            //     answer:this.answer,
            //     roomNo:this.$route.query.roomNo
            // })
        },
        randomValue() {
            this.value = Math.random() * this.max
        },
        show(){
            alert("??")
        },
        test(){
            this.currentIndex++
        },     
        sleep(ms) {
            return new Promise((r) => setTimeout(r, ms));
        },
        expendEvent(value){            
            if(!this.flag){                
                this.flag=!this.flag
            }else if(this.exampleIndex===value){
                this.flag=!this.flag
            }            
            this.exampleIndex=value
        },  
        getRoomByRoomNo(){
			this.$store.dispatch("searchRoomByRoomNo",{
					roomNo:this.$route.query.roomNo
				}).then(()=>{
					this.room=this.ROOM
				})
		},  
        handleSlideClick(){
            console.log("carousel",this.$refs)
        }    
    },
    created(){
        this.getRoomByRoomNo()
		this.connect()
        this.userId=sessionStorage.getItem("userId")        
		this.userNo=sessionStorage.getItem("userNo")  
        this.$store.dispatch("searchQuizByRoomNo",{
            roomNo:this.$route.query.roomNo
        }).then(()=>{  
            this.quizList=this.QUIZLIST
            this.quizList.forEach(el => {
                el.problemList.forEach(el1=>{
                    this.problemList.push(el1)
                    this.userAnswerList.push({
                        answer: el1.answer,
                        prbNo: el1.prbNo,
                        score: el1.score,
                        submitAnswer: []
                    })
                })                
            });            
        })
    },
    mounted () {
        // this.interval = setInterval(() => {
        // if (this.value === 100) {
        //     return (this.value = 0)
        // }
        // this.value += 10
        // }, 1000)

        this.interval=window.setInterval(function(){
            this.prog += 0.1            
        }.bind(this), 20)
    },
    watch:{
        prog(){
            if(this.prog>=100){   
                window.clearInterval(this.interval)
                this.sleep(700).then(() => {                    
                    this.prog=-3       
                    this.userAnswerList[this.currentIndex].submitAnswer.push(-1)           
                    this.currentIndex++  
                    this.$refs.carousel.goToPage(this.$refs.carousel.getNextPage());
                    this.interval=window.setInterval(function(){
                        this.prog += 0.1            
                    }.bind(this), 20)
                });                
            }
        },
        currentIndex(){
            if(this.currentIndex>=this.problemList.length){               
                router.push({name: 'Result', query: {roomNo: this.$route.query.roomNo}})
            }
        },
    }
}
</script>

<style>
#quizheader{
    top: 10px;
    font-weight: bold;
    margin: 0 auto;
    position: relative;
    transition: all .3s ease;
    padding: 0 10px;
    min-width: 980px;
}
#quizbody{
    width: 100%;
    position: relative;
    min-width: 1000px;
}
#roomName{
    font-size: 33px;
    padding-bottom: 30px;
    position: relative;
    text-align: center;
}
#progressBar{
    position: relative;
    margin: 0 auto;
    width: 100%;
}
#qNumber{
    float: left;    
    color: #a9a9a9;
    font-size: 26px;
    padding-right: 1%;
    padding-right: 1%;
    width: 4.5%;
    text-align: right;
    position: relative;
}
#progress{
    width: 87%;
    position: relative;
    margin: 0 auto;    
    padding-top: 1px;
    text-align: center;
}
#qTime{
    float: right;    
    color: #a9a9a9;
    font-size: 26px;
    padding-right: 1%;
    padding-right: 1%;
    width: 4.5%;
    text-align: right;
    position: relative;
}
#quizPreview{
    width:89%;
    /* height: 100%; */
    margin: 0 auto;
}
#qcontent{
    margin-top: 15px;
    position: relative;
    display: inline-block;
    width: 100%;
}
#acontent{
    margin-top: 20px;
    position: relative;
    display: inline-block;
    width: 100%;
}
#qcontentdes{
    width: 100%;
    line-height: 1.3;
    height: auto;
    font-size: 32px;
    font-family: cwtexhei,'HGGGothicssi 80g',Sans-serif;
    font-weight: 600;
    overflow: auto;
    height: 100%;
}
#qdes{
    width: 53%;
    height: 230px;
    float: left;
}
#qimage{
    width: 40%;
    float: right;
}
#qimgForm{
    position: relative;
    width: 100%;
    height: 0;
    overflow: hidden;
    padding-bottom: 54.3%;
}
#qimg{
    background: url("../assets/picture.png") no-repeat center center;
    background-size: contain;
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
}
#acontentUl{
    position: relative;
    width: 100%;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    display: inline-flex !important;
}
#acontentli{
    width: 24%;
    /* height: 23vh; */
    border-radius: 15px;
    float: left;
    margin-right: 1.3%;
    position: relative;
    height: 0;
    overflow: hidden;
    padding-bottom: 17.5%;
}
#aNumber{
    font-size: 2vw;
    font-weight: bold;
    color: #fff;
    float: right;
    margin-top: 15px;
    right: 15px;
    position: relative;
    text-shadow: 3px 3px 0px #00000040;
    z-index: 10;
    /* width: 25px; */
    height: 35px;
    display: inline-block;
    -webkit-text-stroke-width: 1px;
    -webkit-text-stroke-color: #0000005c;
}
#aText{
    position: relative;
    font-size: 28px;
    color: #fff;
    border-radius: 15px;
    text-align: center;
    word-break: normal;
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 0 auto;
    align-items: center;
    /* border: 1px #e5e5e5 solid; */
    line-height: 1.25;
    /* min-height: 23vh; */
    position: absolute;
    opacity: 0.7;
    width: 100%;
    height: 100%;
}
#aDes{
    font-size: 1.9vw;
    color: #fff;
    text-align: center;
    line-height: 1.25;
    width: 96%;
    height: 100%;
    word-break: normal;
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 0 auto;
    align-items: center;
    word-break: break-all;
    position: absolute;
    /* border-radius: 15px; */
    font-family: cwtexhei,'HGGGothicssi 80g',Sans-serif;
    font-weight: 600;
    padding-left: 2%;
}
#acontentUlOX{
    flex-direction: row;
    justify-content: center;
    align-items: center;
    display: inline-flex !important;
    width: 100%;
}
#liO{
    width: 24%;
    /* height: 23vh; */
    border-radius: 15px;
    float: left;
    margin-right: 1.3%;
    position: relative;
    height: 0;
    overflow: hidden;
    padding-bottom: 17.5%;
    border: 1px #4388ee solid;
}
#liX{
    width: 24%;
    /* height: 23vh; */
    border-radius: 15px;
    float: left;
    margin-right: 1.3%;
    position: relative;
    height: 0;
    overflow: hidden;
    padding-bottom: 17.5%;
    border: 1px #ff5f4a  solid;
}
#imgO{
    width: 94%;
    height: 100%;
    margin: 0 auto;
    position: absolute;
    background: url("https://www.quizn.show/webdata/images/o_answer​.png") no-repeat center center;
    background-size: 60%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 0 auto;
    align-items: center;
    left: 3%;
}
#imgX{
    width: 94%;
    height: 100%;
    margin: 0 auto;
    position: absolute;
    background: url("https://www.quizn.show/webdata/images/x_answer​.png") no-repeat center center;
    background-size: 60%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 0 auto;
    align-items: center;
    left: 3%;
}
</style>