package service;


import model.Data;

import java.util.LinkedList;
import java.util.List;


public class DataDemo {
    public static List<Integer> getAll(Data data) {
        List<Integer> all = new LinkedList<>();
        
        for (Integer e: data)
            all.add(e);
        
        return all;
    }
}
