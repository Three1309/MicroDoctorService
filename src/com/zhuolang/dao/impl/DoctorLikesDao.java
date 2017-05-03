package com.zhuolang.dao.impl;

import com.zhuolang.dao.IDoctorLikesDao;
import com.zhuolang.model.DoctorLikes;
import org.springframework.stereotype.Repository;

/**
 * Created by wunaifu on 2017/5/3.
 */
@Repository("doctorLikesDao")
public class DoctorLikesDao extends BaseDao<DoctorLikes> implements IDoctorLikesDao {
}
