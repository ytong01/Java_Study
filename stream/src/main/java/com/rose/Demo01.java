package com.rose;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo01 {

    public static void main(String[] args) {

        List<String> list = IntStream.range(0, 10).mapToObj(new IntFunction<String>() {
            @Override
            public String apply(int value) {
                return value + "";
            }
        }).collect(Collectors.toList());
        list.parallelStream().forEachOrdered(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
