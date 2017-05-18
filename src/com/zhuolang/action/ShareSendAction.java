package com.zhuolang.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuolang.dto.AppointmentDto;
import com.zhuolang.dto.ShareDiscussDto;
import com.zhuolang.dto.ShareDto;
import com.zhuolang.dto.ShareHouseDto;
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
    IShareCollectService shareCollectService;

    @Autowired
    IDoctorService doctorService;

    @Autowired
    IUserService userService;

    /**
     *   通过userId返回用户的分享信息，包括个人信息、被评论点赞收藏数目
     *   @return
     *   @throws IOException
     */
    public String findUserShareInfo()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));

        ShareHouseDto shareHouseDto = shareSendService.findUserShareInfo(userId);
        PrintWriter out = response.getWriter();
        if (shareHouseDto != null) {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(shareHouseDto);
            out.print(jsonObject.toString());
        }else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     *   通过userId返回某用户已发表的帖子信息，包括
     *   @return
     *   @throws IOException
     */
    public String findMyShareInfoHistory()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));

        List<ShareDto> shareDtoList = shareSendService.findMyShareHistory(userId);
        PrintWriter out = response.getWriter();
        if (shareDtoList != null && shareDtoList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (ShareDto shareDto : shareDtoList) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(shareDto);
                jsonArray.add(jsonObject);
            }
            out.print(jsonArray.toString());
        }else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     *   通过userId返回某用户已收藏的帖子信息
     *   @return
     *   @throws IOException
     */
    public String findMyCollectShareInfo()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));

        List<ShareDto> shareDtoList = shareSendService.findMyCollectShares(userId);
        PrintWriter out = response.getWriter();
        if (shareDtoList != null && shareDtoList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (ShareDto shareDto : shareDtoList) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(shareDto);
                jsonArray.add(jsonObject);
            }
            out.print(jsonArray.toString());
        }else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     *   通过userId返回某用户已评论的帖子信息
     *   @return
     *   @throws IOException
     */
    public String findMyDiscussShareInfo()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));

        List<ShareDto> shareDtoList = shareSendService.findMyDiscussShares(userId);
        PrintWriter out = response.getWriter();
        if (shareDtoList != null && shareDtoList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (ShareDto shareDto : shareDtoList) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(shareDto);
                jsonArray.add(jsonObject);
            }
            out.print(jsonArray.toString());
        }else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }


    /**
     *   返回所有帖子信息,是否已收藏、点赞
     *   @return
     *   @throws IOException
     */
    public String findAllShare()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));

        List<ShareDto> shareDtoList = shareSendService.findAllShare(userId);
        PrintWriter out = response.getWriter();
        if (shareDtoList != null && shareDtoList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (ShareDto shareDto : shareDtoList) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(shareDto);
                jsonArray.add(jsonObject);
            }
            out.print(jsonArray.toString());
        }else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     *   通过sendId返回帖子的所有评论
     *   @return
     *   @throws IOException
     */
    public String findAllShareDiscussBySendId()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int sendId = Integer.parseInt(request.getParameter("sendId"));

        List<ShareDiscussDto> discussDtoList = shareDiscussService.findAllShareDiscuss(sendId);
        PrintWriter out = response.getWriter();
        if (discussDtoList != null && discussDtoList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (ShareDiscussDto shareDiscussDto : discussDtoList) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(shareDiscussDto);
                jsonArray.add(jsonObject);
            }
            out.print(jsonArray.toString());
        }else {
            out.print("nodata");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     *   添加分享信息、发帖
     *   @return
     *   @throws IOException
     */
    public String addShareSend()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));
        String sendTitle = request.getParameter("sendTitle");
        String sendContent = request.getParameter("sendContent");

        ShareSend shareSend = new ShareSend();
        shareSend.setUserId(userId);
        shareSend.setSendTitle(sendTitle);
        shareSend.setSendContent(sendContent);
        shareSend.setSendTime(new Date());
        shareSend.setLikesAmount(0);
        shareSend.setDiscussAmount(0);
        shareSend.setCollectAmount(0);

        shareSendService.addShareSend(shareSend);

        PrintWriter out = response.getWriter();
        out.print("addShareSend_success");
        out.flush();
        out.close();
        return null;
    }

    /**
     *   删除分享信息(评论。赞、收藏也会被删除)
     *   @return
     *   @throws IOException
     */
    public String deleteShareSendBySendId()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int sendId = Integer.parseInt(request.getParameter("sendId"));

        PrintWriter out = response.getWriter();
        shareDiscussService.deleteShareDiscussBySendId(sendId);
        shareLikesService.deleteShareLikesBySendId(sendId);
        shareCollectService.deleteShareCollectBySendId(sendId);
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
     *   @return
     *   @throws IOException
     */
    public String addShareDiscuss()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int sendId = Integer.parseInt(request.getParameter("sendId"));
        int discusserId = Integer.parseInt(request.getParameter("discusserId"));
        String discussContent = request.getParameter("discussContent");

        List<ShareSend> shareSendList = shareSendService.findShareSendBySendId(sendId);
        PrintWriter out = response.getWriter();
        if (shareSendList != null && shareSendList.size() > 0) {
            ShareDiscuss shareDiscuss = new ShareDiscuss();
            shareDiscuss.setSendId(sendId);
            shareDiscuss.setDiscusserId(discusserId);
            shareDiscuss.setDiscussContent(discussContent);
            shareDiscuss.setDiscussTime(new Date());

            shareDiscussService.addShareDiscuss(shareDiscuss);

            shareSendService.updateDiscussAmount(sendId, (shareSendList.get(0).getDiscussAmount() + 1));
            out.print("addShareDiscuss_success");
        }else {
            out.print("addShareDiscuss_failure");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     *   删除评论信息
     *   @return
     *   @throws IOException
     */
    public String deleteShareDiscussById()throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));
        int sendId = Integer.parseInt(request.getParameter("sendId"));

        List<ShareSend> shareSendList = shareSendService.findShareSendBySendId(sendId);
        PrintWriter out = response.getWriter();
        if (shareSendList != null && shareSendList.size() > 0) {
            if (shareDiscussService.deleteShareDiscussById(id)){
                shareSendService.updateDiscussAmount(sendId, (shareSendList.get(0).getDiscussAmount() - 1));
                out.print("deleteShareDiscuss_success");
            }else {
                out.print("deleteShareDiscuss_failure");
            }
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
        int likeserId = Integer.parseInt(request.getParameter("likeserId"));

        List<ShareSend> shareSendList = shareSendService.findShareSendBySendId(sendId);
        PrintWriter out = response.getWriter();
        //是否已点赞，如果没点则添加，点了则删除
        if (shareSendList != null && shareSendList.size() > 0) {
            List<ShareLikes> likesList = shareLikesService.findLikesBySendIdAndLikeserId(sendId,likeserId);
            int likesAmount = shareSendList.get(0).getLikesAmount();
            if (likesList != null && likesList.size() > 0) {
                //已点赞，则删除赞
                shareLikesService.deleteshareLikesById(likesList.get(0).getId());
                likesAmount = shareSendList.get(0).getLikesAmount() - 1;
                out.print("dislikes_success");
            }else {
                //没点赞则添加赞
                ShareLikes shareLikes = new ShareLikes();
                shareLikes.setSendId(sendId);
                shareLikes.setLikeserId(likeserId);
                shareLikes.setLikesTime(new Date());
                shareLikesService.addShareLikes(shareLikes);
                likesAmount = shareSendList.get(0).getLikesAmount() + 1;
                out.print("likes_success");
            }
            shareSendService.updateLikesAmount(sendId, likesAmount);

        }else
            out.print("likes_failure");
        out.flush();
        out.close();
        return null;
    }

    /**
     * 收藏分享，收藏数加一或减一
     * @return
     * @throws IOException
     */
    public String updateShareCollect() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        // 根据主键id来更新信息，将整个appointment传到数据库，通过id找到要更新的appointment
        int sendId = Integer.parseInt(request.getParameter("sendId"));
        int collectorId = Integer.parseInt(request.getParameter("collectorId"));

        List<ShareSend> shareSendList = shareSendService.findShareSendBySendId(sendId);
        PrintWriter out = response.getWriter();
        //是否已收藏，如果没收藏则添加，收藏了则删除
        if (shareSendList != null && shareSendList.size() > 0) {

            List<ShareCollect> collectList = shareCollectService.findCollectsBySendIdAndCollectorId(sendId,collectorId);

            int collectAmount = shareSendList.get(0).getCollectAmount();
            if (collectList != null && collectList.size() > 0) {
                //已收藏，则删除收藏
                shareCollectService.deleteShareCollectByCollectId(collectList.get(0).getCollectId());
                collectAmount = shareSendList.get(0).getCollectAmount() - 1;
                out.print("uncollect_success");
            }else {
                //没收藏则添加收藏
                ShareCollect shareCollect = new ShareCollect();
                shareCollect.setSendId(sendId);
                shareCollect.setCollectorId(collectorId);
                shareCollect.setCollectTime(new Date());
                shareCollectService.addShareCollect(shareCollect);
                collectAmount = shareSendList.get(0).getCollectAmount() + 1;
                out.print("addcollect_success");
            }
            shareSendService.updateCollectAmount(sendId, collectAmount);

        }else
            out.print("collect_failure");
        out.flush();
        out.close();
        return null;
    }


}
