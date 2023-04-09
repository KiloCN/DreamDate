package cn.kilo.dreamdate_app_server.interceptor;

import cn.kilo.dreamdate_commons.utils.JwtUtils;
import cn.kilo.dreamdate_commons.utils.UserHolder;
import cn.kilo.dreamdate_model.pojo.User;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. get token from request header
        String token = request.getHeader("Authorization");

        //2. check token weither valid
        boolean isTokenValid = JwtUtils.verifyToken(token);

        //3. if not valid, return 401
        if (!isTokenValid) {
            response.setStatus(401);
            return false;
        }

        //4. if valid, cache user info in ThreadLocal, then release request
        Claims claims = JwtUtils.getClaims(token);
        User user = new User();
        user.setId(Long.valueOf((Integer) claims.get("id")));
        user.setMobile((String) claims.get("mobile"));
        UserHolder.setUserThreadLocal(user);
        return true;
    }
}
