package com.huihe.base_lib.model.im;

public class CustomMsgEntity {

    /**
     * cmd : CustomCmdMsg
     * data : {"cmd":"1","msg":"我这边重新发"}
     * userAvatar : http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png
     * userName : 车模ooo
     */

    private String cmd;
    private DataBean data;
    private String userAvatar;
    private String userName;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static class DataBean {
        /**
         * cmd : 1
         * msg : 我这边重新发
         */

        private String cmd;
        private String msg;

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
