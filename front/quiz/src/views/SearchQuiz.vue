<template>
    <div class="flex h-screen">
        <div class="flex-1 flex flex-col overflow-hidden">
        <header class="flex justify-between items-center bg-blue-300 p-4">
            <nav-bar></nav-bar>
        </header>
        <div class="flex h-full">
            <nav class="flex w-72 h-full ">
            <div class="w-full flex mx-auto pr-6 py-8">
                <div class="w-full h-full  text-gray-900 text-xl">
                </div>
            </div>
            </nav>
            <main class="flex flex-col w-full bg-white overflow-x-hidden overflow-y-auto mb-14">
            <div class="flex w-full mx-auto px-6 py-8">
                <div class="flex flex-col w-full h-full text-gray-900 text-xl ">
                    <div id="quizSearchBody">
                        <div id="quizSearch">
                            <dl id="quizSearchWrap">
                                <input id="quizSearchInput">
                                <button id="quizSearchButton"></button>
                            </dl>
                        </div>
                    </div>
                    <div id="quizCards">
                        <div id="cardWrap">
                            <p id="searchQuizTitle">퀴즈 목록</p>
                            <div class="mt-3" id="cardContainer">
                                <carousel 
                                    :per-page="5"
                                    :mouse-drag="false"
                                    :paginationEnabled="true"
                                    ref="carousel"
                                >
                                    <slide
                                        v-for="(quiz, index) in quizList"
                                        :key="index"
                                        id="quizCard"                                        
                                    >
                                        <div id="quizCardThumb" @click="goDetail(index)">
                                            <div class="w-full h-full" style="background-size: contain;">
                                                <div id="thumb_quizNum">
                                                    <span>Q. {{quiz.problemList.length}}문제</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="quizCardTitle">
                                            <p class="pl-4 py-4">{{quiz.quizName}}</p>
                                            <div id="quizCardInfo">
                                                <dl class="inline-block bottom-3 absolute">
                                                    <dt id="quizCardInfoName">
                                                        <span class="pl-3">{{userNameList[index]}}</span>
                                                    </dt>
                                                    <dt id="quizCardInfoPreview" class="ml-5" @click="quizDetail(index)">
                                                        <span class="pl-4">보기</span>
                                                    </dt>
                                                </dl>
                                            </div>
                                        </div>
                                    </slide>
                                </carousel>
                            </div>
                            
                        </div>
                    </div>
                    
                </div>
            </div>
            </main>
            <nav class="flex w-72 h-full ">
            <div class="w-full flex mx-auto px-6 py-8">
                <div class="w-full h-full flex items-center justify-center text-gray-900 text-xl">
                    <div class="text-center">
                        <v-app>
                            <v-dialog
                                v-model="dialog"
                                width="1000"
                            >
                                <v-card>
                                    <v-card-title class="text-h5 grey lighten-2">
                                        {{quiz.quizName}}
                                    </v-card-title>
                                    <v-carousel hide-delimiters>
                                        <v-carousel-item
                                            v-for="(problem,index) in quiz.problemList"
                                            :key="index"
                                        >
                                            <div id="qcontent">
                                                <ul class="w-full h-full relative inline-block">
                                                    <li id="qdes">
                                                        <p id="qcontentdes">{{problem.title}}</p>                                                                                    
                                                    </li>
                                                    <li id="qimage">
                                                        <div id="qimgForm">
                                                            <div v-if="problem.imgUrl==='none'" id="qimg" :style="{ backgroundImage: 'url(' + noImg + ')'}"></div>
                                                            <div v-if="problem.imgUrl!=='none'" id="qimg" :style="{ backgroundImage: 'url(' + problem.imgUrl + ')'}"></div>
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
                                                <ul v-if="problem.quizType==2" id="acontentUlOX">
                                                    <li>
                                                        <input v-model="shortAnswer" readonly id="shortAnsr" placeholder="정답을 입력해주세요">
                                                    </li>
                                                    <li id="">
                                                        <div class="ml-3 py-3 w-full text-center">
                                                            <button class="bg-gray-500 rounded-md w-28 h-10 text-white">제출</button>
                                                        </div>                
                                                    </li>
                                                </ul>
                                            </div> 
                                        </v-carousel-item>
                                    </v-carousel>                                
                                </v-card>
                            </v-dialog>
                        </v-app>
                    </div>
                </div>
            </div>
            </nav>
        </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import NavBar from '../components/NavBar.vue'
import { Carousel, Slide } from 'vue-carousel';
import noImg from '@/assets/picture.png'

