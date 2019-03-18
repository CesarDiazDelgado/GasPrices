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
import java.util.Scanner;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


public class MainModel{

    private AppDatabase INSTANCE;

    //public List<ProvincesEntity> provincesEntityList;
    //public List<TownsEntity> townsEntityList;

    private myDao MyDao;

    public MainModel (Context context) {

        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Gas_Prices_Database").addCallback(sRoomDatabaseCallback).build();

            MyDao = INSTANCE.myDao();

        }
    }

    public void getCommunitiesEntityList(final Listener<List<CommunitiesEntity>> response) {
        getAsyncTaskCommunity asyncTask = new getAsyncTaskCommunity(response);
        asyncTask.execute();
    }

    private class getAsyncTaskCommunity extends AsyncTask<Void, Void, List<CommunitiesEntity>> {

        private Listener<List<CommunitiesEntity>> mAsyncTaskListener;

        getAsyncTaskCommunity(Listener<List<CommunitiesEntity>> response) {
            mAsyncTaskListener = response;
        }

        @Override
        protected List<CommunitiesEntity> doInBackground(Void... params) {
            return MyDao.getCommunitiesEntityList();
        }

        @Override
        protected void onPostExecute(List<CommunitiesEntity> communityList) {
            mAsyncTaskListener.onResponse(communityList);
        }
    }

    void Basura() {
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
    }

    //Populate database
    private RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateCommunityDbAsync(INSTANCE).execute();
        }
    };

    private class PopulateCommunityDbAsync extends AsyncTask<Void, Void, Void> {

        PopulateCommunityDbAsync(AppDatabase INSTANCE) {
            MyDao = INSTANCE.myDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            Resources r = Resources.getSystem();
            //Communities
            InputStream is = r.openRawResource(R.raw.communities);
            Scanner scanner = new Scanner(is);
            String[] line;
            String s;
            CommunitiesEntity c;

            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                line = s.split("#");

                c = new CommunitiesEntity(Integer.parseInt(line[0]), line[1]);
                MyDao.insertCommunity(c);
            }
            scanner.close();
            return null;
        }
    }

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

