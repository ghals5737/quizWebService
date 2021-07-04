<template>
<v-app>
   <!-- <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
    <button class="navbar-toggler navbar-toggler-right" 
            type="button" 
            data-toggle="collapse"
            data-target="#navbarContent" 
            aria-controls="navbarSupportedContent" 
            aria-expanded="false" 
            aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="brand-icon hidden-md-down" v-on:click="toggleNavDrawer">
      <i class="ion-social-codepen-outline"></i>      
    </a>
    <a class="navbar-brand">Brand</a>
    <div class="collapse navbar-collapse" id="navbarContent">
      <form class="form-inline my-2 my-lg-0 ml-auto">
        <input class="form-control mr-sm-2" type="text" placeholder="Search">
        <button class="btn btn-orange my-2 my-sm-0" type="submit"><i class="ion-search"></i></button>
      </form>
      <ul class="nav hidden-sm-up">
        <li class="nav-item active">
          <a class="nav-link"><i class="ion-clipboard"></i> Charts</a>
        </li>
        <li class="nav-item">
          <a class="nav-link"> <i class="ion-map"></i> Location</a>
        </li>
        <li class="nav-item">
          <a class="nav-link"> <i class="ion-ios-settings-strong"></i> Settings</a>
        </li>
      </ul>
    </div>
  </nav> -->
      <v-toolbar>
      <v-toolbar-title>Toolbar Mobile Menu</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-sm-and-down">
         <v-btn
          v-for="item in menu"
          :key="item.icon"
          :to="item.link"
          flat
        >{{ item.title }}</v-btn>
      </v-toolbar-items>
      <v-menu class="hidden-md-and-up">
        <v-toolbar-side-icon slot="activator"></v-toolbar-side-icon>
        <v-list>
          <v-list-tile v-for="item in menu" :key="item.icon">
            <v-list-tile-content>
              <v-list-tile-title>{{ item.title }}</v-list-tile-title>
             </v-list-tile-content>
          </v-list-tile>   
        </v-list>
      </v-menu>
    </v-toolbar>
</v-app>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'vue-navbar',
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
        toggleNavDrawer () {
            this.$emit('toggle-nav-drawer');
        }       
    },
    created(){        
    },
}
</script>

<style>
.nav-item {
    padding-top: 5px 0;
    color: lightgray;           
}
.nav-item i{ font-size: 1.2em; }
.nav-item:first-child{
    margin-top: 20px;
}
.nav-item:hover{
    background-color: #4e6571;
}
.nav-item.active{
    color: white;
}
</style>