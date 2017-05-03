package com.zhuolang.service;

import com.zhuolang.dto.DoctorDto;
import com.zhuolang.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wunaifu on 2017/4/12.
 */
//@Service
public interface IUserService {

    int addUser(User user) ;

    List<User> userLogin(User user);

    boolean findPhone(String phone);

    List<User> findByPhone(String phone);

    boolean updatePassword(int id, String oldPW, String newPW);

    boolean updateUser(User user);

    List<DoctorDto> findDoctorDto();

    public String findById(int id);

    boolean updatePhone(int id, String phone);

    public boolean updateUserType(int id);

    List<User> findUserByType(int type);

}
