package com.gd.bookshopboot.service.userImpl;

import com.alibaba.fastjson.JSON;
import com.gd.bookshopboot.dao.userDao;
import com.gd.bookshopboot.domain.*;
import com.gd.bookshopboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private userDao userDao;

    /*用户登录*/
    @Override
    public Mall_user userLoginSer(String username, String password) {

        Mall_user user = selectUserByUserNameSer(username);
        String md5password = user.getPassword();
        String salt = user.getSalt();
        String newMd5Password = getMd5Password(password,salt);

//        System.out.println(user);
//        System.out.println("salt = " + salt);
//        System.out.println("new = "+newMd5Password);
//        System.out.println("old = "+md5password);
//        System.out.println(md5password.equals(newMd5Password));

        if(md5password.equals(newMd5Password))
            return user;
        return null;
    }

    /*获取指定图书地评论*/
    @Override
    public ArrayList<Comment> getCommentListSer(int book_id) {
        return userDao.getCommentListDao(book_id);
    }

    /*添加用户评论*/
    @Override
    public int addCommentSer(Comment comment) {
        return userDao.addCommentDao(comment);
    }

    /*根据id删除评论*/
    @Override
    public int deleteCommentByIdSer(int id) {
        return userDao.deleteCommentByIdDao(id);
    }

    /*查询搜收藏表有没有这边图书*/
    @Override
    public CollectionBook queryCollBookByIdSer(int book_id) {
        return userDao.queryCollBookByIdDao(book_id);
    }

    /*添加收藏*/
    @Override
    public int addCollectionSer(CollectionBook collection) {
        return userDao.addCollectionDao(collection);
    }

    /*修改图书库存和销量*/
    @Override
    public int updataBookStockSer(int book_id, int quantity) {
        return userDao.updataBookStockDao(book_id,quantity);
    }

    /*用户立即购买加入订单*/
    @Override
    public int addShoppingCartSer(MallOrder order) {
        return userDao.addShoppingCartDao(order);
    }

    /*查看购物车有没有本商品，有就修改数量，没有再加入购物车*/
    @Override
    public MallCart selectCartbookByIdSer(int boo_id) {
        return userDao.selectCartbookByIdDao(boo_id);
    }

    /*加入购物车*/
    @Override
    public int addCartSer(MallCart mallCart) {
        return userDao.addCartDao(mallCart);
    }

    //购物车有了这件商品就修改数量
    @Override
    public int updataCartbookNumSer(int book_id, int num, int cart_id) {
        return userDao.updataCartbookNumDao(book_id,num,cart_id);
    }

    //根据用户id查询用户的购物车信息
    @Override
    public ArrayList<MallCart> getCartByUserIdSer(int user_id) {
        return userDao.getCartByUserIdDao(user_id);
    }

    //修改购物车中图书的数量
    @Override
    public int buyNumChangeSer(int cart_id, int num) {
        return userDao.buyNumChangeDao(cart_id,num);
    }

    //根据购物车id删除信息
    @Override
    public int deleteRowSer(int cart_id) {
        return userDao.deleteRowDao(cart_id);
    }

    //根据id删除购物信息
    @Override
    public int delectCartById(String carts, String nick_name, String tel, String address) {
        int insert = 0;
        int updata = 0;
        int delect = 0;
        //将接收到的JSON字符串数组，转为一个list集合
        List<MallCart> mallCarts = JSON.parseArray(carts, MallCart.class);
        for (MallCart mallCart : mallCarts) {
            MallOrder Order = new MallOrder();
            Order.setUser_id(mallCart.getUser_id());
            Order.setBook_id(mallCart.getBook_id());
            Order.setNick_name(nick_name);
            Order.setTel(tel);
            Order.setAddress(address);
            Order.setBook_img(mallCart.getBook_img());
            Order.setBook_name(mallCart.getBook_name());
            Order.setBuy_price(mallCart.getPrice());
            Order.setBuy_num(mallCart.getNum());
            Order.setBuy_time(new Date());
            //将每一个购物车信息加入订单
            insert = userDao.addShoppingCartDao(Order);
            if (insert > 0) {
                //修改图书的销量和库存
                updata = userDao.updataBookStockDao(mallCart.getBook_id(), mallCart.getNum());
                if (updata > 0) {
                    //最后删除购物车中的信息
                    delect = userDao.deleteRowDao(mallCart.getCart_id());
                }
            }

        }
        if (insert > 0 && updata > 0 && delect > 0) {
            return 1;
        }
        return 0;
    }

    //清空购物车
    @Override
    public int emptyCartSer(int user_id, String nick_name, String tel, String address) {
        int insert = 0;
        int updata = 0;
        int delect = 0;
        //先根据用户id查询他购物车中的信息
        ArrayList<MallCart> mallCarts = userDao.getCartByUserIdDao(user_id);
        for (MallCart mallCart : mallCarts) {
            MallOrder Order = new MallOrder();
            Order.setUser_id(mallCart.getUser_id());
            Order.setBook_id(mallCart.getBook_id());
            Order.setNick_name(nick_name);
            Order.setTel(tel);
            Order.setAddress(address);
            Order.setBook_img(mallCart.getBook_img());
            Order.setBook_name(mallCart.getBook_name());
            Order.setBuy_price(mallCart.getPrice());
            Order.setBuy_num(mallCart.getNum());
            Order.setBuy_time(new Date());
            //将每一个购物车信息加入订单
            insert = userDao.addShoppingCartDao(Order);
            if (insert > 0) {
                //修改图书的销量和库存
                updata = userDao.updataBookStockDao(mallCart.getBook_id(), mallCart.getNum());
                if (updata > 0) {
                    //最后删除购物车中的信息
                    delect = userDao.deleteRowDao(mallCart.getCart_id());
                }
            }
        }
        if (insert > 0 && updata > 0 && delect > 0) {
            return 1;
        }
        return 0;
    }

    //获取用户的订单
    @Override
    public ArrayList<MallOrder> getUserOrderListSer(int user_id) {
        return userDao.getUserOrderListDao(user_id);
    }

    //根据id删除订单
    @Override
    public int deleteOrderByIdSer(int id) {
        return userDao.deleteOrderByIdDao(id);
    }

    //根据用户名查询用户
    @Override
    public Mall_user selectUserByUserNameSer(String username) {
        return userDao.selectUserByUserNameDao(username);
    }

    //注册用户
    @Override
    public int registerUserSer(Mall_user mall_user) {

        String oldPassword =mall_user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        mall_user.setSalt(salt);
        String md5Password = getMd5Password(oldPassword,salt);
        mall_user.setPassword(md5Password);

        return userDao.registerUserDao(mall_user);
    }

