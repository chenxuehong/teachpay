package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class AuthInfoModel extends JsonListResult<AuthInfoModel.AuthInfoEntity> {
    public class AuthInfoEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 9642
         * user_id : 9641
         * create_time : 2020-09-26 15:22:21
         * aliPay : 1191769395@qq.com
         * wxPay :
         * name :
         * identity_type : ALIPAY_LOGON_ID
         * cash : 0
         * auth_code :
         * state :
         * access_token :
         * scope :
         * refresh_token :
         */


        private String id;
        private String user_id;
        private String create_time;
        private String aliPay;
        private String wxPay;
        private String name;
        private String identity_type;
        private String cash;
        private String auth_code;
        private String state;
        private String access_token;
        private String scope;
        private String refresh_token;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getAliPay() {
            return aliPay;
        }

        public void setAliPay(String aliPay) {
            this.aliPay = aliPay;
        }

        public String getWxPay() {
            return wxPay;
        }

        public void setWxPay(String wxPay) {
            this.wxPay = wxPay;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdentity_type() {
            return identity_type;
        }

        public void setIdentity_type(String identity_type) {
            this.identity_type = identity_type;
        }

        public String getCash() {
            return cash;
        }

        public void setCash(String cash) {
            this.cash = cash;
        }

        public String getAuth_code() {
            return auth_code;
        }

        public void setAuth_code(String auth_code) {
            this.auth_code = auth_code;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }
    }
}
