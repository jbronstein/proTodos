package com.Josh.protodos.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Josh on 4/30/14.
 */
public class TodoPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList <Todo> mTodos;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mTodos = TodoList.get(this).getTodos();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return mTodos.size();
            }

            @Override
            public Fragment getItem(int position) {
                Todo todo = mTodos.get(position);
                return TodoFragment.newInstance(todo.getID());
            }


        });

        UUID todoId = (UUID)getIntent()
                .getSerializableExtra(TodoFragment.EXTRA_TODO_ID);
        for (int i = 0; i< mTodos.size(); i++) {
            if (mTodos.get(i).getID().equals(todoId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
     }


}
