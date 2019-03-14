package com.example.rarct.gasprices.DAO;

import android.icu.text.Replaceable;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface myDao {

    @Insert (onConflict = REPLACE)
    void insertCommunity(CommunitiesEntity community);

    @Insert (onConflict = REPLACE)
    void insertProvince(ProvincesEntity province);

    @Insert
    void insertTown(TownsEntity town);


    @Query("SELECT * FROM Community ORDER BY Community_name")
    List<CommunitiesEntity> getCommunitiesEntityList();

    @Query("SELECT * FROM Provinces WHERE Communities_ID  == :community_id ORDER BY Province_name")
    List<ProvincesEntity> getProvincesEntityList(int community_id);

    @Query("SELECT * FROM Towns WHERE Province_ID == :provinces_id ORDER BY Town_name")
    List<TownsEntity> getTownsEntityList(int provinces_id);
}
