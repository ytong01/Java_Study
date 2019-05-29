package com.rose.completablefuture;

//折扣 定义查询折扣的时候，是1秒
public enum Discount {

    NONE(0),SILVER(5),GOLD(10),PLATNUM(15),DIAMOND(20);

    private final int percent;

    Discount(int percent){
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

}


