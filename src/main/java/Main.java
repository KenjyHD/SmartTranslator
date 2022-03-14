import com.google.gson.Gson;

public class Main {

    public static void main(String[] args){
        Gson gson = new Gson();
        FileReading fileReading = new FileReading();
        Dictionary dictionary = fileReading.readDictionariesFromFolder(
                "src/main/java/init/dictionaries");

        CheckMethods checkMethods = new CheckMethods(dictionary);

        checkMethods.checkAddWord();

        checkMethods.checkRemoveWord();

        checkMethods.checkaAddDefinitionForWord();

        checkMethods.checkRemoveDefinition();

        checkMethods.checkTranslateWord();

        checkMethods.checkTranslateSentence();

        checkMethods.checkTranslateSentences();

        checkMethods.checkGetDefinitionsForWord();

        checkMethods.checkExportDictionary();
    }
}