//    md5重复三次盐值两侧。
    private String getMd5Password(String password, String salt){
        for(int i=0;i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }

    //根据用户id查询用户详情
    @Override
    public Mall_user userInfoSer(int user_id) {
       Mall_user user =  userDao.userInfoDao(user_id);
       user.setPassword("******");
        return user;
    }

    //修改用户头像
    @Override
    public int updataUserTxSer(int user_id, String user_img) {
        return userDao.updataUserTxDao(user_id,user_img);
    }

    //修改用户信息
    @Override
    public int editUserInfoSer(Mall_user mall_user) {
        return userDao.editUserInfoDao(mall_user);
    }

//    修改用户密码
    @Override
    public int changeUserPassword(String username,String newpassword,String password){

        Mall_user user = selectUserByUserNameSer(username);
        System.out.println(user);
        String salt = user.getSalt();
        String dbpassword = user.getPassword();
        if(!dbpassword.equals(getMd5Password(password,salt))){
            return 0;
        }


        String salt1 = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(newpassword,salt1);

        user.setPassword(md5Password);
        user.setSalt(salt1);
        System.out.println(user);
        userDao.changeUserPassword(user);

        return 1;
    }

    //根据用户id查询用户反馈
    @Override
    public ArrayList<Feedback> getFeedbackListSer(int user_id) {
        return userDao.getFeedbackListDao(user_id);
    }

    //添加用户反馈
    @Override
    public int addFeedbackSer(Feedback feedback) {
        return userDao.addFeedbackDao(feedback);
    }

    //根据id删除用户反馈信息
    @Override
    public int removeFeedbackSer(int id) {
        return userDao.removeFeedbackByIdDao(id);
    }

    //获取用户的收藏列表
    @Override
    public ArrayList<CollectionBook> getCollectionListSer(int user_id) {
        return userDao.getCollectionListDao(user_id);
    }

    //删除收藏
    @Override
    public int deleteCollectionByIdSer(int id) {
        return userDao.deleteCollectionByIdDao(id);
    }

    //用户确定收货
    @Override
    public int receivingSer(int id) {
        return userDao.receivingDao(id);
    }
}
