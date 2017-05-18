package com.zhuolang.service;

import com.zhuolang.dto.AppointmentDto;
import com.zhuolang.dto.ShareDto;
import com.zhuolang.dto.ShareHouseDto;
import com.zhuolang.model.Appointment;
import com.zhuolang.model.ShareSend;

import java.util.Date;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
public interface IShareSendService {

    public List<ShareDto> findAllShare(int userId);

    public List<ShareDto> findMyShareHistory(int userId);

    public List<ShareDto> findMyCollectShares(int userId);

    public List<ShareDto> findMyDiscussShares(int userId);

    public ShareHouseDto findUserShareInfo(int userId);

    public void addShareSend(ShareSend shareSend);

    public boolean deleteShareSendBySendId(int sendId);

    public List<ShareSend> findShareSendBySendId(int sendId);

    public boolean updateLikesAmount(int sendId, int likesAmount);

    public boolean updateDiscussAmount(int sendId, int discussAmount);

    public boolean updateCollectAmount(int sendId, int collectAmount);

}
