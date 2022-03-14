import java.util.ArrayList;
import java.util.Arrays;

public class Definition implements Comparable {
    String dict;
    String dictType;
    int year;
    ArrayList<String> text;

    public Definition(String dict, String dictType, int year, ArrayList<String> text) {
        this.dict = dict;
        this.dictType = dictType;
        this.year = year;
        this.text = text;
    }

    public Definition() {
    }

    public String getDict() {
        return dict;
    }

    public String getDictType() {
        return dictType;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getText() {
        return text;
    }

    @Override
    public int compareTo(Object o) {
        int compareYear = ((Definition)o).getYear();
        int myYear = this.year;
        return myYear - compareYear;
    }
}
