package javax.ws.rs.core;

import java.net.URI;
import java.util.List;

/* loaded from: classes4.dex */
public interface UriInfo {
    URI getAbsolutePath();

    UriBuilder getAbsolutePathBuilder();

    URI getBaseUri();

    UriBuilder getBaseUriBuilder();

    List<Object> getMatchedResources();

    List<String> getMatchedURIs();

    List<String> getMatchedURIs(boolean z);

    String getPath();

    String getPath(boolean z);

    MultivaluedMap<String, String> getPathParameters();

    MultivaluedMap<String, String> getPathParameters(boolean z);

    List<PathSegment> getPathSegments();

    List<PathSegment> getPathSegments(boolean z);

    MultivaluedMap<String, String> getQueryParameters();

    MultivaluedMap<String, String> getQueryParameters(boolean z);

    URI getRequestUri();

    UriBuilder getRequestUriBuilder();

    URI relativize(URI uri);

    URI resolve(URI uri);
}
