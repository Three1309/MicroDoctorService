package com.zhuolang.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuolang.dto.AppointmentDto;
import com.zhuolang.model.*;
import com.zhuolang.service.*;
import com.zhuolang.util.TimeUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by wunaifu on 2017/5/2.
 */
@Controller
public class ShareSendAction {
    private static final long serialVersionUID = 1L;

    @Autowired
    IShareSendService shareSendService;
    @Autowired
    IShareDiscussService shareDiscussService;
    @Autowired
    IShareLikesService shareLikesService;

    @Autowired
    IDoctorService doctorService;

    @Autowired
    IUserService userService;

    /**
     *   添加分享信息
     *
     *   @return
     *   @throws IOException
     */
    public String addShareSend()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));
        String sendContent = request.getParameter("sendContent");

        ShareSend shareSend = new ShareSend();
        shareSend.setUserId(userId);
        shareSend.setSendContent(sendContent);
        shareSend.setSendTime(new Date());
        shareSend.setLikesAmount(0);
        shareSend.setDiscussAmount(0);

        shareSendService.addShareSend(shareSend);

        PrintWriter out = response.getWriter();
        out.print("addShareSend_success");
        out.flush();
        out.close();
        return null;
    }

    /**
     *   删除分享信息
     *
     *   @return
     *   @throws IOException
     */
    public String deleteShareSendBySendId()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int sendId = Integer.parseInt(request.getParameter("sendId"));

        PrintWriter out = response.getWriter();
        if (shareSendService.deleteShareSendBySendId(sendId)){
            out.print("deleteShareSend_success");
        }else {
            out.print("deleteShareSend_failure");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     *   添加评论信息
     *
     *   @return
     *   @throws IOException
     */
    public String addShareDiscuss()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int sendId = Integer.parseInt(request.getParameter("sendId"));
        int discussId = Integer.parseInt(request.getParameter("discussId"));
        String discussContent = request.getParameter("discussContent");

        ShareDiscuss shareDiscuss = new ShareDiscuss();
        shareDiscuss.setSendId(sendId);
        shareDiscuss.setDiscussId(discussId);
        shareDiscuss.setDiscussContent(discussContent);
        shareDiscuss.setDiscussTime(new Date());

        shareDiscussService.addShareDiscuss(shareDiscuss);

        PrintWriter out = response.getWriter();
        out.print("addShareSend_success");
        out.flush();
        out.close();
        return null;
    }

    /**
     *   删除评论信息
     *
     *   @return
     *   @throws IOException
     */
    public String deleteShareDiscussBySendId()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));

        PrintWriter out = response.getWriter();
        if (shareDiscussService.deleteShareSendById(id)){
            out.print("deleteShareDiscuss_success");
        }else {
            out.print("deleteShareDiscuss_failure");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     * 给分享点赞，点赞数加一或减一
     * @return
     * @throws IOException
     */
    public String updateShareLikes() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        // 根据主键id来更新信息，将整个appointment传到数据库，通过id找到要更新的appointment
        int sendId = Integer.parseInt(request.getParameter("sendId"));
        int likesId = Integer.parseInt(request.getParameter("likesId"));
//        List<Doctor> doctorList = doctorService.findDoctorByDoctorId(doctorId);
//        PrintWriter out = response.getWriter();
//        if (doctorList != null && doctorList.size() > 0) {
//            //是否已点赞，如果没点则添加，点了则删除
//            if(doctorLikesService.findisOrnotLike(doctorId, appointmentId)){
//                if (doctorList.get(0).getLikenum()>0) {
//                    doctorList.get(0).setLikenum( (doctorList.get(0).getLikenum()-1) );
//                }
//                doctorLikesService.deleteDoctorLikes(doctorId, appointmentId);
//                out.print("dislikes_success");
//            }else {
//                DoctorLikes doctorLikes = new DoctorLikes();
////                Date likeTime = TimeUtil.strToDate(request.getParameter("seeTime"));
//                doctorLikes.setDoctorId(doctorId);
//                doctorLikes.setAppointmentId(appointmentId);
//                doctorLikes.setLikesTime(new Date());
//                doctorLikesService.addDoctorLikes(doctorLikes);
//                doctorList.get(0).setLikenum( (doctorList.get(0).getLikenum()+1) );
//                out.print("likes_success");
//            }
//            doctorService.updateDLikesNum(doctorId, doctorList.get(0).getLikenum());
//        }
//        out.flush();
//        out.close();
        return null;
    }



}
