package com.zhuolang.service;

import com.zhuolang.dto.AppointmentDto;
import com.zhuolang.model.Appointment;

import java.util.Date;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
public interface IAppointmentService {
    void addAppointment(Appointment appointment);

    int CountAppByDIdAndDate(int doctorId, Date date);

    List<AppointmentDto> findByDocId(int doctorId);

    void updateDiagnose(int id, String diagnose);

    void updateAppointment(Appointment appointment);

    public void updateDoctorSay(int id, String doctorSay);

    List<Appointment> findAllAppointment();

    List<Appointment> findAppointmentById(int id);

    List<AppointmentDto> findByPatId(int id);

    List<Appointment> findAppByDId(int doctorId, Date date);

    void deleteAppointment(List<Appointment> findAppointment);
}
