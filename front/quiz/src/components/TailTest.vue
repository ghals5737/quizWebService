<template>
<div class="flex h-screen ">
    <div class="flex-1 flex flex-col overflow-hidden">
        <header class="flex justify-between items-center bg-blue-300 p-4">
            <nav-bar></nav-bar>
        </header>
        <div class="flex h-full">
        <nav class="flex w-72 h-full">
            <div class="w-full flex mx-auto pr-6 py-8">
            <div class="w-full h-fulltext-gray-900 text-xl">
                <p class="mt-2 mb-4 text-center font-extrabold text-xl">퀴즈:총 2문제</p>
                <div class="relative">
                    <ul>
                        <li class="relative">
                            <div class="inline-block ml-4" id="preview">
                                <p class="text-yellow-500 text-sm font-bold pl-1 pt-1" id="preview_type">[선택형]</p>
                                <p class="pl-1 pt-1" id="preview_title">문제1</p>
                                <img class="w-1/2 h-auto absolute right-2 top-2" src="../assets/picture.png">
                                <ul class="absolute right-4 bottom-1">
                                    <li class="inline">
                                        <button class="border-none">
                                            <img src="https://www.quizn.show/webdata/images/delete.png">
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="relative">
                            <div class="inline-block ml-4" id="preview">
                                <p class="text-yellow-500 text-sm font-bold pl-1 pt-1" id="preview_type">[OX]</p>
                                <p class="pl-1 pt-1" id="preview_title">문제2</p>
                                <img class="w-1/2 h-auto absolute right-2 top-2" src="../assets/picture.png">
                                <ul class="absolute right-4 bottom-1">
                                    <li class="inline">
                                        <button class="border-none">
                                            <img src="https://www.quizn.show/webdata/images/delete.png">
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            </div>
        </nav>
        <main class="flex flex-col w-full bg-gray-50 overflow-x-hidden overflow-y-auto mb-14">
            <div class="flex w-full mx-auto px-6 py-8">
            <div class="flex flex-col w-full h-full text-gray-900 text-xl">
                    <input type="text" class="rounded px-4 w-full py-1 bg-gray-200  border border-gray-400 text-gray-700 placeholder-gray-700 focus:bg-white focus:outline-none" placeholder="퀴즈 제목 입력">           
                    <div class="text-center w-full mx-auto mt-3 h-full relative top-0 left-0">
                        <p>1번 문제</p>
                        <div class="mx-auto mt-4" id="time"></div>
                        <div class="mt-5">
                            <ul class="w-full inline-block">
                                <li class="float-left w-1/2">
                                    <div class="p-3">
                                        <div class="border-2 solid w-full overflow-hidden font-semibold" id="content1">

                                        </div>
                                    </div>
                                </li>
                                <li class="float-left w-1/2">
                                    <div class="p-3">
                                        <div class="border-2 solid w-full overflow-hidden font-semibold" id="content2">
                                            <p class="mt-28">No Image</p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="block mt-5">
                            <ul class="w-full relative justify-center align-middle inline-flex flex-row">
                                <li id="selectAnswer">
                                    <span id="answerNumber">1</span>
                                    <p id="answerContent">Answer1</p>
                                </li>
                                <li id="selectAnswer">
                                    <span id="answerNumber">1</span>
                                    <p id="answerContent">Answer1</p>
                                </li>
                                <li id="selectAnswer">
                                    <span id="answerNumber">1</span>
                                    <p id="answerContent">Answer1</p>
                                </li>
                                <li id="selectAnswer">
                                    <span id="answerNumber">1</span>
                                    <p id="answerContent">Answer1</p>
                                </li>
                            </ul>
                        </div>
                    </div>
            </div>
            </div>
        </main>
        <nav class="flex w-1/4 h-full bg-white">
            <div class="w-full block mx-auto px-6 py-8">
                <p class="font-extrabold font-mono text-xl">Type</p>
                <div class="inline-block border-b-2 border-gray-300 py-4 w-full">
                    <ul class="">
                        <li class="float-left mr-1 mb-1">
                            <div class="text-center border-solid border-2 border-gray-400 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type1.png">
                                <p class="text-gray-500 font-semibold">선택형</p>
                            </div>
                        </li>
                        <li class="float-left mr-1 mb-1">
                            <div class="text-center border-solid border-2 border-gray-400 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type2.png">
                                <p class="text-gray-500 font-semibold">OX</p>
                            </div>
                        </li>
                        <li class="float-left mr-1 mb-1">
                            <div class="text-center border-solid border-2 border-gray-400 w-20 h-20 rounded-lg">
                                <img class="mx-auto mt-2" src="https://www.quizn.show/webdata/images/type-icon/ico_type3.png">
                                <p class="text-gray-500 font-semibold">단답형</p>
                            </div>
                        </li>
                    </ul>
                </div>
                <p class="font-extrabold font-mono text-xl text-yellow-500 mt-2">Question</p>
                <div class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <textarea id="questionInput"></textarea>
                </div>
                <p class="font-extrabold font-mono text-xl text-yellow-500 mt-2">Score</p>
                <div class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <div class="flex flex-row">
                        <input type="number" name="price" class="w-full bg-grey-lighter text-grey-darker py-2 font-normal rounded text-grey-darkest border border-grey-lighter rounded-l-none">
                    </div>
                </div>
                <p class="font-extrabold font-mono text-xl text-yellow-500 mt-2">Answer</p>
                <div class="inline-block border-b-2 border-gray-300 py-3 w-full">
                    <ul>
                        <li>
                            <span>
                                <label class="float-left items-center mt-3 mr-2">
                                    <input type="checkbox" class="form-checkbox h-5 w-5 text-green-600" checked>
                                </label>
                            </span>
                            <div class="float-left" id="O"></div>
                        </li>
                        <li>
                            <span>
                                <label class="float-left items-center mt-3 mr-2">
                                    <input type="checkbox" class="form-checkbox h-5 w-5 text-green-600" checked>
                                </label>
                            </span>
                            <div class="float-left" id="X"></div>
                        </li>
                        <!-- <li>
                            <span>
                                <label class="inline-flex items-center mt-3 mr-2">
                                    <input type="checkbox" class="form-checkbox h-5 w-5 text-green-600" checked>
                                </label>
                            </span>
                            <span>
                                <input type="text" class="w-48 bg-grey-lighter text-grey-darker py-2 font-normal rounded text-grey-darkest border border-grey-lighter">
                            </span>
                            <span>
                                <button
                                    class="font-semibold border-l mx-1  py-2 bg-blue-500 hover:bg-blue-400 text-white border-gray-400 w-7 rounded focus:outline-none cursor-pointer"
                                >
                                    <span class="m-auto">+</span>
                                </button>
                            </span>
                            <span>
                                <button
                                    class="font-semibold border-l  py-2 bg-red-500 hover:bg-red-400 text-white border-gray-400 w-7 rounded focus:outline-none cursor-pointer"
                                >
                                    <span class="m-auto">-</span>
                                </button>
                            </span>
                        </li> -->                        
                    </ul>
                </div>
            </div>
        </nav>
        </div>
    </div>
