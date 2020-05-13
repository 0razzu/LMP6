package error;


public enum JcfErrorCode {
    NULL_FIRST_NAME("A first name cannot be null or empty"),
    NULL_SECOND_NAME("A second name cannot be null or empty"),
    NEGATIVE_AGE("Age cannot be negative");
    
    
    private String errorString;
    
    
    JcfErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}
