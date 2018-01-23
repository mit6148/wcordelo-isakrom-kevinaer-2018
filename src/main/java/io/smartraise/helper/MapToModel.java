package io.smartraise.helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.smartraise.model.accounts.Administrator;
import io.smartraise.model.accounts.Donor;
import io.smartraise.model.accounts.Member;

import java.util.Map;

/**
 * Creates a Model from a json mapping
 */
public class MapToModel {

    private static final Gson GSON = new Gson();

    /**
     * @param objectMap the json mapping
     * @return {@link Member} created from mapping
     */
    public static Member mapToMember(Map<String, Object> objectMap) {
        JsonElement jsonElement = GSON.toJsonTree(objectMap);
        return GSON.fromJson(jsonElement, Member.class);
    }

    /**
     * @param objectMap the json mapping
     * @return {@link Administrator} created from mapping
     */
    public static Administrator mapToAdmin(Map<String, Object> objectMap) {
        JsonElement jsonElement = GSON.toJsonTree(objectMap);
        return GSON.fromJson(jsonElement, Administrator.class);
    }

    /**
     * @param objectMap the json mapping
     * @return {@link Donor} created from mapping
     */
    public static Donor mapToDonor(Map<String, Object> objectMap) {
        JsonElement jsonElement = GSON.toJsonTree(objectMap);
        return GSON.fromJson(jsonElement, Donor.class);
    }
}
