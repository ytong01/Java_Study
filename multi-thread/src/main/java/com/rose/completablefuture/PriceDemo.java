package com.rose.completablefuture;

import com.sun.org.apache.xpath.internal.operations.Quo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PriceDemo {
    private List<Shop> shops = new ArrayList<>();


    public List<String> findPrices(String product) {
        /*
         * 方法一：加并行流.parallel()
         * */
//        return shops.parallelStream().map(shop -> String.format("%s price is %.2f ",shop.getName(),shop.getPice(product)))
//                .collect(Collectors.toList());

        Executor executor = Executors.newFixedThreadPool(40);
        List<CompletableFuture<String>> futureList = shops.stream().map(new Function<Shop, CompletableFuture<String>>() {
            @Override
            public CompletableFuture<String> apply(Shop shop) {

                return CompletableFuture.supplyAsync(new Supplier<String>() {
                    @Override
                    public String get() {
                        return String.format("%s price is %.2f ", shop.getName(), shop.getPice(product));
                    }
                }, executor);
            }
        }).collect(Collectors.toList());
        return futureList.stream().map(new Function<CompletableFuture<String>, String>() {
            @Override
            public String apply(CompletableFuture<String> future) {
                return future.join();
            }
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        PriceDemo priceDemo = new PriceDemo();
        for (int i = 0; i < 1; i++) {
            priceDemo.shops.add(new Shop("shop" + i));
        }
        Long start = System.currentTimeMillis();
        System.out.println(priceDemo.findPrices2("苹果x"));
        System.out.println("服务耗时：" + (System.currentTimeMillis() - start));
    }

    public List<String> findPrices2(String product) {
        Executor executor = Executors.newCachedThreadPool();
        List<CompletableFuture<Quote>> priceFuture = shops.stream()
                .map(new Function<Shop, CompletableFuture<Quote>>() {
                    @Override
                    public CompletableFuture<Quote> apply(Shop shop) {
                        CompletableFuture<Quote> quoteCompletableFuture = CompletableFuture
                                .supplyAsync(new Supplier<Double>() {
                                    @Override
                                    public Double get() {
                                        return shop.getPice(product);
                                    }
                                }, executor)
                                .thenCombine(CompletableFuture.supplyAsync(new Supplier<Double>() {
                                    @Override
                                    public Double get() {
                                        return ExchangeDemo.getRate("USA", "CNY");
                                    }
                                }), new BiFunction<Double, Double, Quote>() {
                                    @Override
                                    public Quote apply(Double price, Double rate) {
                                        return new Quote(shop, Discount.DIAMOND, price * rate);
                                    }
                                });
                        return quoteCompletableFuture;
                    };
                }).collect(Collectors.toList());

        return priceFuture.stream().map(new Function<CompletableFuture<Quote>, String>() {
            @Override
            public String apply(CompletableFuture<Quote> future) {
                Quote quote = future.join();
                return String.format("%s price is %.2f ", quote.getShop().getName(), quote.getPrice() * quote.getDiscount().getPercent());
            }
        }).collect(Collectors.toList());
    }

}
