package com.gd.bookshopboot.dao;

import com.gd.bookshopboot.domain.MallBook;
import com.gd.bookshopboot.domain.MallCategory;
import com.gd.bookshopboot.domain.RotationChart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface publicDao {

    /*随机查询13条类别*/
    ArrayList<MallCategory> getThirteenCategoryDao();

    /*获取轮播图*/
    ArrayList<RotationChart> getRotationChartListDao();

    /*随机查询8本图书*/
    ArrayList<MallBook> randomBookDao();

    /*获取最新上架的图书*/
    ArrayList<MallBook> getNewBookDao();

    /*获取首页热销图书*/
    ArrayList<MallBook> salesRankingDao();

    /*随机查询10本图书，猜你喜欢区*/
    ArrayList<MallBook> randomBook_twoDao();

    /*推送类别一样图书*/
    ArrayList<MallBook> randomBook_Bycategoryid(int categoryId);

    /*查询所有图书*/
    ArrayList<MallBook> getBookListDao();

    //根据关键字查询图书
    ArrayList<MallBook> selectkeybookDao(String key);

    /*根据图书类别ID查询图书*/
    ArrayList<MallBook> bookcategoryByIdDao(int categoryId);

    //根据图书id查询图书详情
    MallBook getBookDetailsByIdDao(Integer book_id);

    //根据图书类别id查询所属类别
    MallCategory getCategoryByIdDao(Integer category_id);

    //获取所有图书分类
    ArrayList<MallCategory> getBookCategoryListDao();

}
