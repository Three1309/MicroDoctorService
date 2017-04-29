package com.zhuolang.test;

import com.zhuolang.model.User;
import com.zhuolang.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wnf on 2017/4/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserTest {
    @Autowired
    IUserService service;
    @Test
    public void testAdd() {
        System.out.println("service:"+service);
        User user = new User();
        user.setPhone("18219111625");
        user.setPassword("123456");
        user.setName("吴乃福");
        user.setNickname("夜雨");
        user.setAge(22);
        user.setGender(0);
        user.setAddress("廉江");
        user.setSignature("道不同，不相为谋");
        user.setIntroduction("大家好，我叫吴乃福jaslfjlajflajsfajsd");
        user.setType(0);

        System.out.println(service.addUser(user));
    }
}
