package javax.ws.rs;

/* loaded from: classes.dex */
public class ProcessingException extends RuntimeException {
    private static final long serialVersionUID = -4232431597816056514L;

    public ProcessingException(Throwable th) {
        super(th);
    }

    public ProcessingException(String str, Throwable th) {
        super(str, th);
    }

    public ProcessingException(String str) {
        super(str);
    }
}
