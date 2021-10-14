package michapehlivan.discordbotlib.util.json;

import java.io.FileReader;
import java.io.IOException;
import java.security.KeyException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * A class for easily reading from a Json file
 * @author Micha Pehlivan
 */
public class JsonReader {
    
    private String jsonPath;
    private Gson gson = new Gson();
    private JsonObject file = new JsonObject();
    
    /**
     * Constructor of {@link JsonReader}
     * @param jsonPath The path to the Json file being read
     */
    public JsonReader(String jsonPath){
        this.jsonPath = jsonPath;
    }

    //Parses a Json file into a JsonObject
    private JsonObject parseJson(){
        JsonParser parser = new JsonParser();

        try {
            FileReader reader = new FileReader(jsonPath);
            JsonElement jsonElement = parser.parse(reader);
            reader.close();
            file = jsonElement.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * Read the Json file as a Java String
     * @return The Json file as a String
     */
    public String getFileAsString(){
        return gson.toJson(parseJson());
    }

    /**
     * Read the Json file as a JsonObject (gson)
     * @return The Json file as a JsonObject
     */
    public JsonObject getFileAsJsonObject(){
        return parseJson();
    }

    /**
     * Reads the Json file, and returns the value of the specified key as a String
     * @param key The key of the value being read
     * @return The value belonging to the specified key as a Java String
     * @throws KeyException When the Json file has no value with the specified key
     * @apiNote This method doesn't work with JsonArrays, please use ValueAsJsonElement() for that
     */
    public String getValueAsString(String key) throws KeyException{
        parseJson();
        if(file.has(key)){
            if(file.get(key).isJsonArray()){
                throw new IllegalArgumentException("getValueAsString() should not be used for JsonArrays");
            }
            else{
                return file.get(key).getAsString();
            }
        }
        else{
            throw new KeyException("Json file has no value with key " + key);
        }
    }

    /**
     * Reads the Json file, and returns the value of the specified key as a JsonElement
     * @param key The key of the value being read
     * @return The value belonging to the specified key as a JsonElement
     * @throws KeyException When the Json file has no value with the specified key
     */
    public JsonElement getValueAsJsonElement(String key) throws KeyException{
        parseJson();
        if(file.has(key)){
            return file.get(key);
        }
        else{
            throw new KeyException("Json file has no value with key " + key);
        }
    }
}
