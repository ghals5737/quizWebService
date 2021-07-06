<template>
<v-app id="app">
    <v-app id="inspire">    
    <nav-bar></nav-bar>
    <v-container>
        <v-navigation-drawer
            v-model="drawer"
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
                        :inactive="inactive"
                        @click="selectPrb(i)"
                    >   
                        <v-list-item-content>
                            <v-list-item-title>문제 {{i+1}}</v-list-item-title>                    
                        </v-list-item-content>
                        <v-list-item-content>
                            <v-btn depressed color="error" @click="deleteOrder(i)">delete</v-btn>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>
    </v-container>
    <v-container id="createContent">
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
                            v-model="problem.quizType"
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
                    <v-container fluid>
                    <p>Answer</p>
                    <v-row>
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
    </v-app>
    
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import SideBar from '../components/SideBar.vue'
import NavBar from '../components/NavBar.vue'

export default {
    name: 'CreateQuiz',
    components: {      
        SideBar:SideBar,
        NavBar:NavBar, 
    },
    computed: {
         ...mapGetters(["USER"]),
    },
    data(){
      return{
        items:[],
        options:[
            {name: "객관식",value:1},
            {name: "주관식",value:2}
        ],
        mod:0,
        order:1,      
        rOrder:1,  
        exampleDes:[],
        isAnswer:[],
        quizList:[],
        examListIndex:1,
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
                imgUrl: '',
                prbNo: 0,
                prbOder: 0,
                quizType: this.problem.quizType,
                score: this.problem.score,
                title: this.problem.title
            })            
            this.isAnswer=[]   
            this.problem.answer=''
            this.problem.quizType=0
            this.problem.score=0
            this.problem.title=''
            this.problem.exampleList=[]            
            this.exampleDes=[]        
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
        }
    },
    created(){
        
    },
    watch:{        
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
#createContent{
    padding-top: 80px;
}
</style>
