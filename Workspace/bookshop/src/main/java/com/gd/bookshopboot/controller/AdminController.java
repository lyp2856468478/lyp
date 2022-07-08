package com.gd.bookshopboot.controller;

import com.gd.bookshopboot.domain.*;
import com.gd.bookshopboot.service.AdminService;
import com.gd.bookshopboot.service.PublicService;
import com.gd.bookshopboot.util.OssUtil;
import com.gd.bookshopboot.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PublicService publicService;

    @Autowired
    private AdminService adminService;

    //管理员登录
    @RequestMapping("/login")
    public ResponseResult<Map> adminLogin(@RequestBody Admin adminlogin) {
        Map<String, Object> map = new HashMap<>();

        Admin admin = adminService.adminLoginSer(adminlogin.getAdmin_name(), adminlogin.getAdmin_password());

        if (admin != null) {
            map.put("loginYes", "loginYes");
            return new ResponseResult<Map>().success(map);
        }
        map.put("loginNo", "loginNo");
        return new ResponseResult<Map>().success(map);
    }

    //获取所有用户列表
    @RequestMapping("/getUserList")
    public ResponseResult<PageInfo> getUserList(String query, int pagenum, int pagesize) {
        if ("".equals(query) || query == "") {
            //查询所有用户列表，分页
            PageHelper.startPage(pagenum, pagesize);
            ArrayList<Mall_user> userList = adminService.getUserListSer();
            PageInfo info = new PageInfo(userList);
            return new ResponseResult<PageInfo>().success(info);
        } else {
            //查询所有用户列表，分页
            PageHelper.startPage(pagenum, pagesize);
            ArrayList<Mall_user> mall_user = adminService.selectUserByKeySer(query);
            PageInfo info = new PageInfo(mall_user);
            return new ResponseResult<PageInfo>().success(info);
        }
    }

    //根据用户id删除用户信息
    @RequestMapping("/removeUserById")
    public ResponseResult<Integer> removeUserById(Integer userid) {
        int i = adminService.removeUserByIdSer(userid);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //获取所有用户评论
    @RequestMapping("/getUserCommentList")
    public ResponseResult<PageInfo> getUserCommentList(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<Comment> userCommentList = adminService.getUserCommentListSer();
        PageInfo info = new PageInfo(userCommentList);

        return new ResponseResult<PageInfo>().success(info);
    }

    //根据id删除评论
    @RequestMapping("/removeCommentById")
    public ResponseResult<Integer> removeCommentById(Integer id) {
        int i = adminService.removeCommentByIdSer(id);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //获取所有用户反馈信息
    @RequestMapping("/getFeedbackInfoList")
    public ResponseResult<PageInfo> getFeedbackInfoList(int pagenum, int pagesize) {
        //分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<Feedback> feedbackInfoList = adminService.getFeedbackInfoListSer();
        PageInfo info = new PageInfo(feedbackInfoList);

        return new ResponseResult<PageInfo>().success(info);
    }

    //根据id修改此反馈信息为已读
    @RequestMapping("/updataReadById")
    public ResponseResult<Integer> updataReadById(Integer id) {
        int i = adminService.updataReadByIdSer(id);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //一键读取反馈信息
    @RequestMapping("/oneKeyRead")
    public ResponseResult<Integer> oneKeyRead(){
        int i = adminService.oneKeyReadSer();

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //获取所有轮播图列表
    @RequestMapping("/getRotationChartList")
    public ResponseResult<ArrayList> getRotationChartList() {
        ArrayList<RotationChart> rotationChartList = adminService.getRotationChartListSer();

        return new ResponseResult<ArrayList>().success(rotationChartList);
    }

    //上传轮播图图片到阿里云oss
    @RequestMapping("/uploadRotationChart")
    public Map<String, Object> uploadRotationChart(@RequestPart("rotationChartImg") MultipartFile uploadFile) throws IOException {
        Map<String, Object> map = new HashMap<>();
        //通过getOriginalFilename方法获取文件名称
        String filename = uploadFile.getOriginalFilename();

        if (uploadFile.getSize() > 0) {
            String upload = OssUtil.upload(filename, uploadFile.getInputStream());
            map.put("filename", filename);
            map.put("upload", upload);
            return map;
        }
        return null;
    }

    //添加轮播图图片
    @RequestMapping("/addRotationChart")
    public ResponseResult<Integer> addRotationChart(@RequestBody RotationChart rc) {
        int i = adminService.addRotationChartSer(rc);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //根据id删除轮播图图片
    @RequestMapping("/removeRotationChartById")
    public ResponseResult<Integer> removeRotationChartById(Integer id) {
        int i = adminService.removeRotationChartByIdSer(id);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //获取所有分类
    @RequestMapping("/getCategoryList")
    public ResponseResult<PageInfo> getCategoryList(String query, int pagenum, int pagesize) {
        if ("".equals(query) || query == "") {
            //分页
            PageHelper.startPage(pagenum, pagesize);
            ArrayList<MallCategory> categoryList = publicService.getBookCategoryListSer();

            PageInfo info = new PageInfo(categoryList);

            return new ResponseResult<PageInfo>().success(info);
        } else {
            //根据类别的关键字查询，分页
            PageHelper.startPage(pagenum, pagesize);
            ArrayList<MallCategory> mallCategories = adminService.selectCategoryByKeySer(query);
            PageInfo info = new PageInfo(mallCategories);
            return new ResponseResult<PageInfo>().success(info);
        }
    }

    //添加分类
    @RequestMapping("/addCategory")
    public ResponseResult<Integer> addCategory(String category_name) {
        int i = adminService.addCategorySer(category_name);
        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //根据类别id查询类别
    @RequestMapping("/queryCategoryById")
    public ResponseResult<MallCategory> queryCategoryById(Integer id) {
        MallCategory category = publicService.getCategoryByIdSer(id);

        return new ResponseResult<MallCategory>().success(category);
    }

    //修改类别
    @RequestMapping("/updataCategory")
    public ResponseResult<Integer> updataCategory(@RequestBody MallCategory mc) {
        int i = adminService.updataCategorySer(mc);

        if (i > 0) {
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //删除类别
    @RequestMapping("/removeCategory")
    public ResponseResult<String> removeCategory(Integer id) {
        //根据类别id查询图书，如果这个类别下有图书就不能删除
        ArrayList<MallBook> mallBooks = publicService.bookcategoryByIdSer(id);

        if (mallBooks.size() > 0) {
            return new ResponseResult<String>().success("removeNo1");
        } else {
            int i = adminService.removeCategorySer(id);
            if (i > 0) {
                return new ResponseResult<String>().success("removeYes");
            }
            return new ResponseResult<String>().success("removeNo2");
        }
    }

    //添加图书时获取所有类别
    @RequestMapping("/addBookGetCategoryList")
    public ResponseResult<ArrayList<MallCategory>> addBookGetCategoryList() {
        ArrayList<MallCategory> categoryList = publicService.getBookCategoryListSer();

        return new ResponseResult<ArrayList<MallCategory>>().success(categoryList);
    }

    //上传图书图片到阿里云
    @RequestMapping("/uploadBookImg")
    public Map<String, Object> uploadBookImg(@RequestPart("bookImg") MultipartFile uploadFile) throws IOException {
        Map<String, Object> map = new HashMap<>();
        //通过getOriginalFilename方法获取文件名称
        String filename = uploadFile.getOriginalFilename();

        if (uploadFile.getSize() > 0) {
            String upload = OssUtil.upload(filename, uploadFile.getInputStream());
            map.put("filename", filename);
            map.put("upload", upload);
            return map;
        }
        return null;
    }

    //添加图书
    @RequestMapping("/addBook")
    public ResponseResult<Integer> addBook(@RequestBody MallBook mallBook){
        int i = adminService.addBookSer(mallBook);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //根据图书id查询详情
    @RequestMapping("/getBookDetails")
    public ResponseResult<Map> getBookDetails(Integer book_id){
        Map<String,Object> map= new HashMap<>();
        //根据图书id查询图书详情
        MallBook book = publicService.getBookDetailsByIdSer(book_id);
        map.put("book",book);
        //然后再查询出所有类别
        ArrayList<MallCategory> categoryList = publicService.getBookCategoryListSer();
        map.put("categoryList",categoryList);

        return new ResponseResult<Map>().success(map);
    }

    //修改图书信息
    @RequestMapping("/editBookInfo")
    public ResponseResult<Integer> editBookInfo(@RequestBody MallBook mallBook){
        int i = adminService.editBookInfoSer(mallBook);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //根据id删除图书
    @RequestMapping("/removeBookById")
    public ResponseResult<Integer> removeBookById(Integer book_id){
        int i = adminService.removeBookByIdSer(book_id);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //获取所有未发货订单
    @RequestMapping("/getOrderList")
    public ResponseResult<PageInfo> getOrderList(int pagenum, int pagesize){
        //分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<MallOrder> orderList = adminService.getOrderListSer();
        PageInfo info = new PageInfo(orderList);

        return new ResponseResult<PageInfo>().success(info);
    }

    //发货
    @RequestMapping("/updataOrder")
    public ResponseResult<Integer> updataOrder(Integer order_id){
        int i = adminService.updataOrderSer(order_id);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //一键发货
    @RequestMapping("/oneKeyOrder")
    public ResponseResult<Integer> oneKeyOrder(){
        int i = adminService.oneKeyOrderSer();

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }

    //获取已发货订单列表
    @RequestMapping("/getShippedOrderList")
    public ResponseResult<PageInfo> getShippedOrderList(int pagenum, int pagesize){
        //分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<MallOrder> shippedOrderList = adminService.getShippedOrderListSer();
        PageInfo info = new PageInfo(shippedOrderList);

        return new ResponseResult<PageInfo>().success(info);

    }

    //根据id删除订单
    @RequestMapping("/removeOrderById")
    public ResponseResult<Integer> removeOrderById(Integer order_id){
        int i = adminService.removeOrderByIdSer(order_id);

        if (i>0){
            return new ResponseResult<Integer>().success(i);
        }
        return null;
    }
}
