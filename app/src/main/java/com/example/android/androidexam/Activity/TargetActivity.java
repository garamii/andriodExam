package com.example.android.androidexam.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.android.androidexam.R;

public class TargetActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        //내가 호출 된 Intent로 부터 데이터 취득

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");

        Toast.makeText(getApplicationContext(), "name :" + name + ",phone" + phone, Toast.LENGTH_SHORT).show();

        findViewById(R.id.finish_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Activity 종료
        finish();
    }
}
