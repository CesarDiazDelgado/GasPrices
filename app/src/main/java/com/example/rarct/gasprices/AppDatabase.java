package com.example.rarct.gasprices;

import android.content.Context;
import android.os.AsyncTask;

import com.example.rarct.gasprices.DAO.CommunityDao;
import com.example.rarct.gasprices.DAO.ProvinceDao;
import com.example.rarct.gasprices.DAO.TownDao;
import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CommunitiesEntity.class, ProvincesEntity.class, TownsEntity.class}, version = 1 )
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CommunityDao communityDao();

    public abstract ProvinceDao provinceDao();

    public abstract TownDao townDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Gas_Prices_Database").addCallback(sRoomDatabaseCallback).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    //Populate database
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateCommunityDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateCommunityDbAsync extends AsyncTask<Void, Void, Void> {

        private final CommunityDao communityDao;

        PopulateCommunityDbAsync(AppDatabase db) {
            communityDao = db.communityDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            CommunitiesEntity community = new CommunitiesEntity(17, "Murcia");
            communityDao.insertCommunity(community);
            return null;
        }
    }

}
