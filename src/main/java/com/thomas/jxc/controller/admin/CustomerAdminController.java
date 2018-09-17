package com.thomas.jxc.controller.admin;

import com.thomas.jxc.entity.Customer;
import com.thomas.jxc.entity.Log;
import com.thomas.jxc.service.CustomerService;
import com.thomas.jxc.service.LogService;
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
 * @描述 后台客户Controller
 */
@RestController
@RequestMapping("/power/admin/customer")
public class CustomerAdminController {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private CustomerService mCustomerService;

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
     * 分页查询客户信息
     * @param pCustomer 客户实体
     * @param page 当前页
     * @param rows 页面大小
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions(value="客户管理")
    public Map<String, Object> list(Customer pCustomer, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value  = "rows",required = false) Integer rows) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<Customer> customerList = mCustomerService.list(pCustomer, page, rows, Sort.Direction.ASC, "id");
        Long total = mCustomerService.getCount(pCustomer);
        map.put("rows",customerList);
        map.put("total",total);
        mLogService.save(new Log(Log.SEARCH_ACTION, "查询客户信息"));
        return map;
    }

    /**
     * 添加或者修改客户信息
     * @param pCustomer 客户实体
     * @return map 结果集
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions(value="客户管理")
    public Map<String, Object> saveSupplier(Customer pCustomer){
        Map<String, Object> map = new HashMap<>();
        if(pCustomer.getId()!=null){
            mLogService.save(new Log(Log.UPDATE_ACTION,"更新客户信息"+pCustomer));
        }else{
            mLogService.save(new Log(Log.ADD_ACTION, "添加客户信息"+pCustomer));
        }
        mCustomerService.save(pCustomer);
        map.put("success", true);
        return map;
    }

    /**
     * 删除客户信息
     * @param ids 客户ID
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings({"RedundantThrows", "Duplicates"})
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions(value="客户管理")
    public Map<String, Object> delete(String ids) throws Exception{
        Map<String, Object> map = new HashMap<>();
        String []idsStr=ids.split(",");
        for (String anIdsStr : idsStr) {
            int id = Integer.parseInt(anIdsStr);
            mLogService.save(new Log(Log.DELETE_ACTION, "删除供应商信息" + mCustomerService.findById(id)));
            mCustomerService.delete(id);
        }
        map.put("success",true);
        return map;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
