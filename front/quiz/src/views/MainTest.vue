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

</template>

<script>
import { mapGetters } from 'vuex'
import SideBar from '../components/SideBar.vue'
import NavBar from '../components/NavBar.vue'

export default {
 name: 'MainTest',
    components: {          
      SideBar:SideBar,
      NavBar:NavBar,        
    },
    computed: {
         ...mapGetters(["USER"]),
    },
    data(){
      return{
          dialog: false,
          drawer: null,
          subgroup: [
            ['Sub-link', 'mdi-account-multiple'],
          ],
      }
    },
    methods:{       
    },
    created(){ 
             
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