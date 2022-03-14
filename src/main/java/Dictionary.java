import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Dictionary {
    private final Map<String, TreeSet<Word>> dictionaries;

    public Map<String, TreeSet<Word>> getDictionaries() {
        return dictionaries;
    }

    public Dictionary(Map<String, TreeSet<Word>> dictionaries) {
        this.dictionaries = dictionaries;
    }
    /*
    Method that adds a WORD object to the set of words of the dictionary with the given LANGUAGE
    */
    boolean addWord(Word word, String language){
        TreeSet<Word> words = dictionaries.get(language);
        Gson gson = new Gson();
        String str1 = gson.toJson(word); //generate metadata for the word that needs to be added

        for(Word w : words){
            String str2 = gson.toJson(w); //generate metadata for an existing word of the dictionary

            if (str1.equals(str2)) return false; //check if metadata of the words are identical
        }
        return words.add(word);
    }

    /*
    Method that removes the word from the dictionary if it exists
    */
    boolean removeWord(String word, String language){
        TreeSet<Word> words = dictionaries.get(language);

        for(Word w : words){
            if(word.equals(w.getWord())) return words.remove(w);
        }
        return false;
    }

    /*
    Method that adds a definition object for the list of definitions of the given WORD from the dictionary that has
    the given LANGUAGE
    */
    boolean addDefinitionForWord(String word, String language, Definition definition){
        TreeSet<Word> words = dictionaries.get(language);

        for(Word w : words){ //look for the given word in the set of words
            if (w.getWord().equals(word)) {
                for (Definition def : w.getDefinitions()) { //check if the definition doesn't already exist
                    if(def.getDict().equals(definition.getDict())) return false;
                }
                return w.getDefinitions().add(definition);
            }
        }
        return false;
    }

    /*
    Method that removes a definition for a given WORD that should be found in the dictionary with the given LANGUAGE
    */
    boolean removeDefinition(String word, String language, String dictionary){
        TreeSet<Word> words = dictionaries.get(language);

        for(Word w : words){ //look for the word
            if(word.equals(w.getWord()))
                for(Definition def : w.getDefinitions()){ //look for the requested definition and remove it if found
                    if(dictionary.equals(def.getDict())) return w.getDefinitions().remove(def);
                }
        }
        return false;
    }

    /*
    Method that translates a word from a language to another
    */
    String translateWord(String word, String fromLanguage, String toLanguage){
        TreeSet<Word> wordsFromLanguage = dictionaries.get(fromLanguage);
        TreeSet<Word> wordsToLanguage = dictionaries.get(toLanguage);

        for(Word fromLanguageWord : wordsFromLanguage){ //look for the word in fromLanguage dictionary
            if (word.equals(fromLanguageWord.getWord())) {
                for (Word toLanguageWord : wordsToLanguage) { //look for the word in toLanguage dictionary
                    // check if word_en string values are the same and return translation if they are
                    if (fromLanguageWord.getWord_en().equals(toLanguageWord.getWord_en()))
                        return toLanguageWord.getWord(); //
                }
                return "Could not find the translation in " + toLanguage + " dictionary";
            }
        }
        return "Could not find the word in " + fromLanguage + " dictionary";
    }

    /*
    Method that translates a sentence from a language to another
    */
    String translateSentence(String sentence, String fromLanguage, String toLanguage){
        String[] splitter = sentence.split(" ");
        StringBuilder translatedSentence = new StringBuilder();
        for(String word : splitter){ //translate word by word using the translateWord method
            //if the word couldn't be translated then leave it in the initial form(not translated)
            if(translateWord(word, fromLanguage, toLanguage).startsWith("Could not"))
                translatedSentence.append(word).append(' ');
            else
                translatedSentence.append(translateWord(word, fromLanguage, toLanguage)).append(' ');
        }
        return translatedSentence.toString();
    }

    /*
    Method that translates a language from a language to another in 3 alternatives
    */
    ArrayList<String> translateSentences(String sentence, String fromLanguage, String toLanguage){
        ArrayList<String> translatedSentences = new ArrayList<>();
        TreeSet<Word> fromLanguageWords = dictionaries.get(fromLanguage);
        TreeSet<Word> toLanguageWords = dictionaries.get(toLanguage);

        //spli the translated sentence obtained by using translateSentence method
        String[] splitter = translateSentence(sentence, fromLanguage, toLanguage).split(" ");

        //variable to track the count of already created translation alternatives
        int count = 0;
        for(int i = 0; i < splitter.length; i++){ //for every word in the translated sentence
            for(Word toLanguageWord : toLanguageWords){
                if(splitter[i].equals(toLanguageWord.getWord()))
                    for(Definition definition : toLanguageWord.getDefinitions()){
                        //need synonyms to get different word alternatives
                        if (definition.getDictType().equals("synonyms")){
                            for(String text : definition.getText()){ //replace the word with his synonyms
                                splitter[i] = text;
                                translatedSentences.add(String.join(" ", splitter));
                                if(count++ == 3) return translatedSentences;
                            }
                        }
                    }
            }
        }
        return null;
    }

    /*
    Method that returns the list of definitions for the given WORD from dictionary with the given LANGUAGE
    */
    ArrayList<Definition> getDefinitionsForWord(String word, String language){

        TreeSet<Word> words = dictionaries.get(language);
        ArrayList<Definition> definitions;

        for(Word w : words){
            if(w.getWord().equals(word)) {
                definitions = w.getDefinitions();
                //sort method will sort the definitions by year because the compareTo method from comparable
                //(in class Definition) was overridden to process only the year
                Collections.sort(definitions);
                return definitions;
            }
        }
        return null;
    }

    /*
    Method that exports the dictionary with the given LANGUAGE in json format
    The words in the dictionary are sorted alphabetically(overridden the compareTo method in Word class and the
    words are stocked in TreeSet, so they are automatically sorted)
    Their definitions will be sorted by year(in this method)
    */
    void exportDictionary(String language){
        TreeSet<Word> words = dictionaries.get(language);
        //if there is no dictionary with the given language
        if(words == null){
            try {
                FileWriter fileWriter = new FileWriter(
                        "src\\main\\java\\output\\" + language +
                                "DictionaryExport.out");
                fileWriter.write("No such dictionary");
                fileWriter.close();
                return;
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        //sort every word's definitions by their year
        for(Word word : words){
            Collections.sort(word.getDefinitions());
        }
        //export the dictionary in json format
        try {
            FileWriter fileWriter = new FileWriter(
                    "src\\main\\java\\output\\" + language + "DictionaryExport.out");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(words));
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
