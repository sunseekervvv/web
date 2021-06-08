<template>
  <div class="header_sous">
    <div class="logo">
      <router-link to="/"><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/logo.jpg" alt=""></router-link>
    </div>
    <div class="header_form">
      <input v-model="keyword_input" type="text" placeholder="Search" />
      <a href="#" @click.prevent="searchByKeyword(keyword_input)">Search</a>
    </div>
    <div class="header_ico">
      <div class="header_gw">
        <span style="margin-right: 10px"><router-link to="/cart">Shop Cart</router-link></span>
        <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/shopcart_1.png" />
<!--        <span>0</span>-->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MallSous",
  data(){
    return {
      keyword_input:"",
    }
  },
  methods:{
    searchByKeyword(keyword_input){
      this.$parent.searchByKeyword(keyword_input);
    }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0; }

li,
ul {
  list-style: none; }

a {
  color: #000; }

body,
html {
  background: #fff;
  position: relative; }
.header_sous {
  width: 1210px;
  margin: 0 auto;
  position: relative;
  z-index: 1;
  height: 100px; }
.header_sous .logo {
  z-index: 12;
  float: left;
  width: 276px;
  height: 50px;
  margin: 15px 0 22px; }
.header_sous .header_form {
  overflow: hidden;
  background: #f10215;
  position: absolute;
  left: 380px;
  top: 27px;
  width: 550px;
  height: 35px;
  border: 2px solid #f10215;
  float: left; }
.header_sous .header_form input {
  padding: 4px;
  width: 460px;
  height: 33px;
  line-height: 33px;
  font-size: 14px;
  color: #989898;
  border: 0;
  outline: none;
  float: left; }
.header_sous .header_form a {
  color: #fff;
  border: 0;
  display: block;
  background: #f10215;
  width: 76px;
  height: 35px;
  line-height: 35px;
  font-size: 16px;
  text-align: center;
  float: right;
  text-decoration: none; }
.header_sous .header_ico {
  position: absolute;
  right: 107px;
  top: 27px; }
.header_sous .header_ico .header_gw {
  width: 91px;
  height: 38px;
  border: 1px solid #DFDFDF;
  padding: 0 28px 0 19px;
  background: #F9F9F9;
  line-height: 38px;
  position: relative;
  cursor: default;
  zoom: 1;
  z-index: 10;
  float: left; }
.header_sous .header_ico .header_gw span:nth-child(1) {
  font-size: 13px; }
.header_sous .header_ico .header_gw span:nth-child(1) a {
  color: #F10214;
  text-decoration: none; }
.header_sous .header_ico .header_gw span:nth-child(3) {
  background: #F10214;
  color: #fff;
  border-radius: 50%;
  position: absolute;
  width: 15px;
  height: 15px;
  line-height: 14px;
  top: 5px;
  left: 99px;
  text-align: center;
  font-size: 12px; }
.header_sous .header_ico .header_gw:hover {
  box-shadow: 0px -2px 5px 0px #999;
  border-bottom: 0; }
.header_sous .header_ico .header_ko {
  position: absolute;
  top: 39px;
  left: -160px;
  width: 268px;
  height: 60px;
  line-height: 60px;
  text-align: right;
  background: #fff;
  padding-left: 30px;
  border: 1px solid #DFDFDF;
  margin-top: -1px;
  display: none;
  box-shadow: 0 0 5px 0 #999; }
.header_sous .header_ico .header_ko p {
  background: url(https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/shopcart_nogoods.png) no-repeat;
  font-size: 12px;
  padding-right: 30px;
  width: 89%;
  margin-top: -1px; }
.header_sous .header_form_nav {
  overflow: hidden;
  position: absolute;
  left: 380px;
  top: 65px;
  width: 550px;
  height: 20px;
  line-height: 20px; }
.header_sous .header_form_nav ul li {
  float: left;
  margin-right: 10px;
  white-space: nowrap; }
.header_sous .header_form_nav ul li a {
  color: #666;
  font-size: 12px;
  text-decoration: none; }
.header_sous .header_form_nav ul li .aaaaa {
  color: #f10215; }
.header_sous .header_form_nav ul li:hover a {
  color: #f10215; }
.header_sous nav {
  overflow: hidden;
  position: absolute;
  left: -10px;
  bottom: 0;
  width: 100%;
  height: 40px;
  line-height: 40px;
  float: left; }
.header_sous nav ul {
  float: left; }
.header_sous nav ul li {
  float: left; }
.header_sous nav ul li a {
  height: 33px;
  padding: 0 22px;
  text-align: center;
  text-decoration: none;
  font-size: 14px;
  line-height: 33px;
  color: #333;
  font-weight: 700; }
.header_sous nav ul .nav_li1 {
  padding: 0 10px;
  font-size: 14px;
  position: relative;
  bottom: 0;
  color: #fff;
  width: 190px;
  height: 33px; }
.header_sous nav ul .nav_li1 a {
  display: block;
  height: 33px;
  background: #f30213;
  color: #fff;
  text-decoration: none;
  text-align: center; }
.header_sous nav ul .nav_li:hover a {
  color: red; }
.header_sous nav .spacer {
  overflow: hidden;
  margin: 10px 0;
  width: 1px;
  height: 13px;
  display: block;
  background-color: #eee;
  float: left; }
.header_sous .header_main_left {
  background: #fff;
  position: absolute;
  top: 135px;
  left: 0;
  width: 190px;
  height: 480px;
  margin-right: 10px;
  display: none; }
.header_sous .header_main_left ul {
  padding: 16px 0; }
.header_sous .header_main_left ul li {
  padding-left: 12px;
  height: 28px;
  line-height: 28px;
  font-size: 0;
  position: relative; }
.header_sous .header_main_left ul li a {
  font-size: 12px;
  text-decoration: none; }
.header_sous .header_main_left ul li a b {
  font-weight: 700; }
.header_sous .header_main_left ul li a b:hover {
  color: #C81623; }
.header_sous .header_main_left .header_li2 .header_main_left_main {
  position: absolute;
  left: 190px;
  top: -44px;
  width: 1000px;
  height: 480px;
  background: #fff;
  z-index: 200;
  padding: 20px;
  display: none; }
.header_sous .header_main_left .header_li2 .header_main_left_main .header_sj {
  width: 800px;
  overflow: hidden; }
.header_sous .header_main_left .header_li2 .header_main_left_main .header_sj .header_sj_a {
  display: block;
  background: #6e6568;
  float: left;
  margin-right: 10px;
  padding: 0 10px; }
.header_sous .header_main_left .header_li2 .header_ol {
  margin-top: 10px;
  width: 730px;
  overflow: hidden; }
.header_sous .header_main_left .header_li2 .header_ol .aaa {
  float: left;
  width: 70px;
  font-size: 8px; }
.header_sous .header_main_left .header_li2 .header_ol li {
  border-bottom: 1px solid #eee;
  margin-left: 70px; }
.header_sous .header_main_left .header_li2 .header_ol li a {
  border-left: 1px solid #999;
  font-size: 12px;
  padding-left: 10px;
  margin-right: 10px; }
.header_sous .header_main_left .header_li2 .header_r {
  position: absolute;
  width: 220px;
  height: 480px;
  top: 0;
  right: 45px; }
.header_sous .header_main_left .header_li2 .header_r .header_r_tu {
  width: 220px;
  margin-top: 20px; }
.header_sous .header_main_left .header_li2 .header_r .header_r_tu a {
  display: block;
  float: left;
  width: 100px;
  height: 38px;
  text-align: center;
  border: 1px solid #f0f0f0; }
.header_sous .header_main_left .header_li2 .header_r .header_r_tu1 {
  position: absolute;
  bottom: 0;
  right: 20px; }
.header_sous .header_main_left .header_li2 .header_r .header_r_tu1 a {
  display: block;
  border: 1px solid #fff; }
</style>
