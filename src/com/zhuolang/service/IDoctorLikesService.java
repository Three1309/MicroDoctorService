package com.zhuolang.service;

import com.zhuolang.model.DoctorLikes;

/**
 * Created by wunaifu on 2017/5/3.
 */
public interface IDoctorLikesService {

    public int addDoctorLikes(DoctorLikes doctorLikes);

    public boolean findisOrnotLike(int doctorId, int appointmentId);

    public boolean deleteDoctorLikes(int doctorId, int appointmentId);

}
