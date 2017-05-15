package com.zhuolang.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Entity
@Table(name = "discuss_tab")
public class ShareDiscuss {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "sendId", length = 11)
    private int sendId;

    @Column(name = "discussId", length = 11)
    private int discussId;

    @Column(name = "discussContent")
    private String discussContent;

    @Column(name = "discussTime")
    private Date discussTime;

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

    public int getDiscussId() {
        return discussId;
    }

    public void setDiscussId(int discussId) {
        this.discussId = discussId;
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent;
    }

    public Date getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(Date discussTime) {
        this.discussTime = discussTime;
    }

    @Override
    public String toString() {
        return "ShareDiscuss{" +
                "id=" + id +
                ", sendId=" + sendId +
                ", discussId=" + discussId +
                ", discussContent='" + discussContent + '\'' +
                ", discussTime=" + discussTime +
                '}';
    }
}
