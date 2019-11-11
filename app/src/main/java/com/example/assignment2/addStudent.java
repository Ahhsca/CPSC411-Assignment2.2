package com.example.assignment2;

import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment2.model.Student;
import com.example.assignment2.model.StudentDB;
import com.example.assignment2.model.CourseEnrollment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class addStudent extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        EditText fname = (EditText) findViewById(R.id.add_fname);
        EditText lname = (EditText) findViewById(R.id.add_lname);
        EditText CWID = (EditText) findViewById(R.id.add_CWID);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), SummaryLVActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
    public void goActivity (View view){
        Intent intent = new Intent(addStudent.this, SummaryLVActivity.class);
        startActivity(intent);
    }
    protected void createStudentObjects() {
        Student s1 = new Student("YO", "YO", 555);
        ArrayList<CourseEnrollment> courses = new ArrayList<CourseEnrollment>();
        courses.add(new CourseEnrollment("CPSC349" , "A"));
        courses.add(new CourseEnrollment("CPSC411", "B"));
        courses.add(new CourseEnrollment("CPSC474", "C"));
        s1.setCourses(courses);


        ArrayList<Student>studentList = new ArrayList<>();
        studentList.add(s1);

        StudentDB.getInstance().setStudents(studentList);
    }
}
