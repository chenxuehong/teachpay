package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.base.JsonResult;

public class InsertMessageGroupResultModel extends JsonResult<InsertMessageGroupResultModel.ResultEntity> {

    public class ResultEntity{

        /**
         * result : 创建成功
         * groupId : 1577446767
         * id : 867
         */

        private String result;
        private String groupId;
        private int id;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
