package com.gd.bookshopboot.service;

import com.gd.bookshopboot.domain.*;

import java.util.ArrayList;

public interface UserService {

    //用户登录
    Mall_user userLoginSer(String username, String password);

    //获取指定图书的用户评论
    ArrayList<Comment> getCommentListSer(int book_id);

    //添加用户评论
    int addCommentSer(Comment comment);

    //根据id删除评论
    int deleteCommentByIdSer(int id);

    //查询收藏表有没有这本图书
    CollectionBook queryCollBookByIdSer(int book_id);

    //添加收藏
    int addCollectionSer(CollectionBook collection);

    //修改图书的库存和销量
    int updataBookStockSer(int book_id, int quantity);

    //用户立即购买加入订单
    int addShoppingCartSer(MallOrder order);

    //查看购物车有没有本商品，有就修改数量，没有再加入购物车
    MallCart selectCartbookByIdSer(int boo_id);

    //加入购物车
    int addCartSer(MallCart mallCart);

    //购物车有了这件商品就修改数量
    int updataCartbookNumSer(int book_id, int num, int cart_id);

    //根据用户id查询用户的购物车信息
    ArrayList<MallCart> getCartByUserIdSer(int user_id);

    //修改购物车中图书的数量
    int buyNumChangeSer(int cart_id, int num);

    //根据购物车id删除信息
    int deleteRowSer(int cart_id);

    //根据id删除购物信息
    int delectCartById(String carts, String nick_name, String tel, String address);

    //清空购物车
    int emptyCartSer(int user_id, String nick_name, String tel, String address);

    //获取用户的订单
    ArrayList<MallOrder> getUserOrderListSer(int user_id);

    //根据id删除订单
    int deleteOrderByIdSer(int id);

    //根据用户名查询用户
    Mall_user selectUserByUserNameSer(String username);

    //注册用户
    int registerUserSer(Mall_user mall_user);

    //根据用户id查询用户详情
    Mall_user userInfoSer(int user_id);

    //修改用户头像
    int updataUserTxSer(int user_id, String user_img);

    //修改用户信息
    int editUserInfoSer(Mall_user mall_user);

    //    修改用户密码
    int changeUserPassword(String username, String newpassword, String password);

    //根据用户id查询用户反馈
    ArrayList<Feedback> getFeedbackListSer(int user_id);

    //添加用户反馈
    int addFeedbackSer(Feedback feedback);

    //根据id删除用户反馈信息
    int removeFeedbackSer(int id);

    //获取用户的收藏列表
    ArrayList<CollectionBook> getCollectionListSer(int user_id);

    //删除收藏
    int deleteCollectionByIdSer(int id);

    //用户确定收货
    int receivingSer(int id);
}
