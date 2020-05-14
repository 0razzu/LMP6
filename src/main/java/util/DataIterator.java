package util;


import model.Data;

import java.util.Iterator;


public class DataIterator implements Iterator<Integer> {
    private Data data;
    private int i;
    private int j;
    
    
    public DataIterator(Data data) {
        this.data = data;
        i = 0;
        j = 0;
    }
    
    
    @Override
    public boolean hasNext() {
        return i < data.size();
    }
    
    
    @Override
    public Integer next() {
        while (i < data.size() && j >= data.getGroups()[i].size()) {
            i++;
            j = 0;
        }
        
        if (i >= data.size())
            return null;
        
        return data.getGroups()[i].getData()[j++];
    }
}
