package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.base.JsonResult;

public class NeedPaymentModel extends JsonResult<NeedPaymentModel.NeedPaymentEntity> {
    public static class NeedPaymentEntity{

        /**
         * price : 3
         * needPayment : true
         */

        private String price;
        private boolean needPayment;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public boolean isNeedPayment() {
            return needPayment;
        }

        public void setNeedPayment(boolean needPayment) {
            this.needPayment = needPayment;
        }
    }
}
