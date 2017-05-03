package com.zhuolang.dao.impl;

import com.zhuolang.dao.IAppointmentDao;
import com.zhuolang.model.Appointment;
import org.springframework.stereotype.Repository;

/**
 * Created by wunaifu on 2017/5/2.
 */
@Repository("appointmentDao")
public class AppointmentDao extends BaseDao<Appointment> implements IAppointmentDao{
}
