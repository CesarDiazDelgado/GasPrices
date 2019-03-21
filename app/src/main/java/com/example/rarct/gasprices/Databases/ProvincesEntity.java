package com.example.rarct.gasprices.Databases;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys =
                    @ForeignKey(
                            entity = CommunitiesEntity.class,
                            parentColumns = "ID",
                            childColumns = "Communities_ID"),
                    indices = {@Index(value = "Communities_ID")},
                    tableName = "Provinces")

public class ProvincesEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "ID")
    private int id;

    @ColumnInfo (name = "Province_name")
    private String name;

    @ColumnInfo (name = "Communities_ID")
    private int communityID;

    public ProvincesEntity(int id, String name, int communityID) {
        this.id = id ;
        this.name = name;
        this.communityID = communityID;
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

    public String toString() {
        return name;
    }
}
