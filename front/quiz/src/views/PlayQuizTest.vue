<template>
<div id="app">
    <v-app id="inspire">
        <v-container class="pt-15"><p class="text-center">퀴즈 제목</p></v-container>
        <v-container class="text-xs-center">            
                <v-progress-linear
                    v-model="value"
                    :active="show"                    
                    color="#ffd700"
                    height="30"
                ></v-progress-linear>
        </v-container>
        <v-container>
            <v-carousel hide-delimiters :show-arrows="false" v-model="currentIndex"> 
                <template v-for="(problem, index) in problemList"> 
                    <v-carousel-item v-if="(index + 1) % columns === 1 || columns === 1" 
                                    :key="index"
                    > 
                        <v-row class="flex-nowrap" style="height:100%"> 
                        <template v-for="(n,i) in columns"> 
                            <template v-if="(+index + i) < problemList.length"> 
                            <v-col :key="i">
                                <!-- <v-sheet v-if="(+index + i) < slider.length" 
                                        :color="slider[+index + i]"
                                        height="100%"
                                >
                                <v-row class="fill-height"
                                        align="center"
                                        justify="center"
                                >
                                    <div class="display-3">{{+index + i + 1}}</div>                               
                                    <v-btn  @click="test">aaaa</v-btn>                                
                                </v-row>
                                </v-sheet>-->
                                <v-container>
                                    <v-row justify="space-around">
                                        <v-col cols="5">   
                                            <p class="text-sm-left">{{problem.title}}</p>    
                                        </v-col>
                                        <v-col cols="5">
                                            <v-img
                                                src="https://via.placeholder.com/150"
                                                aspect-ratio="1.7"
                                                contain
                                            ></v-img>                
                                        </v-col>
                                    </v-row>
                                </v-container>
                                <v-container>            
                                    <v-row >               
                                        <v-col
                                            v-for="(item,n) in problem.exampleList"
                                            :key="n"
                                            cols="auto"
                                            md="3"                    
                                        > 
                                            <v-btn
                                            :elevation="n - 1"
                                            height="100"
                                            width="100%"
                                            :color="colors[n]"
                                            @click="goNextPrb"
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
                                </v-container>
                            </v-col>
                            </template> 
                        </template> 
                        </v-row> 
                    </v-carousel-item> 
                </template> 
            </v-carousel>  
        </v-container>        
        <!-- <v-container>
            <v-row justify="space-around">
                <v-col cols="5">   
                    <p class="text-sm-left">Right align on large viewport sizes</p>    
                </v-col>
                <v-col cols="5">              
                    <v-img
                        src="https://via.placeholder.com/150"
                        aspect-ratio="1.7"
                        contain
                    ></v-img>                
                </v-col>
            </v-row>
        </v-container>
        <v-container>            
            <v-row >               
                <v-col
                    v-for="n in 4"
                    :key="n"
                    cols="auto"
                    md="3"                    
                > 
                    <v-btn
                    :elevation="n - 1"
                    height="100"
                    width="100%"                    
                    :color="colors[n-1]"                   
                    >
                    <v-container align-stretch>선택</v-container>                   
                    <p   
                        id="number"
                        justify="center"
                        v-text="n - 1"
                    ></p>                    
                    </v-btn>
                </v-col>                
            </v-row>
        </v-container> -->
        <!-- <v-main>
            <v-carousel hide-delimiters :show-arrows="false" v-model="currentIndex"> 
                <template v-for="(item, index) in slider"> 
                <v-carousel-item v-if="(index + 1) % columns === 1 || columns === 1" 
                                :key="index"
                > 
                    <v-row class="flex-nowrap" style="height:100%"> 
                    <template v-for="(n,i) in columns"> 
                        <template v-if="(+index + i) < slider.length"> 
                        <v-col :key="i"> 
                            <v-sheet v-if="(+index + i) < slider.length" 
                                    :color="slider[+index + i]"
                                    height="100%"
                            >
                            <v-row class="fill-height"
                                    align="center"
                                    justify="center"
                            >
                                <div class="display-3">{{+index + i + 1}}</div>                               
                                <v-btn  @click="test">aaaa</v-btn>                                
                            </v-row>
                            </v-sheet>                           
                        </v-col>
                         
                        </template> 
                    </template> 
                    </v-row> 
                </v-carousel-item> 
                </template> 
            </v-carousel>  
        </v-main> -->
    </v-app>
</div>


</template>

<script>
import { mapGetters } from 'vuex'

export default {
    name: 'PlayQuizTest',
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
        }
    },
    data(){
        return{
            model: null,
            aa:1,
            value: 0,
            max: 100,
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
            interval: {},
            
        }
    },
    methods:{
        goNextPrb(){
            this.currentIndex++
        },     
        submitAnswer(){
            this.answer.userAnswerList=this.userAnswerList
            this.$store.dispatch("submitAnswer",{                
                answer:this.answer
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
    },
}
</script>

<style>
#number{
    margin-bottom: 50px;
}
</style>