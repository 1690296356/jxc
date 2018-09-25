package com.thomas.jxc.controller.admin;

import com.thomas.jxc.entity.GoodsUnit;
import com.thomas.jxc.entity.Log;
import com.thomas.jxc.service.GoodsUnitService;
import com.thomas.jxc.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/7 17:44
 * @描述 后台管理商品单位Controller
 */
@RestController
@RequestMapping("/power/admin/goodsUnit")
public class GoodsUnitAdminController {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private GoodsUnitService mGoodsUnitService;

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
     * 返回所有商品单位 下拉框用到
     * @return map 结果集
     * @throws Exception exception
     */

    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/comboList")
    @RequiresPermissions(value="商品管理")
    public List<GoodsUnit> comboList() throws Exception{
        return mGoodsUnitService.listAll();
    }

    /**
     * 返回所有商品单位信息
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/listAll")
    @RequiresPermissions(value="商品管理")
    public Map<String, Object> ListAll() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("rows", mGoodsUnitService.listAll());
        mLogService.save(new Log(Log.SEARCH_ACTION, "查询商品单位信息"));
        return map;
    }

    /**
     * 添加商品单位
     * @param goodsUnit 商品单位实体
     * @return map 结果集
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions(value="商品管理")
    public Map<String, Object> save(GoodsUnit goodsUnit){
        Map<String, Object> map = new HashMap<>();
        mLogService.save(new Log(Log.UPDATE_ACTION,"添加商品单位信息"));
        mGoodsUnitService.save(goodsUnit);
        map.put("success", true);
        return map;
    }

    /**
     * 删除商品单位
     * @param id 商品单位ID
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions(value="商品管理")
    public Map<String, Object> delete(Integer id) throws Exception{
        mLogService.save(new Log(Log.DELETE_ACTION,"删除用户信息"+mGoodsUnitService.findById(id)));
        Map<String, Object> map = new HashMap<>();
        mGoodsUnitService.delete(id);
        map.put("success",true);
        return map;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
