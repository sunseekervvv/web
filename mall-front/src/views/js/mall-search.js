import $ from "jquery";
import { mapState } from "vuex";
import MallHeader from "@/components/MallHeader";
import MallSous from "@/components/MallSous";
export default {
    name: "MallSearch",
    data(){
        return {
            search_result: [],
            keyword_input:"",
            sortIndex: 0,
            sort_str: ["Comprehensive","SaleCount","Price"],
            sort_desc: true,
            skuPriceFrom: "",
            skuPriceTo: "",
            hasStock: false,
            page_input:""
        }
    },
    components:{
        MallHeader,MallSous
    },
    methods: {
        initJS(){
            $('.tab_im img').hover(function(){
                const a = $(this).prop('src');
                const index = $(this).parents('li').index();
                $(this).parents('li').css('border','2px solid red').siblings('li').css('border','1px solid #ccc');
                $(this).parents('ul').prev().find('img').prop('src',a);
                $(this).parents('ul').siblings('.tab_JE').find('a').eq(index).css('display','block').siblings('a').css('display','none');
                $(this).parents('ul').siblings('.tab_R').find('span').eq(index).css('display','block').siblings('span').css('display','none')
            });

            $(".JD_ipone_one").hover(function(){
                $(this).children("div").css({display:"block"})
            },function(){
                $(this).children("div").css({display:"none"})
            });

            $("#tab>li").click(function() {
                const i = $(this).index();
                $("#container>div").hide().eq(i).show()
            });


            $(".rig_tab>div").hover(function(){
                const i = $(this).index();
                $(this).find('.ico').css({display:'block'}).stop(true).animate({top:"190px"},300)
            },function(){
                const i = $(this).index();
                $(this).find('.ico').css({display:'none'}).stop(true).animate({top:"230px"})
            });

            $('.header_main_left>ul>li').hover(function() {
                $(this).css({background: "#f0f0f0"}).find('.header_main_left_main').stop(true).fadeIn(300)
            }, function() {
                $(this).css({background: "#fff"}).find(".header_main_left_a").css({color: "#000"});
                $(this).find('.header_main_left_main').stop(true).fadeOut(100)
            });

        },
        initSort(){
            let sort = this.$route.query.sort
            if(sort !== undefined){
                let sort_name = sort.split("_")[0]
                let sort_direction = this.sort_desc ? "↓" : "↑";
                if(sort.split("_")[1] === "desc"){
                    this.sort_desc = true
                    sort_direction = "↓"
                }else {
                    this.sort_desc = false
                    sort_direction = "↑"
                }
                this.$set(this.sort_str, 0, "Comprehensive")
                this.$set(this.sort_str, 1, "SaleCount")
                this.$set(this.sort_str, 2, "Price")
                if(sort_name === "hotScore"){
                    this.$set(this.sort_str, 0, "Comprehensive"+sort_direction)
                    this.sortIndex = 0;
                }else if(sort_name === "saleCount"){
                    this.$set(this.sort_str, 1, "SaleCount"+sort_direction)
                    this.sortIndex = 1;
                }else if(sort_name === "skuPrice"){
                    this.$set(this.sort_str, 2, "Price"+sort_direction)
                    this.sortIndex = 2;
                }

            }

            let skuPrice = this.$route.query.skuPrice
            if(skuPrice !== undefined){
                this.skuPriceFrom = skuPrice.split("_")[0]
                this.skuPriceTo = skuPrice.split("_")[1]
            }
        },
        getSearchResult(){
            let params = this.$route.query
            this.axios.get(`search/get/result`,{params: params})
                .then(response =>{
                    this.search_result = response.data.data
                    this.page_input = this.search_result.pageNum
                }
            )
        },
        searchProducts(name, value){
            let params = this.getParams();
            params[name] = value
            this.$router.push({name: 'MallSearch', query: params})
        },
        searchProductsByBrand(id, name){
            let params = this.getParams();
            let oldValue = params["brandId"]
            if(oldValue !== undefined){
                if(oldValue.indexOf(id)!==-1) return;
                let mul_val = oldValue+ "__" + id;
                params["brandId"] = mul_val
            }else {
                params["brandId"] = id
            }
            this.$store.commit("addBrandId", {id,name})
            this.$router.push({name: 'MallSearch', query: params})
        },
        searchProductsByAttr(id, name,value,queryParam){
            let params = this.getParams();
            let oldValue = params["attrs"]
            if(oldValue !== undefined){
                if(oldValue.indexOf(value) !== -1) return;
                let mul_val = oldValue+ "__" + queryParam;
                params["attrs"] = mul_val
            }else {
                params["attrs"] = queryParam
            }
            this.$store.commit("addAttr", {id, name,value})
            this.$router.push({name: 'MallSearch', query: params})
        },
        searchByKeyword(keyword){
            this.searchProducts('keyword',keyword)
        },
        handlePage(pageNumTo){
            let params = this.getParams();
            params["pageNum"] = pageNumTo;
            this.$router.push({name: 'MallSearch', query: params})
        },
        searchByPrice(){
            let skuPriceFrom = Number(this.skuPriceFrom)
            let skuPriceTo = Number(this.skuPriceTo)
            if(isNaN(skuPriceFrom) || isNaN(skuPriceTo)){
                alert("请输入合法的Price区间")
                return;
            }
            if(this.skuPriceFrom !== "" && this.skuPriceTo !== ""){
                if(skuPriceFrom > skuPriceTo){
                    alert("请输入合法的Price区间")
                    return;
                }
            }
            let from = this.skuPriceFrom === 0 ? "" : this.skuPriceFrom+""
            let to = this.skuPriceTo === 0 ? "" : this.skuPriceTo+""
            let price_query = from + "_" + to
            this.searchProducts("skuPrice", price_query)

        },
        searchByStock(){
            this.hasStock = !this.hasStock;
            if(this.hasStock){
                this.searchProducts("hasStock","1")
            }else {
                let params = this.getParams();
                delete params.hasStock
                this.$router.push({name: 'MallSearch', query: params})
            }
        },
        changeSortIndex(i){
            if(i !== this.sortIndex){
                this.sort_desc = true
            }else {  //点自己
                this.sort_desc = !this.sort_desc;
            }
            let value;
            let sort_direction = this.sort_desc ? "_desc" : "_asc";
            if(i === 0){
                value = "hotScore"+sort_direction;
            }else if(i === 1){
                value = "saleCount"+sort_direction;
            }else if(i === 2){
                value = "skuPrice"+sort_direction;
            }
            this.searchProducts("sort",value)
        },
        getParams(){
            let params = {}
            if(location.href.indexOf("?")!==-1){
                let url_params = location.href.toString().split('?')[1]
                let entry_list = url_params.split("&")
                for (let entry of entry_list){
                    let key = entry.split("=")[0]
                    params[key] = entry.split("=")[1]
                }
            }
            return params;

        },
        delBrand(id){
            let params = this.getParams();
            let brand_query = params["brandId"]
            let del_str = "__" + id
            if(brand_query !== undefined){
                // 如果只有一个
                if(brand_query.toString() === id.toString()){
                    delete params["brandId"]
                }else {
                    // 如果是开头
                    if(brand_query.indexOf(del_str) === -1){
                        del_str = id+"__"
                    }
                    brand_query.replace(del_str, "")
                    params["brandId"] = brand_query
                }
                this.$store.commit("delBrandId", id)
                this.$router.push({name: 'MallSearch', query: params})
                this.$forceUpdate()
            }
        },
        delAttr(id, query_str){
            let params = this.getParams();
            let attr_query = params["attrs"]
            let del_str = "__" + query_str
            if(attr_query !== undefined){
                // 如果只有一个
                if(attr_query.toString() === query_str.toString()){
                    delete params["attrs"]
                }else {
                    // 如果是开头
                    if(attr_query.indexOf(del_str) === -1){
                        del_str = query_str+"__"
                    }
                    attr_query.replace(del_str, "")
                    params["attrs"] = attr_query
                }
                this.$store.commit("delAttr", id)
                this.$router.push({name: 'MallSearch', query: params})
                this.$forceUpdate()
            }
        }
    },
    mounted(){
        this.initJS();
        if(this.search_result.length === 0){
            this.getSearchResult();
            this.initSort();
        }
    },
    watch: {
        '$route'(to, from) {
            // this.$router.go(0);
            this.getSearchResult();
            this.initSort();
        }
    },
    computed: {
        ...mapState(["query_params"]),
        notHasBrandId(){
            return this.$route.query.brandId === undefined;
        },
        notSelectAttr(){
            if(this.$route.query.attrs === undefined){
                return this.search_result.attrs
            } else{
                return this.search_result.attrs.filter(attr =>{
                    return this.$route.query.attrs.indexOf(attr.attrId.toString()) === -1;
                })
            }
        }
    }
}
