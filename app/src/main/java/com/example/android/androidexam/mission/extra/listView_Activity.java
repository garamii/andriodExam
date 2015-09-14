
package com.example.android.androidexam.mission.extra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.androidexam.R;

import java.util.ArrayList;
import java.util.List;

public class ListView_Activity extends AppCompatActivity {

    private List<ListData> mData;
    private ListViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_);

        initData();
        initAdapter();
        initListView();

    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.listView_item);
        listView.setAdapter(mAdapter);


    }

    private void initAdapter() {
        mAdapter = new ListViewAdapter(this,mData);


    }

    private void initData() {
        mData = new ArrayList<>();
        for(int i = 1 ; i <= 200 ;i++){
           ListData data = new ListData();
            data.setImageResourceID(R.mipmap.ic_launcher);
            data.setTitle("Item"+i);
            data.setContents("Contents" + i);
            mData.add(data);

        }
    }


}
