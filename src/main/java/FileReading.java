import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FileReading {
    /*
    Method that read all files from a folder; the files should have the metadata of different dictionaries
    */
    Dictionary readDictionariesFromFolder(String path){
        File folder = new File(path);
        Gson gson = new Gson();
        Map<String, TreeSet<Word>> hashMap = new HashMap<>();
        //the type of TreeSet used for fromJson method
        Type type = new TypeToken<TreeSet<Word>>(){}.getType();
        File[] files = folder.listFiles();
        try {
            if(files != null)
                for (File f : files) {
                    String[] splitter = f.getName().split("_"); //get the language from file name
                    FileReader file = new FileReader(f);
                    hashMap.put(splitter[0], gson.fromJson(file, type));
                }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return new Dictionary(hashMap);
    }

    /*
    Method to read the metadata of a word from a json file and stock them in the object
    Returns the object
    */
    Word readWord(String path){
        Word word = new Word();
        try {
            FileReader file = new FileReader(path);
            Gson gson = new Gson();
            word = gson.fromJson(file, Word.class);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return word;
    }

    /*
    Method to read the metadata of a definition from a json file and stock them in the object
    Returns the object
    */
    Definition readDefinition(String path){
        Definition definition = new Definition();
        try {
            FileReader file = new FileReader(path);
            Gson gson = new Gson();
            definition = gson.fromJson(file, Definition.class);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return definition;
    }
}
