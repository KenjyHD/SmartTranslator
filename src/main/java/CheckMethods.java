import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CheckMethods {
    private Dictionary dictionary;

    public CheckMethods(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /*
    Method that checks the addWord method from Dictionary class with 2 examples
    */
    void checkAddWord(){
        System.out.println("Checking addWord method");
        System.out.print("Romanian dictionary: ");

        for(Word word1 : dictionary.getDictionaries().get("ro")){
            System.out.print(word1.getWord() + "  ");
        }

        FileReading fileReading = new FileReading();
        Word word = fileReading.readWord(
                "src/main/java/init/addNewWord.json");

        System.out.print("\nTrying to add word " + word.getWord() + ": ");
        if(dictionary.addWord(word, "ro"))
            System.out.println("word added successfully");
        System.out.print("Romanian dictionary: ");

        for(Word word1 : dictionary.getDictionaries().get("ro")){
            System.out.print(word1.getWord() + "  ");
        }

        word = fileReading.readWord(
                "src/main/java/init/addExistentWord.json");

        System.out.print("\nTrying to add the word " + word.getWord() + ": ");
        if(!dictionary.addWord(word, "ro"))
            System.out.println("word already exists");

        System.out.print("Romanian dictionary: ");
        for(Word word1 : dictionary.getDictionaries().get("ro")){
            System.out.print(word1.getWord() + "  ");
        }
        System.out.println("\n");
    }

    /*
    Method that checks the removeWord method from Dictionary class with 2 examples
    */
    void checkRemoveWord(){
        System.out.println("Checking removeWord method");
        System.out.print("French dictionary: ");

        for(Word word1 : dictionary.getDictionaries().get("fr")){
            System.out.print(word1.getWord() + "  ");
        }

        System.out.print("\nTrying to remove word jeu: ");
        if(dictionary.removeWord("jeu", "fr"))
            System.out.println("word removed successfully");
        System.out.print("French dictionary: ");

        for(Word word1 : dictionary.getDictionaries().get("fr")){
            System.out.print(word1.getWord() + "  ");
        }

        System.out.print("\nTrying to remove word chêne: ");
        if(!dictionary.removeWord("chêne", "fr"))
            System.out.println("could not find the word");
        System.out.print("French dictionary: ");

        for(Word word1 : dictionary.getDictionaries().get("fr")){
            System.out.print(word1.getWord() + "  ");
        }

        System.out.println("\n");
    }

    /*
    Method that checks the addDefinionForWord method from Dictionary class with 2 examples
    */
    void checkaAddDefinitionForWord(){
        System.out.println("Checking addDefinitionForWord method");
        System.out.print("Definition dictionaries for word pisică: ");

        for(Word word1 : dictionary.getDictionaries().get("ro")){
            if(word1.getWord().equals("pisică"))
                for(Definition definition1 : word1.getDefinitions()){
                    System.out.print(definition1.getDict() + "  ");
                }
        }

        FileReading fileReading = new FileReading();
        Definition definition = fileReading.readDefinition(
                "src/main/java/init/addNewDefinition.json");

        System.out.print("\nTrying to add definition from " + definition.getDict() + " dictionary: ");
        if(dictionary.addDefinitionForWord("pisică", "ro", definition))
            System.out.println("definition added successfully");
        System.out.print("Definition dictionaries for word pisică: ");

        for(Word word1 : dictionary.getDictionaries().get("ro")){
            if(word1.getWord().equals("pisică"))
                for(Definition definition1 : word1.getDefinitions()){
                    System.out.print(definition1.getDict() + "  ");
                }
        }

        definition = fileReading.readDefinition(
                "src/main/java/init/addExistentDefinition.json");

        System.out.print("\nTrying to add definitions from " + definition.getDict() + " dictionary: ");
        if(!dictionary.addDefinitionForWord("pisică", "ro", definition))
            System.out.println("definition already exists");
        System.out.print("Definition dictionaries for word pisică: ");

        for(Word word1 : dictionary.getDictionaries().get("ro")){
            if(word1.getWord().equals("pisică"))
                for(Definition definition1 : word1.getDefinitions()){
                    System.out.print(definition1.getDict() + "  ");
                }
        }
    }

    /*
    Method that checks the removeDefinition method from Dictionary class with 2 examples
    */
    void checkRemoveDefinition(){
        System.out.println("\n\nChecking removeDefinition method");
        System.out.print("Definition dictionaries for word merge: ");

        for(Word word1 : dictionary.getDictionaries().get("ro")){
            if(word1.getWord().equals("merge"))
                for(Definition definition1 : word1.getDefinitions()){
                    System.out.print(definition1.getDict() + "  ");
                }
        }

        System.out.print("\nTrying to remove definitions for Micul dicționar academic, ediția a II-a dictionary: ");
        if(dictionary.removeDefinition("merge", "ro", "Micul dicționar academic, ediția a II-a"))
            System.out.println("definition removed successfully");
        System.out.print("Definition dictionaries for word merge: ");

        for(Word word1 : dictionary.getDictionaries().get("ro")){
            if(word1.getWord().equals("merge"))
                for(Definition definition1 : word1.getDefinitions()){
                    System.out.print(definition1.getDict() + "  ");
                }
        }

        System.out.print("\nTrying to remove definitions for DEX online dictionary: ");
        if(!dictionary.removeDefinition("merge", "ro", "DEX online"))
            System.out.println("could not find the dictionary");
        System.out.print("Definition dictionaries for word merge: ");

        for(Word word1 : dictionary.getDictionaries().get("ro")){
            if(word1.getWord().equals("merge"))
                for(Definition definition1 : word1.getDefinitions()){
                    System.out.print(definition1.getDict() + "  ");
                }
        }
        System.out.println("\n");
    }

    /*
    Method that checks the translateWord method from Dictionary class with 2 examples
    */
    void checkTranslateWord(){
        System.out.println("Checking translateWord method");

        System.out.print("Translating word merg from romanian to french: " );
        System.out.println(dictionary.translateWord("merg","ro", "fr"));

        System.out.print("Translating word s'enfuit from french to romanian: " );
        System.out.println(dictionary.translateWord("s'enfuit","fr", "ro"));
        System.out.println();
    }

    /*
    Method that checks the translateSentence method from Dictionary class with 2 examples
    */
    void checkTranslateSentence(){
        System.out.println("Checking translateSentence method");

        System.out.print("Translating sentence pisică fuge merg from romanian to french: " );
        System.out.println(dictionary.translateSentence("pisică fuge","ro", "fr"));

        System.out.print("Translating sentence chat manger from french to romanian: " );
        System.out.println(dictionary.translateSentence("chat manger","fr", "ro"));
        System.out.println();
    }

    /*
    Method that checks the translateSentences method from Dictionary class with 2 examples
    */
    void checkTranslateSentences(){
        System.out.println("Checking translateSentences method");
        System.out.println("Translating sentence chat s'enfuit from french to romanian: " );
        if(dictionary.translateSentences("chat s'enfuit", "fr", "ro") != null) {
            for (int i = 0; i < 3; i++) {
                System.out.println(dictionary.translateSentences("chat s'enfuit", "fr", "ro").get(i));
            }
        }
        else{
            System.out.println("Could not translate the sentence");
        }

        System.out.println("Translating sentence pisică mănâncă from romanian to french: " );
        if(dictionary.translateSentences("pisică mănâncă", "ro", "fr") != null) {
            for (int i = 0; i < 3; i++) {
                System.out.println(dictionary.translateSentences("pisică mănâncă", "ro", "fr").get(i));
            }
        }
        else{
            System.out.println("Could not translate the sentence");
        }
        System.out.println();
    }

    /*
    Method that checks the getDefinitionForWord method from Dictionary class with 2 examples
    */
    void checkGetDefinitionsForWord(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("Definitions metadata for word câine(sorted by year)");
        if(dictionary.getDefinitionsForWord("câine", "ro") != null)
            System.out.println(gson.toJson(dictionary.getDefinitionsForWord("câine", "ro")));
        else
            System.out.println("Could not find the word");

        System.out.println("Definitions metadata for word pere(sorted by year)");
        if(dictionary.getDefinitionsForWord("pere", "ro") != null)
            System.out.println(gson.toJson(dictionary.getDefinitionsForWord("pere", "ro")));
        else
            System.out.println("Could not find the word");
        System.out.println();
    }

    /*
    Method that checks the exportDictionary method from Dictionary class with 2 examples
    */
    void checkExportDictionary(){
        System.out.println("The metadata of romanian and russian dictionaries can be found in the output folder");

        dictionary.exportDictionary("ro");
        dictionary.exportDictionary("ru");
    }
}
