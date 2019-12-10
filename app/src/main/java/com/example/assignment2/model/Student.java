package com.example.assignment2.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.assignment2.R;

import java.util.ArrayList;

public class Student extends PersistentObject {
    protected String mFirstName;
    protected String mLastName;
    protected int mCWID;
    protected ArrayList<CourseEnrollment> mCourses;


    //NEW
    public Student() {
        mFirstName = "";
        mLastName = "";
        mCWID = 0;
        mCourses = new ArrayList<>();
    }
    public Student(String firstName, String lastName, int CWID) {
        mFirstName = firstName;
        mLastName = lastName;
        mCWID = CWID;
        mCourses = new ArrayList<>();
    }

    public void set(String firstName, String lastName, int CWID) {
        mFirstName = firstName;
        mLastName = lastName;
        mCWID = CWID;
    }


    //OLD
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
    public void addCourse(CourseEnrollment course) {
        mCourses.add(course);
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        db.insert("Student", null, vals);

        for(int i=0; i<mCourses.size(); i++) {
            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        mFirstName = cursor.getString(cursor.getColumnIndex("FirstName"));
        mLastName = cursor.getString(cursor.getColumnIndex("LastName"));
        mCWID = cursor.getInt(cursor.getColumnIndex("CWID"));
        mCourses = new ArrayList<CourseEnrollment>();

        Cursor c = db.query("CourseEnrollment", null, "CWID=?", new String[] {Integer.toString(mCWID)}, null, null, null);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                CourseEnrollment course = new CourseEnrollment();
                course.initFrom(db, c);
                mCourses.add(course);
            }
        }
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID INTEGER)");
    }

    public ArrayList<CourseEnrollment> retrieveCoursesFromDB(SQLiteDatabase db) {
        mCourses = new ArrayList<>();
        Cursor cursor = db.query("CourseEnrollment", null, "CWID=?", new String[]{new Integer(mCWID).toString()}, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                CourseEnrollment courseObj = new CourseEnrollment();
                courseObj.initFrom(db, cursor);
                mCourses.add(courseObj);
            }
        }
        return mCourses;
    }
}
