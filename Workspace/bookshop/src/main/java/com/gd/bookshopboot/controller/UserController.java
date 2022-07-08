package com.gd.bookshopboot.controller;

import com.alibaba.fastjson.JSON;
import com.gd.bookshopboot.domain.*;
import com.gd.bookshopboot.service.UserService;
import com.gd.bookshopboot.util.OssUtil;
import com.gd.bookshopboot.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*用户登录*/
    @RequestMapping("/userLogin")
    public ResponseResult<Map> userLogin(String username, String password, String vercode, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Object attribute = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if (!vercode.equals(attribute)) {
            map.put("vercodeError", "验证码错误");
            return new ResponseResult<Map>().success(map);
        }

        Mall_user user = userService.userLoginSer(username, password);
        if (user != null) {
            map.put("loginYes", user);
            return new ResponseResult<Map>().success(map);
        }

        map.put("loginNo", "查无此人");
        return new ResponseResult<Map>().success(map);
    }

    /*获取指定图书的用户评论*/
    @RequestMapping("/getCommentList")
    public ResponseResult<PageInfo> getCommentList(Integer book_id, int pagenum, int pagesize){
        if (book_id != null) {
            //查询指定图书评论，分页
            PageHelper.startPage(pagenum, pagesize);
            ArrayList<Comment> commentList = userService.getCommentListSer(book_id);

            PageInfo info = new PageInfo(commentList);
            return new ResponseResult<PageInfo>().success(info);
        }
        return new ResponseResult<PageInfo>().error();
    }

    /*添加用户评论*/
    @RequestMapping("/addComment")
    public ResponseResult<Integer> addComment(@RequestBody Comment comment){
        comment.setComment_time(new Date());

        int i = userService.addCommentSer(comment);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*根据id删除评论*/
    @RequestMapping("/deleteCommentById")
    public ResponseResult<Integer> deleteCommentById(Integer id){
        int i = userService.deleteCommentByIdSer(id);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*用户收藏*/
    @RequestMapping("/collectionBook")
    public ResponseResult<Map> collectionBook(@RequestBody CollectionBook coll){
        Map<String, Object> map = new HashMap<>();

        //添加收藏之前先查询收藏表中有没有这本图书，如果有就提示用户已收藏了，不需要重复收藏，如果没有才可以添加
        CollectionBook collectionBook = userService.queryCollBookByIdSer(coll.getBook_id());

        if (collectionBook != null) {
            map.put("collectionNo", "collectionNo");
            return new ResponseResult<Map>().success(map);
        }

        //如果收藏表中没有这本图书就添加收藏
        coll.setCollection_time(new Date());
        int i = userService.addCollectionSer(coll);

        if (i>0){
            map.put("collectionYes", "collectionYes");
            return new ResponseResult<Map>().success(map);
        }
        return null;
    }

    /*用户立即购买加入订单，加入订单后修改图书库存和销量*/
    @RequestMapping("/addShoppingCart")
    public ResponseResult<Integer> addShoppingCart(@RequestBody MallOrder order){
        order.setBuy_time(new Date());

        //用户购买的同时需要修改图书的库存和销量
        int j = userService.updataBookStockSer(order.getBook_id(), order.getBuy_num());

        int i = userService.addShoppingCartSer(order);

        if (i > 0 && j > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;

    }

    /*加入购物车*/
    @RequestMapping("/addCart")
    public ResponseResult<Integer> addCart(@RequestBody MallCart mallCart){
        //加入购物车之前先查询一下购物车中有没有本商品，如果有就只需要修改数量，如果没有才加入购物车
        int book_id = mallCart.getBook_id();
        int num = mallCart.getNum();

        //查询购物车有没有本商品
        MallCart mallCart1 = userService.selectCartbookByIdSer(book_id);

        if (mallCart1 == null) {
            //没有本商品就添加购物车
            int i = userService.addCartSer(mallCart);

            if (i > 0) {
                return new ResponseResult<Integer>().success(i);
            }
            return null;
        }

        int cart_id = mallCart1.getCart_id();
        int i = userService.updataCartbookNumSer(book_id, num, cart_id);
        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*根据用户id查询购物车信息*/
    @RequestMapping("/getCartByUserId")
    public ResponseResult<Map> getCartByUserId(int user_id, int pagenum, int pagesize){
        Map<String, Object> map = new HashMap<>();

        double totalprice = 0;

        ArrayList<MallCart> cartByUserIdSer = userService.getCartByUserIdSer(user_id);
        for (MallCart mallCart : cartByUserIdSer) {
            totalprice+=mallCart.getPrice() * mallCart.getNum();
        }

        //查询用户购物车信息，分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<MallCart> cartByUserId = userService.getCartByUserIdSer(user_id);

        PageInfo info = new PageInfo(cartByUserId);

        map.put("totalprice", totalprice);
        map.put("info", info);

        return new ResponseResult<Map>().success(map);
    }

    /*修改购物车中图书的数量*/
    @RequestMapping("/buyNumChange")
    public ResponseResult<Integer> buyNumChange(Integer cart_id,Integer num) {
        int i = userService.buyNumChangeSer(cart_id, num);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    @RequestMapping("/deleteRow")
    public ResponseResult<Integer> deleteRow(int cart_id) {
        int i = userService.deleteRowSer(cart_id);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*结算商品*/
    @RequestMapping("/receiptInfo")
    public ResponseResult<Integer> receiptInfo(String carts, String nick_name, String tel, String address) {
        int i = userService.delectCartById(carts, nick_name, tel, address);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*清空购物车*/
    @RequestMapping("/emptyCart")
    public ResponseResult<Integer> emptyCart(int user_id, String nick_name, String tel, String address){
        int i = userService.emptyCartSer(user_id, nick_name, tel, address);
        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*查询用户所有的订单*/
    @RequestMapping("/getUserOrderList")
    public ResponseResult<PageInfo> getUserOrderList(Integer user_id, int pagenum, int pagesize){
        //查询所有订单，分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<MallOrder> userOrderList = userService.getUserOrderListSer(user_id);

        PageInfo info = new PageInfo(userOrderList);
        return new ResponseResult<PageInfo>().success(info);
    }

    /*根据id删除订单*/
    @RequestMapping("/deleteOrderById")
    public ResponseResult<Integer> deleteOrderById(int id) {
        int i = userService.deleteOrderByIdSer(id);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*注册用户*/
    @RequestMapping("/registerUser")
    public ResponseResult<Map> registerUser(Mall_user user) {
        Map<String, Object> map = new HashMap<>();
        //注册用户之前先查询一下用户名有没有被注册
        String username = user.getUsername();
        Mall_user mall_user = userService.selectUserByUserNameSer(username);

        if (mall_user != null) {
            map.put("registerNo","registerNo");
            return new ResponseResult<Map>().success(map);
        }

        System.out.println(new Date());
        int i = userService.registerUserSer(user);
        if (i>0){
            map.put("registerYes","registerYes");
            return new ResponseResult<Map>().success(map);
        }
        return null;
    }

    /*根据用户id查询用户详情*/
    @RequestMapping("/userInfo")
    public ResponseResult<Mall_user> userInfo(int user_id) {
        Mall_user mall_user = userService.userInfoSer(user_id);

        return new ResponseResult<Mall_user>().success(mall_user);
    }

    /*上传用户头像头像到阿里云*/
    @RequestMapping("/uploadUserTx")
    public String uploadUserTx(@RequestPart("userImg") MultipartFile uploadFile) throws IOException {
        //通过getOriginalFilename方法获取文件名称
        String filename = uploadFile.getOriginalFilename();

        if (uploadFile.getSize() > 0) {
            return OssUtil.upload(filename, uploadFile.getInputStream());
        }
        return "";
    }

    /*修改用户头像*/
    @RequestMapping("/updataUserTx")
    public ResponseResult<Integer> updataUserTx(int user_id, String user_img) {
        int i = userService.updataUserTxSer(user_id, user_img);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*修改用户信息*/
    @RequestMapping("/editUserInfo")
    public ResponseResult<Integer> editUserInfo(String user) {
        //将前端传递过来的参数用字符串接收
        //通过JSON.parseObject方法将JSON格式的字符串解析成一个对象
        Mall_user mall_user = JSON.parseObject(user, Mall_user.class);

        int i = userService.editUserInfoSer(mall_user);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

//    修改用户密码
    @RequestMapping("/updateUserPassword")
    public ResponseResult<Integer> updateUserPassword(String username,String newpassword,String password){
        int i = userService.changeUserPassword(username,newpassword,password);
        if(i==1){
            return new ResponseResult<Integer>().success(i);
        }
         else{
            return new ResponseResult<Integer>().success(0);
        }
    }

    //根据用户id查询用户反馈
    @RequestMapping("/getFeedbackList")
    public ResponseResult<PageInfo> getFeedbackList(Integer user_id,int pagenum, int pagesize){
        //查询用户反馈，分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<Feedback> feedbackList = userService.getFeedbackListSer(user_id);
        PageInfo info=new PageInfo(feedbackList);

        return new ResponseResult<PageInfo>().success(info);
    }

    /*添加用户反馈*/
    @RequestMapping("/addFeedback")
    public ResponseResult<Integer> addFeedback(@RequestBody Feedback feedback){
        feedback.setFeedback_time(new Date());

        int i = userService.addFeedbackSer(feedback);
        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*根据id删除用户反馈信息*/
    @RequestMapping("/removeFeedbackById")
    public ResponseResult<Integer> removeFeedbackById(Integer id){
        int i = userService.removeFeedbackSer(id);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*获取用户的收藏列表*/
    @RequestMapping("/getCollectionList")
    public ResponseResult<PageInfo> getCollectionList(int user_id, int pagenum, int pagesize) {
        //查询用户收藏，分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<CollectionBook> collectionListSer = userService.getCollectionListSer(user_id);

        PageInfo info = new PageInfo(collectionListSer);
        return new ResponseResult<PageInfo>().success(info);
    }

    /*删除收藏*/
    @RequestMapping("/deleteCollectionById")
    public ResponseResult<Integer> deleteCollectionById(int id) {
        int i = userService.deleteCollectionByIdSer(id);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    /*用户确定收货*/
    @RequestMapping("/receiving")
    public ResponseResult<Integer> receiving(Integer id){
        int i = userService.receivingSer(id);

        if (i>0){
            return new  ResponseResult<Integer>().success(i);
        }
        return null;
    }

}
