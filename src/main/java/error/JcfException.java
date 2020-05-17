package error;


public class JcfException extends RuntimeException {
    private final JcfErrorCode errorCode;
    
    
    public JcfException(JcfErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public JcfErrorCode getErrorCode() {
        return errorCode;
    }
    
    
    @Override
    public String getMessage() {
        return errorCode.getErrorString();
    }
}
