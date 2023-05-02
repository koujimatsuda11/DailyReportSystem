package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    /** ログアウト処理を行なう */
    @GetMapping("/logout")
    public String postLogout() {
        // ログイン画面にリダイレクト
        return "redirect:/login";
    }
}