package com.example.assignment2;

import android.os.Bundle;
import android.widget.EditText;

import com.example.assignment2.model.Student;
import com.example.assignment2.model.StudentDB;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        int studentInd = getIntent().getIntExtra("StudentIndex", 0);

        Student studentObject = StudentDB.getInstance().getStudents().get(studentInd);
        EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
        EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
        fNameView.setText(studentObject.getFirstName());
        lNameView.setText(studentObject.getLastName());
    }
}
