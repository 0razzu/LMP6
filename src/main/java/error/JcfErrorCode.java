package error;


public enum JcfErrorCode {
    NULL_FIRST_NAME("A first name cannot be null or empty"),
    NULL_SECOND_NAME("A second name cannot be null or empty"),
    NULL_DEPARTMENT("A department name cannot be null or empty"),
    NULL_PHONE_NUMBER("A phone number cannot be null or empty"),
    NEGATIVE_AGE("Age cannot be negative"),
    PERSON_EXISTS("This person is already in the phone book"),
    PERSON_NOT_FOUND("This person is not in the phone book"),
    PHONE_NUMBER_EXISTS("This number is already in the phone book"),
    PHONE_NUMBER_NOT_FOUND("Could not find this phone number");
    
    
    private final String errorString;
    
    
    JcfErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}
