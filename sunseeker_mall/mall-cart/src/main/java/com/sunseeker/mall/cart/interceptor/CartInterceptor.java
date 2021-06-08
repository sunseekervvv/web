package com.sunseeker.mall.cart.interceptor;

import com.sunseeker.common.constant.AuthServerConstant;
import com.sunseeker.common.constant.CartConstant;
import com.sunseeker.common.vo.MemberResponseVo;
import com.sunseeker.mall.cart.to.UserInfoTo;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class CartInterceptor implements HandlerInterceptor {

    public static ThreadLocal<UserInfoTo> threadLocal=new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberResponseVo memberResponseVo = (MemberResponseVo) session.getAttribute(AuthServerConstant.LOGIN_USER);
        UserInfoTo userInfoTo = new UserInfoTo();
        //1 用户已经登录，设置userId
        if (memberResponseVo!=null){
            userInfoTo.setUserId(memberResponseVo.getId());
        }

        threadLocal.set(userInfoTo);
        return true;
    }

}
