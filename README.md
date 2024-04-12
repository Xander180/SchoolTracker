# WGU Mobile Application Developmenet - C196

## Overview
This Android application is a basic student scheduler as defined by the requirements of C196.  It allows a student to:

- Track Terms
- Track Courses in a given Term
- Track Assessments in a given Course
- Track Instructors and their contact information and associate them with a Course

## Android Version and Hardware
This project was designed to be used with Android API 28 (Pie).  This project was tested on a Pixel 3a (Emulator) and a Pixel 4a (Physical).  See the list of Libraries used for additional versioning information.

## Libraries Used
As of submission, here is the list of libraries used in the gradle file:

```gradle
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Room components
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    androidTestImplementation("androidx.room:room-testing:2.6.1")
}
```

## How to build
Open this project in Android Studio and build.  As long as the gradle file is there, everything should build successfully.

## StoryBoard and How to Use the App
The app consists of 1 primary activity and 4 sub activities.

### The Main Activity
![1-MainMenu](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/8af6e052-4afd-4089-ab17-f96610560bb7)

The main screen provides navigation to the 4 sub-activities. There is also a submenu for adding sample data and deleting all data.

#### Navigate to the Sub-Activities

In order to navigate to the sub-activies of the app, simply click on any of the buttons (Terms, Courses, etc.)

### Term Activity

The Term activity allows the user to view their list of terms.  They can go to a detailed view of the term by clicking on the button.  They can also create a new term by hitting the + button.

![2-TermActivity](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/29a5b59a-bb79-47dc-a65f-0458b43a3081)

#### Term Details Activity

By selecting the Term Button the user opens the Term Details Activity (`TermDetailsActivity.java`).  The Term Details Activity allows the user to view the term's details as well as add courses to the term. The user
may also edit or delete the selected term. Terms cannot be deleted if there are associated courses.


![3-TermDetails](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/7d9d6d87-af8f-402e-8909-a350dbbd0140)

##### Add a Course to a Term

To add a course to a term, the user should click the + icon in the Term Detail Activity. The course list popup menu is scrollable.

Once the course is added, it will show up in the Term Details section.

![14-AddAssoicatedCourses](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/6d3326c5-1f76-47d6-badc-952b5233dc03)



#### Term Edit Activity

By selecting the edit icon Floating Action Button, the user navigates to the Term Edit Activity (`TermEditActivity.java`).  In that activity, the user can edit the Term title, start date, and end date.

![4-TermEdit](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/4bc118b6-327e-442f-aa3a-dcf8c55b95c1)


When the user is done editing, they can hit the Save button in the top right of the screen to save the edited term.


### Courses Activity

The Courses activity allows the user to view their list of courses.  They can go to a detailed view of the course by clicking on the button.  They can also create a new course by hitting the + button.

![5-CourseActivity](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/72ee1537-044c-4de7-8ce2-5547251a0635)

#### Course Details Activity

By selecting the Course Button, the user opens the Course Details Activity (`CourseDetailsActivity.java`).  The Course Details Activity allows the user to view the course's details as well as add assessments and instructors to the course.
The user may also edit or delete the selected course. There is also a submenu at the top right corner where the user can set a notification reminder for when the course is starting and ending.

![6-CourseDetails](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/7b6f893b-86c4-4643-962c-ca74edde62b1)



#### Course Edit Activity

By selecting the edit icon Floating Action Button, the user navigates to the Course Edit Activity (`CourseEditActivity.java`).  In that activity, the user can edit the Term title, start date, end date, course status, and add an optional note.

![7-CourseEdit](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/c058148d-8bf5-4ba7-a0a3-cca0aa32ccd2)


### Assessment Activity

The Assessment activity allows the user to view their list of assessments.  They can go to a detailed view of the assessment by clicking on the button.  They can also create a new assessment by hitting the + button.

![8-AssessmentActivity](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/dc0e202f-5377-468c-815a-f9d28df49f76)


#### Assessment Details Activity

By selecting the Assessment Button, the user opens the Assessment Details Activity (`AssessmentDetailsActivity.java`).  The Assessment Details Activity allows the user to view the assessment's details.
The user may also edit or delete the selected assessment. There is also a submenu at the top right corner where the user can set a notification reminder for when the assessment is due.

![9-AssessmentDetails](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/01cd535f-a244-474d-b638-5f172e3c099c)


#### Assessment Edit Activity

By selecting the edit icon Floating Action Button, the user navigates to the Assessment Edit Activity (`AssessmentEditActivity.java`).  In that activity, the user can edit the assessment title, due date, and assessment type.

![10-AssessmentEdit](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/8fa48910-2744-4b04-8104-a72d32e0af86)



### Instructor Activity

The Instructor Activity provides a location for the user to view their course instructors and their contact information.  Instructors can be assigned to a course in the course details page.

![11-InstructorActivity](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/e3f42d13-1c6b-498c-aa25-5b32fcdca14f)


#### Instructor Details Activity

By selecting the Instructor Button, the user opens the Instructor Details Activity (`InstructorDetailsActivity.java`).  The Instructor Details Activity allows the user to view the instructor's contact information.
The user may also edit or delete the selected instructor.

![12-InstructorDetails](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/cad278e0-c4fe-4fd0-a439-a9236afaf743)


#### Instructor Edit Activity

By selecting the edit icon Floating Action Button, the user navigates to the Instructor Edit Activity (`InstructorEditActivity.java`).  In that activity, the user can edit the instructor's name, email address, and phone number.

![13-InstructorEdit](https://github.com/Xander180/WGU-C196-SchoolTracker/assets/67243244/fd006b69-8f00-4d51-a8bc-b679381b465a)

