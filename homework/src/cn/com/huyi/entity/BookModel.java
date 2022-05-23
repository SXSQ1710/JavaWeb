package cn.com.huyi.entity;

import org.junit.Test;

import java.net.PortUnreachableException;
import java.util.ArrayList;

/**
 * @title: BookModel
 * @Author SXSQ
 * @Description //TODO Book的实体类
 * @Date 2022/3/25 10:45
 **/

public class BookModel {
    private String isbn;
    private String name;
    private String sort;
    private double price;
    private enum Sort {体育类, 军事类, 历史类, 哲学类, 数学类, 天文学类, 政治类, 教育类, 文化类, 法律类}

    public BookModel() {
    }

    public BookModel(String isbn, String name, String sort, float price) {
        this.isbn = isbn;
        this.name = name;
        this.setSort(sort);
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        Sort[] values = Sort.values();
        for (Sort sort1 :values){
            String s = String.valueOf(sort1);
            if (s.equals(sort)) this.sort = s;
        }
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(float price) {
        this.price = price;
    }

    public static ArrayList<String> getAllSort(){
        ArrayList<String> sortlist = new ArrayList<>();
        for (Sort a :Sort.values()){
            sortlist.add(a.toString());
        }
        return sortlist;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                ", price=" + price +
                '}';
    }
}
