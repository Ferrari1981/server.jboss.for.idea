package com.dsy.dsu.AllDatabases.ORMSugar;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

@Dao
public interface TaskDao3 {

    @RawQuery
    Maybe<List<Task> >getRaw(SupportSQLiteQuery query);





}