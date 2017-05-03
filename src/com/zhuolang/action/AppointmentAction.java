package com.zhuolang.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuolang.dao.IDoctorLikesDao;
import com.zhuolang.dto.AppointmentDto;
import com.zhuolang.model.Appointment;
import com.zhuolang.model.Doctor;
import com.zhuolang.model.DoctorLikes;
import com.zhuolang.service.IAppointmentService;
import com.zhuolang.service.IDoctorLikesService;
import com.zhuolang.service.IDoctorService;
import com.zhuolang.service.IUserService;
import com.zhuolang.util.TimeUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSONArray;
/**
 * Created by wunaifu on 2017/5/2.
 */
@Controller
public class AppointmentAction {
    private static final long serialVersionUID = 1L;

    @Autowired
    IAppointmentService appointmentService;

    @Autowired
    IDoctorService doctorService;

    @Autowired
    IUserService userService;

    @Autowired
    IDoctorLikesService doctorLikesService;

    /**
     *   添加预约信息
     *    1、向某医师申请预约  2、申请资料提交
     *   @return
     *   @throws IOException
     */
    public String addAppointment()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        String patientName = request.getParameter("patientName");
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));//在安卓界面上通过点击选择一名医师，所以可以取得其id
        String doctorName = request.getParameter("doctorName");
        Date seeTime = TimeUtil.strToDate(request.getParameter("seeTime"));

        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setSeeTime(seeTime);//就诊时间
        appointment.setDisease(request.getParameter("disease"));
        appointment.setDateTime(new Date());//预约时的时间
        int dNumber = appointmentService.CountAppByDIdAndDate(doctorId, seeTime) + 1;//预约的排号
        appointment.setdNumber(dNumber);

        appointmentService.addAppointment(appointment);
        PrintWriter out = response.getWriter();
        out.print("addAppointment_success");//或者失败
        out.flush();
        out.close();
        return null;
    }

    /**
     * 查看某医师历史就诊队列
     * @return
     * @throws IOException
     */
    public String findDoctSeeHistory() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        List<AppointmentDto> list = appointmentService.findByDocId(Integer.parseInt(request.getParameter("doctorId")));

        PrintWriter out = response.getWriter();
        int num=0;
        if (list != null && list.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (AppointmentDto a : list) {
                if (a.getDiagnose() != null) {
                    JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                    jsonArray.add(jsonObj);
                    num++;
                }
            }
            if (num==0){
                out.print("nodata");
            }else {
                out.print(jsonArray.toString());
            }
//            JSONObject jsonObject=new JSONObject();
//            String appointmentJson=jsonObject.toJSONString(list);
//            out.print(appointmentJson);
        }else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     * 查看某医师预约申请队列
     * @return
     * @throws IOException
     */
    public String findByDocId() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        List<AppointmentDto> list = appointmentService.findByDocId(Integer.parseInt(request.getParameter("doctorId")));

        PrintWriter out = response.getWriter();
        int num = 0;
        if (list != null && list.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (AppointmentDto a : list) {
                if (a.getDiagnose() == null) {
                    JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                    jsonArray.add(jsonObj);
                    num++;
                }
            }
            if (num == 0){
                out.print("nodata");
            }else {
                out.print(jsonArray.toString());
            }
//            JSONObject jsonObject=new JSONObject();
//            String appointmentJson=jsonObject.toJSONString(list);
//            out.print(appointmentJson);
        }else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     * 、医师填写就诊结果
     * @return
     * @throws IOException
     */
    public String updateDiagnose() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        // 根据主键id来更新信息，将整个appointment传到数据库，通过id找到要更新的appointment

        int id = Integer.parseInt(request.getParameter("id"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String diagnose = request.getParameter("diagnose");
        appointmentService.updateDiagnose(id, diagnose);
        List<Doctor> doctorList=doctorService.findDoctorByDoctorId(doctorId);
        if (doctorList != null && doctorList.size() > 0) {
            doctorService.updateAmount(doctorId,doctorList.get(0).getAmount());
        }
        PrintWriter out = response.getWriter();
        out.print("updateDiagnose_success");
        out.flush();
        out.close();
        return null;
    }

    /**
     * 、医师填写预约留言
     * @return
     * @throws IOException
     */
    public String updateSaying() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        // 根据主键id来更新信息，将整个appointment传到数据库，通过id找到要更新的appointment

        int id = Integer.parseInt(request.getParameter("id"));
        String doctorSay = request.getParameter("doctorSay");
        appointmentService.updateDoctorSay(id, doctorSay);

        PrintWriter out = response.getWriter();
        out.print("updateDoctorSay_success");
        out.flush();
        out.close();
        return null;
    }

    /**
     * 在登录状态下查看个人当前的预约信息
     * @return
     * @throws IOException
     */
    public String findByPatId() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        List<AppointmentDto> list = appointmentService.findByPatId(Integer.parseInt(request.getParameter("patientId")));

        PrintWriter out = response.getWriter();
        int nums = 0;
        if (list != null && list.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (AppointmentDto a : list) {
                if (a.getDiagnose() == null) {
                    JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                    jsonArray.add(jsonObj);
                    nums++;
                }
            }
            if (nums==0){
                out.print("nodata");
            }else {

                out.print(jsonArray.toString());
            }
        } else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     * 在登录状态下查看个人历史就诊信息
     * @return
     * @throws IOException
     */
    public String findHistoryAptmByPatId() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        List<AppointmentDto> list = appointmentService.findByPatId(Integer.parseInt(request.getParameter("patientId")));

        PrintWriter out = response.getWriter();
        int num=0;
        if (list != null && list.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (AppointmentDto b : list) {
                if (b.getDiagnose() != null) {
                    JSONObject jsonObj = (JSONObject) JSON.toJSON(b);
                    jsonArray.add(jsonObj);
                    num++;
                }
            }
            if (num==0){
                out.print("nodata");
            }else {
                out.print(jsonArray.toString());
            }
        } else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     * 就诊成功后给医生点赞，医生表中的好评数加一
     * @return
     * @throws IOException
     */
    public String updateDoctorLikes() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        // 根据主键id来更新信息，将整个appointment传到数据库，通过id找到要更新的appointment
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        List<Doctor> doctorList = doctorService.findDoctorByDoctorId(doctorId);
        if (doctorList != null && doctorList.size() > 0) {
            //是否已点赞，如果没点则添加，点了则删除
            if(doctorLikesService.findisOrnotLike(doctorId, appointmentId)){
                if (doctorList.get(0).getLikenum()>0) {
                    doctorList.get(0).setLikenum( (doctorList.get(0).getLikenum()-1) );
                }
                doctorLikesService.deleteDoctorLikes(doctorId, appointmentId);
            }else {
                DoctorLikes doctorLikes = new DoctorLikes();
//                Date likeTime = TimeUtil.strToDate(request.getParameter("seeTime"));
                doctorLikes.setDoctorId(doctorId);
                doctorLikes.setAppointmentId(appointmentId);
                doctorLikes.setLikesTime(new Date());
                doctorLikesService.addDoctorLikes(doctorLikes);
                doctorList.get(0).setLikenum( (doctorList.get(0).getLikenum()+1) );
            }
            doctorService.updateDLikesNum(doctorId, doctorList.get(0).getLikenum());
        }
        PrintWriter out = response.getWriter();
        out.print("likes_success");
        out.flush();
        out.close();
        return null;
    }


}
