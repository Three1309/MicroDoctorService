package com.zhuolang.service.impl;

import com.zhuolang.dao.impl.DoctorDao;
import com.zhuolang.dao.impl.ShareSendDao;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.dto.ShareDto;
import com.zhuolang.model.ShareCollect;
import com.zhuolang.model.ShareLikes;
import com.zhuolang.model.ShareSend;
import com.zhuolang.model.User;
import com.zhuolang.service.IShareCollectService;
import com.zhuolang.service.IShareLikesService;
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
    @Autowired
    IShareCollectService shareCollectService;
    @Autowired
    IShareLikesService shareLikesService;

    /**
     * 查找所有帖子，最新的在前面
     * @return
     */
    @Override
    public List<ShareDto> findAllShare(int userId) {
        List<ShareDto> shareDtoList = new ArrayList<ShareDto>();
        String hqlShareSend="from ShareSend";
        String hql = "from User where id=?";
        List<ShareSend> shareSends = shareSendDao.find(hqlShareSend);
        if (shareSends != null && shareSends.size() > 0) {
            List<ShareDto> shareDtoS = new ArrayList<ShareDto>();
            shareDtoS.clear();
            for (ShareSend shareSend : shareSends) {
                ShareDto shareDto = new ShareDto();
                shareDto.setSendId(shareSend.getSendId());
                shareDto.setSendTitle(shareSend.getSendTitle());
                shareDto.setSendContent(shareSend.getSendContent());
                shareDto.setSendTime(shareSend.getSendTime());
                shareDto.setLikesAmount(shareSend.getLikesAmount());
                shareDto.setDiscussAmount(shareSend.getDiscussAmount());
                shareDto.setCollectAmount(shareSend.getCollectAmount());
                shareDto.setUserId(shareSend.getUserId());

                User user = userDao.get(hql, new Object[]{shareSend.getUserId()});
                if (user != null) {
                    shareDto.setUserName(user.getName());
                    shareDto.setUserNickName(user.getNickname());
                    shareDto.setUserType(user.getType());
                }
                List<ShareCollect> shareCollectList = shareCollectService.findCollectsBySendIdAndCollectorId(shareSend.getSendId(), userId);
                if (shareCollectList != null && shareCollectList.size() > 0) {
                    shareDto.setCollectOrNot("true");
                }else {
                    shareDto.setCollectOrNot("false");
                }
                List<ShareLikes> shareLikesList = shareLikesService.findLikesBySendIdAndLikeserId(shareSend.getSendId(), userId);
                if (shareLikesList != null && shareLikesList.size() > 0) {
                    shareDto.setLikesOrNot("true");
                }else {
                    shareDto.setLikesOrNot("false");
                }
                shareDtoS.add(shareDto);
            }
            shareDtoList.clear();
            if (shareDtoS != null && shareDtoS.size() > 0) {
                for (int i = shareDtoS.size()-1; i >= 0; i--) {
                    System.out.println("shareDtoS.size()="+shareDtoS.size()+" i = "+i);
                    shareDtoList.add(shareDtoS.get(i));
                }
            }
//            shareDtoList.add(shareDtoS.get(0));
            shareDtoS.clear();
        }
        return shareDtoList;
    }

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
        String hql="delete from ShareSend where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(sendId);
        if(shareSendDao.executeHql(hql, object)>0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据sendId查找send
     * @param sendId
     * @return
     */
    @Override
    public List<ShareSend> findShareSendBySendId(int sendId) {
        String hql="from ShareSend where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(sendId);
        List<ShareSend> shareSends = shareSendDao.find(hql, object);
        if (shareSends != null && shareSends.size() > 0) {
            return shareSends;
        } else {
            return null;
        }
    }

    /**
     * 更新点赞数
     * @param sendId
     * @param likesAmount
     * @return
     */
    @Override
    public boolean updateLikesAmount(int sendId,int likesAmount) {
        String hql = "update ShareSend set likesAmount=? where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(likesAmount);
        object.add(sendId);
        if (shareSendDao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新评论数
     * @param sendId
     * @param discussAmount
     * @return
     */
    @Override
    public boolean updateDiscussAmount(int sendId,int discussAmount) {
        String hql = "update ShareSend set discussAmount=? where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(discussAmount);
        object.add(sendId);
        if (shareSendDao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新收藏数
     * @param sendId
     * @param collectAmount
     * @return
     */
    @Override
    public boolean updateCollectAmount(int sendId,int collectAmount) {
        String hql = "update ShareSend set collectAmount=? where sendId=?";
        List<Object> object = new ArrayList<Object>();
        object.add(collectAmount);
        object.add(sendId);
        if (shareSendDao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }
    }

}

