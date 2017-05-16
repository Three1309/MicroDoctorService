package com.zhuolang.dao.impl;

import com.zhuolang.dao.IShareCollectDao;
import com.zhuolang.dao.IShareLikesDao;
import com.zhuolang.model.ShareCollect;
import com.zhuolang.model.ShareLikes;
import org.springframework.stereotype.Repository;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Repository("shareCollectDao")
public class ShareCollectDao extends BaseDao<ShareCollect> implements IShareCollectDao {
}
