<template>
<div id="app">
    <v-app id="inspire">
        <v-container class="pt-15" fluid><p class="text-center">퀴즈 제목</p></v-container>
        <v-container class="text-xs-center" fluid>            
                <!-- <v-progress-linear
                    v-model="value"
                    :active="show"                    
                    color="#ffd700"
                    height="30"
                ></v-progress-linear> -->               
                <v-row no-gutters cols="12">
                    <v-col sm="1" justify="center">
                        <strong><p class="text-center">{{currentIndex+1}}/{{problemList.length}}</p></strong>
                    </v-col>
                    <v-col sm="10">
                        <v-progress-linear
                        class="rounded-pill"
                        background-color="grey"
                        color="#f0cb35"
                        dark v-model="prog"
                        height="15"                         
                        md="5">
                        </v-progress-linear>
                    </v-col>
                    <v-col sm="1">
                        <strong><p class="text-center">{{20-Math.ceil(prog/5.5) }}</p></strong>
                    </v-col>
                </v-row>                                                
        </v-container>
        <v-container fluid>
            <v-carousel hide-delimiters :show-arrows="false" v-model="currentIndex"> 
                <template v-for="(problem, index) in problemList"> 
                    <v-carousel-item v-if="(index + 1) % columns === 1 || columns === 1" 
                                    :key="index"                                    
                    > 
                        <v-row class="flex-nowrap" style="height:100%"> 
                        <template v-for="(n,i) in columns"> 
                            <template v-if="(+index + i) < problemList.length"> 
                            <v-col :key="i">                                
                                <v-container fluid>
                                    <v-row justify="space-around">
                                        <v-col cols="5">   
                                            <p class="text-sm-left">{{problem.title}}</p>    
                                        </v-col>
                                        <v-col cols="5">
                                            <v-img
                                                :src="problem.imgUrl"
                                                height="150px"
                                                width="150px"
                                                aspect-ratio="1.7"
                                                contain
                                            >
                                            </v-img>                
                                        </v-col>
                                    </v-row>
                                </v-container>                                
                                <v-container fluid>                                    
                                    <v-row v-if="problem.quizType==0">               
                                        <v-col
                                            v-for="(item,n) in problem.exampleList"
                                            :key="n"
                                            cols="auto"                                            
                                            md="3"                    
                                        > 
                                            <v-btn
                                            :elevation="n - 1"
                                            height="110"
                                            width="100%"
                                            selected                                            
                                            :color="colors[n]"
                                            @click="goNextPrb(n)"                                                                                      
                                            >
                                                <v-container align-stretch>{{item.des}}</v-container>                   
                                                <p
                                                    id="number"
                                                    justify="center"
                                                    v-text="n+1"
                                                ></p>
                                            </v-btn>                                                                                         
                                        </v-col>
                                    </v-row>
                                    <v-row v-if="problem.quizType==1"> 
                                        <v-col>                                            
                                        </v-col>                                       
                                        <v-col>
                                            <v-card 
                                                height="125"
                                                width="70%"                                                    
                                                @click="goNextPrb(0)"
                                            >  
                                                <v-img 
                                                    max-height="120"
                                                    max-width="200"
                                                    src="@/assets/event_img_o.png"
                                                ></v-img>                                        
                                            </v-card>
                                        </v-col>
                                        <v-col>
                                            <v-card
                                                height="125"
                                                width="70%"                                                
                                                @click="goNextPrb(1)"
                                             >  
                                              <v-img 
                                                max-height="120"
                                                max-width="200"
                                                src="@/assets/event_img_x.png"
                                              >
                                              </v-img>
                                            </v-card>
                                        </v-col>
                                        <v-col>                                            
                                        </v-col> 
                                    </v-row>
                                    <v-row v-if="problem.quizType==2">
                                    </v-row>
                                </v-container>                                
                            </v-col>
                            </template> 
                        </template> 
                        </v-row> 
                    </v-carousel-item> 
                </template> 
            </v-carousel>  
        </v-container> 
        <v-container>            
        </v-container> 
    </v-app>
</div>


</template>

<script>
import { mapGetters } from 'vuex'

