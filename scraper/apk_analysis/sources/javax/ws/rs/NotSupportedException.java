package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class NotSupportedException extends ClientErrorException {
    private static final long serialVersionUID = -8286622745725405656L;

    public NotSupportedException() {
        super(Response.Status.UNSUPPORTED_MEDIA_TYPE);
    }

    public NotSupportedException(String str) {
        super(str, Response.Status.UNSUPPORTED_MEDIA_TYPE);
    }

    public NotSupportedException(Response response) {
        super(validate(response, Response.Status.UNSUPPORTED_MEDIA_TYPE));
    }

    public NotSupportedException(String str, Response response) {
        super(str, validate(response, Response.Status.UNSUPPORTED_MEDIA_TYPE));
    }

    public NotSupportedException(Throwable th) {
        super(Response.Status.UNSUPPORTED_MEDIA_TYPE, th);
    }

    public NotSupportedException(String str, Throwable th) {
        super(str, Response.Status.UNSUPPORTED_MEDIA_TYPE, th);
    }

    public NotSupportedException(Response response, Throwable th) {
        super(validate(response, Response.Status.UNSUPPORTED_MEDIA_TYPE), th);
    }

    public NotSupportedException(String str, Response response, Throwable th) {
        super(str, validate(response, Response.Status.UNSUPPORTED_MEDIA_TYPE), th);
    }
}
