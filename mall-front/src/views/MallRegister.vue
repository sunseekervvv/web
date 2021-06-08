<template>
<div>
  <header>
    <router-link class="logo" to="/" style="margin-left: 100px"><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/logo.jpg" alt=""></router-link>
    <div class="desc" style="margin-left: 130px">Welcome to Register!</div>
    <div class="dfg">
      <span>Already has account？</span>
      <router-link to="/login">Login</router-link>
    </div>
  </header>
  <section>
    <form action="" class="one">
      <div class="register-box">
        <label class="username_label">Username
          <input v-model="register_data.userName" maxlength="20" type="text" placeholder="Input your username" >
        </label>
        <div class="tips" v-if="validate.userName">{{validate_data.userName}}</div>
      </div>
      <div class="register-box">
        <label  class="other_label">Password
          <input v-model="register_data.password" maxlength="20" type="password" placeholder="Input the password">
        </label>
        <div class="tips" v-if="validate.password">{{validate_data.password}}</div>
      </div>
      <div class="register-box">
        <label class="other_label">Confirm
          <input v-model="password_confirm" maxlength="20" type="password" placeholder="Input the password again">
        </label>
        <div class="tips" v-if="validate.password_confirm">{{validate_data.password_confirm}}</div>
      </div>
      <div class="register-box">
        <label  class="other_label">Phone
<!--          <span>中国 0086∨</span>-->
          <input v-model="register_data.phone" class="phone" maxlength="20" type="text" placeholder="Input your phonenum">
        </label>
        <div class="tips" v-if="validate.phone">{{validate_data.phone}}</div>
      </div>
      <div class="register-box" style="white-space: nowrap;display: block;">
        <label  class="other_label">Code
          <input v-model="code" maxlength="20" type="text" placeholder="Input the code right" style="display: inline-block;width: 200px">
        </label>
        <ValidCode @getRightCode="getRightCode" style="display: inline-block;"></ValidCode>
        <div class="tips" v-if="validate.code">{{validate_data.code}}</div>
      </div>
      <div class="arguement">
        <input type="checkbox" id="xieyi" v-model="agree"> Read and agree
        <a href="#">《User Registration Agreement》</a>
        <a href="#">《Privacy policy》</a>
        <div class="tips" style="color: #ccc;" v-if="validate.agree">{{validate_data.agree}}</div>
        <br />
        <br>
        <div class="submit_btn">
          <button type="submit" id="submit_btn" @click.prevent="register">Register</button>
        </div>
      </div>

    </form>
    <div class="two">
      <div class="right_r">
        <div class="right_r1">
          <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/register/a65a18e877a16246a92e1b755bd88a03_03.png"/>
          <span @click.prevent="toSellerRegister">SELLER</span>
        </div>
      </div>
    </div>
  </section>
</div>
</template>

<script src="./js/mall-register.js"></script>

<style src="../assets/css/register/index.css" scoped></style>
<style scoped></style>
