
package com.example.android.androidexam;

/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.android.androidexam.Activity.ActivityExamActivity;
import com.example.android.androidexam.Activity.ParcelableActivity;
import com.example.android.androidexam.Activity.WebActivity;
import com.example.android.androidexam.animation.AnimationActivity;
import com.example.android.androidexam.calendar.CalendarActivity;
import com.example.android.androidexam.calendar2.Calendar2Activity;
import com.example.android.androidexam.database.LogInActivity;
import com.example.android.androidexam.database.parse.ParseLocalDatabaseActivity;
import com.example.android.androidexam.database.parse.ParseLogInActivity;
import com.example.android.androidexam.exam.ListExamActivity;
import com.example.android.androidexam.fragment.FragmentActivity;
import com.example.android.androidexam.graphic.GraphicActivity;
import com.example.android.androidexam.layout.FrameLayoutActivity;
import com.example.android.androidexam.mission.Mission02Activity;
import com.example.android.androidexam.mission.Mission05Activity;
import com.example.android.androidexam.mission.extra.ListView_Activity;
import com.example.android.androidexam.mission.mission01Activity;
import com.example.android.androidexam.mission.mission03Activity;
import com.example.android.androidexam.musicplayer.MusicActivity;
import com.example.android.androidexam.parsing.json.WeatherActivity;
import com.example.android.androidexam.provider.ContactLoaderActivity;
import com.example.android.androidexam.provider.LoadPictureActivity;
import com.example.android.androidexam.thread.ThreadActivity;
import com.example.android.androidexam.viewpager.ScreenSlideActivity;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[] {
                        "title"
                },
                new int[] {
                        android.R.id.text1
                }));
        getListView().setTextFilterEnabled(true);
    }

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();

        // 메뉴 추가 부분
   //      addItem(myData, "TransitionDrawable", TransitionDrawableExamActivity.class);
         addItem(myData, "FrameLayout", FrameLayoutActivity.class);
         addItem(myData, "미션 01", mission01Activity.class);
         addItem(myData, "미션 02", Mission02Activity.class);
         addItem(myData, "미션 03", mission03Activity.class);
         addItem(myData, "화면이동예제", ActivityExamActivity.class);
         addItem(myData, "웹뷰 예제", WebActivity.class);
         addItem(myData, "미션 05", Mission05Activity.class);
         addItem(myData, "animation 연습", AnimationActivity.class);
         addItem(myData, "달력 연습", CalendarActivity.class);
         addItem(myData, "달력(android)", Calendar2Activity.class);
         addItem(myData, "ListView",ListView_Activity.class);
         addItem(myData, "ListView연습",ListExamActivity.class);
         addItem(myData, "Thread 연습",ThreadActivity.class);
         addItem(myData, "parsing 연습",WeatherActivity.class);
         addItem(myData, "fragment 연습",FragmentActivity.class);
         addItem(myData, "Viewpager",ScreenSlideActivity.class);
         addItem(myData, "Graphic",GraphicActivity.class);
         addItem(myData, "Database",LogInActivity.class);
         addItem(myData, "parcelable",ParcelableActivity.class);
         addItem(myData, "Database-parse",ParseLogInActivity.class);
         addItem(myData, "parse 로컬 db",ParseLocalDatabaseActivity.class);
         addItem(myData, "Content provider,Loader - 연락처",ContactLoaderActivity.class);
         addItem(myData, "Content provider Loader - 사진",LoadPictureActivity.class);
        addItem(myData,"music Player", MusicActivity.class);
        // ----- 메뉴 추가 여기까지

        // 이름 순 정렬
     //   Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    protected void addItem(List<Map<String, Object>> data, String name, Class cls) {
        this.addItem(data, name, new Intent(this, cls));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
