package com.example.assignment2.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import java.io.File;

import java.util.ArrayList;

public class StudentDB {
//    private static final StudentDB mInstance = new StudentDB();

    public SQLiteDatabase mSQLiteDatabase;
    protected ArrayList<Student> mStudents;
    public StudentDB(Context context) {
        File dbFile = context.getDatabasePath("student.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

//        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID Text)");
//        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID Text, Grade Text, Student Text)");

        new Student().createTable(mSQLiteDatabase);
        new CourseEnrollment().createTable(mSQLiteDatabase);
    }

//    static public StudentDB getInstance() {
//        return mInstance;
//    }

    public ArrayList<Student> getStudents() {
        return mStudents;
    }

    public void setStudents(ArrayList<Student> students) {
        mStudents = students;
    }

    public void addStudent(Student student) {
        student.insert(mSQLiteDatabase);
    }
    public ArrayList<Student> retrieveStudentObjects() {
        mStudents = new ArrayList<Student>();
        Cursor c = mSQLiteDatabase.query("Student", null, null, null, null, null, null);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                Student studentObj = new Student();
                studentObj.initFrom(mSQLiteDatabase, c);
                mStudents.add(studentObj);
            }
        }
        return mStudents;
    }

    public void createSampleStudentObjs() {
        int unoCWID = 123;
        Student uno = new Student("Oscar", "Cheung", unoCWID);
        ArrayList<CourseEnrollment> courses = new ArrayList<>();
        courses.add(new CourseEnrollment("CPSC349", "A", unoCWID));
        courses.add(new CourseEnrollment("CPSC474", "B", unoCWID));
        courses.add(new CourseEnrollment("CPSC411", "B", unoCWID));
        uno.setCourses(courses);
        uno.insert(mSQLiteDatabase);

        int dosCWID = 2408;
        Student dos = new Student("Kobe", "Bryant", dosCWID);
        courses = new ArrayList<>();
        courses.add(new CourseEnrollment("CPSC343", "A", dosCWID));
        courses.add(new CourseEnrollment("CPSC567", "A", dosCWID));
        dos.setCourses(courses);
        dos.insert(mSQLiteDatabase);

        mStudents = new ArrayList<>();
        mStudents.add(uno);
        mStudents.add(dos);
    }
}
