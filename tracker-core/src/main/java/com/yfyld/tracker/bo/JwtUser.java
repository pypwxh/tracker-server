package com.yfyld.tracker.bo;
import com.yfyld.tracker.model.Permission;
import com.yfyld.tracker.model.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@ToString
public class JwtUser implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities=new ArrayList<>();



    public JwtUser() {
    }


    public JwtUser(User user, List<Permission>permissionList) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();

        if(permissionList.size()!=0){
            for (Permission permission : permissionList) {
                if(permission!=null){ //todo 有个null值
                    authorities.add(new SimpleGrantedAuthority(permission.getValue()));
                }
            }
        }

    }

    // 获取权限信息。。
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 账号是否未过期，默认是false，记得要改一下
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否未锁定，默认是false，记得也要改一下
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号凭证是否未过期，默认是false，记得还要改一下
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 这个有点抽象不会翻译，默认也是false，记得改一下
    @Override
    public boolean isEnabled() {
        return true;
    }

}
