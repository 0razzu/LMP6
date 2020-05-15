package service;


import model.Human;
import model.Student;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestCollectionsDemo {
    @Test
    void testCountStringsStartingWithChar() {
        List<String> list = Arrays.asList("Абв", "Абв123", "1", "STU", "Амл", "124r32", "skj");
        
        assertAll(
                () -> assertEquals(3, CollectionsDemo.countStringsStartingWithChar(list, 'А')),
                () -> assertEquals(2, CollectionsDemo.countStringsStartingWithChar(list, '1')),
                () -> assertEquals(1, CollectionsDemo.countStringsStartingWithChar(list, 's'))
        );
    }
    
    
    @Test
    void testGetPeopleWithPersonSecondName() {
        Human person1 = new Human("Александр", "Алексеевич", "Иванов", 20);
        Human person2 = new Human("Catherina", "Black", 19);
        Human person3 = new Human("Полина", "Артёмовна", "Петрова", 23);
        Human person4 = new Human("Евгений", "Алексеевич", "Иванов", 16);
        Student student1 = new Student("Анастасия", "Андреевна", "Петрова", 17, "Химический");
        
        List<Human> people = Arrays.asList(person1, person2, person4, student1);
        
        assertAll(
                () -> assertEquals(Arrays.asList(person1, person4),
                        CollectionsDemo.getPeopleWithPersonSecondName(people, person1)),
                () -> assertEquals(Collections.singletonList(student1),
                        CollectionsDemo.getPeopleWithPersonSecondName(people, person3))
        );
    }
}
