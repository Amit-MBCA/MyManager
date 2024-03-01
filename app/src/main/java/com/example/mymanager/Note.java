package com.example.mymanager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_tasks")
public class Note {
    private String time;
    private String title;
    private String desc;
    public Note(String time, String title, String desc) {
        this.time = time;
        this.title = title;
        this.desc = desc;
    }

//    private String desc;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @PrimaryKey(autoGenerate=true)
    private int id;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}
