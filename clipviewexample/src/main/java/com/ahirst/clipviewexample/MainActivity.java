package com.ahirst.clipviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ahirst.clipview.ClipView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the clip view
        ClipView clipView = (ClipView) findViewById(R.id.clip_view);

        // Get the list
        ListView paramList = (ListView) findViewById(R.id.list_parameter);
        paramList.setAdapter(new ParameterListAdapter(this, clipView));
    }
}
