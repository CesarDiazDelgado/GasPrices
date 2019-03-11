package com.example.rarct.gasprices;

import android.app.Application;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.util.List;

public class MainModel{

    private Repository repository;

    private List<CommunitiesEntity> communitiesEntityList;
    private List<ProvincesEntity> provincesEntityList;
    private List<TownsEntity> townsEntityList;

    public MainModel (Application application) {
        //super(application);
        repository = new Repository(application);

        communitiesEntityList = repository.getCommunitiesEntityList();
        provincesEntityList = repository.getProvincesEntityList();
        townsEntityList = repository.getTownsEntityList();
    }

    public MainModel() {

    }

    List<CommunitiesEntity> getCommunitiesEntityList() { return communitiesEntityList; }
    public void insert(CommunitiesEntity community) { repository.insertCommunity(community); }

    List<ProvincesEntity> getProvincesEntityList() { return provincesEntityList; }
    public void insert(ProvincesEntity province) { repository.insertProvince(province); }

    List<TownsEntity> getTownsEntityList() { return townsEntityList; }
    public void insert(TownsEntity town) { repository.insertTown(town); }
}


