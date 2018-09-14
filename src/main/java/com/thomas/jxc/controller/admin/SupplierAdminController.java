package com.thomas.jxc.controller.admin;

import com.thomas.jxc.entity.Log;
import com.thomas.jxc.entity.Supplier;
import com.thomas.jxc.service.LogService;
import com.thomas.jxc.service.SupplierService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/13 19:46
 * @描述 后台供应商Controller
 */
@RestController
@RequestMapping("/power/admin/supplier")
public class SupplierAdminController {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private SupplierService mSupplierService;

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
     * 分页查询供应商信息
     * @param pSupplier 供应商实体
     * @param page 当前页
     * @param rows 页面大小
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions(value="供应商管理")
    public Map<String, Object> list(Supplier pSupplier, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value  = "rows",required = false) Integer rows) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<Supplier> supplierList = mSupplierService.list(pSupplier, page, rows, Sort.Direction.ASC, "id");
        Long total = mSupplierService.getCount(pSupplier);
        map.put("rows",supplierList);
        map.put("total",total);
        mLogService.save(new Log(Log.SEARCH_ACTION, "查询供应商信息"));
        return map;
    }

    /**
     * 添加或者修改供应商信息
     * @param pSupplier 供应商实体
     * @return map 结果集
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions(value="供应商管理")
    public Map<String, Object> saveSupplier(Supplier pSupplier){
        Map<String, Object> map = new HashMap<>();
        if(pSupplier.getId()!=null){
            mLogService.save(new Log(Log.UPDATE_ACTION,"更新供应商信息"+pSupplier));
        }else{
            mLogService.save(new Log(Log.ADD_ACTION, "添加供应商信息"+pSupplier));
        }
        mSupplierService.save(pSupplier);
        map.put("success", true);
        return map;
    }

    /**
     * 删除供应商信息
     * @param ids 供应商ID
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions(value="供应商管理")
    public Map<String, Object> delete(String ids) throws Exception{
        Map<String, Object> map = new HashMap<>();
        String []idsStr=ids.split(",");
        for (String anIdsStr : idsStr) {
            int id = Integer.parseInt(anIdsStr);
            mLogService.save(new Log(Log.DELETE_ACTION, "删除供应商信息" + mSupplierService.findById(id)));
            mSupplierService.delete(id);
        }
        map.put("success",true);
        return map;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
