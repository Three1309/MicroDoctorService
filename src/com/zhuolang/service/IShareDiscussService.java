package com.zhuolang.service;

import com.zhuolang.dto.ShareDiscussDto;
import com.zhuolang.model.ShareDiscuss;

import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
public interface IShareDiscussService {

    public List<ShareDiscussDto> findAllShareDiscuss(int sendId);

    public void addShareDiscuss(ShareDiscuss shareDiscuss);

    public boolean deleteShareDiscussById(int id);

    public boolean deleteShareDiscussBySendId(int sendId);

}
