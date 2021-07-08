<template>
    <v-app id="inspire">
        <v-app id="inspire">
            <side-bar></side-bar>
            <nav-bar></nav-bar>     
            <v-main>

            <v-row justify="center">
            <v-dialog
                v-model="dialog"
                persistent
                max-width="600px"
            >
                <v-card>
                <v-card-title>
                    <span class="text-h5">방만들기</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                    <v-row>
                        <v-col
                        cols="12"
                        sm="6"
                        md="4"
                        >
                        <v-text-field
                        v-model="room.roomName"        
                        label="방이름"
                        required       
                        ></v-text-field>
                        </v-col>
                        <v-col
                        cols="12"
                        sm="6"
                        md="4"
                        >
                        <v-text-field
                            v-model="room.capacity"        
                            label="인원수"
                            required       
                        ></v-text-field>
                        </v-col>
                        <v-col
                        cols="12"
                        sm="6"
                        md="4"
                        >
                        <v-switch v-model="room.private" class="ma-2" :label="`${switchText}`"></v-switch>
                        </v-col>
                    </v-row>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                    color="blue darken-1"
                    text
                    @click="dialog = false"
                    >
                    취소
                    </v-btn>
                    <v-btn
                    color="blue darken-1"
                    text
                    @click="createQuiz"
                    >
                    방만들기
                    </v-btn>
                </v-card-actions>
                </v-card>
            </v-dialog>
            </v-row>
            <!-- <v-container
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
            </v-container> -->
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
            dialog: false,
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
        this.dialog=true
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