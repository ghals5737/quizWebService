<template>
    <v-app>
        <v-container>
            <v-text-field v-model="roomNo" label="방번호 입력"></v-text-field>          
            <v-btn @click="searchAnswer">정답 검색</v-btn>                  
            <v-textarea v-model="totalScore" label="총점수"></v-textarea>
            <v-textarea v-model="rightScore" label="맞은점수"></v-textarea>
        </v-container>
        {{ANSWERLIST}}
    </v-app>    
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'QuizResult',
    components: {      
    },
    computed: {
         ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ","ANSWERLIST"]),
    },
    data(){      
        return { 
            roomNo:0,
            totalScore:0,
            rightScore:0,
            answerList:[],       
        }
    },
    methods:{
        searchAnswer(){
            this.$store.dispatch("searchAnswerByRoomNo",{
                roomNo:this.roomNo,
                userNo:sessionStorage.getItem('userNo')
            }).then(()=>{  
                this.answerList=this.ANSWERLIST
                this.answerList.forEach(el=>{
                    this.totalScore+=el.score
                    if(el.isAnswer)this.rightScore+=el.score
                })              
            })            
        },        
    },
    created(){                
         
    },
    watch:{
        
    }
}
</script>

<style>

</style>