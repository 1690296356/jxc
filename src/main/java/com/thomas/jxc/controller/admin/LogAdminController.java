package com.thomas.jxc.controller.admin;

import com.thomas.jxc.entity.Log;
import com.thomas.jxc.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/12 16:13
 * @描述 后台日志Controller
 */
@RestController
@RequestMapping("/power/admin/log")
public class LogAdminController {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
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
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          dateFormat.setLenient(true);
          binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); //true:允许输入空值， false:不能输入空值
    }

    /**
     * 分页查询系统日志
     * @param pLog 日志实体
     * @param page 当前页
     * @param rows 页面大小
     * @return map 结果集
     * @throws Exception exception
     */
    @SuppressWarnings("RedundantThrows")
    @RequestMapping("/list")
    @RequiresPermissions(value="系统日志")
    public Map<String, Object> list(Log pLog, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value  = "rows",required = false) Integer rows) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<Log> logList = mLogService.list(pLog, page, rows, Sort.Direction.DESC, "time");
        Long total = mLogService.getCount(pLog);
        map.put("rows",logList);
        map.put("total",total);
        mLogService.save(new Log(Log.SEARCH_ACTION, "查询系统日志"));
        return map;
    }


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
