package com.thomas.jxc.realm;

import com.thomas.jxc.entity.Menu;
import com.thomas.jxc.entity.Role;
import com.thomas.jxc.entity.User;
import com.thomas.jxc.repository.MenuRepository;
import com.thomas.jxc.repository.RoleRepository;
import com.thomas.jxc.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/3 16:34
 * @描述 TODO
 */
public class MyRealm  extends AuthorizingRealm {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private MenuRepository menuRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userRepository.findByUserName(userName);
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        List<Role> roleList = roleRepository.findByUserId(user.getId());
        Set<String> roles = new HashSet<String>();
        for (Role role:roleList){
            roles.add(role.getName());
            List<Menu> menuList = menuRepository.findByRoleId(role.getId());
            for (Menu menu:menuList){
                info.addStringPermission(menu.getName());
            }
        }
        info.setRoles(roles);
        return info;
    }

    /**
     *身份权限认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userRepository.findByUserName(userName);
        if(user != null){
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"xxx");
            return authcInfo;
        }else{
            return null;
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
