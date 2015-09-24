
package com.example.android.androidexam.calendar.database.contract;

import android.provider.BaseColumns;

/**
 * Created by junsuk on 15. 9. 18..
 */
public final class ScheduleContract {

public static final String[] PROJECTION_ALL = new String[]{
        //컬럼명 정의
    ScheduleEntry._ID,
    ScheduleEntry.COLUMN_NAME_DATE,
    ScheduleEntry.COLUMN_NAME_HOUR,
    ScheduleEntry.COLUMN_NAME_MINUTE,
    ScheduleEntry.COLUMN_NAME_CONTENTS
};
    public ScheduleContract() {
    }
    public static abstract class ScheduleEntry implements BaseColumns {
        public static final String TABLE_NAME = "Schedule";
        public static final String COLUMN_NAME_HOUR = "hour";
        public static final String COLUMN_NAME_MINUTE = "minute";
        public static final String COLUMN_NAME_CONTENTS = "contract";
        public static final String COLUMN_NAME_DATE = "DATE";
    }
}
