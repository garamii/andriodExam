package com.example.android.androidexam.thread;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.androidexam.R;


public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mThreadBtn1;
    private Button mThreadBtn2;
    private TextView mNumberTextView1;
    private TextView mNumberTextView2;

    private static final String TAG = ThreadActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mThreadBtn1 = (Button) findViewById(R.id.btn_thread1);
        mThreadBtn2 = (Button) findViewById(R.id.btn_thread2);
        mNumberTextView1 = (TextView) findViewById(R.id.tv_number1);
        mNumberTextView2 = (TextView) findViewById(R.id.tv_number2);

        mThreadBtn1.setOnClickListener(this);
        mThreadBtn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thread1:
                progressDialogExam();
                break;
            case R.id.btn_thread2:
                break;
        }

    }

    private void progressDialogExam() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("다운로드중");
        progressDialog.setCancelable(false);//뒤로가기로 캘슬 되는것 막기
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                //2초동안 다운로드
                for(int i =0; i<10;i++){
                    try {
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                //다운로드 끝나면 progreessDialog를 닫는다
                progressDialog.dismiss();


            }
        }).start();
    }

    //백그라운드 처리는 되지만 UI변경은 안되고 스레드 종료시 마지막 결과만 보여진다
    private void runOnUiThreadExam() {
        //UI thread( 로 동작하게 해주는 Activity 제공 메소드
        //백그라운드로 작업하며 마지막 결과만 보여줌
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다 1000 = 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }
                    Log.d(TAG, "" + i);               //background
                    mNumberTextView1.setText("" + i); //foreground
                }


            }
        });
    }


    private void threadAndHandler() {
        // Handler 클래스 상속을 생략한 것

        //보이는 부분에서 동작 하는 Thread
        //UI Thread
        //Foreground Thread
        //Main Thread
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mNumberTextView1.setText("" + msg.arg1);
            }
        };

        // Thread 클래스 상속을 생략 한것

        //안보이는 부분에서 동작하는 Thread
        //thread
        //background Thread
        //worker Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //스레드로 동작하는 부분
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다 1000 = 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }
                    Message msg = new Message();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                }
            }
        });
        thread.start();
    }

    //스레드 사용방법 1
    //background에서 동작

    private void thread1() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //스레드로 동작하는 부분
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다 1000 = 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }
                    mNumberTextView1.setText("" + i);
                }
            }
        });
        thread.start();
    }
}
