package com.ljnt.redis.controller;

import com.ljnt.redis.Entity.User;
import com.ljnt.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @ Program       :  com.ljnt.redis.controller.index
 * @ Description   :
 * @ Author        :  lj
 * @ CreateDate    :  2020-2-6 22:27
 */
@RestController
public class IndexController {
    @Autowired
    RedisUtil redisUtil;
    @RequestMapping("/set")
    public boolean set(String name, String age){
        User user=new User();
        user.setId((long) new Random().nextInt());
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        user.setGuid("0");
        user.setCreateTime(new Date());
        return redisUtil.hset("jedis", name,user);
    }

    @RequestMapping("/get")
    public Map<Object, Object> redisget(){
        return redisUtil.hmget("jedis");
    }
    @RequestMapping("/expire")
    public boolean expire(String key){
        return redisUtil.expire(key,60);
    }

}
