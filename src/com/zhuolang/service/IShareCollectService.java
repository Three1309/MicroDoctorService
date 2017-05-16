package com.zhuolang.service;

import com.zhuolang.model.ShareCollect;
import com.zhuolang.model.ShareLikes;

import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
public interface IShareCollectService {

    public void addShareCollect(ShareCollect shareCollect);

    public boolean deleteShareCollectBySendId(int sendId);

    public boolean deleteShareCollectByCollectId(int collectId);

    public List<ShareCollect> findCollectsBySendIdAndCollectorId(int sendId, int collectorId);

}
