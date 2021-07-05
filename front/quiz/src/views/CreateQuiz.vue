<template>
<v-app id="inspire">
    <v-app id="inspire">
      <side-bar></side-bar>
      <nav-bar></nav-bar>     
      <v-main>
        <v-container
          class="fill-height"
          fluid
        >
            <v-container>
                <v-row>
                    <v-col md="2">
                    <v-list>
                        <v-subheader>등록한 문제</v-subheader>
                        <v-list-item-group color="primary">
                            <v-list-item
                                v-for="(item, i) in quiz.problemList"
                                :key="i"
                                :inactive="inactive"
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
                    </v-col>
                    <v-col>
                    <v-text-field v-model="quiz.quizName" label="퀴즈 제목 입력"></v-text-field>            
                    <!--<div class="btn btn-group">   -->   
                    <v-container>
                    <v-row          
                        justify="start"
                    >
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
                    </v-row>
                    <v-row          
                        justify="start"
                    >
                        <v-col cols="auto">
                        <v-btn depressed color="primary" @click="addOrder">Insert Question</v-btn>
                        </v-col>
                        <v-col cols="auto">
                            <v-btn depressed color="error" @click="deleteLastOrder">Delete Last Question</v-btn>
                        </v-col>
                        <v-col cols="auto">             
                            <v-btn depressed color="warning" @click="makeQuiz">create quiz</v-btn>
                        </v-col>                    
                    </v-row>
                    </v-container>  
                    <!--</div>-->
                    
                    <v-divider></v-divider>
                    <v-form>
                    <v-container>                 
                        <v-card >
                        <v-subheader dark class="blue text--white">문제 {{order}}</v-subheader>  
                        <v-text-field
                        v-model="problem.title"                                    
                        label="문제를 입력하세요"            
                        ></v-text-field>
                        <v-text-field
                        v-model="problem.score"                                    
                        label="점수를 입력하세요"            
                        ></v-text-field>
                            <v-divider></v-divider>
                        <v-container fluid>
                        <v-row >
                            <v-col cols="auto">
                            <v-checkbox class="d-flex" value="1" v-model="isAnswer"></v-checkbox>
                            </v-col>
                            <v-col>
                            <v-text-field v-model="exampleList[0].des" label="선택지를 입력하세요"></v-text-field>
                            </v-col>
                        </v-row> 
                        <v-row >
                            <v-col cols="auto">
                            <v-checkbox class="d-flex" value="2" v-model="isAnswer"></v-checkbox>
                            </v-col>
                            <v-col>
                            <v-text-field v-model="exampleList[1].des" label="선택지를 입력하세요"></v-text-field>
                            </v-col>
                        </v-row> 
                        <v-row >
                            <v-col cols="auto">
                            <v-checkbox class="d-flex" value="3" v-model="isAnswer"></v-checkbox>
                            </v-col>
                            <v-col>
                            <v-text-field v-model="exampleList[2].des" label="선택지를 입력하세요"></v-text-field>
                            </v-col>
                        </v-row> 
                        <v-row >
                            <v-col cols="auto">
                            <v-checkbox class="d-flex" value="4" v-model="isAnswer"></v-checkbox>
                            </v-col>
                            <v-col>
                            <v-text-field v-model="exampleList[3].des" label="선택지를 입력하세요"></v-text-field>
                            </v-col>
                        </v-row> 
                        </v-container>
                    </v-card>       
                    </v-container>
                </v-form>
                </v-col>
                </v-row>  
            </v-container>
        </v-container>
      </v-main>
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
        items:[
          
        ],
        options:[
          {name: "객관식",value:1},
          {name: "주관식",value:2}
        ],
        mod:0,
        order:1,
        isAnswer:[],
        quizList:[],
        problem:{
                answer: '',
                exampleList: [                 
                ],
                imgUrl: '',
                prbNo: 0,
                prbOder: 0,
                quizType: 0,
                score: 0,
                title: ''
        },
        exampleList:[
            { 
                des: '',
                examNo: 1,
                exampleNO: 0
            },
            { 
                des: '',
                examNo: 2,
                exampleNO: 0
            },
            { 
                des: '',
                examNo: 3,
                exampleNO: 0
            },
            { 
                des: '',
                examNo: 4,
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
            this.exampleList.forEach(el=>{
                copyExampleList.push({
                    des: el.des,
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
            this.exampleList.forEach(element => {
                element.des=''
            });           
            this.order=this.quiz.problemList.length+1;
        },
        deleteOrder(index){
            this.quiz.problemList.splice(index, 1);
            this.order=this.quiz.problemList.length+1;
        },
        deleteLastOrder(){
            this.quiz.problemList.splice(-1, 1);
            this.order=this.quiz.problemList.length+1;
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
        }
    },
    created(){
        
    },
}
</script>

<style>

</style>