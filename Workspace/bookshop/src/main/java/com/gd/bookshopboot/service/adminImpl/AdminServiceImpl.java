package com.gd.bookshopboot.service.adminImpl;

import com.gd.bookshopboot.dao.adminDao;
import com.gd.bookshopboot.domain.*;
import com.gd.bookshopboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private adminDao adminDao;

    //管理员登录
    @Override
    public Admin adminLoginSer(String admin_name, String admin_password) {
        return adminDao.adminLoginDao(admin_name,admin_password);
    }

    //获取所有用户列表
    @Override
    public ArrayList<Mall_user> getUserListSer() {
        return adminDao.getUserListDao();
    }

    //根据用户名的关键字查询指定用户
    @Override
    public ArrayList<Mall_user> selectUserByKeySer(String key) {
        return adminDao.selectUserByKeyDao(key);
    }

    //根据用户id删除用户信息
    @Override
    public int removeUserByIdSer(Integer user_id) {
        return adminDao.removeUserByIdDao(user_id);
    }

    //获取所有用户评论
    @Override
    public ArrayList<Comment> getUserCommentListSer() {
        return adminDao.getUserCommentListDao();
    }

    //根据id删除评论
    @Override
    public int removeCommentByIdSer(int id) {
        return adminDao.removeCommentByIdDao(id);
    }

    //获取所有用户反馈信息
    @Override
    public ArrayList<Feedback> getFeedbackInfoListSer() {
        return adminDao.getFeedbackInfoListDao();
    }

    //添加类别
    @Override
    public int addCategorySer(String category_name) {
        return adminDao.addCategoryDao(category_name);
    }

    //据id修改此反馈信息为已读
    @Override
    public int updataReadByIdSer(int id) {
        return adminDao.updataReadByIdDao(id);
    }

    //一键读取反馈信息
    @Override
    public int oneKeyReadSer() {
        return adminDao.oneKeyReadDao();
    }

    //获取所有轮播图列表
    @Override
    public ArrayList<RotationChart> getRotationChartListSer() {
        return adminDao.getRotationChartListDao();
    }

    //添加轮播图图片
    @Override
    public int addRotationChartSer(RotationChart rc) {
        return adminDao.addRotationChartDao(rc);
    }

    //根据id删除轮播图图片
    @Override
    public int removeRotationChartByIdSer(int id) {
        return adminDao.removeRotationChartByIdDao(id);
    }

    //根据类别关键字查询类别
    @Override
    public ArrayList<MallCategory> selectCategoryByKeySer(String key) {
        return adminDao.selectCategoryByKeyDao(key);
    }

    //修改图书类别
    @Override
    public int updataCategorySer(MallCategory mc) {
        return adminDao.updataCategoryDao(mc);
    }

    //删除类别
    @Override
    public int removeCategorySer(int id) {
        return adminDao.removeCategoryDao(id);
    }

    //添加图书
    @Override
    public int addBookSer(MallBook mallBook) {
        return adminDao.addBookDao(mallBook);
    }

    //修改图书信息
    @Override
    public int editBookInfoSer(MallBook mallBook) {
        return adminDao.editBookInfoDao(mallBook);
    }

    //根据id删除图书
    @Override
    public int removeBookByIdSer(int book_id) {
        return adminDao.removeBookByIdDao(book_id);
    }

    //获取所有未发货订单
    @Override
    public ArrayList<MallOrder> getOrderListSer() {
        return adminDao.getOrderListDao();
    }

    //发货
    @Override
    public int updataOrderSer(int order_id) {
        return adminDao.updataOrderDao(order_id);
    }

    //一键发货
    @Override
    public int oneKeyOrderSer() {
        return adminDao.oneKeyOrderDao();
    }

    //获取所有已发货订单
    @Override
    public ArrayList<MallOrder> getShippedOrderListSer() {
        return adminDao.getShippedOrderListDao();
    }

    //根据id删除订单
    @Override
    public int removeOrderByIdSer(int order_id) {
        return adminDao.removeOrderByIdDao(order_id);
    }
}
