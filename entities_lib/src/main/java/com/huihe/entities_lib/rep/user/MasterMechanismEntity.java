package com.huihe.entities_lib.rep.user;

import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import java.util.List;

public class MasterMechanismEntity {
    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 9
     * create_time : 2020-07-01 10:51:06
     * update_time : 2020-07-01 10:56:35
     * mechanism_name : 英杰外语培训班
     * user_id : 4260
     * mechanism_addr : 杭州市杭海路601号三堡产业大厦-A座-1509
     * mechanism_logo : http://img.huihejituan.com/1593572181822.png
     * mechanism_telephone : 13093793169
     * mechanism_language : Bulgarian/Russian
     * mechanism_advantage : 鱼鱼鱼，隐隐约约，古古怪怪
     * introduction_pic : http://img.huihejituan.com/1593572182677.jpg,http://img.huihejituan.com/1593572183525.jpg
     * introduction_content : 呼呼呼吸
     * contacts : 陈小姐
     * contact_telephone : 138672660941
     * contact_information :
     * contacts_title : 英语外教
     * status : 1
     * support_means : http://img.huihejituan.com/1593572184303.jpg
     * mechanism_no : 1593571866763
     * sort_weight : 0
     * latitude : 30.2758686432
     * longitude : 120.2342040143
     * map : null
     */

    private Object sortName;
    private Object orderBy;
    private Object fileds;
    private String totalPage;
    private String id;
    private String create_time;
    private String update_time;
    private String mechanism_name;
    private String user_id;
    private String mechanism_addr;
    private String mechanism_logo;
    private String mechanism_telephone;
    private String mechanism_language;
    private String mechanism_advantage;
    private String introduction_pic;
    private String introduction_content;
    private String contacts;
    private String contact_telephone;
    private String contact_information;
    private String contacts_title;
    private Integer status;
    private String support_means;
    private String facade_view;
    private String mechanism_no;
    private Double latitude;
    private Double longitude;
    private MasterMechanismModel.MasterMechanismEntity.Map map;
    private String distance;
    private String type;
    private String refuse_contect;
    private String recommender_id;
    private Boolean is_recommend;
    private Boolean is_support_teach_paypal;
    private String cash;
    private String uid;
    private String categories;
    private String categories_child;
    private Float avg_rating;

    public Float getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(Float avg_rating) {
        this.avg_rating = avg_rating;
    }

    private boolean isCooperative = true;
    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Boolean getIs_support_teach_paypal() {
        return is_support_teach_paypal;
    }

    public String getFacade_view() {
        return facade_view;
    }

    public String getCategories() {
        return categories;
    }

    public String getCategories_child() {
        return categories_child;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setCategories_child(String categories_child) {
        this.categories_child = categories_child;
    }

    public void setCooperative(boolean cooperative) {
        isCooperative = cooperative;
    }

    public boolean isCooperative() {
        return isCooperative;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRecommender_id(String recommender_id) {
        this.recommender_id = recommender_id;
    }

    public void setIs_recommend(Boolean is_recommend) {
        this.is_recommend = is_recommend;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRecommender_id() {
        return recommender_id;
    }

    public Boolean getIs_recommend() {
        return is_recommend;
    }

    public String getCash() {
        return cash;
    }

    public String getUid() {
        return uid;
    }

    public void setRefuse_contect(String refuse_contect) {
        this.refuse_contect = refuse_contect;
    }

    public String getRefuse_contect() {
        return refuse_contect;
    }
    public String getDistance() {
        return distance;
    }

    public String getType() {
        return type;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMechanism_name() {
        return mechanism_name;
    }

    public void setMechanism_name(String mechanism_name) {
        this.mechanism_name = mechanism_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMechanism_addr() {
        return mechanism_addr;
    }

    public void setMechanism_addr(String mechanism_addr) {
        this.mechanism_addr = mechanism_addr;
    }

    public String getMechanism_logo() {
        return mechanism_logo;
    }

    public void setMechanism_logo(String mechanism_logo) {
        this.mechanism_logo = mechanism_logo;
    }

    public String getMechanism_telephone() {
        return mechanism_telephone;
    }

    public void setMechanism_telephone(String mechanism_telephone) {
        this.mechanism_telephone = mechanism_telephone;
    }

    public String getMechanism_language() {
        return mechanism_language;
    }

    public void setMechanism_language(String mechanism_language) {
        this.mechanism_language = mechanism_language;
    }

    public String getMechanism_advantage() {
        return mechanism_advantage;
    }

    public void setMechanism_advantage(String mechanism_advantage) {
        this.mechanism_advantage = mechanism_advantage;
    }

    public String getIntroduction_pic() {
        return introduction_pic;
    }

    public void setIntroduction_pic(String introduction_pic) {
        this.introduction_pic = introduction_pic;
    }

    public String getIntroduction_content() {
        return introduction_content;
    }

    public void setIntroduction_content(String introduction_content) {
        this.introduction_content = introduction_content;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContact_telephone() {
        return contact_telephone;
    }

    public void setContact_telephone(String contact_telephone) {
        this.contact_telephone = contact_telephone;
    }

    public String getContact_information() {
        return contact_information;
    }

    public void setContact_information(String contact_information) {
        this.contact_information = contact_information;
    }

    public String getContacts_title() {
        return contacts_title;
    }

    public void setContacts_title(String contacts_title) {
        this.contacts_title = contacts_title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSupport_means() {
        return support_means;
    }

    public void setSupport_means(String support_means) {
        this.support_means = support_means;
    }

    public String getMechanism_no() {
        return mechanism_no;
    }

    public void setMechanism_no(String mechanism_no) {
        this.mechanism_no = mechanism_no;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public MasterMechanismModel.MasterMechanismEntity.Map getMap() {
        return map;
    }

    public void setMap(MasterMechanismModel.MasterMechanismEntity.Map map) {
        this.map = map;
    }
    public static class Map{

        /**
         * totalIncome : 0
         * dayBeginCount : 0
         * DayEarnings : null
         * sale_num : 2
         * teachers_num : 6
         * beginCount : 0
         * historyCount : 6
         */

        private String totalIncome;
        private String dayBeginCount;
        private String DayEarnings;
        private String sale_num;
        private String teachers_num;
        private String beginCount;
        private String historyCount;
        private List<MasterSetPriceEntity> masterSetPriceEntity;

        public List<MasterSetPriceEntity> getMasterSetPriceEntity() {
            return masterSetPriceEntity;
        }

        public String getTotalIncome() {
            return totalIncome;
        }

        public void setTotalIncome(String totalIncome) {
            this.totalIncome = totalIncome;
        }

        public String getDayBeginCount() {
            return dayBeginCount;
        }

        public void setDayBeginCount(String dayBeginCount) {
            this.dayBeginCount = dayBeginCount;
        }

        public String getDayEarnings() {
            return DayEarnings;
        }

        public void setDayEarnings(String DayEarnings) {
            this.DayEarnings = DayEarnings;
        }

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }

        public String getTeachers_num() {
            return teachers_num;
        }

        public void setTeachers_num(String teachers_num) {
            this.teachers_num = teachers_num;
        }

        public String getBeginCount() {
            return beginCount;
        }

        public void setBeginCount(String beginCount) {
            this.beginCount = beginCount;
        }

        public String getHistoryCount() {
            return historyCount;
        }

        public void setHistoryCount(String historyCount) {
            this.historyCount = historyCount;
        }

    }
}
