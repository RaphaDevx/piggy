package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class BadRequestException extends ClientErrorException {
    private static final long serialVersionUID = 7264647684649480265L;

    public BadRequestException() {
        super(Response.Status.BAD_REQUEST);
    }

    public BadRequestException(String str) {
        super(str, Response.Status.BAD_REQUEST);
    }

    public BadRequestException(Response response) {
        super(validate(response, Response.Status.BAD_REQUEST));
    }

    public BadRequestException(String str, Response response) {
        super(str, validate(response, Response.Status.BAD_REQUEST));
    }

    public BadRequestException(Throwable th) {
        super(Response.Status.BAD_REQUEST, th);
    }

    public BadRequestException(String str, Throwable th) {
        super(str, Response.Status.BAD_REQUEST, th);
    }

    public BadRequestException(Response response, Throwable th) {
        super(validate(response, Response.Status.BAD_REQUEST), th);
    }

    public BadRequestException(String str, Response response, Throwable th) {
        super(str, validate(response, Response.Status.BAD_REQUEST), th);
    }
}
