package com.zhuolang.service;

import com.zhuolang.model.ShareDiscuss;

/**
 * Created by wunaifu on 2017/5/2.
 */
public interface IShareDiscussService {

    public void addShareDiscuss(ShareDiscuss shareDiscuss);

    public boolean deleteShareSendById(int id);

}
