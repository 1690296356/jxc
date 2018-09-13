package com.thomas.jxc.controller.admin;

import com.thomas.jxc.entity.Log;
import com.thomas.jxc.entity.Role;
import com.thomas.jxc.entity.User;
import com.thomas.jxc.entity.UserRole;
import com.thomas.jxc.service.LogService;
import com.thomas.jxc.service.RoleService;
import com.thomas.jxc.service.UserRoleService;
import com.thomas.jxc.service.UserService;
import com.thomas.jxc.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/power/admin/user")
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

    @Resource
    private LogService mLogService;

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
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/list")
    @RequiresPermissions(value="用户管理")
    public Map<String, Object> list(User pUser, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value  = "rows",required = false) Integer rows) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<User> userList = mUserService.list(pUser, page, rows, Sort.Direction.ASC, "id");
        for (User u:userList) {
            List<Role> roleList = mRoleService.findByUserId(u.getId());
            StringBuilder sb = new StringBuilder();
            for (Role r:roleList){
                sb.append(",").append(r.getName());
            }
            u.setRoles(sb.toString().replaceFirst(",",""));
        }

        Long total = mUserService.getCount(pUser);
        map.put("rows",userList);
        map.put("total",total);
        mLogService.save(new Log(Log.SEARCH_ACTION, "查询用户信息"));
        return map;
    }

    /**
     * 添加或者修改用户信息
     * @param pUser 用户实体
     * @return map 结果集
     */
    @RequestMapping("/save")
    @RequiresPermissions(value="用户管理")
    public Map<String, Object> saveUser(User pUser){
        Map<String, Object> map = new HashMap<>();
        if(pUser.getId()==null){
            if(mUserService.findByUserName(pUser.getUserName())!=null){
                map.put("success", false);
                map.put("errorInfo", "用户名已经存在!");
                return map;
            }
        }
        if(pUser.getId()!=null){
            mLogService.save(new Log(Log.UPDATE_ACTION,"更新用户信息"+pUser));
        }else{
            mLogService.save(new Log(Log.ADD_ACTION, "添加用户信息"+pUser));
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
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/delete")
    @RequiresPermissions(value="用户管理")
    public Map<String, Object> delete(Integer id) throws Exception{
        mLogService.save(new Log(Log.DELETE_ACTION,"删除用户信息"+mUserService.findByUserId(id)));
        Map<String, Object> map = new HashMap<>();
        mUserRoleService.deleteByUserId(id);
        mUserService.delete(id);
        map.put("success",true);
        return map;
    }


    /**
     * 保存用户角色信息
     * @param roleIDS 角色ID集
     * @param userID  用户ID
     * @return 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/save/role")
    @RequiresPermissions("用户管理")
    public Map<String, Object> saveRoleSet(String roleIDS,Integer userID)throws Exception{
        Map<String,Object> map = new HashMap<>();
        mUserRoleService.deleteByUserId(userID);
        if(StringUtil.isNotEmpty(roleIDS)){
            String roleIdStr[] = roleIDS.split(",");
            for (String aRoleIdStr : roleIdStr) {
                UserRole userRole = new UserRole();
                userRole.setUser(mUserService.findByUserId(userID));
                userRole.setRole(mRoleService.findById(Integer.parseInt(aRoleIdStr)));
                mUserRoleService.save(userRole);
            }
        }
        map.put("success",true);
        mLogService.save(new Log(Log.UPDATE_ACTION,"保存用户角色管理"));
        return map;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
