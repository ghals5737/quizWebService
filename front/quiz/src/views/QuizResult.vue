<template>
    <v-app>        
        <v-container class="grey lighten-5">
            <v-row no-gutters cols="12">           
                <!-- <v-card class="pa-4 mb-2 rounded-pill" outlined tile>
                    <v-row>
                        <v-col sm="1">1등</v-col>                    
                        <v-col sm="10">user1</v-col>
                        <v-col sm="1">100</v-col>
                    </v-row>
                </v-card>-->
                <div class="container">
                <header>
                <h1>퀴즈 결과 Top 5</h1>
                </header>
                <div class="wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>순위</th>
                                <th>유저</th>
                                <th>점수</th>
                                <th>맞은개수</th>
                            </tr>
                        </thead>
                        <tbody 
                            v-for="i in length"
                            :key="i"
                        >
                            <tr>
                                <td class="rank">{{i}}</td>
                                <td class="team">{{userList[i-1].userId}}</td>
                                <td class="points">{{userList[i-1].total}}</td>
                                <td class="up-down">{{userList[i-1].rightCnt}}/{{answerList.length}}</td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
            </div>
            </v-row>            
        </v-container>
        <v-container class="text-center">
            <v-row id="content2">
                <v-col>
                    당신은 {{myRank}}위 입니다.
                </v-col>
            </v-row>            
            <v-row id="content3">
                <v-col>
                    [ {{rightScore}} ]
                </v-col>
            </v-row>
        </v-container>        
        <v-container>
            <v-row>
                <v-col md="3"></v-col>
                <v-col md="6">
                    <v-card
                        outlined 
                        color="#f5f5f5"
                        class="text-center"
                        id="resultcard"                                 
                    >
                    <v-row id="content1">
                        <v-col>총참가자: {{userList.length}}명</v-col>
                    </v-row>
                    <v-row>
                        <v-col>{{answerList.length}}문제 중</v-col>
                    </v-row>
                    <v-row>
                        <v-col><span id="span1">{{rightCnt}}문제</span> 정답, <span id="span2">{{answerList.length-rightCnt}}문제</span> 오답</v-col>
                    </v-row>                          
                    </v-card>
                </v-col>
            </v-row>            
        </v-container>        
    </v-app>    
</template>

<script>
import { mapGetters } from 'vuex'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
//import router from '../router/index'

export default {
    name: 'QuizResult',
    components: {      
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
    },
    watch:{
        
    }
}
</script>

<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	color: #989c9b;
}
#resultcard{
   /* font-family: 'Lato', Arial, sans-serif;	 */
    font-weight: bold;
    font-size: 20px;
}
#content1{
    font-size: 30px;
}
#content2{
    /* font-family: 'Lato', Arial, sans-serif;	     */
    font-size: 40px;
}
#content3{
    /* font-family: 'Lato', Arial, sans-serif;	 */    
    font-size: 30px;
}
#span1{
    color: #30a7fc;
    
}
#span2{
    color: #ff3633;
    
}
.container > header {
	margin: 0 auto;
	padding: 1em;
	text-align: center;
}

.container > header h1 {
  font-weight: 600;
	font-size: 3em;
	margin: 0;
}

.wrapper {
	line-height: 1.5em;
	margin: 0 auto;
	padding: 2em 0 3em;
	width: 90%;
	max-width: 2000px;
	overflow: hidden;
}

table {
    border-collapse: collapse;
    width: 100%;
    background: #fff;
}

th {
    background-color: #326295;
    font-weight: bold;
    color: #fff;
    white-space: nowrap;
}

td, th {
    padding: 1em 1.5em;
    text-align: left;
}

tbody th {
	background-color: #2ea879;
}
tbody tr:nth-child(2n-1) {
    background-color: #f5f5f5;
    transition: all .125s ease-in-out;
}
tbody tr:hover {
    background-color: rgba(50,98,149,.3);
}

td.rank {
	text-transform: capitalize;
}

</style>