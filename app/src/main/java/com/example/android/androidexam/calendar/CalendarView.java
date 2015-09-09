package com.example.android.androidexam.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by student on 2015-09-08.
 *
 * 달력이다
 */
public class CalendarView extends GridView implements AdapterView.OnItemClickListener {

    // 코드 상에서 생성 될 때 호출 하는 생성자
    public CalendarView(Context context) {
        this(context, null);
    }
    // xml 에 정의 되었을 때 호출 되는 생성자
    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    // Widget 에서 호출 했을때 호출 되는 생성자
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        setNumColumns(7);       // 열 을 7로 설정
        setBackgroundResource(android.R.color.darker_gray); //배경을 회색으로
        setVerticalSpacing(1);
        setHorizontalSpacing(1);

        //아이템 클릭 이벤트
        setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(getAdapter() != null) {
            if(getAdapter() instanceof CalendarAdapter) {
                CalendarAdapter adapter = (CalendarAdapter) getAdapter();
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            }else {
               throw new IllegalStateException("CalendatAdapter 를 세팅 해야 합니다");
            }

        }
    }
}
