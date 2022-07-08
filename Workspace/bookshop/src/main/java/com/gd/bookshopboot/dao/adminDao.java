package com.gd.bookshopboot.dao;

import com.gd.bookshopboot.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface adminDao {

    //管理员登录
    Admin adminLoginDao(@Param("admin_name") String admin_name, @Param("admin_password") String admin_password);

    //获取所有用户列表
    ArrayList<Mall_user> getUserListDao();

    //根据用户名的关键字查询指定用户
    ArrayList<Mall_user> selectUserByKeyDao(String key);

    //根据用户id删除用户信息
    int removeUserByIdDao(Integer user_id);

    //获取所有用户评论
    ArrayList<Comment> getUserCommentListDao();

    //根据id删除评论
    int removeCommentByIdDao(int id);

    //获取所有用户反馈信息
    ArrayList<Feedback> getFeedbackInfoListDao();

    //添加类别
    int addCategoryDao(String category_name);

    //根据id修改此反馈信息为已读
    int updataReadByIdDao(int id);

    //一键读取反馈信息
    int oneKeyReadDao();

    //获取所有轮播图列表
    ArrayList<RotationChart> getRotationChartListDao();

    //添加轮播图图片
    int addRotationChartDao(RotationChart rc);

    //根据id删除轮播图图片
    int removeRotationChartByIdDao(int id);

    //根据类别的关键字查询类别
    ArrayList<MallCategory> selectCategoryByKeyDao(String key);

    //修改图书类别
    int updataCategoryDao(MallCategory mc);

    //删除类别
    int removeCategoryDao(int id);

    //添加图书
    int addBookDao(MallBook mallBook);

    //修改图书信息
    int editBookInfoDao(MallBook mallBook);

    //根据id删除图书
    int removeBookByIdDao(int book_id);

    //获取所有未发货订单
    ArrayList<MallOrder> getOrderListDao();

    //发货
    int updataOrderDao(int order_id);

    //一键发货
    int oneKeyOrderDao();

    //获取已发货订单列表
    ArrayList<MallOrder> getShippedOrderListDao();

    //根据id删除订单
    int removeOrderByIdDao(int order_id);
}
