package service;


import java.util.List;


public class CollectionsDemo {
    public static int countStringsStartingWithChar(List<String> strings, char c) {
        int counter = 0;
        
        for (String str: strings)
            if (str.length() > 0 && str.charAt(0) == c)
                counter++;
        
        return counter;
    }
}
