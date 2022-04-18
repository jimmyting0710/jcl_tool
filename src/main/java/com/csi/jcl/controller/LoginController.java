package com.csi.jcl.controller;

import com.csi.jcl.entity.CheckCodeEntity;
import com.csi.jcl.service.UserInfoService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * 定義LoginController類別
 *
 * @author si1204 丁哥/si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/17
 */
@Controller
public class LoginController {

    private static final Logger logger =
            LogManager.getLogger(LoginController.class);

    /**
     * 注入DefaultKaptcha
     *
     * @author si1204 丁哥/si1206 Sam Chen
     * @version 1.8
     * @date 2021/08/17
     */
    private final DefaultKaptcha defaultKaptcha;
    public LoginController(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }

    /**
     * 登入頁面
     *
     * @author si1204 丁哥/si1206 Sam Chen
     * @version 1.8
     * @return login.html
     * @date 2021/08/17
     */
    @GetMapping("/loginPage")
    public String showLoginPage() {
        logger.info("It's login page");
        return "login/login";
    }

    /**
     * 取得圖片驗證碼
     *
     * @author si1204 丁哥/si1206 Sam Chen
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return Kaptcha圖片
     * @version 1.8
     * @date 2021/08/17
     *
     */
    @GetMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 創建驗證碼文本
        String capText = defaultKaptcha.createText();
        logger.info("capText: " + capText);
        // 創建驗證碼圖片
        BufferedImage image = defaultKaptcha.createImage(capText);
        // 將驗證碼文本放進 Session 中
        CheckCodeEntity checkCodeEntity = new CheckCodeEntity(capText);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, checkCodeEntity);
        Cookie cookie = new Cookie("capText", capText);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);

        // 將驗證碼圖片返回，禁止驗證碼圖片緩存
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    // 之後要練習用Controller取session

}
