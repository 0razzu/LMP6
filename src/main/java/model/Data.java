package model;


import error.JcfErrorCode;
import error.JcfException;
import util.DataIterator;

import java.util.Iterator;


public class Data implements Iterable<Integer> {
    private String name;
    private Group[] groups;
    
    
    public Data(String name, Group... groups) {
        setName(name);
        setGroups(groups);
    }
    
    
    public void setName(String name) {
        if (name == null || name.length() == 0)
            throw new JcfException(JcfErrorCode.NULL_DATA_NAME);
        
        this.name = name;
    }
    
    
    public void setGroups(Group... groups) {
        if (groups == null)
            throw new JcfException(JcfErrorCode.NULL_DATA_GROUPS);
        
        for (Group group: groups)
            if (group == null)
                throw new JcfException(JcfErrorCode.NULL_DATA_GROUPS);
        
        this.groups = groups;
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public Group[] getGroups() {
        return groups;
    }
    
    
    public int size() {
        return groups.length;
    }
    
    
    @Override
    public Iterator<Integer> iterator() {
        return new DataIterator(this);
    }
}
