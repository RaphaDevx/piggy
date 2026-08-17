package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class NotAcceptableException extends ClientErrorException {
    private static final long serialVersionUID = -1476163816796529078L;

    public NotAcceptableException() {
        super(Response.Status.NOT_ACCEPTABLE);
    }

    public NotAcceptableException(String str) {
        super(str, Response.Status.NOT_ACCEPTABLE);
    }

    public NotAcceptableException(Response response) {
        super(validate(response, Response.Status.NOT_ACCEPTABLE));
    }

    public NotAcceptableException(String str, Response response) {
        super(str, validate(response, Response.Status.NOT_ACCEPTABLE));
    }

    public NotAcceptableException(Throwable th) {
        super(Response.Status.NOT_ACCEPTABLE, th);
    }

    public NotAcceptableException(String str, Throwable th) {
        super(str, Response.Status.NOT_ACCEPTABLE, th);
    }

    public NotAcceptableException(Response response, Throwable th) {
        super(validate(response, Response.Status.NOT_ACCEPTABLE), th);
    }

    public NotAcceptableException(String str, Response response, Throwable th) {
        super(str, validate(response, Response.Status.NOT_ACCEPTABLE), th);
    }
}
