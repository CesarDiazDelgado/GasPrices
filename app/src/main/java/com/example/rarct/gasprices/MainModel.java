package com.example.rarct.gasprices;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.example.rarct.gasprices.DAO.myDao;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
//import com.example.rarct.gasprices.Databases.ProvincesEntity;
//import com.example.rarct.gasprices.Databases.TownsEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


public class MainModel{

    private AppDatabase INSTANCE;

    public List<CommunitiesEntity> communitiesEntityList;
    //public List<ProvincesEntity> provincesEntityList;
    //public List<TownsEntity> townsEntityList;

    private myDao MyDao;


    public MainModel (Context context) {


        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Gas_Prices_Database").addCallback(sRoomDatabaseCallback).build();

            MyDao = INSTANCE.myDao();

            //communitiesEntityList = getCommunitiesEntityList();
            //provincesEntityList = getProvincesEntityList();
            //townsEntityList = getTownsEntityList();
        }
    }

    public List<CommunitiesEntity> getCommunitiesEntityList() {
        return (List<CommunitiesEntity>) new getAsyncTaskCommunity(MyDao).execute(); /*MyDao.getCommunitiesEntityList();*/
    }

    public void insert(CommunitiesEntity community) { MyDao.insertCommunity(community); }

    private class getAsyncTaskCommunity extends AsyncTask<Void, Void, List<CommunitiesEntity>> {

        private myDao mAsyncTaskDao;

        getAsyncTaskCommunity(myDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<CommunitiesEntity> doInBackground(Void... params) {
            return mAsyncTaskDao.getCommunitiesEntityList();
        }
    }

      /* public List<ProvincesEntity> getProvincesEntityList() {
        return provincesEntityList;
    }
    //public void insert(ProvincesEntity province) { MyDao.insertProvince(province); }

    public List<TownsEntity> getTownsEntityList() {
        return townsEntityList;
    }
    //public void insert(TownsEntity town) {MyDao.insertTown(town); }


    public void insertCommunity () {
        new getAsyncTaskCommunity(MyDao).execute();
    }

    /*public void insertProvince (ProvincesEntity provincesEntity) {
        new insertAsyncTaskProvince(MyDao).execute(provincesEntity);
    }

    public void insertTown (TownsEntity townsEntity) {
        new insertAsyncTaskTown(MyDao).execute(townsEntity);
    }
*/

   /* private static class insertAsyncTaskProvince extends AsyncTask<ProvincesEntity, Void, Void> {

        private myDao mAsyncTaskDao;

        insertAsyncTaskProvince(myDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final ProvincesEntity... params) {
            mAsyncTaskDao.insertProvince(params[0]);
            return null;
        }
    }

    private static class insertAsyncTaskTown extends AsyncTask<TownsEntity, Void, Void> {

        private myDao mAsyncTaskDao;

        insertAsyncTaskTown(myDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final TownsEntity... params) {
            mAsyncTaskDao.insertTown(params[0]);
            return null;
        }
    }*/

    //Populate database
    private RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateCommunityDbAsync(INSTANCE).execute();
                }
            };

    private  class PopulateCommunityDbAsync extends AsyncTask<Void, Void, Void> {

        //private final myDao MyDao;

        PopulateCommunityDbAsync(AppDatabase INSTANCE) {
            MyDao = INSTANCE.myDao();
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
                    MyDao.insertCommunity(c);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            /*
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
                    MyDao.insertProvince(p);
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
                    MyDao.insertTown(t);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
*/
            return null;
        }
    }

}


