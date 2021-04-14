package com.huihe.base_lib.model.personal;

import com.google.gson.annotations.SerializedName;
import com.huihe.base_lib.model.base.JsonResult;

public class WxPayModel extends JsonResult<WxPayModel.WxPayEntity> {

    public class WxPayEntity{

        /**
         * package : Sign=WXPay
         * paySign : BC7E231963C491C6B598FDE241CC6AC9
         * appid : wx488f197a77f6021b
         * partnerid : 1488811802
         * prepayid : wx311554304264370adf6835391035951900
         * noncestr : 2fa8797b43934c348bdbecaf19be7931
         * timestamp : 1580457157
         * prepay_id : wx311554304264370adf6835391035951900
         */

        @SerializedName("package")
        private String packageX;
        private String paySign;
        private String appid;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;
        private String prepay_id;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }
    }
}
