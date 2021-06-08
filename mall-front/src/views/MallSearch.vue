<template>
  <div>
    <MallHeader></MallHeader>
    <!--搜索导航-->
    <MallSous></MallSous>


    <!--手机-->
    <div class="mall_nav">
      <div class="mall_nav_bar">
        <div class="mall_nav_one a">
          <a href="#">Filter Nav</a>
        </div>
        <i><img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/search/right-@1x.png" alt=""></i>
        <div class="mall_nav_one c">
          <a href="#" @click.prevent="delBrand(entry[0])" v-for="(entry,i) in query_params.brandId"><span>品牌:</span><span>{{entry[1]}}</span> ×</a>
          <a href="#" @click.prevent="delAttr(entry[0],entry[0]+'_'+entry[1][1])" v-for="(entry,i) in query_params.attrs"><span>{{entry[1][0]}}</span>: <span>{{entry[1][1]}}</span> ×</a>

        </div>
      </div>
    </div>

    <!--商品筛选和排序-->
    <div class="mall_banner w">
      <div class="mall_nav">
        <div class="mall_selector">
          <!--手机商品筛选-->
          <div class="title">
            <h3><b>Filter</b><em>Attributions</em></h3>
<!--            <div class="st-ext">共&nbsp;<span>10135</span>个商品 </div>-->
          </div>
          <div class="mall_nav_logo">
            <!--品牌-->
            <div class="mall_nav_wrap" v-if="notHasBrandId">
              <div class="sl_key">
                <span><b>Brand：</b></span>
              </div>
              <div class="sl_value">
                <div class="sl_value_logo">
                  <ul>
                    <li v-for="(brand,i) in search_result.brands" style="border: 0">
                      <a href="#" @click.prevent="searchProductsByBrand(brand.brandId, brand.brandName)">
                        <img :src="brand.brandImg" alt="">
                        <div>{{ brand.brandName }}</div>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>

            <!--其他属性-->
            <div class="mall_pre" v-for="(attr,i) in notSelectAttr" :key="attr.attrId">
              <div class="sl_key"><span><b>{{attr.attrName}}</b></span></div>
              <div class="sl_value">
                <ul>
                  <li v-for="(val,i) in attr.attrValue"><a href="#" @click.prevent="searchProductsByAttr(attr.attrId, attr.attrName,val,attr.attrId+'_'+val)">{{ val }}</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <!--排序-->
        <div class="mall_banner_main">
          <!--商品精选-->
          <div class="mall_con_left">
            <div class="mall_con_left_bar">
              <div class="mall_con_one">
                <div class="mt">
                  <h3>Product Recommend</h3>
                  <span>ad</span>
                </div>
                <div class="mc">
                </div>
              </div>
            </div>
          </div>
          <!--综合排序-->
          <div class="mall_con_right">
            <div class="filter">
              <!--综合排序-->
              <div class="filter_top">
                <div class="filter_top_left">
                  <a href="#" @click.prevent="changeSortIndex(0)" :style="sortIndex === 0 ? 'color:#FFF;border-color:#e4393c;background:#e4393c':'color:#333;border-color:#CCC;background:#FFF'">{{ sort_str[0] }}</a>
                  <a href="#" @click.prevent="changeSortIndex(1)" :style="sortIndex === 1 ? 'color:#FFF;border-color:#e4393c;background:#e4393c':'color:#333;border-color:#CCC;background:#FFF'">{{ sort_str[1] }}</a>
                  <a href="#" @click.prevent="changeSortIndex(2)" :style="sortIndex === 2 ? 'color:#FFF;border-color:#e4393c;background:#e4393c':'color:#333;border-color:#CCC;background:#FFF'">{{ sort_str[2] }}</a>
                  <a href="#" @click.prevent="searchByStock()" :style="hasStock ? 'color:#FFF;border-color:#e4393c;background:#e4393c':'color:#333;border-color:#CCC;background:#FFF'">HasStock</a>
                  &nbsp;&nbsp;
                  <input type="text" style="width: 100px;margin-left: 30px" v-model="skuPriceFrom">
                  -
                  <input type="text" style="width: 100px" v-model="skuPriceTo">  <button @click.prevent="searchByPrice()">Confirm</button>
                </div>
              </div>
              <!--排序内容-->
              <div class="rig_tab">
                <div v-for="(product, i) in search_result.products">
                  <p class="da">
                    <router-link :to="{name:'MallItem',params:{product_id: product.skuId}}" >
                      <img :src="product.skuImg" alt="">
                    </router-link>
                  </p>
<!--                  <ul class="tab_im">-->
<!--                    <li>-->
<!--                      <a href="#">-->
<!--                        <img :src="product.skuImg" alt="">-->
<!--                      </a>-->
<!--                    </li>-->
<!--                  </ul>-->
                  <p class="tab_R">
                    <span>¥{{product.skuPrice}}</span>
                  </p>
                  <p class="tab_JE">
                    <a href="#" :title="product.skuTitle" v-html="product.skuTitle"></a>
                  </p>
<!--                  <p class="tab_PI">已有<span>11万+</span>热门评价-->
<!--                    <a href="#">二手有售</a>-->
<!--                  </p>-->
                  <p class="tab_CP"><a href="#" title="XDU MALL Shop">{{product.seller}}</a>
<!--                    <a href='#' title="联系供应商进行咨询">-->
<!--                      <img src="https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/search/img/xcxc.png">-->
<!--                    </a>-->
                  </p>
<!--                  <div class="tab_FO">-->
<!--                    <div class="FO_one">-->
<!--                      <p>XDU-->
<!--                        <span>谷粒商城自营,品质保证</span>-->
<!--                      </p>-->
<!--                      <p>Recommend-->
<!--                        <span>该商品参加满赠活动</span>-->
<!--                      </p>-->
<!--                    </div>-->
<!--                  </div>-->
                </div>
              </div>
              <!--分页-->
              <div class="filter_page">
                <div class="page_wrap">
                  <span class="page_span1">
                      <a href="#" @click.prevent="handlePage(search_result.pageNum-1)" v-if="search_result.pageNum>1">< Pre</a>
                      <a href="#" @click.prevent="handlePage(pageNum)" :style="pageNum === search_result.pageNum ? 'border: 0;color:#ee2222;background: #fff' : ''" v-for="(pageNum,i) in search_result.pageNavs ">{{ pageNum }}</a>
                      <a href="#" @click.prevent="handlePage(search_result.pageNum+1)" v-if="search_result.pageNum<search_result.totalPages">Next ></a>
                  </span>
                  <span class="page_span2">
                      <em>Total <b>{{ search_result.totalPages }}</b> Pages&nbsp;&nbsp;To</em>
                      <input type="number" value="1" v-model="page_input">
                      <em>Page</em>
                      <a href="#" @click.prevent="handlePage(page_input)">Confirm</a>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script src="./js/mall-search.js">

</script>
<style src="../assets/css/search/index.css" scoped></style>
<style src="../assets/css/home/iconfont.css" scoped></style>
<style scoped>
</style>
