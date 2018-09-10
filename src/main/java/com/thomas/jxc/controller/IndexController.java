package com.thomas.jxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/5 17:36
 * @描述 首页Controller
 */
@Controller
public class IndexController {
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
     * 登录请求
     * @return str
     */
    @RequestMapping(value={"/login","/"})
    public String login(){
        return "/login";
    }


    /**
     * 主界面
     * @return str
     */
    @RequestMapping(value="/main")
    public String toMain(){
        return "/main";
    }


    /**
     * 用户管理界面
     * @return str
     */
    @RequestMapping(value="/power/user")
    public String toUser(){
        return "/power/user";
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
