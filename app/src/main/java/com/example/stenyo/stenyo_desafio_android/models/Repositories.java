package com.example.stenyo.stenyo_desafio_android.models;

import java.io.Serializable;
import java.util.List;

public class Repositories implements Serializable {

    public int totalCount;

    public boolean incompleteResults;

    public List<Items> items;
}
