package com.csi.jcl.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author si1206 Sam Chen
 * @date 2021/08/17
 * 圖形驗證碼的配置類
 */
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha captchaProducer() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 是否有邊框
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        // 邊框顏色
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "192,192,192");
        // 驗證碼圖片的寬和高
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "110");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "40");
        // 驗證碼顏色
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "0,0,0");
        // 驗證碼字體大小
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "32");
        // 驗證碼生成幾個字元
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 驗證碼隨機字元庫
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");
        // 驗證碼圖片默認是有線條乾擾的，我們設置成沒有乾擾
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
