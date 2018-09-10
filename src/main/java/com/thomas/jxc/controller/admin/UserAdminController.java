package com.thomas.jxc.controller.admin;

import com.thomas.jxc.entity.Role;
import com.thomas.jxc.entity.User;
import com.thomas.jxc.service.RoleService;
import com.thomas.jxc.service.UserRoleService;
import com.thomas.jxc.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/7 17:44
 * @描述 TODO
 */
@RestController
@RequestMapping("/admin/user")
public class UserAdminController {
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

    @Resource
    private UserRoleService mUserRoleService;

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
     * 分页查询用户信息
     * @param pUser 用户实体
     * @param page 当前页
     * @param rows 页面大小
     * @return map 结果集
     * @throws Exception exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(User pUser, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value  = "rows",required = false) Integer rows) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<User> userList = mUserService.list(pUser, page, rows, Sort.Direction.ASC, "id");
        for (User u:userList) {
            List<Role> roleList = mRoleService.findByUserId(u.getId());
            StringBuffer sb = new StringBuffer();
            for (Role r:roleList){
                sb.append(","+r.getName());
            }
            u.setRoles(sb.toString().replaceFirst(",",""));
        }

        Long total = mUserService.getCount(pUser);
        map.put("rows",userList);
        map.put("total",total);
        return map;
    }

    /**
     * 添加或者修改用户信息
     * @param pUser 用户实体
     * @return map 结果集
     */
    @RequestMapping("/save")
    public Map<String, Object> saveUser(User pUser){
        Map<String, Object> map = new HashMap<>();
        if(pUser.getId()==null){
            if(mUserService.findByUserName(pUser.getUserName())!=null){
                map.put("success", false);
                map.put("errorInfo", "用户名已经存在!");
                return map;
            }
        }
        mUserService.save(pUser);
        map.put("success", true);
        return map;
    }

    /**
     * 删除用户信息
     * @param id 用户ID
     * @return map 结果集
     * @throws Exception exception
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id) throws  Exception{
        Map<String, Object> map = new HashMap<>();
        mUserRoleService.deleteByUserId(id);
        mUserService.delete(id);
        map.put("success",true);
        return map;
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
