package com.example.rarct.gasprices;


import android.content.Context;

import com.example.rarct.gasprices.DAO.CommunityDao;
import com.example.rarct.gasprices.DAO.ProvinceDao;
import com.example.rarct.gasprices.DAO.TownDao;
import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CommunitiesEntity.class, ProvincesEntity.class, TownsEntity.class}, version = 1 )
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CommunityDao communityDao();

    public abstract ProvinceDao provinceDao();

    public abstract TownDao townDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Gas_Prices_Database").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
