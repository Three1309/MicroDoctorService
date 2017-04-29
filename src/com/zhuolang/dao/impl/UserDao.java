package com.zhuolang.dao.impl;

import com.zhuolang.dao.IUserDao;
import com.zhuolang.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by wnf on 2017/4/12.
 */
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao{
}
