<template>
  <v-app>
   <form>    
    <v-text-field
      v-model="userId"      
      label="ID"
      name="userId"
      required      
    ></v-text-field>    
    <v-text-field
      v-model="pw"      
      :counter="10"
      label="PASSWORD"
      name="pw"
      required      
    ></v-text-field>
    <v-btn class="mr-4" @click="logIn">logIn</v-btn>
    <v-btn class="mr-4" @click="signUp">signUp</v-btn>
    <v-btn >clear</v-btn>
  </form>
  </v-app>
</template>

<script>
  export default {
    name: 'Home',
    components: {      
    },
    data(){
      return{
        userId:'',
        pw:'',
      }
    },
    methods:{
      signUp(){        
        this.$store.dispatch("signUp",{
          userId: this.userId,
          pw: this.pw
        })        
      },
      logIn(){
        this.$store.dispatch("logIn",{
          userId: this.userId,
          pw: this.pw
        })        
      },
    },
  }
</script>
