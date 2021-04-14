package com.huihe.base_lib.model.banner;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.ui.widget.banner.entity.SimpleBannerInfo;

public class BannerModel extends JsonListResult<BannerModel.BannerEntity> {

    public static class BannerEntity extends SimpleBannerInfo {

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
         * type : advertisement
         * pic : http://www.huihejituan.com/tripPictstorage/qmore/banner/779549f908bf11be460ced05a533137.jpg
         * source_url : http://www.huihejituan.com:8082/qmore/#/LearningBook
         * source_type : web
         * create_time : 2019-05-24 19:35:22
         * update_time : 2019-10-25 16:38:04
         * state : 2
         * type_id : 57
         * theme :
         */

        private String id;
        private String type;
        private String pic;
        private String source_url;
        private String source_type;
        private String create_time;
        private String update_time;
        private int state;
        private String type_id;
        private String theme;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public String getSource_type() {
            return source_type;
        }

        public void setSource_type(String source_type) {
            this.source_type = source_type;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        @Override
        public Object getXBannerUrl() {
            return getPic();
        }
    }
}
