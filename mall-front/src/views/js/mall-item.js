import $ from "jquery"
import MallHeader from "@/components/MallHeader";
import MallSous from "@/components/MallSous";
export default {
    name: 'MallItem',
    data(){
        return {
            item_data:{},
            active_flag:{},
            active_data:{},
            count:1,
            activeName:"descript",
            commentList:{}
        }
    },
    components:{
        MallHeader,MallSous
    },
    methods:{
        initJS(){
            function Zoom(imgbox, hoverbox, l, t, x, y, h_w, h_h, showbox) {
                let moveX = x - l - (h_w / 2);
                //鼠标区域距离
                let moveY = y - t - (h_h / 2);
                //鼠标区域距离
                if(moveX < 0) {moveX = 0}
                if(moveY < 0) {moveY = 0}
                if(moveX > imgbox.width() - h_w) {moveX = imgbox.width() - h_w}
                if(moveY > imgbox.height() - h_h) {moveY = imgbox.height() - h_h}
                //判断鼠标使其不跑出图片框
                const zoomX = showbox.width() / imgbox.width();
                //求图片比例
                const zoomY = showbox.height() / imgbox.height();

                showbox.css({
                    left: -(moveX * zoomX),
                    top: -(moveY * zoomY)
                })
                hoverbox.css({
                    left: moveX,
                    top: moveY
                })
                //确定位置
            }

            function Zoomhover(imgbox, hoverbox, showbox) {
                const l = imgbox.offset().left;
                const t = imgbox.offset().top;
                const w = hoverbox.width();
                const h = hoverbox.height();
                let time;
                $(".probox img,.hoverbox").mouseover(function(e) {
                    const x = e.pageX;
                    const y = e.pageY;
                    $(".hoverbox,.showbox").show();
                    hoverbox.css("opacity", "0.3")
                    time = setTimeout(function() {
                        Zoom(imgbox, hoverbox, l, t, x, y, w, h, showbox)
                    }, 1)
                }).mousemove(function(e) {
                    const x = e.pageX;
                    const y = e.pageY;
                    time = setTimeout(function() {
                        Zoom(imgbox, hoverbox, l, t, x, y, w, h, showbox)
                    }, 1)
                }).mouseout(function() {
                    showbox.parent().hide()
                    hoverbox.hide();
                })
            }
            $(function() {
                Zoomhover($(".probox img"), $(".hoverbox"), $(".showbox img"));
                $(".box-lh-one ul li").hover(function() {
                    $('.img1').attr("src", this.item_data.info.skuDefaultImg);
                })
            })
        },
        getItemData(){
            let product_id = this.$route.params.product_id
            this.axios.get(`product/skuinfo/skuItem/${product_id}`)
                .then(response =>{
                    this.item_data = response.data.data
                    for (let attr of this.item_data.saleAttr) {
                        for (let i = 0; i < attr.attrValues.length; i++) {
                            if(attr.attrValues[i].skuIds.indexOf(this.item_data.info.skuId)!==-1){
                                this.$set(this.active_flag, attr.attrId, i)
                                this.$set(this.active_data, attr.attrId, attr.attrValues[i].skuIds.split(","))
                            }
                        }

                    }

                    this.axios.get("product/spucomment/list",{params:{spu_id:this.item_data.info.spuId}}).then(res => {
                        if(res.data.code === 0){
                            this.commentList = res.data.page.list
                        }else {
                            alert("comment fetch error")
                        }
                    }).catch(err => {
                        alert("comment fetch error")
                    })
                }
            )
        },
        changeSaleAttr(attrId, i, skuIds){
            let active_flag = this.active_flag
            active_flag[attrId] = i
            let active_data = this.active_data
            active_data[attrId] = skuIds.split(",")
            let skuIds_list = []
            for(let key in active_data){
                skuIds_list.push(active_data[key])
            }
            let res = skuIds_list.reduce((a, b) => a.filter(c => b.includes(c)))[0]
            this.$router.push({name: 'MallItem', params: {product_id: res}})
        },
        addToCart(){
            this.axios.get("auth/getLoginUser")
                .then(response => {
                    let code = response.data.code
                    if(code !== 0){
                        alert("Please login first")
                    }
                })
                .catch(err => {
                    alert("unexcepted error")
                })
            this.axios.post('cart/addCartItem',{skuId:this.item_data.info.skuId, num:this.count})
                .then(response => {
                    let cartItem = response.data.data
                    if(response.data.code === 0){
                        this.$router.push({
                            name: 'MallCartSuccess',
                            params: {
                                skuTitle: this.item_data.info.skuTitle,
                                count: this.count,
                                skuImg: this.item_data.info.skuDefaultImg,
                                skuId:this.item_data.info.skuId
                            }
                         })
                    }else {
                        alert("Add Cart Failed with unexpected error! Try Again")
                    }
                })
                .catch(error =>{
                    alert("Add Cart Failed with unexpected error! Try Again")
                })
        },
        searchByKeyword(keyword) {
            this.$router.push({name: 'MallSearch', query: {keyword:keyword}})
        },
    },
    mounted() {
        if(this.item_data.info === undefined){
            this.getItemData();
        }
        this.initJS();
    },
    watch: {
        '$route'(to, from) {
            this.getItemData();
        }
    },
}
