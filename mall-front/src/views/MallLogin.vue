<template>
<div>
  <!--顶部logo-->
  <header>
    <router-link to="/"><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/logo.jpg" /></router-link>
    <p><b>Welcome to login!</b></p>
  </header>

  <div class="top-2">
    <div class="top-2a">
      <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/login/4de5019d2404d347897dee637895d02b_11.png" style="margin-top: 7px"/>
      <p>Please complete the mobile phone number verification as soon as possible</p>
    </div>
  </div>

  <!--大图-->
  <div class="top-3">
    <div class="img_1">
<!--      <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/login/5731485aN1134b4f0.png" />-->
    </div>
    <div id="sign">
      <div class="si_top">
        <p>
          <span>Don't transfer money casually. Beware of fraud</span>
        </p>
      </div>
      <div class="si_cen">
<!--        <h2 class="btn1">Qr code</h2>-->
<!--        <span>|</span>-->
        <h2 class="act btn1" style="width: 100%;margin-top: 10px">Welcome to Login</h2>
      </div>
      <div class="si_bom1 tab">
        <ul>
          <li class="top_1">
            <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/login/user_03.png" />
            <input type="text" placeholder="Username / phone" v-model="login_data.loginAccount"/>
          </li>
          <li>
            <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/login/user_06.png"/>
            <input type="password" placeholder="Password" v-model="login_data.password"/>
          </li>
          <li class="bri">
            <a href="">Forget Password?</a>
          </li>
          <li class="ent"><button class="btn2" @click.prevent="login"><a class="a">login</a></button></li>
        </ul>
      </div>
      <div class="si_out">
        <ul>
          <li>
            <a href="#">
              <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/login/qq.png" />
              <span>QQ</span>
            </a>
          </li>
          <li class="f4"> | </li>
          <li>
            <a href="">
              <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/login/weixin.png" />
              <span>Wechat</span>
            </a>
          </li>
        </ul>
        <h5 class="rig">
          <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/login/4de5019d2404d347897dee637895d02b_25.png" />
          <span><router-link to="/register">Register</router-link></span>
        </h5>
      </div>
    </div>
  </div>

</div>
</template>

<script src="./js/mall-login.js"></script>

<style src="../assets/css/login/login.css" scoped></style>
<style scoped></style>
