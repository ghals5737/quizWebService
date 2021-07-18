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
                        <div class="border-yellow-300 border-2" id="titleBox">방제목</div>
                        <p class="mx-auto" id="userCnt">
                            <span class="ml-5">총 참여자수 0</span>
                        </p>
                        <div id="userNameBox">
                            <div id="userName">
                                <ul>

                                </ul>
                            </div>
                        </div>
                        <div class="mt-10 text-center">
                            <button id="startButton">
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
import NavBar from '../components/NavBar.vue'

export default {
    name: 'WaitingRoom',
    components: {
        NavBar      
    },
    data(){
      return{
        userId:'',
        pw:'',
      }
    },
    methods:{
      signUp(){        
        this.$store.dispatch("signUp",{
          userId: this.userId,
          pw: this.pw
        })        
      },
      logIn(){
        this.$store.dispatch("logIn",{
          userId: this.userId,
          pw: this.pw
        })        
      },
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
</style>