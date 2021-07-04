<template>
  <div id="app">
  <vuenavbar @toggle-nav-drawer="toggleDrawer"></vuenavbar>
  <vuedrawer v-bind:open="open">
    <div slot="drawer-content">
      <ul class="nav flex-column">
        <li class="nav-item active">
          <a class="nav-link">
            <i class="ion-clipboard"></i> Charts
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link">
            <i class="ion-map"></i> Location
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link">
            <i class="ion-ios-settings-strong"></i> Settings
          </a>
        </li>
      </ul>
    </div>
    <div slot="main-content">
      <div class="container-fluid">
        <div class="row">          
          <div class="col" v-for="item in items" v-bind:key="item">
            <div class="card">
              <div class="card-block">
                <p><i :class="item"></i></p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </vuedrawer>
</div>

</template>

<script>
import { mapGetters } from 'vuex'
import vuedrawer from '@/components/vue-drawer.vue'
import vuenavbar from '@/components/vue-navbar.vue'
export default {
 name: 'MainTest',
    components: {  
        vuedrawer:vuedrawer,
        vuenavbar:vuenavbar,    
    },
    computed: {
         ...mapGetters(["USER"]),
    },
    data(){
      return{
          aa:'',
         open: true,
         items: [
            'ion-social-html5-outline',
            'ion-social-nodejs',
            'ion-social-css3-outline',
            'ion-social-github'
         ],
      }
    },
    methods:{
        toggleDrawer() {              
            this.open = !this.open;
        }  
    },
    created(){        
    },
}
</script>

<style>
a {
  cursor: pointer;
}
.container-fluid { padding: 20px; }

.navbar {
  background-color: #0277BD;
  color: white;    
}
.navbar.brand-icon {
    font-size: 1.7em;
    padding-right: 20px;
}

.btn-orange {
  background-color: #EF6C00;
  color: white;
}
#app {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  
  
}
#app.card.card-block {
    color: #EF6C00;
    text-align: center;
    font-size: 6rem;
}
#vue-navdrawer {
  position: relative;
  width: 10%;
  height: 100%;
  background-color: #37474F;
  color: white;
  float: left;
}

#vue-navcontent {
  position: relative;
  width: 100%;
  height: 100%;
}

#navdrawer-container {
  position: absolute;
  width: 100%;
  height: 100%;
}



.left-nav-enter,
.left-nav-leave-to {
  transform: translateX(-100%);
  -webkit-transform: translateX(-100%);
}

.left-nav-enter-active,
.left-nav-leave-active {
  transition: all .5s ease;
}

@keyframes content-close {
  from {
    width: 90%;
    left: 10%;
  }
  to {
    width: 100%;
    left: 0;
  }
}

@keyframes content-open {
  from {
    width: 100%;
    left: 0;
  }
  to {
    width: 90%;
    left: 10%;
  }
}
</style>