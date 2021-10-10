package michapehlivan.discordbotlib.util.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonWriter {
    
    private String jsonPath;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JsonObject file = new JsonObject();

    public JsonWriter(String jsonPath){
        this.jsonPath = jsonPath;
        JsonParser parser = new JsonParser();

        try {
            FileReader reader = new FileReader(jsonPath);
            JsonElement jsonElement = parser.parse(reader);
            reader.close();
            file = jsonElement.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addObject(String key, Object object) throws KeyException{
        if(!file.has(key)){
            file.add(key, gson.toJsonTree(object, object.getClass()));
            try {
                FileWriter writer = new FileWriter(jsonPath);
                gson.toJson(file, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new KeyException("Json file already has a value with key " + key);
        }
    }

    public void addArray(String key, List<?> array) throws KeyException{
        if(!file.has(key)){
            file.add(key, gson.toJsonTree(array, array.getClass()));
            try {
                FileWriter writer = new FileWriter(jsonPath);
                gson.toJson(file, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new KeyException("Json file already has a value with key " + key);
        }
    }

    public void replaceObject(String key, Object newObject) throws KeyException{
        if(file.has(key)){
            file.remove(key);
            file.add(key, gson.toJsonTree(newObject, newObject.getClass()));
            try {
                FileWriter writer = new FileWriter(jsonPath);
                gson.toJson(file, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new KeyException("Json file has no value with key " + key);
        }
    }

    public void replaceArray(String key, List<?> newArray) throws KeyException{
        if(file.has(key)){
            file.remove(key);
            file.add(key, gson.toJsonTree(newArray, newArray.getClass()));
            try {
                FileWriter writer = new FileWriter(jsonPath);
                gson.toJson(file, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new KeyException("Json file has no value with key " + key);
        }
    }

}
