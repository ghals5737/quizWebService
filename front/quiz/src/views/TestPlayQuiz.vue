<template>
<div id="app">
    <v-app id="inspire">
        <nav-bar></nav-bar>      
        <v-container>
        <v-navigation-drawer
            :clipped="$vuetify.breakpoint.lgAndUp"
            app
        >   
            <v-row>
                <v-col class="text-center h2">
                    유저 rank
                </v-col>                
            </v-row>            
            <v-list>                
                <v-list-item-group color="primary">
                    <v-list-item
                        v-for="(item, i) in userList"
                        :key="i"                        
                    > 
                        <span class="h4 mr-5">{{i+1}}등</span>                        
                        <v-list-item-avatar>
                             <Gravatar class="" :email="item.userId" :size="200" />                                           
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title class="h4">&nbsp;{{item.userId}}</v-list-item-title>
                        </v-list-item-content>
                            
                        <!-- <v-avatar size="40" left>
                        </v-avatar> -->
                        <span class="h4" right>{{item.total}}점</span>
                       
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>
    </v-container>
         
        <v-main>
        <v-container class="pt-15" fluid><p class="text-center">퀴즈 제목</p></v-container>
        <v-container class="text-xs-center" fluid>            
                <v-row no-gutters cols="12">
                    <v-col sm="1" justify="center">
                        <strong><p class="text-center">{{currentIndex+1}}/{{problemList.length}}</p></strong>
                    </v-col>
                    <v-col sm="10">
                        <v-progress-linear
                        class="rounded-pill"
                        background-color="grey"
                        color="#f0cb35"
                        dark v-model="prog"
                        height="15"                         
                        md="5">
                        </v-progress-linear>
                    </v-col>
                    <v-col sm="1">
                        <strong><p class="text-center">{{20-Math.ceil(prog/5.5) }}</p></strong>
                    </v-col>
                </v-row>                                                
        </v-container>
        <v-container fluid>
            <v-carousel hide-delimiters :show-arrows="false" v-model="currentIndex"> 
                <template v-for="(problem, index) in problemList"> 
                    <v-carousel-item v-if="(index + 1) % columns === 1 || columns === 1" 
                                    :key="index"                                    
                    > 
                        <v-row class="flex-nowrap" style="height:100%"> 
                        <template v-for="(n,i) in columns"> 
                            <template v-if="(+index + i) < problemList.length"> 
                            <v-col :key="i">                                
                                <v-container fluid>
                                    <v-row justify="space-around">
                                        <v-col cols="5">   
                                            <p class="text-sm-left">{{problem.title}}</p>    
                                        </v-col>
                                        <v-col cols="5">
                                            <v-img
                                                :src="problem.imgUrl"
                                                height="150px"
                                                width="150px"
                                                aspect-ratio="1.7"
                                                contain
                                            >
                                            </v-img>                
                                        </v-col>
                                    </v-row>
                                </v-container>                                
                                <v-container fluid>                                    
                                    <v-row v-if="problem.quizType==0">               
                                        <v-col
                                            v-for="(item,n) in problem.exampleList"
                                            :key="n"
                                            cols="auto"                                            
                                            md="3"                    
                                        > 
                                            <v-btn
                                            :elevation="n - 1"
                                            height="110"
                                            width="100%"
                                            selected                                            
                                            :color="colors[n]"
                                            @click="goNextPrb(n)"                                                                                      
                                            >
                                                <v-container align-stretch>{{item.des}}</v-container>                   
                                                <p
                                                    id="number"
                                                    justify="center"
                                                    v-text="n+1"
                                                ></p>
                                            </v-btn>                                                                                         
                                        </v-col>
                                    </v-row>
                                    <v-row v-if="problem.quizType==1"> 
                                        <v-col>                                            
                                        </v-col>                                       
                                        <v-col>
                                            <v-card 
                                                height="125"
                                                width="70%"                                                    
                                                @click="goNextPrb(0)"
                                            >  
                                                <v-img 
                                                    max-height="120"
                                                    max-width="200"
                                                    src="@/assets/event_img_o.png"
                                                ></v-img>                                        
                                            </v-card>
                                        </v-col>
                                        <v-col>
                                            <v-card
                                                height="125"
                                                width="70%"                                                
                                                @click="goNextPrb(1)"
                                             >  
                                              <v-img 
                                                max-height="120"
                                                max-width="200"
                                                src="@/assets/event_img_x.png"
                                              >
                                              </v-img>
                                            </v-card>
                                        </v-col>
                                        <v-col>                                            
                                        </v-col> 
                                    </v-row>
                                    <v-row v-if="problem.quizType==2">
                                    </v-row>
                                </v-container>                                
                            </v-col>
                            </template> 
                        </template> 
                        </v-row> 
                    </v-carousel-item> 
                </template> 
            </v-carousel>  
        </v-container> 
        <v-container>            
        </v-container> 
            
        </v-main>
    </v-app>
</div>


</template>

<script>
import { mapGetters } from 'vuex'
//import SideBar from '../components/SideBar.vue'
import NavBar from '../components/NavBar.vue'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import router from '../router/index'
import Gravatar from 'vue-gravatar'

export default {
    name: 'TestPlayQuiz',
    components: {          
        NavBar,
        Gravatar,
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
#number{
    margin-bottom: 50px;
}
.v.application--wrap {   
    min-height: 1vh !important;
}
div.v-progress-linear__determinate{
    background: #f0cb35;
    background: -moz-linear-gradient(90deg,#f0cb35 0%, #c02425 100%);
    background: -webkit-linear-gradient(90deg,#f0cb35 0%, #c02425 100%);
    background: linear-gradient(90deg,#f0cb35 0%,  #c02425 100%);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#f0cb35",endColorstr="#c02425",GradientType=1);
}

</style>