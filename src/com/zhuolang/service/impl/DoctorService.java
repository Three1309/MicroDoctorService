package com.zhuolang.service.impl;

import com.zhuolang.dao.IDoctorDao;
import com.zhuolang.dao.impl.BaseDao;
import com.zhuolang.model.Doctor;
import com.zhuolang.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wunaifu on 2017/4/27.
 */
@Service("doctorService")
public class DoctorService implements IDoctorService{

    @Autowired
    IDoctorDao dao;

    @Override
    public void addDoctor(Doctor doctor) {
        dao.save(doctor);
    }
    @Override
    public void updateDoctor(Doctor doctor) {
        dao.update(doctor);
    }

    @Override
    public boolean updateRegiDoctor(int id) {
        String hql = "update Doctor set amount=0 where doctorId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(id);
        if (dao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Doctor> findAllDoctor() {
        String hql = "from Doctor";
        return dao.find(hql);
    }

    @Override
    public List<Doctor> findDoctorById(int id) {
        String hql="from Doctor where id=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        List<Doctor> doctorList = dao.find(hql,idObject);
        return doctorList;
    }

    @Override
    public List<Doctor> findDoctorByDoctorId(int doctorId) {
        String hql="from Doctor where doctorId=?";

        List<Object> doctorIdObject = new ArrayList<Object>();
        doctorIdObject.add(doctorId);
        List<Doctor> doctList = dao.find(hql,doctorIdObject);
        return doctList;
    }

    @Override
    public List<Doctor> findAllnoDoctors() {
        String hql="from Doctor where amount=-1";
        List<Doctor> doctList = dao.find(hql);
        return doctList;
    }

//	@Override
//	public List<Doctor> findDoctor(String hql) {
//		return dao.find(hql);
//	}

    @Override
    public void deleteDoctor(List<Doctor> findDoctor) {
        List<Doctor> list=findDoctor;
        if (list != null && list.size() > 0) {
            for(int i=0;i<list.size();i++) {
                dao.delete(list.get(i));
            }
        }

    }



}
