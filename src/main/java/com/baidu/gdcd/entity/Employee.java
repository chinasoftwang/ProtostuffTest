package com.baidu.gdcd.entity;

import java.util.Date;

public class Employee {

    private String empName; // 员工姓名
    private int empId; // 员工ID
    private String sex; // 性别
    private Date birthday; // 出生日期
    private int departmentId; // 部门ID

    public Employee() {
    }

    public Employee(String empName, int empId, String sex, Date birthday,
            int departmentId) {
        this.empName = empName;
        this.empId = empId;
        this.sex = sex;
        this.birthday = birthday;
        this.departmentId = departmentId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee [empName=" + empName + ", empId=" + empId + ", sex="
                + sex + ", birthday=" + birthday + ", departmentId="
                + departmentId + "]";
    }

}
