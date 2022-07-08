package com.gd.bookshopboot.dao;

import com.gd.bookshopboot.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface userDao {

    //用户登录
    Mall_user userLoginSer(@Param("username") String username, @Param("password") String password);

    //获取指定图书的用户评论
    ArrayList<Comment> getCommentListDao(int book_id);

    //添加用户评论
    int addCommentDao(Comment comment);

    //根据id删除评论
    int deleteCommentByIdDao(int id);

    //查询收藏表有没有这本图书
    CollectionBook queryCollBookByIdDao(int book_id);

    //添加收藏
    int addCollectionDao(CollectionBook collection);

    //修改图书的库存和销量
    int updataBookStockDao(@Param("book_id") int book_id, @Param("quantity") int quantity);

    //用户立即购买加入订单
    int addShoppingCartDao(MallOrder Order);

    //查看购物车有没有本商品，有就修改数量，没有再加入购物车
    MallCart selectCartbookByIdDao(int book_id);

    //加入购物车
    int addCartDao(MallCart mallCart);

    //购物车有了这件商品就修改数量
    int updataCartbookNumDao(@Param("book_id") int book_id, @Param("num") int num, @Param("cart_id") int cart_id);

    //根据用户id查询用户的购物车信息
    ArrayList<MallCart> getCartByUserIdDao(int user_id);

    //修改购物车中图书的数量
    int buyNumChangeDao(@Param("cart_id") int cart_id, @Param("num") int num);

    //根据购物车id删除信息
    int deleteRowDao(int cart_id);

    //获取用户的订单
    ArrayList<MallOrder> getUserOrderListDao(int user_id);

    //根据id删除订单
    int deleteOrderByIdDao(int id);

    //根据用户名查询用户
    Mall_user selectUserByUserNameDao(String username);

    //注册用户
    int registerUserDao(Mall_user mall_user);

    //根据用户id查询用户详情
    Mall_user userInfoDao(int user_id);

    //修改用户头像
    int updataUserTxDao(@Param("user_id") int user_id, @Param("user_img") String user_img);

    //修改用户信息
    int editUserInfoDao(Mall_user mall_user);

//    修改用户密码
    void changeUserPassword(Mall_user mall_user);

    //根据用户id查询用户反馈
    ArrayList<Feedback> getFeedbackListDao(int user_id);

    //添加用户反馈
    int addFeedbackDao(Feedback feedback);

    //根据id删除用户反馈信息
    int removeFeedbackByIdDao(int id);

    //获取用户的收藏列表
    ArrayList<CollectionBook> getCollectionListDao(int user_id);

    //删除收藏
    int deleteCollectionByIdDao(int id);

    //用户确定收货
    int receivingDao(int id);
}
