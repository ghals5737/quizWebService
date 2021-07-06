<template>
   <v-navigation-drawer        
        :clipped="$vuetify.breakpoint.lgAndUp"
        app
      >
        
        <v-list-item class="d-lg-none" @click.stop="drawer = !drawer">
          <v-list-item-title>Close</v-list-item-title>
          <v-list-item-icon>
            <v-icon>mdi-close</v-icon>
          </v-list-item-icon>
        </v-list-item>
        
        <v-divider class="d-lg-none"></v-divider>
        
        <!-- <v-list-item>
          <v-list-item-content>
            <v-list-item-title>
              <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">            
          </template>
          <v-list>
            <v-list-item link>
                Change
            </v-list-item>
            <v-list-item link>
                Manage
            </v-list-item>
          </v-list>
        </v-menu>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item> -->
        
        <v-list dense>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>

            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-group
            :value="true"
            prepend-icon="mdi-folder"
          >
            <template v-slot:activator>
              <v-list-item-title>Group</v-list-item-title>
            </template>

            <v-list-group
              :value="true"
              no-action
              sub-group
            >
              <template v-slot:activator>
                <v-list-item-content>
                  <v-list-item-title>Sub-group</v-list-item-title>
                </v-list-item-content>
              </template>

              <v-list-item
                v-for="([title, icon], i) in subgroup"
                :key="i"
                link
              >
                <v-list-item-title v-text="title"></v-list-item-title>

                <v-list-item-icon>
                  <v-icon v-text="icon"></v-icon>
                </v-list-item-icon>
              </v-list-item>
            </v-list-group>

          </v-list-group>
        </v-list>
      </v-navigation-drawer>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'SideBar',
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
        
    },
    created(){        
    },
}
</script>

<style>

</style>