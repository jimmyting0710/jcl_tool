package com.csi.jcl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author si1206 Sam Chen
 */
@SpringBootApplication
public class JclApplication {
    private static final Logger logger =
            LogManager.getLogger(JclApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JclApplication.class, args);
        logger.info("Starting program");
    }

}
