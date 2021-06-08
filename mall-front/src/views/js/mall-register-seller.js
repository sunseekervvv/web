import ValidCode from "@/components/ValidCode";
import axios from "axios";
export default {
    name: "MallRegister",
    components:{
        ValidCode
    },
    data(){
        return{
            code_right:"",
            agree: false,
            register_data:{
                username:"",
                password:"",
                phone:""
            },
            password_confirm:"",
            code:"",
            validate:{
                username:false,
                password:false,
                password_confirm:false,
                phone:false,
                code:false,
                agree:false
            },
            validate_data:{
                username:"username can't be null",
                password:"Password can't be null",
                password_confirm:"Not same with password",
                phone:"Phone number is illegal",
                code:"Wrong code",
                agree: "Please agree"
            }

        }
    },
    methods:{
        toSeller(){
            window.location.href="https://xdu-mall-back.shop/"
        },
        toCustomer(){
          this.$router.push("/register")
        },
        getRightCode(code){
            this.code_right = code
        },
        register(){
            this.initValidate()
            this.validateData()
            let sysUser = {

            }
            axios.post('seller/user/save',this.register_data)
                .then(response => {
                    if(response.data.code === 0){
                        alert("Register Success! Redirect to Login")
                        window.location.href="https://xdu-mall-back.shop/"
                    }else if(response.data.code === 15001){
                        this.validate_data.username = "username already exist"
                        this.validate.username = true
                    }else if(response.data.code === 15002){
                        this.validate_data.phone = "Phone already register"
                        this.validate.phone = true
                    }else {
                        alert("Register Failed with unexpected error! Redirect Again")
                    }
                })
                .catch(error =>{
                    alert("Register Failed with unexpected error! Redirect Again")
                })
        },
        initValidate(){
            this.validate.username = false;
            this.validate.password = false;
            this.validate.password_confirm = false;
            this.validate.phone = false;
            this.validate.code = false;
            this.validate.agree = false;
        },
        validateData(){
            if(this.register_data.username === ""){
                this.validate_data.username = "username can't be null"
                this.validate.username = true
            }
            if(this.register_data.password === ""){
                this.validate.password = true
            }
            if(this.password_confirm !== this.register_data.password){
                this.validate.password_confirm = true
            }
            if(!/^[1]([3-9])[0-9]{9}$/.test(this.register_data.phone)){
                this.validate_data.phone = "Phone number is illegal"
                this.validate.phone = true
            }
            if(this.code_right.toLowerCase() !== this.code.toLowerCase()){
                this.validate.code = true
            }
            if(!this.agree){
                this.validate.agree = true
            }
        }
    }
}
