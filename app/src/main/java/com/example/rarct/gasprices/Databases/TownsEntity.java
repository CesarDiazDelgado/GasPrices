package com.example.rarct.gasprices.Databases;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys =
                    @ForeignKey(
                            entity = ProvincesEntity.class,
                            parentColumns = "ID", //my parent id
                            childColumns = "Province_ID"), //my id
                    indices = {@Index(value = "Province_ID", unique = true)},
                    tableName = "Towns")

public class TownsEntity implements Comparable{

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "ID")
    private int id;

    @ColumnInfo (name = "Town_name")
    private String name;

    @ColumnInfo (name = "Province_ID")
    private int provinceID;

    public TownsEntity(int id, String name, int province_id) {
        this.id = id ;
        this.name = name;
        this.provinceID = province_id;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
