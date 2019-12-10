package com.example.assignment2;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.assignment2.model.Student;
import com.example.assignment2.model.StudentDB;
import com.example.assignment2.model.CourseEnrollment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    LinearLayout root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        createStudentObjects();
        setContentView(R.layout.student_summary);

        root = findViewById(R.id.student_summary);

//        ArrayList<Student> studentList = StudentDB.getInstance().getStudents();
        ArrayList<Student> studentList = new StudentDB(this).retrieveStudentObjects();
        for (int i=0; i<studentList.size(); i++) {
            Student s = studentList.get(i);
            LayoutInflater inflater = LayoutInflater.from(this);
            View row_view = inflater.inflate(R.layout.student_row, root, false);

            TextView firstName = (TextView) row_view.findViewById(R.id.first_name);
            firstName.setText(s.getFirstName());

            TextView lastName = (TextView) row_view.findViewById(R.id.last_name);
            lastName.setText(s.getLastName());

            TextView CWID = (TextView) row_view.findViewById(R.id.CWID);
            CWID.setText(s.getCWID());
            root.addView(row_view);
        }
    }

//    protected void createStudentObjects() {
//        Student s1 = new Student("Oscar", "Cheung", 333);
//        ArrayList<CourseEnrollment> courses = new ArrayList<CourseEnrollment>();
//        courses.add(new CourseEnrollment("CPSC349" , "A"));
//        courses.add(new CourseEnrollment("CPSC411", "B"));
//        courses.add(new CourseEnrollment("CPSC474", "C"));
//        s1.setCourses(courses);
//
//        Student s2 = new Student("Kobe", "Bryant", 1);
//        courses = new ArrayList<CourseEnrollment>();
//        courses.add(new CourseEnrollment("CPSC301", "A"));
//        s2.setCourses(courses);
//
//        Student s3 = new Student("Lebron", "James", 2);
//        courses = new ArrayList<CourseEnrollment>();
//        courses.add(new CourseEnrollment("CPSC201", "A"));
//        s3.setCourses(courses);
//
//        ArrayList<Student>studentList = new ArrayList<>();
//        studentList.add(s1);
//        studentList.add(s2);
//        studentList.add(s3);
//
//        StudentDB.getInstance().setStudents(studentList);
//    }
}
