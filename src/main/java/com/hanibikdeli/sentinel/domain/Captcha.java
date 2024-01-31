package com.hanibikdeli.sentinel.domain;

import lombok.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Captcha {

    private String id;

    private String captchaCode;

    private BufferedImage captchaImage;


    public String getImageBase64() {
        try {
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(captchaImage, "png", os);
            byte[] bytes = os.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
