<template>
	<v-app id="app">
		<v-app id="inspire">
		<nav-bar></nav-bar>
		<side-bar></side-bar>		
		<v-container bg fill-height grid-list-md text-xs-center>
		<v-main>
			<v-card
				class="mx-auto"
				max-width="400"				
			>
			<v-card-title class="white--text orange darken-4 h2">
				{{room.roomName}}
				<v-spacer></v-spacer>
				
			</v-card-title>
		
			<v-card-text class="pt-4 h3">
				<v-icon>
					mdi-account
				</v-icon>
				<span>총 참여자 수 {{userList.length}}/{{room.capacity}}</span>
			</v-card-text>
		
			<v-divider></v-divider>
		
			<v-virtual-scroll
				:items="userList"
				:item-height="50"
				height="300"
			>
				<template v-slot:default="{ item }">
				<v-list-item>
					<v-chip
						color="indigo"
						text-color="white"
						class="mx-auto"
					>
					<v-avatar left>
						<Gravatar class="mt-5" :email="item.userId" :size="200" />
					</v-avatar>
					<span mr="5">&nbsp;{{item.userId}}</span>
					</v-chip>
				</v-list-item>
				</template>
			</v-virtual-scroll>
				<v-card-actions >
					<v-btn class="mx-auto" color="success" @click="quizStart">퀴즈 시작</v-btn>
				</v-card-actions>
			</v-card>
		</v-main>
		</v-container>    
		</v-app>
	</v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import SideBar from '../components/SideBar.vue'
import NavBar from '../components/NavBar.vue'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import router from '../router/index'
import Gravatar from 'vue-gravatar'

export default {
    name: 'SocketTest',
    components: {   
		NavBar:NavBar,
		Gravatar,           
        SideBar:SideBar,       
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
							router.push({name: 'TestPlayQuiz', query: {roomNo: this.$route.query.roomNo}})		
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

</style>