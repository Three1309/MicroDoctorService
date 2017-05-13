package com.zhuolang.service.impl;

import com.zhuolang.dao.IDoctorDao;
import com.zhuolang.dao.IUserDao;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.dto.DoctorDto;
import com.zhuolang.model.Doctor;
import com.zhuolang.model.User;
import com.zhuolang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wnf on 2017/4/12.
 */
@Service("userService")
public class UserService implements IUserService {
    // 注入服务层，操作数据持久化
    @Autowired
    IUserDao userDao;

    @Autowired
    IDoctorDao doctorDao;
    /**
     *业务逻辑操作
     * 添加数据
     */
    @Override
    public int addUser(User user){
        return (int) userDao.save(user);
    }

    /**
     * 查找phone和password，有则将数据存到userList中
     */
    @Override
    public List<User> userLogin(User user) {
        String hql = "from User where phone=? and password=?";
        List<Object> object = new ArrayList<Object>();
        object.add(user.getPhone());
        object.add(user.getPassword());
        List<User> userList = userDao.find(hql, object);
        return userList;

    }
    /**
     * 通过phone找用户，找不到，返回false
     */
    @Override
    public boolean findPhone(String phone) {
        String hql="from User where phone=?";
        User user = userDao.get(hql, new Object[]{phone});
        if (user == null) {//为空就是没有，找不到，返回false
            return false;
        } else {
            return true;
        }
    }

    /**
     * 通过phone找用户，找到返回用户数据
     */
    @Override
    public List<User> findByPhone(String phone) {
        String hql="from User where phone=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(phone);
        List<User> userList = userDao.find(hql, idObject);
        return userList;
    }

    /**
     * 通过id找用户，找到返回用户数据
     */
    @Override
    public String findById(int id) {
        String hql="from User where id=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        List<User> userList = userDao.find(hql, idObject);
        if (userList != null && userList.size() > 0) {
            return userList.get(0).getName();
        }else
            return "";
    }

    /**
     * 通过id找用户，找到返回用户所有数据
     */
    @Override
    public List<User> findUserDataById(int id) {
        String hql="from User where id=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        List<User> userList = userDao.find(hql, idObject);
        if (userList != null && userList.size() > 0) {
            return userList;
        }else
            return null;
    }


    /**
     * 修改密码
     * @param id
     * @param oldPW
     * @param newPW
     * @return
     */
    @Override
    public boolean updatePassword(int id, String oldPW, String newPW) {
        String hql1 = "from User where id=?";
        User user = userDao.get(hql1, new Object[]{id});
        String password = user.getPassword();
        if (oldPW.equals(password)) {
            String hql2 = "update User set password=? where id=?";
            List<Object> object = new ArrayList<Object>();
            object.add(newPW);
            object.add(id);
            userDao.executeHql(hql2, object);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改手机
     * @param id
     * @param phone
     * @return
     */
    @Override
    public boolean updatePhone(int id, String phone) {
        if (!findPhone(phone)){
            String hql2 = "update User set phone=? where id=?";
            List<Object> object = new ArrayList<Object>();
            object.add(phone);
            object.add(id);
            userDao.executeHql(hql2, object);
            return true;
        }
        else
            return false;

    }

    /**
     * 更新用户信息，更新完成返回true
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
//        userDao.update(user);
        String hql = "update User set nickname=?,name=?,gender=?,address=?,signature=?,introduction=?,age=? where id=?";
        List<Object> object = new ArrayList<Object>();
        object.add(user.getNickname());
        object.add(user.getName());
        object.add(user.getGender());
        object.add(user.getAddress());
        object.add(user.getSignature());
        object.add(user.getIntroduction());
        object.add(user.getAge());
        object.add(user.getId());
        if (userDao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUserType(int id) {
//        userDao.update(user);
        String hql = "update User set type=1 where id=?";
        List<Object> object = new ArrayList<Object>();
        object.add(id);
        if (userDao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public List<User> findUserByType(int type) {
        String hql = "from User where type=?";
        return userDao.find(hql, new Object[]{type});
    }

    @Override
    public List<DoctorDto> findDoctorDto() {
        List<User> userList = findUserByType(1);
        List<DoctorDto> doctorDtoList = new ArrayList<DoctorDto>();
        if (userList != null && userList.size() > 0) {
            String hql = "from Doctor where doctorId=?";
            for (User user : userList) {
                DoctorDto dto=new DoctorDto();
                dto.setId(user.getId());
                dto.setName(user.getName());
                dto.setNickname(user.getNickname());
                dto.setPassword(user.getPassword());
                dto.setGender(user.getGender());
                dto.setAge(user.getAge());
                dto.setPhone(user.getPhone());
                dto.setAddress(user.getAddress());
                dto.setSignature(user.getSignature());
                dto.setIntroduction(user.getIntroduction());

                Doctor doctor = doctorDao.get(hql, new Object[]{user.getId()});
                if (doctor != null) {
                    dto.setHospital(doctor.getHospital());
                    dto.setOffice(doctor.getOffice());
                    dto.setAmount(doctor.getAmount());
                    dto.setLikenum(doctor.getLikenum());
                }
                doctorDtoList.add(dto);
            }
        }

        return doctorDtoList;
    }


    @Override
    public List<DoctorDto> findDoctorDtoByOffandHosp(String office,String hospital) {
        String hql = "from Doctor where office=? and hospital=? and amount!=-1";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(office);
        idObject.add(hospital);
        List<Doctor> doctorList = doctorDao.find(hql, idObject);

        List<DoctorDto> doctorDtoList = new ArrayList<DoctorDto>();

        if (doctorList != null && doctorList.size() > 0) {
            for (Doctor doctor : doctorList) {
                DoctorDto dto=new DoctorDto();

                dto.setId(doctor.getDoctorId());
                dto.setHospital(doctor.getHospital());
                dto.setOffice(doctor.getOffice());
                dto.setAmount(doctor.getAmount());
                dto.setLikenum(doctor.getLikenum());

                List<User> userList = findUserDataById(doctor.getDoctorId());
                if (userList.size() > 0) {
                    dto.setName(userList.get(0).getName());
                    dto.setNickname(userList.get(0).getNickname());
                    dto.setPassword(userList.get(0).getPassword());
                    dto.setGender(userList.get(0).getGender());
                    dto.setAge(userList.get(0).getAge());
                    dto.setPhone(userList.get(0).getPhone());
                    dto.setAddress(userList.get(0).getAddress());
                    dto.setSignature(userList.get(0).getSignature());
                    dto.setIntroduction(userList.get(0).getIntroduction());
                }
                doctorDtoList.add(dto);
            }
        }

        return doctorDtoList;
    }
}
