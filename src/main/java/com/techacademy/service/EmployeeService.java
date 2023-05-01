package com.techacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }

    /** 全件を検索して返す */
    public Employee getEmployee(Integer id) {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findById(id).get();
    }

    /** employeeの登録を行なう */
    @Transactional
    public Employee saveEmployee(Employee employee) {
        Authentication auth = employee.getAuthentication();
        auth.setEmployee(employee);
        auth.setPassword(passwordEncoder.encode(employee.getAuthentication().getPassword()));

        return employeeRepository.save(employee);
    }

    /** employeeの更新を行なう */
    @Transactional
    public Employee updateEmployee(Employee employee, Integer id) {
        Employee currentEmployee = this.getEmployee(id);
        Authentication auth = currentEmployee.getAuthentication();
        currentEmployee.setName(employee.getName());

        auth.setCode(employee.getAuthentication().getCode());
        auth.setRole(employee.getAuthentication().getRole());

        if (employee.getAuthentication().getPassword() == null) {
            auth.setPassword(currentEmployee.getAuthentication().getPassword());
        } else {
            auth.setPassword(passwordEncoder.encode(employee.getAuthentication().getPassword()));
        }

        return employeeRepository.save(currentEmployee);
    }

    /** 従業員の削除 */
    public Employee removeEmployee(Integer id) {
        Employee currentEmployee = this.getEmployee(id);
        currentEmployee.setDeleteFlag(true);
        return employeeRepository.save(currentEmployee);
    }

}
