
package com.example.android.androidexam.graphic;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.androidexam.R;
import com.example.android.androidexam.utils.storage.StorageUtill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by student on 2015-09-17.
 */
public class GraphicActivity extends AppCompatActivity {
    ShapeView mShapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mShapeView = new ShapeView(this);
        setContentView(mShapeView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                return true;
            case R.id.change_color:
                openDialog();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void changeColor() {

    }

    private void openDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(GraphicActivity.this);
        builder.setTitle("타이틀");
        builder.setMessage("메세지");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(GraphicActivity.this, "확인 눌렸어요", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("닫기", null);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();       // AlertDialog 를 최종 생성
    }


    private void save() {
        Toast.makeText(GraphicActivity.this, "save", Toast.LENGTH_SHORT).show();

        mShapeView.setDrawingCacheEnabled(true);
        Bitmap bitmap =Bitmap.createBitmap(mShapeView.getDrawingCache());
        mShapeView.setDrawingCacheEnabled(false);

        // 외부 저장소에 접근이 가능 하면, 파일 생성
        if (StorageUtill.isExternalStorageWritable()) {

            File file = new File(

                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                    "pictureTest.jpg"
                    );


            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
                Toast.makeText(GraphicActivity.this, "저장 되었습니다.", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }
            sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("fill://" +file.toString())
            ));


        } else {
            Toast.makeText(GraphicActivity.this, "메모리를 사용 할수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
