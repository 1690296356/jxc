package com.thomas.jxc.controller.admin;

import com.thomas.jxc.entity.Goods;
import com.thomas.jxc.entity.Log;
import com.thomas.jxc.service.GoodsService;
import com.thomas.jxc.service.LogService;
import com.thomas.jxc.util.StringUtil;
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
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
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


    /**
     * 生成商品编码
     * @return str str
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @ResponseBody
    @RequestMapping("/genGoodsCode")
    @RequiresPermissions(value="商品管理")
    public String genGoodsCode() throws Exception{
        String maxGoodsCode=mGoodsService.getMaxGoodsCode();
        if(StringUtil.isNotEmpty(maxGoodsCode)){
            Integer code = Integer.parseInt(maxGoodsCode)+1;
            StringBuilder codes = new StringBuilder(code.toString());
            int length = codes.length();
            for (int i = 4; i >length ; i--) {
                codes.insert(0, "0");
            }
            return codes.toString();
        }else{
            return "0001";
        }
    }

    /**
     * 添加或者修改商品信息
     * @param goods
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions(value="商品管理")
    public Map<String, Object> save(Goods goods) throws Exception{
        Map<String, Object> map = new HashMap<>();
        if(goods.getId() !=null){
            mLogService.save(new Log(Log.UPDATE_ACTION,"更新商品信息"+goods));
        }else{
            mLogService.save(new Log(Log.ADD_ACTION,"添加商品信息"+goods));
            goods.setLastPurchasingPrice(goods.getPurchasingPrice());//设置上次进价为当前价格
        }
        mGoodsService.save(goods);
        map.put("success", true);
        return map;
    }

    /**
     * 删除商品信息
     * @param id id
     * @return map map
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions(value="商品管理")
    public Map<String, Object> delete(Integer id)throws Exception{
        Map<String,Object> map = new HashMap<>();
        Goods goods = mGoodsService.findById(id);
        if(goods.getState()==1){
            map.put("success", false);
            map.put("errorInfo", "该商品已经期初入库,不能删除");
        }else if(goods.getState()==2){
            map.put("success", false);
            map.put("errorInfo", "该商品已经发生单据，不能删除");
        }else{
            mLogService.save(new Log(Log.DELETE_ACTION,"删除商品信息"+goods));
            mGoodsService.delete(id);
            map.put("success", true);
        }
        return map;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
