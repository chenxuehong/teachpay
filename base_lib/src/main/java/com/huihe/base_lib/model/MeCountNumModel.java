package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonResult;

public class MeCountNumModel extends JsonResult<MeCountNumModel.MeCountNumEntity> {
    public static class MeCountNumEntity {

        /**
         * dayCount : 0
         * querySoonCount : 0
         * catCoinTotal : 369001
         * classMinuteTotal : 2819041
         * classCardCurriculumTotal : 69517
         * studyCardCurriculum : 1439
         */

        private int studentSoonCount;
        private int masterSoonCount;
        private int catCoinTotal;
        private int classCardMinuteTotal;
        private int classCardCurriculumTotal;
        private int studyCardCurriculum;

        public int getStudentSooncount() {
            return studentSoonCount;
        }

        public int getMasterSoonCount() {
            return masterSoonCount;
        }

        public int getCatCoinTotal() {
            return catCoinTotal;
        }

        public void setCatCoinTotal(int catCoinTotal) {
            this.catCoinTotal = catCoinTotal;
        }

        public int getClassMinuteTotal() {
            return classCardMinuteTotal;
        }

        public void setClassMinuteTotal(int classCardMinuteTotal) {
            this.classCardMinuteTotal = classCardMinuteTotal;
        }

        public int getClassCardCurriculumTotal() {
            return classCardCurriculumTotal;
        }

        public void setClassCardCurriculumTotal(int classCardCurriculumTotal) {
            this.classCardCurriculumTotal = classCardCurriculumTotal;
        }

        public int getStudyCardCurriculum() {
            return studyCardCurriculum;
        }

        public void setStudyCardCurriculum(int studyCardCurriculum) {
            this.studyCardCurriculum = studyCardCurriculum;
        }
    }
}
