import $ from "jquery";
import Swiper from "swiper"
import MallHeader from "@/components/MallHeader";
import MallSous from "@/components/MallSous";
export default {
    name: 'MallHome',
    props: {
        msg: String
    },
    components:{
        MallHeader,MallSous
    },
    data(){
        return {
            category_level_first: [],
            category_data: [],
            active:-1,
            user:{}
        }
    },
    methods:{
        enter(i){
            this.active = i;
        },
        leave(i){
            this.active = -1;
        },
        searchByKeyword(keyword) {
            this.$router.push({name: 'MallSearch', query: {keyword:keyword}})
        },
        initCategory(){
            this.axios.get('product/category/list/first')
                 .then(response => {
                    this.category_level_first = response.data.data
                    let tmp = []
                    for (let i = 0; i < this.category_level_first.length; i++) {
                        tmp.push(false)
                    }
                    this.active = tmp
                }
            )
            this.axios.get('product/category/list/other')
                .then(response => {
                    this.category_data = response.data
                }
            )
        },
        initJS(){
            $(".header_ol a").hover(function() {
                $(this).css({color: "#c81623"})
            }, function() {
                $(this).css({color: "#999"})
                $(".aaa").css({color: "#111"})
            })
            //轮播图
            const swiper1 = new Swiper(".swiper1", {
                loop: true,
                autoplay: 2000,
                effect: 'fade',
                fade: {
                    crossFade: false,
                },
                pagination: ".swiper-pagination",
                paginationClickable: true,
                prevButton: '.swiper-button-prev',
                nextButton: '.swiper-button-next',
                autoplayDisableOnInteraction: false,
            });
            //货品分类
            $('.header_main_left>ul>li').hover(function() {
                $(this).css({background: "#989898"}).find('.header_main_left_main').stop(true).fadeIn(300)
            }, function() {
                $(this).css({background: "#6e6568"}).find(".header_main_left_a").css({color: "#fff"})
                $(this).find('.header_main_left_main').stop(true).fadeOut(100)
            })
            //促销公告选项卡
            $(".header_new_t p").hover(function() {
                const i = $(this).index();
                $(".header_new_t p").removeClass("active").eq(i).addClass("active")
                $(".header_new_connter_1").hide().eq(i).show()
            })

        },
        async getUser(user){
            this.user = user
        }
    },
    mounted() {
        this.initJS();
        this.initCategory();
        // if(this.$session.get("user") !== undefined){
        //     this.user = this.$session.get("user")
        // }
    }
}
