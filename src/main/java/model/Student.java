package model;


import error.JcfErrorCode;
import error.JcfException;


public class Student extends Human {
    private String department;
    
    
    public Student(String firstName, String patronymicName, String secondName, int age, String department) {
        super(firstName, patronymicName, secondName, age);
        setDepartment(department);
    }
    
    
    public Student(String firstName, String secondName, int age, String department) {
        super(firstName, secondName, age);
        setDepartment(department);
    }
    
    
    public void setDepartment(String department) {
        if (department == null || department.length() == 0)
            throw new JcfException(JcfErrorCode.NULL_DEPARTMENT);
        
        this.department = department;
    }
    
    
    public String getDepartment() {
        return department;
    }
}
