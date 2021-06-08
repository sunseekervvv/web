<template>
<div>
  <MallHeader></MallHeader>
  <!--搜索导航-->
  <MallSous></MallSous>
  <div class="One_BdyShop">
    <div class="OneBdy_box">
      <div class="One_tabTop">
        <div class="One_Topleft">
          <span>All products</span>

        </div>

      </div>
      <div class="One_ShopTop">
        <ul>
          <li><input type="checkbox" class="allCheck" v-model="select_all" @click="selectAll()">Select All</li>
          <li>Title</li>
          <li>Per price</li>
          <li>Count</li>
          <li>Total price</li>
          <li>Option</li>
        </ul>
      </div>

      <div class="One_ShopCon">
        <ul>
          <li v-for="(item,i) in cartList.items">
            <div></div>
            <div>
              <ol>
                <li><input type="checkbox" class="check" v-model="item.check" @click="checkCart(item.skuId, !item.check, i)"/></li>
                <li>
                  <dt><img :src="item.image" alt=""></dt>
                  <dd>
                    <p>
                      <span>{{item.title}}</span><br>
                      <span v-for="attr in item.skuAttrValues">{{attr}}<br></span>
                    </p>
                  </dd>
                </li>
                <li>
                  <p class="dj" >￥{{item.price}}</p>
                </li>
                <li style="margin-left: 40px;">
                  <p>
                    <span @click.prevent="item.count = (item.count-1>=1)?item.count-1:item.count;countItem(item.skuId,item.count)">-</span>
                    <span><b>{{item.count}}</b></span>
                    <span @click.prevent="item.count++;countItem(item.skuId,item.count)">+</span>
                  </p>
                </li>
                <li style="font-weight:bold;margin-left: 60px">
                  <p class="zj">￥{{item.totalPrice}}</p>
                </li>
                <li style="margin-left: 80px;">
                  <p @click="deleteItem(item.skuId)">Delete</p>
                </li>
              </ol>
            </div>
          </li>

        </ul>
      </div>
      <div class="One_ShopFootBuy fix1">
        <div>
          <ul>
            <li @click="deleteAll()">Delete Select</li>
          </ul>
        </div>
        <div>
<!--          <font style="color:#e64346;font-weight:bold;" class="sumNum"> </font>&nbsp;-->

          <ul>
            <li><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/cart/buyNumleft.png" alt=""></li>
            <li><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/cart/buyNumright.png" alt=""></li>
          </ul>
        </div>
        <div style="margin-left: 860px">
          <ol>
            <li>Total: <span style="color:#e64346;font-weight:bold;font-size:16px;" class="fnt">￥{{cartList.totalAmount}}</span> </li>
          </ol>
        </div>
          <div><button @click.prevent="toTrade()" type="button">To order</button></div>
      </div>
    </div>
  </div>


</div>
</template>

<script src="./js/mall-cart.js"></script>

<style src="../assets/css/cart/One_bdyStyle.css" scoped></style>
<style src="../assets/css/cart/index.css" scoped></style>
<style src="../assets/css/cart/One_mainFoot.css" scoped></style>
<style src="../assets/css/cart/font-awesome-4.7.0/css/font-awesome.css" scoped></style>
<style scoped></style>
