package com.example.stenyo.stenyo_desafio_android.models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class Result implements Serializable {

    public boolean result;

    public ArrayList<Error> errors;

    public static String getFirstError(ArrayList<Error> errors) {
        return errors.get(0).msgs.get(0);
    }

    public static void showErrors(ArrayList<Error> errors) {
        for(Error error:errors) {
            for(String msg: error.msgs) {
                Log.v("Error " + error.field, msg);
            }
        }

    }

}
