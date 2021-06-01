package com.atguigu.bookstore.test;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    /*
       测试使用BigDecimal解决double类型计算精度问题
    */
    @Test
    public void testBigDecimal(){
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(2);
        //加
        BigDecimal add = a.add(b);
        System.out.println(add);
        //返回int类型的值
        System.out.println(add.intValue());
        //减
        BigDecimal subtract = a.subtract(b);
        System.out.println(subtract);
        //乘
        BigDecimal multiply = a.multiply(b);
        System.out.println(multiply);
        //除
        BigDecimal divide = a.divide(b);
        System.out.println(divide);
        //使用BigDecimal解决double类型的计算精度问题（需要使用String类型的构造器）
        BigDecimal bigDecimal = new BigDecimal("0.01");
        BigDecimal bigDecimal2 = new BigDecimal("0.09");
        //加
        BigDecimal add1 = bigDecimal.add(bigDecimal2);
        System.out.println(add1.doubleValue());

    }
}
