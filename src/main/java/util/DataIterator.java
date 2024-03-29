package util;


import model.Data;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class DataIterator implements Iterator<Integer> {
    private final Data data;
    private int i;
    private int j;
    
    
    public DataIterator(Data data) {
        this.data = data;
        i = 0;
        j = 0;
    }
    
    
    @Override
    public boolean hasNext() {
        while (i < data.size() && j >= data.getGroups()[i].size()) {
            i++;
            j = 0;
        }
        
        return i < data.size();
    }
    
    
    @Override
    public Integer next() {
        if (hasNext())
            return data.getGroups()[i].getData()[j++];
        
        throw new NoSuchElementException();
    }
}
