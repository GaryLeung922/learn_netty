package com.xiaojiaqi.netty.test;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/18 10:33 AM
 */
public class Main {
    public static void main(String[] args) {
        int i = 0;
        for(int j=0;j<50;j++){
            i = i++;
            ++i;

        }
        System.out.println(i);
    }
}
