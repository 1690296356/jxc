package com.thomas.jxc.controller.admin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.thomas.jxc.entity.Menu;
import com.thomas.jxc.entity.Role;
import com.thomas.jxc.entity.RoleMenu;
import com.thomas.jxc.service.*;
import com.thomas.jxc.util.StringUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/11 10:59
 * @描述 后台管理角色Controller
 */
@RestController
@RequestMapping("/power/admin/role")
public class RoleAdminController {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private RoleService mRoleService;

    @Resource
    private UserRoleService mUserRoleService;

    @Resource
    private RoleMenuService mRoleMenuService;

    @Resource
    private MenuService mMenuService;
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
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/list/all-role")
    @RequiresPermissions(value={"用户管理","角色管理"},logical = Logical.OR)
    public Map<String,Object> listAll() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("rows",mRoleService.listAll());
        return map;
    }


    /**
     * 分页查询角色信息
     * @param pRole 角色实体
     * @param page 当前页
     * @param rows 页面大小
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/list")
    @RequiresPermissions(value="角色管理")
    public Map<String, Object> list(Role pRole, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value  = "rows",required = false) Integer rows) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<Role> roleList = mRoleService.list(pRole, page, rows, Sort.Direction.ASC, "id");
        Long total = mRoleService.getCount(pRole);
        map.put("rows",roleList);
        map.put("total",total);
        return map;
    }

    /**
     * 添加或者修改角色信息
     * @param pRole 角色实体
     * @return map 结果集
     */
    @RequestMapping("/save")
    @RequiresPermissions(value="角色管理")
    public Map<String, Object> saveUser(Role pRole){
        Map<String, Object> map = new HashMap<>();
        if(pRole.getId()==null){
            if(mRoleService.findByRoleByRoleName(pRole.getName())!=null){
                map.put("success", false);
                map.put("errorInfo", "角色名已经存在!");
                return map;
            }
        }
        mRoleService.save(pRole);
        map.put("success", true);
        return map;
    }

    /**
     * 删除角色信息
     * @param id 用户ID
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/delete")
    @RequiresPermissions(value="角色管理")
    public Map<String, Object> delete(Integer id) throws Exception{
        Map<String, Object> map = new HashMap<>();
        mRoleMenuService.deleteByRoleId(id);
        mUserRoleService.deleteByRoleId(id);
        mRoleService.delete(id);
        map.put("success",true);
        return map;
    }

    /**
     * 根据父节点获取所有复选框权限菜单
     * @param parentId 父节点ID
     * @param roleId 角色ID
     * @return str str
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/load/check-menu-info")
    @RequiresPermissions(value="角色管理")
    public String loadCheckMenuInfo(Integer parentId,Integer roleId)throws Exception{
        List<Menu> menuList = mMenuService.findByRoleId(roleId);
        List<Integer> menuIdList = new LinkedList<>();
        for (Menu menu:menuList) {
            menuIdList.add(menu.getId());
        }
        return getAllCheckMenuByParentId(parentId, menuIdList).toString();
    }


    /**
     * 根据父节点id和权限菜单id集合获取所有复选框菜单集合
     * @param parentId 父节点Id
     * @param menuIdList 权限菜单id
     * @return jsonArray 复选框菜单集合
     */
    @SuppressWarnings({"UnnecessaryContinue", "WeakerAccess"})
    public JsonArray getAllCheckMenuByParentId(Integer parentId, List<Integer> menuIdList){
        JsonArray jsonArray= this.getCheckMenuByParentId(parentId, menuIdList);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            if("open".equals(jsonObject.get("state").getAsString())){
                continue;
            }else{
                jsonObject.add("children",getAllCheckMenuByParentId(jsonObject.get("id").getAsInt(), menuIdList));
            }
        }

        return jsonArray;
    }

    /**
     * 根据父节点id和权限菜单id集合获取一层复选框菜单集合
     * @param parentId 父节点id
     * @param menuIdList 权限菜单id集合
     * @return jsonArray jsonArray
     */
    @SuppressWarnings({"SuspiciousMethodCalls", "WeakerAccess"})
    public JsonArray getCheckMenuByParentId(Integer parentId, List<Integer> menuIdList){
        List<Menu> menuList = mMenuService.findByParentId(parentId);
        JsonArray jsonArray = new JsonArray();
        for (Menu menu:menuList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id",menu.getId());//节点Id
            jsonObject.addProperty("text", menu.getName());//节点名称
            if(menu.getState()==1){
                jsonObject.addProperty("state","closed");//根节点
            }else{
                jsonObject.addProperty("state","open");//叶子节点
            }
            jsonObject.addProperty("iconCls",menu.getIcon());//节点图标
            if(menuIdList.contains(menu.getId())){
                jsonObject.addProperty("checked",true);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    /**
     * 保存角色权限设置
     * @param menuIds 权限菜单Ids
     * @param roleId 角色Id
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings({"ForLoopReplaceableByForEach", "unused"})
    @RequestMapping("/save/menu-set")
    @RequiresPermissions(value="角色管理")
    public Map<String, Object> saveMenuSet(String menuIds, Integer roleId)throws Exception{
        Map<String, Object> map = new HashMap<>();
        mRoleMenuService.deleteByRoleId(roleId);//根据角色id删除所有角色权限关联实体
        if(StringUtil.isNotEmpty(menuIds)){
            String idsStr[] = menuIds.split(",");
            for (int i = 0; i < idsStr.length; i++) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRole(mRoleService.findById(roleId));
                roleMenu.setMenu(mMenuService.findById(Integer.parseInt(idsStr[i])));
                mRoleMenuService.save(roleMenu);
            }
        }
        map.put("success", true);
        return map;
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
