package com.hanibikdeli.sentinel.core;

import com.hanibikdeli.sentinel.domain.Captcha;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Service
public class CaptchaGenerationService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int WIDTH = 150;
    private static final int HEIGHT = 50;
    private static final int CHAR_COUNT = 6;
    private static final int FONT_SIZE = 30;
    private static final int NOISE_COUNT = 50;


    public Captcha generateCaptchaImage() {
        Random random = new Random();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getRandomColor(random));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < NOISE_COUNT; i++) {
            g.setColor(getRandomColor(random));
            g.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT), random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }

        String captchaText = "";
        for (int i = 0; i < CHAR_COUNT; i++) {
            char ch = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            captchaText += ch;
            g.setColor(getRandomColor(random));
            g.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
            int x = (WIDTH / CHAR_COUNT) * i + 10;
            int y = HEIGHT / 2 + 5;
            g.drawChars(new char[]{ch}, 0, 1, x, y);
        }

        g.dispose();
        return Captcha
                .builder()
                .captchaCode(captchaText)
                .captchaImage(image)
                .build();
    }

    private Color getRandomColor(Random random) {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}
