<template>
<v-app id="app">
    <v-app id="inspire">    
    <nav-bar></nav-bar>
    <v-container>
        <v-navigation-drawer
            :clipped="$vuetify.breakpoint.lgAndUp"
            app
        >   
            <v-row>
                <v-col class="text-center">
                    등록한 문제
                </v-col>                
            </v-row>            
            <v-list>                
                <v-list-item-group color="primary">
                    <v-list-item
                        v-for="(item, i) in quiz.problemList"
                        :key="i"                        
                    > 
                    <v-list-item-content>
                        <v-card
                            class="mx-auto"
                            outlined
                            @click="selectPrb(i)"
                        >
                            <v-list-item three-line>
                                <v-list-item-content>
                                <div class="text-h5 mb-4">
                                    문제 {{i+1}}
                                </div>
                                <v-list-item-title class="mb-1">
                                    {{options[item.quizType].name}}
                                </v-list-item-title>                               
                                </v-list-item-content>
                        
                                <v-list-item-avatar
                                tile
                                size="80"
                                color="grey"
                                >
                                <v-img 
                                    :src="item.imgUrl"
                                >
                                </v-img>
                                </v-list-item-avatar>
                            </v-list-item>
                        
                            <v-card-actions>
                                <v-btn
                                    outlined
                                    rounded
                                    text
                                    @click="deleteOrder(i)"
                                    class="mx-auto"
                                >
                                문제삭제
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>
    </v-container>
    <v-container></v-container>
    <v-main>
    <v-container id="createContent" fluid>
                <v-container>
                <v-row          
                    justify="start"
                >
                    <v-col>
                        <v-text-field v-model="quiz.quizName" label="퀴즈 제목 입력"></v-text-field>
                    </v-col>
                    <v-col cols="auto">
                        <v-select 
                            depressed
                            v-model="quizType"
                            label="퀴즈유형"
                            :items="options"
                            item-text="name"
                            item-value="value"
                        ></v-select>
                    </v-col>        
                    <v-col cols="auto">             
                        <v-btn depressed color="warning" @click="makeQuiz">퀴즈 만들기</v-btn>
                    </v-col>                    
            </v-row>
            <v-row>
                <v-col>
                    <v-text-field v-model="searchWord"></v-text-field>
                </v-col>
                <v-col>
                    <v-btn depressed color="warning" @click="getEncyc">웅진 백과 검색</v-btn>
                </v-col>
                <v-col>
                    <span>등록할 이미지</span>
                    <v-img
                        v-if="imgUrl!=='none'"
                        :src="imgUrl"
                        height="140px"
                        width="200px"
                        class="mx-auto"
                    >
                    </v-img>
                </v-col>
            </v-row>
                </v-container>  
                <!--</div>-->
                
                <v-divider></v-divider>
                <v-form>
                <v-container>                 
                    <v-card >                        
                    <v-card-title dark class="blue text--white">문제 {{order}}</v-card-title> 
                    <v-container fluid>
                        <v-row>
                            <p>Question</p>                       
                            <v-col md="10">
                                <v-text-field
                                v-model="problem.title"                                    
                                label="문제를 입력하세요"                                    
                                ></v-text-field>                            
                            </v-col>
                            <v-col>
                                <v-text-field
                                    v-model="problem.score"                                    
                                    label="점수를 입력하세요"                                             
                                ></v-text-field>                                 
                            </v-col>
                        </v-row>           
                    </v-container> 
                    <v-container>
                        <v-row>                        
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
                                            class="ma-5 rounded-xl"
                                            height="280"
                                            width="220"
                                            outlined
                                            @click="getEncycDetail(item.dictSeq)"
                                        >
                                        <v-card-title>
                                            {{item.headwd}}
                                        </v-card-title>
                                        <v-card-subtitle>
                                            {{item.orgHeadwd}}
                                        </v-card-subtitle>
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
                        </v-row>
                    </v-container>
                    <v-container>
                        <v-row v-if="detailFlag">
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
                        </v-row>
                    </v-container>
                    <v-container fluid>
                    <p>Answer</p>
                    <v-row v-if="probleFlag[0]">
                        <v-col
                            v-for="(item,n) in exampleList"
                            :key="n"
                            cols="12"
                        >
                        <v-text-field
                            v-model="exampleDes[n]"                            
                            outlined
                            clearable
                            label="지문을 입력하세요"
                            type="text"
                        >
                            <template v-slot:prepend mb="10">
                            <v-checkbox
                                v-model="isAnswer"                                    
                                color="success"
                                :value="n"
                                hide-details                                                                        
                            ></v-checkbox>
                            </template>                                
                            <template v-slot:append-outer>
                            <v-menu
                                style="top: -12px"
                                offset-y
                            >
                                <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            small
                                            fab                                                
                                            color="primary"                                            
                                            @click="addAnswer"
                                        >                                                                               
                                        <v-icon>
                                        mdi-plus
                                        </v-icon>
                                        </v-btn>
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"                                            
                                            small                                                
                                            fab                                            
                                            color="error"                                            
                                            @click="deleteAnswer(n)"
                                        >                                       
                                        <v-icon>
                                        mdi-delete
                                        </v-icon>
                                        </v-btn>
                                </template>                                    
                            </v-menu>
                            </template>
                        </v-text-field>
                        </v-col>
                    </v-row>
                    <v-row v-if="probleFlag[1]">                      
                        <v-col md="2"></v-col>
                        <v-col>
                            <v-checkbox v-model="oxAnswer1" @click="oxClick1"></v-checkbox>
                            <img src="@/assets/event_img_o.png" style="width: 300px;">
                        </v-col>
                        <v-col>
                            <v-checkbox v-model="oxAnswer2" @click="oxClick2"></v-checkbox>
                            <img src="@/assets/event_img_x.png" style="width: 300px;">
                        </v-col> 
                        <v-col md="4"></v-col>                       
                        </v-row>
                        <v-row v-if="probleFlag[2]">
                        주간식
                        </v-row>
                    </v-container>
                    <v-container>
                        <v-row>
                            <v-col class="mx-auto" cols="auto">
                                    <v-btn depressed color="blue-grey lighten-3" @click="addOrder"><span id="content4">저장</span></v-btn>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card>                     
                </v-container>
            </v-form>
    </v-container>    
    </v-main>        
    </v-app>
    </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
