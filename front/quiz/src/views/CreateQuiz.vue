<template>

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