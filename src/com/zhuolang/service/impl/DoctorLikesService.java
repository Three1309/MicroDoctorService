package com.zhuolang.service.impl;

import com.zhuolang.dao.IDoctorLikesDao;
import com.zhuolang.model.DoctorLikes;
import com.zhuolang.service.IDoctorLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Service("doctorLikesService")
public class DoctorLikesService implements IDoctorLikesService {
    @Autowired
    IDoctorLikesDao doctorLikesDao;

    @Override
    public int addDoctorLikes(DoctorLikes doctorLikes){
        return (int) doctorLikesDao.save(doctorLikes);
    }

    /**
     * 通过doctorId 和 appointmentId 找是否点赞，找不到，返回false
     */
    @Override
    public boolean findisOrnotLike(int doctorId,int appointmentId) {
        String hql="from DoctorLikes where doctorId=? and apotmid=?";
        DoctorLikes doctorLikes = doctorLikesDao.get(hql, new Object[]{doctorId,appointmentId});
        if (doctorLikes == null) {//为空就是没有，找不到，返回false
            return false;
        } else {
            return true;
        }
    }

    /**
     * 如果点赞则删除
     * @param doctorId
     * @return
     */
    @Override
    public boolean deleteDoctorLikes(int doctorId,int appointmentId) {
        String hql = "delete Doctor where doctorId=? and apotmid=?";
        List<Object> object = new ArrayList<Object>();
        object.add(doctorId);
        if (doctorLikesDao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }

    }

}
