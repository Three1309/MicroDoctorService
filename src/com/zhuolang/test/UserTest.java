package com.zhuolang.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuolang.model.Doctor;
import com.zhuolang.model.User;
import com.zhuolang.service.IDoctorService;
import com.zhuolang.service.IUserService;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wnf on 2017/4/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserTest {
    @Autowired
    IUserService service;

    @Autowired
    IDoctorService doctorService;

    @Test
    public void testAdd() {
        System.out.println("service:" + service);
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

    @Test
    public void findAllOfficeTest() throws IOException {
        List<Doctor> doctorList = doctorService.findAllDoctor();
        List<String> officeList = new ArrayList<String>();

        if (doctorList != null && doctorList.size() > 0) {
            for (int i = 0; i < doctorList.size(); i++) {
                for (int j=i+1;j<doctorList.size();) {
                    if ( doctorList.get(i).getOffice().equals( doctorList.get(j).getOffice() ) ) {
                        doctorList.remove(j);
                    }else {
                        j++;
                    }
                }
            }
        }
        if (doctorList != null && doctorList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (Doctor a : doctorList) {
                JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                jsonArray.add(jsonObj);

            }
            System.out.println(jsonArray.toString());
        }else {
            System.out.println();
        }
    }

}
