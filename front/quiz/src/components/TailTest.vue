<template>
<div class="flex h-screen ">
    <div class="flex-1 flex flex-col overflow-hidden">
        <header class="flex justify-between items-center bg-blue-300 p-4">
            <nav-bar></nav-bar>
        </header>
        <div class="flex h-full">
        <nav class="flex w-72 h-full">
            <div class="w-full flex mx-auto pr-6 py-8">
            <div class="w-full h-full text-gray-900 text-xl">
                <p class="mt-2 mb-4 text-center font-extrabold text-xl">퀴즈: 총 {{quiz.problemList.length}} 문제</p>
                <div class="relative">
                    <ul>
                        <li 
                            v-for="(item, i) in quiz.problemList"
                            :key="i"
                            class="relative"
                            @click="quizDetail(item)"
                        >
                            <div class="inline-block ml-4" id="preview">
                                <p class="text-yellow-500 text-sm font-bold pl-1 pt-1" id="preview_type">[{{options[item.quizType].name}}]</p>
                                <p class="pl-1 pt-1" id="preview_title">문제 {{i+1}}</p>
                                <img class="w-1/2 h-auto absolute right-2 top-2" :src="item.imgUrl!=='none'?item.imgUrl:noImg">
                                <ul class="absolute right-4 bottom-1">
                                    <li class="inline">
                                        <button class="border-none" @click="deleteOrder(i)">
                                            <img src="https://www.quizn.show/webdata/images/delete.png">
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            </div>
        </nav>
        <main class="flex flex-col w-full bg-gray-50 overflow-x-hidden overflow-y-auto mb-14">
            <div class="flex w-full mx-auto px-6 py-8">
            <div class="flex flex-col w-full h-full text-gray-900 text-xl">
                    <div class="text-center">
                        <input type="text" v-model="quiz.quizName" class="rounded px-4 py-1 bg-gray-200  border border-gray-400 text-gray-700 placeholder-gray-700 focus:bg-white focus:outline-none" placeholder="퀴즈 제목 입력">
                        <button @click="makeQuiz" class="ml-4 bg-gray-500 rounded-md w-32 h-10 text-white">퀴즈 만들기</button>
                    </div>
                    <div class="text-center w-full mx-auto mt-3 h-full relative top-0 left-0">
                        <p>{{quiz.problemList.length+1}}번 문제</p>
                        <div class="mx-auto mt-4" id="time"></div>
                        <div class="mt-5">
                            <ul class="w-full inline-block">
                                <li class="float-left w-1/2">
                                    <div class="p-3">
                                        <div class="border-2 solid w-full overflow-y-auto inline-block font-semibold" id="content1">
                                            <p class="flex justify-center break-all w-full items-center h-full">{{problem.title}}</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="float-left w-1/2">
                                    <div class="p-3">
                                        <!-- <div class="border-2 solid w-full overflow-hidden font-semibold" id="content2">
                                            <p class="mt-28">No Image</p>
                                        </div> -->
                                        <div
                                            v-if="imgUrl!=='none'"
                                            v-bind:style="{ backgroundImage: 'url(' + imgUrl + ')'}"
                                            class="w-full overflow-hidden font-semibold"
                                            id="content2"
                                        >   
                                        </div>
                                        <div 
                                            v-if="imgUrl=='none'" 
                                            class="w-full overflow-hidden border-2"
                                            style="border: 1px #8f8f8f solid" 
                                            id="content2"
                                        >
                                            
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div v-if="eycFlag">
                            <v-slide-group
                                class="pa-4"                            
                                show-arrows
                            >
                                <v-slide-item
                                v-for="(item,n) in encycList"
                                :key="n"
                                >
                                <v-hover>
                                    <template v-slot:default="{ hover }">
                                        <v-card
                                            class=" mx-2"
                                            height="250"
                                            width="200"
                                            rounded-full
                                            outlined
                                            @click="getEncycDetail(item.dictSeq)"
                                        >
                                        <v-card-title>
                                            {{item.headwd}}
                                        </v-card-title>
                                        <v-img
                                            v-if="item.url!=='none'"
                                            :src="item.url"
                                            height="90px"
                                            width="150px"
                                            class="mx-auto"
                                        >
                                        </v-img>
                                        <v-card-text>
                                            {{item.des}}
                                        </v-card-text>
                                        <v-fade-transition>
                                            <v-overlay
                                            v-if="hover"
                                            absolute  
                                            >
                                                <p>상세보기</p>
                                            </v-overlay>
                                        </v-fade-transition>
                                        </v-card>
                                    </template>
                                </v-hover>
                                </v-slide-item>
                            </v-slide-group>
                            <v-btn v-if="!detailFlag" @click="closeEncyFlag">닫기</v-btn>
                        </div>
                        <div v-if="detailFlag">
                            <v-slide-group
                                class="pa-4"                            
                                show-arrows
                            >
                                <v-slide-item
                                v-for="(item,n) in photoList"
                                :key="n"
                                >
                                    <v-col>
                                        <v-hover>
                                            <template v-slot:default="{ hover }">
                                                    <v-img                                    
                                                        :src="item"
                                                        height="90px"
                                                        width="150px"
                                                        class="mx-auto"
                                                        @click="addImg(item)"
                                                    >
                                                        <v-fade-transition>
                                                            <v-overlay
                                                            v-if="hover"
                                                            absolute  
                                                            >
                                                                <p>이미지 등록</p>
                                                            </v-overlay>
                                                        </v-fade-transition>
                                                    </v-img>
                                            </template>
                                        </v-hover>
                                    </v-col>
                                </v-slide-item>
                            </v-slide-group>
                            <v-sheet
                            class="overflow-y-auto"
                            max-height="150"
                            tile
                            >
                            <p>{{encycDetail.headwdCntt}}</p>
                            </v-sheet>
                            <v-btn @click="closeDetailFlag">닫기</v-btn>
                        </div>
                        <div class="block mt-5">
                            <ul 
                                v-if="typeFlag1"
                                class="w-full relative justify-center align-middle inline-flex flex-row"
                            >
                                <li 
                                    v-for="(item,n) in exampleList"
                                    :key="n"
                                    id="selectAnswer"
                                    class=" overflow-y-auto inline-block"
                                >
                                    <span id="answerNumber">{{n+1}}</span>
                                    <p class="flex justify-center break-all w-full items-center h-full" id="answerContent">{{exampleDes[n]}}</p>
                                </li>
                            </ul>
                            <ul 
                                v-if="typeFlag2"
                                class="w-full relative justify-center align-middle inline-flex flex-row"
                            >
                                <li
                                    id="selectAnswer"
                                >
                                    <p id="imgO"></p>
                                </li>
                                <li
                                    id="selectAnswer"
                                >
                                    <p id="imgX"></p>
                                </li>
                            </ul>
                            <ul 
                                v-if="typeFlag3"
                                class="w-full relative justify-center align-middle inline-flex flex-row"
                            >
                                <div id="contentShortArea">
                                    <p id="contentShortAreaText">정답을 입력해 주세요</p>
                                </div>
                            </ul>
                        </div>
                    </div>
            </div>
            </div>
        </main>
        <nav class="flex w-1/4 h-full bg-white">
            <div class="w-full block mx-auto px-3 py-8 overflow-y-auto">
                <p class="font-extrabold font-mono text-xl">Type</p>
                <div class="inline-block border-b-2 border-gray-300 py-4 w-full">
                    <ul class="">
                        <li v-if="!typeFlag1" class="float-left mr-1 mb-1">
                            <div @click="changeType(0)" class="text-center border-solid border-2 border-gray-400 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type1.png">
                                <p class="text-gray-500 font-semibold">선택형</p>
                            </div>
                        </li>
                        <li v-if="typeFlag1" class="float-left mr-1 mb-1">
                            <div @click="changeType(0)" class="text-center border-solid border-2 border-yellow-500 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type1_on.png">
                                <p class="text-yellow-500 font-semibold text-yellow-500">선택형</p>
                            </div>
                        </li>
                        <li v-if="!typeFlag2" class="float-left mr-1 mb-1">
                            <div @click="changeType(1)" class="text-center border-solid border-2 border-gray-400 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type2.png">
                                <p class="text-gray-500 font-semibold">OX</p>
                            </div>
                        </li>
                        <li v-if="typeFlag2" class="float-left mr-1 mb-1">
                            <div @click="changeType(1)" class="text-center border-solid border-2 border-yellow-500 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type2_on.png">
                                <p class="text-yellow-500 font-semibold">OX</p>
                            </div>
                        </li>
                        <li v-if="!typeFlag3" class="float-left mr-1 mb-1">
                            <div @click="changeType(2)" class="text-center border-solid border-2 border-gray-400 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type3.png">
                                <p class="text-gray-500 font-semibold">단답형</p>
                            </div>
                        </li>
                        <li v-if="typeFlag3" class="float-left mr-1 mb-1">
                            <div @click="changeType(2)" class="text-center border-solid border-2 border-yellow-500 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type3_on.png">
                                <p class="text-yellow-500 font-semibold">단답형</p>
                            </div>
                        </li>
                    </ul>
                </div>
                <p class="font-semibold text-lg text-yellow-500 mt-2">웅진백과 검색</p>
                <div class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <span>
                        <input v-model="searchWord" type="text" class="w-48 bg-grey-lighter text-grey-darker py-2 font-normal rounded text-grey-darkest border border-grey-lighter">
                    </span>
                    <span>
                        <button
                            @click="getEncyc"
                            class="font-semibold border-l mx-1  py-2 bg-yellow-500 hover:bg-yellow-400 text-white border-gray-400 w-14 rounded focus:outline-none cursor-pointer"
                        >
                            <span class="m-auto">검색</span>
                        </button>
                    </span>
                </div>
                <p class="font-extrabold font-mono text-xl text-yellow-500 mt-2">Question</p>
                <div class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <textarea v-model="problem.title" id="questionInput"></textarea>
                </div>
                <p class="font-extrabold font-mono text-xl text-yellow-500 mt-2">Score</p>
                <div class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <div class="flex flex-row">
                        <input v-model="problem.score" type="number" name="price" class="w-full bg-grey-lighter text-grey-darker py-2 font-normal rounded text-grey-darkest border border-grey-lighter rounded-l-none">
                    </div>
                </div>
                <p class="font-extrabold font-mono text-xl text-yellow-500 mt-2">Answer</p>
                <div v-if="typeFlag1" class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <ul>
                        <li
                            v-for="(item,n) in exampleList"
                            :key="n"
                        >
                            <span>
                                <label class="inline-flex items-center mt-3 mr-2">
                                    <input v-model="isAnswer" :value="n" type="checkbox" class="form-checkbox h-5 w-5 text-green-600" checked>
                                </label>
                            </span>
                            <span>
                                <input v-model="exampleDes[n]" type="text" class="w-48 bg-grey-lighter text-grey-darker py-2 font-normal rounded text-grey-darkest border border-grey-lighter">
                            </span>
                            <span>
                                <button
                                    @click="addAnswer"
                                    class="font-semibold border-l mx-1  py-2 bg-blue-500 hover:bg-blue-400 text-white border-gray-400 w-7 rounded focus:outline-none cursor-pointer"
                                >
                                    <span class="m-auto">+</span>
                                </button>
                            </span>
                            <span>
                                <button
                                    @click="deleteAnswer(n)"
                                    class="font-semibold border-l  py-2 bg-red-500 hover:bg-red-400 text-white border-gray-400 w-7 rounded focus:outline-none cursor-pointer"
                                >
                                    <span class="m-auto">-</span>
                                </button>
                            </span>
                        </li>
                    </ul>
                </div>
                <div v-if="typeFlag2" class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <ul>
                        <li>
                            <span>
                                <label class="float-left items-center mt-3 mr-2">
                                    <input v-model="oxAnswer1" @click="oxClick1" type="checkbox" class="form-checkbox h-5 w-5 text-green-600" checked>
                                </label>
                            </span>
                            <div class="float-left" id="O"></div>
                        </li>
                        <li>
                            <span>
                                <label class="float-left items-center mt-3 mr-2">
                                    <input v-model="oxAnswer2" @click="oxClick2" type="checkbox" class="form-checkbox h-5 w-5 text-green-600" checked>
                                </label>
                            </span>
                            <div class="float-left" id="X"></div>
                        </li>             
                    </ul>
                </div>
                <div v-if="typeFlag3" class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <input v-model="shortAnswer" id="shortAnsr" placeholder="정답을 입력해주세요">
                </div>
                <div class="py-3 w-full text-center">
                    <button @click="addOrder" class="bg-gray-500 rounded-md w-28 h-10 text-white">저장</button>
                </div>                
            </div>
            <div class="text-center">
                <v-app>
                    <v-dialog
                        v-model="dialog"
                        width="1000"
                    >
                        <v-card>
                            <div id="qcontent">
                                <ul class="w-full h-full relative inline-block">
                                    <li id="qdes" class="">
                                        <p id="qcontentdes" class="flex justify-center break-all w-full items-center h-full">{{dTitle}}</p>                                                                                    
                                    </li>
                                    <li id="qimage">
                                        <div id="qimgForm">
                                            <div v-if="dUrl==='none'" id="qimg" :style="{ backgroundImage: 'url(' + noImg + ')'}"></div>
                                            <div v-if="dUrl!=='none'" id="qimg" :style="{ backgroundImage: 'url(' + dUrl + ')'}"></div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="my-5" id="acontent">
                                <ul v-if="dType==0" id="acontentUl">
                                    <li 
                                        v-for="(item,n) in dExampleList"
                                        :key="n"
                                        id="acontentli"
                                        :style="{background:color[n]}" 
                                        @click="goNextPrb(n)"
                                    >
                                        <span id="aNumber">{{n+1}}</span>
                                        <div id="aText"></div>
                                        <p id="aDes" class="flex justify-center break-all w-full items-center h-full">{{item.des}}</p>
                                    </li>
                                </ul>
                                <ul v-if="dType==1" id="acontentUlOX">
                                    <li id="liO" @click="goNextPrb(0)">
                                        <p id="imgO"></p>
                                    </li>
                                    <li id="liX" @click="goNextPrb(1)">
                                        <p id="imgX"></p>
                                    </li>
                                </ul>
                                <ul v-if="dType==2" id="acontentUlOX">
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
                        </v-card>
                    </v-dialog>
                </v-app>
            </div>
        </nav>
        </div>
    </div>
