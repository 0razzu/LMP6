package model;


import error.IllegalArgumentMessage;

import java.util.*;


public class PhoneBook {
    private Map<Human, List<String>> data;
    
    
    public PhoneBook() {
        data = new HashMap<>();
    }
    
    
    public void addPerson(Human person) {
        if (data.containsKey(person))
            throw new IllegalArgumentException(IllegalArgumentMessage.PERSON_EXISTS);
        
        data.put(person, new ArrayList<>());
    }
    
    
    public void delPerson(Human person) {
        data.remove(person);
    }
    
    
    public void addPhoneNumber(Human person, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() == 0)
            throw new IllegalArgumentException(IllegalArgumentMessage.NULL_PHONE_NUMBER);

        /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
         Странная проверка. Один номер может принадлежать нескольким людям.
         Понятно что с айпи телефонией и мобильной связью это все менее актуально,
         но еще есть масса миниатс, у которых ограниченная емкость, поэтому номер может
         быть один на кабинет, а телефонов там будет стоять щтуки четыре.
        */
        // fixed
        
        if (!data.containsKey(person))
            addPerson(person);
        
        data.get(person).add(phoneNumber);
    }
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Не стоит кидать исключения, когда нет исключительной ситуации.
     Вас попросили удалить номер, если удалять нечего, то можно просто выйти.
    */
    // fixed
    public void delPhoneNumber(Human person, String phoneNumber) {
        List<String> numbers = data.get(person);
        
        if (numbers != null)
            numbers.remove(phoneNumber);
    }
    
    
    public Set<Human> getPeople() {
        return data.keySet();
    }
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Еще одно не нужное исключение. Пустой список будет более адекватным результатом.
    */
    // fixed
    public List<String> getNumbers(Human person) {
        List<String> numbers = data.get(person);
        
        return numbers == null? new ArrayList<>() : numbers;
    }
    
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     снова не нуное исключение. Ну и на одном номере может быть несколько человек см. выше.
    */
    // fixed
    public List<Human> getPeople(String phoneNumber) {
        List<Human> people = new ArrayList<>();
        
        for (Map.Entry<Human, List<String>> entry: data.entrySet())
            for (String number: entry.getValue())
                if (number.equals(phoneNumber)) {
                    people.add(entry.getKey());
                    
                    break;
                }
        
        return people;
    }
    
    
    public PhoneBook getPhoneBookBySecondNamePrefix(String prefix) {
        PhoneBook phoneBook = new PhoneBook();
        
        if (prefix != null && prefix.length() != 0)
            for (Human person: data.keySet())
                if (person.getSecondName().startsWith(prefix))
                    phoneBook.data.put(person, data.get(person));
        
        return phoneBook;
    }
}
