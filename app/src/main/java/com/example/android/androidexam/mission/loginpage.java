package com.example.android.androidexam.mission;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.androidexam.R;

public class loginpage extends AppCompatActivity {

    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

//    public void onButton(View v){
//        AlertDialog dialog = createDialogBox();
//        dialog.show();
//    }
//
//    private AlertDialog createDialogBox() {
//
//        AlertDialog.Builder buirder = new AlertDialog.Builder(getApplicationContext());
//        buirder.setTitle("안내");
//        buirder.setMessage("종료하시겠습니까?");
//
//        buirder.setPositiveButton(R.id.cstmrBtn, DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface , int whichButton){
//
//            }
//        });
//
//        return null;
//    }




    findViewById(R.id.cstmrBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = ((TextView) findViewById(R.id.cstmrBtn)).getText().toString();
                openDialog();

            }
        });
        findViewById(R.id.salesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = ((TextView) findViewById(R.id.salesBtn)).getText().toString();
                openDialog();
            }
        });
        findViewById(R.id.productsBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = ((TextView) findViewById(R.id.productsBtn)).getText().toString();
                openDialog();
            }
        });

    }

    private void openDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(loginpage.this);
        builder.setTitle(title);
        builder.setMessage("메세지");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(loginpage.this, "확인 눌렸어요", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("닫기", null);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();       // AlertDialog 를 최종 생성
    }


}