</div>
</template>

<script>
import NavBar from '../components/NavBar.vue'
import { mapGetters } from 'vuex'
import noImg from '@/assets/picture.png'
//import { Carousel, Slide } from 'vue-carousel';
import swal from 'sweetalert';

export default {
    name: 'TailTest',
    components: {      
        NavBar:NavBar,
    },
    computed: {
        ...mapGetters(["USER","ENCYCLIST","ENCYCDETAIL","PHOTOLIST"]),
    },
    data(){
        return{
        dialog:false,
        items:[],
        options:[
            {name: "선택형",value:0},
            {name: "OX",value:1},
            {name: "단답형",value:2}
        ],
        color:['#02abb0','#318cff','#f8ac59','#ed5565'],
        mod:0,
        shortAnswer:"",
        noImg:noImg,
        dTitle:'',
        dUrl:'',
        dType:'',
        dExampleList:[],
        typeFlag1:true,
        typeFlag2:false,
        typeFlag3:false,
        order:1,      
        rOrder:1,  
        imgUrl:'none',
        exampleDes:[],
        isAnswer:[],
        quizList:[],
        encycList:[],
        photoList:[],
        encycDetail:{
            headWordDscr: '',
            headwd: '',
            headwdCntt:'',
        },
        examListIndex:1,
        quizType:0,
        detailFlag:false,
        eycFlag:false,
        searchWord:'',
        oxAnswer1:false,
        oxAnswer2:false,
        probleFlag:[true,false,false],
        problem:{
                answer: '',
                exampleList: [                 
                ],
                imgUrl: '',
                prbNo: 0,
                prbOder: 0,
                quizType: 0,
                score: '',
                title: ''
        },
        exampleList:[
            { 
                des: '',
                examNo: 1,
                exampleNO: 0
            },
        ],        
        quiz:{
            problemList: [             
            ],
            quizName: '',
            quizNo: 0,
            regTime: new Date(),
            userNo: 0
            }
        }
    },
    methods:{
        addOrder(){            
            let copyExampleList=[]
            let cnt=1;
            if(this.typeFlag3){
                this.isAnswer=[]
                this.isAnswer.push(this.shortAnswer)
            }
            if(this.isAnswer.length===0){
                swal('Error', '정답을 입력해주세요.', 'error')
            }
            else{
                this.exampleDes.forEach(el=>{
                    copyExampleList.push({
                        des: el,
                        examNo: cnt++,
                        exampleNO: 0
                    })
                })
                this.quiz.problemList.push({
                    answer: this.isAnswer[0],
                    exampleList: copyExampleList,
                    imgUrl: this.imgUrl,
                    prbNo: 0,
                    prbOder: 0,
                    quizType: this.quizType,
                    score: this.problem.score,
                    title: this.problem.title
                })            
                this.isAnswer=[]   
                this.problem.answer=''
                if(this.typeFlag1)this.quizType=0
                if(this.typeFlag2)this.quizType=1
                if(this.typeFlag3)this.quizType=2            
                this.problem.score=0
                this.problem.title=''
                this.problem.exampleList=[]            
                this.exampleDes=[]     
                this.oxAnswer1=false;   
                this.oxAnswer2=false;
                this.order=this.quiz.problemList.length+1;
                this.rOrder=this.order
                this.shortAnswer=""
            }
        },
        deleteOrder(index){
            this.quiz.problemList.splice(index, 1);
            this.order=this.quiz.problemList.length+1;
            this.rOrder=this.order
        },
        makeQuiz(){
            //prbOrder 설정
            let prbOrder=1;
            this.quiz.problemList.forEach(el=>{
                el.prbOder=prbOrder++            
            })
            this.quiz.userNo=sessionStorage.getItem("userNo")
            //userNo 설정
            this.$store.dispatch("makeQuiz",{
                quiz:this.quiz
            })
        },        
        addAnswer(){
            if(this.examListIndex>=4){
                alert("최대 4개까지 등록가능 합니다.")
            }else{
                this.exampleList.push({
                    des: this.exampleDes,
                    examNo: this.examListIndex+1,
                    exampleNO: 0
                })
                this.examListIndex++
            }
        },
        deleteAnswer(index){
            if(this.examListIndex<=1){
                alert("지문은 최소 1개 이상이여야 합니다")
            }else{
                this.exampleList.splice(index,1)
                this.examListIndex--
            }
        },
        selectPrb(index){
            let prb=this.quiz.problemList[index]
            this.problem.title=prb.title
            this.problem.score=prb.score
            this.exampleDes=[]
            prb.exampleList.forEach(el=>{                
                this.exampleDes.push(el.des)
            })            
        },
        oxClick1(){
            this.oxAnswer1=true;
            this.oxAnswer2=false;
            this.isAnswer[0]=0;
        },
        oxClick2(){
            this.oxAnswer1=false;
            this.oxAnswer2=true;
            this.isAnswer[0]=1;
        },
        getEncyc(){
            this.eycFlag=true
            this.$store.dispatch("getEncyc",{searchWord:this.searchWord}).then(()=>{
                this.encycList=this.ENCYCLIST
                //console.log("aaaabbbbb")
                //console.log(this.encycList)
                //console.log(this.encycList[0])
            })
        },
        getEncycDetail(dictSeq){
            this.detailFlag=true
            this.$store.dispatch("getEncycDetail",{dictSeq:dictSeq}).then(()=>{
                this.encycDetail=this.ENCYCDETAIL
                this.photoList=this.PHOTOLIST
                //console.log("testtest")
                //console.log(this.encycDetail)
                //console.log(this.photoList)
            })
        },
        closeDetailFlag(){
            this.detailFlag=false
        },
        closeEncyFlag(){
            this.eycFlag=false
        },
        addImg(url){
            this.imgUrl=url            
        },
        changeType(value){
            if(value==0){
                this.typeFlag1=true
                this.typeFlag2=false
                this.typeFlag3=false
                this.exampleDes=[]
            }else if(value==1){
                this.typeFlag1=false
                this.typeFlag2=true
                this.typeFlag3=false
            }else{
                this.typeFlag1=false
                this.typeFlag2=false
                this.typeFlag3=true
                this.shortAnswer=""
            }
            this.quizType=value
        },
        quizDetail(item){
            this.dialog=!this.dialog
            this.dTitle=item.title
            this.dUrl=item.imgUrl
            this.dType=item.quizType
            this.dExampleList=item.exampleList
        }
    },
    created(){
        
    },
    watch:{   
        quizType(){
            if(this.quizType==0){
                this.probleFlag[0]=true
                this.probleFlag[1]=false
                this.probleFlag[2]=false
                this.exampleList=[{des: '',examNo: 1,exampleNO: 0},]
            }else if(this.quizType==1){
                this.probleFlag[0]=false
                this.probleFlag[1]=true
                this.probleFlag[2]=false          
            }else{
                this.probleFlag[0]=false
                this.probleFlag[1]=false
                this.probleFlag[2]=true
            }
        }     
    }
}
</script>

