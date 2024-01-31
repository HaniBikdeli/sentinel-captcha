package com.hanibikdeli.sentinel.captcha;

import com.hanibikdeli.sentinel.core.CaptchaGenerationService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Log4j2
public class CaptchaTests {

    @Autowired
    private CaptchaGenerationService captchaService;

    @Test
    public void testCaptchaImage() {
        BufferedImage image = captchaService.generateCaptchaImage().getCaptchaImage();
        try {
            ImageIO.write(image, "png", new FileOutputStream(new File("captcha.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
