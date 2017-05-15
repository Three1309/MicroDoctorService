package com.zhuolang.service.impl;

import com.zhuolang.dao.impl.DoctorDao;
import com.zhuolang.dao.impl.ShareSendDao;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.model.ShareSend;
import com.zhuolang.service.IShareSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
@Service("shareSendService")
public class ShareSendService implements IShareSendService {

    // 注入服务层，操作数据持久化

    @Autowired
    UserDao userDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    ShareSendDao shareSendDao;

    /**
     *添加分享
     */
    @Override
    public void addShareSend(ShareSend shareSend) {
        try {
            shareSendDao.save(shareSend);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据sendId删除分享
     * @param sendId
     * @return
     */
    @Override
    public boolean deleteShareSendBySendId(int sendId) {
        String hql="from ShareSend where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(sendId);
        if(shareSendDao.executeHql(hql, object)>0){
            return true;
        } else {
            return false;
        }
    }

}

