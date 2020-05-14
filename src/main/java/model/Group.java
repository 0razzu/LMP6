package model;


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
