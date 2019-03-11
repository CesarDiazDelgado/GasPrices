package com.example.rarct.gasprices.Databases;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys =
                    @ForeignKey(
                            entity = CommunitiesEntity.class,
                            parentColumns = "ID",
                            childColumns = "Communities_ID"),
                    indices = {@Index(value = "Communities_ID", unique = true)},
                    tableName = "Provinces")

public class ProvincesEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "ID")
    int id;

    @ColumnInfo (name = "Province_name")
    private String name;

    @ColumnInfo (name = "Communities_ID")
    private int communityID;

    public ProvincesEntity(int id, String name) {// ,int community_id) {
        this.id = id ;
        this.name = name;
        //this.communityID = community_id;
    }


    @NonNull
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCommunityID() {
        return communityID;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommunityID(int communityID) {
        this.communityID = communityID;
    }
}
