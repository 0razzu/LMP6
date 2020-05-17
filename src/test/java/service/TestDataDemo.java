package service;


import model.Data;
import model.Group;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class TestDataDemo {
    @Test
    void testDataDemo1() {
        assertEquals(Collections.emptyList(), DataDemo.getAll(new Data("No data indeed")));
    }
    
    
    @Test
    void testDataDemo2() {
        assertThrows(NullPointerException.class, () -> DataDemo.getAll(null));
    }
    
    
    @Test
    void testDataDemo3() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6),
                DataDemo.getAll(new Data("Some data", new Group(1, 1, 2, 3), new Group(2, 4, 5), new Group(3, 6))));
    }
}
