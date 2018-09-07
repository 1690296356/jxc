package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.Menu;
import com.thomas.jxc.repository.MenuRepository;
import com.thomas.jxc.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/7 11:25
 * @描述 TODO
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================

    @Resource
    private MenuRepository mMenuRepository;

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
    @Override
    public List<Menu> findByParentIDAndRoleID(int pParentID, int pRoleID) {
        return mMenuRepository.findByParentIDAndRoleID(pParentID, pRoleID);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
