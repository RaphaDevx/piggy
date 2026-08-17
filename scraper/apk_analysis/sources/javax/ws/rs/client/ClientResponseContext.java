package javax.ws.rs.client;

import java.io.InputStream;
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
public interface ClientResponseContext {
    Set<String> getAllowedMethods();

    Map<String, NewCookie> getCookies();

    Date getDate();

    InputStream getEntityStream();

    EntityTag getEntityTag();

    String getHeaderString(String str);

    MultivaluedMap<String, String> getHeaders();

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

    boolean hasEntity();

    boolean hasLink(String str);

    void setEntityStream(InputStream inputStream);

    void setStatus(int i);

    void setStatusInfo(Response.StatusType statusType);
}
