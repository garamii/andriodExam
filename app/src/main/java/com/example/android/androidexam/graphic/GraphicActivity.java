
package com.example.android.androidexam.graphic;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
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
            default:
                return super.onOptionsItemSelected(item);
        }

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

        } else {
            Toast.makeText(GraphicActivity.this, "메모리를 사용 할수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
