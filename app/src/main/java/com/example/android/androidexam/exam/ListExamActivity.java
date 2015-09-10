
package com.example.android.androidexam.exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.androidexam.R;

import java.util.ArrayList;
import java.util.List;

public class ListExamActivity extends AppCompatActivity {

    private List<ItemExam> mData;
    private CustomAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exam);

        initdata();
        initAdapter();
        initListView();
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.listView_item);
        listView.setAdapter(mAdapter);

    }

    private void initdata() {
        mData = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            ItemExam itemExam = new ItemExam();
            itemExam.setImageRecourceId(R.drawable.back4);
            itemExam.setTitle("item" + i);
            itemExam.setContents("item" + i);
            mData.add(itemExam);

        }
    }
    private void initAdapter(){
        mAdapter = new CustomAdapter(this,mData);
    }


}
