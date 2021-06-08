<template>
<div>
  <MallHeader @user="getUser"></MallHeader>
  <MallSous></MallSous>
  <div id="big">
    <div id="bigLeft">
      <ul>
        <h5>Order</h5>
        <li :style="activeNav === 0? 'color:#E4393C;font-weight:800;cursor: pointer' : 'cursor: pointer'" @click="activeNav = 0">My orders</li>
      </ul>
      <ul>
        <h5>Settings</h5>
        <li :style="activeNav === 1? 'color:#E4393C;font-weight:800;cursor: pointer' : 'cursor: pointer'" style="cursor: pointer"  @click="activeNav = 1">My info</li>
        <li :style="activeNav === 2? 'color:#E4393C;font-weight:800;cursor: pointer' : 'cursor: pointer'" style="cursor: pointer"  @click="activeNav = 2">Address</li>
      </ul>
    </div>
    <div class="bigRight" v-if="activeNav === 0">
      <div class="allBig">
        <div class="allOrder">
          <ul class="allOrderTop">
            <li>
              <ol>
                <li @click="changeFilter(-1)" :style="activeFilter === -1 ? 'color: #E4393C;' :''">All</li>
                <li @click="changeFilter(0)" :style="activeFilter === 0 ? 'color: #E4393C;' :''">Wait pay</li>
                <li @click="changeFilter(1)" :style="activeFilter === 1 ? 'color: #E4393C;' :''">Wait send</li>
                <li @click="changeFilter(2)" :style="activeFilter === 2 ? 'color: #E4393C;' :''">Wait receive</li>
                <li @click="changeFilter(3)" :style="activeFilter === 3 ? 'color: #E4393C;' :''">Done</li>
                <li @click="changeFilter(4)" :style="activeFilter === 4 ? 'color: #E4393C;' :''">Timeout</li>
              </ol>
            </li>
          </ul>
        </div>
        <div class="details">
          <ul class="detailsUl">
            <div class="detailsUlDiv">
              <li class="detailsLi" style="margin-left: -20px;">Order info</li>
              <div style="clear:both;"></div>
            </div>

            <li class="detailsLi1" style="margin-left: 30px;">Consignee</li>
            <li class="detailsLi1">Cost</li>
            <li class="detailsLi1 detailsLiok">Status</li>
            <li class="detailsLi1">Options</li>
            <div style="clear:both;"></div>
          </ul>
        </div>
        <table class="table" v-for="order in orderList" v-if="order.items.length > 0">
          <div v-for="item in order.items" >
            <tr>
              <td colspan="7" style="background:#F7F7F7" >
                <span style="color:#AAAAAA">{{ order.createTime }}</span>
                <span><ruby style="color:#AAAAAA">OrderSn:</ruby>   {{ order.orderSn }}</span>
                <span>{{ item.seller }}<i class="table_i"></i></span>
                <i class="table_i5 isShow"></i>
              </td>
            </tr>
            <tr class="tr">
              <td colspan="3">
                <img :src="item.skuPic" alt="" style="cursor: pointer" class="img" @click.prevent="toDetail(item.skuId)">
                <div style="padding-top: 30px">
                  <span>{{item.skuName}} &nbsp; ×{{item.skuQuantity}}</span>
                </div>
                <div style="clear:both"></div>
              </td>
              <td>{{ order.receiverName }}<i><i class="table_i1"></i></i></td>
              <td style="padding-left:10px;color:#AAAAB1;">
                <p style="margin-bottom:5px;">Total ￥{{item.realAmount}}</p>
                <hr style="width:90%;">
                <p>Alipay</p>
              </td>
              <td v-if="item.status === 0">
                <ul>
                  <li style="color:#71B247;">Wait Pay</li>
                </ul>
              </td>
              <td v-if="item.status === 1">
                <ul>
                  <li style="color:#71B247;">Wait send</li>
                </ul>
              </td>
              <td v-if="item.status === 2">
                <ul>
                  <li style="color:#71B247;">Wait receive</li>
                  <li style="margin:4px 0;" class="hide"><i class="table_i2"></i>Trace<i class="table_i3"></i></li>
                </ul>
              </td>
              <td v-if="item.status === 3">
                <ul>
                  <li style="color:#71B247;">Done</li>
                </ul>
              </td>
              <td v-if="item.status === 4 || item.status === 5">
                <ul>
                  <li style="color:#71B247;">Not pay</li>
                  <li style="color:#71B247;">Timeout</li>
                </ul>
              </td>
              <td v-if="item.status === 0">
                <p>To pay</p>
                <p @click.prevent="deleteOrder(item.id)">Cancel</p>
              </td>
              <td v-if="item.status === 1">
                <p style="margin:4px 0; cursor: pointer" @click.prevent="deleteOrder(item.id)">Cancel</p>
                <p style="cursor: pointer">Urge</p>
              </td>
              <td v-if="item.status === 2">
                <button style="cursor: pointer" @click.prevent="confirmOrder(item.id)">Confirm</button>
                <p style="margin:4px 0; cursor: pointer" @click.prevent="deleteOrder(item.id)">Cancel</p>
                <p style="cursor: pointer">Urge</p>
              </td>
              <td v-if="item.status === 3">
                <router-link :to="{name:'MallComment',params:{product_id: item.skuId}}" >
                  <button style="cursor: pointer">To comment</button>
                </router-link>
                <p style="cursor: pointer" @click.prevent="deleteOrder(item.id)">Cancel</p>
              </td>
              <td v-if="item.status === 4  || item.status === 5">
                <p style="cursor: pointer" @click.prevent="deleteOrder(item.id)">Delete</p>
              </td>
            </tr>
          </div>
        </table>

        <div class="order_btm">
          <div>
            <button style="margin-right: 10px;cursor: pointer">Back</button>
            <span>1</span>
            <button style="margin-left: 10px;cursor: pointer">Next</button>
          </div>
        </div>
        <div style="clear:both;"></div>
      </div>
    </div>
