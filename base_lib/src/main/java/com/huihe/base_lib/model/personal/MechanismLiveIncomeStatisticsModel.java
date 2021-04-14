package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonResult;

import java.math.BigDecimal;

public class MechanismLiveIncomeStatisticsModel extends JsonResult<MechanismLiveIncomeStatisticsModel.MechanismLiveIncomeStatisticsEntity> {
    public static class MechanismLiveIncomeStatisticsEntity{

        /**
         * dayCash : 0
         * totalCash : 0
         * monthCash : 0
         */

        private BigDecimal dayCash;
        private BigDecimal totalCash;
        private BigDecimal monthCash;

        public BigDecimal getDayCash() {
            return dayCash;
        }

        public void setDayCash(BigDecimal dayCash) {
            this.dayCash = dayCash;
        }

        public BigDecimal getTotalCash() {
            return totalCash;
        }

        public void setTotalCash(BigDecimal totalCash) {
            this.totalCash = totalCash;
        }

        public BigDecimal getMonthCash() {
            return monthCash;
        }

        public void setMonthCash(BigDecimal monthCash) {
            this.monthCash = monthCash;
        }
    }
}
