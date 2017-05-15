package com.zhuolang.service.impl;

import com.zhuolang.dao.impl.DoctorDao;
import com.zhuolang.dao.impl.ShareLikesDao;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.model.ShareLikes;
import com.zhuolang.service.IShareLikesService;
import com.zhuolang.service.IShareSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
@Service("shareLikesService")
public class ShareLikesService implements IShareLikesService {

    // 注入服务层，操作数据持久化

    @Autowired
    UserDao userDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    ShareLikesDao shareLikesDao;

    /**
     * 点赞
     * @param shareLikes
     */
    @Override
    public void addShareLikes(ShareLikes shareLikes) {
        try {
            shareLikesDao.save(shareLikes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id取消赞
     * @param id
     * @return
     */
    @Override
    public boolean deleteshareLikesById(int id) {
        String hql="from ShareLikes where id=?";
        List<Object> object = new ArrayList<Object>();

        object.add(id);
        if(shareLikesDao.executeHql(hql, object)>0){
            return true;
        } else {
            return false;
        }
    }

}

