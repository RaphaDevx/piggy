package javax.ws.rs.client;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/* loaded from: classes3.dex */
public interface ClientRequestContext {
    void abortWith(Response response);

    List<Locale> getAcceptableLanguages();

    List<MediaType> getAcceptableMediaTypes();

    Client getClient();

    Configuration getConfiguration();

    Map<String, Cookie> getCookies();

    Date getDate();

    Object getEntity();

    Annotation[] getEntityAnnotations();

    Class<?> getEntityClass();

    OutputStream getEntityStream();

    Type getEntityType();

    String getHeaderString(String str);

    MultivaluedMap<String, Object> getHeaders();

    Locale getLanguage();

    MediaType getMediaType();

    String getMethod();

    Object getProperty(String str);

    Collection<String> getPropertyNames();

    MultivaluedMap<String, String> getStringHeaders();

    URI getUri();

    boolean hasEntity();

    void removeProperty(String str);

    void setEntity(Object obj);

    void setEntity(Object obj, Annotation[] annotationArr, MediaType mediaType);

    void setEntityStream(OutputStream outputStream);

    void setMethod(String str);

    void setProperty(String str, Object obj);

    void setUri(URI uri);
}
