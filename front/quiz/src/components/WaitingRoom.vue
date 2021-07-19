<template>
    <div class="flex h-screen bg">
        <div class="flex-1 flex flex-col overflow-hidden">
            <header class="flex justify-between items-center bg-blue-300 p-4">
                <nav-bar></nav-bar>
            </header>
            <div class="flex h-full bg-indigo-400">
                <nav class="flex w-72 h-full ">
                <div class="w-full flex mx-auto px-6 py-8">
                    <div class="w-full h-full flex items-center justify-center text-gray-900 text-xl"></div>
                </div>
                </nav>
                <main class="flex flex-col w-full  overflow-x-hidden overflow-y-auto mb-14">
                <div class="flex w-full mx-auto px-6 py-8">
                    <div class="flex flex-col w-full h-full text-xl">
                        <!-- <p class=" text-5xl inline-block text-yellow-300" id="quizTitle">aaaaaaaaaa</p> -->
                        <div class="border-yellow-300 border-2" id="titleBox">{{room.roomName}}</div>
                        <p class="mx-auto" id="userCnt">
                            <span class="ml-5">총 참여자수 {{userList.length}}/{{room.capacity}}</span>
                        </p>
                        <div id="userNameBox">
                            <div id="userName">
                                <ul class="text-center" id="userul">
                                    <li id="userli" v-for="(item,index) in userList" :key="index">
                                        <div class="flex justify-center items-center m-1 font-medium py-1 px-4 bg-white rounded-full text-black bg-purple-100 border border-purple-300 ">
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
                        <div class="mt-10 text-center">
                            <button 
                                id="startButton"
                                @click="quizStart"
                            >
                                시작
                            </button>                            
                        </div>
                    </div>
                </div>
                </main>
                <nav class="flex w-72 h-full ">
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
    name: 'WaitingRoom',
    components: {   
		NavBar:NavBar,
		// Gravatar,           
        // SideBar:SideBar,       
    },
    computed: {
        ...mapGetters(["USER","ROOM","USERLIST"]),
    },
    data(){
        return{
            msg:'',
			room:'',
			userId:'',
			userNo:'',
			isConnected:false,
			isStart:false,
			msgList:[],
			userList:[],
            imgUrl:[
                require("../assets/bf_change_apple_s.png"),
                require("../assets/bf_change_bana_s.png"),
                require("../assets/bf_change_berry_s.png"),
                require("../assets/bf_change_choco_s.png"),
                require("../assets/bf_change_kiwi_s.png"),
                require("../assets/long_sentence_select_icon.png"),
            ],
        }
    },
    methods:{
        sendEnter() {
			if (this.stompClient && this.stompClient.connected) {
				let msg = { 
					'userId':this.userId,
					'userNo':this.userNo,
					'roomNo':this.$route.query.roomNo,	
					'isOwner':this.userNo==this.room.owner_no			
				}              
				this.stompClient.send("/pub/enter", JSON.stringify(msg),{} )     
			}else{
				alert('Send Fail')
				console.log("Send Fail")
			}
		},
		sendPlay(){
			if (this.stompClient && this.stompClient.connected) {
				// let msg = { 
				// 	'roomNo':this.$route.query.roomNo	
				// }              
				this.stompClient.send("/pub/play", JSON.stringify(this.$route.query.roomNo),{} )     
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
					this.stompClient.subscribe("/sub/enter/"+this.$route.query.roomNo, res => {
						console.log("enter",typeof res.body)
						let result=JSON.parse(res.body)
						this.userList=[]
						result.forEach(el => {
							this.userList.push({
								userId:el.userId,
								userNo:el.userNo,
								roomNo:el.roomNo
							})
						});
					});   
					this.stompClient.subscribe("/sub/play/"+this.$route.query.roomNo, () => {
						if(!this.isStart){
							router.push({name: 'PlayTest', query: {roomNo: this.$route.query.roomNo}})		
							this.isStart=true
						}
						
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
		getRoomByRoomNo(){
			this.$store.dispatch("searchRoomByRoomNo",{
					roomNo:this.$route.query.roomNo
			}).then(()=>{
					this.room=this.ROOM
			})
		},
		start(callback){	
			this.connect()		
			callback()
		},
		quizStart(){
			this.sendPlay()
			//router.push({name: 'TestPlayQuiz', query: {roomNo: this.roomNo}})		
		},
		getRank(){
			alert("??")
			this.$store.dispatch("getRank",{
					roomNo:this.$route.query.roomNo
			}).then(()=>{
					//this.room=this.ROOM
			})
		},
    },
    created(){      
		this.getRoomByRoomNo()
		this.connect()
        this.userId=sessionStorage.getItem("userId")        
		this.userNo=sessionStorage.getItem("userNo")  
		// this.$store.dispatch("getUserListByRoom",{
		// 	roomNo:this.$route.query.roomNo
		// }).then(()=>{
		// 	this.USERLIST.forEach(el => {
		// 		this.userList.push({
		// 			userId:el.userId,
		// 			userNo:el.userNo,
		// 			roomNo:el.roomNo
		// 		})
		// 	});
		// })
		//router.push({name: 'PlayQuiz', query: {roomNo: value.roomNo}})          
    },
	watch:{
		isConnected(){
			if(this.isConnected){
				this.sendEnter()
			}
		}
	},
}
</script>

<style>
#titleBox{
    width: 68%;
    max-width: 720px;
    height: 80px;
    background-image: url("https://www.quizn.show/webdata/images/pin_box_bg.jpg");
    font-size: 46px;
    border-radius: 10px;
    color: #000;
    text-align: center;
    top: 20px;
    font-weight: bold;
    position: relative;
    margin: 0 auto;
    line-height: 1.7;
    transition: all .3s ease;
}
#userCnt{
    background: url("https://www.quizn.show/webdata/images/all-user-icon.png") no-repeat left top 2px;
    font-size: 28px;
    margin-top: 60px;
    color: #fff;
    display: inline-block;
    padding-left: 38px;
    text-align: center;
}
#userName{
    position: relative;
    width: 100%;
    height: 60%;
    overflow-y: auto;
}
#userNameBox{
    width: 90%;
    background: #fff;
    margin: 0 auto;
    margin-top: 5%;
    color: #fff;
    font-size: 22px;
    background: rgba(255, 255, 255, .3);
    border-radius: 10px;   
    height: 60%;
}
#startButton{
    color: #fff;
    border-radius: 30px;
    border: none;
    background-image: -webkit-linear-gradient(right, rgb(254,146,35) 0%, rgb(255,92,76) 100%);
    background-image: -o-linear-gradient(right, rgb(254,146,35) 0%, rgb(255,92,76) 100%);
    background-image: linear-gradient(to right, rgb(254,146,35) 0%, rgb(255,92,76) 100%);
    font-size: 20px;
    padding: 16px 60px;
    /* border-bottom: rgb(206, 92, 50) 7px solid; */
    margin-top: -20px;
}
#userul{
    display: inline-block;
    margin: 20px 0;
    position: relative;
    width: 95%;
}
#userli{
    list-style: none;
    text-align: center;
    margin: 5px;
    position: relative;
    display: inline-block;
    line-height: 2.3;
}
#userChip{
    border-radius: 31px;
    background: white;
}

</style>