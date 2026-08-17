package javax.ws.rs.core;

import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import com.google.android.gms.wallet.WalletConstants;
import com.tealium.remotecommands.RemoteCommand;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Link;
import javax.ws.rs.ext.RuntimeDelegate;

/* loaded from: classes4.dex */
public abstract class Response implements AutoCloseable {
    public abstract boolean bufferEntity();

    @Override // java.lang.AutoCloseable
    public abstract void close();

    public abstract Set<String> getAllowedMethods();

    public abstract Map<String, NewCookie> getCookies();

    public abstract Date getDate();

    public abstract Object getEntity();

    public abstract EntityTag getEntityTag();

    public abstract String getHeaderString(String str);

    public abstract Locale getLanguage();

    public abstract Date getLastModified();

    public abstract int getLength();

    public abstract Link getLink(String str);

    public abstract Link.Builder getLinkBuilder(String str);

    public abstract Set<Link> getLinks();

    public abstract URI getLocation();

    public abstract MediaType getMediaType();

    public abstract MultivaluedMap<String, Object> getMetadata();

    public abstract int getStatus();

    public abstract StatusType getStatusInfo();

    public abstract MultivaluedMap<String, String> getStringHeaders();

    public abstract boolean hasEntity();

    public abstract boolean hasLink(String str);

    public abstract <T> T readEntity(Class<T> cls);

    public abstract <T> T readEntity(Class<T> cls, Annotation[] annotationArr);

    public abstract <T> T readEntity(GenericType<T> genericType);

    public abstract <T> T readEntity(GenericType<T> genericType, Annotation[] annotationArr);

    protected Response() {
    }

    public MultivaluedMap<String, Object> getHeaders() {
        return getMetadata();
    }

    public static ResponseBuilder fromResponse(Response response) {
        ResponseBuilder status = status(response.getStatus());
        if (response.hasEntity()) {
            status.entity(response.getEntity());
        }
        for (String str : response.getHeaders().keySet()) {
            Iterator it = ((List) response.getHeaders().get(str)).iterator();
            while (it.hasNext()) {
                status.header(str, it.next());
            }
        }
        return status;
    }

    public static ResponseBuilder status(StatusType statusType) {
        return ResponseBuilder.newInstance().status(statusType);
    }

    public static ResponseBuilder status(Status status) {
        return status((StatusType) status);
    }

    public static ResponseBuilder status(int i) {
        return ResponseBuilder.newInstance().status(i);
    }

    public static ResponseBuilder status(int i, String str) {
        return ResponseBuilder.newInstance().status(i, str);
    }

    public static ResponseBuilder ok() {
        return status(Status.OK);
    }

    public static ResponseBuilder ok(Object obj) {
        ResponseBuilder ok = ok();
        ok.entity(obj);
        return ok;
    }

    public static ResponseBuilder ok(Object obj, MediaType mediaType) {
        return ok().entity(obj).type(mediaType);
    }

    public static ResponseBuilder ok(Object obj, String str) {
        return ok().entity(obj).type(str);
    }

    public static ResponseBuilder ok(Object obj, Variant variant) {
        return ok().entity(obj).variant(variant);
    }

    public static ResponseBuilder serverError() {
        return status(Status.INTERNAL_SERVER_ERROR);
    }

    public static ResponseBuilder created(URI uri) {
        return status(Status.CREATED).location(uri);
    }

    public static ResponseBuilder accepted() {
        return status(Status.ACCEPTED);
    }

    public static ResponseBuilder accepted(Object obj) {
        return accepted().entity(obj);
    }

    public static ResponseBuilder noContent() {
        return status(Status.NO_CONTENT);
    }

    public static ResponseBuilder notModified() {
        return status(Status.NOT_MODIFIED);
    }

    public static ResponseBuilder notModified(EntityTag entityTag) {
        return notModified().tag(entityTag);
    }

    public static ResponseBuilder notModified(String str) {
        return notModified().tag(str);
    }

    public static ResponseBuilder seeOther(URI uri) {
        return status(Status.SEE_OTHER).location(uri);
    }

    public static ResponseBuilder temporaryRedirect(URI uri) {
        return status(Status.TEMPORARY_REDIRECT).location(uri);
    }

    public static ResponseBuilder notAcceptable(List<Variant> list) {
        return status(Status.NOT_ACCEPTABLE).variants(list);
    }

    public static abstract class ResponseBuilder {
        public abstract ResponseBuilder allow(Set<String> set);

        public abstract ResponseBuilder allow(String... strArr);

        public abstract Response build();

        public abstract ResponseBuilder cacheControl(CacheControl cacheControl);

        public abstract ResponseBuilder clone();

        public abstract ResponseBuilder contentLocation(URI uri);

        public abstract ResponseBuilder cookie(NewCookie... newCookieArr);

        public abstract ResponseBuilder encoding(String str);

        public abstract ResponseBuilder entity(Object obj);

        public abstract ResponseBuilder entity(Object obj, Annotation[] annotationArr);

        public abstract ResponseBuilder expires(Date date);

        public abstract ResponseBuilder header(String str, Object obj);

        public abstract ResponseBuilder language(String str);

        public abstract ResponseBuilder language(Locale locale);

        public abstract ResponseBuilder lastModified(Date date);

        public abstract ResponseBuilder link(String str, String str2);

        public abstract ResponseBuilder link(URI uri, String str);

        public abstract ResponseBuilder links(Link... linkArr);

        public abstract ResponseBuilder location(URI uri);

        public abstract ResponseBuilder replaceAll(MultivaluedMap<String, Object> multivaluedMap);

        public abstract ResponseBuilder status(int i);

        public abstract ResponseBuilder status(int i, String str);

        public abstract ResponseBuilder tag(String str);

