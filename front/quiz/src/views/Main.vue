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
          <v-row
            align="center"
            justify="center"
          >
            <p class="text--disabled">Page content</p>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </v-app>
  <!-- <v-app>   
      <div class="my-2">
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
      </div>        
      
  </v-app> -->
</template>

<script>
import { mapGetters } from 'vuex'
import SideBar from '../components/SideBar.vue'
import NavBar from '../components/NavBar.vue'

export default {
    name: 'Main',
    components: {   
      SideBar:SideBar,
      NavBar:NavBar,           
    },
    computed: {
         ...mapGetters(["USER"]),
    },
    data(){
      return{
        userId:'',
        pw:'',
        dialog: false,
        drawer: null,
        subgroup: [
          ['Sub-link', 'mdi-account-multiple'],
        ],
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
.theme--light.v-app-bar.v-toolbar.v-sheet {
    background-color: #fff;
}

.theme--light.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined) {
    background-color: #fff;
}

.theme--light.v-btn {
    color: rgba(0,0,0,.54);
}

.v-application--is-ltr .v-list--dense .v-list-group--sub-group .v-list-group__header {
    padding-left: 32px;
}

.theme--light.v-application {
  background: #fafafa
}
</style>