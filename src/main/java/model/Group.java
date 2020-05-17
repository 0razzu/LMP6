package model;


import error.JcfErrorCode;
import error.JcfException;


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
}
