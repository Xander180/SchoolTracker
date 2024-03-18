package com.wguc196.schooltracker.helpers;

import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Assessment;
import com.wguc196.schooltracker.entities.AssessmentType;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.CourseStatus;
import com.wguc196.schooltracker.entities.Instructor;
import com.wguc196.schooltracker.entities.Term;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {
    private static final String SAMPLE_TERM_TITLE = "Sample Term";
    private static final String SAMPLE_COURSE_TITLE = "Sample Course";
    private static final String SAMPLE_ASSESSMENT_TITLE = "Sample Assessment";

    private static Date getDate(int diff) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, diff);
        return calendar.getTime();
    }

    public static List<Term> getTerms() {
        List<Term> sampleTerms = new ArrayList<>();

        sampleTerms.add(new Term(10000, SAMPLE_TERM_TITLE + " 1", getDate(0), getDate(10)));
        sampleTerms.add(new Term(10001, SAMPLE_TERM_TITLE + " 2", getDate(-100), getDate(10)));
        sampleTerms.add(new Term(10002, SAMPLE_TERM_TITLE + " 3", getDate(-1000), getDate(10)));

        return sampleTerms;
    }

    public static List<Course> getCourses() {
        List<Course> sampleCourses = new ArrayList<>();

        sampleCourses.add(new Course(10000, SAMPLE_COURSE_TITLE + " 1", getDate(0), getDate(10), CourseStatus.IN_PROGRESS, "", 10000));
        sampleCourses.add(new Course(10001, SAMPLE_COURSE_TITLE + " 2", getDate(-100), getDate(10), CourseStatus.IN_PROGRESS, "", 10001));
        sampleCourses.add(new Course(10002, SAMPLE_COURSE_TITLE + " 3", getDate(-1000), getDate(10), CourseStatus.IN_PROGRESS, "", 10002));

        return sampleCourses;
    }

    public static List<Assessment> getAssessments() {
        List<Assessment> sampleAssessments = new ArrayList<>();

        sampleAssessments.add(new Assessment(10000, SAMPLE_ASSESSMENT_TITLE + " 1", getDate(0), AssessmentType.OA, 10000));
        sampleAssessments.add(new Assessment(10001, SAMPLE_ASSESSMENT_TITLE + " 2", getDate(-100), AssessmentType.PA, 10001));
        sampleAssessments.add(new Assessment(10002, SAMPLE_ASSESSMENT_TITLE + " 3", getDate(-1000), AssessmentType.OA, 10002));

        return sampleAssessments;
    }

    public static List<Instructor> getInstructors() {
        List<Instructor> sampleInstructors = new ArrayList<>();

        sampleInstructors.add(new Instructor("Arno Dorian", "arno.dorian@wgu.edu", "123-456-7890", 10000));
        sampleInstructors.add(new Instructor("James Kidd", "james.kidd@wgu.edu", "234-567-8901", 10001));
        sampleInstructors.add(new Instructor("Haythem Kenway", "haythem.kenway@wgu.edu", "345-678-9012", 10002));

        return  sampleInstructors;
    }

    public static void insertSampleData (Repository repository) {
        for (Term term : getTerms()) {
            repository.insert(term);
        }
        for (Course course : getCourses()) {
            repository.insert(course);
        }
        for (Assessment assessment : getAssessments()) {
            repository.insert(assessment);
        }
        for (Instructor instructor : getInstructors()) {
            repository.insert(instructor);
        }
    }
}
