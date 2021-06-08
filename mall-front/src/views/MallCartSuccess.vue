<template>
<div>
  <MallHeader></MallHeader>
  <!--搜索导航-->
  <MallSous></MallSous>
  <div class="main">
    <div class="success-wrap">
      <div class="w" id="result">
        <div class="m succeed-box">
          <div class="mc success-cont">
            <div class="success-lcol">
              <div class="success-top"><b class="succ-icon"></b>
                <h3 class="ftx-02">Add to shop cart success !</h3></div>
              <div class="p-item">
                <div class="p-img">
                  <a href="/javascript:;" target="_blank"><img style="height: 60px;width:60px;" :src="this.cartItem.skuImg"></a>
                </div>
                <div class="p-info">
                  <div class="p-name">
                    <a>{{this.cartItem.skuTitle}}</a>
                  </div>
                  <div class="p-extra"> <span class="txt">  Num：{{this.cartItem.count}}</span></div>
                </div>
                <div class="clr"></div>
              </div>
            </div>
            <div class="success-btns success-btns-new">
              <div class="success-ad">
                <a href="/#none"></a>
              </div>
              <div class="clr"></div>
              <div class="bg_shop">
                <router-link :to="{name:'MallItem',params:{product_id: cartItem.skuId}}" class="btn-tobback" >Product info</router-link>
                <router-link to="/cart" class="btn-addtocart" id="GotoShoppingCart">To shop cart</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>
</template>

<script src="./js/mall-cart-success.js"></script>
<style src="../assets/css/cart/success.css" scoped></style>
<style scoped></style>
