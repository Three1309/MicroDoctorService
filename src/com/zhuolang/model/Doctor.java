package com.zhuolang.model;

import javax.persistence.*;

/**
 * Created by wunaifu on 2017/4/27.
 */
@Entity
@Table(name = "doctor_tab")
public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name="doctorId",length=11)
    private int doctorId;

    @Column(name="hospital",length=225)
    private String hospital;

    @Column(name="office",length=225)
    private String office;

    @Column(name="amount",length=11)
    private int amount;

    @Column(name="likenum",length=11)
    private int likenum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLikenum() {
        return likenum;
    }

    public void setLikenum(int likenum) {
        this.likenum = likenum;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", hospital='" + hospital + '\'' +
                ", office='" + office + '\'' +
                ", amount=" + amount +
                ", likenum=" + likenum +
                '}';
    }
}
