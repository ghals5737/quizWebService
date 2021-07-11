<template>
    <v-app>
		<v-container>
			{{room}}
		</v-container>
		<v-container>
			<v-btn v-if="userNo===room.ownerNo">시작</v-btn>
			<v-btn>나가기</v-btn>			
		</v-container>
		<v-container>
			<v-row>
				<v-col>인원</v-col>
				<v-col>{{userList.length}}/{{room.capacity}}</v-col>
			</v-row>
		</v-container>
		<v-container>
			<v-row>접속한 유저</v-row>
			<v-row 
				v-for="(item,n) in userList"
				:key="n"
			>
				<v-col>{{item}}</v-col>
			</v-row>
		</v-container>
    </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import SideBar from '../components/SideBar.vue'
import NavBar from '../components/NavBar.vue'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    name: 'SocketTest',
    components: {   
        SideBar:SideBar,
        NavBar:NavBar,           
    },
    computed: {
        ...mapGetters(["USER","ROOM"]),
    },
    data(){
        return{
            msg:'',
			room:'',
			userId:'',
			userNo:'',
			isConnected:false,
			msgList:[],
			userList:[],
        }
    },
    methods:{
        sendEnter() {
			if (this.stompClient && this.stompClient.connected) {
				// let msg = { 
				// 	'userId':this.userId,					
				// }              
				this.stompClient.send("/pub/enter", JSON.stringify(this.userId),{} )     
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
					this.stompClient.subscribe("/sub/chat", res => {
						console.log("aamsg",res.body)
						this.msgList.push(res.body)
					});   
					this.stompClient.subscribe("/sub/enter", res => {
						console.log("enter",res.body)
						this.userList.push(res.body)
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
		
    },
    created(){      
		this.getRoomByRoomNo()
		this.connect()
        this.userId=sessionStorage.getItem("userId")        
        this.userNo=sessionStorage.getItem("userNo")      
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