export default {
    name: 'PlayQuiz',
    components: {          
    },
    computed: {
        ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ"]),
        columns() {
            if (this.$vuetify.breakpoint.xl) {
                return 1;
            }

            if (this.$vuetify.breakpoint.lg) {
                return 1;
            }

            if (this.$vuetify.breakpoint.md) {
                return 1;
            }

            return 1;
        },  
    },
    data(){
        return{
            angle: '50',
            color1: 'red',
            color2: 'blue',
            model: null,
            flag:false,
            exampleIndex:-1,
            aa:1,
            value: 0,
            max: 100,
            prog: 0,
            interval:'',
            currentIndex:0,
            problemList:[],
            userAnswerList:[],
            quizList:[],            
            answer:{
                userAnswerList: [],
                userNo: sessionStorage.getItem('userNo'),
                roomNo: this.$route.query.roomNo
            },
            colors:[
                "light-blue darken-3",
                "blue lighten-3",
                "red accent-2",
                "orange lighten-2"
            ],
            slider: [
                "red", 
                "green", 
                "orange", 
                "blue", 
                "pink", 
                "purple", 
                "indigo", 
                "cyan", 
                "deep-purple", 
                "light-green", 
                "deep-orange", 
                "blue-grey"
            ],            
        }
    },
    methods:{
        goNextPrb(n){            
            window.clearInterval(this.interval)
            this.sleep(700).then(() => {                    
                    this.prog=-3
                    this.userAnswerList[this.currentIndex].submitAnswer.push(n)            
                    this.currentIndex++    
                    this.interval=window.setInterval(function(){
                        this.prog += 0.1            
                    }.bind(this), 20)
            });  
        },     
        submitAnswer(){
            this.answer.userAnswerList=this.userAnswerList
            this.$store.dispatch("submitAnswer",{                
                answer:this.answer,
                roomNo:this.$route.query.roomNo
            })
        },
        randomValue() {
            this.value = Math.random() * this.max
        },
        show(){
            alert("??")
        },
        test(){
            this.currentIndex++
        },     
        sleep(ms) {
            return new Promise((r) => setTimeout(r, ms));
        },
        expendEvent(value){            
            if(!this.flag){                
                this.flag=!this.flag
            }else if(this.exampleIndex===value){
                this.flag=!this.flag
            }            
            this.exampleIndex=value
        },        
    },
    created(){
        this.userId=sessionStorage.getItem("userId")        
        this.userNo=sessionStorage.getItem("userNo")
        this.$store.dispatch("searchQuizByRoomNo",{
            roomNo:this.$route.query.roomNo
        }).then(()=>{  
            this.quizList=this.QUIZLIST
            this.quizList.forEach(el => {
                el.problemList.forEach(el1=>{
                    this.problemList.push(el1)
                    this.userAnswerList.push({
                        answer: el1.answer,
                        prbNo: el1.prbNo,
                        score: el1.score,
                        submitAnswer: []
                    })
                })                
            });            
        })
    },
    mounted () {
        // this.interval = setInterval(() => {
        // if (this.value === 100) {
        //     return (this.value = 0)
        // }
        // this.value += 10
        // }, 1000)
        this.interval=window.setInterval(function(){
            this.prog += 0.1            
        }.bind(this), 20)
    },
    watch:{
        prog(){
            if(this.prog>=100){   
                window.clearInterval(this.interval)
                this.sleep(700).then(() => {                    
                    this.prog=-3       
                    this.userAnswerList[this.currentIndex].submitAnswer.push(-1)           
                    this.currentIndex++    
                    this.interval=window.setInterval(function(){
                        this.prog += 0.1            
                    }.bind(this), 20)
                });                
            }
        },
        currentIndex(){
            if(this.currentIndex>=this.problemList.length){               
                this.submitAnswer()
            }
        },
    }
}
</script>

<style>
#number{
    margin-bottom: 50px;
}
.v.application--wrap {   
    min-height: 1vh !important;
}
div.v-progress-linear__determinate{
    background: #f0cb35;
    background: -moz-linear-gradient(90deg,#f0cb35 0%, #c02425 100%);
    background: -webkit-linear-gradient(90deg,#f0cb35 0%, #c02425 100%);
    background: linear-gradient(90deg,#f0cb35 0%,  #c02425 100%);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#f0cb35",endColorstr="#c02425",GradientType=1);
}

</style>