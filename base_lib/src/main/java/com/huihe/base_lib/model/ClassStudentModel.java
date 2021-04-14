package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonResult;

import java.util.List;

public class ClassStudentModel extends JsonResult<ClassStudentModel.ClassStudentData> {
    public static class ClassStudentData{

        private String total;
        private List<ClassStudentBean> rows;

        public String getTotal() {
            return total;
        }

        public List<ClassStudentBean> getRows() {
            return rows;
        }
    }
}
