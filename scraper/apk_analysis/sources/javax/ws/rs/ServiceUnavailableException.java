package javax.ws.rs;

import java.util.Date;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.RuntimeDelegate;

/* loaded from: classes.dex */
public class ServiceUnavailableException extends ServerErrorException {
    private static final long serialVersionUID = 3821068205617492633L;

    public ServiceUnavailableException() {
        super(Response.status(Response.Status.SERVICE_UNAVAILABLE).build());
    }

    public ServiceUnavailableException(String str) {
        super(str, Response.status(Response.Status.SERVICE_UNAVAILABLE).build());
    }

    public ServiceUnavailableException(Long l) {
        super(Response.status(Response.Status.SERVICE_UNAVAILABLE).header(HttpHeaders.RETRY_AFTER, l).build());
    }

    public ServiceUnavailableException(String str, Long l) {
        super(str, Response.status(Response.Status.SERVICE_UNAVAILABLE).header(HttpHeaders.RETRY_AFTER, l).build());
    }

    public ServiceUnavailableException(Date date) {
        super(Response.status(Response.Status.SERVICE_UNAVAILABLE).header(HttpHeaders.RETRY_AFTER, date).build());
    }

    public ServiceUnavailableException(String str, Date date) {
        super(str, Response.status(Response.Status.SERVICE_UNAVAILABLE).header(HttpHeaders.RETRY_AFTER, date).build());
    }

    public ServiceUnavailableException(Response response) {
        super(validate(response, Response.Status.SERVICE_UNAVAILABLE));
    }

    public ServiceUnavailableException(String str, Response response) {
        super(str, validate(response, Response.Status.SERVICE_UNAVAILABLE));
    }

    public ServiceUnavailableException(Date date, Throwable th) {
        super(Response.status(Response.Status.SERVICE_UNAVAILABLE).header(HttpHeaders.RETRY_AFTER, date).build(), th);
    }

    public ServiceUnavailableException(String str, Date date, Throwable th) {
        super(str, Response.status(Response.Status.SERVICE_UNAVAILABLE).header(HttpHeaders.RETRY_AFTER, date).build(), th);
    }

    public ServiceUnavailableException(Long l, Throwable th) {
        super(Response.status(Response.Status.SERVICE_UNAVAILABLE).header(HttpHeaders.RETRY_AFTER, l).build(), th);
    }

    public ServiceUnavailableException(String str, Long l, Throwable th) {
        super(str, Response.status(Response.Status.SERVICE_UNAVAILABLE).header(HttpHeaders.RETRY_AFTER, l).build(), th);
    }

    public ServiceUnavailableException(Response response, Throwable th) {
        super(validate(response, Response.Status.SERVICE_UNAVAILABLE), th);
    }

    public ServiceUnavailableException(String str, Response response, Throwable th) {
        super(str, validate(response, Response.Status.SERVICE_UNAVAILABLE), th);
    }

    public boolean hasRetryAfter() {
        return getResponse().getHeaders().containsKey(HttpHeaders.RETRY_AFTER);
    }

    public Date getRetryTime(Date date) {
        String headerString = getResponse().getHeaderString(HttpHeaders.RETRY_AFTER);
        if (headerString == null) {
            return null;
        }
        try {
            long parseLong = Long.parseLong(headerString);
            Long valueOf = Long.valueOf(parseLong);
            long time = date.getTime();
            valueOf.getClass();
            return new Date(time + (parseLong * 1000));
        } catch (NumberFormatException unused) {
            return (Date) RuntimeDelegate.getInstance().createHeaderDelegate(Date.class).fromString(headerString);
        }
    }
}
