
package com.example.android.androidexam.musicplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.androidexam.R;

/**
 * Created by student on 2015-10-01.
 */
public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_PICK_MUSIC = 1;
    private ImageView mImageView;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music);

        findViewById(R.id.btn_file_pick).setOnClickListener(this);
        findViewById(R.id.btn_play_pause).setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.iv_thumbnail);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_file_pick:
                pickFile();
                break;

            case R.id.btn_play_pause:
                if (mMediaPlayer != null) {
                    if (!mMediaPlayer.isPlaying()) {
                        mMediaPlayer.start(); // no need to call prepare();
                                              // create() does that for you
                        ((ImageButton) v).setImageResource(android.R.drawable.ic_media_pause);
                    } else {
                        mMediaPlayer.pause();
                        ((ImageButton) v).setImageResource(android.R.drawable.ic_media_play);
                    }
                }
                break;
        }
    }

    private void pickFile() {
        // 음악 파일을 선택 하고, 정보들을 화면에 표시

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intent, "음악파일선택"), REQUEST_PICK_MUSIC);
        }
        // 정보들을 화면에 표시
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 정보들을 화면에 표시
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICK_MUSIC) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(getApplicationContext(), data.getData());
            String album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String duration = retriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

            Toast.makeText(MusicActivity.this,
                    album + ", " + title + ", " + artist + ", " + duration, Toast.LENGTH_SHORT)
                    .show();

            byte albumImage[] = retriever.getEmbeddedPicture();
            if (null != albumImage) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(albumImage, 0, albumImage.length);
                mImageView.setImageBitmap(bitmap);
            }

        }

        mMediaPlayer = MediaPlayer.create(this, data.getData());
        mMediaPlayer.start();

    }
}
