import $ from "jquery"
import MallHeader from "@/components/MallHeader";
import MallSous from "@/components/MallSous";
export default {
    name: 'MallComment',
    data(){
        return {
            item_data:{},
            user:{},
            star:5,
            comment_data:"",
            commentList:[]
        }
    },
    components:{
        MallHeader,MallSous
    },
    methods:{
        getItemData(){
            let product_id = this.$route.params.product_id
            this.axios.get(`product/skuinfo/skuItem/${product_id}`)
                .then(response =>{
                    this.item_data = response.data.data
                    this.axios.get("product/spucomment/list",{params:{spu_id:this.item_data.info.spuId}}).then(res => {
                        if(res.data.code === 0){
                            this.commentList = res.data.page.list
                        }else {
                            alert("comment fetch error")
                        }
                    }).catch(err => {
                        alert("comment fetch error")
                    })
                })
        },
        searchByKeyword(keyword) {
            this.$router.push({name: 'MallSearch', query: {keyword:keyword}})
        },
        async getUser(user){
            this.user = user
        },
        submitComment(){
            let spuComment = {
                skuId: this.item_data.info.skuId,
                spuId: this.item_data.info.spuId,
                spuName: this.item_data.info.skuName,
                memberNickName: this.user.username,
                star: this.star,
                content: this.comment_data,
                memberIcon: this.user.header
            }
            this.axios.post('product/spucomment/save', spuComment).then(res => {
                if(res.data.code === 0){
                    alert("success")
                    // this.$router.push("/space")
                }else {
                    alert("unexcepted error, try again")
                }
            }).catch(err => {
                alert("unexcepted error, try again")
            })
        }
    },
    mounted() {
        this.getItemData();
    },
}
