package com.wguc196.schooltracker.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "instructors")
public class Instructor {
    @PrimaryKey(autoGenerate = true)
    private int instructorID;
    private String name;
    private String phone;
    private String email;
    private int courseID;

    @Ignore
    public Instructor() {

    }

    @Ignore
    public Instructor(int instructorID, String name, String phone, String email, int courseID) {
        this.instructorID = instructorID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.courseID = courseID;
    }

    @Ignore
    public Instructor(String name, String phone, String email, int courseID) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.courseID = courseID;
    }

    public Instructor(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
