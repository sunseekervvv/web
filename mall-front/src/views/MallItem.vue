<template>
  <div>
    <MallHeader></MallHeader>
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
                <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/item/7270ffc3baecdd448958f9f5e69cf60f.png" alt="" /> Buy immediately
              </div>
              <div class="yuyue-two">
                <ul>
                  <li>
                    <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/item/f64963b63d6e5849977ddd6afddc1db5.png" />
                    <span>{{item_data.info.saleCount}}</span> sells
                  </li>
                </ul>
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
            <div class="box-wrap clear">
              <div class="box-wrap-one clear">
                <ul>
                  <li>Additional</li>
                  <li><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/item/90a6fa41d0d46b4fb0ff6907ca17c478.png" /></li>
                </ul>
              </div>
            </div>

            <div class="box-stock">
              <ul class="box-ul">
                <li>Stock</li>
                <li>
                  <span>{{item_data.hasStock?"HasStock":"Out of stock"}}</span>
                </li>
              </ul>
            </div>
            <div class="box-attr-3" style="margin-top: 10px;padding-top: 10px">
              <div class="box-attr-2 clear"  v-for="attr in item_data.saleAttr">
                <dl>
                  <dt>{{ attr.attrName }}</dt>
                  <dd v-for="(val,i) in attr.attrValues" :style="i === active_flag[attr.attrId]? 'border: solid 1px red;':'border: solid 1px #CCCCCC;'">
                    <a href="#" @click.prevent="changeSaleAttr(attr.attrId,i,val.skuIds)">{{ val.attrValue }}</a>
                  </dd>
                </dl>
              </div>
            </div>

            <div class="box-btns clear" style="margin-top: 95px">
              <div class="box-btns-one">
                <input type="text" v-model="count" />
                <div class="box-btns-one1">
                  <div><button id="jia" @click="count++">+</button></div>
                  <div><button id="jian" @click="count = (count-1>=1)?count-1:count">-</button>
                  </div>
                </div>
              </div>
              <div class="box-btns-two" @click.prevent="addToCart()">
                <a href="#">
                  Add to cart
                </a>
              </div>
              <div class="box-btns-three">
                <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/item/e4ed3606843f664591ff1f68f7fda12d.png" alt="" /> Share
              </div>
            </div>

          </div>

        </div>
      </div>
      <el-tabs type="card" style="text-align: center" v-model="activeName">
        <el-tab-pane label="Description" name="descript">
          <img :src="image" alt="" v-for="image in item_data.desc.decript.split(',')">
        </el-tab-pane>
        <el-tab-pane label="Attributions" name="attrs">
          <div class="guiGebox">
            <div class="guiGe" v-for="group in item_data.groupAttrs">
              <h3>{{group.groupName}}</h3>
              <dl>
                <div v-for="attr in group.attrs">
                  <dt>{{attr.attrName}}</dt>
                  <dd>{{attr.attrValue}}</dd>
                </div>
              </dl>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="Comments" name="comments">
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
                <p style="color: #e4393c"><b>{{scope.row.spuName}}</b></p>
                <el-rate v-model="scope.row.star" disabled style="margin-top: 10px"></el-rate>
                <p style="margin-top: 10px">{{scope.row.content}}</p>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script src="./js/mall-item.js"></script>
<style src="../assets/css/item/shop.css" scoped></style>
<style src="../assets/css/item/header.css" scoped></style>
<style src="../assets/css/item/main.css" scoped></style>
<style src="../assets/css/item/SHOUhou.css" scoped></style>
<style scoped>

</style>
