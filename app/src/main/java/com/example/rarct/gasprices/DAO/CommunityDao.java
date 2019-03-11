package com.example.rarct.gasprices.DAO;

import android.icu.text.Replaceable;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface CommunityDao {

    @Insert (onConflict = REPLACE)
    void insertCommunity(CommunitiesEntity community);

    @Query("SELECT * FROM Community ORDER BY Community_name")
    List<CommunitiesEntity> allCommunities();
}
