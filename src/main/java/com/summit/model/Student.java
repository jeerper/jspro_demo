package com.summit.model;

import org.springframework.stereotype.Component;

/**
 * @author: jeerper
 * @date: 2021-07-01 14:50:44
 */
//@Component
public class Student {
    private String id;
    private String score;
    private String grade;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
