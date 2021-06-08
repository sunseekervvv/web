import axios from "axios";
import MallHeader from "@/components/MallHeader";
import MallSous from "@/components/MallSous";
export default {
    name: "MallCartSuccess",
    components:{
        MallHeader,MallSous
    },
    data(){
        return{
            cartItem:{
                skuTitle: "",
                count: "",
                skuImg: "",
                skuId:""
            }
        }
    },
    methods:{
        searchByKeyword(keyword) {
            this.$router.push({name: 'MallSearch', query: {keyword:keyword}})
        },
    },
    mounted() {
        if(this.$route.params.skuTitle !== undefined){
            this.cartItem.skuTitle = this.$route.params.skuTitle
            this.cartItem.count = this.$route.params.count
            this.cartItem.skuImg = this.$route.params.skuImg
            this.cartItem.skuId = this.$route.params.skuId
        }else {
            alert("Illegal access! Redirect to home")
            this.$router.push("/")
        }
    }
}
