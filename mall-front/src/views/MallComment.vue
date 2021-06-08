<template>
  <div>
    <MallHeader  @user="getUser"></MallHeader>
    <!--搜索导航-->
    <MallSous></MallSous>

    <div class="crumb-wrap">
      <div class="w">

        <div class="contact">
          <ul class="contact-ul">
            <li>

              <span class="contact-sp">
								<span class="contact-sp1">
								XDU
							</span>
							<span class="contact-sp2">
								{{ item_data.info.seller }}
							    </span>
							</span>
            </li>
          </ul>
        </div>

      </div>
    </div>
    <div class="Shop">
      <div class="box">
        <div class="box-one ">
          <div class="boxx">
            <div class="imgbox">
              <div class="probox">
                <img class="img1" alt="" :src="item_data.info.skuDefaultImg">
                <div class="hoverbox"></div>
              </div>
              <div class="showbox">
                <img class="img1" alt="" :src="item_data.info.skuDefaultImg">
              </div>
            </div>

            <div class="box-lh">
              <div class="box-lh-one">
                <ul>
                  <li v-for="image in item_data.images"><img :src="image.imgUrl" /></li>
                </ul>
              </div>
              <div id="left">
                <
              </div>
              <div id="right">
                >
              </div>

            </div>

          </div>
          <div class="box-two">
            <div class="box-name">{{item_data.info.skuTitle}}</div>
            <div class="box-hide">{{item_data.info.skuSubtitle}}</div>
            <div class="box-yuyue">
              <div class="yuyue-one">
                <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/item/7270ffc3baecdd448958f9f5e69cf60f.png" alt="" /> We need your comment
              </div>
            </div>
            <div class="box-summary clear">
              <ul>
                <li>Price</li>
                <li>
                  <span>￥</span>
                  <span>{{ item_data.info.price }}</span>
                </li>
                <li>
                </li>
              </ul>
            </div>
            <br>
            <el-card class="box-card" shadow="never">
              <div class="block">
                <el-rate v-model="star"></el-rate>
              </div>
              <br>
              <el-input style="height: 160px" :rows="7" type="textarea" placeholder="Please Input your comment" v-model="comment_data" maxlength="30" show-word-limit></el-input>
              <br><br>
              <el-button type="danger" style="float: right" @click.prevent="submitComment">Submit</el-button>
            </el-card>
          </div>

        </div>
      </div>
      <el-table
          :data="commentList"
          style="width: 100%" fit>
        <el-table-column
            label="User"
            width="180">
          <template slot-scope="scope">
            <img :src="scope.row.memberIcon" alt="" style="height: 40px">
            <br>
            <span>{{ scope.row.memberNickName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Comment" width="900">
          <template slot-scope="scope">
            <!--                <el-card class="box-card" shadow="never" style="height: auto">-->
            <p style="color: #e4393c"><b>{{scope.row.spuName}}</b></p>
            <el-rate v-model="scope.row.star" disabled style="margin-top: 10px"></el-rate>
            <p style="margin-top: 10px">{{scope.row.content}}</p>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div>

    </div>
  </div>
</template>

<script src="./js/mall-comment.js"></script>
<style src="../assets/css/item/shop.css" scoped></style>
<style src="../assets/css/item/header.css" scoped></style>
<style src="../assets/css/item/main.css" scoped></style>
<style src="../assets/css/item/SHOUhou.css" scoped></style>
<style scoped>

</style>
