<template>
  <v-app>
        <div class="flex items-center justify-center" id="logWrap">
          <div class="min-h-screen bg-gray-100 flex flex-col justify-center sm:py-12">
            <div class="p-10 xs:p-0 mx-auto md:w-full md:max-w-md">
              <h1 class="font-bold text-center text-2xl mb-5">Login</h1>  
              <div class="bg-white shadow w-full rounded-lg divide-y divide-gray-200">
                <div class="px-5 py-7">
                  <label class="font-semibold text-sm text-gray-600 pb-1 block">아이디</label>
                  <input v-model="userId" type="text" class="border rounded-lg px-3 py-2 mt-1 mb-5 text-sm w-full" />
                  <label class="font-semibold text-sm text-gray-600 pb-1 block">비밀번호</label>
                  <input v-model="pw" type="password" class="border rounded-lg px-3 py-2 mt-1 mb-5 text-sm w-full" />
                  <button type="button" @click="logIn" class="transition duration-200 bg-yellow-500 hover:bg-yellow-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                      <span class="inline-block mr-2">Login</span>
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-4 h-4 inline-block">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8l4 4m0 0l-4 4m4-4H3" />
                      </svg>
                  </button>
                </div>
                <div class="py-5">
                  <div class="grid grid-cols-2 gap-1">
                    <div class="text-center sm:text-left whitespace-nowrap">
                      <button class="transition duration-200 mx-5 px-5 py-4 cursor-pointer font-normal text-sm rounded-lg text-gray-500 hover:bg-gray-100 focus:outline-none focus:bg-gray-200 focus:ring-2 focus:ring-gray-400 focus:ring-opacity-50 ring-inset">
                          <span class="inline-block ml-1">회원가입</span>
                      </button>
                    </div>
                    
                  </div>
                </div>
              </div>
             
            </div>
          </div>
          <!-- <v-card width="400">
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
          </v-card> -->
        </div>
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
<style>
#loginHeader{
  position: relative;
  width: 100%;
  margin: 0 auto;
  text-align: center;
}
#loginContainer{
  position: relative;
  width: 100%;
  min-height: 400px;
  padding-bottom: 5%;
}
#login_content{
  position: relative;
  margin: 0 auto;
  height: auto;
  margin-top: 45px;
  text-align: center;
  width: 768px;
}
#login_content_wrap{
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  vertical-align: baseline;
  background: transparent;
}
#logWrap{
  background: #efefef;    
}
#inputId{
  width: 480px;
  height: 60px;
  border: 1px solid #dcdcdc;
  border-radius: 4px;
  background: #fff;
  text-indent: 20px;
  font-size: 20px;
  color: #000;
}
</style>