<style>
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap');

    html, body {
        font-family: 'Noto Sans KR', sans-serif;
    }
    #questionInput {
        resize: none;
        width:100%;
        height: 90px;
        overflow:auto;
        border:1px solid #dddddd;
        background:#FFF;
        border-radius: 5px;
        text-indent: 2px;
        margin:0 0 5px;
        padding-top:5px;
    }
    #O{
        width: 70px;
		height: 70px;
        background-image: url("https://www.quizn.show/webdata/images/o_answer.png");
    }
    #X{
        width: 70px;
		height: 70px;
        background-image: url("https://www.quizn.show/webdata/images/x_answer.png");
    }
    #time{
        width: 96%;
        height: 10px;
        background: #e5e5e5;
        border-radius: 5px;
    }    
    #content1{
        height: 14vw;
        max-height: 14vw;
        border: 1px #8f8f8f solid;
    }
    #content2{
        height: 14vw;
        max-height: 14vw;
    }
    #selectAnswer{
        border: 2px #8f8f8f solid;
        border-radius: 6%;
        float: left;
        margin-right: 1.2%;
        width: 24%;
        height: 0;
        overflow: hidden;
        padding-bottom: 17.4%;
        box-sizing: border-box;
        position: relative;
    }
    #answerNumber{
        font-weight: bold;
        color: #8f8f8f;
        float: right;
        /* font-style: italic; */
        /* font-family: 'Montserrat', sans-serif; */
        margin-top: 18px;
        right: 18px;
        position: absolute;
        font-size: 1.2vw;
        z-index: 1;
    }
    #answerContent{
        text-align: center;
        background: transparent;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        line-height: 1.3;
        font-size: 1.2vw;
        color: rgb(49, 49, 49);
        position: absolute;
        width: 100%;
        height: 100%;
        /* font-family: cwtexhei,'HGGGothicssi 60g',Sans-serif; */
    }
    #preview{
        width: 90%;
        /* height: 100%; */
        border: 1px solid #d9d9d9;
        background: #fff;
        display: inline-block;
        margin-left: 20px;
        position: relative;
        height: 0;
        overflow: hidden;
        padding-bottom: 62%;
        font-size: 2vw;
    }
    #preview_title{
        font-size: 14px;        
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
        margin: 0 auto;
        height: auto;
        position: relative;        
    }    
    #shortAnsr{
        border: 1px solid #dddddd;
        border-radius: 3px;
        height: 40px;
        color: #333;
        /* margin-right: 4px; */
        width: 100%;
        font-family: cwtexhei,'HGGGothicssi 60g',Sans-serif;
    }
    #contentShortArea{
        border: 1px #d9d9d9 solid;
        font-size: 24px;
        color: #999;
        line-height: 1.2;
        width: 100%;
        height: 0;
        overflow: hidden;
        padding-bottom: 17.5%;
        font-size: 1.25vw;
        word-break: break-all;
        word-wrap: break-word;
        white-space: pre-line;
        text-align: center;
        font-family: cwtexhei,'HGGGothicssi 60g',Sans-serif;
        display: inline-block;
        margin: 0 auto;
        position: relative;
    }
    #contentShortAreaText{
        position: absolute;
        top: 0;
        width: 100%;
        text-align: center;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        height: 100%;
    }
</style>