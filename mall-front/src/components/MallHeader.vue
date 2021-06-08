<template>
  <div class="header_head">
    <div class="header_head_box">
      <b class="header_head_p">
        <div style="overflow: hidden">
          <router-link to='/' class="header_head_p_a1" style="width:73px;">Home</router-link>
        </div>
        <div class="header_head_p_cs">
          <a href="#" style="background: #C81623;color: #fff;">北京</a>
          <a href="#">上海</a>
          <a href="#">天津</a>
          <a href="#">重庆</a>
          <a href="#">河北</a>
          <a href="#">山西</a>
          <a href="#">河南</a>
          <a href="#">辽宁</a>
          <a href="#">吉林</a>
          <a href="#">黑龙江</a>
          <a href="#">内蒙古</a>
          <a href="#">江苏</a>
          <a href="#">山东</a>
          <a href="#">安徽</a>
          <a href="#">浙江</a>
          <a href="#">福建</a>
          <a href="#">湖北</a>
          <a href="#">湖南</a>
          <a href="#">广东</a>
          <a href="#">广西</a>
          <a href="#">江西</a>
          <a href="#">四川</a>
          <a href="#">海南</a>
          <a href="#">贵州</a>
          <a href="#">云南</a>
          <a href="#">西藏</a>
          <a href="#">陕西</a>
          <a href="#">甘肃</a>
          <a href="#">青海</a>
          <a href="#">宁夏</a>
          <a href="#">新疆</a>
          <a href="#">港澳</a>
          <a href="#">台湾</a>
          <a href="#">钓鱼岛</a>
          <a href="#">海外</a>
        </div>
      </b>
      <ul>
        <li>
          <a @click.prevent="log" class="li_2" style="cursor: pointer">{{undefined === this.user.username ? "Login" : this.user.username}}</a>
        </li>
        <li>
          <router-link to="/register" class="li_2"> Register</router-link>
        </li>
        <span>|</span>
        <li>
          <router-link to="/space" >My Space</router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import qs from "qs";

