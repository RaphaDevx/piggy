package javax.ws.rs;

import javax.ws.rs.core.Response;

/* loaded from: classes.dex */
public class WebApplicationException extends RuntimeException {
    private static final long serialVersionUID = 8273970399584007146L;
    private final Response response;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WebApplicationException() {
        this((Throwable) null, Response.Status.INTERNAL_SERVER_ERROR);
    }

    public WebApplicationException(String str) {
        this(str, (Throwable) null, Response.Status.INTERNAL_SERVER_ERROR);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WebApplicationException(Response response) {
        this((Throwable) null, response);
    }

    public WebApplicationException(String str, Response response) {
        this(str, (Throwable) null, response);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WebApplicationException(int i) {
        this((Throwable) null, i);
    }

    public WebApplicationException(String str, int i) {
        this(str, (Throwable) null, i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WebApplicationException(Response.Status status) {
        this((Throwable) null, status);
    }

    public WebApplicationException(String str, Response.Status status) {
        this(str, (Throwable) null, status);
    }

    public WebApplicationException(Throwable th) {
        this(th, Response.Status.INTERNAL_SERVER_ERROR);
    }

    public WebApplicationException(String str, Throwable th) {
        this(str, th, Response.Status.INTERNAL_SERVER_ERROR);
    }

    public WebApplicationException(Throwable th, Response response) {
        this(computeExceptionMessage(response), th, response);
    }

    public WebApplicationException(String str, Throwable th, Response response) {
        super(str, th);
        if (response == null) {
            this.response = Response.serverError().build();
        } else {
            this.response = response;
        }
    }

    private static String computeExceptionMessage(Response response) {
        Response.StatusType statusType;
        if (response != null) {
            statusType = response.getStatusInfo();
        } else {
            statusType = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return "HTTP " + statusType.getStatusCode() + ' ' + statusType.getReasonPhrase();
    }

    public WebApplicationException(Throwable th, int i) {
        this(th, Response.status(i).build());
    }

    public WebApplicationException(String str, Throwable th, int i) {
        this(str, th, Response.status(i).build());
    }

    public WebApplicationException(Throwable th, Response.Status status) throws IllegalArgumentException {
        this(th, Response.status(status).build());
    }

    public WebApplicationException(String str, Throwable th, Response.Status status) throws IllegalArgumentException {
        this(str, th, Response.status(status).build());
    }

    public Response getResponse() {
        return this.response;
    }

    static Response validate(Response response, Response.Status status) {
        if (status.getStatusCode() == response.getStatus()) {
            return response;
        }
        throw new IllegalArgumentException(String.format("Invalid response status code. Expected [%d], was [%d].", Integer.valueOf(status.getStatusCode()), Integer.valueOf(response.getStatus())));
    }

    static Response validate(Response response, Response.Status.Family family) {
        if (response.getStatusInfo().getFamily() == family) {
            return response;
        }
        throw new IllegalArgumentException(String.format("Status code of the supplied response [%d] is not from the required status code family \"%s\".", Integer.valueOf(response.getStatus()), family));
    }
}
