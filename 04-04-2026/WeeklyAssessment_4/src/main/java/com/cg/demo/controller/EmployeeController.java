package com.cg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cg.demo.entity.Employee;
import com.cg.demo.service.EmployeeServiceImpl;
import com.cg.dto.EmpDto;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl service;

    @GetMapping("/viewall")
    public String viewAllEmployees(Model model, 
            @RequestParam(value = "msg", required = false) String msg) {
        List<Employee> list = service.getAllEmployees();
        model.addAttribute("list", list);
        model.addAttribute("msg", msg);
        return "viewall";
    }

    @GetMapping("/edit/{eid}")
    public ModelAndView editEmployee(@PathVariable Integer eid) {
        ModelAndView mv = new ModelAndView("edit");
        Employee emp = service.getEmployeeById(eid);
        EmpDto empDto = new EmpDto();
        empDto.setEmpId(emp.getEmpId());
        empDto.setEmpName(emp.getEmpName());
        empDto.setEmpSal(emp.getEmpSal());
        empDto.setEmpDoj(emp.getEmpDoj());
        empDto.setDeptName(emp.getDeptName());
        mv.addObject("emp", empDto);
        return mv;
    }

    @GetMapping("/delete/{eid}")
    public String delete(@PathVariable Integer eid, Model model) {
        try {
            service.deleteEmployee(eid);
            model.addAttribute("msg", "Employee Deleted Successfully");
        } catch (Exception e) {
            model.addAttribute("msg", "Error deleting employee: " + e.getMessage());
        }
        model.addAttribute("list", service.getAllEmployees());
        return "viewall";
    }

    @PostMapping("/update")
    public String updateEmployee(@Valid @ModelAttribute("emp") EmpDto empDto, 
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit"; 
        }

        Employee emp = new Employee();
        emp.setEmpId(empDto.getEmpId());
        emp.setEmpName(empDto.getEmpName());
        emp.setEmpSal(empDto.getEmpSal());
        emp.setEmpDoj(empDto.getEmpDoj());
        emp.setDeptName(empDto.getDeptName());

        try {
            service.editEmployee(emp);
            model.addAttribute("msg", "Employee Edited Successfully");
        } catch (Exception e) {
            model.addAttribute("msg", "Error updating employee: " + e.getMessage());
        }

        model.addAttribute("list", service.getAllEmployees());
        return "viewall";
    }
}