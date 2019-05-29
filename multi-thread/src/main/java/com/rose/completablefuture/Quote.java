package com.rose.completablefuture;

public class Quote {
    private Shop shop;
    private Discount discount;
    private Double price;

    public Quote(Shop shop, Discount discount, Double price) {
        this.shop = shop;
        this.discount = discount;
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
