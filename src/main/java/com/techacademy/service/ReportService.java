package com.techacademy.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository ReportRepository;


    public ReportService(ReportRepository repository) {
        this.ReportRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return ReportRepository.findAll();
    }

    /** 全件を検索して返す */
    public Report getReport(Integer id) {
        // リポジトリのfindAllメソッドを呼び出す
        return ReportRepository.findById(id).get();
    }

    /** 全件を検索して返す */
    public List<Report> getReportByEmployee(Employee employee) {
        return ReportRepository.findByEmployee(employee);
    }

    /** employeeの登録を行なう */
    @Transactional
    public Report saveReport(Report report, UserDetail userDetail) {
        report.setEmployee(userDetail.getEmployee());
        if (report.getReportDate() == null) {
            report.setReportDate(LocalDate.now());
        }
        return ReportRepository.save(report);
    }

    @Transactional
    public Report updateReport(Report report) {
        Report currentReport = this.getReport(report.getId());
        report.setEmployee(currentReport.getEmployee());
        if (report.getReportDate() == null) {
            report.setReportDate(LocalDate.now());
        }
        return ReportRepository.save(report);
    }
    public boolean checkReportForUpdate(Integer id, Employee employee) {
        Report report = this.getReport(id);
        return report.getEmployee().getId() == employee.getId();
    }
}
