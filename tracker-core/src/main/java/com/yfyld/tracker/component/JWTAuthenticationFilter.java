package com.yfyld.tracker.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yfyld.tracker.bo.JwtUser;
import com.yfyld.tracker.util.CommonResult;
import com.yfyld.tracker.model.User;
import com.yfyld.tracker.util.JsonUtil;
import com.yfyld.tracker.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("adminUserDetailsService")
    private UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        // 从输入流中获取到登录的信息
        try {
            User loginUser = new ObjectMapper().readValue(request.getInputStream(), User.class);
            rememberMe.set(loginUser.getRememberMe() == null ? false : loginUser.getRememberMe());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        boolean isRemember = rememberMe.get();

        String role = "";
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }

        Claims claims = Jwts.claims();
        claims.put("role", jwtUser.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));

        String token = JwtTokenUtil.TOKEN_PREFIX + JwtTokenUtil.createToken(claims,jwtUser.getUsername(), isRemember);

        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(JsonUtil.objectToJson(new CommonResult().success(tokenMap)));
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(JsonUtil.objectToJson(new CommonResult().validateFailed("账号或者密码错误")));
        response.getWriter().flush();
    }
}