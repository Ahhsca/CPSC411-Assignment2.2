package com.example.assignment2.model;

import java.util.ArrayList;

public class StudentDB {
    private static final StudentDB mInstance = new StudentDB();

    private ArrayList<Student> mStudents;

    static public StudentDB getInstance() {
        return mInstance;
    }

    public ArrayList<Student> getStudents() {
        return mStudents;
    }

    public void setStudents(ArrayList<Student> students) {
        mStudents = students;
    }
}
