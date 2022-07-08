package com.gd.bookshopboot.service;

import com.gd.bookshopboot.domain.*;

import java.util.ArrayList;

public interface AdminService {

    //管理员登录
    Admin adminLoginSer(String admin_name, String admin_password);

    //获取所有用户列表
    ArrayList<Mall_user> getUserListSer();

    //根据用户名的关键字查询指定用户
    ArrayList<Mall_user> selectUserByKeySer(String key);

    //根据用户id删除用户信息
    int removeUserByIdSer(Integer user_id);

    //获取所有用户评论
    ArrayList<Comment> getUserCommentListSer();

    //根据id删除评论
    int removeCommentByIdSer(int id);

    //获取所有用户反馈信息
    ArrayList<Feedback> getFeedbackInfoListSer();

    //添加类别
    int addCategorySer(String category_name);

    //根据id修改此反馈信息为已读
    int updataReadByIdSer(int id);

    //一键读取反馈信息
    int oneKeyReadSer();

    //获取所有轮播图列表
    ArrayList<RotationChart> getRotationChartListSer();

    //添加轮播图图片
    int addRotationChartSer(RotationChart rc);

    //根据id删除轮播图图片
    int removeRotationChartByIdSer(int id);

    //根据类别关键字查询类别
    ArrayList<MallCategory> selectCategoryByKeySer(String key);

    //修改图书类别
    int updataCategorySer(MallCategory mc);

    //删除类别
    int removeCategorySer(int id);

    //添加图书
    int addBookSer(MallBook mallBook);

    //修改图书信息
    int editBookInfoSer(MallBook mallBook);

    //根据id删除图书
    int removeBookByIdSer(int book_id);

    //获取所有未发货订单
    ArrayList<MallOrder> getOrderListSer();

    //发货
    int updataOrderSer(int order_id);

    //一键发货
    int oneKeyOrderSer();

    //获取所有已发货订单
    ArrayList<MallOrder> getShippedOrderListSer();

    //根据id删除订单
    int removeOrderByIdSer(int order_id);
}
