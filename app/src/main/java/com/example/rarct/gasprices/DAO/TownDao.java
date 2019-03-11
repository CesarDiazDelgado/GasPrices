package com.example.rarct.gasprices.DAO;

import com.example.rarct.gasprices.Databases.TownsEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TownDao {

    @Insert
    void insertTown(TownsEntity town);

    @Query("SELECT * FROM Towns LEFT JOIN Provinces ON Province_ID = provinces.ID ORDER BY Town_name")
    List<TownsEntity> getTownsEntityList();
}
