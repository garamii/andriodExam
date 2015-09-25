
package com.example.android.androidexam.provider;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.androidexam.R;

/**
 * Created by student on 2015-09-25.
 */
public class LoadPictureAdapter extends CursorAdapter {

    private final LayoutInflater mLayoutInflater;

    public LoadPictureAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);

        mLayoutInflater = LayoutInflater.from(context);
    }

    // 맨 처음 레이아웃을 만드는 부분
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();

        View view = mLayoutInflater.inflate(R.layout.item_picture, parent, false);

        holder.imageView = (ImageView) view.findViewById(R.id.imageView);
        view.setTag(holder);

        return view;
    }

    // 데이터를 세팅 하는 부분
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        //id 가져오가
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));

//        Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media)

        //bitmap 샘플링
        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inSampleSize = 2;

        // id 로부터 bitmap 생성
        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(),
                id,
                MediaStore.Images.Thumbnails.MINI_KIND,
               options );

        holder.imageView.setImageBitmap(bitmap);

    }

    static class ViewHolder {
        ImageView imageView;
    }
}
