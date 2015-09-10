
package com.example.android.androidexam.calendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.android.androidexam.R;
import com.example.android.androidexam.calendar.adapter.CalendarAdapter;
import com.example.android.androidexam.calendar.adapter.TodoAdapter;
import com.example.android.androidexam.calendar.model.Schedule;
import com.example.android.androidexam.calendar.view.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;
    private TextView mTitleTextView;
    private ListView mTodoListView;
    private TodoAdapter mTodoAdapter;

    //모든 일정이 답긴 맵
    private Map<Calendar, List<Schedule>> mScheduleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mScheduleMap = new HashMap<>();

        mTitleTextView = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.prevMonthBtn).setOnClickListener(this);
        findViewById(R.id.nextMonthBtn).setOnClickListener(this);

        mTodoListView = (ListView) findViewById(R.id.lv_todo);

        // 어댑터 준비
        // View 에 어댑터를 설정
        mCalendarAdapter = new CalendarAdapter(this);
        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mCalendarView.setAdapter(mCalendarAdapter);

        //테스트
//        List<Schedule> test = new ArrayList<>();
//        test.add(new Schedule(5, 30, "기상"));
//        test.add(new Schedule(7, 30, "기상"));
//        test.add(new Schedule(9, 30, "기상"));
//        mTodoAdapter = new TodoAdapter(this, test);
//        mTodoListView.setAdapter(mTodoAdapter);

        // 아이템 클릭 이벤트 연결
        mCalendarView.setOnItemClickListener(this);
        mCalendarView.setOnItemLongClickListener(this);

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
        mTitleTextView.setText(year + "년" + month + "월");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //선택된 것으로 함
        mCalendarAdapter.setSelectedPosition(position);
        mCalendarAdapter.notifyDataSetChanged();

      Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);
        List<Schedule> list = mScheduleMap.get(calendar);

        if (list == null) {
            list = Collections.<Schedule>emptyList();
        }

        //어댑터에 표시할 스케쥴 리스트를 전달
        mTodoAdapter = new TodoAdapter(CalendarActivity.this,list);
        //리스트뷰에 어댑터를 연결
        mTodoListView.setAdapter(mTodoAdapter);



    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View layout = getLayoutInflater().inflate(R.layout.dialog_schedule, null);
        final TimePicker timePicker = (TimePicker) layout.findViewById(R.id.picker_time);
        final EditText editText = (EditText) layout.findViewById(R.id.et_schedule);

        // 롱 클릭 한 곳의 Calendar 객체를 얻음
        final Calendar calendar =(Calendar) mCalendarAdapter.getItem(position);
      //  Toast.makeText(CalendarActivity.this,calendar.toString(), Toast.LENGTH_SHORT).show();

        builder.setNegativeButton("닫기", null);
        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //뭔가 합니다


                //getCurrentHour
                Schedule schedule = new Schedule(timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(),
                        editText.getText().toString());
                //저장

                //현재 날장 연결 된 일정 리스트를 얻음
                List<Schedule> list = mScheduleMap.get(calendar);

                //만약 리스트가 없으면 초기화
                if(list == null){
                    list = new ArrayList<>();
                }
                //지금 추가할 스케쥴을 추가
                list.add(schedule);
                // 전체 일정에 오늘 날짜와 스케쥴을 연결하여 추가
                mScheduleMap.put(calendar, list);

                //어댑터에 표시할 스케쥴 리스트를 전달
                mTodoAdapter = new TodoAdapter(CalendarActivity.this, list);
                //리스트 뷰에 어댑터를 연결
            mTodoListView.setAdapter(mTodoAdapter);
              //  Toast.makeText(CalendarActivity.this, schedule.toString(), Toast.LENGTH_SHORT).show();


            }
        });

        // LayoutInflater.from(this).inflate(R.layout.item_calendar,parent,false);
        builder.setView(layout);
        builder.show();

        return true;
    }
}
