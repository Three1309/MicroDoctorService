package com.zhuolang.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wunaifu on 2017/5/2.
 */

@Entity
@Table(name = "appointment_tab")
public class Appointment {
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "patientId", length = 11)
    private int patientId;//病人ID

    @Column(name = "doctorId", length = 11)
    private int doctorId;//医师ID

    @Column(name = "seeTime")
    private Date seeTime;//预约了去就诊日期时间

    @Column(name = "disease")
    private String disease;//病症

    @Column(name = "dateTime")
    private Date dateTime;//预约时间、

    @Column(name = "diagnose")
    private String diagnose;//医生诊断

    @Column(name = "dNumber")
    private int dNumber;

    @Column(name = "doctorSay")
    private String doctorSay;//病症

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getSeeTime() {
        return seeTime;
    }

    public void setSeeTime(Date seeTime) {
        this.seeTime = seeTime;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public int getdNumber() {
        return dNumber;
    }

    public void setdNumber(int dNumber) {
        this.dNumber = dNumber;
    }

    public String getDoctorSay() {
        return doctorSay;
    }

    public void setDoctorSay(String doctorSay) {
        this.doctorSay = doctorSay;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", seeTime=" + seeTime +
                ", disease='" + disease + '\'' +
                ", dateTime=" + dateTime +
                ", diagnose='" + diagnose + '\'' +
                ", dNumber=" + dNumber +
                ", doctorSay='" + doctorSay + '\'' +
                '}';
    }
}