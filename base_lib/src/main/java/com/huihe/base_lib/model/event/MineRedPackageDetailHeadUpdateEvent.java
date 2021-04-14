package com.huihe.base_lib.model.event;

public class MineRedPackageDetailHeadUpdateEvent {

    private int catCoinAdd;
    private int catCoinDown;

    public int getCatCoinAdd() {
        return catCoinAdd;
    }

    public int getCatCoinDown() {
        return catCoinDown;
    }

    public MineRedPackageDetailHeadUpdateEvent(int catCoinAdd, int catCoinDown) {

        this.catCoinAdd = catCoinAdd;
        this.catCoinDown = catCoinDown;
    }
}
