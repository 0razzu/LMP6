package model;


import error.JcfErrorCode;
import error.JcfException;

import java.util.*;


public class PhoneBook {
    private Map<Human, List<String>> data;
    
    
    public PhoneBook() {
        data = new HashMap<>();
    }
    
    
    public void addPerson(Human person) {
        if (data.containsKey(person))
            throw new JcfException(JcfErrorCode.PERSON_EXISTS);
        
        data.put(person, new ArrayList<>());
    }
    
    
    public void delPerson(Human person) {
        if (data.remove(person) == null)
            throw new JcfException(JcfErrorCode.PERSON_NOT_FOUND);
    }
    
    
    public void addPhoneNumber(Human person, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() == 0)
            throw new JcfException(JcfErrorCode.NULL_PHONE_NUMBER);

        /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
         Странная проверка. Один номер может принадлежать нескольким людям.
         Понятно что с айпи телефонией и мобильной связью это все менее актуально,
         но еще есть масса миниатс, у которых ограниченная емкость, поэтому номер может
         быть один на кабинет, а телефонов там будет стоять щтуки четыре.
        */
        for (List<String> numbers: data.values())
            if (numbers.contains(phoneNumber))
                throw new JcfException(JcfErrorCode.PHONE_NUMBER_EXISTS);
        
        if (!data.containsKey(person))
            addPerson(person);
        
        data.get(person).add(phoneNumber);
    }
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     Не стоит кидать исключения, когда нет исключительной ситуации.
     Вас попросили удалить номер, если удалять нечего, то можно просто выйти.
    */
    public void delPhoneNumber(Human person, String phoneNumber) {
        if (!data.containsKey(person))
            throw new JcfException(JcfErrorCode.PERSON_NOT_FOUND);
        
        if (!data.get(person).remove(phoneNumber))
            throw new JcfException(JcfErrorCode.PHONE_NUMBER_NOT_FOUND);
    }
    
    
    public Set<Human> getPeople() {
        return data.keySet();
    }
    
   /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
    Еще одно не нужное исключение. Пустой список будет более адекватным результатом.
   */
    public List<String> getNumbers(Human person) {
        if (!data.containsKey(person))
            throw new JcfException(JcfErrorCode.PERSON_NOT_FOUND);
        
        return data.get(person);
    }
    
    /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
     снова не нуное исключение. Ну и на одном номере может быть несколько человек см. выше.
    */
    public Human getPerson(String phoneNumber) {
        for (Map.Entry<Human, List<String>> entry: data.entrySet())
            for (String number: entry.getValue())
                if (number.equals(phoneNumber))
                    return entry.getKey();
        
        throw new JcfException(JcfErrorCode.PERSON_NOT_FOUND);
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
