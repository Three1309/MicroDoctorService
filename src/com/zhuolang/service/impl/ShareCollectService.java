package com.zhuolang.service.impl;

import com.zhuolang.dao.impl.DoctorDao;
import com.zhuolang.dao.impl.ShareCollectDao;
import com.zhuolang.dao.impl.ShareSendDao;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.model.ShareCollect;
import com.zhuolang.model.ShareSend;
import com.zhuolang.service.IShareCollectService;
import com.zhuolang.service.IShareSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
@Service("shareCollectService")
public class ShareCollectService implements IShareCollectService {

    // 注入服务层，操作数据持久化

    @Autowired
    UserDao userDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    ShareCollectDao shareCollectDao;

    /**
     *添加收藏
     */
    @Override
    public void addShareCollect(ShareCollect shareCollect) {
        try {
            shareCollectDao.save(shareCollect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据sendId删除收藏
     * @param sendId
     * @return
     */
    @Override
    public boolean deleteShareCollectBySendId(int sendId) {
        String hql="delete from ShareCollect where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(sendId);
        if(shareCollectDao.executeHql(hql, object)>0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据collectId删除收藏
     * @param collectId
     * @return
     */
    @Override
    public boolean deleteShareCollectByCollectId(int collectId) {
        String hql="delete from ShareCollect where collectId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(collectId);
        if(shareCollectDao.executeHql(hql, object)>0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据sendId、collectorId查找是否已收藏，并返回该条数据
     * @param sendId
     * @param collectorId
     * @return
     */
    @Override
    public List<ShareCollect> findCollectsBySendIdAndCollectorId(int sendId,int collectorId) {
        String hql="from ShareCollect where sendId=? and collectorId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(sendId);
        object.add(collectorId);
        List<ShareCollect> shareCollectList = shareCollectDao.find(hql, object);
        if (shareCollectList != null && shareCollectList.size() > 0) {
            return shareCollectList;
        } else {
            return null;
        }
    }

}

