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
                <form>
                        <v-text-field
                        v-model="room.roomName"        
                        label="방이름"
                        required       
                        ></v-text-field>
                        <v-text-field
                        v-model="room.capacity"        
                        label="인원수"
                        required       
                        ></v-text-field>        
                        <v-switch v-model="room.private" class="ma-2" :label="`${switchText}`"></v-switch>
                        <v-btn class="mr-4" @click="createQuiz">방만들기</v-btn>        
                </form>
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
    name: 'CreateRoom',
    components: {       
        SideBar:SideBar,
        NavBar:NavBar, 
    },
    computed: {
        ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ"]),
    },
    data(){      
        return { 
            userNo:0,
            room:{
                capacity: 0,
                endCode: '',
                owner_no: 0,
                private: false,
                regTime: '',
                roomName: '',
                roomNo: 0
            },
            switchText:'비공개',                                                    
        }
    },
    methods:{
        createQuiz(){
            this.room.owner_no=sessionStorage.getItem("userNo")
            this.$store.dispatch("goRoomDetail",{
                room:this.room
            })
        },
    },
    created(){       
        this.userNo=sessionStorage.getItem('userNo')                
    },
    watch:{
        'room.private'(){           
            if(this.room.private===true){
                this.switchText='공개'
            }else{
                this.switchText='비공개'
            }                
        }
    }
}
</script>

<style>

</style>