        public abstract ResponseBuilder tag(EntityTag entityTag);

        public abstract ResponseBuilder type(String str);

        public abstract ResponseBuilder type(MediaType mediaType);

        public abstract ResponseBuilder variant(Variant variant);

        public abstract ResponseBuilder variants(List<Variant> list);

        public abstract ResponseBuilder variants(Variant... variantArr);

        protected ResponseBuilder() {
        }

        protected static ResponseBuilder newInstance() {
            return RuntimeDelegate.getInstance().createResponseBuilder();
        }

        public ResponseBuilder status(StatusType statusType) {
            if (statusType == null) {
                throw new IllegalArgumentException();
            }
            return status(statusType.getStatusCode(), statusType.getReasonPhrase());
        }

        public ResponseBuilder status(Status status) {
            return status((StatusType) status);
        }
    }

    public interface StatusType {
        Status.Family getFamily();

        String getReasonPhrase();

        int getStatusCode();

        default Status toEnum() {
            return Status.fromStatusCode(getStatusCode());
        }
    }

    public enum Status implements StatusType {
        OK(200, "OK"),
        CREATED(ComposerKt.providerKey, "Created"),
        ACCEPTED(ComposerKt.compositionLocalMapKey, "Accepted"),
        NO_CONTENT(ComposerKt.providerMapsKey, "No Content"),
        RESET_CONTENT(205, "Reset Content"),
        PARTIAL_CONTENT(ComposerKt.referenceKey, "Partial Content"),
        MOVED_PERMANENTLY(301, "Moved Permanently"),
        FOUND(302, "Found"),
        SEE_OTHER(303, "See Other"),
        NOT_MODIFIED(304, "Not Modified"),
        USE_PROXY(305, "Use Proxy"),
        TEMPORARY_REDIRECT(307, "Temporary Redirect"),
        BAD_REQUEST(RemoteCommand.Response.STATUS_BAD_REQUEST, "Bad Request"),
        UNAUTHORIZED(TypedValues.CycleType.TYPE_CURVE_FIT, "Unauthorized"),
        PAYMENT_REQUIRED(402, "Payment Required"),
        FORBIDDEN(TypedValues.CycleType.TYPE_ALPHA, "Forbidden"),
        NOT_FOUND(404, "Not Found"),
        METHOD_NOT_ALLOWED(WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, "Method Not Allowed"),
        NOT_ACCEPTABLE(WalletConstants.ERROR_CODE_SPENDING_LIMIT_EXCEEDED, "Not Acceptable"),
        PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
        REQUEST_TIMEOUT(408, "Request Timeout"),
        CONFLICT(WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR, "Conflict"),
        GONE(WalletConstants.ERROR_CODE_INVALID_TRANSACTION, "Gone"),
        LENGTH_REQUIRED(WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE, "Length Required"),
        PRECONDITION_FAILED(WalletConstants.ERROR_CODE_UNSUPPORTED_API_VERSION, "Precondition Failed"),
        REQUEST_ENTITY_TOO_LARGE(WalletConstants.ERROR_CODE_UNKNOWN, "Request Entity Too Large"),
        REQUEST_URI_TOO_LONG(WalletConstants.ERROR_CODE_ILLEGAL_CALLER, "Request-URI Too Long"),
        UNSUPPORTED_MEDIA_TYPE(WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, "Unsupported Media Type"),
        REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
        EXPECTATION_FAILED(417, "Expectation Failed"),
        PRECONDITION_REQUIRED(428, "Precondition Required"),
        TOO_MANY_REQUESTS(429, "Too Many Requests"),
        REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
        INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
        NOT_IMPLEMENTED(TypedValues.PositionType.TYPE_TRANSITION_EASING, "Not Implemented"),
        BAD_GATEWAY(TypedValues.PositionType.TYPE_DRAWPATH, "Bad Gateway"),
        SERVICE_UNAVAILABLE(TypedValues.PositionType.TYPE_PERCENT_WIDTH, "Service Unavailable"),
        GATEWAY_TIMEOUT(TypedValues.PositionType.TYPE_PERCENT_HEIGHT, "Gateway Timeout"),
        HTTP_VERSION_NOT_SUPPORTED(TypedValues.PositionType.TYPE_SIZE_PERCENT, "HTTP Version Not Supported"),
        NETWORK_AUTHENTICATION_REQUIRED(FrameMetricsAggregator.EVERY_DURATION, "Network Authentication Required");

        private final int code;
        private final Family family;
        private final String reason;

        public enum Family {
            INFORMATIONAL,
            SUCCESSFUL,
            REDIRECTION,
            CLIENT_ERROR,
            SERVER_ERROR,
            OTHER;

            public static Family familyOf(int i) {
                int i2 = i / 100;
                if (i2 == 1) {
                    return INFORMATIONAL;
                }
                if (i2 == 2) {
                    return SUCCESSFUL;
                }
                if (i2 == 3) {
                    return REDIRECTION;
                }
                if (i2 == 4) {
                    return CLIENT_ERROR;
                }
                if (i2 == 5) {
                    return SERVER_ERROR;
                }
                return OTHER;
            }
        }

        Status(int i, String str) {
            this.code = i;
            this.reason = str;
            this.family = Family.familyOf(i);
        }

        @Override // javax.ws.rs.core.Response.StatusType
        public Family getFamily() {
            return this.family;
        }

        @Override // javax.ws.rs.core.Response.StatusType
        public int getStatusCode() {
            return this.code;
        }

        @Override // javax.ws.rs.core.Response.StatusType
        public String getReasonPhrase() {
            return toString();
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.reason;
        }

        public static Status fromStatusCode(int i) {
            for (Status status : values()) {
                if (status.code == i) {
                    return status;
                }
            }
            return null;
        }
    }
}
