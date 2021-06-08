import MallHeader from "@/components/MallHeader";
import MallSous from "@/components/MallSous";
import singleUpload from "@/components/upload/singleUpload";
import qs from "qs";
import th from "element-ui/src/locale/lang/th";
export default {
    name: "MallOrderList",
    components:{
        MallHeader,MallSous,singleUpload
    },
    data(){
        return{
            orderListAll:{},
            activeFilter: -1,
            activeNav: 0,
            user:{},
            headerImg : "",
            gender:-1,
            birthDay:"",
            job:"",
            sign:"",
            addresses:[],
            receiver:"",
            phone:"",
            province:"",
            city:"",
            detailAddress:"",
        }
    },
    methods:{
        searchByKeyword(keyword) {
            this.$router.push({name: 'MallSearch', query: {keyword:keyword}})
        },
        getUpload(imgUrl){
            this.headerImg = imgUrl
            let user = this.user
            user.header = this.headerImg
            this.axios.post('member/member/update',user)
                .then(response => {
                    if(response.data.code === 0){
                        alert("Save success!")
                    }else {
                        alert("Unexpected error! Try Again")
                    }
                })
                .catch(error =>{
                    alert("Unexpected error! Try Again")
                })
        },
        getAddresses(){
        let userId = this.user.id
          this.axios.get('member/memberreceiveaddress/getAddressByCurrentUser',{params:{userId:userId}}).then(response => {
              this.addresses = response.data
          }).catch(err =>{
              alert("Unexpected error! Try Again")
          })
        },
        updateAddress(memberReceiveAddress){
            this.axios.post('member/memberreceiveaddress/update',memberReceiveAddress)
                .then(response => {
                    if(response.data.code === 0){
                        alert("Save success!")
                    }else {
                        alert("Unexpected error! Try Again")
                    }
                })
                .catch(error =>{
                    alert("Unexpected error! Try Again")
                })
        },
        saveAddress(){
            let memberReceiveAddress = {
                memberId: this.user.id,
                name: this.receiver,
                phone: this.phone,
                province: this.province,
                city: this.city,
                detailAddress: this.detailAddress,
            }
            this.axios.post('member/memberreceiveaddress/save',memberReceiveAddress)
                .then(response => {
                    if(response.data.code === 0){
                        this.getAddresses()
                        alert("Save success!")
                    }else {
                        alert("Unexpected error! Try Again")
                    }
                })
                .catch(error =>{
                    alert("Unexpected error! Try Again")
                })
        },
        deleteAddress(addressId){
            let ids = [addressId]
            this.axios.post('member/memberreceiveaddress/delete',ids)
                .then(response => {
                    if(response.data.code === 0){
                        this.getAddresses()
                        alert("Delete success!")
                    }else {
                        alert("Unexpected error! Try Again")
                    }
                })
                .catch(error =>{
                    alert("Unexpected error! Try Again")
                })
        },
        async getUser(user){
            this.user = user
            this.gender = this.user.gender
            this.job = user.job
            this.birthDay = user.birth
            this.sign = user.sign
            this.headerImg = (user.header === null || user.header === "") ? "https://sunseeker-mall.oss-cn-beijing.aliyuncs.com/front/img/home/touxiang.png" : user.header
            this.getAddresses()
        },
        saveUser(){
            let user = this.user
            user.job = this.job
            user.birth = this.birthDay
            user.sign = this.sign
            this.axios.post('member/member/update',user)
                .then(response => {
                    if(response.data.code === 0){
                        alert("Save success!")
                    }else {
                        alert("Unexpected error! Try Again")
                    }
                })
                .catch(error =>{
                    alert("Unexpected error! Try Again")
                })
        },
        getOrderList(){
            this.axios.post('order/order/memberOrder')
                .then(response => {
                    if(response.data.code === 0){
                        let orderListAll = response.data.page.list
                        let href = window.location.href
                        if(href.indexOf("out_trade_no")!==-1){
                            let arr = href.split("?")[1].split("&")
                            let orderSn
                            for (const string of arr) {
                                if(string.indexOf("out_trade_no")!==-1){
                                    orderSn = string.split("=")[1]
                                }
                            }
                            for (let i = 0; i < orderListAll.length; i++) {
                                if(orderListAll[i].orderSn === orderSn){
                                    for (let j = 0; j < orderListAll[i].items.length; j++) {
                                        orderListAll[i].items[j].status = 1
                                    }
                                }
                            }

                            this.axios.post('order/orderitem/updateOrderSn', {orderSn}).then(res => {
                                console.log(res)
                            }).catch(err => {
                                console.log(err)
                            })
                        }
                        this.orderListAll = orderListAll
                        // this.orderList = response.data.page.list
                        this.activeFilter = -1
                    }else {
                        alert("Unexpected error! Try Again")
                    }
                })
                .catch(error =>{
                    alert("Unexpected error! Try Again")
                })
        },
        changeFilter(i){
            this.activeFilter = i
        },
        toComment(id){
          this.$router.push("/comment")
        },
        confirmOrder(itemId){
            this.$confirm('Confirm the order?', 'Delete', {
                confirmButtonText: 'Confirm',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                let orderItem = {
                    id: itemId
                }
                this.axios.post('order/orderitem/confirm',orderItem)
                    .then(response => {
                        if(response.data.code === 0){
                            this.getOrderList()
                            this.$message({
                                type: 'success',
                                message: 'Confirm Success'
                            });
                        }else {
                            alert("Unexpected error! Try Again")
                        }
                    })
                    .catch(error =>{
                        alert("Unexpected error! Try Again")
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: 'Canceled'
                });
            });
        },
        deleteOrder(itemId){
            this.$confirm('Delete this Order?', 'Delete', {
                confirmButtonText: 'Confirm',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                let ids = [itemId]
                this.axios.post('order/orderitem/delete',qs.stringify({ids},{ indices: false }))
                    .then(response => {
                        if(response.data.code === 0){
                            this.getOrderList()
                            this.$message({
                                type: 'success',
                                message: 'Delete Success'
                            });
                        }else {
                            alert("Unexpected error! Try Again")
                        }
                    })
                    .catch(error =>{
                        alert("Unexpected error! Try Again")
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: 'Canceled'
                });
            });

        },
        toDetail(skuId){
            this.$router.push({name:'MallItem',params:{product_id: skuId}})
        }
    },
    mounted() {
        this.getOrderList();
    },
    computed:{
        orderList(){
            if(this.activeFilter === -1){
                return this.orderListAll
            }else if(this.activeFilter === 0){
                let list = JSON.parse(
                    JSON.stringify(this.orderListAll)
                );
                list.items = this.orderListAll.filter(item => item)
                for (let i = 0; i < list.length; i++) {
                    list[i].items = list[i].items.filter(item => item.status === 0)
                }
                return list
                // return this.orderListAll.filter(order => order.status === 0)
            }else if(this.activeFilter === 1){
                let list = JSON.parse(
                    JSON.stringify(this.orderListAll)
                );
                list.items = this.orderListAll.filter(item => item)
                for (let i = 0; i < list.length; i++) {
                    list[i].items = list[i].items.filter(item => item.status === 1)
                }
                return list
                // return this.orderListAll.filter(order => order.status === 1 || order.status === 2)
            }else if( this.activeFilter === 2){
                let list = JSON.parse(
                    JSON.stringify(this.orderListAll)
                );
                list.items = this.orderListAll.filter(item => item)
                for (let i = 0; i < list.length; i++) {
                    list[i].items = list[i].items.filter(item => item.status === 2)
                }
                return list
                // return this.orderListAll.filter(order => order.status === 1 || order.status === 2)
            } else if(this.activeFilter === 3){
                let list = JSON.parse(JSON.stringify(this.orderListAll));
                list.items = this.orderListAll.filter(item => item)
                for (let i = 0; i < list.length; i++) {
                    list[i].items = list[i].items.filter(item => item.status === 3)
                }
                return list
                // return this.orderListAll.filter(order => order.status === 3)
            }else {
                let list = JSON.parse(
                    JSON.stringify(this.orderListAll)
                );
                list.items = this.orderListAll.filter(item => item)
                for (let i = 0; i < list.length; i++) {
                    list[i].items = list[i].items.filter(item => item.status === 4 || item.status === 5)
                }
                return list
                // return this.orderListAll.filter(order => order.status === 4 || order.status === 5)
            }
        }
    }

}
