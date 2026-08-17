package javax.ws.rs.ext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/* loaded from: classes4.dex */
public interface MessageBodyReader<T> {
    boolean isReadable(Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType);

    T readFrom(Class<T> cls, Type type, Annotation[] annotationArr, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException;
}
