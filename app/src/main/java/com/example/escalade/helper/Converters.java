package com.example.escalade.helper;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static int[] fromString(String value) {
        Type listType = new TypeToken<int[]>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(int[] list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
