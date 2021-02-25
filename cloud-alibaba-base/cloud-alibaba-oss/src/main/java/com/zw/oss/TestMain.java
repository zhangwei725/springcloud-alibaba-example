package com.zw.oss;

import sun.rmi.runtime.Log;

public class TestMain {
    public static void main(String[] args) {
        String image = "123.1231321.png";
        String substring = image.substring(image.lastIndexOf("."));
        System.out.println(substring);
    }

}
