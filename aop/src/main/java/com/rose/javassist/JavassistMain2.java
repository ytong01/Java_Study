package com.rose.javassist;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

public class JavassistMain2 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(JavassistTest.class);
        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method method) {
                return method.getName().equals("method");
            }
        });

        factory.setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                System.out.println("前置处理");
                Object result = proceed.invoke(self, args);
                System.out.println("后置处理");
                return result;
            }
        });
        Class clazz = factory.createClass();
        JavassistTest proxy = (JavassistTest) clazz.newInstance();
        proxy.method();
    }
}
