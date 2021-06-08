package com.sunseeker.mall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sunseeker.common.constant.AuthServerConstant;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.R;
import com.sunseeker.common.vo.MemberResponseVo;
import com.sunseeker.mall.auth.feign.MemberFeignService;
import com.sunseeker.mall.auth.service.LoginService;
import com.sunseeker.mall.auth.vo.UserLoginVo;
import com.sunseeker.mall.auth.vo.UserRegisterVo;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberFeignService memberFeignService;

    @RequestMapping("/register")
    public R register(@Valid @RequestBody UserRegisterVo registerVo, BindingResult result){
        System.out.println(registerVo);
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            //1.1 如果校验不通过，则封装校验结果
            result.getFieldErrors().forEach(item -> errors.put(item.getField(), item.getDefaultMessage()));
            return R.error().put("errors", errors);
        }
        R r = memberFeignService.register(registerVo);
        return r;
    }

    @RequestMapping("/login")
    public R login(@RequestBody UserLoginVo vo, HttpServletResponse response,HttpSession session){
        R r = memberFeignService.login(vo);

        if (r.getCode() == 0) {
            String jsonString = JSON.toJSONString(r.get("memberEntity"));
            MemberResponseVo memberResponseVo = JSON.parseObject(jsonString, new TypeReference<>() {});
            session.setMaxInactiveInterval(60*60*10);
            session.setAttribute(AuthServerConstant.LOGIN_USER, memberResponseVo);
            Cookie cookie = new Cookie("user",memberResponseVo.getUsername());
            cookie.setPath("/");   //
            cookie.setMaxAge(24*60*60);       //存活一天
            response.addCookie(cookie);
            return R.ok().put("data",memberResponseVo);
        }else {
            String msg = (String) r.get("msg");
            Map<String, String> errors = new HashMap<>();
            errors.put("msg", msg);
            return R.error().put("errors", errors);
        }
    }

    @RequestMapping("/getLoginUser")
    public R getLoginUser(HttpSession session){
        if(session.getAttribute(AuthServerConstant.LOGIN_USER) != null){
            MemberResponseVo attribute = (MemberResponseVo)session.getAttribute(AuthServerConstant.LOGIN_USER);
            return R.ok().put("data", attribute);
        }else {
            return R.error(201,"user not login");
        }
    }
    @RequestMapping("/logout")
    public R logOut(HttpSession session, HttpServletRequest request){
        if(session.getAttribute(AuthServerConstant.LOGIN_USER) != null){
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie: cookies){
                cookie.setMaxAge(0);
            }
            session.removeAttribute(AuthServerConstant.LOGIN_USER);
            return R.ok();
        }else {
            return R.error();
        }
    }
}
