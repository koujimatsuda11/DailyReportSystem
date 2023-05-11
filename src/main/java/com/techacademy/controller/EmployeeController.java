package com.techacademy.controller;

import java.util.List;

import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Authentication.Create;
import com.techacademy.entity.Authentication.Update;
import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService employeeService) {
        this.service = employeeService;
    }
    /** listを表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        List<Employee> employeelist = service.getEmployeeList();
        model.addAttribute("employeelist", employeelist);
        model.addAttribute("size", employeelist.size());
        return "employee/list";
    }

    /** registを表示 */
    @GetMapping("/regist")
    public String getRegist(@ModelAttribute Employee employee) {
        // employee/regist.htmlに画面遷移
        return "employee/regist";
    }

    /** employeeの登録 */
    @PostMapping("/regist")
    public String getRegist(@Validated({ Create.class, Default.class }) Employee employee, BindingResult res, Model model) {
        if(res.hasErrors()) {
            // エラーあり
            return getRegist(employee);
        }
        // employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee更新画面を表示 */
    @GetMapping("/detail/{id}/")
    public String getEmployeeDetail(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // User更新画面に遷移
        return "employee/detail";
    }

    /** Employee更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Employee employee, Model model) {
        if (id == null) {
            model.addAttribute("employee", employee);
        } else {
            // Modelに登録
            Employee emp = service.getEmployee(id);
            emp.getAuthentication().setPassword(null);
            model.addAttribute("employee", emp);
        }
        // User更新画面に遷移
        return "employee/update";
    }

    /** User更新処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(@PathVariable("id") Integer id, @Validated({ Update.class, Default.class }) Employee employee, BindingResult res, Model model) {
        if(res.hasErrors()) {
            // エラーあり
            return getEmployee(null, employee, model);
        }
        // User登録
        service.updateEmployee(employee, id);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** User更新処理 */
    @GetMapping("/remove/{id}/")
    public String removeEmployee(@PathVariable("id") Integer id) {
        // User登録
        service.removeEmployee(id);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

}
