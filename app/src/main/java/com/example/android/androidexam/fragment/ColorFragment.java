package com.example.android.androidexam.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.androidexam.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 *
 * 생성시 랜덤한 색깔이 적용되는 fragment
 */
public class ColorFragment extends Fragment {

    private ImageView mImageView;

    private List<String> list;
    public ColorFragment() {
    }

    // View 를 만드는 곳
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_color, container, false);

        mImageView = (ImageView)view.findViewById(R.id.iv_image);

        return view;
    }


    //
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        list = new ArrayList<>();
        mImageView.setBackgroundColor(getRandomColor());

    }

    public void setColor(int color){
        mImageView.setBackgroundColor(color);
    }

    private  int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