export default {
  name: "MallHeader",
  data(){
    return{
      user:{},
    }
  },
  methods:{
    getUser(){
      this.axios.get("auth/getLoginUser")
          .then(response => {
            let code = response.data.code
            if(code === 0){
              this.user = response.data.data
              this.$emit("user", this.user);
            }else if(code === 201){
              this.user = {}
            }else {
              alert("unexcepted error")
            }
          })
          .catch(err => {
            alert("unexcepted error")
          })
    },
    log(){
      if(undefined === this.user.username){
        this.$router.push("/login")
      }else {
        this.$confirm('Logout?', 'Logout', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.axios.post('auth/logout')
              .then(response => {
                if(response.data.code === 0){
                  this.getUser()
                  this.$message({
                    type: 'success',
                    message: 'Logout Success'
                  });
                }else {
                  alert("Unexpected error! Try Again")
                }
              })
              .catch(error =>{
                alert("Unexpected error! Try Again")
              })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Canceled'
          });
        });
      }
    }
  },
  mounted() {
    this.getUser()
    // this.user = this.$session.get("user")
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
.header_head {
  background: #E3E4E5;
  height: 30px;
  line-height: 30px;
  position: relative; }
.header_head .header_head_box {
  width: 1210px;
  margin: 0 auto; }
.header_head .header_head_box .img {
  float: left;
  display: block;
  width: 190px;
  height: 170px; }
.header_head .header_head_box .header_head_p {
  font-size: 12px;
  float: left;
  position: relative;
  width: 160px;
  height: 30px;
  margin-left: 10px;
  font-weight: 100; }
.header_head .header_head_box .header_head_p a {
  text-decoration: none;
  color: #999;
  float: left;
  display: block;
  width: 60px;
  text-align: center; }
.header_head .header_head_box .header_head_p .header_head_p_a1:hover {
  color: red; }
.header_head .header_head_box .header_head_p_cs {
  position: absolute;
  width: 340px;
  height: 230px;
  top: 30px;
  left: 60px;
  background: #fff;
  z-index: 200;
  display: none; }
.header_head .header_head_box .header_head_p_cs a {
  color: #999;
  display: block;
  width: 15%;
  margin: 5px 5px 0 0;
  text-align: center;
  float: left;
  font-weight: 100;
  text-decoration: none; }
.header_head .header_head_box .header_head_p_cs a:hover {
  color: #C81623; }
.header_head .header_head_box ul {
  float: right; }
.header_head .header_head_box ul li {
  float: left;
  padding: 0 10px; }
.header_head .header_head_box ul li a {
  text-decoration: none;
  color: #999;
  font-size: 12px; }
.header_head .header_head_box ul li .li_2 {
  color: red; }
.header_head .header_head_box ul a:hover {
  color: #C81623; }
.header_head .header_head_box ul .header_wdjd {
  width: 60px;
  position: relative; }
.header_head .header_head_box ul .header_wdjd img {
  position: absolute;
  right: 6px;
  top: 12px;
  transition-duration: 1s; }
.header_head .header_head_box ul .header_wdjd img:hover {
  transform: rotate(180deg); }
.header_head .header_head_box ul .header_wdjd .header_wdjd_txt {
  position: absolute;
  top: 30px;
  left: 0;
  width: 208px;
  height: 170px;
  background: #fff;
  z-index: 10;
  display: none; }
.header_head .header_head_box ul .header_wdjd .header_wdjd_txt ul:nth-child(2) {
  margin-top: 15px;
  padding-top: 10px;
  border-top: 1px solid #ccc; }
.header_head .header_head_box ul .header_wdjd .header_wdjd_txt li {
  width: 84px;
  height: 25px; }
.header_head .header_head_box ul .header_wdjd1 {
  width: 60px;
  position: relative; }
.header_head .header_head_box ul .header_wdjd1 img {
  position: absolute;
  right: 6px;
  top: 12px;
  transition-duration: 1s; }
.header_head .header_head_box ul .header_wdjd1 .header_wdjd_txt {
  position: absolute;
  top: 30px;
  left: -100px;
  width: 180px;
  height: 270px;
  background: #fff;
  z-index: 10;
  display: none; }
.header_head .header_head_box ul .header_wdjd1 .header_wdjd_txt p {
  color: #666;
  font-size: 14px;
  padding: 0 20px 0 10px; }
.header_head .header_head_box ul .header_wdjd1 .header_wdjd_txt ul:nth-child(2) {
  margin-top: 10px;
  border-top: 1px solid #ccc; }
.header_head .header_head_box ul .header_wdjd1 .header_wdjd_txt li {
  width: 70px;
  height: 27px; }
.header_head .header_head_box ul .header_sjjd {
  position: relative; }
.header_head .header_head_box ul .header_sjjd .header_sjjd_div {
  position: absolute;
  top: 30px;
  display: none;
  right: 5px;
  border: 1px solid #ccc;
  padding: 3px 3px 0; }
.header_head .header_head_box ul .header_wzdh {
  position: relative; }
.header_head .header_head_box ul .header_wzdh .header_wzdh_txt {
  position: absolute;
  top: 30px;
  left: -1057px;
  background: #fff;
  z-index: 10;
  width: 1210px;
  height: 190px;
  display: none; }
.header_head .header_head_box ul .header_wzdh .header_wzdh_txt ul {
  padding: 10px;
  float: left;
  width: 25%;
  height: 100%;
  border-right: 1px solid #eee; }
.header_head .header_head_box ul .header_wzdh .header_wzdh_txt ul p {
  margin-left: 10px;
  margin-bottom: -5px; }
.header_head .header_head_box ul .header_wzdh .header_wzdh_txt ul li {
  height: 25px; }
.header_head .header_head_box ul span {
  font-size: 12px;
  color: #ccc;
  float: left; }
</style>
