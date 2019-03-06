package com.bdqn.controller;

import org.apache.ibatis.reflection.SystemMetaObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class text {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));

        System.out.println(new Date(System.currentTimeMillis()));
    }
}
