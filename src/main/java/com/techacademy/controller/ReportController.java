package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService reportService) {
        this.service = reportService;
    }
    /** listを表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("reportlist", service.getReportList());
        return "report/list";
    }

    /** registを表示 */
    @GetMapping("/regist")
    public String getRegist(@ModelAttribute Report report, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        // employee/regist.htmlに画面遷移
        model.addAttribute("employee", userDetail.getEmployee());

        return "report/regist";
    }

    /** employeeの登録 */
    @PostMapping("/regist")
    public String getRegist(@Validated Report report, BindingResult res, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        if(res.hasErrors()) {
            // エラーあり
            return getRegist(report, userDetail, model);
        }
        // employee登録
        service.saveReport(report, userDetail);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }

    /** detailを表示 */
    @GetMapping("/detail/{id}/")
    public String getReportDetail(@PathVariable("id") Integer id, Model model) {
        // employee/regist.htmlに画面遷移
        model.addAttribute("report", service.getReport(id));

        return "report/detail";
    }

    /** 日報更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getReport(@PathVariable("id") Integer id, Report report, Model model) {
        if (id == null) {
            model.addAttribute("report", report);
        } else {
            // Modelに登録
            model.addAttribute("report", service.getReport(id));
        }
        // User更新画面に遷移
        return "report/update";
    }

    /** 日報更新処理 */
    @PostMapping("/update/{id}/")
    public String postReport(@PathVariable("id") Integer id, @Validated Report report, BindingResult res, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        if(res.hasErrors()) {
            // エラーあり
            return getReport(null, report, model);
        }
        // User登録
        service.updateReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }
}
