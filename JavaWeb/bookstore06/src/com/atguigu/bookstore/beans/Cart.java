package com.atguigu.bookstore.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

//购物车类
public class Cart implements Serializable {
    //Map的key是图书的id，Map的值对应的购物项
    private Map<String , CartItem> map = new LinkedHashMap<>();
    private int totalCount; //购物车中图书的总数量，通过计算得到
    private double totalAmount; //购物车中图书的总金额，通过计算得到

    //添加图书到购物车的方法
    public void addBook2Cart(Book book){
        //根据图书的id从购物车中获取对应的购物项
        CartItem cartItem = map.get(book.getId() + "");
        if(cartItem == null){
            //证明当前购物车中还没有该图书对应的购物项，此时需要创建该购物项
            cartItem = new CartItem();
            //将book设置到购物项中
            cartItem.setBook(book);
            //设置购物项中该图书的数量为1
            cartItem.setCount(1);
            //将购物项放到购物车中
            map.put(book.getId()+"",cartItem);
        }else{
            //购物车中已经有该购物项，此时只需要将购物项中图书的数量加1即可
            //获取当前购物项中图书的数量
            int count = cartItem.getCount();
            //设置当前购物项中图书的数量为count+1
            cartItem.setCount(count+1);
        }
    }

    //更新购物项的方法
    public void updateCarItem(String bookId , String bookCount){
        //从map中获取要更新的购物项
        CartItem cartItem = map.get(bookId);
        try {
            //将图书的数量转为Interger类型
            int count = Integer.parseInt(bookCount);
            //判断数量是否是大于0的数
            if(count > 0){
                //设置当前购物项中的数量为count
                cartItem.setCount(count);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    //删除购物项的方法
    public void deleteCartItem(String bookId){
        //删除map中对应的购物项
        map.remove(bookId);
    }

    //清空购物车的方法
    public void clearCart(){
        //清空map中所有的购物项
        map.clear();
    }

    //获取Map中所以的购物项的方法
    public List<CartItem> getCartItems(){
        //获取Map中所以的购物项
        Collection<CartItem> values = map.values();
        return new ArrayList<>(values);
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    //购物车中图书的总数量是每个购物项中图书的数量之和
    public int getTotalCount() {
        int totalCount = 0;
        //获取所以的购物项
        List<CartItem> cartItems = getCartItems();
        //将购物项中的图书的数量进行累加
        for (CartItem cartItem : cartItems) {
            totalCount = totalCount + cartItem.getCount();
        }
        return totalCount;
    }

//    public void setTotalCount(int totalCount) {
//        this.totalCount = totalCount;
//    }

    //购物车中图书的总金额是每个购物项中图书的金额小计之和
    public double getTotalAmount() {
//        double totalAmount = 0;
        BigDecimal bigTotalAmount = new BigDecimal("0");
        //获取所有的购物项
        List<CartItem> cartItems = getCartItems();
        //将购物项中的金额小计进行累加
        for (CartItem cartItem : cartItems) {
//            totalAmount = totalAmount + cartItem.getAmount();
            BigDecimal bigAmount = new BigDecimal(cartItem.getAmount() + "");
            bigTotalAmount = bigTotalAmount.add(bigAmount);
        }
        return bigTotalAmount.doubleValue();
    }

//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }

    @Override
    public String toString() {
        return "Cart{" +
                "map=" + map +
                ", totalCount=" + totalCount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
