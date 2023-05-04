package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {

    /** Topを表示 */
    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }
}