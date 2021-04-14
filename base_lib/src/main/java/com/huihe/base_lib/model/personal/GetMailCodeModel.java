package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonResult;

public class GetMailCodeModel extends JsonResult<GetMailCodeModel.GetMailCodeEntity> {
    public class GetMailCodeEntity {

        /**
         * code : 10016
         * message : 该账户已存在
         * data : null
         * time : 1580818609335
         * resultEnum : null
         */

        private int code;
        private String message;
        private Object data;
        private long time;
        private Object resultEnum;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Object getResultEnum() {
            return resultEnum;
        }

        public void setResultEnum(Object resultEnum) {
            this.resultEnum = resultEnum;
        }
    }
}
