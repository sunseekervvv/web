<template>
<div>

  <MallHeader></MallHeader>
  <!--logo图片-->
  <div class="top-1">
    <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/logo.jpg" class="img1" /><span>Order page</span>
    <!--右侧绿色-->
    <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/order/08.png" class="img2" />
  </div>

  <!--主体部分-->
  <div class="section">
    <!--收货人信息-->
    <div class="top-2">
      <span>Consignee Information</span>
      <span>New receiving address</span>
    </div>

    <!--地址-->
    <div class="top-3" v-for="address in confirmData.memberAddressVos">
      <p style="cursor: pointer" @click="selectAddress(address.id)" :style="address.id === activeAddressId? 'border:2px solid red;': 'border:2px solid #E3E4E5'">{{address.name}}</p>
      <span>{{ address.province }} {{address.city}} {{address.detailAddress}} {{ address.phone }}</span>
    </div>
    <div class="hh1"/>
    <!--谷粒学院自提-->


    <!--支付方式-->
    <h4 class="h4">Payment Method</h4>

    <div class="top-6">
<!--      <p><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/wechat.png" alt="" style="height: 20px;margin-bottom: 3px;margin-left: -15px;margin-right: 5px">Wechat</p>-->
      <p style="cursor: pointer"><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/alipay.png" alt="" style="height: 20px;margin-left: -25px;margin-right: -50px">AliPay</p>
    </div>
    <div class="hh1"></div>
    <!--送货清单-->
    <h4 class="h4" style="margin-top: 5px;">Order List</h4>
    <div class="top_1">
      <div class="to_right" v-for="item in confirmData.items">
        <h5>SHOP: XDU MALL</h5>
        <!--图片-->
        <div class="yun1">
          <img :src="item.image" class="yun"/>
          <div class="mi">
            <p>{{ item.title }}</p>
            <p>
              <span style="color: red;">￥{{item.price}}</span>
              <span style="margin-left: 10px"> x{{item.count}} </span>
              <span style="margin-left: 10px">Has stock</span>
            </p>
            <p class="tui-1"><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/order/i_07.png" />No reason return within 7 days</p>
          </div>
        </div>

      </div>

    </div>
    <div class="bto">
      <div class="hh2"></div>
    </div>
    <div class="xia">
      <div class="qian">
        <p class="qian_y">
          <span style="color:red;">{{ confirmData.count }} Products</span>
        </p>
        <br>
        <p class="qian_y">
          <span>Total Price：</span>
          <span class="rmb">￥{{confirmData.total}}</span>
        </p>
        <p class="qian_y">
          <span>Freight： </span>
          <span class="rmb"> &nbsp ￥0.00</span>
        </p>

      </div>

      <div class="yfze">
        <p class="yfze_a"><span class="z">Total：</span><span class="hq">￥{{confirmData.payPrice}}</span></p>
        <p class="yfze_b">Send to： {{ activeAddress.province }} {{activeAddress.city}} {{activeAddress.detailAddress}}</p>
        <p class="yfze_b">Consignee：{{ activeAddress.name }}  {{ activeAddress.phone }} </p>

      </div>
      <button @click="submit()" class="tijiao">Submit</button>
    </div>
  </div>

</div>
</template>

<script src="./js/mall-order.js"></script>


<style src="../assets/css/order/mall.css" scoped></style>
<!--<style src="../assets/css/order/index.css" scoped></style>-->
<style scoped>

</style>
