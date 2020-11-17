package javademo.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {
    public static final Integer A_PAGE_SIZE = 6;//一页展示的条数
    public static final Integer PAGE_NUM = 5;//页码展示的数量
    //上一页
    public static Integer prePageControl(Integer page){
        return page==1 ? page : page - 1;
    }
    public static Integer nextPageControl(Integer page, Integer listCount){
        //算最大页数
        Integer pages = listCount % A_PAGE_SIZE == 0 ? listCount / A_PAGE_SIZE : listCount / A_PAGE_SIZE + 1;
        Integer nextPage = null;
        if(page.equals(pages)){
            nextPage = pages;
        } else {
            nextPage = page + 1;
        }
        return nextPage;
    }
    public static List<Integer> pageNumListControl(Integer page, Integer listCount){
        List<Integer> integerList = new ArrayList<>();
        //算最大页数
        Integer pages = listCount % A_PAGE_SIZE == 0 ? listCount / A_PAGE_SIZE : listCount / A_PAGE_SIZE + 1;
        Integer minPagesNum = null;
        Integer maxPagesNum = null;
        if(page % PAGE_NUM == 0){
            minPagesNum = page - PAGE_NUM + 1;
            maxPagesNum = page;
        } else {
            minPagesNum = page - page % PAGE_NUM + 1;
            maxPagesNum = (page + PAGE_NUM - page % PAGE_NUM>=pages) ? pages:(page + PAGE_NUM - page % PAGE_NUM);
        }
        for(int i = minPagesNum;i<= maxPagesNum;i++){
            integerList.add(i);//添加最小页到最大页之间的页码
        }
        return integerList;
    }
    public static Integer pagesControl(Integer listCount){
        int m = (listCount % A_PAGE_SIZE == 0) ? (listCount / A_PAGE_SIZE) : (listCount / A_PAGE_SIZE + 1);
        if(m == 0)
            return m+1;
        else
            return m;
    }
}
