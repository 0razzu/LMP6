package error;


public class JcfException extends RuntimeException {
    private JcfErrorCode errorCode;
    
    
    public JcfException(JcfErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public JcfErrorCode getErrorCode() {
        return errorCode;
    }
}
