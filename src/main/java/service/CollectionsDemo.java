package service;


import model.Human;

import java.util.ArrayList;
import java.util.List;


public class CollectionsDemo {
    public static int countStringsStartingWithChar(List<String> strings, char c) {
        int counter = 0;
        
        for (String str: strings)
            if (str.length() > 0 && str.charAt(0) == c)
                counter++;
        
        return counter;
    }
    
    
    public static List<Human> getPeopleWithPersonSecondName(List<Human> people, Human person) {
        List<Human> peopleWithPersonSecondName = new ArrayList<>(people);
        String secondName = person.getSecondName();
        
        for (Human e: people)
            if (e.getSecondName().equals(secondName))
                peopleWithPersonSecondName.add(e);
        
        return peopleWithPersonSecondName;
    }
    
    
    public static List<Human> getListWithoutPerson(List<Human> people, Human person) {
        List<Human> listWithoutPerson = new ArrayList<>(people);
        
        listWithoutPerson.removeIf(e -> e.equals(person));
        
        return listWithoutPerson;
    }
}
