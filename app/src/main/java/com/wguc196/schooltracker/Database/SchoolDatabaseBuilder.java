package com.wguc196.schooltracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wguc196.schooltracker.DAO.AssessmentDAO;
import com.wguc196.schooltracker.DAO.CourseDAO;
import com.wguc196.schooltracker.DAO.TermDAO;
import com.wguc196.schooltracker.Entities.Assessment;
import com.wguc196.schooltracker.Entities.Course;
import com.wguc196.schooltracker.Entities.Term;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 1, exportSchema = false)
public abstract class SchoolDatabaseBuilder extends RoomDatabase {
    public  abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
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
