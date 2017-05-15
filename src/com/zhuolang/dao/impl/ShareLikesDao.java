package com.zhuolang.dao.impl;

import com.zhuolang.dao.IShareLikesDao;
import com.zhuolang.model.ShareLikes;
import org.springframework.stereotype.Repository;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Repository("shareLikesDao")
public class ShareLikesDao extends BaseDao<ShareLikes> implements IShareLikesDao {
}
