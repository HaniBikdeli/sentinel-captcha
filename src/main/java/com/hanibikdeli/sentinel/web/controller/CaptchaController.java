package com.hanibikdeli.sentinel.web.controller;

import com.hanibikdeli.sentinel.core.CaptchaGenerationService;
import com.hanibikdeli.sentinel.domain.Captcha;
import com.hanibikdeli.sentinel.web.vm.CaptchaVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaGenerationService captchaGenerationService;

    @GetMapping("")
    public String captcha(Model model) {
        Captcha captcha = captchaGenerationService.generateCaptchaImage();
        CaptchaVM captchaVM = CaptchaVM.builder()
                .captchaImage(captcha.getImageBase64())
                .build();
        model.addAttribute("captcha", captchaVM);
        return "captcha";
    }
}
