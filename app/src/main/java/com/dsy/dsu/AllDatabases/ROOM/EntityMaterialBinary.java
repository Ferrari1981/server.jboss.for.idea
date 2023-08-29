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
    private Blob image;

    @ColumnInfo(name = "files")
    private Blob files;

    @ColumnInfo(name = "uuid")
    private BigInteger uuid;

    @ColumnInfo(name = "parent_uuid")
    private BigInteger parent_uuid;

    @ColumnInfo(name = "user_update")
    private Integer user_update;

    @ColumnInfo(name = "current_table")
    private BigInteger current_table;


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Blob getFiles() {
        return files;
    }

    public void setFiles(Blob files) {
        this.files = files;
    }

    public BigInteger getUuid() {
        return uuid;
    }

    public void setUuid(BigInteger uuid) {
        this.uuid = uuid;
    }

    public BigInteger getParent_uuid() {
        return parent_uuid;
    }

    public void setParent_uuid(BigInteger parent_uuid) {
        this.parent_uuid = parent_uuid;
    }

    public Integer getUser_update() {
        return user_update;
    }

    public void setUser_update(Integer user_update) {
        this.user_update = user_update;
    }

    public BigInteger getCurrent_table() {
        return current_table;
    }

    public void setCurrent_table(BigInteger current_table) {
        this.current_table = current_table;
    }
}