package com.zhuolang.service;

import com.zhuolang.dto.DoctorDto;
import com.zhuolang.model.Doctor;

import java.util.List;

/**
 * Created by wunaifu on 2017/4/27.
 */
public interface IDoctorService {

    void addDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    public boolean updateRegiDoctor(int id);

    public boolean updateRegiDoctorInfo(int id, String hospital, String office);

    List<Doctor> findAllDoctor();

    public List<DoctorDto> findAllnoDoctors();

    List<Doctor> findDoctorById(int id);

    List<Doctor> findDoctorByDoctorId(int doctorId);

    void deleteDoctor(List<Doctor> findDoctor);

    public boolean deleteDoctorByDoctorId(int doctorId);


}
