package michapehlivan.discordbotlib.util.json;

import java.io.FileReader;
import java.io.IOException;
import java.security.KeyException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonReader {
    
    private String jsonPath;
    private Gson gson = new Gson();
    private JsonObject file = new JsonObject();
    
    public JsonReader(String jsonPath){
        this.jsonPath = jsonPath;
    }

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

    public String getFileAsString(){
        return gson.toJson(parseJson());
    }

    public JsonObject getFileAsJsonObject(){
        return parseJson();
    }

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
