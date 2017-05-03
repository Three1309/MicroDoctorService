package com.zhuolang.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Entity
@Table(name = "doclikes_tab")
public class DoctorLikes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "patientId", length = 11)
    private int appointmentId;

    @Column(name = "patientId", length = 11)
    private int doctorId;

    @Column(name = "seeTime")
    private Date likesTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getLikesTime() {
        return likesTime;
    }

    public void setLikesTime(Date likesTime) {
        this.likesTime = likesTime;
    }

    @Override
    public String toString() {
        return "DoctorLikes{" +
                "id=" + id +
                ", appointmentId=" + appointmentId +
                ", doctorId=" + doctorId +
                ", likesTime=" + likesTime +
                '}';
    }
}
