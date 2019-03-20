package com.example.rarct.gasprices;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.example.rarct.gasprices.DAO.myDao;
import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CommunitiesEntity.class ,ProvincesEntity.class, TownsEntity.class}, version = 1 )
public abstract class AppDatabase extends RoomDatabase {
    public abstract myDao myDao();

}
