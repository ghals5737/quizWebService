<template>
  <v-app>
   <!-- <form>    
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
  </form> -->
  <v-container class="mx-auto">    
        <v-card width="400">
          <v-card-title>
            <h1 class="display-1">Login</h1>
          </v-card-title>
          <v-card-text>
            <v-form>
              <v-text-field
                v-model="userId"  
                label="Username"
                prepend-icon="mdi-account-circle"
              >
              </v-text-field>
              <v-text-field
                :type="showPassword ? 'text' : 'password'"
                label="Password"
                prepend-icon="mdi-lock"
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                v-model="pw"
                @click:append="showPassword = !showPassword"
              >
              </v-text-field>
            </v-form>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn color="success" @click="signUp">Register</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="info" @click="logIn">Login</v-btn>
          </v-card-actions>
        </v-card>
      </v-container>
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
