
package com.example.android.androidexam.mission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.androidexam.R;

public class mission03Activity extends AppCompatActivity {

    private EditText mId;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03);

        mId = (EditText) findViewById(R.id.idEdText);
        mPassword = (EditText) findViewById(R.id.pswdEdText);

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mId.getText().toString().equals("1111")
                        && mPassword.getText().toString().equals("1111")) {
                    Intent intent = new Intent(getApplicationContext(), LoginPageActivity.class);

                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "로그인 정보가 잘못 되었습니다.", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

    }

}
