package com.zhuolang.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Entity
@Table(name = "collect_tab")
public class ShareCollect {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int collectId;

    @Column(name = "sendId", length = 11)
    private int sendId;

    @Column(name = "collectorId", length = 11)
    private int collectorId;

    @Column(name = "collectTime")
    private Date collectTime;

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "ShareCollect{" +
                "collectId=" + collectId +
                ", sendId=" + sendId +
                ", collectorId=" + collectorId +
                ", collectTime=" + collectTime +
                '}';
    }
}
