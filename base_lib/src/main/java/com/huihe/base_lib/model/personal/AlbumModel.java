package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

public class AlbumModel extends JsonListResult<AlbumModel.AlbumEntity> {
    public class AlbumEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 22
         * user_id : 9099
         * create_time : 2019-06-21 09:49:14
         * update_time : 2019-12-26 10:14:08
         * photo : http://img.curiousmore.com/F969874D-6BA4-49F0-A466-42805997E88E/Documents/1huihe1561081893.png,http://img.curiousmore.com/F969874D-6BA4-49F0-A466-42805997E88E/Documents/1huihe1561081894.png,http://img.curiousmore.com/F969874D-6BA4-49F0-A466-42805997E88E/Documents/1huihe1561081895.png,http://img.curiousmore.com/F969874D-6BA4-49F0-A466-42805997E88E/Documents/1huihe1561081895.png,http://img.curiousmore.com/85DF75F3-7498-41AA-B57F-DB9994D11590/Documents/1huihe1565169436.png,http://img.curiousmore.com/85DF75F3-7498-41AA-B57F-DB9994D11590/Documents/1huihe1565169437.png,http://img.curiousmore.com/85DF75F3-7498-41AA-B57F-DB9994D11590/Documents/1huihe1565169437.png,http://img.curiousmore.com/D-9193-470A-950D-7A68982BAF92/data/Containers/Data/Application/3AB7B2E0-5B10-4DF9-AA55-4935B1AA97AE/Documents/1huihe1565836005.png,http://img.curiousmore.com/282-7C28DDBF5464/Documents/1huihe1571192210.png,http://img.curiousmore.com/E-F384-4A16-9C74-D261F7096666/data/Containers/Data/Application/AEEB075E-FA3D-497E-9933-D4B3FA2DEC3C/Documents/1huihe1571291982.png,http://img.curiousmore.com/596-E84390E30514/Documents/1huihe1571647436.png,http://img.curiousmore.com/596-E84390E30514/Documents/1huihe1571647438.png,http://img.curiousmore.com/596-E84390E30514/Documents/1huihe1571647439.png,http://img.curiousmore.com/596-E84390E30514/Documents/1huihe1571647439.png,http://img.curiousmore.com/910-D43EBA799927/Documents/1huihe1572510996.png,http://img.curiousmore.com/481-78461FA144AB/Documents/1huihe1573178365.png,http://img.curiousmore.com/015-4148CEE274B1/Documents/1huihe1573983807.png,http://img.curiousmore.com/646-300B1AEDA531/Documents/1huihe1577084383.png,http://img.curiousmore.com/A14-405EC583F935/Documents/1huihe1577084591.png,http://img.curiousmore.com/3A7-5C4F37C8F530/Documents/1huihe1577084704.png,http://img.curiousmore.com/7EB-D5D52E7DFCD5/Documents/1huihe1577326457.png
         * status : 1
         * remark :
         */

        private String id;
        private String user_id;
        private String create_time;
        private String update_time;
        private String photo;
        private int status;
        private String remark;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
