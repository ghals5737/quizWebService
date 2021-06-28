<template>
<v-app>
<v-container>
  <v-row>
      <v-col md="2">
        <v-navigation-drawer
          floating
          permanent
        >
          <v-list
            dense
            rounded
          >
            <v-list-item
              v-for="item in items"
              :key="item.title"
              link
            >
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>aa</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>  
      </v-col>
      <v-col>
      <v-text-field label="퀴즈 제목 입력"></v-text-field>            
      <!--<div class="btn btn-group">   -->   
      <v-container>
        <v-row          
          justify="left"
        >
        <v-col cols="auto">
         <v-select 
              depressed
              v-model="mod"
              label="퀴즈유형"
              :items="options"
              item-text="name"
              item-value="value"
              ></v-select>
        </v-col>
        </v-row>
        <v-row          
          justify="left"
        >
          <v-col cols="auto">
            <v-btn depressed color="primary">Insert Question</v-btn>
          </v-col>
          <v-col cols="auto">
              <v-btn depressed color="error">Delete Last Question</v-btn>
          </v-col>
          <v-col cols="auto">             
          </v-col>                    
        </v-row>
      </v-container>  
      <!--</div>      -->
      
      <v-divider></v-divider>
      <v-form v-model="valid">
      <v-container>                 
         <v-card >
          <v-subheader dark class="blue text--white">문제 1</v-subheader>  
              <v-divider></v-divider>
           <v-container fluid>
            <v-row >
             <v-col cols="auto">
              <v-checkbox class="d-flex" v-model="checkbox1"></v-checkbox>
             </v-col>
             <v-col>
              <v-text-field label="Include files"></v-text-field>
             </v-col>
            </v-row> 
            <v-row >
             <v-col cols="auto">
              <v-checkbox class="d-flex" v-model="checkbox1"></v-checkbox>
             </v-col>
             <v-col>
              <v-text-field label="Include files"></v-text-field>
             </v-col>
            </v-row> 
            <v-row >
             <v-col cols="auto">
              <v-checkbox class="d-flex" v-model="checkbox1"></v-checkbox>
             </v-col>
             <v-col>
              <v-text-field label="Include files"></v-text-field>
             </v-col>
            </v-row> 
            <v-row >
             <v-col cols="auto">
              <v-checkbox class="d-flex" v-model="checkbox1"></v-checkbox>
             </v-col>
             <v-col>
              <v-text-field label="Include files"></v-text-field>
             </v-col>
            </v-row> 
          </v-container>
        </v-card>       
      </v-container>
    </v-form>
    </v-col>
  </v-row>

</v-container>
</v-app>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'CreateQuiz',
    components: {      
    },
    computed: {
         ...mapGetters(["USER"]),
    },
    data(){
      return{
        items:[
          {title:"aa"}
        ],
        options:[
          {name: "객관식",value:1},
          {name: "주관식",value:2}
        ],
        mod:0,
        order:0,
        quizList:[],
        quiz:{
            problemList: [
                {
                answer: '',
                exampleList: [
                    {
                    des: '',
                    examNo: 0,
                    exampleNO: 0
                    }
                ],
                imgUrl: '',
                prbNo: 0,
                prbOder: 0,
                quizType: 0,
                score: 0,
                title: ''
                }
            ],
            quizName: '',
            quizNo: 0,
            regTime: new Date(),
            userNo: 0
        }
      }
    },
    methods:{
        
    },
    created(){
        
    },
}
</script>

<style>

</style>