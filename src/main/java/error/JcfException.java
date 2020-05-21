package error;

/* Филиппов А.В. 21.05.2020 Комментарий не удалять.
 Очень плохая идея расширять рантайм исключения. Это исключения JVM.
 Пользователи наследуют от Exception и обрабатывают исключения.

RuntimeException is the superclass of those exceptions that can be thrown during the normal
operation of the Java Virtual Machine.
RuntimeException and its subclasses are unchecked exceptions. Unchecked exceptions do not
need to be declared in a method or constructor's throws clause if they can be thrown by the
execution of the method or constructor and propagate outside the method or constructor
boundary.
*/
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
