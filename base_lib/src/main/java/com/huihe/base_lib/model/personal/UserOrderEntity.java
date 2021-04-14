package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;

import java.util.List;

public class UserOrderEntity {

    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * totalPage : 0
     * id : 9728
     * user_id : 4260
     * pay_id : 0
     * type : gift
     * pay_count : 10
     * payment_id : 4260
     * create_time : 2020-03-11 13:38:50
     * pay_time : 2020-03-11 13:38:50
     * status : 2
     * order_no : GK20200311133850837944592
     * category_id : 0
     * type_id : 1
     * gift_count : 1
     * source : rebate
     * map : {"giftinfo":[{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":1,"gift_name":"见面礼","create_time":"2019-09-27 17:50:15","update_time":"2019-09-27 17:51:11","type":"","amount":10,"discount_amout":10,"is_discount":false,"status":1,"pic":"http://www.huihejituan.com/tripPictstorage/qmore/gift/meeting.png","introduction":""}],"payinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://www.huihejituan.com/tripPictstorage/user/2214/play/f49f0994-e4f7-4de8-807e-b0b400b1e028_200_200.jpg","background_pic":"http://img.curiousmore.com/1583764071517.jpg","birth":811555200000,"cash":"0.000","cat_coin":"9.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"尼加拉瓜","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":8,"local_time":1583763850458,"log_out_time":1583315708000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"跑腿代购","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1583763850000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}}
     * remarks :
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static class MapBean {
        /**
         * giftinfo : [{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":1,"gift_name":"见面礼","create_time":"2019-09-27 17:50:15","update_time":"2019-09-27 17:51:11","type":"","amount":10,"discount_amout":10,"is_discount":false,"status":1,"pic":"http://www.huihejituan.com/tripPictstorage/qmore/gift/meeting.png","introduction":""}]
         * payinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://www.huihejituan.com/tripPictstorage/user/2214/play/f49f0994-e4f7-4de8-807e-b0b400b1e028_200_200.jpg","background_pic":"http://img.curiousmore.com/1583764071517.jpg","birth":811555200000,"cash":"0.000","cat_coin":"9.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"尼加拉瓜","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":8,"local_time":1583763850458,"log_out_time":1583315708000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"跑腿代购","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1583763850000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}
         */

        private UserInfoEntity payinfo;
        private UserInfoEntity userinfo;
        private List<GiftinfoBean> giftinfo;
        public UserInfoEntity getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserInfoEntity userinfo) {
            this.userinfo = userinfo;
        }
        public UserInfoEntity getPayinfo() {
            return payinfo;
        }

        public void setPayinfo(UserInfoEntity payinfo) {
            this.payinfo = payinfo;
        }

        public List<GiftinfoBean> getGiftinfo() {
            return giftinfo;
        }

        public void setGiftinfo(List<GiftinfoBean> giftinfo) {
            this.giftinfo = giftinfo;
        }

        public static class GiftinfoBean {
            /**
             * pageSize : 10
             * currentPage : 0
             * totalItem : 0
             * startRow : 0
             * sortName : null
             * orderBy : null
             * fileds : null
             * totalPage : 0
             * id : 1
             * gift_name : 见面礼
             * create_time : 2019-09-27 17:50:15
             * update_time : 2019-09-27 17:51:11
             * type :
             * amount : 10
             * discount_amout : 10
             * is_discount : false
             * status : 1
             * pic : http://www.huihejituan.com/tripPictstorage/qmore/gift/meeting.png
             * introduction :
             */

            private int pageSize;
            private int currentPage;
            private int totalItem;
            private int startRow;
            private Object sortName;
            private Object orderBy;
            private Object fileds;
            private int totalPage;
            private int id;
            private String gift_name;
            private String create_time;
            private String update_time;
            private String type;
            private String amount;
            private String discount_amout;
            private boolean is_discount;
            private int status;
            private String pic;
            private String introduction;

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getTotalItem() {
                return totalItem;
            }

            public void setTotalItem(int totalItem) {
                this.totalItem = totalItem;
            }

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public Object getSortName() {
                return sortName;
            }

            public void setSortName(Object sortName) {
                this.sortName = sortName;
            }

            public Object getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(Object orderBy) {
                this.orderBy = orderBy;
            }

            public Object getFileds() {
                return fileds;
            }

            public void setFileds(Object fileds) {
                this.fileds = fileds;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGift_name() {
                return gift_name;
            }

            public void setGift_name(String gift_name) {
                this.gift_name = gift_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getDiscount_amout() {
                return discount_amout;
            }

            public void setDiscount_amout(String discount_amout) {
                this.discount_amout = discount_amout;
            }

            public boolean isIs_discount() {
                return is_discount;
            }

            public void setIs_discount(boolean is_discount) {
                this.is_discount = is_discount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }
        }
    }
}
