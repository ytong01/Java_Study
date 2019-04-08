package com.rose.javassist;

public class JavassistTest {

    private String prop;

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public void method() {
        System.out.println("do something..." + prop);
    }
}
