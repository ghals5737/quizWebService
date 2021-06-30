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
      :items="quizList"
      :items-per-page="itemsPerPage"      
      :page.sync="page"
      :search="search"
      hide-default-footer
       @click:row="goDetail"
    ></v-data-table>
  </v-card>
  <v-pagination
      v-model="page"      
      :length="pageCount"      
      :page="page"
      :total-visible="3"      
   ></v-pagination>
   {{page}}
</v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import router from '../router/index'
export default {
    name: 'SearchQuiz',
    components: {      
    },
    computed: {
         ...mapGetters(["USER","QUIZLIST","TOTAL"]),
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
        headers: [
            {
            text: '퀴즈 제목',
            align: 'start',
            sortable: false,
            value: 'quizName',
            },
            { text: '문항수', value: 'problemList.length' },            
            { text: '생성일', value: 'regTime' },            
        ],
        quizList: [        
        ],
        }  
    },
    methods:{
        goDetail(value){            
            this.$store.commit("addQuiz",{quiz:value});
            router.push({name: 'QuizInfo', query: {quizNo: value.quizNo}})                                                                                               
        },     
    },
    created(){
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