package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonResult;

import java.util.List;

public class ClassRoomModel extends JsonResult<ClassRoomModel.ClassRoomEntity2> {
    public static class ClassRoomEntity2 {
        private String total;
        private List<ClassRoomEntity> rows;

        public String getTotal() {
            return total;
        }

        public List<ClassRoomEntity> getRows() {
            return rows;
        }
    }
}
