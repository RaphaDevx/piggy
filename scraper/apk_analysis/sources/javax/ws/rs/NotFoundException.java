package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class NotFoundException extends ClientErrorException {
    private static final long serialVersionUID = -6820866117511628388L;

    public NotFoundException() {
        super(Response.Status.NOT_FOUND);
    }

    public NotFoundException(String str) {
        super(str, Response.Status.NOT_FOUND);
    }

    public NotFoundException(Response response) {
        super(validate(response, Response.Status.NOT_FOUND));
    }

    public NotFoundException(String str, Response response) {
        super(str, validate(response, Response.Status.NOT_FOUND));
    }

    public NotFoundException(Throwable th) {
        super(Response.Status.NOT_FOUND, th);
    }

    public NotFoundException(String str, Throwable th) {
        super(str, Response.Status.NOT_FOUND, th);
    }

    public NotFoundException(Response response, Throwable th) {
        super(validate(response, Response.Status.NOT_FOUND), th);
    }

    public NotFoundException(String str, Response response, Throwable th) {
        super(str, validate(response, Response.Status.NOT_FOUND), th);
    }
}