<!--    <div style="clear:both;"></div>-->
    <div class="bigRight" style="  background: #f5f5f5;margin-top: 30px" v-if="activeNav === 1">
<!--      <div style="color: #E4393C;font-weight: 800;margin-top: 10px;margin-left: 30px">{{user.username}}</div>-->

      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 30px;width: 40px">Header:  </label>
        <img style="margin-left: 10px;width: 50px;" :src="headerImg" alt="">
        <single-upload @input="getUpload" style="display: inline-block;margin-left: 30px;"></single-upload>
      </div>

      <div style="margin-top: 20px;font-size: 12px;">


      </div>

      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 30px;width: 40px">Gender:  </label>
        <el-radio-group v-model="gender" style="margin-left: 10px">
          <el-radio :label="0">Male</el-radio>
          <el-radio :label="1">FeMale</el-radio>
        </el-radio-group>
      </div>
      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 30px;width: 40px">Birth:  </label>
          <el-date-picker style="margin-left: 22px;"
              v-model="birthDay"
              type="date"
              placeholder="Choose Date">
          </el-date-picker>
      </div>
      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 30px;width: 40px">Job:  </label>
        <el-input v-model="job" placeholder="Please input" style="width: 220px;margin-left: 30px"></el-input>
      </div>
      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 30px;width: 40px">sign:  </label>
        <el-input
            style="width: 220px;margin-left: 25px"
            type="textarea"
            placeholder="Please input"
            v-model="sign"
            maxlength="30"
            show-word-limit
        >
        </el-input>
      </div>

      <el-button style="margin-left: 230px;margin-top: 30px;margin-bottom: 30px" type="danger" @click.prevent="saveUser()">Save</el-button>
    </div>

    <div class="bigRight" style="  background: #f5f5f5;margin-top: 30px" v-if="activeNav === 2">

      <el-card class="box-card" v-for="address in addresses" style="display: inline-block" :key="address.id">
        <div slot="header" class="clearfix">
          <span>{{address.name}}</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click.prevent="deleteAddress(address.id)">Delete</el-button>
        </div>
        <div class="text item">
          <span>Receiver: </span>
          <el-input v-model="address.name" placeholder="Please input" style="width: 320px;margin-left: 50px"></el-input>
        </div>
        <div class="text item">
          <span>Phone: </span>
          <el-input v-model="address.phone" placeholder="Please input" style="width: 320px;margin-left: 65px"></el-input>
        </div>
        <div class="text item">
          <span>Province: </span>
          <el-input v-model="address.province" placeholder="Please input" style="width: 320px;margin-left: 50px"></el-input>
        </div>
        <div class="text item">
          <span>City: </span>
          <el-input v-model="address.city" placeholder="Please input" style="width: 320px;margin-left: 80px"></el-input>
        </div>
        <div class="text item">
          <span>DetailAddress: </span>
          <el-input v-model="address.detailAddress" placeholder="Please input" style="width: 320px;margin-left: 10px"></el-input>
        </div>
        <el-button style="margin-left: 360px;margin-top: 10px;" type="danger" @click.prevent="updateAddress(address)">Save</el-button>
      </el-card>
      <h3 style="color: #E4393C;margin-left: 30px;margin-top: 30px;margin-bottom: 20px">NEW</h3>
      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 50px;width: 40px">Receiver:  </label>
        <el-input v-model="receiver" placeholder="Please input" style="width: 320px;margin-left: 65px"></el-input>
      </div>
      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 50px;width: 40px">Phone:  </label>
        <el-input v-model="phone" placeholder="Please input" style="width: 320px;margin-left: 75px"></el-input>
      </div>
      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 50px;width: 40px">Province:  </label>
        <el-input v-model="province" placeholder="Please input" style="width: 320px;margin-left: 60px"></el-input>
      </div>
      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 50px;width: 40px">City:  </label>
        <el-input v-model="city" placeholder="Please input" style="width: 320px;margin-left: 90px"></el-input>
      </div>
      <div style="margin-top: 20px;font-size: 12px;">
        <label style="margin-left: 50px;width: 40px">DetailAddress:  </label>
        <el-input v-model="detailAddress" placeholder="Please input" style="width: 320px;margin-left: 30px"></el-input>
      </div>
      <el-button style="margin-left: 410px;margin-top: 10px;margin-bottom: 50px" type="danger" @click.prevent="saveAddress()">Save</el-button>
    </div>
  </div>
</div>
</template>


<script src="./js/mall-space.js"></script>
<style src="../assets/css/orderlist/index.css" scoped></style>
<style scoped></style>
