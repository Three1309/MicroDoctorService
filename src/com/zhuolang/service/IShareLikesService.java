package com.zhuolang.service;

import com.zhuolang.model.ShareLikes;

/**
 * Created by wunaifu on 2017/5/2.
 */
public interface IShareLikesService {

    public void addShareLikes(ShareLikes shareLikes);

    public boolean deleteshareLikesById(int id);

}
