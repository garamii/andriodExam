package com.example.android.androidexam.mission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.androidexam.R;

public class mission03Activity extends AppCompatActivity {

   private EditText id;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03);

        id = (EditText)findViewById(R.id.idEdText);
        password = (EditText)findViewById(R.id.pswdEdText);


        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(id.getText().toString().equals("1111")&&password.getText().toString().equals("1111")){
                Intent intent = new Intent(getApplicationContext(),loginpage.class);

                startActivity(intent);

            }
                Toast.makeText(getApplicationContext(), "로그인 정보가 잘못 되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });

    }



}
