<template>
<v-app>    
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
        :items="roomList"
        :items-per-page="itemsPerPage"      
        :page.sync="page"
        :search="search"
        hide-default-footer
        @click:row="goRoom"
    ></v-data-table>
    </v-card>
    <v-pagination
        v-model="page"      
        :length="pageCount"      
        :page="page"
        :total-visible="3"      
    ></v-pagination>   
</v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import router from '../router/index'
export default {
    name: 'SearchRoom',
    components: {      
    },
    computed: {
        ...mapGetters(["USER","QUIZLIST","TOTAL","ROOMLIST"]),
    },
    data(){      
        return {
        page:0,
        total:0,
        start:0,
        size:5,
        startPage:0,
        endPage:0,
        itemsPerPage:5,
        pageCount:0,
        search: '',
        userNo:0,
        headers: [
            {
            text: '방 제목',
            align: 'start',
            sortable: false,
            value: 'roomName',
            },
            { text: '인원수', value: 'capacity' },            
            { text: '생성일', value: 'regTime' },            
        ],
        roomList: [        
        ],
        }  
    },
    methods:{
        goRoom(value){            
            router.push({name: 'PlayQuiz', query: {roomNo: value.roomNo}})
        },
    },
    created(){
        this.$store.dispatch("initRoom").then(()=>{
                this.total=this.TOTAL
                this.pageCount=parseInt(this.total/this.itemsPerPage)+1
                this.$store.dispatch("searchRoom",{
                    page:this.page,
                    size:this.size
                }).then(()=>{
                    this.roomList=this.ROOMLIST                           
                })
        })  
        this.userNo=sessionStorage.getItem('userNo')
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