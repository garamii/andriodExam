
package com.example.android.androidexam.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.androidexam.R;
import com.example.android.androidexam.calendar.model.Schedule;

import java.util.List;

/**
 * Created by student on 2015-09-08. 달력 표시용 어뎁터
 */
public class TodoAdapter extends BaseAdapter {

    private List<Schedule> mList;
    private Context mContext;

    public TodoAdapter(Context context, List<Schedule> data) {
        mContext = context;
        mList = data;

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_todo, parent, false);

            holder.todoTextView = (TextView) convertView.findViewById(R.id.tv_todo);

            convertView.setTag(holder);

        } else {
            // 재사용
            holder = (ViewHolder) convertView.getTag();
        }
        // Data 를 Layout 에 설정

        Schedule schedule = mList.get(position);


        holder.todoTextView.setText(schedule.toString());

        return convertView;
    }

    static class ViewHolder {
        TextView todoTextView;

    }
}
