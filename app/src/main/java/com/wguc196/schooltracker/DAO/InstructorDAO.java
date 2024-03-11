package com.wguc196.schooltracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.wguc196.schooltracker.entities.Instructor;

import java.util.List;

@Dao
public interface InstructorDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Instructor instructor);

    @Update
    void update(Instructor instructor);

    @Delete
    void delete(Instructor instructor);

    @Query("SELECT * FROM instructors ORDER BY instructorID ASC")
    List<Instructor> getAllInstructors();

    @Query("SELECT * FROM instructors WHERE courseID = :courseID ORDER BY instructorID ASC")
    List<Instructor> getAssociatedInstructors(int courseID);
}
