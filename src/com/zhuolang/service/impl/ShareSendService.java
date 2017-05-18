package com.zhuolang.service.impl;

import com.zhuolang.dao.impl.*;
import com.zhuolang.dto.ShareDto;
import com.zhuolang.dto.ShareHouseDto;
import com.zhuolang.model.*;
import com.zhuolang.service.IShareCollectService;
import com.zhuolang.service.IShareDiscussService;
import com.zhuolang.service.IShareLikesService;
import com.zhuolang.service.IShareSendService;
import com.zhuolang.util.TimeUtil;
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
    ShareLikesDao shareLikesDao;
    @Autowired
    ShareCollectDao shareCollectDao;
    @Autowired
    ShareDiscussDao shareDiscussDao;

    @Autowired
    IShareCollectService shareCollectService;
    @Autowired
    IShareLikesService shareLikesService;
    @Autowired
    IShareDiscussService shareDiscussService;

    /**
     * 根据userId找到其所有的分享,收集个人信息并统计各被评论、赞、收藏数量
     *
     * @return
     */
    @Override
    public ShareHouseDto findUserShareInfo(int userId) {
        ShareHouseDto shareHouseDto = new ShareHouseDto();

        String hql = "from User where id=?";
        User user = userDao.get(hql, new Object[]{userId});
        if (user != null) {
            shareHouseDto.setUserId(userId);
            shareHouseDto.setUserName(user.getName());
            shareHouseDto.setUserNickName(user.getNickname());
            shareHouseDto.setUserType(user.getType());
            shareHouseDto.setUserJianjie(user.getIntroduction());

            String hqlShareSend="from ShareSend where userId=?";
            List<Object> object = new ArrayList<Object>();
            object.add(userId);
            List<ShareSend> shareSendList = shareSendDao.find(hqlShareSend, object);
            int collectAmount = 0;
            int discussAmount = 0;
            int likesAmount = 0;
            if (shareSendList != null && shareSendList.size() > 0) {

//                for (ShareSend shareSend : shareSendList) {
//                    String hqlShareLike = "from ShareLikes where sendId=?";
//                    List<Object> objectShareLike = new ArrayList<Object>();
//                    objectShareLike.add(userId);
//                    List<ShareLikes> shareLikesList = shareLikesDao.find(hqlShareLike, objectShareLike);
//                    if (shareLikesList != null && shareLikesList.size() > 0) {
//
//                    }
//                }
                for (int i = 0; i < shareSendList.size(); i++) {
                    collectAmount = collectAmount + shareSendList.get(i).getCollectAmount();
                    discussAmount = discussAmount + shareSendList.get(i).getDiscussAmount();
                    likesAmount = likesAmount + shareSendList.get(i).getLikesAmount();
                }

            }
            shareHouseDto.setCollectAmount(collectAmount);
            shareHouseDto.setDiscussAmount(discussAmount);
            shareHouseDto.setLikesAmount(likesAmount);
        }

        return shareHouseDto;
    }


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
     * 查找我发表的所有帖子，最新的在前面
     * @return
     */
    @Override
    public List<ShareDto> findMyShareHistory(int userId) {
        List<ShareDto> shareDtoList = new ArrayList<ShareDto>();
        String hql = "from User where id=?";
        User user = userDao.get(hql, new Object[]{userId});

        String hqlShareSend="from ShareSend where userId=?";
        List<Object> objectShareSend = new ArrayList<Object>();
        objectShareSend.add(userId);
        List<ShareSend> shareSendList = shareSendDao.find(hqlShareSend, objectShareSend);

        if (shareSendList != null && shareSendList.size() > 0) {
            List<ShareDto> shareDtoS = new ArrayList<ShareDto>();
            shareDtoS.clear();
            for (ShareSend shareSend : shareSendList) {
                ShareDto shareDto = new ShareDto();
                shareDto.setSendId(shareSend.getSendId());
                shareDto.setSendTitle(shareSend.getSendTitle());
                shareDto.setSendContent(shareSend.getSendContent());
                shareDto.setSendTime(shareSend.getSendTime());
                shareDto.setLikesAmount(shareSend.getLikesAmount());
                shareDto.setDiscussAmount(shareSend.getDiscussAmount());
                shareDto.setCollectAmount(shareSend.getCollectAmount());
                shareDto.setUserId(shareSend.getUserId());
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
//                    System.out.println("shareDtoS.size()="+shareDtoS.size()+" i = "+i);
                    shareDtoList.add(shareDtoS.get(i));
                }
            }
//            shareDtoList.add(shareDtoS.get(0));
            shareDtoS.clear();
        }
        return shareDtoList;
    }


    /**
     * 查找我收藏的所有帖子，最新的在前面
     * @return
     */
    @Override
    public List<ShareDto> findMyCollectShares(int userId) {
        List<ShareDto> shareDtoList = new ArrayList<ShareDto>();
        String hqlShareSend="from ShareSend where sendId=?";
        String hqlUser = "from User where id=?";

        String hqlCollect="from ShareCollect where collectorId=?";
        List<Object> objectCollect = new ArrayList<Object>();
        objectCollect.add(userId);
        List<ShareCollect> shareCollectList = shareCollectDao.find(hqlCollect, objectCollect);
        if (shareCollectList != null && shareCollectList.size() > 0) {
//            List<ShareCollect> shareCollects = new ArrayList<ShareCollect>();
            List<ShareDto> shareDtoS = new ArrayList<ShareDto>();

            for (ShareCollect shareCollect : shareCollectList) {
                ShareSend shareSend = shareSendDao.get(hqlShareSend,new Object[]{shareCollect.getSendId()});
                if (shareSend != null) {
                    ShareDto shareDto = new ShareDto();
                    shareDto.setSendId(shareSend.getSendId());
                    shareDto.setSendTitle(shareSend.getSendTitle());
                    shareDto.setSendContent(shareSend.getSendContent());
                    shareDto.setSendTime(shareSend.getSendTime());
                    shareDto.setLikesAmount(shareSend.getLikesAmount());
                    shareDto.setDiscussAmount(shareSend.getDiscussAmount());
                    shareDto.setCollectAmount(shareSend.getCollectAmount());
                    shareDto.setUserId(shareSend.getUserId());
                    String dateStr = TimeUtil.dateToString(shareCollect.getCollectTime());
                    shareDto.setCollectOrNot(dateStr);//收藏时间
                    User user = userDao.get(hqlUser, new Object[]{shareSend.getUserId()});
                    if (user != null) {
                        shareDto.setUserName(user.getName());
                        shareDto.setUserNickName(user.getNickname());
                        shareDto.setUserType(user.getType());
                    }
                    List<ShareLikes> shareLikesList = shareLikesService.findLikesBySendIdAndLikeserId(shareSend.getSendId(), userId);
                    if (shareLikesList != null && shareLikesList.size() > 0) {
                        shareDto.setLikesOrNot("true");
                    }else {
                        shareDto.setLikesOrNot("false");
                    }
                    shareDtoS.add(shareDto);
                }
            }
            shareDtoList.clear();
            if (shareDtoS != null && shareDtoS.size() > 0) {
                for (int i = shareDtoS.size()-1; i >= 0; i--) {
                    shareDtoList.add(shareDtoS.get(i));
                }
            }
            shareDtoS.clear();

        }
        return shareDtoList;
    }

    /**
     * 查找我评论的所有帖子，最新的在前面
     * @return
     */
    @Override
    public List<ShareDto> findMyDiscussShares(int userId) {
        List<ShareDto> shareDtoList = new ArrayList<ShareDto>();
        String hqlShareSend="from ShareSend where sendId=?";
        String hqlUser = "from User where id=?";

        String hqlCollect="from ShareDiscuss where discusserId=?";
        List<Object> objectCollect = new ArrayList<Object>();
        objectCollect.add(userId);
        List<ShareDiscuss> shareCollectList = shareDiscussDao.find(hqlCollect, objectCollect);
        if (shareCollectList != null && shareCollectList.size() > 0) {
//            List<ShareCollect> shareCollects = new ArrayList<ShareCollect>();
            List<ShareDto> shareDtoS = new ArrayList<ShareDto>();

            for (ShareDiscuss shareCollect : shareCollectList) {
                ShareSend shareSend = shareSendDao.get(hqlShareSend,new Object[]{shareCollect.getSendId()});
                if (shareSend != null) {
                    ShareDto shareDto = new ShareDto();
                    shareDto.setSendId(shareSend.getSendId());
                    shareDto.setSendTitle(shareSend.getSendTitle());
                    shareDto.setSendContent(shareSend.getSendContent());
                    shareDto.setSendTime(shareSend.getSendTime());
                    shareDto.setLikesAmount(shareSend.getLikesAmount());
                    shareDto.setDiscussAmount(shareSend.getDiscussAmount());
                    shareDto.setCollectAmount(shareSend.getCollectAmount());
                    shareDto.setUserId(shareSend.getUserId());
                    //评论时间
                    String dateStr = TimeUtil.dateToString(shareCollect.getDiscussTime());
                    shareDto.setCollectOrNot(dateStr);
                    //评论内容
                    shareDto.setLikesOrNot(shareCollect.getDiscussContent());
                    User user = userDao.get(hqlUser, new Object[]{shareSend.getUserId()});
                    if (user != null) {
                        shareDto.setUserName(user.getName());
                        shareDto.setUserNickName(user.getNickname());
                        shareDto.setUserType(user.getType());
                    }

                    shareDtoS.add(shareDto);
                }
            }
            shareDtoList.clear();
            if (shareDtoS != null && shareDtoS.size() > 0) {
                for (int i = shareDtoS.size()-1; i >= 0; i--) {
                    shareDtoList.add(shareDtoS.get(i));
                }
            }
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

