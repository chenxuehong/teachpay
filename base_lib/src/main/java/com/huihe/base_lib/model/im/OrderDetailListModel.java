package com.huihe.base_lib.model.im;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonResult;

import java.util.List;

public class OrderDetailListModel extends JsonResult<OrderDetailListModel.OrderDetailEntity> {

    public static class OrderDetailEntity {
        List<UserOrderEntity> userOrderEntities;
        public int consumeCount;
        public int getCount;

        public List<UserOrderEntity> getUserOrderEntities() {
            return userOrderEntities;
        }

        public int getConsumeCount() {
            return consumeCount;
        }

        public int getGetCount() {
            return getCount;
        }

        public void setUserOrderEntities(List<UserOrderEntity> userOrderEntities) {
            this.userOrderEntities = userOrderEntities;
        }

        public void setConsumeCount(int consumeCount) {
            this.consumeCount = consumeCount;
        }

        public void setGetCount(int getCount) {
            this.getCount = getCount;
        }

        public static class UserOrderEntity {

            /**
             * pageSize : 10
             * currentPage : 0
             * totalItem : 0
             * startRow : 0
             * totalPage : 0
             * id : 10767
             * user_id : 9191
             * pay_id : 0
             * type : redenvelopes
             * pay_count : 10
             * payment_id : 4260
             * create_time : 2020-03-28 04:02:38
             * pay_time : 2020-03-28 04:02:38
             * status : 2
             * order_no : GK20200328120238048779967
             * category_id : 0
             * type_id : 0
             * gift_count : 0
             * source : chat
             * map : {"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1584104232184.jpg","background_pic":"http://img.curiousmore.com/1584354512798.jpg","birth":811526400000,"cash":"0.000","cat_coin":"9.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1554712462000,"distrib_qrcode":"","duration":0,"hometown":"阿富汗·马扎里沙里夫","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":8,"local_time":1584354513871,"log_out_time":1584131023000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":0,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1584325713000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}}
             * transaction_type : false
             * logo_url : http://www.huihejituan.com/tripPictstorage/qmore/type/redenvelopes.png
             * remarks : 财源滚滚
             */

            private String id;
            private String user_id;
            private String pay_id;
            private String type;
            private String pay_count;
            private String payment_id;
            private String create_time;
            private String pay_time;
            private int status;
            private String order_no;
            private String category_id;
            private String type_id;
            private String gift_count;
            private String source;
            private MapBean map;
            private boolean transaction_type;
            private String logo_url;
            private String remarks;

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

            public String getPay_id() {
                return pay_id;
            }

            public void setPay_id(String pay_id) {
                this.pay_id = pay_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPay_count() {
                return pay_count;
            }

            public void setPay_count(String pay_count) {
                this.pay_count = pay_count;
            }

            public String getPayment_id() {
                return payment_id;
            }

            public void setPayment_id(String payment_id) {
                this.payment_id = payment_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getGift_count() {
                return gift_count;
            }

            public void setGift_count(String gift_count) {
                this.gift_count = gift_count;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public MapBean getMap() {
                return map;
            }

            public void setMap(MapBean map) {
                this.map = map;
            }

            public boolean isTransaction_type() {
                return transaction_type;
            }

            public void setTransaction_type(boolean transaction_type) {
                this.transaction_type = transaction_type;
            }

            public String getLogo_url() {
                return logo_url;
            }

            public void setLogo_url(String logo_url) {
                this.logo_url = logo_url;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public static class MapBean {
                /**
                 * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1584104232184.jpg","background_pic":"http://img.curiousmore.com/1584354512798.jpg","birth":811526400000,"cash":"0.000","cat_coin":"9.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1554712462000,"distrib_qrcode":"","duration":0,"hometown":"阿富汗·马扎里沙里夫","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":8,"local_time":1584354513871,"log_out_time":1584131023000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":0,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1584325713000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}
                 */

                private UserInfoEntity userinfo;
                public UserInfoEntity getUserinfo() {
                    return userinfo;
                }
                public void setUserinfo(UserInfoEntity userinfo) {
                    this.userinfo = userinfo;
                }

            }
        }
    }

}
