package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class UserIdentityModel extends JsonListResult<UserIdentityModel.UserIdentityEntity> {
    public static class UserIdentityEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 14
         * user_id : 9191
         * user_name : Kitychen
         * id_card : 340827199211224937
         * status : 2
         * id_frount : http://img.curiousmore.com/736-B422D02A27F1/Documents/1huihe1584159710.png
         * id_back : http://img.curiousmore.com/736-B422D02A27F1/Documents/1huihe1584159718.png
         * id_onhand : http://img.curiousmore.com/736-B422D02A27F1/Documents/1huihe1584159724.png
         * apply_time : 2020-03-14 04:18:11
         * update_time : 2020-03-14 04:18:11
         * is_pay : false
         * nationality : 本国
         * start_time : null
         * end_time : null
         * map : null
         * nick_name : null
         */


        private String id;
        private String user_id;
        private String user_name;
        private String id_card;
        private int status;
        private String id_frount;
        private String id_back;
        private String id_onhand;
        private String apply_time;
        private String update_time;
        private boolean is_pay;
        private String nationality;
        private String start_time;
        private String end_time;
        private Object map;
        private Object nick_name;
        private String refuse_contect;

        public void setRefuse_contect(String refuse_contect) {
            this.refuse_contect = refuse_contect;
        }

        public String getRefuse_contect() {
            return refuse_contect;
        }



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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getId_frount() {
            return id_frount;
        }

        public void setId_frount(String id_frount) {
            this.id_frount = id_frount;
        }

        public String getId_back() {
            return id_back;
        }

        public void setId_back(String id_back) {
            this.id_back = id_back;
        }

        public String getId_onhand() {
            return id_onhand;
        }

        public void setId_onhand(String id_onhand) {
            this.id_onhand = id_onhand;
        }

        public String getApply_time() {
            return apply_time;
        }

        public void setApply_time(String apply_time) {
            this.apply_time = apply_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public boolean isIs_pay() {
            return is_pay;
        }

        public void setIs_pay(boolean is_pay) {
            this.is_pay = is_pay;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public Object getMap() {
            return map;
        }

        public void setMap(Object map) {
            this.map = map;
        }

        public Object getNick_name() {
            return nick_name;
        }

        public void setNick_name(Object nick_name) {
            this.nick_name = nick_name;
        }
    }
}
