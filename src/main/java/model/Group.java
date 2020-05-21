package model;


import error.JcfErrorCode;
import error.JcfException;

import java.util.Arrays;
import java.util.Objects;


public class Group {
    private int id;
    private int[] data;
    
    
    public Group(int id, int... data) {
        setId(id);
        setData(data);
    }
    
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    public void setData(int... data) {
        /* Филиппов А.В. 21.05.2020 Комментарий не удалять.
         на некорректные аргументы обычно кидают IllegalArgumentException
        */
        if (data == null)
            throw new JcfException(JcfErrorCode.NULL_GROUP_DATA);
        
        this.data = data;
    }
    
    
    public int getId() {
        return id;
    }
    
    
    public int[] getData() {
        return data;
    }
    
    
    public int size() {
        return data.length;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return id == group.id &&
                Arrays.equals(data, group.data);
    }
    
    
    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
