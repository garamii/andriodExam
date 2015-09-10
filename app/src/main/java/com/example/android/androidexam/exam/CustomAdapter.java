
package com.example.android.androidexam.exam;

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
 * Created by student on 2015-09-10.
 * ㅎㅎ
 */

// 커스텁 어댑터 작성
// 기본 baseAdapter를 상속 받아서 커스텀 할것이기 때문에
// BaseAdapter를 (Extends)상속받아서 내맘대로 어뎁터를 개조한다
public class CustomAdapter extends BaseAdapter {

    // BaseAdapter를 사용하기 위해선 4가지 메소드가 기본적으로 필요해
    // BaseAdapter를 알트 엔터 눌러서 메소드를 import한다

    //
    // costionAdapter를 사용하기 위해서 레이아웃을 가져올기위해 생성자를 생성한다
    // [생성자 = CustomAdapter() 이고 ()를 구현해야한다 ]
    // 레이아웃을 가져오기 위핸선 context 가 필요하니까
    // context를 만들고 데이터 들을 처리할 배열 <List> 을 만든다
    // customAdapter는 데이터에 접근하기위한 context와 데이터를 처리하기위한 data를 받는게 만든다

    private List<ItemExam> mData;

    private Context mContext;

    public CustomAdapter(Context context, List<ItemExam> data) {

        mContext = context;
        mData = data;
    }

    // 데이터에 맞도록 오버라이드 된 메소드들을 고친다
    @Override
    // 데이터의 갯수
    public int getCount() {

        // 오버라이드 return 0;
        // mData에 있는 데이터의 갯수를 구한다
        return mData.size();
    }

    @Override
    // position 번째의 아이템
    public Object getItem(int position) {

       //오버라이드 return null;
        //mData에 있는 데이터의 있는 위치의 아이템을 가져온다
        return mData.get(position);
    }

    @Override
    // 각 item의 id
    public long getItemId(int position) {
       //오버라이드 return 0;
        //item의 ID = 몇번째 위치의 아이템인지 판별

        return position;
    }

    @Override
    // 각 position 번째의 item 을 보여줄 View
    // viewHolder 패턴을 이용해서 View가 한번
    // 로드 되었을때 재사용할수있게 코드를 짠다
    public View getView(int position, View convertView, ViewGroup parent) {
        //VIewHolder를 만든다
        //ViewHolder가 없으니 사용하가 위해서
        //inerClass로 구현

        ViewHolder holder;


        //데이터에 접근하기위해서 컨텍스트를 전역변수로 생성
        if(convertView ==null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            //
            ImageView image = (ImageView) convertView.findViewById(R.id.mImage);
            TextView title = (TextView) convertView.findViewById(R.id.mTitle);
            TextView contents = (TextView) convertView.findViewById(R.id.mContents);

            //처음 불러왔으면 다시 로딩하지않기위해 홀더에 값을 저장한다
            //holder를 사용하기 위해서 아래쪽에있는 홀더를 구현해야하는데
            //private class로 정의되는데 사용하기위해서 static으로 변경
            //image,title,contents 를 저장하기위해서
            //이 항목들의 형식을 정해주어야 한다

            holder = new ViewHolder();
            holder.image = image;
            holder.title = title;
            holder.contents = contents;

            //위의 정보를 가진 tag들을 생성해서 홀더에 넣어둔다

            convertView.setTag(holder);
        }else{

            holder = (ViewHolder) convertView.getTag();
        }

        //View에 표시할 데이터를 얻어온다
        ItemExam itemExam = (ItemExam) getItem(position);

        holder.image.setImageResource(itemExam.getImageRecourceId());
        holder.title.setText(itemExam.getTitle());
        holder.contents.setText(itemExam.getContents());


        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView title;
        TextView contents;

    }
}
