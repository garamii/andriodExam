package com.example.android.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.android.androidexam.R;

public class loginpage extends AppCompatActivity {

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



                Toast.makeText(getApplicationContext(), "고객 관리", Toast.LENGTH_SHORT).show();

               finish();
            }
        });
        findViewById(R.id.salesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"매출 관리",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        findViewById(R.id.productsBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"상품 관리",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }



}
