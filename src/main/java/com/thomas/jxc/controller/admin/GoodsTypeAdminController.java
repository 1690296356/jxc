package com.thomas.jxc.controller.admin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.thomas.jxc.entity.GoodsType;
import com.thomas.jxc.entity.Log;
import com.thomas.jxc.service.GoodsService;
import com.thomas.jxc.service.GoodsTypeService;
import com.thomas.jxc.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/basicData/admin/goodsType")
public class GoodsTypeAdminController {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private GoodsTypeService mGoodsTypeService;

    @Resource
    private LogService mLogService;

    @Resource
    private GoodsService mGoodsService;

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
    @RequestMapping("/save")
    @RequiresPermissions(value="商品管理")
    public Map<String,Object>  save(String name,Integer parentId)throws Exception{
        Map<String, Object> map=new HashMap<>();
        GoodsType goodsType = new GoodsType();
        goodsType.setName(name);
        goodsType.setpId(parentId);
        goodsType.setIcon("icon-folder");
        goodsType.setState(0);
        mGoodsTypeService.save(goodsType);

        GoodsType parentGoodsType = mGoodsTypeService.findById(parentId);
        parentGoodsType.setState(1);
        mGoodsTypeService.save(parentGoodsType);

        mLogService.save(new Log(Log.ADD_ACTION, "添加商品类别信息"));
        map.put("success", true);
        return map;
    }

    /**
     * 商品类别删除
     * @param id 商品类别id
     * @return 商品类别结果集 商品类别结果集
     * @throws Exception exception
     */
    @RequestMapping("/delete")
    @RequiresPermissions(value="商品管理")
    public Map<String, Object> delete(Integer id)throws Exception{
        Map<String, Object> map = new HashMap<>();
        if(mGoodsService.findByTypeId(id).size()==0){
            GoodsType goodsType = mGoodsTypeService.findById(id);
            if(mGoodsTypeService.findByParentId(goodsType.getpId()).size()==1){
                GoodsType parentGoodsType=mGoodsTypeService.findById(goodsType.getpId());
                parentGoodsType.setState(0);
                mGoodsTypeService.save(parentGoodsType);
            }
            mLogService.save(new Log(Log.DELETE_ACTION,"删除商品类别信息"+goodsType));
            mGoodsTypeService.delete(id);
            map.put("success", true);
        }else{
            map.put("success", false);
            map.put("errorInfo", "该类别下含有商品，不能删除!");
        }
        return map;
    }


    /**
     * 根据父节点获取所有复选款权限菜单
     * @return jsonArray 复选框菜单集合
     */
    @SuppressWarnings({"UnnecessaryContinue", "WeakerAccess"})
    @RequestMapping("/loadTreeInfo")
    @RequiresPermissions(value="角色管理")
    public String loadTreeInfo(){
        mLogService.save(new Log(Log.SEARCH_ACTION,"查询所有商品类别信息"));
        return getAllByParentId(-1).toString();
    }

    /**
     * 根据父节点id和权限菜单id集合获取一层复选框菜单集合
     * @param parentId 父节点id
     * @return jsonArray jsonArray
     */
    @SuppressWarnings({"SuspiciousMethodCalls", "WeakerAccess"})
    public JsonArray getAllByParentId(Integer parentId){
        JsonArray jsonArray = this.getByParentId(parentId);
        for (int i=0;i<jsonArray.size();i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            if("open".equals(jsonObject.get("state").getAsString())){
               continue;
            }else{
                jsonObject.add("children",getAllByParentId(jsonObject.get("id").getAsInt()));//叶子节点
            }
        }
        return jsonArray;
    }

    /**
     * 根据父节点查询所有子节点
     * @param parentId 父节点
     * @return jsonArray jsonArray
     */
    public JsonArray getByParentId(Integer parentId){
        List<GoodsType> goodsTypeList = mGoodsTypeService.findByParentId(parentId);
        JsonArray jsonArray = new JsonArray();
        for (GoodsType goodsType:goodsTypeList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", goodsType.getId());
            jsonObject.addProperty("text",goodsType.getName());
            if(goodsType.getState()==1){
                jsonObject.addProperty("state","closed");//根节点
            }else{
                jsonObject.addProperty("state","opne");//叶子节点
            }
            jsonObject.addProperty("iconCls", goodsType.getIcon());//节点图标
            JsonObject attributeObject = new JsonObject();//扩展属性
            attributeObject.addProperty("state", goodsType.getState());//节点状态
            jsonObject.add("attributes", attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
