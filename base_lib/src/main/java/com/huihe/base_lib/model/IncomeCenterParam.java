package com.huihe.base_lib.model;

import java.math.BigDecimal;

public class IncomeCenterParam {

    /**
     * @desc 个人收益、机构收益、外教推荐官收益、机构推荐官收益
     */
    public String title;
    // 总收益
    public BigDecimal totalIncome;
    // 今日收益
    public BigDecimal todayIncome;

    public IncomeCenterParam(String title, BigDecimal totalIncome, BigDecimal todayIncome) {
        this.title = title;
        this.totalIncome = totalIncome;
        this.todayIncome = todayIncome;
    }
}
