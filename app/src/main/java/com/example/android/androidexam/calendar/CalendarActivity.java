
package com.example.android.androidexam.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.android.androidexam.R;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;
    private TextView mTitleTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


       mTitleTextView =(TextView) findViewById(R.id.tv_title);
        findViewById(R.id.prevMonthBtn).setOnClickListener(this);
        findViewById(R.id.nextMonthBtn).setOnClickListener(this);

        // 어댑터 준비
        mCalendarAdapter = new CalendarAdapter(this);

        // View 에 어댑터를 설정
        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mCalendarView.setAdapter(mCalendarAdapter);

        // 이벤트 리스너 연결
        mCalendarView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.prevMonthBtn:
                mCalendarAdapter.prevMonth();
                break;
            case R.id.nextMonthBtn:
                mCalendarAdapter.nextMonth();
                break;
            default:
                break;
        }
        updateTitle();
    }

    private void updateTitle() {
        int year = mCalendarAdapter.getCalendar().get(Calendar.YEAR);
        int month = mCalendarAdapter.getCalendar().get(Calendar.MONTH) + 1;
        mTitleTextView.setText(year + "년" + month+ "월" );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCalendarAdapter.setSelectedPosition(position);
        //다시 그려달라고 명령
        mCalendarAdapter.notifyDataSetChanged();
    }
}
