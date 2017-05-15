package com.zhuolang.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Entity
@Table(name = "send_tab")
public class ShareSend {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int sendId;

    @Column(name = "userId", length = 11)
    private int userId;

    @Column(name = "sendContent")
    private String sendContent;

    @Column(name = "sendTime")
    private Date sendTime;

    @Column(name = "likesAmount", length = 11)
    private int likesAmount;

    @Column(name = "discussAmount", length = 11)
    private int discussAmount;

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getLikesAmount() {
        return likesAmount;
    }

    public void setLikesAmount(int likesAmount) {
        this.likesAmount = likesAmount;
    }

    public int getDiscussAmount() {
        return discussAmount;
    }

    public void setDiscussAmount(int discussAmount) {
        this.discussAmount = discussAmount;
    }

    @Override
    public String toString() {
        return "ShareSend{" +
                "sendId=" + sendId +
                ", userId=" + userId +
                ", sendContent='" + sendContent + '\'' +
                ", sendTime=" + sendTime +
                ", likesAmount=" + likesAmount +
                ", discussAmount=" + discussAmount +
                '}';
    }
}
