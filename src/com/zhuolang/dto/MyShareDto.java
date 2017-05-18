package com.zhuolang.dto;

import java.util.Date;

/**
 * Created by wunaifu on 2017/5/15.
 */
public class MyShareDto {

    private int sendId;//帖子的ID
    private int userId;//发帖者的ID
    private String userName;//发帖人的名字
    private String userNickName;//发帖人的昵称
    private int userType;//发帖人的权限类型
    private String userJianjie;//用户简介

    private String sendTitle;//帖子标题
    private String sendContent;//帖子内容
    private Date sendTime;//发帖时间
    private int likesAmount;//点赞数
    private int discussAmount;//评论数
    private int collectAmount;//收藏数
    private String collectOrNot;//是否收藏
    private String likesOrNot;//是否点赞

}
