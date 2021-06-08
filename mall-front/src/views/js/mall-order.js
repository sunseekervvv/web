import qs from "qs"
import MallHeader from "@/components/MallHeader";
import th from "element-ui/src/locale/lang/th";
export default {
    name: "MallOrder",
    components:{
        MallHeader
    },
    data(){
        return{
            confirmData:{},
            activeAddressId: ""
        }
    },
    methods:{
        searchByKeyword(keyword) {
            this.$router.push({name: 'MallSearch', query: {keyword:keyword}})
        },
        getConfirm(){
            this.axios.post('order/order/toTrade')
                .then(response => {
                    if(response.data.code === 0){
                        this.confirmData = response.data.data
                    //    defaultStatus
                        this.activeAddressId = this.confirmData.memberAddressVos.filter(address => address.defaultStatus === 1)[0].id
                    }else {
                        alert("Unexpected error! Try Again")
                    }
                })
                .catch(error =>{
                    alert("Unexpected error! Try Again")
                })
        },
        submit(){
            this.axios.post('order/order/submitOrder',qs.stringify({
                    addrId: this.activeAddressId,
                    payType:1,
                    orderToken:this.confirmData.orderToken,
                    payPrice:this.confirmData.payPrice,
                    remarks:""
                 }))
                .then(response => {
                    if(response.data.code === 0){
                        let orderSn = response.data.order.orderSn
                        this.axios.get('order/pay/aliPayOrder',{params:{orderSn:orderSn}}).then(response => {
                            // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
                            const divForm = document.getElementsByTagName('div')
                            if (divForm.length) {
                                document.body.removeChild(divForm[0])
                            }
                            const div = document.createElement('div')
                            div.innerHTML = response.data // data就是接口返回的form 表单字符串
                            document.body.appendChild(div)
                            document.forms[0].setAttribute('target', '_blank') // 新开窗口跳转
                            document.forms[0].submit()
                        })
                    }else if(response.data.code === 201){
                        alert(response.data.msg)
                    }else {
                        alert("Unexcepted error")
                    }
                })
                .catch(error =>{
                    alert("Unexcepted error")
                })
        },
        selectAddress(id){
            this.activeAddressId = id
        }
    },
    mounted() {
        this.getConfirm()
    },
    computed:{
        activeAddress() {
            return this.confirmData.memberAddressVos.filter(address => address.id === this.activeAddressId)[0]
        }
    }
}
