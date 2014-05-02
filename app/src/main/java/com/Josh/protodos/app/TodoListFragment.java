package com.Josh.protodos.app;


import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.todos_title);
        mTodos = TodoList.get(getActivity()).getTodos();

        TodoAdapter adapter = new TodoAdapter(mTodos);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView l, View v, int position, long id){
        Todo t = ((TodoAdapter)getListAdapter()).getItem(position);

        //start Todo_Pager_Activity
        Intent i = new Intent(getActivity(), TodoPagerActivity.class);
        i.putExtra(TodoFragment.EXTRA_TODO_ID, t.getID());
        startActivity(i);

    }

    //inner todoAdapter class to properly present each todoFragment
    private class TodoAdapter extends ArrayAdapter<Todo> {
        public TodoAdapter(ArrayList<Todo> todos) {
            super(getActivity(), 0, todos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_todo, null);
            }

            Todo t = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.todo_list_item_titleTextView);
            titleTextView.setText(t.getTitle());

            final CheckBox solvedCheckBox =
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_todo_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_new_todo:
                Todo todo = new Todo();
                TodoList.get(getActivity()).addTodo(todo);
                Intent i = new Intent(getActivity(), TodoPagerActivity.class);
                i.putExtra(TodoFragment.EXTRA_TODO_ID, todo.getID());
                startActivityForResult(i, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo MenuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.todo_list_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        TodoAdapter adapter = (TodoAdapter) getListAdapter();
        Todo todo = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_todo:
                TodoList.get(getActivity()).deleteTodo(todo);
                adapter.notifyDataSetChanged();
                return true;
            }

        return super.onContextItemSelected(item);

    }

    //Add functionality to delete fragments and update the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        ListView listView = (ListView)v.findViewById(android.R.id.list);
        registerForContextMenu(listView);
        return v;
    }



}
