package com.example.rarct.gasprices.DAO;

import com.example.rarct.gasprices.Databases.ProvincesEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProvinceDao {

    @Insert (onConflict = REPLACE)
    void insertProvince(ProvincesEntity province);

    @Query("SELECT * FROM Provinces LEFT JOIN Community ON Communities_ID = community.ID ORDER BY Province_name")
    List<ProvincesEntity> allProvinces();
}
