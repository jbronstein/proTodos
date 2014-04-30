package com.Josh.protodos.app;

import android.support.v4.app.Fragment;

/**
 * Created by Josh on 4/30/14.
 */
public class TodoListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new TodoListFragment();

    }

}
