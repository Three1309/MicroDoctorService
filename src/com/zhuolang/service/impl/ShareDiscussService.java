package com.zhuolang.service.impl;

import com.zhuolang.dao.impl.DoctorDao;
import com.zhuolang.dao.impl.ShareDiscussDao;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.dto.ShareDiscussDto;
import com.zhuolang.model.ShareDiscuss;
import com.zhuolang.model.User;
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
     * 查找帖子的所有评论
     * @return
     */
    @Override
    public List<ShareDiscussDto> findAllShareDiscuss(int sendId) {
        List<ShareDiscussDto> discussDtoList = new ArrayList<ShareDiscussDto>();
        String hql = "from User where id=?";

        String hqlShareDiscuss="from ShareDiscuss where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(sendId);
        List<ShareDiscuss> shareDiscussList = shareDiscussDao.find(hqlShareDiscuss,object);

        if (shareDiscussList != null && shareDiscussList.size() > 0) {
            for (ShareDiscuss shareDiscuss : shareDiscussList) {
                ShareDiscussDto shareDiscussDto = new ShareDiscussDto();

                shareDiscussDto.setId(shareDiscuss.getId());
                shareDiscussDto.setSendId(shareDiscuss.getSendId());
                shareDiscussDto.setUserId(shareDiscuss.getDiscusserId());
                shareDiscussDto.setDiscussContent(shareDiscuss.getDiscussContent());
                shareDiscussDto.setDiscussTime(shareDiscuss.getDiscussTime());

                User user = userDao.get(hql, new Object[]{shareDiscuss.getDiscusserId()});
                if (user != null) {
                    shareDiscussDto.setUserName(user.getName());
                    shareDiscussDto.setUserNickName(user.getNickname());
                    shareDiscussDto.setUserType(user.getType());
                }
                discussDtoList.add(shareDiscussDto);
            }
        }
        return discussDtoList;
    }


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
     * 根据sendId删除评论
     * @param sendId
     * @return
     */
    @Override
    public boolean deleteShareDiscussBySendId(int sendId) {
        String hql="delete from ShareDiscuss where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(sendId);
        if(shareDiscussDao.executeHql(hql, object)>0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @Override
    public boolean deleteShareDiscussById(int id) {
        String hql="delete from ShareDiscuss where id=?";
        List<Object> object = new ArrayList<Object>();

        object.add(id);
        if(shareDiscussDao.executeHql(hql, object)>0){
            return true;
        } else {
            return false;
        }
    }

}

