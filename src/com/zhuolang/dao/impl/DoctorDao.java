package com.zhuolang.dao.impl;

import com.zhuolang.dao.IDoctorDao;
import com.zhuolang.model.Doctor;
import org.springframework.stereotype.Repository;

/**
 * Created by wunaifu on 2017/4/27.
 */
@Repository("doctorDao")
public class DoctorDao extends BaseDao<Doctor> implements IDoctorDao{
}
