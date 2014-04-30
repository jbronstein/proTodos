package com.Josh.protodos.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;



public class TodoFragment extends Fragment {

    private Todo mTodo;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTodo = new Todo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todo, parent, false);

        mTitleField=(EditText)v.findViewById(R.id.todo_title);
        mTitleField.addTextChangedListener(new TextWatcher(){
            public void onTextChanged (
                    CharSequence c,int start, int before, int count){
                mTodo.setTitle(c.toString());
            }

            public void beforeTextChanged(
                CharSequence c, int start, int count, int after) {
                // This space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // This one too
            }
        });

        mDateButton = (Button)v.findViewById(R.id.todo_date);
        mDateButton.setText(mTodo.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.todo_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mTodo.setSolved(isChecked);
            }
        });

        return v;

        }
}