
package com.example.android.androidexam.database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.androidexam.R;
import com.example.android.androidexam.database.contract.UserContract;
import com.example.android.androidexam.database.helper.UserDbHelper;
import com.example.android.androidexam.database.provider.UserProvider;


/**
 * Created by junsuk on 15. 9. 18.. DB 연습 - 로그인 Activity
 */
public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private UserDbHelper mUserDbHelper;
    private EditText mEmail;
    private EditText mPassword;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mCheckBox = (CheckBox) findViewById(R.id.check_email);

        findViewById(R.id.tv_sign_up).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

        mUserDbHelper = new UserDbHelper(this);
        //TODO  mEmail 에 SharedPreference 에 저장된값이 있으면 가져와서 셋팅

        mEmail.setText(loadEmail());

    }

    private String loadEmail() {

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("email" , "");
        return email;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.btn_login:
                // Todo 로그인 처리

                //                UserDbHelper helper = new UserDbHelper(this);
//                Cursor cursor = helper.query();

                Cursor cursor = getContentResolver().query(UserProvider.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    while (cursor.moveToNext()) {
                        String email = cursor.getString(
                                cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_EMAIL));
                        String password = cursor.getString(
                                cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_PASSWORD));
                        if (email.equals(mEmail.getText().toString())&&
                                password.equals(mPassword.getText().toString())) {
                            Toast.makeText(LogInActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            //TODO SharedPreference 저장하는 부분

                            if(mCheckBox.isChecked()){
                                saveEmail(mEmail.getText().toString());
                            }else {
                                saveEmail("");
                            }
                            return;
                        }
                    }

                }

                Toast.makeText(LogInActivity.this, "이메일 또는 패스워드가 틀렸습니다", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void saveEmail(String email) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("pref_email", email);
        editor.commit();  //동기식 sync
        //editor.apply(); //비동기식 async
    }
}
