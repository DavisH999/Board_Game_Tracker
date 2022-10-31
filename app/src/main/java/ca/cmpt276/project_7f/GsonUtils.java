package ca.cmpt276.project_7f;

import com.google.gson.Gson;

public class GsonUtils {
    public static String getJsonStringFromObject(Object object)
    {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T>T getObjectFromJsonString(String jsonString, Class<T> tClass)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonString,tClass);
    }
}

