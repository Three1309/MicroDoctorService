package com.zhuolang.service;

import com.zhuolang.model.Doctor;

import java.util.List;

/**
 * Created by wunaifu on 2017/4/27.
 */
public interface IDoctorService {

    void addDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    public boolean updateRegiDoctor(int id);

    List<Doctor> findAllDoctor();

    public List<Doctor> findAllnoDoctors();

    List<Doctor> findDoctorById(int id);

    List<Doctor> findDoctorByDoctorId(int doctorId);

    void deleteDoctor(List<Doctor> findDoctor);


}
