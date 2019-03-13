package com.yfyld.tracker.component;

import com.yfyld.tracker.model.Permission;
import com.yfyld.tracker.service.user.UserService;
import com.yfyld.tracker.util.JwtTokenUtil;
import com.yfyld.tracker.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {

        super(authenticationManager);

    }



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        String _projectHeader = request.getHeader(JwtTokenUtil.PROJECT_HEADER);
        Long projectHeader=null;
        if(_projectHeader!=null){
            projectHeader=Long.valueOf(_projectHeader);
        }
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        if(projectHeader!=null){
            UserService userService = (UserService) SpringUtils.getBean("userService");
            List<Permission> permissionList =userService.getPermissionList(projectHeader);
            if(permissionList.size()!=0&&permissionList.get(0)!=null){
                for (Permission permission : permissionList) {
                    authorities.add(new SimpleGrantedAuthority(permission.getValue()));
                }
            }
        }


        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader,authorities));
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader,List<SimpleGrantedAuthority> authorities){
        String token = tokenHeader.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        String username = JwtTokenUtil.getUsername(token);
        Collection<String> roles = JwtTokenUtil.getUserRole(token);



        if (username != null) {

            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }

            return new UsernamePasswordAuthenticationToken(username, null,
                    authorities
            );
        }
        return null;
    }
}