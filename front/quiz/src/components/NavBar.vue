<template>
  <v-app-bar
        :clipped-left="$vuetify.breakpoint.lgAndUp"
        app
      >
        <!-- <v-app-bar-nav-icon
          @click.stop="drawer = !drawer"
          class="hidden-lg-and-up"
        ></v-app-bar-nav-icon> -->
        <span
          style="width: 300px"
          class="d-none d-sm-inline"
        >
          <v-toolbar-title class="flex">
            <img src="../assets/puzzle-piece.png" class="w-8 mr-2">
            <p>Quiz</p>
          </v-toolbar-title>
        </span>
        <v-tabs align-with-title v-model="selectedTab">
          <v-tab key=0 @click="goCreateQuiz"><h6 class="font-weight-black">퀴즈 만들기</h6></v-tab>
          <v-tab key=1 @click="goCreateRoom"><h6 class="font-weight-black">방 만들기</h6></v-tab>
          <v-tab key=2 @click="goSearchQuiz"><h6 class="font-weight-black">퀴즈 찾아보기</h6></v-tab>
          <v-tab key=3 @click="goSearchRoom"><h6 class="font-weight-black">방 찾아보기</h6></v-tab>
        </v-tabs>
        
        <!-- <v-text-field
          flat
          solo
          outlined
          dense
          hide-details
          prepend-inner-icon="mdi-magnify"
          label="Search"
          class="hidden-sm-and-down"
        ></v-text-field> -->
        <v-spacer></v-spacer>
        <!-- <v-btn icon>
          <v-icon>mdi-apps</v-icon>
        </v-btn>
        <v-btn icon>
          <v-icon>mdi-bell</v-icon>
        </v-btn> -->
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              v-bind="attrs"
              v-on="on"
              elevation="0"
              icon
              class="d-inline d-md-none"
            >
              <v-avatar
                color="primary"
                size="30"
              >
                <Gravatar class="" :email="userId" :size="200" />
              </v-avatar>
            </v-btn>
          </template>
          <v-list>
            <v-list-item @click="logout">
              <v-list-item-title>
                Logout
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              v-bind="attrs"
              v-on="on"
              large
              text
              outlined
              class="px-2 ml-3 d-none d-md-inline"
            >
              <v-avatar
                color="primary"
                size="30"
              >
                <Gravatar class="" :email="userId" :size="200" />
              </v-avatar>
              <span class="pl-3 d-none d-md-inline">{{userId}}</span>
            </v-btn>
          </template>
          <v-list>
            <v-list-item @click="logout" link>
              <v-list-item-title>
                Logout
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-app-bar>      
</template>

<script>
import { mapGetters } from 'vuex'
import Gravatar from 'vue-gravatar'
export default {
    name: 'NavBar',
    components: { 
      Gravatar     
    },
    computed: {
        ...mapGetters(["USER"]),
    },
    data(){
      return{
        userId:'',
        active_tab:3,
        selectedTab:2,
        tabs:[
          '퀴즈 만들기',
          '방 만들기',
          '퀴즈 찾아보기',
          '방 찾아보기'
        ],
        pw:'',
      }
    },
    methods:{
        goCreateQuiz(){
            for (let i = 1; i < 99999; i++)window.clearInterval(i);
            sessionStorage.setItem("selected",0)
            this.$store.dispatch("goCreateQuiz",{
                userId: this.userId,
                userNo: this.userNo
            })  
        },
        goSearchQuiz(){
            for (let i = 1; i < 99999; i++)window.clearInterval(i);
            sessionStorage.setItem("selected",2)
            this.$store.dispatch("goSearchQuiz",{
                userId: this.userId,
                userNo: this.userNo
            })  
        },    
        goCreateRoom(){
            for (let i = 1; i < 99999; i++)window.clearInterval(i);
            sessionStorage.setItem("selected",1)
            this.$store.dispatch("goCreateRoom")
        },   
        goSearchRoom(){
            for (let i = 1; i < 99999; i++)window.clearInterval(i);
            sessionStorage.setItem("selected",3)
            this.$store.dispatch("goSearchRoom")
        },   
        logout(){
          this.$store.dispatch("logout")
        } ,   
    },
    created(){
        this.userId=sessionStorage.getItem("userId")        
        this.userNo=sessionStorage.getItem("userNo")
        this.selectedTab=sessionStorage.getItem("selected")*1
    },
    mounted () {
        
    },
}
</script>

<style>

</style>