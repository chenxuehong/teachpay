package com.huihe.base_lib.model;

import java.util.List;

public class MechanismCategoryEntity {

    /**
     * id : 10
     * name : 教辅学科
     * type : 1
     * parent_id : 0
     * weights : 0
     * status : 2
     * create_time : 2021-01-13 17:58:11
     * update_time : 2021-01-13 17:59:26
     */

    private String id;
    private String name;
    private String type;
    private String parent_id;
    private String weights;
    private String status;
    private String create_time;
    private String update_time;
    private Map map;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getWeights() {
        return weights;
    }

    public String getStatus() {
        return status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public Map getMap() {
        return map;
    }

    public static class Map{
        private List<MechanismCategoryChildEntity> childList;

        public List<MechanismCategoryChildEntity> getChildList() {
            return childList;
        }
    }
}
