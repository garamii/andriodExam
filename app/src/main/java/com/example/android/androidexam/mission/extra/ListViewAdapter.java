package com.example.android.androidexam.mission.extra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.androidexam.R;

import java.util.List;

/**
 * Created by student on 2015-09-09.
 * ㅎㅎ
 */
public class ListViewAdapter extends BaseAdapter {

    public List<ListData> mData;
    private Context mContext;

    public ListViewAdapter(Context context,List<ListData> data){

        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itemlist,parent,false);
            ImageView image = (ImageView)convertView.findViewById(R.id.mImage);
            TextView title = (TextView)convertView.findViewById(R.id.mTitle);
            TextView contents = (TextView)convertView.findViewById(R.id.mContents);

            holder = new ViewHolder();
            holder.image = image;
            holder.title = title;
            holder.contents = contents;

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ListData data =(ListData) getItem(position);

        holder.image.setImageResource(data.getImageResourceID());
        holder.title.setText(data.getTitle());
        holder.contents.setText(data.getContents());


        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView title;
        TextView contents;
    }
}