export default {
    name: 'RoomDetailTest',
    noImg:noImg,
    components: {
        NavBar:NavBar,
        Carousel,
        Slide,
    },
    computed: {
        ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ","ROOM","USERNAMELIST"]),           
    },
    data(){      
        return { 
            dialog: false,
            color:['#02abb0','#318cff','#f8ac59','#ed5565'],
            room:{
                capacity: 0,
                endCode: '',
                owner_no: 0,
                private: false,
                regTime: '',
                roomName: '',
                roomNo: 0
            },
            page:0,                                                                                                                                                       
            total:0,
            start:0,
            size:50,
            startPage:0,
            endPage:0,
            itemsPerPage:5,
            pageCount:0,
            search: '',
            headers: [
                {
                text: '퀴즈 제목',
                align: 'start',
                sortable: false,
                value: 'quizName',
                },
                { text: '문항수', value: 'problemList.length' },            
                { text: '생성일', value: 'regTime' },
                { text: 'action', value: 'actions', sortable: false },            
            ],
            imgUrl:[
                require("../assets/bf_change_apple_s.png"),
                require("../assets/bf_change_bana_s.png"),
                require("../assets/bf_change_berry_s.png"),
                require("../assets/bf_change_choco_s.png"),
                require("../assets/bf_change_kiwi_s.png"),
            ],
            quizList: [],
            regQuizList:[],
            quizNoList:[],
            userNoList:[],
            userNameList:[],
            quiz:'',
        }
    },
    methods:{
        goDetail(value){            
            //this.$store.commit("addQuiz",{quiz:value});
            //router.push({name: 'QuizInfo', query: {quizNo: value.quizNo}})  
            this.regQuizList.push(this.quizList[value])
        },
        addQuiz(){
            this.regQuizList.forEach(el => {
                this.quizNoList.push(el.quizNo)
            });
            this.$store.dispatch("addRoomQuiz",{
                quizNoList: this.quizNoList,
                roomNo: this.room.roomNo
            });
        },     
        deleteOrder(index){
            this.regQuizList.splice(index, 1);
        },
        getUserNameByUserNo(){
            this.$store.dispatch("getUserNameByUserNo",{
                userNoList: this.userNoList,
            }).then(()=>{
                this.userNameList=this.USERNAMELIST
            });
        },
        quizDetail(value){
            this.dialog=!this.dialog
            this.$store.dispatch("searchQuizByquizNo",{
                quizNo: value+1,
            }).then(()=>{
                this.quiz=this.QUIZ
            });
            
        },
    },
    created(){       
        this.userNo=sessionStorage.getItem('userNo')
        //this.room=this.ROOM
        this.room.roomNo=this.$route.query.roomNo        
        this.$store.dispatch("initQuiz").then(()=>{
                this.total=this.TOTAL
                this.pageCount=parseInt(this.total/this.itemsPerPage)+1
                this.$store.dispatch("searchQuiz",{
                    page:this.page,
                    size:this.size
                }).then(()=>{
                    this.quizList=this.QUIZLIST
                    this.quizList.forEach(el=>{
                        this.userNoList.push(el.userNo)
                    })
                    this.getUserNameByUserNo()
                })
        })
    },
    watch:{
        page(){
            this.$store.dispatch("searchQuiz",{
                page:this.page-1,
                size:this.size
            }).then(()=>{
                this.quizList=this.QUIZLIST       
                this.quizList.forEach(el=>{
                    this.userNoList.push(el.userNo)
                })
                this.getUserNameByUserNo()                    
            })
        }
        
    }
}
</script>

<style>
#quizSearchBody{
    flex-direction: row;
    justify-content: center;
    align-items: center;
    display: flex;
}
#quizSearch{
    width: 68%;
    border-bottom: 1px solid #8c8c8c;
    position: relative;
    margin-top: 70px;
    display: inline-flex;
}

#quizSearchWrap{
    height: 49px;
    /* width: 78%; */
    width: 90%;
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    vertical-align: baseline;
    background: transparent;
}
#quizSearchInput{
    background-color: transparent;
    border: none;
    margin: 0;
    padding: 0;
    word-wrap: break-word;
    outline: none;
    font-size: 20px;
    color: #000;
    font-family: cwtexhei,'HGGGothicssi 40g',Sans-serif;
    line-height: 2.8;
    padding-left: 20px;
    width: 100%;
}
#quizSearchButton{
    background: url("https://www.quizn.show/webdata/images/search.png") no-repeat;
    width: 26px;
    height: 26px;
    border: none;
    /* margin: -5px 10px 7px 0; */
    position: absolute;
    right: 15px;
    top: 15px;
}
#quizCards{
    margin-top: 70px;
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    vertical-align: baseline;
    background: transparent;
}
#cardWrap{
    margin-top: 40px;
    position: relative;
    height: auto;
}
#searchQuizTitle{
    font-size: 24px;
    display: inline;
    bottom: 10px;
    position: relative;
}
#cardContainer{
    margin-left: auto;
    margin-right: auto;
    position: relative;
    overflow: hidden;
    z-index: 1;
}
#quizCard{
    width: 20% !important;
    border: 1px #dddddd solid;
    border-radius: 20px;
    float: left;
    margin-right: 1.21%;
    background-color: #fff;
    box-sizing: border-box;
    height: 0;
    overflow: hidden;
    padding-bottom: 21.5%;
    min-width: 195px;
    min-height: 0px;
}
#quizCardInfo{
    padding: 10px 20px 5px 20px;
    overflow: hidden;
    width: 88%;
}
#quizCardThumb{
    position: relative;
    width: 100%;
    height: 0;
    overflow: hidden;
    padding-bottom: 54.3%;
    background-color: #ddd;
    background: url("../assets/picture.png") no-repeat center;
}
#quizCardTitle{
    position: relative;
    width: 100%;
    height: 0;
    overflow: hidden;
    padding-bottom: 54.3%;    
}
#quizCardInfo{
    padding: 10px 20px 5px 20px;
    overflow: hidden;
    width: 88%;
}
#thumb_quizNum{
    padding: 10px 15px;
    background-color: #ff8322;
    position: absolute;
    color: #fff;
    font-size: 15px;
}
#quizCardInfoName{
    background: url("https://www.quizn.show/webdata/images/other_answer_p_img.png") no-repeat center left;
    float: left;
}
#quizCardInfoPreview{
    background: url("https://www.quizn.show/webdata/images/play.png") no-repeat center left;
    text-align: right;
    position: relative;
    float: right;
}
</style>