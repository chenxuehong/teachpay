package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class UserOrderModel extends JsonListResult<UserOrderModel.UserOrderEntity> {
    public class UserOrderEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * totalPage : 0
         * id : 8455
         * user_id : 9099
         * pay_id : 3436
         * type : gift
         * pay_count : 10
         * payment_id : 9205
         * create_time : 2020-01-21 13:44:56
         * pay_time : 2020-01-21 13:44:56
         * status : 2
         * order_no : GK20200121134456191534654
         * category_id : 0
         * type_id : 0
         * gift_count : 1
         * source : note
         * map : {"giftinfo":[],"noteinfo":{"comment_count":24,"video_duration":"","fileds":"","sortName":"","orderBy":"","pageSize":10,"content":"所有产品均有正品开版，专柜原厂皮料五金，专供代购的顶级品质！相似度达到99%\n包包鞋子衣服首饰均有货，上万款细节实拍，真金真钻带正规机构检测报告证书\n\n朋友圈每天更新各种大牌款式细节实拍\n（只出精品！追求地摊货请绕道！）\n微信：fukeshoes","cover":"","is_read":false,"picts":"http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064601.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064602.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064603.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064603.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064604.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064605.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064606.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064607.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064607.png","watch_count":0,"classfiy":"Other","id":3436,"oper_id":0,"map":null,"like_count":6,"create_time":"2020-02-01 23:48:15","img_width":0,"startRow":0,"totalItem":0,"totalPage":0,"img_height":0,"collect_count":0,"url":"http://www.curiousmore.com:8768/eg-api/push/noteshare.html?note_id=3436","share_count":0,"user_id":9205,"location":"","style":1,"currentPage":0,"status":2},"payinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"","background_pic":"","birth":1565420401000,"cash":"0.000","cat_coin":"90.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1565420401000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1569311211551,"log_out_time":1566180940000,"mail":"","member_level":0,"message_num":0,"mobile":"17708642780","mother_tongue":"Romanian","national_flag":"","nick_name":"","online_state":0,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1569311211000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9205","user_id":9205},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"/data/user/0/com.eghuihe.qmore/files/158025988625820200129_090440.jpg.jpg","birth":1578585600000,"cash":"0.000","cat_coin":"21361.000","chatting_count":45,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":10,"local_time":1580259781294,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1580259781000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}}
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

        public  class MapBean {
            /**
             * giftinfo : []
             * noteinfo : {"comment_count":24,"video_duration":"","fileds":"","sortName":"","orderBy":"","pageSize":10,"content":"所有产品均有正品开版，专柜原厂皮料五金，专供代购的顶级品质！相似度达到99%\n包包鞋子衣服首饰均有货，上万款细节实拍，真金真钻带正规机构检测报告证书\n\n朋友圈每天更新各种大牌款式细节实拍\n（只出精品！追求地摊货请绕道！）\n微信：fukeshoes","cover":"","is_read":false,"picts":"http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064601.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064602.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064603.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064603.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064604.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064605.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064606.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064607.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064607.png","watch_count":0,"classfiy":"Other","id":3436,"oper_id":0,"map":null,"like_count":6,"create_time":"2020-02-01 23:48:15","img_width":0,"startRow":0,"totalItem":0,"totalPage":0,"img_height":0,"collect_count":0,"url":"http://www.curiousmore.com:8768/eg-api/push/noteshare.html?note_id=3436","share_count":0,"user_id":9205,"location":"","style":1,"currentPage":0,"status":2}
             * payinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"","background_pic":"","birth":1565420401000,"cash":"0.000","cat_coin":"90.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1565420401000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1569311211551,"log_out_time":1566180940000,"mail":"","member_level":0,"message_num":0,"mobile":"17708642780","mother_tongue":"Romanian","national_flag":"","nick_name":"","online_state":0,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1569311211000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9205","user_id":9205}
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"/data/user/0/com.eghuihe.qmore/files/158025988625820200129_090440.jpg.jpg","birth":1578585600000,"cash":"0.000","cat_coin":"21361.000","chatting_count":45,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":10,"local_time":1580259781294,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1580259781000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}
             */

            private NoteinfoBean noteinfo;
            private PayinfoBean payinfo;
            private UserinfoBean userinfo;
            private List<GiftInfoBean> giftinfo;

            public NoteinfoBean getNoteinfo() {
                return noteinfo;
            }

            public void setNoteinfo(NoteinfoBean noteinfo) {
                this.noteinfo = noteinfo;
            }

            public PayinfoBean getPayinfo() {
                return payinfo;
            }

            public void setPayinfo(PayinfoBean payinfo) {
                this.payinfo = payinfo;
            }

            public UserinfoBean getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserinfoBean userinfo) {
                this.userinfo = userinfo;
            }

            public List<GiftInfoBean> getGiftinfo() {
                return giftinfo;
            }

            public void setGiftinfo(List<GiftInfoBean> giftinfo) {
                this.giftinfo = giftinfo;
            }

            public class GiftInfoBean{

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
                private int amount;
                private int discount_amout;
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

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public int getDiscount_amout() {
                    return discount_amout;
                }

                public void setDiscount_amout(int discount_amout) {
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
            public  class NoteinfoBean {
                /**
                 * comment_count : 24
                 * video_duration :
                 * fileds :
                 * sortName :
                 * orderBy :
                 * pageSize : 10
                 * content : 所有产品均有正品开版，专柜原厂皮料五金，专供代购的顶级品质！相似度达到99%
                 包包鞋子衣服首饰均有货，上万款细节实拍，真金真钻带正规机构检测报告证书

                 朋友圈每天更新各种大牌款式细节实拍
                 （只出精品！追求地摊货请绕道！）
                 微信：fukeshoes
                 * cover :
                 * is_read : false
                 * picts : http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064601.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064602.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064603.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064603.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064604.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064605.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064606.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064607.png,http://img.curiousmore.com/D47-43541B009317/Documents/1huihe1578064607.png
                 * watch_count : 0
                 * classfiy : Other
                 * id : 3436
                 * oper_id : 0
                 * map : null
                 * like_count : 6
                 * create_time : 2020-02-01 23:48:15
                 * img_width : 0
                 * startRow : 0
                 * totalItem : 0
                 * totalPage : 0
                 * img_height : 0
                 * collect_count : 0
                 * url : http://www.curiousmore.com:8768/eg-api/push/noteshare.html?note_id=3436
                 * share_count : 0
                 * user_id : 9205
                 * location :
                 * style : 1
                 * currentPage : 0
                 * status : 2
                 */

                private int comment_count;
                private String video_duration;
                private String fileds;
                private String sortName;
                private String orderBy;
                private int pageSize;
                private String content;
                private String cover;
                private boolean is_read;
                private String picts;
                private int watch_count;
                private String classfiy;
                private int id;
                private int oper_id;
                private Object map;
                private int like_count;
                private String create_time;
                private int img_width;
                private int startRow;
                private int totalItem;
                private int totalPage;
                private int img_height;
                private int collect_count;
                private String url;
                private int share_count;
                private int user_id;
                private String location;
                private int style;
                private int currentPage;
                private int status;

                public int getComment_count() {
                    return comment_count;
                }

                public void setComment_count(int comment_count) {
                    this.comment_count = comment_count;
                }

                public String getVideo_duration() {
                    return video_duration;
                }

                public void setVideo_duration(String video_duration) {
                    this.video_duration = video_duration;
                }

                public String getFileds() {
                    return fileds;
                }

                public void setFileds(String fileds) {
                    this.fileds = fileds;
                }

                public String getSortName() {
                    return sortName;
                }

                public void setSortName(String sortName) {
                    this.sortName = sortName;
                }

                public String getOrderBy() {
                    return orderBy;
                }

                public void setOrderBy(String orderBy) {
                    this.orderBy = orderBy;
                }

                public int getPageSize() {
                    return pageSize;
                }

                public void setPageSize(int pageSize) {
                    this.pageSize = pageSize;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public boolean isIs_read() {
                    return is_read;
                }

                public void setIs_read(boolean is_read) {
                    this.is_read = is_read;
                }

                public String getPicts() {
                    return picts;
                }

                public void setPicts(String picts) {
                    this.picts = picts;
                }

                public int getWatch_count() {
                    return watch_count;
                }

                public void setWatch_count(int watch_count) {
                    this.watch_count = watch_count;
                }

                public String getClassfiy() {
                    return classfiy;
                }

                public void setClassfiy(String classfiy) {
                    this.classfiy = classfiy;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getOper_id() {
                    return oper_id;
                }

                public void setOper_id(int oper_id) {
                    this.oper_id = oper_id;
                }

                public Object getMap() {
                    return map;
                }

                public void setMap(Object map) {
                    this.map = map;
                }

                public int getLike_count() {
                    return like_count;
                }

                public void setLike_count(int like_count) {
                    this.like_count = like_count;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public int getImg_width() {
                    return img_width;
                }

                public void setImg_width(int img_width) {
                    this.img_width = img_width;
                }

                public int getStartRow() {
                    return startRow;
                }

                public void setStartRow(int startRow) {
                    this.startRow = startRow;
                }

                public int getTotalItem() {
                    return totalItem;
                }

                public void setTotalItem(int totalItem) {
                    this.totalItem = totalItem;
                }

                public int getTotalPage() {
                    return totalPage;
                }

                public void setTotalPage(int totalPage) {
                    this.totalPage = totalPage;
                }

                public int getImg_height() {
                    return img_height;
                }

                public void setImg_height(int img_height) {
                    this.img_height = img_height;
                }

                public int getCollect_count() {
                    return collect_count;
                }

                public void setCollect_count(int collect_count) {
                    this.collect_count = collect_count;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getShare_count() {
                    return share_count;
                }

                public void setShare_count(int share_count) {
                    this.share_count = share_count;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public int getStyle() {
                    return style;
                }

                public void setStyle(int style) {
                    this.style = style;
                }

                public int getCurrentPage() {
                    return currentPage;
                }

                public void setCurrentPage(int currentPage) {
                    this.currentPage = currentPage;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }

            public  class PayinfoBean {
                /**
                 * account_state : 1
                 * admin_id : 0
                 * advertising_position : false
                 * avatar :
                 * background_pic :
                 * birth : 1565420401000
                 * cash : 0.000
                 * cat_coin : 90.000
                 * chatting_count : 0
                 * city :
                 * contacts_num : 0
                 * country :
                 * create_time : 1565420401000
                 * distrib_qrcode :
                 * duration : 0
                 * hometown :
                 * identity_auth : false
                 * intro :
                 * invite_code :
                 * is_help : false
                 * is_high_quality : false
                 * is_id : false
                 * is_mail : false
                 * is_member : false
                 * is_mobile : false
                 * is_name : false
                 * is_robot : false
                 * is_teenagers : false
                 * is_unread : false
                 * lable :
                 * languages :
                 * like_category :
                 * like_num : 0
                 * local_time : 1569311211551
                 * log_out_time : 1566180940000
                 * mail :
                 * member_level : 0
                 * message_num : 0
                 * mobile : 17708642780
                 * mother_tongue : Romanian
                 * national_flag :
                 * nick_name :
                 * online_state : 0
                 * oper_id : 0
                 * overseas_auth : false
                 * overseas_identity_name :
                 * preference :
                 * qrcode :
                 * rating : 0
                 * registr_num : 0
                 * sex : 1
                 * signature :
                 * update_time : 1569311211000
                 * url : http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9205
                 * user_id : 9205
                 */

                private int account_state;
                private int admin_id;
                private boolean advertising_position;
                private String avatar;
                private String background_pic;
                private long birth;
                private String cash;
                private String cat_coin;
                private int chatting_count;
                private String city;
                private int contacts_num;
                private String country;
                private long create_time;
                private String distrib_qrcode;
                private int duration;
                private String hometown;
                private boolean identity_auth;
                private String intro;
                private String invite_code;
                private boolean is_help;
                private boolean is_high_quality;
                private boolean is_id;
                private boolean is_mail;
                private boolean is_member;
                private boolean is_mobile;
                private boolean is_name;
                private boolean is_robot;
                private boolean is_teenagers;
                private boolean is_unread;
                private String lable;
                private String languages;
                private String like_category;
                private int like_num;
                private long local_time;
                private long log_out_time;
                private String mail;
                private int member_level;
                private int message_num;
                private String mobile;
                private String mother_tongue;
                private String national_flag;
                private String nick_name;
                private int online_state;
                private int oper_id;
                private boolean overseas_auth;
                private String overseas_identity_name;
                private String preference;
                private String qrcode;
                private int rating;
                private int registr_num;
                private int sex;
                private String signature;
                private long update_time;
                private String url;
                private int user_id;

                public int getAccount_state() {
                    return account_state;
                }

                public void setAccount_state(int account_state) {
                    this.account_state = account_state;
                }

                public int getAdmin_id() {
                    return admin_id;
                }

                public void setAdmin_id(int admin_id) {
                    this.admin_id = admin_id;
                }

                public boolean isAdvertising_position() {
                    return advertising_position;
                }

                public void setAdvertising_position(boolean advertising_position) {
                    this.advertising_position = advertising_position;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getBackground_pic() {
                    return background_pic;
                }

                public void setBackground_pic(String background_pic) {
                    this.background_pic = background_pic;
                }

                public long getBirth() {
                    return birth;
                }

                public void setBirth(long birth) {
                    this.birth = birth;
                }

                public String getCash() {
                    return cash;
                }

                public void setCash(String cash) {
                    this.cash = cash;
                }

                public String getCat_coin() {
                    return cat_coin;
                }

                public void setCat_coin(String cat_coin) {
                    this.cat_coin = cat_coin;
                }

                public int getChatting_count() {
                    return chatting_count;
                }

                public void setChatting_count(int chatting_count) {
                    this.chatting_count = chatting_count;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public int getContacts_num() {
                    return contacts_num;
                }

                public void setContacts_num(int contacts_num) {
                    this.contacts_num = contacts_num;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public String getDistrib_qrcode() {
                    return distrib_qrcode;
                }

                public void setDistrib_qrcode(String distrib_qrcode) {
                    this.distrib_qrcode = distrib_qrcode;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public String getHometown() {
                    return hometown;
                }

                public void setHometown(String hometown) {
                    this.hometown = hometown;
                }

                public boolean isIdentity_auth() {
                    return identity_auth;
                }

                public void setIdentity_auth(boolean identity_auth) {
                    this.identity_auth = identity_auth;
                }

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public String getInvite_code() {
                    return invite_code;
                }

                public void setInvite_code(String invite_code) {
                    this.invite_code = invite_code;
                }

                public boolean isIs_help() {
                    return is_help;
                }

                public void setIs_help(boolean is_help) {
                    this.is_help = is_help;
                }

                public boolean isIs_high_quality() {
                    return is_high_quality;
                }

                public void setIs_high_quality(boolean is_high_quality) {
                    this.is_high_quality = is_high_quality;
                }

                public boolean isIs_id() {
                    return is_id;
                }

                public void setIs_id(boolean is_id) {
                    this.is_id = is_id;
                }

                public boolean isIs_mail() {
                    return is_mail;
                }

                public void setIs_mail(boolean is_mail) {
                    this.is_mail = is_mail;
                }

                public boolean isIs_member() {
                    return is_member;
                }

                public void setIs_member(boolean is_member) {
                    this.is_member = is_member;
                }

                public boolean isIs_mobile() {
                    return is_mobile;
                }

                public void setIs_mobile(boolean is_mobile) {
                    this.is_mobile = is_mobile;
                }

                public boolean isIs_name() {
                    return is_name;
                }

                public void setIs_name(boolean is_name) {
                    this.is_name = is_name;
                }

                public boolean isIs_robot() {
                    return is_robot;
                }

                public void setIs_robot(boolean is_robot) {
                    this.is_robot = is_robot;
                }

                public boolean isIs_teenagers() {
                    return is_teenagers;
                }

                public void setIs_teenagers(boolean is_teenagers) {
                    this.is_teenagers = is_teenagers;
                }

                public boolean isIs_unread() {
                    return is_unread;
                }

                public void setIs_unread(boolean is_unread) {
                    this.is_unread = is_unread;
                }

                public String getLable() {
                    return lable;
                }

                public void setLable(String lable) {
                    this.lable = lable;
                }

                public String getLanguages() {
                    return languages;
                }

                public void setLanguages(String languages) {
                    this.languages = languages;
                }

                public String getLike_category() {
                    return like_category;
                }

                public void setLike_category(String like_category) {
                    this.like_category = like_category;
                }

                public int getLike_num() {
                    return like_num;
                }

                public void setLike_num(int like_num) {
                    this.like_num = like_num;
                }

                public long getLocal_time() {
                    return local_time;
                }

                public void setLocal_time(long local_time) {
                    this.local_time = local_time;
                }

                public long getLog_out_time() {
                    return log_out_time;
                }

                public void setLog_out_time(long log_out_time) {
                    this.log_out_time = log_out_time;
                }

                public String getMail() {
                    return mail;
                }

                public void setMail(String mail) {
                    this.mail = mail;
                }

                public int getMember_level() {
                    return member_level;
                }

                public void setMember_level(int member_level) {
                    this.member_level = member_level;
                }

                public int getMessage_num() {
                    return message_num;
                }

                public void setMessage_num(int message_num) {
                    this.message_num = message_num;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getMother_tongue() {
                    return mother_tongue;
                }

                public void setMother_tongue(String mother_tongue) {
                    this.mother_tongue = mother_tongue;
                }

                public String getNational_flag() {
                    return national_flag;
                }

                public void setNational_flag(String national_flag) {
                    this.national_flag = national_flag;
                }

                public String getNick_name() {
                    return nick_name;
                }

                public void setNick_name(String nick_name) {
                    this.nick_name = nick_name;
                }

                public int getOnline_state() {
                    return online_state;
                }

                public void setOnline_state(int online_state) {
                    this.online_state = online_state;
                }

                public int getOper_id() {
                    return oper_id;
                }

                public void setOper_id(int oper_id) {
                    this.oper_id = oper_id;
                }

                public boolean isOverseas_auth() {
                    return overseas_auth;
                }

                public void setOverseas_auth(boolean overseas_auth) {
                    this.overseas_auth = overseas_auth;
                }

                public String getOverseas_identity_name() {
                    return overseas_identity_name;
                }

                public void setOverseas_identity_name(String overseas_identity_name) {
                    this.overseas_identity_name = overseas_identity_name;
                }

                public String getPreference() {
                    return preference;
                }

                public void setPreference(String preference) {
                    this.preference = preference;
                }

                public String getQrcode() {
                    return qrcode;
                }

                public void setQrcode(String qrcode) {
                    this.qrcode = qrcode;
                }

                public int getRating() {
                    return rating;
                }

                public void setRating(int rating) {
                    this.rating = rating;
                }

                public int getRegistr_num() {
                    return registr_num;
                }

                public void setRegistr_num(int registr_num) {
                    this.registr_num = registr_num;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public String getSignature() {
                    return signature;
                }

                public void setSignature(String signature) {
                    this.signature = signature;
                }

                public long getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(long update_time) {
                    this.update_time = update_time;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }
            }

            public  class UserinfoBean {
                /**
                 * account_state : 1
                 * admin_id : 0
                 * advertising_position : false
                 * avatar : http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png
                 * background_pic : /data/user/0/com.eghuihe.qmore/files/158025988625820200129_090440.jpg.jpg
                 * birth : 1578585600000
                 * cash : 0.000
                 * cat_coin : 21361.000
                 * chatting_count : 45
                 * city : 阿森松岛
                 * contacts_num : 678
                 * country : 阿鲁巴
                 * create_time : 1555058447000
                 * distrib_qrcode :
                 * duration : 0
                 * hometown : 卡范
                 * identity_auth : false
                 * intro :
                 * invite_code :
                 * is_help : true
                 * is_high_quality : false
                 * is_id : false
                 * is_mail : false
                 * is_member : true
                 * is_mobile : false
                 * is_name : false
                 * is_robot : false
                 * is_teenagers : false
                 * is_unread : false
                 * lable :
                 * languages : 不限/法语
                 * like_category :
                 * like_num : 10
                 * local_time : 1580259781294
                 * log_out_time : 1575536162000
                 * mail :
                 * member_level : 1
                 * message_num : 0
                 * mobile : 18196548552
                 * mother_tongue : Dutch
                 * national_flag :
                 * nick_name : 车模ooo
                 * online_state : 1
                 * oper_id : 0
                 * overseas_auth : false
                 * overseas_identity_name : 留学生
                 * preference : 语言学习
                 * qrcode :
                 * rating : 0
                 * registr_num : 1
                 * sex : 2
                 * signature :
                 * update_time : 1580259781000
                 * url : http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099
                 * user_id : 9099
                 */

                private int account_state;
                private int admin_id;
                private boolean advertising_position;
                private String avatar;
                private String background_pic;
                private long birth;
                private String cash;
                private String cat_coin;
                private int chatting_count;
                private String city;
                private int contacts_num;
                private String country;
                private long create_time;
                private String distrib_qrcode;
                private int duration;
                private String hometown;
                private boolean identity_auth;
                private String intro;
                private String invite_code;
                private boolean is_help;
                private boolean is_high_quality;
                private boolean is_id;
                private boolean is_mail;
                private boolean is_member;
                private boolean is_mobile;
                private boolean is_name;
                private boolean is_robot;
                private boolean is_teenagers;
                private boolean is_unread;
                private String lable;
                private String languages;
                private String like_category;
                private int like_num;
                private long local_time;
                private long log_out_time;
                private String mail;
                private int member_level;
                private int message_num;
                private String mobile;
                private String mother_tongue;
                private String national_flag;
                private String nick_name;
                private int online_state;
                private int oper_id;
                private boolean overseas_auth;
                private String overseas_identity_name;
                private String preference;
                private String qrcode;
                private int rating;
                private int registr_num;
                private int sex;
                private String signature;
                private long update_time;
                private String url;
                private int user_id;

                public int getAccount_state() {
                    return account_state;
                }

                public void setAccount_state(int account_state) {
                    this.account_state = account_state;
                }

                public int getAdmin_id() {
                    return admin_id;
                }

                public void setAdmin_id(int admin_id) {
                    this.admin_id = admin_id;
                }

                public boolean isAdvertising_position() {
                    return advertising_position;
                }

                public void setAdvertising_position(boolean advertising_position) {
                    this.advertising_position = advertising_position;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getBackground_pic() {
                    return background_pic;
                }

                public void setBackground_pic(String background_pic) {
                    this.background_pic = background_pic;
                }

                public long getBirth() {
                    return birth;
                }

                public void setBirth(long birth) {
                    this.birth = birth;
                }

                public String getCash() {
                    return cash;
                }

                public void setCash(String cash) {
                    this.cash = cash;
                }

                public String getCat_coin() {
                    return cat_coin;
                }

                public void setCat_coin(String cat_coin) {
                    this.cat_coin = cat_coin;
                }

                public int getChatting_count() {
                    return chatting_count;
                }

                public void setChatting_count(int chatting_count) {
                    this.chatting_count = chatting_count;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public int getContacts_num() {
                    return contacts_num;
                }

                public void setContacts_num(int contacts_num) {
                    this.contacts_num = contacts_num;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public String getDistrib_qrcode() {
                    return distrib_qrcode;
                }

                public void setDistrib_qrcode(String distrib_qrcode) {
                    this.distrib_qrcode = distrib_qrcode;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public String getHometown() {
                    return hometown;
                }

                public void setHometown(String hometown) {
                    this.hometown = hometown;
                }

                public boolean isIdentity_auth() {
                    return identity_auth;
                }

                public void setIdentity_auth(boolean identity_auth) {
                    this.identity_auth = identity_auth;
                }

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public String getInvite_code() {
                    return invite_code;
                }

                public void setInvite_code(String invite_code) {
                    this.invite_code = invite_code;
                }

                public boolean isIs_help() {
                    return is_help;
                }

                public void setIs_help(boolean is_help) {
                    this.is_help = is_help;
                }

                public boolean isIs_high_quality() {
                    return is_high_quality;
                }

                public void setIs_high_quality(boolean is_high_quality) {
                    this.is_high_quality = is_high_quality;
                }

                public boolean isIs_id() {
                    return is_id;
                }

                public void setIs_id(boolean is_id) {
                    this.is_id = is_id;
                }

                public boolean isIs_mail() {
                    return is_mail;
                }

                public void setIs_mail(boolean is_mail) {
                    this.is_mail = is_mail;
                }

                public boolean isIs_member() {
                    return is_member;
                }

                public void setIs_member(boolean is_member) {
                    this.is_member = is_member;
                }

                public boolean isIs_mobile() {
                    return is_mobile;
                }

                public void setIs_mobile(boolean is_mobile) {
                    this.is_mobile = is_mobile;
                }

                public boolean isIs_name() {
                    return is_name;
                }

                public void setIs_name(boolean is_name) {
                    this.is_name = is_name;
                }

                public boolean isIs_robot() {
                    return is_robot;
                }

                public void setIs_robot(boolean is_robot) {
                    this.is_robot = is_robot;
                }

                public boolean isIs_teenagers() {
                    return is_teenagers;
                }

                public void setIs_teenagers(boolean is_teenagers) {
                    this.is_teenagers = is_teenagers;
                }

                public boolean isIs_unread() {
                    return is_unread;
                }

                public void setIs_unread(boolean is_unread) {
                    this.is_unread = is_unread;
                }

                public String getLable() {
                    return lable;
                }

                public void setLable(String lable) {
                    this.lable = lable;
                }

                public String getLanguages() {
                    return languages;
                }

                public void setLanguages(String languages) {
                    this.languages = languages;
                }

                public String getLike_category() {
                    return like_category;
                }

                public void setLike_category(String like_category) {
                    this.like_category = like_category;
                }

                public int getLike_num() {
                    return like_num;
                }

                public void setLike_num(int like_num) {
                    this.like_num = like_num;
                }

                public long getLocal_time() {
                    return local_time;
                }

                public void setLocal_time(long local_time) {
                    this.local_time = local_time;
                }

                public long getLog_out_time() {
                    return log_out_time;
                }

                public void setLog_out_time(long log_out_time) {
                    this.log_out_time = log_out_time;
                }

                public String getMail() {
                    return mail;
                }

                public void setMail(String mail) {
                    this.mail = mail;
                }

                public int getMember_level() {
                    return member_level;
                }

                public void setMember_level(int member_level) {
                    this.member_level = member_level;
                }

                public int getMessage_num() {
                    return message_num;
                }

                public void setMessage_num(int message_num) {
                    this.message_num = message_num;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getMother_tongue() {
                    return mother_tongue;
                }

                public void setMother_tongue(String mother_tongue) {
                    this.mother_tongue = mother_tongue;
                }

                public String getNational_flag() {
                    return national_flag;
                }

                public void setNational_flag(String national_flag) {
                    this.national_flag = national_flag;
                }

                public String getNick_name() {
                    return nick_name;
                }

                public void setNick_name(String nick_name) {
                    this.nick_name = nick_name;
                }

                public int getOnline_state() {
                    return online_state;
                }

                public void setOnline_state(int online_state) {
                    this.online_state = online_state;
                }

                public int getOper_id() {
                    return oper_id;
                }

                public void setOper_id(int oper_id) {
                    this.oper_id = oper_id;
                }

                public boolean isOverseas_auth() {
                    return overseas_auth;
                }

                public void setOverseas_auth(boolean overseas_auth) {
                    this.overseas_auth = overseas_auth;
                }

                public String getOverseas_identity_name() {
                    return overseas_identity_name;
                }

                public void setOverseas_identity_name(String overseas_identity_name) {
                    this.overseas_identity_name = overseas_identity_name;
                }

                public String getPreference() {
                    return preference;
                }

                public void setPreference(String preference) {
                    this.preference = preference;
                }

                public String getQrcode() {
                    return qrcode;
                }

                public void setQrcode(String qrcode) {
                    this.qrcode = qrcode;
                }

                public int getRating() {
                    return rating;
                }

                public void setRating(int rating) {
                    this.rating = rating;
                }

                public int getRegistr_num() {
                    return registr_num;
                }

                public void setRegistr_num(int registr_num) {
                    this.registr_num = registr_num;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public String getSignature() {
                    return signature;
                }

                public void setSignature(String signature) {
                    this.signature = signature;
                }

                public long getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(long update_time) {
                    this.update_time = update_time;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }
            }
        }
    }
}
