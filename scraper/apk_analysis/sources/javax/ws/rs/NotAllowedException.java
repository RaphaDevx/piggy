package javax.ws.rs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class NotAllowedException extends ClientErrorException {
    private static final long serialVersionUID = -586776054369626119L;

    public NotAllowedException(String str, String... strArr) {
        super(validateAllow(createNotAllowedResponse(str, strArr)));
    }

    public NotAllowedException(String str, String str2, String... strArr) {
        super(str, validateAllow(createNotAllowedResponse(str2, strArr)));
    }

    private static Response createNotAllowedResponse(String str, String... strArr) {
        Set<String> singleton;
        if (str == null) {
            throw new NullPointerException("No allowed method specified.");
        }
        if (strArr != null && strArr.length > 0) {
            singleton = new HashSet<>(strArr.length + 1);
            singleton.add(str);
            Collections.addAll(singleton, strArr);
        } else {
            singleton = Collections.singleton(str);
        }
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).allow(singleton).build();
    }

    public NotAllowedException(Response response) {
        super(validate(response, Response.Status.METHOD_NOT_ALLOWED));
    }

    public NotAllowedException(String str, Response response) {
        super(str, validate(response, Response.Status.METHOD_NOT_ALLOWED));
    }

    public NotAllowedException(Throwable th, String... strArr) {
        super(validateAllow(Response.status(Response.Status.METHOD_NOT_ALLOWED).allow(strArr).build()), th);
    }

    public NotAllowedException(String str, Throwable th, String... strArr) {
        super(str, validateAllow(Response.status(Response.Status.METHOD_NOT_ALLOWED).allow(strArr).build()), th);
    }

    public NotAllowedException(Response response, Throwable th) {
        super(validateAllow(validate(response, Response.Status.METHOD_NOT_ALLOWED)), th);
    }

    public NotAllowedException(String str, Response response, Throwable th) {
        super(str, validateAllow(validate(response, Response.Status.METHOD_NOT_ALLOWED)), th);
    }

    private static Response validateAllow(Response response) {
        if (response.getHeaders().containsKey(HttpHeaders.ALLOW)) {
            return response;
        }
        throw new IllegalArgumentException("Response does not contain required 'Allow' HTTP header.");
    }
}
