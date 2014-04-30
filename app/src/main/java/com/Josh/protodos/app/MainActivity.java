package com.Josh.protodos.app;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;


public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        UUID todoId = (UUID)getIntent()
                .getSerializableExtra(TodoFragment.EXTRA_TODO_ID);

        return TodoFragment.newInstance(todoId);
    }

}


