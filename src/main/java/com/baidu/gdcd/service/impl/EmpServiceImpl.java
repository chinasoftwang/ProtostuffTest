package com.baidu.gdcd.service.impl;

import java.util.List;

import com.baidu.gdcd.data.Employees;
import com.baidu.gdcd.data.RedisKeys;
import com.baidu.gdcd.data.RedisTemplate;
import com.baidu.gdcd.entity.Employee;
import com.baidu.gdcd.service.IEmpService;

public class EmpServiceImpl implements IEmpService {

    public Employee findEmpById(int empId) {
        // 先从缓存中去找员工编号是empId的员工信息
        Employee emp = RedisTemplate.getEmployee(empId);
        if (emp != null) {
            return emp;
        }
        // 缓存中没有则从db获取
        Employees emps = new Employees(5);
        List<Employee> empList = emps.getEmployeeList();
        for (Employee employee : empList) {
            if (employee.getEmpId() == empId) {
                // 如果在db找到则加入缓存
                RedisTemplate.setEmployee(employee);
                return employee;
            }
        }
        return null;
    }

    public List<Employee> findEmpsByDeptId(int departmentId) {
        List<Employee> empList = null;
        // 优先取缓存
        empList = RedisTemplate.getEmployeeList(departmentId);
        if (empList != null && empList.size() > 0) {
            return empList;
        }
        // 然后db
        Employees emps = new Employees(7);
        List<Employee> tempList = emps.getEmployeeList();
        for (Employee emp : tempList) {
            if (emp.getDepartmentId() == departmentId) {
                empList.add(emp);
            }
        }
        // 最后加入缓存
        RedisTemplate.setEmployeeList(departmentId, empList);
        return empList;
    }

    /**
     * 移除缓存数据 1 成功 0 失败
     */
    public String deleteEmpById(int empId) {
        // 先移除List列表
        Employees emps = new Employees(5);
        List<Employee> empList = emps.getEmployeeList();
        empList.remove(empId - 1);
        // 然后移除缓存数据
        String key = RedisKeys.EMP.getKey() + empId;
        long res = RedisTemplate.removeRedisKey(key);
        return res == 1 ? "移除成功" : "移除失败/员工不存在";
    }

}
