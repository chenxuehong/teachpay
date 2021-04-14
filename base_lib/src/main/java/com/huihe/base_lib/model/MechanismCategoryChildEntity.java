package com.huihe.base_lib.model;

public class MechanismCategoryChildEntity {

    /**
     * id : 11
     * name : 语文
     * type : 2
     * parent_id : 10
     * weights : 0
     * status : 2
     * create_time : 2021-01-13 17:58:38
     * update_time : 2021-01-13 17:58:38
     * map : null
     */

    private String id;
    private String name;
    private String type;
    private String parent_id;
    private String weights;
    private String status;
    private String create_time;
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
