package javax.ws.rs.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.core.MediaType;

/* loaded from: classes4.dex */
public interface Providers {
    <T> ContextResolver<T> getContextResolver(Class<T> cls, MediaType mediaType);

    <T extends Throwable> ExceptionMapper<T> getExceptionMapper(Class<T> cls);

    <T> MessageBodyReader<T> getMessageBodyReader(Class<T> cls, Type type, Annotation[] annotationArr, MediaType mediaType);

    <T> MessageBodyWriter<T> getMessageBodyWriter(Class<T> cls, Type type, Annotation[] annotationArr, MediaType mediaType);
}
