<template>
    <div class="flex h-screen bg">
        <div class="flex-1 flex flex-col overflow-hidden">
            <header class="flex justify-between items-center bg-blue-300 p-4">
                <nav-bar></nav-bar>
            </header>
            <div class="flex h-full ">
                <nav class="flex w-72 h-full ">
                <div class="w-full flex mx-auto px-6 py-8">
                    <div class="w-full h-full flex items-center justify-center text-gray-900 text-xl"></div>
                </div>
                </nav>
                <main class="flex flex-col w-full  overflow-x-hidden overflow-y-auto mb-14">
                <div class="flex w-full mx-auto px-6 py-8">
                    <div class="flex flex-col w-full h-full text-xl">
                        <div class="w-full relative" id="quizheader">
                            <p id="roomName">room1</p>
                            <div id="progressBar">
                                <div id="qNumber">
                                    1/2
                                </div>
                                <div id="qTime">
                                    30
                                </div>
                                <div class="" id="progress">
                                    <div class="mt-2 bg-gray-600 rounded-full">
                                        <div class="w-6/12 mt-2 bg-purple-900 py-2 rounded-full"> </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mt-4" id="quizbody">
                            <div id="quizPreview">
                                <div id="qcontent">
                                    <ul class="w-full h-full relative inline-block">
                                        <li id="qdes">
                                            <p id="qcontentdes">aaaaa</p>                                                                                    
                                        </li>
                                        <li id="qimage">
                                            <div id="qimgForm">
                                                <div id="qimg"></div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div id="acontent">
                                    <ul id="acontentUl">

                                    </ul>
                                </div>
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
//import Gravatar from 'vue-gravatar'

export default {
    name: 'TestPlayQuiz',
    components: {          
        NavBar,
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
            colors:[
                "light-blue darken-3",
                "blue lighten-3",
                "red accent-2",
                "orange lighten-2"
            ],
            slider: [
                "red", 
                "green", 
                "orange", 
                "blue", 
                "pink", 
                "purple", 
                "indigo", 
                "cyan", 
                "deep-purple", 
                "light-green", 
                "deep-orange", 
                "blue-grey"
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
                    // this.$store.dispatch("submitAnswer",{                
                    //     answer:this.answer,
                    //     roomNo:this.$route.query.roomNo
                    // })          
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
        // this.interval=window.setInterval(function(){
        //     this.prog += 0.1            
        // }.bind(this), 20)
    },
    watch:{
        prog(){
            if(this.prog>=100){   
                window.clearInterval(this.interval)
                this.sleep(700).then(() => {                    
                    this.prog=-3       
                    this.userAnswerList[this.currentIndex].submitAnswer.push(-1)           
                    this.currentIndex++    
                    this.interval=window.setInterval(function(){
                        this.prog += 0.1            
                    }.bind(this), 20)
                });                
            }
        },
        currentIndex(){
            if(this.currentIndex>=this.problemList.length){               
                router.push({name: 'QuizResult', query: {roomNo: this.$route.query.roomNo}})
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
    
}
</style>