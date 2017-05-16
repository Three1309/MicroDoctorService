package com.zhuolang.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Entity
@Table(name = "likes_tab")
public class ShareLikes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "sendId", length = 11)
    private int sendId;

    @Column(name = "likeserId", length = 11)
    private int likeserId;

    @Column(name = "likesTime")
    private Date likesTime;

    @Override
    public String toString() {
        return "ShareLikes{" +
                "id=" + id +
                ", sendId=" + sendId +
                ", likeserId=" + likeserId +
                ", likesTime=" + likesTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getLikeserId() {
        return likeserId;
    }

    public void setLikeserId(int likeserId) {
        this.likeserId = likeserId;
    }

    public Date getLikesTime() {
        return likesTime;
    }

    public void setLikesTime(Date likesTime) {
        this.likesTime = likesTime;
    }
}
