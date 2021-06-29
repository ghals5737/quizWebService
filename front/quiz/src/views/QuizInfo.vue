<template>
<v-app>
    <v-list>
        <v-subheader>퀴즈 상세정보</v-subheader>
        <v-list-item-group color="primary">
            <v-list-item
                v-for="(item, i) in QUIZ.problemList"
                :key="i"                
            >                                  
                  <v-card >
                    <v-subheader dark class="blue text--white">문제 {{i+1}}</v-subheader>  
                    <v-text-field
                        v-model="problem.title"                                    
                        label="문제를 입력하세요"            
                    ></v-text-field>
                        <v-divider></v-divider>                          
                    <v-container fluid>
                        <v-row 
                            v-for="(citem,ci) in item.exampleList"
                            :key="ci"
                        >                             
                            <v-col cols="auto">
                                <v-checkbox class="d-flex" value="1" v-model="isAnswer"></v-checkbox>
                            </v-col>                              
                        </v-row> 
                    </v-container>
                  </v-card>       
            </v-list-item>
        </v-list-item-group>
    </v-list>
</v-app>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'QuizInfo',
    components: {      
    },
    computed: {
         ...mapGetters(["USER","QUIZLIST","TOTAL","QUIZ"]),
    },
    data(){      
        return {            
        }
    },
    methods:{
        
    },
    created(){
        
    },
    watch:{
        
    }
}
</script>

<style>

</style>