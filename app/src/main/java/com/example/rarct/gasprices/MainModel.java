package com.example.rarct.gasprices;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.example.rarct.gasprices.DAO.myDao;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

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

    private AppDatabase appDatabase;
    private static MainModel INSTANCE;
    private myDao MyDao;
    private final Resources resources;

    //public List<ProvincesEntity> provincesEntityList;
    //public List<TownsEntity> townsEntityList;

    private MainModel (Context context) {

        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Gas_Prices_Database").build();

        MyDao = appDatabase.myDao();
        resources = context.getResources();
    }

    public static MainModel getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new MainModel(context);
        }
        return INSTANCE;
    }

    //Communities
    public void getCommunitiesEntityList(final Listener<List<CommunitiesEntity>> response) {
        getAsyncTaskCommunity asyncTask = new getAsyncTaskCommunity(response);
        asyncTask.execute();
    }

    public void PopulateCommunities() {
        PopulateCommunityDbAsync asyncTask = new PopulateCommunityDbAsync(appDatabase);
        asyncTask.execute();
    }

    //Provinces
    public void getProvincesEntityList(int communityID, final Listener<List<ProvincesEntity>> response) {
        getAsyncTaskProvince asyncTask = new getAsyncTaskProvince(response, communityID);
        asyncTask.execute();
    }

    public void PopulateProvinces() {
        PopulateProvincesDbAsync asyncTask = new PopulateProvincesDbAsync(appDatabase);
        asyncTask.execute();
    }

    //Towns
    public void getTownsEntityList(int provinceID, final Listener<List<TownsEntity>> response) {
        getAsyncTaskTown asyncTask = new getAsyncTaskTown(response, provinceID);
        asyncTask.execute();
    }

    public void PopulateTowns() {
        PopulateTownsDbAsync asyncTask = new PopulateTownsDbAsync(appDatabase);
        asyncTask.execute();
    }


    //Get communities, provinces and towns
    private class getAsyncTaskCommunity extends AsyncTask<Void, Void, List<CommunitiesEntity>> {

        private Listener<List<CommunitiesEntity>> mAsyncTaskListener;

        getAsyncTaskCommunity(Listener<List<CommunitiesEntity>> response) {
            mAsyncTaskListener = response;
        }

        @Override
        protected List<CommunitiesEntity> doInBackground(Void... params) {
            if (MyDao.getCommunitiesEntityList().isEmpty()) {
                PopulateCommunities();
            }
            return MyDao.getCommunitiesEntityList();
        }

        @Override
        protected void onPostExecute(List<CommunitiesEntity> communityList) {
            mAsyncTaskListener.onResponse(communityList);
        }
    }

    private class getAsyncTaskProvince extends AsyncTask<Void, Void, List<ProvincesEntity>> {

        private Listener<List<ProvincesEntity>> mAsyncTaskListener;
        private int id;

        getAsyncTaskProvince(Listener<List<ProvincesEntity>> response, int communityID) {
            mAsyncTaskListener = response;
            id = communityID;
        }

        @Override
        protected List<ProvincesEntity> doInBackground(Void... params) {
            if (MyDao.getProvincesEntityList(id).isEmpty()) {
                PopulateProvinces();
            }
            return MyDao.getProvincesEntityList(id);
        }

        @Override
        protected void onPostExecute(List<ProvincesEntity> provincesList) {
            mAsyncTaskListener.onResponse(provincesList);
        }
    }

    private class getAsyncTaskTown extends AsyncTask<Void, Void, List<TownsEntity>> {

        private Listener<List<TownsEntity>> mAsyncTaskListener;
        private int id;

        getAsyncTaskTown(Listener<List<TownsEntity>> response, int provinceID) {
            mAsyncTaskListener = response;
            id = provinceID;
        }

        @Override
        protected List<TownsEntity> doInBackground(Void... params) {
            if (MyDao.getTownsEntityList(id).isEmpty()) {
                PopulateTowns();
            }
            return MyDao.getTownsEntityList(id);
        }

        @Override
        protected void onPostExecute(List<TownsEntity> townsList) {
            mAsyncTaskListener.onResponse(townsList);
        }
    }


    //Populate communities, provinces and towns
    private class PopulateCommunityDbAsync extends AsyncTask<Void, Void, Void> {

        PopulateCommunityDbAsync(AppDatabase database) {
            MyDao = database.myDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            InputStream is = resources.openRawResource(R.raw.communities);
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

    private class PopulateProvincesDbAsync extends AsyncTask<Void, Void, Void> {

        PopulateProvincesDbAsync(AppDatabase database) {
            MyDao = database.myDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            InputStream is = resources.openRawResource(R.raw.provinces);
            Scanner scanner = new Scanner(is);
            String[] line;
            String s;
            ProvincesEntity p;

            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                line = s.split("#");

                p = new ProvincesEntity(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]));
                MyDao.insertProvince(p);
            }
            scanner.close();
            return null;
        }
    }

    private class PopulateTownsDbAsync extends AsyncTask<Void, Void, Void> {

        PopulateTownsDbAsync(AppDatabase database) {
            MyDao = database.myDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            InputStream is = resources.openRawResource(R.raw.towns);
            Scanner scanner = new Scanner(is);
            String[] line;
            String s;
            TownsEntity p;

            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                line = s.split("#");

                p = new TownsEntity(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]));
                MyDao.insertTown(p);
            }
            scanner.close();
            return null;
        }
    }


}




