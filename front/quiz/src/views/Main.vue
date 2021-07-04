<template>
  <v-app>   
      <!-- <div class="my-2">
        <v-btn small @click="goCreateQuiz">퀴즈생성</v-btn>
      </div>
      <div class="my-2">
        <v-btn small color="primary" @click="goSearchQuiz">퀴즈검색</v-btn>
      </div>
      <div class="my-2">
        <v-btn small color="error" @click="goCreateRoom">방만들기</v-btn>
      </div>
      <div class="my-2">
        <v-btn small color="success" @click="goSearchRoom">방검색</v-btn>
      </div> 
       <div class="my-2">
        <v-btn small color="success" @click="goQuizResult">퀴즈결과</v-btn>
      </div>         -->
      
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'Main',
    components: {      
    },
    computed: {
         ...mapGetters(["USER"]),
    },
    data(){
      return{
        userId:'',
        pw:'',
      }
    },
    methods:{
        goCreateQuiz(){
            this.$store.dispatch("goCreateQuiz",{
                userId: this.userId,
                userNo: this.userNo
            })  
        },
        goSearchQuiz(){
            this.$store.dispatch("goSearchQuiz",{
                userId: this.userId,
                userNo: this.userNo
            })  
        },    
        goCreateRoom(){
            this.$store.dispatch("goCreateRoom")
        },   
        goSearchRoom(){
            this.$store.dispatch("goSearchRoom")
        },
        goQuizResult(){
            this.$store.dispatch("goQuizResult")
        }
    },
    created(){
        this.userId=sessionStorage.getItem("userId")        
        this.userNo=sessionStorage.getItem("userNo")        
    },
}
</script>

<style>

</style>