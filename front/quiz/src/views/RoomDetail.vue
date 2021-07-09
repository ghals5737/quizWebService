<template>
    <v-app id="inspire">
    <v-app id="inspire">
        <nav-bar></nav-bar>
        <v-container>
        <v-navigation-drawer
            :clipped="$vuetify.breakpoint.lgAndUp"
            app
        >   
            <v-row>
                <v-col class="text-center">
                    등록한 퀴즈
                </v-col>                
            </v-row>            
            <v-list>                
                <v-list-item-group color="primary">
                    <v-list-item
                        v-for="(item, i) in regQuizList"
                        :key="i"                            
                    >   
                        <v-list-item-content>
                            <v-list-item-title>퀴즈 {{i+1}}</v-list-item-title>                    
                        </v-list-item-content>
                        <v-list-item-content>
                            <v-btn depressed color="error" @click="deleteOrder(i)">delete</v-btn>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>
        </v-container>
        <v-main>
        <v-container            
            fluid
        >
            <v-container>
                <v-row>
                    <v-col>
                        <v-btn color="primary" @click="addQuiz">방만들기</v-btn>
                        <v-card>
                            <v-card-title>
                            Quiz
                            <v-spacer></v-spacer>
                            <v-text-field
                                v-model="search"
                                append-icon="mdi-magnify"
                                label="Search"
                                single-line
                                hide-details
                            ></v-text-field>
                            </v-card-title>
                            <v-data-table
                            :headers="headers"
                            :items="quizList"
                            :items-per-page="itemsPerPage"      
                            :page.sync="page"
                            :search="search"
                            hide-default-footer
                            @click:row="goDetail"
                            >                    
                            </v-data-table>
                        </v-card>
                            <v-pagination
                                v-model="page"      
                                :length="pageCount"      
                                :page="page"
                                :total-visible="3"      
                            ></v-pagination>            
                    </v-col>
                </v-row>
            </v-container>
        </v-container>
        </v-main>
    </v-app>
    </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import NavBar from '../components/NavBar.vue'

export default {
    name: 'RoomDetail',
    components: {
        NavBar:NavBar, 
    },
    computed: {
        ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ","ROOM"]),           
    },
    data(){      
        return {
            room:{
                capacity: 0,
                endCode: '',
                owner_no: 0,
                private: false,
                regTime: '',
                roomName: '',
                roomNo: 0
            },
            page:0,                                                                                                                                                       
            total:0,
            start:0,
            size:5,
            startPage:0,
            endPage:0,
            itemsPerPage:5,
            pageCount:0,
            search: '',
            headers: [
                {
                text: '퀴즈 제목',
                align: 'start',
                sortable: false,
                value: 'quizName',
                },
                { text: '문항수', value: 'problemList.length' },            
                { text: '생성일', value: 'regTime' },
                { text: 'action', value: 'actions', sortable: false },            
            ],
            quizList: [],
            regQuizList:[],
            quizNoList:[],
        }
    },
    methods:{
        goDetail(value){            
            //this.$store.commit("addQuiz",{quiz:value});
            //router.push({name: 'QuizInfo', query: {quizNo: value.quizNo}})                                                                                               
            this.regQuizList.push(value.quizNo)
        },
        addQuiz(){
            this.regQuizList.forEach(el => {
                this.quizNoList.push(el)
            });
            this.$store.dispatch("addRoomQuiz",{
                quizNoList: this.quizNoList,
                roomNo: this.room.roomNo
            });
        },     
        deleteOrder(index){
            this.regQuizList.splice(index, 1);
        },
    },
    created(){       
        this.userNo=sessionStorage.getItem('userNo')
        //this.room=this.ROOM
        this.room.roomNo=this.$route.query.roomNo        
        this.$store.dispatch("initQuiz").then(()=>{
                this.total=this.TOTAL
                this.pageCount=parseInt(this.total/this.itemsPerPage)+1
                this.$store.dispatch("searchQuiz",{
                    page:this.page,
                    size:this.size
                }).then(()=>{
                    this.quizList=this.QUIZLIST
                })
        })
    },
    watch:{
        page(){
            this.$store.dispatch("searchQuiz",{
                page:this.page-1,
                size:this.size
            }).then(()=>{
                this.quizList=this.QUIZLIST                           
            })
        }
        
    }
}
</script>

<style>

</style>