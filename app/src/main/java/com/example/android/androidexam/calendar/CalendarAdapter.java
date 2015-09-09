
package com.example.android.androidexam.calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.androidexam.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by student on 2015-09-08. 달력 표시용 어뎁터
 */
public class CalendarAdapter extends BaseAdapter {

    private List<Calendar> mList;
    private Context mContext;
    private Calendar mCalendar;

        private int mSelectedPosition = -1;

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int mSelectedPosition) {
        this.mSelectedPosition = mSelectedPosition;
    }

    public CalendarAdapter(Context context) {
        mContext = context;

        // 오늘
        mCalendar = GregorianCalendar.getInstance();
        createCalendar(mCalendar);
    }

    private void createCalendar(Calendar calendar) {
            mList = new ArrayList<>();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        // 마지막 날
        int lastDay = calendar.getActualMaximum(Calendar.DATE);

        // 이달의 첫 번째 날이 몇번째 부터 시작하는지
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);

        // 공백
        for (int i = 1; i < firstDay; i++) {
            mList.add(null);
        }

        // 이번달 달력 에디터
        for (int i = 1; i <= lastDay; i++) {

            mList.add(new GregorianCalendar(year, month, i));
        }
    }

    public void prevMonth() {

        changeMonth(-1);
    }

    public void nextMonth() {

        changeMonth(1);

    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    private void changeMonth(int month) {

        //아이템 선택을 안한 상태로
        mSelectedPosition = -1;
        // 다음달로 설정
        mCalendar.add(Calendar.MONTH, month);
        createCalendar(mCalendar);


        // 어댑터에 바뀐 데이터를 반영하도록 알려 줌
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 아이템이 화면에 보일때 호출 됨
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        // Layout 을 완성하고 ViewHoler 와 연결
        // 이유 : findViewById를 한번만 하려고

        if (convertView == null) {
            holder = new ViewHolder();

            // 처음 로드
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, parent,false);

            holder.dateTextView = (TextView) convertView.findViewById(R.id.tv_date);

            convertView.setTag(holder);

        } else {
            // 재사용
            holder = (ViewHolder)convertView.getTag();
        }
        // Data 를 Layout 에 설정

        Calendar calendar = mList.get(position);
        if (calendar != null) {
            //날짜 빼오기

            holder.dateTextView.setText("" + calendar.get(Calendar.DATE));

            if(position %7 == 0){
                holder.dateTextView.setTextColor(Color.RED);
            }else if((position+1) %7 == 0) {
                holder.dateTextView.setTextColor(Color.BLUE);
            }else{
                holder.dateTextView.setTextColor(Color.BLACK);
            }
        } else{
           holder.dateTextView.setText("");
        }
        //선택 된 셀의 배경 색상 변경
        if(position == mSelectedPosition){
            convertView.setBackgroundColor(Color.YELLOW);
        }else{
            convertView.setBackgroundColor(Color.WHITE);
        }
        
        return convertView;
    }

    static class ViewHolder {
        TextView dateTextView;

    }
}
