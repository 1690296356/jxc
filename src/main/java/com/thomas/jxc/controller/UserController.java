package com.thomas.jxc.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.thomas.jxc.entity.Log;
import com.thomas.jxc.entity.Menu;
import com.thomas.jxc.entity.Role;
import com.thomas.jxc.entity.User;
import com.thomas.jxc.service.LogService;
import com.thomas.jxc.service.MenuService;
import com.thomas.jxc.service.RoleService;
import com.thomas.jxc.service.UserService;
import com.thomas.jxc.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Resource
    private MenuService mMenuService;

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
            mLogService.save(new Log(Log.LOGIN_ACTION,"用户登录"));
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
     * @return str  str
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @GetMapping("/load/user-info")
    public String loadUserInfo(HttpSession session)throws Exception{
        User currentUser = (User) session.getAttribute("currentUser");
        Role currentRole = (Role) session.getAttribute("currentRole");
        return "欢迎您:"+currentUser.getTrueName()+"&nbsp;[&nbsp;"+currentRole.getName()+"&nbsp;]";
    }

    /**
     * 加载权限菜单
     * @param session session
     * @param pParentID 父节点
     * @return str str
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @PostMapping("load/menu-info")
    public String loadMenuInfo(HttpSession session,Integer pParentID)throws Exception{
        Role currentRole = (Role) session.getAttribute("currentRole");
        return this.getAllMenuByParentID(pParentID,currentRole.getId()).toString();

    }

    /**
     * 获取所有菜单信息
     * @param pParentID 父节点ID
     * @param pRoleID 角色ID
     * @return jsonArray jsonArray
     */
    @SuppressWarnings("WeakerAccess")
    public JsonArray getAllMenuByParentID(Integer pParentID, Integer pRoleID){
        JsonArray jsonArray = this.getMenuByParentID(pParentID,pRoleID);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            if("open".equalsIgnoreCase(jsonObject.get("state").getAsString())){
                //noinspection UnnecessaryContinue
                continue;
            }else{
                jsonObject.add("children",getAllMenuByParentID(jsonObject.get("id").getAsInt(),pRoleID));
            }
        }
        return jsonArray;
    }


    /**
     * 根据父节点ID和用户角色ID查询菜单
     * @param pParentID 父节点ID
     * @param pRoleID 用户角色ID
     * @return jsonArray jsonArray
     */
    @SuppressWarnings("WeakerAccess")
    public JsonArray getMenuByParentID(Integer pParentID, Integer pRoleID){
        List<Menu> menuList= mMenuService.findByParentIDAndRoleID(pParentID, pRoleID);
        JsonArray jsonArray = new JsonArray();
        for (Menu menu:menuList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", menu.getId());//节点ID
            jsonObject.addProperty("text",menu.getName());//节点名称

            if(menu.getState()==1){
                jsonObject.addProperty("state","closed");//根节点
            }else{
                jsonObject.addProperty("state","open");//叶子结点
            }
            jsonObject.addProperty("iconCls",menu.getIcon());

            JsonObject attributeObject = new JsonObject();//扩展属性
            attributeObject.addProperty("url", menu.getUrl());//菜单请求地址
            jsonObject.addProperty("attributes",attributeObject.toString());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
