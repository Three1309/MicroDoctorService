package com.zhuolang.service;

import com.zhuolang.dto.AppointmentDto;
import com.zhuolang.model.Appointment;
import com.zhuolang.model.ShareSend;

import java.util.Date;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
public interface IShareSendService {

    public void addShareSend(ShareSend shareSend);

    public boolean deleteShareSendBySendId(int sendId);

}
