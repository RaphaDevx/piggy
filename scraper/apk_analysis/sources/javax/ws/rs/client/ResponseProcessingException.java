package javax.ws.rs.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;

/* loaded from: classes3.dex */
public class ResponseProcessingException extends ProcessingException {
    private static final long serialVersionUID = -4923161617935731839L;
    private final Response response;

    public ResponseProcessingException(Response response, Throwable th) {
        super(th);
        this.response = response;
    }

    public ResponseProcessingException(Response response, String str, Throwable th) {
        super(str, th);
        this.response = response;
    }

    public ResponseProcessingException(Response response, String str) {
        super(str);
        this.response = response;
    }

    public Response getResponse() {
        return this.response;
    }
}
