package com.example.rarct.gasprices.Databases;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = "ID", unique = true)}, tableName = "Community")
public class CommunitiesEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "ID")
    public int id;

    @ColumnInfo (name = "Community_name")
    private String name;

    public CommunitiesEntity(int id, String name) {
        this.id = id ;
        this.name = name;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
