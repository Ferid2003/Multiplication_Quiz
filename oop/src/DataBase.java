import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DataBase {

    public static FileWriter file;



    private HashMap<String,String> data;

    public DataBase() {
        HashMap<String,String> data = new HashMap<>();
        data.put("admin","123");
        data.put("qaqa","123");
        data.put("rafiq","123");
        data.put("bob","123");

        this.data = data;
    }

    public HashMap<String, String> getData() {
        return data;
    }
}
