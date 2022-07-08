package com.gd.bookshopboot.service.publicimpl;

import com.gd.bookshopboot.dao.publicDao;
import com.gd.bookshopboot.domain.MallBook;
import com.gd.bookshopboot.domain.MallCategory;
import com.gd.bookshopboot.domain.RotationChart;
import com.gd.bookshopboot.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class publicServiceImpl implements PublicService {

    @Autowired
    private publicDao publicDao;

    /*随机查询13条类别*/
    @Override
    public ArrayList<MallCategory> getThirteenCategorySer() {
        return publicDao.getThirteenCategoryDao();
    }

    /*获取轮播图*/
    @Override
    public ArrayList<RotationChart> getRotationChartListSer() {
        return publicDao.getRotationChartListDao();
    }

    /*随机查询8本图书*/
    @Override
    public ArrayList<MallBook> randomBookSer() {
        return publicDao.randomBookDao();
    }

    /*获取最新上架的图书*/
    @Override
    public ArrayList<MallBook> getNewBookSer() {
        return publicDao.getNewBookDao();
    }

    /*获取首页热销图书*/
    @Override
    public ArrayList<MallBook> salesRankingSer() {
        return publicDao.salesRankingDao();
    }

    /*随机查询10本图书，猜你喜欢区*/
    @Override
    public ArrayList<MallBook> randomBook_twoSer() {
        return publicDao.randomBook_twoDao();
    }

//    推荐类别一样的图书
    @Override
    public ArrayList<MallBook> randomBook_byCategoryID(int categoryId) {
        return publicDao.randomBook_Bycategoryid(categoryId);
    }

    /*查询所有图书*/
    @Override
    public ArrayList<MallBook> getBookListSer() {
        return publicDao.getBookListDao();
    }

    /*根据关键字查询图书*/
    @Override
    public ArrayList<MallBook> selectkeybookSer(String key) {
        return publicDao.selectkeybookDao(key);
    }

    /*根据图书类别ID查询图书*/
    @Override
    public ArrayList<MallBook> bookcategoryByIdSer(int categoryId) {
        return publicDao.bookcategoryByIdDao(categoryId);
    }

    /*根据图书id查询图书*/
    @Override
    public MallBook getBookDetailsByIdSer(Integer book_id) {
        return publicDao.getBookDetailsByIdDao(book_id);
    }

    /*根据图书类别id查询所属类别*/
    @Override
    public MallCategory getCategoryByIdSer(Integer category_id) {
        return publicDao.getCategoryByIdDao(category_id);
    }

    //查询所有图书类别
    @Override
    public ArrayList<MallCategory> getBookCategoryListSer() {
        return publicDao.getBookCategoryListDao();
    }


}
