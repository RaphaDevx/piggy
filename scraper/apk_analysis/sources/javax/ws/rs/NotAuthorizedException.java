package javax.ws.rs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class NotAuthorizedException extends ClientErrorException {
    private static final long serialVersionUID = -3156040750581929702L;
    private transient List<Object> challenges;

    public NotAuthorizedException(Object obj, Object... objArr) {
        super(createUnauthorizedResponse(obj, objArr));
        this.challenges = cacheChallenges(obj, objArr);
    }

    public NotAuthorizedException(String str, Object obj, Object... objArr) {
        super(str, createUnauthorizedResponse(obj, objArr));
        this.challenges = cacheChallenges(obj, objArr);
    }

    public NotAuthorizedException(Response response) {
        super(validate(response, Response.Status.UNAUTHORIZED));
    }

    public NotAuthorizedException(String str, Response response) {
        super(str, validate(response, Response.Status.UNAUTHORIZED));
    }

    public NotAuthorizedException(Throwable th, Object obj, Object... objArr) {
        super(createUnauthorizedResponse(obj, objArr), th);
        this.challenges = cacheChallenges(obj, objArr);
    }

    public NotAuthorizedException(String str, Throwable th, Object obj, Object... objArr) {
        super(str, createUnauthorizedResponse(obj, objArr), th);
        this.challenges = cacheChallenges(obj, objArr);
    }

    public NotAuthorizedException(Response response, Throwable th) {
        super(validate(response, Response.Status.UNAUTHORIZED), th);
    }

    public NotAuthorizedException(String str, Response response, Throwable th) {
        super(str, validate(response, Response.Status.UNAUTHORIZED), th);
    }

    public List<Object> getChallenges() {
        if (this.challenges == null) {
            this.challenges = (List) getResponse().getHeaders().get(HttpHeaders.WWW_AUTHENTICATE);
        }
        return this.challenges;
    }

    private static Response createUnauthorizedResponse(Object obj, Object[] objArr) {
        if (obj == null) {
            throw new NullPointerException("Primary challenge parameter must not be null.");
        }
        Response.ResponseBuilder header = Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, obj);
        if (objArr != null) {
            for (Object obj2 : objArr) {
                header.header(HttpHeaders.WWW_AUTHENTICATE, obj2);
            }
        }
        return header.build();
    }

    private static List<Object> cacheChallenges(Object obj, Object[] objArr) {
        ArrayList arrayList = new ArrayList((objArr == null ? 0 : objArr.length) + 1);
        arrayList.add(obj);
        if (objArr != null) {
            arrayList.addAll(Arrays.asList(objArr));
        }
        return Collections.unmodifiableList(arrayList);
    }
}
