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
public interface Dao1 {


    @Query("SELECT * FROM Task as t")
   Maybe<List<Task>>  getAll();




    @Query("SELECT * FROM Task as t WHERE id =:newid")
    Maybe<List<Task>> getAllWithNId(Integer newid);

    @Query("UPDATE Task   SET task = :newtask WHERE id=:newid")
    Maybe<Integer>  updateNewTask(  String newtask,int newid);



    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);


 @RawQuery
 Maybe<List<Task> >getRawTwo(SupportSQLiteQuery query);

}
