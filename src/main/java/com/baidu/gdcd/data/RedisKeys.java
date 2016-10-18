package com.baidu.gdcd.data;

public enum RedisKeys {

    // 定义redis缓存时的key值
    EMP("emp_", 1), DEPT("dept_", 2);

    private String name;
    private int index;

    private RedisKeys(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getKey() {
        return this.name;
    }
}
