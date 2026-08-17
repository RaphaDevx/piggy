package javax.ws.rs.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import javax.ws.rs.core.MediaType;

/* loaded from: classes4.dex */
public interface InterceptorContext {
    Annotation[] getAnnotations();

    Type getGenericType();

    MediaType getMediaType();

    Object getProperty(String str);

    Collection<String> getPropertyNames();

    Class<?> getType();

    void removeProperty(String str);

    void setAnnotations(Annotation[] annotationArr);

    void setGenericType(Type type);

    void setMediaType(MediaType mediaType);

    void setProperty(String str, Object obj);

    void setType(Class<?> cls);
}
