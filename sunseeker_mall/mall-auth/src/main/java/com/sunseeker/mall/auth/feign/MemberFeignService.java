package com.sunseeker.mall.auth.feign;

import com.sunseeker.common.utils.R;
import com.sunseeker.mall.auth.vo.UserLoginVo;
import com.sunseeker.mall.auth.vo.UserRegisterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "mall-member")
public interface MemberFeignService {

    @RequestMapping("member/member/register")
    R register(@RequestBody UserRegisterVo registerVo);


    @RequestMapping("member/member/login")
    R login(@RequestBody UserLoginVo loginVo);

}
