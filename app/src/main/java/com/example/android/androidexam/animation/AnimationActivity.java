
package com.example.android.androidexam.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.androidexam.R;

/**
 * Created by student on 2015-09-08.
 *달력
 */
public class AnimationActivity extends Activity implements View.OnClickListener {

    private Animation mScaleAnrotateAnimation;
    private ImageView mImageView;
    private Button mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);

        mImageView = (ImageView) findViewById(R.id.aniImage);
        mBtnStart = (Button) findViewById(R.id.btnStart);

        mBtnStart.setOnClickListener(this);

        mScaleAnrotateAnimation = AnimationUtils.loadAnimation
                (AnimationActivity.this, R.anim.anim_scale_rotate);

        mScaleAnrotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mImageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        mImageView.startAnimation(mScaleAnrotateAnimation);

    }
}
