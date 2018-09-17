package com.thomas.jxc.controller.admin;

import com.thomas.jxc.entity.Goods;
import com.thomas.jxc.entity.Log;
import com.thomas.jxc.service.GoodsService;
import com.thomas.jxc.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/17 16:15
 * @描述 后台管理商品Controller
 */
@Controller
@RequestMapping("/power/admin/goods")
public class GoodsAdminController {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private GoodsService mGoodsService;


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
     * 分页查询商品信息
     * @param goods goods
     * @param page 当前页
     * @param rows 页面大小
     * @return  map 商品集合
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions(value="商品管理")
    public Map<String, Object> list(Goods goods, @RequestParam(value = "page", required = false)Integer page, @RequestParam(value = "rows", required = false)Integer rows) throws  Exception{
        Map<String, Object> map = new HashMap<>();
        List<Goods> goodsList = mGoodsService.list(goods, page, rows, Sort.Direction.ASC, "id");
        Long total = mGoodsService.getCount(goods);
        map.put("rows", goodsList);
        map.put("total", total);
        mLogService.save(new Log(Log.SEARCH_ACTION, "查询商品信息"));

        return map;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
