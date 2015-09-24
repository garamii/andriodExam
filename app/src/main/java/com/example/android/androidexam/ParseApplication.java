
package com.example.android.androidexam;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by student on 2015-09-24.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "HqZaQmP7fbchNwD3Sto4FeTdBRpXjqxrZkzbz2HR",
                "LizxWoSjJLm3bKiU3IDd1MAXjOF51L88ZnD3L9gX");
    }
}
