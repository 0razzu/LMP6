package model;


import error.JcfErrorCode;
import error.JcfException;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.PERSON_EXISTS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testDelPerson() {
        PhoneBook phoneBook = new PhoneBook();
        
        phoneBook.addPerson(person1);
        phoneBook.addPerson(person2);
        phoneBook.delPerson(person1);
        
        Set<Human> people = phoneBook.getPeople();
        
        assertTrue(!people.contains(person1) && people.contains(person2));
        
        try {
            phoneBook.delPerson(person1);
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.PERSON_NOT_FOUND, e.getErrorCode());
        }
        
        try {
            List<String> numbers = phoneBook.getNumbers(person1);
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.PERSON_NOT_FOUND, e.getErrorCode());
        }
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
                () -> assertEquals(person1, phoneBook.getPerson(phone1)),
                () -> assertEquals(Arrays.asList(phone2, phone3), phoneBook.getNumbers(person2)),
                () -> assertEquals(person2, phoneBook.getPerson(phone2)),
                () -> assertEquals(person2, phoneBook.getPerson(phone2))
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
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.NULL_PHONE_NUMBER, e.getErrorCode());
        }
        
        try {
            phoneBook.addPhoneNumber(person1, "");
            fail("\"\"");
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.NULL_PHONE_NUMBER, e.getErrorCode());
        }
        
        try {
            phoneBook.addPhoneNumber(person1, phone3);
            fail("existing phone number");
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.PHONE_NUMBER_EXISTS, e.getErrorCode());
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
                () -> assertEquals(Collections.singletonList(phone3), phoneBook.getNumbers(person2))
        );
        
        try {
            Human person = phoneBook.getPerson(phone1);
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.PERSON_NOT_FOUND, e.getErrorCode());
        }
    }
    
    
    @Test
    void testDelPhoneNumberException() {
        PhoneBook phoneBook = new PhoneBook();
        
        phoneBook.addPhoneNumber(person1, phone1);
        phoneBook.addPhoneNumber(person2, phone2);
        phoneBook.addPhoneNumber(person2, phone3);
        phoneBook.addPhoneNumber(person3, phone4);
        
        phoneBook.delPhoneNumber(person1, phone1);
        phoneBook.delPhoneNumber(person2, phone2);
        
        try {
            phoneBook.delPhoneNumber(person4, phone1);
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.PERSON_NOT_FOUND, e.getErrorCode());
        }
        
        try {
            phoneBook.delPhoneNumber(person2, phone2);
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.PHONE_NUMBER_NOT_FOUND, e.getErrorCode());
        }
        
        try {
            phoneBook.delPhoneNumber(person2, phone4);
        } catch (JcfException e) {
            assertEquals(JcfErrorCode.PHONE_NUMBER_NOT_FOUND, e.getErrorCode());
        }
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
                () -> assertEquals(student1, phoneBook4.getPerson(phone7))
        );
    }
}
