package javax.ws.rs.core;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;
import javax.ws.rs.ext.RuntimeDelegate;

/* loaded from: classes4.dex */
public abstract class UriBuilder {
    public abstract URI build(Object... objArr) throws IllegalArgumentException, UriBuilderException;

    public abstract URI build(Object[] objArr, boolean z) throws IllegalArgumentException, UriBuilderException;

    public abstract URI buildFromEncoded(Object... objArr) throws IllegalArgumentException, UriBuilderException;

    public abstract URI buildFromEncodedMap(Map<String, ?> map) throws IllegalArgumentException, UriBuilderException;

    public abstract URI buildFromMap(Map<String, ?> map);

    public abstract URI buildFromMap(Map<String, ?> map, boolean z) throws IllegalArgumentException, UriBuilderException;

    public abstract UriBuilder clone();

    public abstract UriBuilder fragment(String str);

    public abstract UriBuilder host(String str);

    public abstract UriBuilder matrixParam(String str, Object... objArr);

    public abstract UriBuilder path(Class cls);

    public abstract UriBuilder path(Class cls, String str);

    public abstract UriBuilder path(String str);

    public abstract UriBuilder path(Method method);

    public abstract UriBuilder port(int i);

    public abstract UriBuilder queryParam(String str, Object... objArr);

    public abstract UriBuilder replaceMatrix(String str);

    public abstract UriBuilder replaceMatrixParam(String str, Object... objArr);

    public abstract UriBuilder replacePath(String str);

    public abstract UriBuilder replaceQuery(String str);

    public abstract UriBuilder replaceQueryParam(String str, Object... objArr);

    public abstract UriBuilder resolveTemplate(String str, Object obj);

    public abstract UriBuilder resolveTemplate(String str, Object obj, boolean z);

    public abstract UriBuilder resolveTemplateFromEncoded(String str, Object obj);

    public abstract UriBuilder resolveTemplates(Map<String, Object> map);

    public abstract UriBuilder resolveTemplates(Map<String, Object> map, boolean z) throws IllegalArgumentException;

    public abstract UriBuilder resolveTemplatesFromEncoded(Map<String, Object> map);

    public abstract UriBuilder scheme(String str);

    public abstract UriBuilder schemeSpecificPart(String str);

    public abstract UriBuilder segment(String... strArr);

    public abstract String toTemplate();

    public abstract UriBuilder uri(String str);

    public abstract UriBuilder uri(URI uri);

    public abstract UriBuilder userInfo(String str);

    protected UriBuilder() {
    }

    protected static UriBuilder newInstance() {
        return RuntimeDelegate.getInstance().createUriBuilder();
    }

    public static UriBuilder fromUri(URI uri) {
        return newInstance().uri(uri);
    }

    public static UriBuilder fromUri(String str) {
        return newInstance().uri(str);
    }

    public static UriBuilder fromLink(Link link) {
        if (link == null) {
            throw new IllegalArgumentException("The provider 'link' parameter value is 'null'.");
        }
        return fromUri(link.getUri());
    }

    public static UriBuilder fromPath(String str) throws IllegalArgumentException {
        return newInstance().path(str);
    }

    public static UriBuilder fromResource(Class<?> cls) {
        return newInstance().path(cls);
    }

    public static UriBuilder fromMethod(Class<?> cls, String str) {
        return newInstance().path(cls, str);
    }
}
