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
public interface DaoRoom {

    // TODO: 29.08.2023  запросы для всех таблиц GET ALL
    @Query("SELECT * FROM MODIFITATION_Client_ROOM as mod")
    Maybe<List<EntityMaterialBinary>> getAllMod();

    @Query("SELECT * FROM MODIFITATION_Client_ROOM as mamtbin")
    Maybe<List<Entitymodifversions>> getAllMatrBi();


    @RawQuery
    Maybe<List<Entitymodifversions> >getRawMod(SupportSQLiteQuery query);

    @RawQuery
    Maybe<List<EntityMaterialBinary> >getRawMatbin(SupportSQLiteQuery query);


    // TODO: 29.08.2023  вставка данных и обновленние   Binary

    @Insert
    void insert(EntityMaterialBinary entityMaterialBinary);

    @Delete
    void delete(EntityMaterialBinary entityMaterialBinary);

    @Update
    void update(EntityMaterialBinary entityMaterialBinary);

    // TODO: 29.08.2023  вставка данных и обновленние  для всех таблиц Version

    @Insert
    void insert(Entitymodifversions entitymodifversions);

    @Delete
    void delete(Entitymodifversions entitymodifversions);

    @Update
    void update(Entitymodifversions entitymodifversions);

}
