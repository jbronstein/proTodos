package com.Josh.protodos.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Josh on 4/30/14.
 */
public class TodoList {
    private ArrayList<Todo> mTodos;

    private static TodoList sTodoList;
    private Context mAppContext;

    private TodoList(Context appContext) {
        mAppContext = appContext;
        mTodos = new ArrayList<Todo>();
        for (int i = 0; i < 100; i++){
            Todo t = new Todo();
            t.setTitle("Crime #" + i);
            t.setSolved(i % 2 == 0);
            mTodos.add(t);
        }
    }

    public static TodoList get(Context c){
        if (sTodoList == null){
            sTodoList = new TodoList(c.getApplicationContext());
        }
        return sTodoList;
    }

    public ArrayList<Todo> getTodos(){
        return mTodos;
    }

    public Todo getTodo(UUID id){
        for (Todo t : mTodos) {
            if (t.getID().equals(id))
                return t;
        }
        return null;
    }

}
