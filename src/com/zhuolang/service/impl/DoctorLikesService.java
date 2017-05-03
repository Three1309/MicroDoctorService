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
        String hql="from DoctorLikes where doctorId=? and apotmId=?";
//        DoctorLikes doctorLikes = doctorLikesDao.find(hql, new Object[]{doctorId,appointmentId});
        List<Object> object = new ArrayList<Object>();
        object.add(doctorId);
        object.add(appointmentId);
        List<DoctorLikes> doctorLikes = doctorLikesDao.find(hql, object);
        if (doctorLikes != null && doctorLikes.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 如果点赞则删除
     * @param doctorId
     * @return
     */
    @Override
    public boolean deleteDoctorLikes(int doctorId,int appointmentId) {
        String hql="from DoctorLikes where doctorId=? and apotmId=?";
//        DoctorLikes doctorLikes = doctorLikesDao.find(hql, new Object[]{doctorId,appointmentId});
        List<Object> object = new ArrayList<Object>();
        object.add(doctorId);
        object.add(appointmentId);
        List<DoctorLikes> doctorLikes = doctorLikesDao.find(hql, object);
        if (doctorLikes != null && doctorLikes.size() > 0) {
            doctorLikesDao.delete(doctorLikes.get(0));
            return true;
        } else {
            return false;
        }
    }

}
