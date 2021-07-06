<template>
   
   <v-app-bar
        :clipped-left="$vuetify.breakpoint.lgAndUp"
        app
      >
        <v-app-bar-nav-icon
          @click.stop="drawer = !drawer"
          class="hidden-lg-and-up"
        ></v-app-bar-nav-icon>
        <span
         style="width: 300px"
         class="d-none d-sm-inline"
        >
         
        <v-toolbar-title>
            quiz
        </v-toolbar-title>
         
        </span>
        <v-tabs align-with-title>
          <v-tab @click="goCreateQuiz"><h6 class="font-weight-black">퀴즈 만들기</h6></v-tab>
          <v-tab @click="goCreateRoom"><h6 class="font-weight-black">방 만들기</h6></v-tab>
          <v-tab @click="goSearchQuiz"><h6 class="font-weight-black">퀴즈 찾아보기</h6></v-tab>
          <v-tab @click="goSearchRoom"><h6 class="font-weight-black">방 찾아보기</h6></v-tab>
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
        <v-btn icon>
          <v-icon>mdi-apps</v-icon>
        </v-btn>
        <v-btn icon>
          <v-icon>mdi-bell</v-icon>
        </v-btn>
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
                <img
                  src="https://avataaars.io/?avatarStyle=Transparent&topType=LongHairStraight&accessoriesType=Blank&hairColor=BrownDark&facialHairType=Blank&clotheType=BlazerShirt&eyeType=Default&eyebrowType=Default&mouthType=Default&skinColor=Light"></img>
              </v-avatar>
            </v-btn>
          </template>
          <v-list>
            <v-list-item>
              <v-list-item-title>
                View profile
              </v-list-item-title>
            </v-list-item>
            <v-list-item>
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
                <img
                  src="https://avataaars.io/?avatarStyle=Transparent&topType=LongHairStraight&accessoriesType=Blank&hairColor=BrownDark&facialHairType=Blank&clotheType=BlazerShirt&eyeType=Default&eyebrowType=Default&mouthType=Default&skinColor=Light"></img>
              </v-avatar>
              <span class="pl-3 d-none d-md-inline">{{userId}}</span>
            </v-btn>
          </template>
          <v-list>
            <v-list-item link>
              <v-list-item-title>
                View profile
              </v-list-item-title>
            </v-list-item>
            <v-list-item link>
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
export default {
    name: 'NavBar',
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
    },
    created(){
        this.userId=sessionStorage.getItem("userId")        
        this.userNo=sessionStorage.getItem("userNo")
    },
}
</script>

<style>

</style>