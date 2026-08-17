package javax.ws.rs.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
public interface ParamConverterProvider {
    <T> ParamConverter<T> getConverter(Class<T> cls, Type type, Annotation[] annotationArr);
}
