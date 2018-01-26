package com.example.stenyo.stenyo_desafio_android.models;

//Nome / Foto do autor do PR, Título do PR, Data do PR e Body do PR
//Ao tocar em um item, deve abrir no browser a página do Pull Request em questão

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PullRequest implements Serializable, Parcelable {
    public String htmlUrl;
    public String title;
    public String body;
    public String createdAt;
    public User user;

    protected PullRequest(Parcel in) {
        htmlUrl = in.readString();
        title = in.readString();
        body = in.readString();
        createdAt = in.readString();
    }

    public static final Creator<PullRequest> CREATOR = new Creator<PullRequest>() {
        @Override
        public PullRequest createFromParcel(Parcel in) {
            return new PullRequest(in);
        }

        @Override
        public PullRequest[] newArray(int size) {
            return new PullRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(htmlUrl);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(createdAt);
    }
}
