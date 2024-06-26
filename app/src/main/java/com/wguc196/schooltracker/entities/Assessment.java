package com.wguc196.schooltracker.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assessments")
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String title;
    private Date date;
    private final AssessmentType assessmentType;
    private int courseID;

    @Ignore
    public Assessment(int assessmentID, String title, Date date, AssessmentType assessmentType) {
        this.assessmentID = assessmentID;
        this.title = title;
        this.date = date;
        this.assessmentType = assessmentType;
    }

    public Assessment(int assessmentID, String title, Date date, AssessmentType assessmentType, int courseID) {
        this.assessmentID = assessmentID;
        this.title = title;
        this.date = date;
        this.assessmentType = assessmentType;
        this.courseID = courseID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AssessmentType getAssessmentType() {
        return assessmentType;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
