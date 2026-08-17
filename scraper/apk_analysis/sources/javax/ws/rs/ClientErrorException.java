package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class ClientErrorException extends WebApplicationException {
    private static final long serialVersionUID = -4101970664444907990L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClientErrorException(Response.Status status) {
        super((Throwable) null, validate(Response.status(status).build(), Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(String str, Response.Status status) {
        super(str, (Throwable) null, validate(Response.status(status).build(), Response.Status.Family.CLIENT_ERROR));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClientErrorException(int i) {
        super((Throwable) null, validate(Response.status(i).build(), Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(String str, int i) {
        super(str, (Throwable) null, validate(Response.status(i).build(), Response.Status.Family.CLIENT_ERROR));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClientErrorException(Response response) {
        super((Throwable) null, validate(response, Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(String str, Response response) {
        super(str, (Throwable) null, validate(response, Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(Response.Status status, Throwable th) {
        super(th, validate(Response.status(status).build(), Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(String str, Response.Status status, Throwable th) {
        super(str, th, validate(Response.status(status).build(), Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(int i, Throwable th) {
        super(th, validate(Response.status(i).build(), Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(String str, int i, Throwable th) {
        super(str, th, validate(Response.status(i).build(), Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(Response response, Throwable th) {
        super(th, validate(response, Response.Status.Family.CLIENT_ERROR));
    }

    public ClientErrorException(String str, Response response, Throwable th) {
        super(str, th, validate(response, Response.Status.Family.CLIENT_ERROR));
    }
}
