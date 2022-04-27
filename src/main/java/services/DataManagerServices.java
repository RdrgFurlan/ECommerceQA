package services;

import java.util.HashMap;
import java.util.Map;

public class DataManagerServices {

    private static Map<String, String> storedVariables = new HashMap<>();

    public static void setStoredVariable(String key, String value){
        storedVariables.put(key, value);
    }

    public static String getStoredVariable(String key){
        return storedVariables.get(key);
    }
}
