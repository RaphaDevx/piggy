package javax.ws.rs.ext;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/* loaded from: classes4.dex */
public interface MessageBodyWriter<T> {
    default long getSize(T t, Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType) {
        return -1L;
    }

    boolean isWriteable(Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType);

    void writeTo(T t, Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException;
}
