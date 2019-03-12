package com.example.rarct.gasprices;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.example.rarct.gasprices.DAO.CommunityDao;
import com.example.rarct.gasprices.DAO.ProvinceDao;
import com.example.rarct.gasprices.DAO.TownDao;
import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        private final ProvinceDao provinceDao;
        private final TownDao townDao;

        PopulateCommunityDbAsync(AppDatabase db) {
            communityDao = db.communityDao();
            provinceDao = db.provinceDao();
            townDao = db.townDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            Resources r = Resources.getSystem();
            //Communities
            try {
                InputStream is = r.openRawResource(R.raw.communities);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                String[] s;
                CommunitiesEntity c;

                while ((line = reader.readLine()) != null) {
                    s = line.split("#");
                    c = new CommunitiesEntity(Integer.parseInt(s[0]), s[1]);
                    communityDao.insertCommunity(c);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Provinces
            try {
                InputStream is = r.openRawResource(R.raw.provinces);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                String[] s;
                ProvincesEntity p;

                while ((line = reader.readLine()) != null) {
                    s = line.split("#");
                    p = new ProvincesEntity(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]));
                    provinceDao.insertProvince(p);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Towns
            try {
                InputStream is = r.openRawResource(R.raw.towns);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                String[] s;
                TownsEntity t;

                while ((line = reader.readLine()) != null) {
                    s = line.split("#");
                    t = new TownsEntity(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]));
                    townDao.insertTown(t);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