</div>
</template>

<script>
import NavBar from '../components/NavBar.vue'
    export default {
        name: 'TailTest',
        components: {
            NavBar:NavBar,                 
        },
        data(){
        return{
            userId:'',
            pw:'',
        }
        },
        methods:{
        signUp(){        
            this.$store.dispatch("signUp",{
            userId: this.userId,
            pw: this.pw
            })        
        },
        logIn(){
            this.$store.dispatch("logIn",{
            userId: this.userId,
            pw: this.pw
            })        
        },
        },
    }
</script>

<style>
    #questionInput {
        resize: none;
        width:100%;
        height: 90px;
        overflow:auto;
        border:1px solid #dddddd;
        background:#FFF;
        border-radius: 5px;
        text-indent: 2px;
        margin:0 0 5px;
        padding-top:5px;
    }
    #O{
        width: 70px;
		height: 70px;
        background-image: url("https://www.quizn.show/webdata/images/o_answer.png");
    }
    #X{
        width: 70px;
		height: 70px;
        background-image: url("https://www.quizn.show/webdata/images/x_answer.png");
    }
    #time{
        width: 96%;
        height: 10px;
        background: #e5e5e5;
        border-radius: 5px;
    }    
    #content1{
        height: 14vw;
        max-height: 14vw;
        border: 1px #d9d9d9 solid;
    }
    #content2{
        height: 14vw;
        max-height: 14vw;
        border: 1px #d9d9d9 solid;
        background-color: #d9d9d9;
    }
    #selectAnswer{
        border: 1px #d9d9d9 solid;
        border-radius: 6%;
        float: left;
        margin-right: 1.2%;
        width: 24%;
        height: 0;
        overflow: hidden;
        padding-bottom: 17.4%;
        box-sizing: border-box;
        position: relative;
    }
    #answerNumber{
        font-weight: bold;
        color: #d3d3d3;
        float: right;
        /* font-style: italic; */
        /* font-family: 'Montserrat', sans-serif; */
        margin-top: 18px;
        right: 18px;
        position: absolute;
        font-size: 1.2vw;
        z-index: 1;
    }
    #answerContent{
        text-align: center;
        background: transparent;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        line-height: 1.3;
        font-size: .95vw;
        color: #999;
        position: absolute;
        width: 100%;
        height: 100%;
        /* font-family: cwtexhei,'HGGGothicssi 60g',Sans-serif; */
    }
    #preview{
        width: 90%;
        /* height: 100%; */
        border: 1px solid #d9d9d9;
        background: #fff;
        display: inline-block;
        margin-left: 20px;
        position: relative;
        height: 0;
        overflow: hidden;
        padding-bottom: 62%;
        font-size: 2vw;
    }
    #preview_title{
        font-size: 14px;        
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
        margin: 0 auto;
        height: auto;
        position: relative;        
    }    
</style>