
package com.example.android.androidexam.thread;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.androidexam.R;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mThreadBtn1;
    private Button mThreadBtn2;
    private TextView mNumberTextView1;
    private TextView mNumberTextView2;
    private ProgressBar mProgressBar;

    private static final String TAG = ThreadActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        //프로그레스바
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        //버튼
        mThreadBtn1 = (Button) findViewById(R.id.btn_thread1);
        mThreadBtn2 = (Button) findViewById(R.id.btn_thread2);

        //텍스트
        mNumberTextView1 = (TextView) findViewById(R.id.tv_number1);
        mNumberTextView2 = (TextView) findViewById(R.id.tv_number2);

        //버튼 온클릭 연결
        mThreadBtn1.setOnClickListener(this);
        mThreadBtn2.setOnClickListener(this);

    }
    //클릭이벤트


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thread1:
                progressDialogExam();
                break;
            case R.id.btn_thread2:
                new DownloadTask().execute();
                break;
        }

    }

    private void progressDialogExam() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("다운로드중");
        progressDialog.setCancelable(false);// 뒤로가기로 캘슬 되는것 막기
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                // 2초동안 다운로드
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 다운로드 끝나면 progreessDialog를 닫는다
                progressDialog.dismiss();

            }
        }).start();
    }

    // 백그라운드 처리는 되지만 UI변경은 안되고 스레드 종료시 마지막 결과만 보여진다
    private void runOnUiThreadExam() {
        // UI thread( 로 동작하게 해주는 Activity 제공 메소드
        // 백그라운드로 작업하며 마지막 결과만 보여줌
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
                    Log.d(TAG, "" + i); // background
                    mNumberTextView1.setText("" + i); // foreground
                }

            }
        });
    }

    private void threadAndHandler() {
        // Handler 클래스 상속을 생략한 것

        // 보이는 부분에서 동작 하는 Thread
        // UI Thread
        // Foreground Thread
        // Main Thread
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mNumberTextView1.setText("" + msg.arg1);
            }
        };

        // Thread 클래스 상속을 생략 한것

        // 안보이는 부분에서 동작하는 Thread
        // thread
        // background Thread
        // worker Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작하는 부분
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

    // 스레드 사용방법 1
    // background에서 동작

    private void thread1() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작하는 부분
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

    private class DownloadTask extends AsyncTask<Void, Integer, Void> {
        private AlertDialog.Builder mmBuilder;

        // UI Thread
        // doInBackground 전에 호출됨
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mmBuilder = new AlertDialog.Builder(ThreadActivity.this);
            mmBuilder.setMessage("다운로드가 완료 되었습니다");
            mmBuilder.setNegativeButton("닫기",null);

            mProgressBar.setProgress(0);
        }

        // background 스레드

        @Override
        protected Void doInBackground(Void... params) {

            //다운로드 처리
            for (int i = 0; i< 100; i++){
                //0.2초 쉬고
                try {
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                    Log.e(TAG,e.getMessage());
                }

                // onProgressUpdate 를 호출
               publishProgress(i+1);
            }
            return null;
        }

        // UI Tread
        // doInBackground 에서 publishProgress 로 호출 할 수 있음
        // 직접 호출 하지 않는 이유 : 죽으니까
        // (Integer... values)  ... = 배열[] 을 넘길수있고 배열이 아닌것도 넘길수있음
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mProgressBar.setProgress(values[0]);
            mNumberTextView2.setText(values[0] + " %" );
        }

        // UI Thread
        // doInBackground 가 수행 된 후에 호출됨
        // doIn Background 에서 return 된 값이 파라메터로 넘어옴
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mmBuilder.show();
        }
    }
}
