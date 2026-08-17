package javax.ws.rs.container;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/* loaded from: classes3.dex */
public interface ContainerResponseContext {
    Set<String> getAllowedMethods();

    Map<String, NewCookie> getCookies();

    Date getDate();

    Object getEntity();

    Annotation[] getEntityAnnotations();

    Class<?> getEntityClass();

    OutputStream getEntityStream();

    EntityTag getEntityTag();

    Type getEntityType();

    String getHeaderString(String str);

    MultivaluedMap<String, Object> getHeaders();

    Locale getLanguage();

    Date getLastModified();

    int getLength();

    Link getLink(String str);

    Link.Builder getLinkBuilder(String str);

    Set<Link> getLinks();

    URI getLocation();

    MediaType getMediaType();

    int getStatus();

    Response.StatusType getStatusInfo();

    MultivaluedMap<String, String> getStringHeaders();

    boolean hasEntity();

    boolean hasLink(String str);

    void setEntity(Object obj);

    void setEntity(Object obj, Annotation[] annotationArr, MediaType mediaType);

    void setEntityStream(OutputStream outputStream);

    void setStatus(int i);

    void setStatusInfo(Response.StatusType statusType);
}
