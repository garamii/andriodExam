package com.example.android.androidexam.mission;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.androidexam.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mission05Activity extends AppCompatActivity implements View.OnClickListener {
    //변수명 설정
    public EditText mName;
    public EditText mAge;
    public Button mSaveBtn;
    public Button mDateBtn;

    public String mDateInformation;

    public String mCurrentdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission05);

        //변수 초기화
        mName = (EditText) findViewById(R.id.name_Et);
        mAge = (EditText) findViewById(R.id.age_Et);
        mDateBtn = (Button) findViewById(R.id.dateBtn);
        mSaveBtn = (Button) findViewById(R.id.saveBtn);

        //버튼들 클릭시 onClick 과 연결
        mDateBtn.setOnClickListener(this);
        mSaveBtn.setOnClickListener(this);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy 년 MM 월 dd 일");
        mCurrentdate = CurDateFormat.format(date);

        mDateBtn.setText(mCurrentdate);

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            mDateInformation = year + " 년 " + (monthOfYear + 1) + " 월 " + dayOfMonth + " 일";
            mDateBtn.setText(mDateInformation);


//            Toast.makeText(getApplicationContext(), year + "년" + (monthOfYear + 1) + "월" + dayOfMonth
//                    + "일", Toast.LENGTH_SHORT).show();


        }
    };


    @Override
    public void onClick(View v) {

        //고객 이름,나이 지역변수로 설정
        String customerName = mName.getText().toString();
        String customerAge = mAge.getText().toString();

        switch (v.getId()) {
            //날짜 버튼 클릭시 이벤트 처리
            case R.id.dateBtn:
                DatePickerDialog dialog = new DatePickerDialog(this, listener, 2013, 10, 22);
                dialog.show();
                break;

            //저장 버튼 클릭시 이벤트 처리
            case R.id.saveBtn:
                Toast.makeText(getApplicationContext(), "이름: " + customerName + "  나이: "
                        + customerAge + "  날짜: " + mDateInformation, Toast.LENGTH_SHORT).show();
                break;

        }


    }
}