package com.example.rarct.gasprices;

import android.app.Application;
import android.os.AsyncTask;

import com.example.rarct.gasprices.DAO.CommunityDao;
import com.example.rarct.gasprices.DAO.ProvinceDao;
import com.example.rarct.gasprices.DAO.TownDao;
import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.util.List;

public class Repository {

    //Communities
    private CommunityDao communityDao;
    private List<CommunitiesEntity> communitiesEntityList;

    //Provinces
    private ProvinceDao provinceDao;
    private List<ProvincesEntity> provincesEntityList;

    //Towns
    private TownDao townDao;
    private List<TownsEntity> townsEntityList;

    Repository (Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);

        communityDao = db.communityDao();
        communitiesEntityList = getCommunitiesEntityList();

        provinceDao = db.provinceDao();
        provincesEntityList = getProvincesEntityList();

        townDao = db.townDao();
        townsEntityList = getTownsEntityList();
    }

    public List<CommunitiesEntity> getCommunitiesEntityList() {
        return communitiesEntityList;
    }

    public List<ProvincesEntity> getProvincesEntityList() {
        return provincesEntityList;
    }

    public List<TownsEntity> getTownsEntityList() {
        return townsEntityList;
    }


    public void insertCommunity (CommunitiesEntity communitiesEntity) {
        new insertAsyncTaskCommunity(communityDao).execute(communitiesEntity);
    }

    public void insertProvince (ProvincesEntity provincesEntity) {
        new insertAsyncTaskProvince(provinceDao).execute(provincesEntity);
    }

    public void insertTown (TownsEntity townsEntity) {
        new insertAsyncTaskTown(townDao).execute(townsEntity);
    }

    private static class insertAsyncTaskCommunity extends AsyncTask<CommunitiesEntity, Void, Void> {

        private CommunityDao mAsyncTaskDao;

        insertAsyncTaskCommunity(CommunityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final CommunitiesEntity... params) {
            mAsyncTaskDao.insertCommunity(params[0]);
            return null;
        }
    }

    private static class insertAsyncTaskProvince extends AsyncTask<ProvincesEntity, Void, Void> {

        private ProvinceDao mAsyncTaskDao;

        insertAsyncTaskProvince(ProvinceDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final ProvincesEntity... params) {
            mAsyncTaskDao.insertProvince(params[0]);
            return null;
        }
    }

    private static class insertAsyncTaskTown extends AsyncTask<TownsEntity, Void, Void> {

        private TownDao mAsyncTaskDao;

        insertAsyncTaskTown(TownDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final TownsEntity... params) {
            mAsyncTaskDao.insertTown(params[0]);
            return null;
        }
    }
}
