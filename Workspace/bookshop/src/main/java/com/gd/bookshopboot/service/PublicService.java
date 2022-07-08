package com.gd.bookshopboot.service;

import com.gd.bookshopboot.domain.MallBook;
import com.gd.bookshopboot.domain.MallCategory;
import com.gd.bookshopboot.domain.RotationChart;

import java.util.ArrayList;

public interface PublicService {
    /*随机查询13条类别*/
    ArrayList<MallCategory> getThirteenCategorySer();

    /*获取轮播图*/
    ArrayList<RotationChart> getRotationChartListSer();

    /*随机查询8本图书*/
    ArrayList<MallBook> randomBookSer();

    /*获取最新上架的图书*/
    ArrayList<MallBook> getNewBookSer();

    /*获取首页热销图书*/
    ArrayList<MallBook> salesRankingSer();

    /*随机查询10本图书，猜你喜欢区*/
    ArrayList<MallBook> randomBook_twoSer();

    //    推荐类别一样的图书
    ArrayList<MallBook> randomBook_byCategoryID(int categoryId);

    /*查询所有图书*/
    ArrayList<MallBook> getBookListSer();

    /*根据关键字查询图书*/
    ArrayList<MallBook> selectkeybookSer(String key);

    /*根据图书类别ID查询图书*/
    ArrayList<MallBook> bookcategoryByIdSer(int categoryId);

    //根据图书id查询详情
    MallBook getBookDetailsByIdSer(Integer book_id);

    //根据图书类别id查询所属类别
    MallCategory getCategoryByIdSer(Integer category_id);

    //查询所有图书类别
    ArrayList<MallCategory> getBookCategoryListSer();
}
