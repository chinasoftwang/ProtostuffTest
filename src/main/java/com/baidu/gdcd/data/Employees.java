package com.baidu.gdcd.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baidu.gdcd.entity.Employee;

public class Employees {

    // 指定员工个数,默认是2
    int count = 2;

    // 性别
    String[] sexs = { "男", "女", "其他" };

    public Employees() {

    }

    public Employees(int count) {
        if (count > 2) {
            this.count = count;
        }
    }

    /**
     * 封装员工List 假设List数据来源于DB
     * 
     * @return
     */
    public List<Employee> getEmployeeList() {
        List<Employee> empList = new ArrayList<Employee>();
        for (int i = 0; i < count; i++) {
            int deptId = i % 2 + 1;
            int index = Math.random() > 0.5 ? 1 : 0;
            Employee emp = new Employee("Test" + (i + 1), i + 1, sexs[index],
                    new Date(), deptId);
            empList.add(emp);
        }
        return empList;
    }

}
