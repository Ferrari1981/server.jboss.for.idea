package com.dsy.dsu.AllDatabases.ROOM;


import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Blob;

@Entity(tableName = "materials_databinary",  indices = {
        @Index(value = "uuid",unique = true),
        @Index(value = "current_table", unique = true)}
)
public class EntityMaterialBinary implements Serializable {

    @PrimaryKey(autoGenerate = false)
    private Integer _id;

    @ColumnInfo(name = "image")
    private byte[] image;

    @ColumnInfo(name = "files")
    private  byte[] files;

    @ColumnInfo(name = "uuid")
    private Long uuid;

    @ColumnInfo(name = "parent_uuid")
    private Long parent_uuid;

    @ColumnInfo(name = "user_update")
    private Integer user_update;

    @ColumnInfo(name = "current_table")
    private Long current_table;


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public  byte[] getImage() {

        return image;
    }

    public void setImage( byte[] image) {
        this.image = image;
    }

    public  byte[] getFiles() {

        return files;
    }

    public void setFiles( byte[] files) {

        this.files = files;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getParent_uuid() {
        return parent_uuid;
    }

    public void setParent_uuid(Long parent_uuid) {
        this.parent_uuid = parent_uuid;
    }

    public Integer getUser_update() {
        return user_update;
    }

    public void setUser_update(Integer user_update) {
        this.user_update = user_update;
    }

    public Long getCurrent_table() {
        return current_table;
    }

    public void setCurrent_table(Long current_table) {
        this.current_table = current_table;
    }
}