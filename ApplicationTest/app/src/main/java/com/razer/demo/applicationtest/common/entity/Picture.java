package com.razer.demo.applicationtest.common.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName="picture")
public class Picture {

    @NonNull
    @PrimaryKey
    private long id;


    @ColumnInfo(name = "description" )
    private String description;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}


