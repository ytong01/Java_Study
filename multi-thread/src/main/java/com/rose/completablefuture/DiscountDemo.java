package com.rose.completablefuture;

import java.text.NumberFormat;

public class DiscountDemo {

    public static void delay() {

        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String applyDiscount(Quote quote){
        return quote.getShop()+" price is " +apply(quote.getPrice(),quote.getDiscount());
    }

    private static String apply(double price,Discount discussion) {
        delay();
        return NumberFormat.getInstance().format(price * (100 - discussion.getPercent())/100);
    }

}