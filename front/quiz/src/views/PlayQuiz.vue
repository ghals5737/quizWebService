<template>
<v-app>
    <v-list>
        <v-subheader>퀴즈 상세정보</v-subheader>
        <v-list-item-group color="primary">
            <v-list-item
                v-for="(problem, i) in quiz.problemList"
                :key="i"                
            >     
            <v-container fluid>
                <v-subheader  dark class="blue text--white">문제 {{i+1}}</v-subheader>  
                <v-text-field
                    disabled                        
                    v-model="problem.title"
                ></v-text-field>
                    <v-divider></v-divider>        
                    <v-row 
                        v-for="(citem,ci) in problem.exampleList"
                        :key="ci"
                    >           
                        <v-col cols="auto">
                        <v-checkbox class="d-flex" value="1"></v-checkbox>
                        </v-col>
                        <v-col>
                        <v-text-field disabled v-model="citem.des"></v-text-field>
                        </v-col>                     
                    </v-row> 
                </v-container>                  
            </v-list-item>
        </v-list-item-group>
    </v-list>    
</v-app>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'PlayQuiz',
    components: {      
    },
    computed: {
         ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ"]),
    },
    data(){      
        return { 
            quiz:'',
            roomNo:'',           
        }
    },
    methods:{
        
    },
    created(){                
        this.$store.dispatch("searchQuizByRoomNo",{
            roomNo:this.$route.query.roomNo
        }).then(()=>{  
            this.quiz=this.QUIZ              
        })
    },
    watch:{
        
    }
}
</script>

<style>

</style>