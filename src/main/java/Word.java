import java.util.ArrayList;
import java.util.Arrays;

public class Word implements Comparable {
    private String word;
    private String word_en;
    private String type;
    private ArrayList<String> singular;
    private ArrayList<String> plural;
    private ArrayList<Definition> definitions;

    public Word(String word, String word_en, String type, ArrayList<String> singular, ArrayList<String> plural, ArrayList<Definition> definitions) {
        this.word = word;
        this.word_en = word_en;
        this.type = type;
        this.singular = singular;
        this.plural = plural;
        this.definitions = definitions;
    }

    public Word() {
    }

    public String getWord() {
        return word;
    }

    public String getWord_en() {
        return word_en;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getSingular() {
        return singular;
    }

    public ArrayList<String> getPlural() {
        return plural;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    @Override
    public int compareTo(Object o) {
        String compareWord = ((Word)o).getWord();
        String myWord = this.word;
        return myWord.compareTo(compareWord);
    }
}
