package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class ServerErrorException extends WebApplicationException {
    private static final long serialVersionUID = 4730895276505569556L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ServerErrorException(Response.Status status) {
        super((Throwable) null, validate(Response.status(status).build(), Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(String str, Response.Status status) {
        super(str, (Throwable) null, validate(Response.status(status).build(), Response.Status.Family.SERVER_ERROR));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ServerErrorException(int i) {
        super((Throwable) null, validate(Response.status(i).build(), Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(String str, int i) {
        super(str, (Throwable) null, validate(Response.status(i).build(), Response.Status.Family.SERVER_ERROR));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ServerErrorException(Response response) {
        super((Throwable) null, validate(response, Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(String str, Response response) {
        super(str, (Throwable) null, validate(response, Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(Response.Status status, Throwable th) {
        super(th, validate(Response.status(status).build(), Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(String str, Response.Status status, Throwable th) {
        super(str, th, validate(Response.status(status).build(), Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(int i, Throwable th) {
        super(th, validate(Response.status(i).build(), Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(String str, int i, Throwable th) {
        super(str, th, validate(Response.status(i).build(), Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(Response response, Throwable th) {
        super(th, validate(response, Response.Status.Family.SERVER_ERROR));
    }

    public ServerErrorException(String str, Response response, Throwable th) {
        super(str, th, validate(response, Response.Status.Family.SERVER_ERROR));
    }
}
