package model;


import org.junit.jupiter.api.Test;

import java.util.*;

import static error.IllegalArgumentMessage.NULL_DATA_GROUPS;
import static error.IllegalArgumentMessage.NULL_DATA_NAME;
import static org.junit.jupiter.api.Assertions.*;


public class TestData {
    @Test
    void testData() {
        Data data0 = new Data("Empty data set");
        Data data1 = new Data("Some data set", new Group(23, 2), new Group(1, 5, 6, -7));
        
        assertAll(
                () -> assertEquals("Empty data set", data0.getName()),
                () -> assertEquals(0, data0.size()),
                () -> assertArrayEquals(new Group[]{}, data0.getGroups()),
                () -> assertEquals("Some data set", data1.getName()),
                () -> assertEquals(2, data1.size()),
                () -> assertArrayEquals(new Group[]{new Group(23, 2), new Group(1, 5, 6, -7)}, data1.getGroups())
        );
    }
    
    
    @Test
    void testDataException() {
        try {
            Data data1 = new Data("");
            fail("data1");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_DATA_NAME, e.getMessage());
        }
        
        try {
            Data data2 = new Data(null);
            fail("data2");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_DATA_NAME, e.getMessage());
        }
        
        try {
            Data data3 = new Data("Null data set", (Group[]) null);
            fail("data3");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_DATA_GROUPS, e.getMessage());
        }
        
        try {
            Data data4 = new Data("Some data set", new Group(5, 5), null, new Group(1));
            fail("data4");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_DATA_GROUPS, e.getMessage());
        }
    }
    
    
    @Test
    void testIterator1() {
        Data data = new Data("Some data", new Group(3, 2, 4, -5), new Group(1, 0, 6, -7, 11));
        List<Integer> expected = Arrays.asList(2, 4, -5, 0, 6, -7, 11);
        List<Integer> actual = new ArrayList<>(7);
        
        for (Integer e: data)
            actual.add(e);
        
        assertEquals(expected, actual);
    }
    
    
    @Test
    void testIterator2() {
        Data data = new Data("Some data", new Group(2, 4, -8), new Group(11, 0, 2));
        List<Integer> expected = Arrays.asList(4, -8, 0, 2);
        List<Integer> actual = new ArrayList<>(4);
        Iterator<Integer> iterator = data.iterator();
        
        assertTrue(iterator.hasNext());
        
        while (iterator.hasNext())
            actual.add(iterator.next());
        
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertThrows(NoSuchElementException.class, iterator::next)
        );
    }
    
    
    @Test
    void testIterator3() {
        Data data = new Data("Some data", new Group(3));
        
        assertAll(
                () -> assertFalse(data.iterator().hasNext()),
                () -> assertThrows(NoSuchElementException.class, data.iterator()::next)
        );
    }
    
    
    @Test
    void testEquals() {
        Data data1 = new Data("Some data set", new Group(1, 2), new Group(2, 5, 6, -7));
        Data data2 = new Data("Some data set", new Group(1, 2), new Group(2, 5, 6, -7));
        Data data3 = new Data("Another data set", new Group(1, 2), new Group(2, 5, 6, -7));
        Data data4 = new Data("Some data set", new Group(2, 5, 6, -7));
        Data data5 = new Data("Some data set", new Group(1, 2), new Group(2, 0, 6, -7));
        
        assertAll(
                () -> assertEquals(data1, data1),
                () -> assertEquals(data1, data2),
                () -> assertEquals(data2, data1),
                () -> assertNotEquals(data1, ""),
                () -> assertNotEquals(data1, data3),
                () -> assertNotEquals(data1, data4),
                () -> assertNotEquals(data1, data5)
        );
    }
}
