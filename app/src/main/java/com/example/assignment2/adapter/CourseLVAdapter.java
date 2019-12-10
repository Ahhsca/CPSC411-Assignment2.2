package com.example.assignment2.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.assignment2.R;
import com.example.assignment2.model.CourseEnrollment;
import com.example.assignment2.model.Student;

public class CourseLVAdapter extends BaseAdapter {

    protected Student mStudent;

    private class CustomWatcher implements TextWatcher
    {
        private CourseEnrollment item;
        private int pos;
        private boolean isCourseID;

        public CustomWatcher(CourseEnrollment item, int pos, boolean isCourseID){
            this.item = item;
            this.pos = pos;
            this.isCourseID = isCourseID;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){
            if (isCourseID) {
                getItem(pos).setCourseID(charSequence.toString());
            }
            else {
                getItem(pos).setGrade(charSequence.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable editable){
        }
    }

    public CourseLVAdapter(Student student) {
        mStudent = student;
    }

    public Student getStudent() {
        return mStudent;
    }

    @Override
    public int getCount() {
        return mStudent.getCourses().size();
    }

    @Override
    public CourseEnrollment getItem(int position) {
        return mStudent.getCourses().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row_view;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row_view = inflater.inflate(R.layout.grade_row, parent, false);
        } else row_view = convertView;

        CourseEnrollment course = getItem(position);
        final int pos = position;

        final EditText courseView = (EditText) row_view.findViewById(R.id.course_id);
        final EditText gradeView = (EditText) row_view.findViewById(R.id.grade_id);

        CustomWatcher oldCourseWatcher = (CustomWatcher)courseView.getTag();
        if(oldCourseWatcher != null) {
            courseView.removeTextChangedListener(oldCourseWatcher);
        }
        CustomWatcher oldGradeWatcher = (CustomWatcher)gradeView.getTag();
        if(oldGradeWatcher != null) {
            gradeView.removeTextChangedListener(oldGradeWatcher);
        }

        courseView.setText(course.getCourseID());
        gradeView.setText(course.getGrade());
        row_view.setTag(new Integer(position));

        CustomWatcher newCourseWatcher = new CustomWatcher(course, pos, true);
        courseView.setTag(newCourseWatcher);
        courseView.addTextChangedListener(newCourseWatcher);

        CustomWatcher newGradeWatcher = new CustomWatcher(course, pos, false);
        gradeView.setTag(newGradeWatcher);
        gradeView.addTextChangedListener(newGradeWatcher);

        return row_view;
    }
}

