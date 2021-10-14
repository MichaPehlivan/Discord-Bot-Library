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

/**
 * Class for easily writing to a Json file
 * @author Micha Pehlivan
 */
public class JsonWriter {
    
    private String jsonPath;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JsonObject file = new JsonObject();

    /**
     * Constructor of {@link JsonWriter}
     * @param jsonPath The path to the Json to write to
     */
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

    /**
     * Add a Java object to the Json file as a JsonOjbect
     * @param key The key to link the Java object to
     * @param object The Java Object to add to the file
     * @throws KeyException When the Json file already has a value associated with the specified key
     */
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

    /**
     * Add a Java list to the Json file as a JsonArray
     * @param key The key to link the Java list to
     * @param array The Java list to add to the file
     * @throws KeyException When the Json file already has a value associated with the specified key
     */
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

    /**
     * Replace a value in the Json file with a Java object
     * @param key The key of the value to replace
     * @param newObject The Java object to replace the current value with
     * @throws KeyException When the Json file has no value associated with the specified key
     */
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

    /**
     * Replace a JsonArray in the Json file with a Java list
     * @param key The key of the Array to replace
     * @param newArray The Java list to replace the current array with
     * @throws KeyException When the Json object has no value associated with the specified key
     */
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

    /**
     * Clears the Json file of all values
     */
    public void clearJson(){
        file = new JsonObject();
        FileWriter writer;
        try {
            writer = new FileWriter(jsonPath);
            gson.toJson(file, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
