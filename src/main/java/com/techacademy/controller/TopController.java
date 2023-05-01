package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
public class TopController {
    private final ReportService service;

    public TopController(ReportService reportService) {
        this.service = reportService;
    }

    /** Topを表示 */
    @GetMapping("/")
    public String indexAction( @AuthenticationPrincipal UserDetail userDetail, Model model) {

        // 全件検索結果をModelに登録
        model.addAttribute("reportlist", service.getReportByEmployee(userDetail.getEmployee()));
        // index.htmlに画面遷移
        return "index";
    }

}