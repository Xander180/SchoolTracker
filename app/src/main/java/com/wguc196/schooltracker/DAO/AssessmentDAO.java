package com.wguc196.schooltracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.wguc196.schooltracker.entities.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM assessments ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();

    @Query("SELECT * FROM assessments WHERE assessmentID = :assessmentID")
    Assessment getAssessment(int assessmentID);

    @Query("SELECT * FROM assessments WHERE courseID = :courseID  ORDER BY assessmentID ASC")
    List<Assessment> getAssociatedAssessments(int courseID);

    @Query("DELETE FROM assessments")
    void deleteAllAssessments();
}
