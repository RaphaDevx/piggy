package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class ForbiddenException extends ClientErrorException {
    private static final long serialVersionUID = -2740045367479165061L;

    public ForbiddenException() {
        super(Response.Status.FORBIDDEN);
    }

    public ForbiddenException(String str) {
        super(str, Response.Status.FORBIDDEN);
    }

    public ForbiddenException(Response response) {
        super(validate(response, Response.Status.FORBIDDEN));
    }

    public ForbiddenException(String str, Response response) {
        super(str, validate(response, Response.Status.FORBIDDEN));
    }

    public ForbiddenException(Throwable th) {
        super(Response.Status.FORBIDDEN, th);
    }

    public ForbiddenException(String str, Throwable th) {
        super(str, Response.Status.FORBIDDEN, th);
    }

    public ForbiddenException(Response response, Throwable th) {
        super(validate(response, Response.Status.FORBIDDEN), th);
    }

    public ForbiddenException(String str, Response response, Throwable th) {
        super(str, validate(response, Response.Status.FORBIDDEN), th);
    }
}
