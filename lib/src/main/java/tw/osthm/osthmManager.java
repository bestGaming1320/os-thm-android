package tw.osthm;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class osthmManager {

    public static final String externalDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String themes_folder = externalDir + "/.osthm/themes/";
    public static final String config_file = externalDir + "/.osthm/conf";

    public static void setConf(String key, String value) throws IOException {
        init();
        HashMap<String, String> data = loadConfJson();
        data.put(key, value);
        StorageUtil.createFile(config_file, new Gson().toJson(data));
    }

    public static String getConf(String key, String defaultValue) throws IOException {
        init();
        HashMap<String, String> data = loadConfJson();
        return data.containsKey(key) ? data.get(key) : defaultValue;
    }

    public static boolean containsConf(String key) throws IOException {
        init();
        HashMap<String, String> data = loadConfJson();
        return data.containsKey(key);
    }

    public static void removeConf(String key) throws IOException {
        init();
        HashMap<String, String> data = loadConfJson();
        if (data.containsKey(key)) {
            data.remove(key);
            StorageUtil.createFile(config_file, new Gson().toJson(data));
        }
    }

    public static void clearConf() throws IOException {
        init();
        StorageUtil.createFile(config_file, "{}");
    }

    public static void setTheme(HashMap<String, Object> theme) throws IOException {
        init();
        StorageUtil.createFile(themes_folder + theme.get("uuid").toString(),
                new Gson().toJson(theme));
    }

    public static HashMap<String, Object> getTheme(String uuid) throws IOException {
        init();
        return new Gson().fromJson(StorageUtil.readFile(themes_folder + uuid),
                new TypeToken<HashMap<String, Object>>(){}.getType());
    }

    public static ArrayList<HashMap<String, Object>> getThemes() throws IOException {
        init();
        List<File> files = StorageUtil.getFiles(themes_folder);
        ArrayList<HashMap<String, Object>> themes = new ArrayList<>();
        for(File file : files) {
            themes.add((HashMap<String, Object>)new Gson().fromJson(StorageUtil
                            .readFile(file.getAbsolutePath()),
                    new TypeToken<HashMap<String, Object>>(){}.getType()));
        }
        return themes;
    }

    public static void removeTheme(String uuid) throws IOException {
        init();
        StorageUtil.deleteFile(themes_folder + uuid);
    }

    public static void clearThemes() throws IOException {
        init();
        List<File> files = StorageUtil.getFiles(themes_folder);
    }

    // Utilities ===================================================================================

    // Load config file into HashMap
    private static HashMap<String, String> loadConfJson() throws IOException {
        return new Gson().fromJson(StorageUtil.readFile(config_file),
                new TypeToken<HashMap<String, Object>>(){}.getType());
    }

    // Initialize
    private static void init() throws IOException {
        // Initialize

        // Check if .osthm folder exist
        if (!StorageUtil.isDirectoryExists(themes_folder) ||
                !StorageUtil.isFileExist(config_file)) {
            // Initialize the folder structure
            // Create the folder .osthm and .osthm/themes
            StorageUtil.createDirectory(themes_folder);

            // Create the config file
            StorageUtil.createFile(config_file, "{}");
        } else {
            // Check if the config file is valid or not
            if (!isJSONValid(StorageUtil.readFile(config_file)))
                StorageUtil.createFile(config_file, "{}");

            // Check if the themes are valid or not
            List<File> files = StorageUtil.getFiles(themes_folder);
            for (File file : files) {
                // If the theme is invalid, then delete it
                if (!isJSONValid(StorageUtil.readFile(file.getAbsolutePath())))
                    StorageUtil.deleteFile(file.getAbsolutePath());
                else {
                    // Detect if there are any filename that doesn't matches the UUID, then fix it
                    HashMap<String, Object> thm = new Gson().fromJson(StorageUtil
                                    .readFile(file.getAbsolutePath()),
                            new TypeToken<HashMap<String, Object>>(){}.getType());

                    if (thm.get("uuid").toString().equals(file.getName()))
                        StorageUtil.rename(file.getAbsolutePath(), themes_folder +
                                thm.get("uuid").toString());
                }
            }
        }
    }

    // Check if the JSON is valid or not
    private static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
