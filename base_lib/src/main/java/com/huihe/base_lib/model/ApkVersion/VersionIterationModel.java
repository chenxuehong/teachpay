package com.huihe.base_lib.model.ApkVersion;

import com.huihe.base_lib.model.base.JsonResult;

public class VersionIterationModel extends JsonResult<VersionIterationModel.VersionIterationEntity> {
    public static class VersionIterationEntity {

        /**
         * is_iteration : true
         * new_info : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":1,"user_id":36,"create_time":"2020-02-21 14:12:31","version":2,"url":"http://www.huihejituan.com/tripPictstorage/qmore/apk/__UNI__1E98192_0606110202.apk","status":2,"name":"好气地奇","is_new":true,"platform":"android","iteration_content":""}
         */

        private boolean is_iteration;
        private NewInfoBean new_info;

        public boolean isIs_iteration() {
            return is_iteration;
        }

        public void setIs_iteration(boolean is_iteration) {
            this.is_iteration = is_iteration;
        }

        public NewInfoBean getNew_info() {
            return new_info;
        }

        public void setNew_info(NewInfoBean new_info) {
            this.new_info = new_info;
        }

        public static class NewInfoBean {
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
             * user_id : 36
             * create_time : 2020-02-21 14:12:31
             * version : 2
             * url : http://www.huihejituan.com/tripPictstorage/qmore/apk/__UNI__1E98192_0606110202.apk
             * status : 2
             * name : 好气地奇
             * is_new : true
             * platform : android
             * iteration_content :
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
            private int user_id;
            private String create_time;
            private String version;
            private String url;
            private int status;
            private String name;
            private boolean is_new;
            private String platform;
            private String iteration_content;

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

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isIs_new() {
                return is_new;
            }

            public void setIs_new(boolean is_new) {
                this.is_new = is_new;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public String getIteration_content() {
                return iteration_content;
            }

            public void setIteration_content(String iteration_content) {
                this.iteration_content = iteration_content;
            }
        }
    }
}
