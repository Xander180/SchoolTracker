package com.wguc196.schooltracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.wguc196.schooltracker.DAO.AssessmentDAO;
import com.wguc196.schooltracker.DAO.CourseDAO;
import com.wguc196.schooltracker.DAO.InstructorDAO;
import com.wguc196.schooltracker.DAO.TermDAO;
import com.wguc196.schooltracker.entities.Assessment;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.Instructor;
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.DateConverter;

@Database(entities = {Term.class, Course.class, Assessment.class, Instructor.class}, version = 3, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class SchoolDatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract InstructorDAO instructorDAO();
    private static volatile SchoolDatabaseBuilder INSTANCE;

    static SchoolDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SchoolDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SchoolDatabaseBuilder.class, "SchoolDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
