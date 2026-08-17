package javax.ws.rs.container;

import java.io.InputStream;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/* loaded from: classes3.dex */
public interface ContainerRequestContext {
    void abortWith(Response response);

    List<Locale> getAcceptableLanguages();

    List<MediaType> getAcceptableMediaTypes();

    Map<String, Cookie> getCookies();

    Date getDate();

    InputStream getEntityStream();

    String getHeaderString(String str);

    MultivaluedMap<String, String> getHeaders();

    Locale getLanguage();

    int getLength();

    MediaType getMediaType();

    String getMethod();

    Object getProperty(String str);

    Collection<String> getPropertyNames();

    Request getRequest();

    SecurityContext getSecurityContext();

    UriInfo getUriInfo();

    boolean hasEntity();

    void removeProperty(String str);

    void setEntityStream(InputStream inputStream);

    void setMethod(String str);

    void setProperty(String str, Object obj);

    void setRequestUri(URI uri);

    void setRequestUri(URI uri, URI uri2);

    void setSecurityContext(SecurityContext securityContext);
}
