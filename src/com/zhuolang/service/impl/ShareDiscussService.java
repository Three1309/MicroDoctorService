package com.zhuolang.service.impl;

import com.zhuolang.dao.impl.DoctorDao;
import com.zhuolang.dao.impl.ShareDiscussDao;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.model.ShareDiscuss;
import com.zhuolang.service.IShareDiscussService;
import com.zhuolang.service.IShareSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
@Service("shareDiscussService")
public class ShareDiscussService implements IShareDiscussService {

    // 注入服务层，操作数据持久化

    @Autowired
    UserDao userDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    ShareDiscussDao shareDiscussDao;


    /**
     * 添加评论
     * @param shareDiscuss
     */
    @Override
    public void addShareDiscuss(ShareDiscuss shareDiscuss) {
        try {
            shareDiscussDao.save(shareDiscuss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @Override
    public boolean deleteShareSendById(int id) {
        String hql="from ShareDiscuss where id=?";
        List<Object> object = new ArrayList<Object>();

        object.add(id);
        if(shareDiscussDao.executeHql(hql, object)>0){
            return true;
        } else {
            return false;
        }
    }

}

