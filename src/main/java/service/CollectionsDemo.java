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
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Зачем выделять под список однофамильцев список с размером равным исходному?
     Очевидно же, что однофамильцев меньше.
    */
    // fixed
    public static List<Human> getPeopleWithPersonSecondName(List<Human> people, Human person) {
        List<Human> peopleWithPersonSecondName = new ArrayList<>();
        String secondName = person.getSecondName();
        
        for (Human e: people)
            if (e.getSecondName().equals(secondName))
                peopleWithPersonSecondName.add(e);
        
        return peopleWithPersonSecondName;
    }
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Не работает. Из задания "При изменении элементов входного списка элементы выходного изменяться не должны".
     Тест поправил.
    */
    // fixed
    public static List<Human> getListWithoutPerson(List<Human> people, Human person) throws CloneNotSupportedException {
        List<Human> listWithoutPerson = new ArrayList<>();
        
        for (Human p: people)
            if (!p.equals(person))
                listWithoutPerson.add(p.clone());
        
        return listWithoutPerson;
    }
    
    
    public static List<Set<Integer>> getNotIntersecting(List<Set<Integer>> sets, Set<Integer> set) {
        List<Set<Integer>> notIntersecting = new ArrayList<>();
        
        for (Set<Integer> s: sets) {
            Set<Integer> intersection = new HashSet<>(s);
            intersection.retainAll(set);
            
            if (intersection.size() == 0)
                notIntersecting.add(s);
        }
        
        return notIntersecting;
    }
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Тоже не очевидно, что размер результата будет сравним с исходниным списком.
     Возрастную пирамиду видели? Лет до 60 люди достаточно плотно распредлены.
    */
    // fixed
    public static <T extends Human> Set<T> getMaxAged(List<T> people) {
        Set<T> maxAged = new HashSet<>();
        int maxAge = 0;
        
        for (T e: people) {
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
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Не работает. см. тест.
    */
    // fixed
    public static <T extends Human> List<T> getSortedListOfPeople(Set<T> people) {
        Set<T> sortedSetOfPeople = new TreeSet<>(new FullNameHumanComparator<>());
        
        sortedSetOfPeople.addAll(people);
        
        return new ArrayList<>(sortedSetOfPeople);
    }
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Не работает. См. тест.
    */
    // fixed
    public static Set<Human> filterPeopleByIds(Map<Integer, Human> people, Set<Integer> ids) {
        Set<Human> filteredSet = new HashSet<>();
        
        for (Integer id: ids) {
            Human person = people.get(id);
            
            if (person != null)
                filteredSet.add(person);
        }
        
        return filteredSet;
    }
    
    
    public static List<Integer> getAgedOver18Ids(Map<Integer, Human> people) {
        List<Integer> agedOver18 = new ArrayList<>();
        
        for (Integer id: people.keySet())
            if (people.get(id).getAge() >= 18)
                agedOver18.add(id);
        
        return agedOver18;
    }
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Еще есть набор ключ-значение people.entrySet()
     Экономия скобочек как-нибудь вас накажет - я не видел еще ни одного руководства по стилю,
     где поощряется ваш стиль )

     Вот стиль гугля https://google.github.io/styleguide/javaguide.html#s4.1-braces
    */
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
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Не работает по тем же причинам, что и 6-ое задание.
    */
    // fixed
    public static Map<Integer, Map<Character, List<Human>>> getPeopleByAgeAndSecondNameFirstCharacter(Set<Human> people) {
        Map<Integer, List<Human>> peopleByAges = getPeopleByAges(people);
        Map<Integer, Map<Character, List<Human>>> peopleByAgeAndSecondNameFirstCharacter =
                new HashMap<>(peopleByAges.size());
        /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
         Компаратор понадобится только в конце процедуры. Зачем вы его объявляете здесь?
        */
        // fixed
        
        for (Integer age: peopleByAges.keySet()) {
            List<Human> peopleOfCurrAge = peopleByAges.get(age);

            /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
             бессмысленная проверка, срабатывающая всегда
            */
            // fixed
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
    
        Comparator<Human> comparator = new FullNameHumanComparator<>().reversed();
        
        for (Integer age: peopleByAgeAndSecondNameFirstCharacter.keySet()) {
            Map<Character, List<model.Human>> peopleOfCurrAge = peopleByAgeAndSecondNameFirstCharacter.get(age);
            
            for (Character character: peopleByAgeAndSecondNameFirstCharacter.get(age).keySet())
                peopleOfCurrAge.get(character).sort(comparator);
        }
        
        return peopleByAgeAndSecondNameFirstCharacter;
    }
}
