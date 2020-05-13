package service;


import model.Human;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    
    
    public static List<Set<Integer>> getNotIntersecting(List<Set<Integer>> sets, Set<Integer> set) {
        List<Set<Integer>> notIntersecting = new ArrayList<>(sets.size());
        
        for (Set<Integer> s: sets) {
            Set<Integer> intersection = new HashSet<>(s);
            intersection.retainAll(set);
            
            if (intersection.size() == 0)
                notIntersecting.add(s);
        }
        
        return notIntersecting;
    }
}
