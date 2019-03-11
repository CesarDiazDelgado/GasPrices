package com.example.rarct.gasprices;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;

/*
@Dao
public interface DAOs {

    @Insert
    void insertCommunity(CommunitiesEntity community);

    @Insert
    void insertProvince(ProvincesEntity province);

    @Insert
    void insertTown(TownsEntity town);


    @Query("SELECT * FROM Community ORDER BY Community_name")
    List<CommunitiesEntity> allCommunities();

    @Query("SELECT * FROM Provinces LEFT JOIN Community ON Communities_ID = community.ID ORDER BY Province_name")
    List<ProvincesEntity> allProvinces();

    @Query("SELECT * FROM Towns LEFT JOIN Provinces ON Province_ID = provinces.ID ORDER BY Town_name")
    List<TownsEntity> allTowns();


    enum gasTypeEnum {
        code, label
    }


    @Database(entities = {CommunitiesEntity.class, ProvincesEntity.class, TownsEntity.class}, version = 1 )
    abstract class AppDatabase extends RoomDatabase {

        public abstract DAOs getDAO();
    }

}
*/