
package com.example.android.androidexam.calendar.model;

/**
 * Created by student on 2015-09-10. 일정
 * ㅎㅎ
 */
public class Schedule {


    /**
     * 시간
     */
   private int hour;

    /**
     * 분
     */
   private int minute;

    /**
     * 내용
     */
   private String contents;

    public Schedule(int hour, int minute, String contents) {
        this.hour = hour;
        this.minute = minute;
        this.contents = contents;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;   
    }

    @Override
    public String toString() {
        return hour + ":"+ minute + " " + contents;
    }
}
