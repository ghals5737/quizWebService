<template>
  <section id="navdrawer">
  <div id="navdrawer-container" v-bind:class="open ? 'nav-open' : 'nav-closed'">
    <transition name="left-nav">
      <div id="vue-navdrawer" v-show="open" class="hidden-md-down">
        <slot name="drawer-content"></slot>
       </div>
    </transition>
    <div id="vue-navcontent">
      <slot name="main-content"></slot>
    </div>
  </div>
</section>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'vue-drawer',
    components: {      
    },
    props: {
    open: {
      type: Boolean,
      required: true
    }
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

.nav-open#vue-navcontent {
    left: 10%;
    width: 90%;
    animation: content-open 0.5s ease;
  }
.nav-closed#vue-navcontent {
    animation: content-close 0.5s ease;
}

</style>