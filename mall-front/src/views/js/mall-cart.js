import MallHeader from "@/components/MallHeader";
import MallSous from "@/components/MallSous";
import qs from "qs"
export default {
    name: "MallCart",
    components:{
        MallHeader,MallSous
    },
    data(){
      return{
          cartList:{},
          select_all: true
      }
    },
    methods:{
        checkCart(skuId, isChecked,i){
            this.axios.post("cart/checkCart",qs.stringify({isChecked:isChecked?1:0,skuId}))
                .then(response =>{
                    if(response.data.code === 0){
                        this.getCartData()
                        this.select_all = true
                        for (let item of  this.cartList.items) {
                            if(!item.check){
                                this.select_all = false
                                break
                            }
                        }
                    }else {
                        alert("Failed ! Try again")
                    }
                })
                .catch(err =>{
                    alert("Failed ! Try again")
                    this.getCartData()
                })
        },
        getCartData(){
            this.axios.get("cart/cartList")
                .then(response =>{
                    if(response.data.code === 0){
                        this.cartList = response.data.data
                        for (let item of  this.cartList.items) {
                            if(!item.check){
                                this.select_all = false
                                break
                            }
                        }
                    }else {
                        alert("Failed ! Try again")
                    }
                })
                .catch(err =>{
                    // alert("Failed ! Try agai")
                })
        },
        countItem(skuId,count){
            this.axios.post("cart/countItem",qs.stringify({num:count,skuId}))
                .then(response =>{
                    if(response.data.code === 0){
                        this.getCartData()
                    }else {
                        alert("Failed ! Try again")
                    }
                })
                .catch(err =>{
                    alert("Failed ! Try again")
                    this.getCartData()
                })
        },
        deleteItem(skuId){
            this.$confirm('Delete item from cart?', 'Delete', {
                confirmButtonText: 'Confirm',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.axios.post("cart/deleteItem",qs.stringify({skuId}))
                    .then(response =>{
                        if(response.data.code === 0){
                            this.getCartData()
                        }else {
                            alert("Failed ! Try again")
                        }
                    })
                    .catch(err =>{
                        alert("Failed ! Try again")
                        this.getCartData()
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: 'Canceled'
                });
            });

        },
        deleteAll(){
            this.$confirm('Delete items from cart?', 'Delete', {
                confirmButtonText: 'Confirm',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                let skuIds = []
                for (let item of  this.cartList.items) {
                    if(item.check){
                        skuIds.push(item.skuId)
                    }
                }
                this.axios.post("cart/deleteSelect",qs.stringify({skuIds},{ indices: false }))
                    .then(response =>{
                        if(response.data.code === 0){
                            this.getCartData()
                        }else {
                            alert("Failed ! Try again")
                        }
                    })
                    .catch(err =>{
                        alert("Failed ! Try again")
                        this.getCartData()
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: 'Canceled'
                });
            });


        },
        selectAll(){
            this.axios.post("cart/selectAll",qs.stringify({isChecked:!this.select_all?1:0}))
                .then(response =>{
                    if(response.data.code === 0){
                        this.getCartData()
                    }else {
                        alert("Failed ! Try again")
                    }
                })
                .catch(err =>{
                    alert("Failed ! Try again")
                    this.getCartData()
                })
        },
        searchByKeyword(keyword) {
            this.$router.push({name: 'MallSearch', query: {keyword:keyword}})
        },

        toTrade(){
            // console.log(this.cartList)
            // let cartItems = this.cartList
            // cartList.items = cartList.items.filter(item => {return item.check === true})
            this.$router.push("/order")
        }
    },
    mounted() {
        this.getCartData()
    }
}
