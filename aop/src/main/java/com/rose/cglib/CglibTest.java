package com.rose.cglib;

public class CglibTest {

    public void method() {
        System.out.println("do something...");
    }

    public static void main(String[] args) {

        CglibProxy proxy = new CglibProxy();
        CglibTest target = (CglibTest) proxy.getProxy(CglibTest.class);
        target.method();
    }
}
