package com.wguc196.schooltracker.database;

import android.app.Application;

import com.wguc196.schooltracker.DAO.AssessmentDAO;
import com.wguc196.schooltracker.DAO.CourseDAO;
import com.wguc196.schooltracker.DAO.InstructorDAO;
import com.wguc196.schooltracker.DAO.TermDAO;
import com.wguc196.schooltracker.entities.Assessment;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.Instructor;
import com.wguc196.schooltracker.entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final TermDAO mTermDAO;
    private final CourseDAO mCourseDAO;
    private final AssessmentDAO mAssessmentDAO;
    private final InstructorDAO mInstructorDAO;

    private List<Term> mAllTerms;
    private List<Course> mAllCourses;
    private List<Assessment> mAllAssessments;
    private List<Instructor> mAllInstructors;

    private Course course;
    private Assessment assessment;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        SchoolDatabaseBuilder db = SchoolDatabaseBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();
        mInstructorDAO = db.instructorDAO();
    }

    // Terms
    public List<Term> getmAllTerms() throws InterruptedException {
        databaseExecutor.execute(() -> mAllTerms = mTermDAO.getAllTerms());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  mAllTerms;
    }

    public void insert(Term term) {
        databaseExecutor.execute(() -> mTermDAO.insert(term));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void update(Term term) {
        databaseExecutor.execute(() -> mTermDAO.update(term));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void delete(Term term) {
        databaseExecutor.execute(() -> mTermDAO.delete(term));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void deleteAllTerms() {
        databaseExecutor.execute(mTermDAO::deleteAllTerms);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Courses
    public List<Course> getmAllCourses() throws InterruptedException {
        databaseExecutor.execute(() -> mAllCourses = mCourseDAO.getAllCourses());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  mAllCourses;
    }

    public Course getCourse(int courseID) throws InterruptedException {
        databaseExecutor.execute(() -> course = mCourseDAO.getCourse(courseID));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return course;
    }

    public List<Course> getmAssociatedCourses(int termID) throws InterruptedException {
        databaseExecutor.execute(() -> mAllCourses = mCourseDAO.getAssociatedCourses(termID));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  mAllCourses;
    }

    public void insert(Course course) {
        databaseExecutor.execute(() -> mCourseDAO.insert(course));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void update(Course course) {
        databaseExecutor.execute(() -> mCourseDAO.update(course));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void delete(Course course) {
        databaseExecutor.execute(() -> mCourseDAO.delete(course));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void deleteAllCourses() {
        databaseExecutor.execute(mCourseDAO::deleteAllCourses);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Assessments
    public List<Assessment> getmAllAssessments() throws InterruptedException {
        databaseExecutor.execute(() -> mAllAssessments = mAssessmentDAO.getAllAssessments());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  mAllAssessments;
    }

    public Assessment getmAssessment(int assessmentID) {
        databaseExecutor.execute(() -> assessment = mAssessmentDAO.getAssessment(assessmentID));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return assessment;
    }

    public List<Assessment> getmAssociatedAssessments(int courseID) throws InterruptedException {
        databaseExecutor.execute(() -> mAllAssessments = mAssessmentDAO.getAssociatedAssessments(courseID));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  mAllAssessments;
    }

    public void insert(Assessment assessment) {
        databaseExecutor.execute(() -> mAssessmentDAO.insert(assessment));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void update(Assessment assessment) {
        databaseExecutor.execute(() -> mAssessmentDAO.update(assessment));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void delete(Assessment assessment) {
        databaseExecutor.execute(() -> mAssessmentDAO.delete(assessment));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }

    public void deleteAllAssessments() {
        databaseExecutor.execute(mAssessmentDAO::deleteAllAssessments);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Instructors
    public List<Instructor> getmAllInstructors() throws InterruptedException {
        databaseExecutor.execute(() -> mAllInstructors = mInstructorDAO.getAllInstructors());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  mAllInstructors;
    }

    public List<Instructor> getmAssociatedInstructors(int courseID) throws InterruptedException {
        databaseExecutor.execute(() -> mAllInstructors = mInstructorDAO.getAssociatedInstructors(courseID));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  mAllInstructors;
    }

    public void insert(Instructor instructor) {
        databaseExecutor.execute(() -> mInstructorDAO.insert(instructor));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Instructor instructor) {
        databaseExecutor.execute(() -> mInstructorDAO.update(instructor));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Instructor instructor) {
        databaseExecutor.execute(() -> mInstructorDAO.delete(instructor));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllInstructors() {
        databaseExecutor.execute(mInstructorDAO::deleteAllInstructors);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        deleteAllTerms();
        deleteAllCourses();
        deleteAllAssessments();
        deleteAllInstructors();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
