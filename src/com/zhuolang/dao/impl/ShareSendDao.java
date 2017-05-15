package com.zhuolang.dao.impl;

import com.zhuolang.dao.IShareSendDao;
import com.zhuolang.model.ShareSend;
import org.springframework.stereotype.Repository;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Repository("shareSendDao")
public class ShareSendDao extends BaseDao<ShareSend> implements IShareSendDao {
}
