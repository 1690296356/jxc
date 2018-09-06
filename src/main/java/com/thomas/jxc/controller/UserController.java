package com.thomas.jxc.controller;

import com.thomas.jxc.entity.Role;
import com.thomas.jxc.entity.User;
import com.thomas.jxc.service.RoleService;
import com.thomas.jxc.service.UserService;
import com.thomas.jxc.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 10:41
 * @描述 userController
 */
@RestController
@RequestMapping("/user")
public class UserController {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private UserService mUserService;

    @Resource
    private RoleService mRoleService;
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
     * 用户登录
     *
     */
    @RequestMapping("/login")
    public Map<String,Object> login(String pImageCode, @Valid User pUser, BindingResult bindingResult, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        if(StringUtil.isEmpty(pImageCode)){
            map.put("success",false);
            map.put("errorInfo","请输入验证码!");
            return map;
        }
        if(!session.getAttribute("checkcode").equals(pImageCode)){
            map.put("success",false);
            map.put("errorInfo","验证码输入错误!");
            return map;
        }
        if(bindingResult.hasErrors()){
            map.put("success",false);
            map.put("errorInfo",bindingResult.getFieldError().getDefaultMessage());
            return map;
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(pUser.getUserName(), pUser.getPassword());
        try{
            subject.login(token);
            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            User currentUser = mUserService.findByUserName(userName);
            session.setAttribute("currentUser",currentUser);
            List<Role> roleList = mRoleService.findByUserId(currentUser.getId());
            map.put("roleList", roleList);
            map.put("roleSize",roleList.size());
            map.put("success",true);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("errorInfo","用户名或者密码错误!");
            return map;
        }
    }

    /**
     * 保存角色信息
     * @param pRoleId 角色ID
     * @param session session
     * @return map 结果集
     * @throws Exception
     */
    @RequestMapping("/save/role")
    public Map<String,Object> saveRole(Integer pRoleId,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Role currentRole = mRoleService.findById(pRoleId);
        session.setAttribute("currentRole",currentRole);
        map.put("success",true);
        return map;
    }

    /**
     * 加载当前用户信息
     * @param session session
     * @return
     * @throws Exception
     */
    @GetMapping("/load/user-info")
    public String loadUserInfo(HttpSession session)throws Exception{
        User currentUser = (User) session.getAttribute("currentUser");
        Role currentRole = (Role) session.getAttribute("currentRole");
        return "欢迎您:"+currentUser.getTrueName()+"&nbsp;[&nbsp;"+currentRole.getName()+"&nbsp;]";
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
