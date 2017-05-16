package com.zhuolang.service;

import com.zhuolang.model.ShareLikes;

import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
public interface IShareLikesService {

    public void addShareLikes(ShareLikes shareLikes);

    public boolean deleteshareLikesById(int id);

    public boolean deleteShareLikesBySendId(int sendId);

    public List<ShareLikes> findLikesBySendIdAndLikeserId(int sendId, int likeserId);

}
