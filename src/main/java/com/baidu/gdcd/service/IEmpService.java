package com.baidu.gdcd.service;

import java.util.List;

import com.baidu.gdcd.entity.Employee;

public interface IEmpService {

    // 根据员工Id获取员工信息
    Employee findEmpById(int empId);

    // 获取某部门下的所有员工信息
    List<Employee> findEmpsByDeptId(int departmentId);

    // 删除某个员工
    String deleteEmpById(int empId);

}
