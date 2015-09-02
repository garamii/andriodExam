
package com.example.android.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.androidexam.R;

public class mission01Activity extends AppCompatActivity {

    private Button mDownBtn;
    private Button mUpBtn;
    private ImageView mImageView1;
    private ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);

        mDownBtn = (Button) findViewById(R.id.downBtn);
        mUpBtn = (Button) findViewById(R.id.upBtn);
        mImageView1 = (ImageView) findViewById(R.id.topPlaceImage);
        mImageView2 = (ImageView) findViewById(R.id.downPlaceImage);

        mDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });

        mUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });

    }

    // // 버튼 클릭시 작동
    // public void onClick(View view) {
    // if (view.getId() == R.id.downBtn) {
    // changeImage();
    // } else if (view.getId() == R.id.upBtn) {
    // changeImage();
    // }
    // }

    public void changeImage() {

        // 상단 이미지 상태 변경
        if (mImageView1.getVisibility() == View.VISIBLE) {
            mImageView1.setVisibility(View.INVISIBLE);
        } else {
            mImageView1.setVisibility(View.VISIBLE);
        }

        // 하단 이미지 상태 변경
        if (mImageView2.getVisibility() == View.VISIBLE) {
            mImageView2.setVisibility(View.INVISIBLE);
        } else {
            mImageView2.setVisibility(View.VISIBLE);
        }
    }
}
