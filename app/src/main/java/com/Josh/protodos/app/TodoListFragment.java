package com.Josh.protodos.app;


import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class TodoListFragment extends ListFragment {

    private ArrayList<Todo> mTodos;
    private static final String TAG = "TodoListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.todos_title);
        mTodos = TodoList.get(getActivity()).getTodos();

        TodoAdapter adapter = new TodoAdapter(mTodos);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Todo t = ((TodoAdapter)getListAdapter()).getItem(position);

        //start Todo_Pager_Activity
        Intent i = new Intent(getActivity(), TodoPagerActivity.class);
        i.putExtra(TodoFragment.EXTRA_TODO_ID, t.getID());
        startActivity(i);

    }

    private class TodoAdapter extends ArrayAdapter<Todo> {
        public TodoAdapter(ArrayList<Todo> todos) {
            super(getActivity(), 0, todos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_todo, null);
            }
            //Configure view for this Todo item
            Todo t = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.todo_list_item_titleTextView);
            titleTextView.setText(t.getTitle());

            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.todo_list_item_dateTextView);
            dateTextView.setText(t.getDate().toString());

            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.todo_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(t.isSolved());

            return convertView;


        }

    }

    @Override
    public void onResume(){
        super.onResume();
        ((TodoAdapter)getListAdapter()).notifyDataSetChanged();

    }


}
