package com.atguigu.bookstore.beans;

import java.util.List;

public class Page<T> {
    private List<T> list; //每页显示的记录存放的集合
    private static final int PAGE_SIZE = 5; //设置每页显示5条记录
    private int pageNo; //当前页，通过用户传入
    private int totalPageNo; //总页数，通过计算得到
    private int totalRecord; //总记录数，通过查询数据库得到

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public int getPageNo() {
        //如果当前页是一个小于等于0的数则返回第1页
        if(pageNo <= 0){
            return 1;
        //如果当前页大于总页数则返回总页数
        }else if(pageNo > getTotalPageNo()){
            return getTotalPageNo();
        }else{
            return pageNo;
        }
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    //总页数由总记录数和每页显示的条数计算得到
    public int getTotalPageNo() {
        //如果总记录数能除尽PAGE_SIZE，则总页数是它们的商
        if(totalRecord % PAGE_SIZE == 0){
            totalPageNo = totalRecord / PAGE_SIZE;
        }
        //如果总记录数不能除尽PAGE_SIZE，则总页数是它们的商加1
        else{
            totalPageNo = totalRecord / PAGE_SIZE + 1;
        }
        return totalPageNo;
    }

//    public void setTotalPageNo(int totalPageNo) {
//        this.totalPageNo = totalPageNo;
//    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    @Override
    public String toString() {
        return "Page{" +
                "list=" + list +
                ", pageNo=" + pageNo +
                ", totalPageNo=" + totalPageNo +
                ", totalRecord=" + totalRecord +
                '}';
    }
}
