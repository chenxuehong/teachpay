package com.huihe.base_lib.model;

public class ClassRoomEntity {

    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 5
     * create_time : 2021-01-07 16:17:48
     * start_time : null
     * update_time : 2021-01-07 16:17:48
     * status : 2
     * mechanism_id : 26
     * name : 5
     * stringList : null
     * being_used : null
     */
    private String id;
    private String create_time;
    private Object start_time;
    private String update_time;
    private String status;
    private String mechanism_id;
    private String name;

    public String getId() {
        return id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public Object getStart_time() {
        return start_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getStatus() {
        return status;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getName() {
        return name;
    }
}
