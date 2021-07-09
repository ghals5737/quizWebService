<template>
    <v-app>
		<v-container>
			<v-btn>시작</v-btn>
			<v-btn>나가기</v-btn>			
		</v-container>
		<v-container>
			<v-row>
				<v-col>인원</v-col>
				<v-col>1/10</v-col>
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
        ...mapGetters(["USER"]),
    },
    data(){
        return{
            msg:'',
			msgList:[],
			userList:[],
        }
    },
    methods:{
        send() {
			if (this.stompClient && this.stompClient.connected) {
				let msg = { 
					'msg':this.msg,					
				}              
				this.stompClient.send("/pub/chat/message", JSON.stringify(msg),{} )     
			}else{
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
						console.log('구독으로 받은 메시지 입니다.', res.body)
						// 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
						console.log("aamsg",res.body)
						this.msgList.push(res.body)
					});               
				},
				error => {
					// 소켓 연결 실패
					console.log('소켓 연결 실패', error)
					this.connected = false
					}
				);        
        }
    },
    created(){
        this.connect()
        this.userId=sessionStorage.getItem("userId")        
        this.userNo=sessionStorage.getItem("userNo")      
		//router.push({name: 'PlayQuiz', query: {roomNo: value.roomNo}})          
    },
}
</script>

<style>

</style>