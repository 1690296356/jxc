package com.thomas.jxc.repository;

import com.thomas.jxc.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/3 16:47
 * @描述 权限菜单接口层
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================

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
     *根据父节点以及用户角色id查询子节点
     * @param pParentID 父节点ID
     * @param pRoleID 用户角色ID
     * @return 菜单列表
     */
    @Query(value = "SELECT * FROM t_menu WHERE p_id=?1 AND id IN( SELECT menu_id FROM t_role_menu WHERE role_id=?2)",nativeQuery = true)
    public List<Menu> findByParentIDAndRoleID(int pParentID, int pRoleID);

    /**
     *根据角色id获取菜单集合
     * @param roleId 角色ID
     * @return 菜单列表
     */
    @Query(value="SELECT m.* FROM t_role r,t_role_menu rm,t_menu m WHERE rm.`role_id`=r.`id` AND rm.`menu_id`=m.`id` AND r.`id`=?1",nativeQuery=true)
    public List<Menu> findByRoleId(int roleId);




    /**
     *根据父节点查找所有子节点
     * @param  pParentID 父节点ID
     * @return 菜单列表
     */
    @Query(value = "SELECT * FROM t_menu WHERE p_id=?1", nativeQuery=true)
    public List<Menu> findByParentId(int pParentID);


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}