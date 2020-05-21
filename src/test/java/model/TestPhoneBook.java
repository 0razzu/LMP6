package model;


import org.junit.jupiter.api.Test;
import util.FullNameHumanComparator;

import java.util.*;

import static error.IllegalArgumentMessage.NULL_PHONE_NUMBER;
import static error.IllegalArgumentMessage.PERSON_EXISTS;
import static org.junit.jupiter.api.Assertions.*;


public class TestPhoneBook {
    private static final Human person1 = new Human("Александр", "Алексеевич", "Ивин", 20);
    private static final Human person2 = new Human("Catherina", "Black", 20);
    private static final Human person3 = new Human("Павел", "Артёмович", "Петров", 23);
    private static final Human person4 = new Human("Евгений", "Алексеевич", "Иванов", 16);
    private static final Human person5 = new Human("Евгений", "Алексеевич", "Иванов", 15);
    private static final Human person6 = new Human("Полина", "Сергеевна", "Петрова", 15);
    private static final Student student1 = new Student("Анастасия", "Андреевна", "Петрова", 17, "Химический");
    private static final String phone1 = "+7 (932) 182-42-93";
    private static final String phone2 = "+1 (925) 800-6290";
    private static final String phone3 = "53-24-94";
    private static final String phone4 = "+7 (3812) 38-99-38,2";
    private static final String phone5 = "+49 231 56 42 73";
    private static final String phone6 = "858-633-6353";
    private static final String phone7 = "+44 20 1234 1234";
    
    
    @Test
    void testAddPerson() {
        PhoneBook phoneBook = new PhoneBook();
        
        phoneBook.addPerson(person1);
        phoneBook.addPerson(person2);
        
        Set<Human> people = phoneBook.getPeople();
        
        assertTrue(people.contains(person1) && people.contains(person2));
        
        try {
            phoneBook.addPerson(person1);
        } catch (IllegalArgumentException e) {
            assertEquals(PERSON_EXISTS, e.getMessage());
        }
    }
    
    
    @Test
    void testDelPerson() {
        PhoneBook phoneBook = new PhoneBook();
        
        phoneBook.addPerson(person1);
        phoneBook.addPerson(person2);
        phoneBook.delPerson(person1);
        
        Set<Human> people = phoneBook.getPeople();
        
        assertAll(
                () -> assertTrue(!people.contains(person1) && people.contains(person2)),
                () -> assertEquals(Collections.emptyList(), phoneBook.getNumbers(person1))
        );
    }
    
    
    @Test
    void testAddPhoneNumber() {
        PhoneBook phoneBook = new PhoneBook();
        
        phoneBook.addPerson(person1);
        phoneBook.addPhoneNumber(person1, phone1);
        phoneBook.addPhoneNumber(person2, phone2);
        phoneBook.addPhoneNumber(person2, phone3);
        
        assertAll(
                () -> assertEquals(Collections.singletonList(phone1), phoneBook.getNumbers(person1)),
                () -> assertEquals(Collections.singletonList(person1), phoneBook.getPeople(phone1)),
                () -> assertEquals(Arrays.asList(phone2, phone3), phoneBook.getNumbers(person2)),
                () -> assertEquals(Collections.singletonList(person2), phoneBook.getPeople(phone2)),
                () -> assertEquals(Collections.singletonList(person2), phoneBook.getPeople(phone3))
        );
    }
    
    
    @Test
    void testAddPhoneNumberException() {
        PhoneBook phoneBook = new PhoneBook();
        
        phoneBook.addPhoneNumber(person1, phone1);
        phoneBook.addPhoneNumber(person2, phone2);
        phoneBook.addPhoneNumber(person2, phone3);
        
        try {
            phoneBook.addPhoneNumber(person1, null);
            fail("null");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_PHONE_NUMBER, e.getMessage());
        }
        
        try {
            phoneBook.addPhoneNumber(person1, "");
            fail("\"\"");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_PHONE_NUMBER, e.getMessage());
        }
    }
    
    
    @Test
    void testDelPhoneNumber() {
        PhoneBook phoneBook = new PhoneBook();
        
        phoneBook.addPhoneNumber(person1, phone1);
        phoneBook.addPhoneNumber(person2, phone2);
        phoneBook.addPhoneNumber(person2, phone3);
        
        phoneBook.delPhoneNumber(person1, phone1);
        phoneBook.delPhoneNumber(person2, phone2);
        
        assertAll(
                () -> assertEquals(Collections.emptyList(), phoneBook.getNumbers(person1)),
                () -> assertTrue(phoneBook.getPeople().contains(person1)),
                () -> assertEquals(Collections.singletonList(phone3), phoneBook.getNumbers(person2)),
                () -> assertEquals(Collections.emptyList(), phoneBook.getPeople(phone1))
        );
    }
    
    
    @Test
    void testGetPeople() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPhoneNumber(person1, phone1);
        phoneBook.addPhoneNumber(person2, phone1);
        phoneBook.addPhoneNumber(person3, phone2);
        phoneBook.addPhoneNumber(person4, phone1);
        
        List<Human> expected = Arrays.asList(person2, person4, person1);
        List<Human> actual = phoneBook.getPeople(phone1);
        actual.sort(new FullNameHumanComparator<Human>());
        
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(Collections.singletonList(person3), phoneBook.getPeople(phone2)),
                () -> assertEquals(Collections.emptyList(), phoneBook.getPeople(phone3))
        );
    }
    
    
    @Test
    void testGetPhoneBookBySecondNamePrefix() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPhoneNumber(person1, phone1);
        phoneBook.addPhoneNumber(person2, phone2);
        phoneBook.addPhoneNumber(person3, phone3);
        phoneBook.addPhoneNumber(person4, phone4);
        phoneBook.addPhoneNumber(person5, phone5);
        phoneBook.addPerson(person6);
        phoneBook.addPhoneNumber(student1, phone6);
        phoneBook.addPhoneNumber(student1, phone7);
        
        PhoneBook phoneBook0 = phoneBook.getPhoneBookBySecondNamePrefix("Абв");
        PhoneBook phoneBook1 = phoneBook.getPhoneBookBySecondNamePrefix("Bla");
        PhoneBook phoneBook2 = phoneBook.getPhoneBookBySecondNamePrefix("Ив");
        PhoneBook phoneBook3 = phoneBook.getPhoneBookBySecondNamePrefix("Иви");
        PhoneBook phoneBook4 = phoneBook.getPhoneBookBySecondNamePrefix("Петров");
        
        assertAll(
                () -> assertEquals(Collections.emptySet(), phoneBook0.getPeople()),
                () -> assertEquals(Collections.singleton(person2), phoneBook1.getPeople()),
                () -> assertEquals(new HashSet<>(Arrays.asList(person1, person4, person5)), phoneBook2.getPeople()),
                () -> assertEquals(Collections.singleton(person1), phoneBook3.getPeople()),
                () -> assertEquals(new HashSet<>(Arrays.asList(person3, person6, student1)), phoneBook4.getPeople()),
                () -> assertEquals(Collections.emptyList(), phoneBook4.getNumbers(person6)),
                () -> assertEquals(Collections.singletonList(student1), phoneBook4.getPeople(phone7))
        );
    }
}
