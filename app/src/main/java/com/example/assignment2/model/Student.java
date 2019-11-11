package com.example.assignment2.model;

import java.util.ArrayList;

public class Student {
    protected String mFirstName;
    protected String mLastName;
    protected int mCWID;

    protected ArrayList<CourseEnrollment> mCourses;

    public Student(String firstName, String lastName, int CWID) {
        mFirstName = firstName;
        mLastName = lastName;
        mCWID = CWID;
    }

    public String getFirstName() {
        return mFirstName;
    }
    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }
    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getCWID() {
        return mCWID;
    }
    public void setCWID(int CWID) {
        mCWID = CWID;
    }

    public ArrayList<CourseEnrollment> getCourses() {
        return mCourses;
    }
    public void setCourses(ArrayList<CourseEnrollment> courses) {
        mCourses = courses;
    }
}
