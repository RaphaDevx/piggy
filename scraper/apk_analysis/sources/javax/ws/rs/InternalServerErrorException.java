package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class InternalServerErrorException extends ServerErrorException {
    private static final long serialVersionUID = -6515710697540553309L;

    public InternalServerErrorException() {
        super(Response.Status.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String str) {
        super(str, Response.Status.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(Response response) {
        super(validate(response, Response.Status.INTERNAL_SERVER_ERROR));
    }

    public InternalServerErrorException(String str, Response response) {
        super(str, validate(response, Response.Status.INTERNAL_SERVER_ERROR));
    }

    public InternalServerErrorException(Throwable th) {
        super(Response.Status.INTERNAL_SERVER_ERROR, th);
    }

    public InternalServerErrorException(String str, Throwable th) {
        super(str, Response.Status.INTERNAL_SERVER_ERROR, th);
    }

    public InternalServerErrorException(Response response, Throwable th) {
        super(validate(response, Response.Status.INTERNAL_SERVER_ERROR), th);
    }

    public InternalServerErrorException(String str, Response response, Throwable th) {
        super(str, validate(response, Response.Status.INTERNAL_SERVER_ERROR), th);
    }
}
