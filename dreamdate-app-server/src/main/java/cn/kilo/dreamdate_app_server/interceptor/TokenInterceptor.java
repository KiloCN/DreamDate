package cn.kilo.dreamdate_app_server.interceptor;

import cn.kilo.dreamdate_commons.utils.JwtUtils;
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

        //4. if valid, release request
        return true;
    }
}
