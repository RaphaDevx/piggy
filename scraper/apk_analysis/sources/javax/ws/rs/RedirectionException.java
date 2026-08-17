package javax.ws.rs;

import java.net.URI;
import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class RedirectionException extends WebApplicationException {
    private static final long serialVersionUID = -2584325408291098012L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RedirectionException(Response.Status status, URI uri) {
        super((Throwable) null, validate(Response.status(status).location(uri).build(), Response.Status.Family.REDIRECTION));
    }

    public RedirectionException(String str, Response.Status status, URI uri) {
        super(str, (Throwable) null, validate(Response.status(status).location(uri).build(), Response.Status.Family.REDIRECTION));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RedirectionException(int i, URI uri) {
        super((Throwable) null, validate(Response.status(i).location(uri).build(), Response.Status.Family.REDIRECTION));
    }

    public RedirectionException(String str, int i, URI uri) {
        super(str, (Throwable) null, validate(Response.status(i).location(uri).build(), Response.Status.Family.REDIRECTION));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RedirectionException(Response response) {
        super((Throwable) null, validate(response, Response.Status.Family.REDIRECTION));
    }

    public RedirectionException(String str, Response response) {
        super(str, (Throwable) null, validate(response, Response.Status.Family.REDIRECTION));
    }

    public URI getLocation() {
        return getResponse().getLocation();
    }
}
