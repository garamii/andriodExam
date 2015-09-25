
package com.example.android.androidexam.provider;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.androidexam.R;

/**
 * Created by student on 2015-09-25.
 */
public class LoadPictureFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView mListView;
    private LoadPictureAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_load_picture, container, false);

        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new LoadPictureAdapter(getActivity(),null,true);
        mListView.setAdapter(mAdapter);
        // 동기방식(앱이 느려지는것처럼 보이게할수있다)
        // getActivity().getContentResolver().query()

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(0,null,this);
    }

    // CursorLoader 를 생성
    //background Thread 에서 동작
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    //oncreateLoader 에서 작업이 끝난후에 호출 됨
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);


    }

    // 데이타를 리셋 처리
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
