package com.example.stenyo.stenyo_desafio_android.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

// Nome do repositório, Descrição do Repositório, Nome /
// Foto do autor, Número de Stars, Número de Forks
public class Items implements Serializable, Parcelable {

    public String name;

    public String fullName;

    public String description;

    public int forksCount;

    public int stargazersCount;

    public Owner owner;

    protected Items(Parcel in) {
        name = in.readString();
        fullName = in.readString();
        description = in.readString();
        forksCount = in.readInt();
        stargazersCount = in.readInt();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(description);
        dest.writeInt(forksCount);
        dest.writeInt(stargazersCount);
    }
}
