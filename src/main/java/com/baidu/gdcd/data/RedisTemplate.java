package com.baidu.gdcd.data;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

import com.baidu.gdcd.entity.Employee;
import com.baidu.gdcd.util.RedisUtil;
import com.baidu.gdcd.util.SerializeUtil;

public class RedisTemplate {

    // 设置缓存的有效时长
    private static final int EFFECTIVE_SECONDS = 60;

    /**
     * 从redis获取单个员工信息
     * 
     * @return
     */
    public static Employee getEmployee(int empId) {
        String key = RedisKeys.EMP.getKey() + empId;
        Employee emp = null;
        Jedis redis = RedisUtil.getRedisInstance();
        if (redis.exists(key.getBytes())) {
            byte[] bytes = redis.get(key.getBytes());
            emp = SerializeUtil.deserialize(bytes, Employee.class);
        }
        redis.disconnect();
        return emp;
    }

    /**
     * 把Employee对象序列化到redis
     * 
     * @param employee
     */
    public static void setEmployee(Employee employee) {
        Jedis redis = RedisUtil.getRedisInstance();
        byte[] bytes = SerializeUtil.serialize(employee);
        String key = RedisKeys.EMP.getKey() + employee.getEmpId();
        redis.set(key.getBytes(), bytes);
        // 设置有效时长 ms
        redis.expire(key.getBytes(), EFFECTIVE_SECONDS);
        redis.disconnect();
    }

    /**
     * 从redis获取员工列表
     * 
     * @param key
     * @return
     */
    public static List<Employee> getEmployeeList(int deptId) {
        String key = RedisKeys.DEPT.getKey() + deptId;
        List<Employee> empList = new ArrayList<Employee>();
        Jedis redis = RedisUtil.getRedisInstance();
        if (redis.exists(key.getBytes())) {
            byte[] bytes = redis.get(key.getBytes());
            empList = SerializeUtil.deserializeList(bytes, Employee.class);
        }
        redis.disconnect();
        return empList;
    }

    /**
     * 序列化员工信息到redis
     * 
     * @param key
     * @param empList
     */
    public static void setEmployeeList(int deptId, List<Employee> empList) {
        String key = RedisKeys.DEPT.getKey() + deptId;
        Jedis redis = RedisUtil.getRedisInstance();
        byte[] bytes = SerializeUtil.serializeList(empList);
        redis.set(key.getBytes(), bytes);
        redis.expire(key.getBytes(), EFFECTIVE_SECONDS);
        redis.disconnect();
    }

    /**
     * 移除redis中的数据
     * 
     * @param key
     * @return
     */
    public static long removeRedisKey(String key) {
        long res = -99;
        Jedis redis = RedisUtil.getRedisInstance();
        res = redis.del(key);
        return res;
    }

}
