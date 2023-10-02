package com.wgcloud.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wgcloud.config.CommonConfig;
import com.wgcloud.entity.AccountInfo;
import com.wgcloud.util.shorturl.MD5;
import com.wgcloud.util.staticvar.StaticKeys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @version v2.3
 * @ClassName:LoginCotroller.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: LoginCotroller.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginCotroller {

    private static final Logger logger = LoggerFactory.getLogger(LoginCotroller.class);

    @Resource
    private CommonConfig commonConfig;

    /**
     * Turn to the login page
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(Model model, HttpServletRequest request) {
        return "login/login";
    }

    /**
     * Login system
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("loginOut")
    public String loginOut(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login/toLogin";
    }

    /**
     * Administrator login verification
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "login")
    public String login(Model model, HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String passwd = request.getParameter("md5pwd");
//        String code = request.getParameter(StaticKeys.SESSION_CODE);
        HttpSession session = request.getSession();
        try {
            if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(passwd)) {
                /*if (!code.equals(session.getAttribute(StaticKeys.SESSION_CODE))) {
                    model.addAttribute("error", "Verification code error");
                    return "login/login";
                }*/
                AccountInfo accountInfo = new AccountInfo();
                if (MD5.GetMD5Code(commonConfig.getAdmindPwd()).equals(passwd) && StaticKeys.ADMIN_ACCOUNT.equals(userName)) {
                    accountInfo.setAccount(StaticKeys.ADMIN_ACCOUNT);
                    accountInfo.setId(StaticKeys.ADMIN_ACCOUNT);
                    request.getSession().setAttribute(StaticKeys.LOGIN_KEY, accountInfo);
                    return "redirect:/dash/main";
                }
            }
        } catch (Exception e) {
            logger.error("Login abnormal:", e);
        }
        model.addAttribute("error", "Account or password error");
        return "login/login";
    }


}