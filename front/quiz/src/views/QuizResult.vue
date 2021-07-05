<template>
    <v-app>
        <p>방번호</p>
        
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