package com.rose.completablefuture;

//汇率的服务 定义也是1秒
public class ExchangeDemo {
 
    public static void delay() {
        try {
            Thread.sleep(1000l);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}
    public static double getRate(String source,String target){
        delay();
        return 10;
    }
 
}
