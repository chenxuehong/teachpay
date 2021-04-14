package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonResult;

public class IdentifyResultModel extends JsonResult<IdentifyResultModel.IdentifyResultEntity> {
    public class IdentifyResultEntity {

        /**
         * result : false
         * remark : 修改失败失败
         */

        private boolean result;
        private String remark;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