//import SideBar from '../components/SideBar.vue'
import NavBar from '../components/NavBar.vue'

export default {
    name: 'CreateQuiz',
    components: {      
        //SideBar:SideBar,
        NavBar:NavBar, 
    },
    computed: {
        ...mapGetters(["USER","ENCYCLIST","ENCYCDETAIL","PHOTOLIST"]),
    },
    data(){
        return{
        items:[],
        options:[
            {name: "객관식",value:0},
            {name: "OX",value:1},
            {name: "주관식",value:2}
        ],
        mod:0,
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
            this.quizType=0
            this.problem.score=0
            this.problem.title=''
            this.problem.exampleList=[]            
            this.exampleDes=[]     
            this.oxAnswer1=false;   
            this.oxAnswer2=false;
            this.order=this.quiz.problemList.length+1;
            this.rOrder=this.order
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
        addImg(url){
            this.imgUrl=url            
        },
    },
    created(){
        
    },
    watch:{   
        quizType(){
            if(this.quizType==0){
                this.probleFlag[0]=true
                this.probleFlag[1]=false
                this.probleFlag[2]=false
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
.v-text-field--outlined .v-input__prepend-outer, .v-text-field--outlined .v-input__append-outer {
    margin-top: -5px;    
}
.v-btn--fab.v-size--small {    
    margin-top: 8px;
    margin-right: 2px;
}
#content4{
    /* font-family: 'Lato', Arial, sans-serif; */
    font-weight: bold;
    font-size: 15px;
}
</style>
