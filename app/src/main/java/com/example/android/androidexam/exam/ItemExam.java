package com.example.android.androidexam.exam;

/**
 * Created by student on 2015-09-10.
 * ㅎㅎ
 */
public class ItemExam {

    //item에 들어가는 항목들 설정

    private int imageRecourceId; //사진
    private String title;       //제목
    private String contents;    //내용
    //그후 이 정보들 사용할수있게
    //Getter 와 Setter를 Generate(발생시키다) 한다
    //그후에 커스텀 어댑터 작성

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getImageRecourceId() {
        return imageRecourceId;
    }

    public void setImageRecourceId(int imageRecourceId) {
        this.imageRecourceId = imageRecourceId;
    }
}
