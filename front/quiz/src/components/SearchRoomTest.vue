<template>
<div class="flex h-screen">
    <div class="flex-1 flex flex-col overflow-hidden">
    <header class="flex justify-between items-center bg-blue-300 p-4">
        <nav-bar></nav-bar>
    </header>
    <div class="flex h-full">
        <nav class="flex w-72 h-full ">
        <div class="w-full flex mx-auto pr-6 py-8">
            <div class="w-full h-full  text-gray-900 text-xl">
            </div>
        </div>
        </nav>
        <main class="flex flex-col w-full bg-white overflow-x-hidden overflow-y-auto mb-14">
        <div class="flex w-full mx-auto px-6 py-8">
            <div class="flex flex-col w-full h-full text-gray-900 text-xl ">
                <div id="quizSearchBody">
                    <div id="quizSearch">
                        <dl id="quizSearchWrap">
                            <input id="quizSearchInput">
                            <button id="quizSearchButton"></button>
                        </dl>
                    </div>
                </div>
                <div id="quizCards">
                    <div id="cardWrap">
                        <p class="" id="searchQuizTitle">방 목록</p>
                        <div class="mt-3" id="cardContainer">
                            <carousel 
                                :per-page="5"
                                :mouse-drag="false"
                                :paginationEnabled="true"
                                ref="carousel"
                            >
                                <slide
                                    v-for="(room, index) in roomList"
                                    :key="index"
                                    id="quizCard"
                                    @click="goRoom(room)"                                        
                                >
                                    <div id="quizCardThumb" @click="goDetail(index)">
                                        <div class="w-full h-full" style="background-size: contain;">
                                            <div id="thumb_quizNum">
                                                <span>최대 {{room.capacity}}명</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="quizCardTitle">
                                        <p class="pl-4 py-4">{{room.roomName}}</p>
                                        <div id="quizCardInfo">
                                            <dl class="inline-block bottom-3 absolute">
                                                <dt id="quizCardInfoName">
                                                    <span class="pl-3">{{userNameList[index]}}</span>
                                                </dt>
                                                <dt id="roomRegTime" class="ml-5">
                                                    <span class="pl-4">{{room.regTime}}</span>
                                                </dt>
                                            </dl>
                                        </div>
                                    </div>
                                </slide>
                            </carousel>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
        </main>
        <nav class="flex w-72 h-full ">
        <div class="w-full flex mx-auto px-6 py-8">
            <div class="w-full h-full flex items-center justify-center text-gray-900 text-xl">
            </div>
        </div>
        </nav>
    </div>
    </div>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
import router from '../router/index'
import NavBar from '../components/NavBar.vue'

export default {
    name: 'SearchRoomTest',
    components: {
        NavBar:NavBar, 
    },
    computed: {
        ...mapGetters(["USER","QUIZLIST","TOTAL","ROOMLIST","USERNAMELIST"]),
    },
    data(){      
        return {
            page:0,
            total:0,
            start:0,
            size:50,
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
            roomList: [],
            userNoList:[],
            userNameList:[],
        }  
    },
    methods:{
        goRoom(value){            
            router.push({name: 'WaitingRoom', query: {roomNo: value.roomNo}})
        },
        getUserNameByUserNo(){
            this.$store.dispatch("getUserNameByUserNo",{
                userNoList: this.userNoList,
            }).then(()=>{
                this.userNameList=this.USERNAMELIST
            });
        },
        leftPad(value) {
            if (value >= 10) {
                return value;
            }
            return `0${value}`;
        },
        toStringByFormatting(source, delimiter = '-') {
            const year = source.getFullYear();
            const month = this.leftPad(source.getMonth() + 1);
            const day = this.leftPad(source.getDate());
            return [year, month, day].join(delimiter);
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
                    this.roomList.forEach(el=>{
                        this.userNoList.push(el.owner_no)
                        el.regTime=this.toStringByFormatting(new Date(el.regTime))
                    })
                    this.getUserNameByUserNo()                        
                })
        })  
        this.userNo=sessionStorage.getItem('userNo')
    },
    watch:{
        page(){
            this.$store.dispatch("searchRoom",{
                page:this.page-1,
                size:this.size
            }).then(()=>{
                this.roomList=this.ROOMLIST                           
            })
        }
    }
}
</script>

<style>
#roomRegTime{
    text-align: right;
    position: relative;
    float: right;
}
</style>