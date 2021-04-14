package com.huihe.base_lib.model;

public class MechanismAverageScoreEntity {

    /**
     * course_quality : 0
     * environment : 0
     * average_score : 0
     * cost_effectiveness : 0
     * commentCount : 0
     * faculty : 0
     * attitude : 0
     */

    private Float course_quality;
    private Float environment;
    private Float average_score;
    private Float cost_effectiveness;
    private String commentCount;
    private Float faculty;
    private Float attitude;

    public Float getCourse_quality() {
        return course_quality;
    }

    public Float getEnvironment() {
        return environment;
    }

    public Float getAverage_score() {
        return average_score;
    }

    public Float getCost_effectiveness() {
        return cost_effectiveness;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public Float getFaculty() {
        return faculty;
    }

    public Float getAttitude() {
        return attitude;
    }
}
