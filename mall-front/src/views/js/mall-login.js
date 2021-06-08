import axios from "axios";

export default {
    name: "MallLogin",
    data(){
        return{
            login_data:{
                loginAccount:"",
                password:""
            }
        }
    },
    methods:{
        login(){
            this.axios.post('auth/login',this.login_data)
                .then(response => {
                    let user = response.data.data
                    if(response.data.code === 0){
                        alert("Login Success!")
                        this.$router.push("/")
                    }else {
                        alert("Login Failed with unexpected error! Login Again")
                    }
                })
                .catch(error =>{
                    alert("Login Failed with unexpected error! Redirect Again")
                })
        }
    },
    mounted() {
        this.axios.get("auth/getLoginUser")
            .then(response => {
                let code = response.data.code
                if(code === 0){
                    alert("Already login")
                    this.$router.back()
                }
            })
            .catch(err => {
                alert("unexcepted error")
            })
    }
}
