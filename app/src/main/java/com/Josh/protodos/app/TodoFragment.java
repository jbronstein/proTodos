package com.Josh.protodos.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import java.util.Date;
import java.util.UUID;


public class TodoFragment extends Fragment {


    public static final String EXTRA_TODO_ID = "com.Josh.protodos.app.todo_id";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;

    private Todo mTodo;
    private EditText mTitleField;
    private EditText mDetailsField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    //get the right fragment item for us to look at
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID todoId = (UUID)getArguments().getSerializable(EXTRA_TODO_ID);

        mTodo = TodoList.get(getActivity()).getTodo(todoId);
    }

    //new fragment instance
    public static TodoFragment newInstance(UUID todoId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TODO_ID, todoId);

        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void updateDate(){
        mDateButton.setText(mTodo.getDate().toString());
    }

    //Main View method, generate the title, details, dialog date, and checkbox
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todo, parent, false);

        mTitleField=(EditText)v.findViewById(R.id.todo_title);
        mTitleField.setText(mTodo.getTitle());
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


        mDetailsField=(EditText)v.findViewById(R.id.details_text);
        mDetailsField.setText(mTodo.getDetails());
        mDetailsField.addTextChangedListener(new TextWatcher(){
            public void onTextChanged (
                    CharSequence c,int start, int before, int count){
                mTodo.setDetails(c.toString());
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
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mTodo.getDate());
                dialog.setTargetFragment(TodoFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }

        });


        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.todo_solved);
        mSolvedCheckBox.setChecked(mTodo.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mTodo.setSolved(isChecked);
            }
        });

        return v;
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTodo.setDate(date);
            updateDate();
        }

    }

}