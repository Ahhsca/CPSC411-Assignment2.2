package com.example.assignment2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import com.example.assignment2.model.Student;
import com.example.assignment2.model.StudentDB;
import com.example.assignment2.model.CourseEnrollment;
import com.example.assignment2.adapter.SummaryLVAdapter;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class SummaryLVActivity extends Activity {

    protected ListView mSummaryView;
    protected SummaryLVAdapter ad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.summary_listview);

//        createStudentObjects();
        mSummaryView = findViewById(R.id.summary_list_view_id);
        ad = new SummaryLVAdapter(this);
        mSummaryView.setAdapter(ad);

    }

//    protected void createStudentObjects() {
//        Student s1 = new Student("Oscar", "Cheung", 333);
//        ArrayList<CourseEnrollment> courses = new ArrayList<CourseEnrollment>();
//        courses.add(new CourseEnrollment("CPSC349", "A", 23432));
//        courses.add(new CourseEnrollment("CPSC411", "B+", 34324));
//        courses.add(new CourseEnrollment("CPSC474", "B-",5345));
//        s1.setCourses(courses);
//
//        Student s2 = new Student("Kobe", "Bryant", 1);
//        courses = new ArrayList<CourseEnrollment>();
//        courses.add(new CourseEnrollment("CPSC301", "A",324));
//        s2.setCourses(courses);
//
//        Student s3 = new Student("Lebron", "James", 2);
//        courses = new ArrayList<CourseEnrollment>();
//        courses.add(new CourseEnrollment("CPSC201", "C",2345));
//        s3.setCourses(courses);
//
//        ArrayList<Student> studentList = new ArrayList<> ();
//        studentList.add(s1);
//        studentList.add(s2);
//        studentList.add(s3);
//
//        StudentDB.getInstance().setStudents(studentList);
//    }
    public void goToActivity (View v){
        Intent intent = new Intent(v.getContext(), StudentDetailsActivity.class);
        v.getContext().startActivity(intent);
    }
    @Override
    protected void onStart() {
        ad.notifyDataSetChanged();
        super.onStart();
    }

}
