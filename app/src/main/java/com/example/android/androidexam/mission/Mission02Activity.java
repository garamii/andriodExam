package com.example.android.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.androidexam.R;


public class Mission02Activity extends AppCompatActivity {

    private Button tost;
    private Button close;
    private EditText editText;
    private TextView byteString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02);

        editText = (EditText) findViewById(R.id.editText);
        byteString = (TextView) findViewById(R.id.byteString);
        tost = (Button) findViewById(R.id.toast);
        close = (Button) findViewById(R.id.close);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               int length = s.toString().getBytes().length;
                byteString.setText(length+"/80 바이트");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), editText.getText().toString(),Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }


}


