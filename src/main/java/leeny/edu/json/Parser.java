package leeny.edu.json;

import com.google.gson.Gson;

public final class Parser {
    private static Gson gson;

    private Parser() {
        gson = new Gson();
    }

    public static String getJsonFromObject(ResponseJSON response) {
        return gson.toJson(response);
    }

    public static ResponseJSON getObjectFromJson(String jsonString){
        return gson.fromJson(jsonString, ResponseJSON.class);
    }
}
