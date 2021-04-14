package com.huihe.base_lib.model;

public class MechanismUserEntity {

    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 27
     * user_id : 9449
     * create_time : 2020-12-31 20:38:39
     * update_time : 2020-12-31 20:38:39
     * mechanism_id : 4
     * is_new : true
     * free_course : 0
     * status : 1
     */
    private String id;
    private String user_id;
    private String create_time;
    private String update_time;
    private String mechanism_id;
    private Boolean is_new;
    private String free_course;
    private String status;
    private Map map;

    public Map getMap() {
        return map;
    }

    public static class Map{
        private UserInfoEntity userinfo;

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }
    }
    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public Boolean getIs_new() {
        return is_new;
    }

    public String getFree_course() {
        return free_course;
    }

    public String getStatus() {
        return status;
    }
}
