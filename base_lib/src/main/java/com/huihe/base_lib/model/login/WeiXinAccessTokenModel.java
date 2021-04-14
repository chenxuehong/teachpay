package com.huihe.base_lib.model.login;

import com.huihe.base_lib.model.base.JsonResult;

public class WeiXinAccessTokenModel extends JsonResult<WeiXinAccessTokenModel.WeiXinAccessTokenEntity> {

    public static class WeiXinAccessTokenEntity{


        /**
         * access_token : 31_Rf9HTewhE1njAKpHZZ8709idSOcAUm2cOgwnyVwGb4vCyOrBigOJ0WI7TtdEzTQpPB6-xl2E915jQ5mTJZ3ruHSJFBcHmvYcPjLgRUb7j9k
         * expires_in : 7200
         * refresh_token : 31_UkuT0hwIC5e_eLtrkHQCOlDMJlIGfofOVEzvAGvUkGVN0y8B2YyqmHRJyN_-jM_IXKCnAlOaQiMzJTYU8EYKsRD7C5zER2aiPUMngnBlknw
         * openid : oEDv_syn6DZRcngSZ4_VDPsLHbQE
         * scope : snsapi_userinfo
         * unionid : ojBfv5701LHhqA2RLoUh-jJfimYE
         */

        private String access_token;
        private int expires_in;
        private String refresh_token;
        private String openid;
        private String scope;
        private String unionid;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }
    }
}
