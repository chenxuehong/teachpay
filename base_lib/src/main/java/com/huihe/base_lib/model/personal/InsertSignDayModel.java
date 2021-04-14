package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonResult;

public class InsertSignDayModel extends JsonResult<InsertSignDayModel.InsertSignDayEntity> {
    public static class InsertSignDayEntity{

        /**
         * result : false
         * remark : 已经签到过，不能重复签到
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
