package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. <br />
 * User: PANKAJ CHOUDHARY <br />
 * Date: 11/8/19 <br />
 * Time: 10:02 AM <br />
 */
public final class JsonFormatUtility {

    private static final Gson GSON = new Gson();

    // private constructor
    private JsonFormatUtility() {
    }


    public static <T> T getObjectFromJsonString(String jsonString, Class<T> objectClass) {
        return GSON.fromJson(jsonString, objectClass);
    }

    public static <T> String getJsonStringFromObject(T object) {
        if (object == null) {
            return null;
        }
        return GSON.toJson(object);
    }

    public static <T> List<T> parseGsonArray(String json, Class<T[]> model) {
        return Arrays.asList(new Gson().fromJson(json, model));
    }

}