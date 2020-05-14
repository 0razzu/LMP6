package service;


import model.Human;
import util.FullNameHumanComparator;

import java.util.*;


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
    
    
    public static List<Human> getMaxAged(List<Human> people) {
        List<Human> maxAged = new ArrayList<>(people.size());
        int maxAge = 0;
        
        for (Human e: people) {
            int age = e.getAge();
            
            if (age == maxAge)
                maxAged.add(e);
            
            else if (age > maxAge) {
                maxAge = age;
                maxAged.clear();
                maxAged.add(e);
            }
        }
        
        return maxAged;
    }
    
    
    public static List<Human> getSortedListOfPeople(Set<Human> people) {
        Set<Human> sortedSetOfPeople = new TreeSet<>(new FullNameHumanComparator<>());
        
        sortedSetOfPeople.addAll(people);
        
        return new ArrayList<>(sortedSetOfPeople);
    }
    
    
    public static Set<Human> filterPeopleByIds(Map<Integer, Human> people, Set<Integer> ids) {
        Set<Human> filteredSet = new HashSet<>();
        
        for (Integer id: ids)
            filteredSet.add(people.get(id));
        
        return filteredSet;
    }
    
    
    public static List<Integer> getAgedOver18Ids(Map<Integer, Human> people) {
        List<Integer> agedOver18 = new ArrayList<>(people.size());
        
        for (Integer id: people.keySet())
            if (people.get(id).getAge() >= 18)
                agedOver18.add(id);
        
        return agedOver18;
    }
    
    
    public static Map<Integer, Integer> getAges(Map<Integer, Human> people) {
        Map<Integer, Integer> ages = new HashMap<>(people.size());
        
        for (Integer id: people.keySet())
            ages.put(id, people.get(id).getAge());
        
        return ages;
    }
    
    
    public static Map<Integer, List<Human>> getPeopleByAges(Set<Human> people) {
        Map<Integer, List<Human>> peopleByAges = new HashMap<>();
        
        for (Human person: people) {
            int age = person.getAge();
            
            if (peopleByAges.containsKey(age))
                peopleByAges.get(age).add(person);
            
            else {
                List<Human> list = new LinkedList<>();
                
                list.add(person);
                peopleByAges.put(age, list);
            }
        }
        
        return peopleByAges;
    }
    
    
    public static Map<Integer, Map<Character, List<Human>>> getPeopleByAgeAndSecondNameFirstCharacter(Set<Human> people) {
        Map<Integer, List<Human>> peopleByAges = getPeopleByAges(people);
        Map<Integer, Map<Character, List<Human>>> peopleByAgeAndSecondNameFirstCharacter =
                new HashMap<>(peopleByAges.size());
        Comparator<Human> comparator = new FullNameHumanComparator<>().reversed();
        
        for (Integer age: peopleByAges.keySet()) {
            List<Human> peopleOfCurrAge = peopleByAges.get(age);
            
            if (!peopleByAgeAndSecondNameFirstCharacter.containsKey(age))
                peopleByAgeAndSecondNameFirstCharacter.put(age, new HashMap<>());
            
            for (Human person: peopleOfCurrAge) {
                Map<Character, List<Human>> characterToPeopleMap = peopleByAgeAndSecondNameFirstCharacter.get(age);
                char firstCharacter = person.getSecondName().charAt(0);
                
                if (characterToPeopleMap.containsKey(firstCharacter))
                    characterToPeopleMap.get(firstCharacter).add(person);
                
                else {
                    List<Human> list = new LinkedList<>();
                    list.add(person);
                    characterToPeopleMap.put(firstCharacter, list);
                }
            }
        }
        
        for (Integer age: peopleByAgeAndSecondNameFirstCharacter.keySet()) {
            Map<Character, List<model.Human>> peopleOfCurrAge = peopleByAgeAndSecondNameFirstCharacter.get(age);
            
            for (Character character: peopleByAgeAndSecondNameFirstCharacter.get(age).keySet())
                peopleOfCurrAge.get(character).sort(comparator);
        }
        
        return peopleByAgeAndSecondNameFirstCharacter;
    }
}
