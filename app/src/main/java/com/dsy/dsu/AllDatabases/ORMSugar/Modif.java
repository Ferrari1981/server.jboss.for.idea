package com.dsy.dsu.AllDatabases.ORMSugar;


import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Modif implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task")
    private String task;

    @ColumnInfo(name = "nameyask")
    private String nameyask;

    @ColumnInfo(name = "finish_by")
    private String finishBy;

    @ColumnInfo(name = "finished")
    private boolean finished;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getNameyask() {
        return nameyask;
    }

    public void setNameyask(String nameyask) {
        this.nameyask = nameyask;
    }

    public String getFinishBy() {
        return finishBy;
    }

    public void setFinishBy(String finishBy) {
        this.finishBy = finishBy;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}