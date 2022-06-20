package utils;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class Common {
    public static String readJsonFile(String filePath){
        String json=null;
        JSONParser objectParser= new JSONParser();
        try {
            String jsonFilePath=filePath+".json";
            String currrentDir=System.getProperty("user.dir");
            Object obj = objectParser.parse(new FileReader(currrentDir+jsonFilePath));
            json=new Gson().toJson(obj);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
return json;
    }

    public static String jsonPathQuery(String jsonString, String jsonQuery){
        JsonPath jsonPath=new JsonPath(jsonString);
        return jsonPath.get(jsonQuery).toString();
    }
}
