package javax.ws.rs.ext;

import com.fasterxml.jackson.core.JsonPointer;
import java.lang.reflect.ReflectPermission;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Variant;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public abstract class RuntimeDelegate {
    private static final String JAXRS_DEFAULT_RUNTIME_DELEGATE = "org.glassfish.jersey.internal.RuntimeDelegateImpl";
    public static final String JAXRS_RUNTIME_DELEGATE_PROPERTY = "javax.ws.rs.ext.RuntimeDelegate";
    private static volatile RuntimeDelegate cachedDelegate;
    private static final Object RD_LOCK = new Object();
    private static ReflectPermission suppressAccessChecksPermission = new ReflectPermission("suppressAccessChecks");

    public interface HeaderDelegate<T> {
        T fromString(String str);

        String toString(T t);
    }

    public abstract <T> T createEndpoint(Application application, Class<T> cls) throws IllegalArgumentException, UnsupportedOperationException;

    public abstract <T> HeaderDelegate<T> createHeaderDelegate(Class<T> cls) throws IllegalArgumentException;

    public abstract Link.Builder createLinkBuilder();

    public abstract Response.ResponseBuilder createResponseBuilder();

    public abstract UriBuilder createUriBuilder();

    public abstract Variant.VariantListBuilder createVariantListBuilder();

    protected RuntimeDelegate() {
    }

    public static RuntimeDelegate getInstance() {
        RuntimeDelegate runtimeDelegate = cachedDelegate;
        if (runtimeDelegate == null) {
            synchronized (RD_LOCK) {
                runtimeDelegate = cachedDelegate;
                if (runtimeDelegate == null) {
                    runtimeDelegate = findDelegate();
                    cachedDelegate = runtimeDelegate;
                }
            }
        }
        return runtimeDelegate;
    }

    private static RuntimeDelegate findDelegate() {
        try {
            Object find = FactoryFinder.find(JAXRS_RUNTIME_DELEGATE_PROPERTY, JAXRS_DEFAULT_RUNTIME_DELEGATE, RuntimeDelegate.class);
            if (!(find instanceof RuntimeDelegate)) {
                String str = RuntimeDelegate.class.getName().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, JsonPointer.SEPARATOR) + ".class";
                ClassLoader classLoader = RuntimeDelegate.class.getClassLoader();
                if (classLoader == null) {
                    classLoader = ClassLoader.getSystemClassLoader();
                }
                throw new LinkageError("ClassCastException: attempting to cast" + find.getClass().getClassLoader().getResource(str) + " to " + classLoader.getResource(str));
            }
            return (RuntimeDelegate) find;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setInstance(RuntimeDelegate runtimeDelegate) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(suppressAccessChecksPermission);
        }
        synchronized (RD_LOCK) {
            cachedDelegate = runtimeDelegate;
        }
    }
}
