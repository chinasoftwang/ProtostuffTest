package com.baidu.gdcd.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.baidu.gdcd.entity.Employee;
import com.baidu.gdcd.service.IEmpService;

public class EmpServiceImplTest {

    @Test
    public void testFindEmpById() {
        int empId = 2;
        IEmpService empService = new EmpServiceImpl();
        Employee emp = empService.findEmpById(empId);
        if (emp != null) {
            assertEquals(empId, emp.getEmpId());
        }
    }

    @Test
    public void testFindEmpsByDeptId() {
        int deptId = 1;
        IEmpService empService = new EmpServiceImpl();
        List<Employee> empList = empService.findEmpsByDeptId(deptId);
        assertEquals(empList.size(), 4);
        for (Employee emp : empList) {
            assertEquals(emp.getDepartmentId(), 1);
        }
    }

    @Test
    public void testDeleteEmpById() {
        int empId = 2;
        IEmpService empService = new EmpServiceImpl();
        String result = empService.deleteEmpById(empId);
        assertNotNull(result);
    }

}
