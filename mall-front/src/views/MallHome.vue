<template>
  <div>
    <header>
      <MallHeader @user="getUser"></MallHeader>
      <!--搜索导航-->
      <MallSous></MallSous>
      <!--轮播主体内容-->
      <div class="header_main">
        <div class="header_banner">
          <div class="header_main_left" align="left">
            <ul>
              <li class="header_li2" v-for="(category1,i) in category_level_first" @mouseenter="enter(i)" @mouseleave="leave(i)" :key="category1.catId">
                <a href="#" class="header_main_left_a"><b>{{category1.name}}</b></a>
                <div class="header_main_left_main" :style="active === i ? 'display:block;' : 'display:none;'" style="opacity: 1;" >
                  <ol class="header_ol">
                    <div style="display: inline-block" v-for="(category2,i) in category_data[category1.catId]" :key="category2.catId">
                      <a href="#" style="color: #111;" class="aaa">{{category2.name}}</a>
                      <li style="height: 60px;">
                        <router-link :to="{path:'/search',query: {catalog3Id: category3.id}}" style="color: #999;" v-for="(category3,i) in category2.catalog3List" :key="category3.id">{{category3.name}}</router-link>
<!--                        <a href="#" style="color: #999;" v-for="(category3,i) in category2.catalog3List" :key="category3.catId">{{category3.name}}</a>-->
                      </li>
                    </div>
                  </ol>
                </div>
              </li>
            </ul>

          </div>
          <div class="header_main_center">
            <div class="swiper-container swiper1">
              <div class="swiper-wrapper">
                <div class="swiper-slide" >
                  <a href="#"><img style="height: 620px" src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/xdu.jpg" /></a>
                </div>
              </div>
              <div class="swiper-pagination"></div>
              <div class="swiper-button-next swiper-button-white"></div>
              <div class="swiper-button-prev swiper-button-white"></div>
            </div>
            <div class="header_main_center_b">
<!--              <a href="#"><img src="@/assets/img/5a13bf0bNe1606e58.jpg" /></a>-->
            </div>
          </div>
          <div class="header_main_right">
            <div class="header_main_right_user">
              <div class="user_info">
                <div class="user_info_tou">
                  <a href="#"><img class="" style="width: 50px" :src="(user.header === null || user.header === '') ? 'https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/home/touxiang.png' : user.header"></a>
                </div>
                <div class="user_info_show">
                  <p class="" v-if="undefined !== this.user.username">
                    <span style="color: red">Hi, {{undefined === this.user.username ? "Login" : this.user.username}}</span>
                    <br>
                    Welcome to
                    <br>
                      XDU MALL
                  </p>
                  <p v-if="undefined === this.user.username">
                    Hi, guest <br> Login or Register
                  </p>
                </div>
              </div>
              <div class="user_info_hide">
                <router-link to="/space">My space</router-link>
                <a href="#">VIP</a>
              </div>
            </div>
            <div class="header_main_right_new">
              <div class="header_new">
                <div class="header_new_t">
                  <p class="active">
                    <a href="#">Promotion</a>
                  </p>
                  <p>
                    <a href="#">Notice</a>
                  </p>
                </div>
                <div class="header_new_connter">
                  <div class="header_new_connter_1">
                    <ul>
                      <li>
                        <a href="#">xxxxxx</a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
  </div>

</template>

<script src="./js/mall-home.js"></script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="../assets/css/home/GL.css" scoped></style>
<style src="../assets/css/home/swiper-3.4.2.min.css" scoped></style>
<style scoped>
</style>
