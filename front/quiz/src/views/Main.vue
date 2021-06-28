<template>
  <v-app>   
      <div class="my-2">
        <v-btn small @click="goCreateQuiz">퀴즈생성</v-btn>
      </div>
      <div class="my-2">
        <v-btn small color="primary" @click="goSearchQuiz">퀴즈검색</v-btn>
      </div>
      <div class="my-2">
        <v-btn small color="error">Error</v-btn>
      </div>
      <div class="my-2">
        <v-btn small disabled>Disabled</v-btn>
      </div>      
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
                userId: USER.userId,
                userNo: USER.userNo
            })  
        },
        goSearchQuiz(){
            this.$store.dispatch("goSearchQuiz",{
                userId: USER.userId,
                userNo: USER.userNo
            })  
        }              
    },
    created(){
        this.userId=sessionStorage.getItem("userId")
    },
}
</script>

<style>

</style>