package com.example.assignment2.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseEnrollment extends PersistentObject{
    protected String mCourseID;
    protected String mGrade;
    protected int mCWID;

    public CourseEnrollment(String courseID, String grade, int CWID) {
        mCourseID = courseID;
        mGrade = grade;
        mCWID = CWID;
    }

    public CourseEnrollment() {

    }
    public String getCourseID() {
        return mCourseID;
    }
    public void setCourseID(String courseID) {
        mCourseID = courseID;
    }

    public String getGrade() {
        return mGrade;
    }
    public void setGrade(String grade) {
        mGrade = grade;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("CourseID", mCourseID);
        vals.put("Grade", mGrade);
        vals.put("CWID", mCWID);
        db.insert("CourseEnrollment", null, vals);
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID Text, Grade Text, CWID INTEGER)");
    }
    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {
        mCourseID = c.getString(c.getColumnIndex("CourseID"));
        mGrade = c.getString(c.getColumnIndex("Grade"));
        mCWID = c.getInt(c.getColumnIndex("CWID"));
    }
}
