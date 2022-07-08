package com.gd.bookshopboot.controller;

import com.gd.bookshopboot.domain.MallBook;
import com.gd.bookshopboot.domain.MallCategory;
import com.gd.bookshopboot.domain.RotationChart;
import com.gd.bookshopboot.service.PublicService;
import com.gd.bookshopboot.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private PublicService publicService;

    /*随机查询13条图书类别*/
    @RequestMapping("/getThirteenCategory")
    public ResponseResult<ArrayList<MallCategory>> getThirteenCategory(){
        ArrayList<MallCategory> thirteenCategorySer = publicService.getThirteenCategorySer();

        return new ResponseResult<ArrayList<MallCategory>>().success(thirteenCategorySer);
    }

    /*获取轮播图*/
    @RequestMapping("/getRotationChartList")
    public ResponseResult<ArrayList<RotationChart>> getRotationChartList(){
        ArrayList<RotationChart> rotationChartList = publicService.getRotationChartListSer();

        return new ResponseResult<ArrayList<RotationChart>>().success(rotationChartList);
    }

    /*随机查询8本图书*/
    @RequestMapping("/randomBook")
    public ResponseResult<ArrayList<MallBook>> randomBook(){
        ArrayList<MallBook> mallBooks = publicService.randomBookSer();

        return new ResponseResult<ArrayList<MallBook>>().success(mallBooks);
    }

    /*获取最新上架的图书*/
    @RequestMapping("/getNewBook")
    public ResponseResult<ArrayList<MallBook>> getNewBook(){
        ArrayList<MallBook> newBookSer = publicService.getNewBookSer();

        return new ResponseResult<ArrayList<MallBook>>().success(newBookSer);
    }

    /*获取首页热销图书*/
    @RequestMapping("/salesRanking")
    public ResponseResult<ArrayList<MallBook>> salesRanking(){
        ArrayList<MallBook> mallBooks = publicService.salesRankingSer();

        return new ResponseResult<ArrayList<MallBook>>().success(mallBooks);
    }

    /*随机查询10本图书，猜你喜欢区*/
    @RequestMapping("/randomBook_two")
    public ResponseResult<ArrayList<MallBook>> randomBook_two(){
        ArrayList<MallBook> mallBooks = publicService.randomBook_twoSer();

        return new ResponseResult<ArrayList<MallBook>>().success(mallBooks);
    }

    /*推荐类别一样的图书*/
    @RequestMapping("/randomBook_three")
    public ResponseResult<ArrayList<MallBook>> randomBook_three(int categoryId){

        ArrayList<MallBook> mallBooks = publicService.randomBook_byCategoryID(categoryId);

        return new ResponseResult<ArrayList<MallBook>>().success(mallBooks);
    }


    /*查询全部图书*/
    @RequestMapping("/getBookList")
    public ResponseResult<PageInfo> getBookList(String query, int pagenum, int pagesize){
        if ("".equals(query) || query == "") {
            //查询所有图书，分页
            PageHelper.startPage(pagenum,pagesize);
            ArrayList<MallBook> bookListSer = publicService.getBookListSer();
            PageInfo info = new PageInfo(bookListSer);
            return new ResponseResult<PageInfo>().success(info);
        }else {
            //模糊查询图书
            PageHelper.startPage(pagenum, pagesize);
            ArrayList<MallBook> mallBooks = publicService.selectkeybookSer(query);
            PageInfo info = new PageInfo(mallBooks);
            return new ResponseResult<PageInfo>().success(info);
        }
    }

    /*根据图书类别ID查询图书*/
    @RequestMapping("/bookcategoryById")
    public ResponseResult<PageInfo> bookcategoryById(int categoryId, int pagenum, int pagesize) {
        //分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<MallBook> mallBooks = publicService.bookcategoryByIdSer(categoryId);
        PageInfo info = new PageInfo(mallBooks);

        return new ResponseResult<PageInfo>().success(info);
    }

    /*根据图书id查询详情*/
    @RequestMapping("/getBookDetails")
    public ResponseResult<Map> getBookDetails(Integer book_id){
        //根据图书id查询图书详情
        MallBook book = publicService.getBookDetailsByIdSer(book_id);

        //查询到图书详情后根据这本图书类别id查询所属类别
        MallCategory category = publicService.getCategoryByIdSer(book.getCategory_id());
        Map<String,Object> map= new HashMap<>();
        map.put("book",book);
        map.put("category",category);

        return new ResponseResult<Map>().success(map);
    }

    /*获取所有分类1，用于分页查询*/
    @RequestMapping("/getBookCategoryListOne")
    public ResponseResult<PageInfo> getBookCategoryListOne(int pagenum, int pagesize) {
        //分页
        PageHelper.startPage(pagenum, pagesize);
        ArrayList<MallCategory> bookCategoryListSer = publicService.getBookCategoryListSer();

        PageInfo info=new PageInfo(bookCategoryListSer);
        return new  ResponseResult<PageInfo>().success(info);
    }

    /*获取所有分类2，用于下拉餐单*/
    @RequestMapping("/getBookCategoryListTwo")
    public ResponseResult<ArrayList<MallCategory>> getBookCategoryListTwo(){
        ArrayList<MallCategory> bookCategoryListSer = publicService.getBookCategoryListSer();

        return new ResponseResult<ArrayList<MallCategory>>().success(bookCategoryListSer);
    }

    /*获取根据类别id查询类别*/
    @RequestMapping("/getCategoryById")
    public ResponseResult<MallCategory> getCategoryById(Integer category_id){
        MallCategory categoryById = publicService.getCategoryByIdSer(category_id);

        return new ResponseResult<MallCategory>().success(categoryById);
    }

}
