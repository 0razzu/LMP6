package model;


import org.junit.jupiter.api.Test;

import static error.IllegalArgumentMessage.NULL_GROUP_DATA;
import static org.junit.jupiter.api.Assertions.*;


public class TestGroup {
    @Test
    void testGroup() {
        Group group0 = new Group(1);
        Group group1 = new Group(-249, 432, -3, 31);
        
        assertAll(
                () -> assertEquals(1, group0.getId()),
                () -> assertEquals(0, group0.size()),
                () -> assertArrayEquals(new int[]{}, group0.getData()),
                () -> assertEquals(-249, group1.getId()),
                () -> assertEquals(3, group1.size()),
                () -> assertArrayEquals(new int[]{432, -3, 31}, group1.getData())
        );
    }
    
    
    @Test
    void testGroupException() {
        try {
            Group group = new Group(3, (int[]) null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_GROUP_DATA, e.getMessage());
        }
    }
    
    
    @Test
    void testEquals() {
        Group group1 = new Group(1, 1, 2, -3);
        Group group2 = new Group(1, 1, 2, -3);
        Group group3 = new Group(0, 1, 2, -3);
        Group group4 = new Group(1);
        Group group5 = new Group(1, 1, 2, 3);
        
        assertAll(
                () -> assertEquals(group1, group1),
                () -> assertEquals(group1, group2),
                () -> assertEquals(group2, group1),
                () -> assertNotEquals(group1, ""),
                () -> assertNotEquals(group1, group3),
                () -> assertNotEquals(group1, group4),
                () -> assertNotEquals(group1, group5)
        );
    }
}
