package com.yryz.writer.common.test;


public class Test {
    public static void main(String[] args) {


        Point<String> p = null;
        p = new Point<>("张三");
        p.setVar("李四");
        System.out.println(p.getVar());
    }
}
