package com.example.assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.assignment2.adapter.CourseLVAdapter;
import com.example.assignment2.model.CourseEnrollment;
import com.example.assignment2.model.Student;
import com.example.assignment2.model.StudentDB;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentDetailsActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected ListView mCourses;
    protected Student studentObj;
    protected CourseLVAdapter ad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int studentInd = getIntent().getIntExtra("StudentIndex", -1);

        setContentView(R.layout.student_detail);
        final EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
        final EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
        final EditText cwidView = (EditText) findViewById(R.id.cwid_val_id);

        mToolbar = findViewById(R.id.toolbar);
        setActionBar(mToolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        TextView toolbarTitle = findViewById(R.id.title);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        final StudentDB studentDB = new StudentDB(this);
        ArrayList<Student> students = studentDB.retrieveStudentObjects();

        //if click on current student
        if (studentInd >= 0){
            toolbarTitle.setText("Edit Student");
            studentObj = studentDB.retrieveStudentObjects().get(studentInd);
        }
        else {
            toolbarTitle.setText("Add Student");
            studentObj = new Student();
            CourseEnrollment c = new CourseEnrollment("","", studentObj.getCWID());
            studentObj.addCourse(c);
        }

        mCourses = findViewById(R.id.courses_list);
        ad = new CourseLVAdapter(studentObj, studentDB.mSQLiteDatabase);
        mCourses.setAdapter(ad);


        Button addButton = findViewById(R.id.addCourse);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentObj.addCourse(new CourseEnrollment("","", studentObj.getCWID()));
                ad.notifyDataSetChanged();
            }
        });


        if (studentInd < 0) {
            fNameView.setText("");
            lNameView.setText("");
            cwidView.setText("");
        }
        else {
            fNameView.setText(studentObj.getFirstName());
            lNameView.setText(studentObj.getLastName());
            cwidView.setText(Integer.toString(studentObj.getCWID()));
        }

        Button doneButton = findViewById(R.id.nextButton);
        doneButton.setText("CONFIRM");
        doneButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (studentInd < 0) {
                            String fName = fNameView.getText().toString();
                            String lName = lNameView.getText().toString();
                            int cwid = Integer.parseInt(cwidView.getText().toString());
                            studentObj.set(fName, lName, cwid);
                            studentObj.insert(studentDB.mSQLiteDatabase);

//                            ArrayList<Student> studList = StudentDB.getInstance().getStudents();
                            ArrayList<Student> studentList = studentDB.retrieveStudentObjects();
//                            studentList.add(studentObj);
//                            StudentDB.getInstance().setStudents(studentList);
                        } else {
                            String fName = fNameView.getText().toString();
                            String lName = lNameView.getText().toString();
                            int cwid = Integer.parseInt(cwidView.getText().toString());
                            studentObj.set(fName, lName, cwid);
                            studentObj.insert(studentDB.mSQLiteDatabase);
//                            ArrayList<Student> studentList = studentDB.retrieveStudentObjects();
//                            StudentDB.getInstance().getStudents().set(studentInd, studentObj);
                        }
                        onBackPressed();
                        finish();
                    }
                }
        );
    }
}
