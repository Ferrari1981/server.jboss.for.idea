package com.dsy.dsu.AllDatabases.ROOM;


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
public interface DaoROOM {


    @Query("SELECT * FROM EntityMaterialBinary as t")
   Maybe<List<EntityMaterialBinary>>  getAll();




    @Query("SELECT * FROM EntityMaterialBinary as t WHERE id =:newid")
    Maybe<List<EntityMaterialBinary>> getAllWithNId(Integer newid);

    @Query("UPDATE EntityMaterialBinary   SET task = :newtask WHERE id=:newid")
    Maybe<Integer>  updateNewTask(  String newtask,int newid);



    @Insert
    void insert(EntityMaterialBinary entityMaterialBinary);

    @Delete
    void delete(EntityMaterialBinary entityMaterialBinary);

    @Update
    void update(EntityMaterialBinary entityMaterialBinary);


 @RawQuery
 Maybe<List<EntityMaterialBinary> >getRaw(SupportSQLiteQuery query);

 @RawQuery
 Maybe<List<EntityMaterialBinary> >getRawTwo(SupportSQLiteQuery query);

}
