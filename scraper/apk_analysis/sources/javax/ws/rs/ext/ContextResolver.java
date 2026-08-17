package javax.ws.rs.ext;

/* loaded from: classes4.dex */
public interface ContextResolver<T> {
    T getContext(Class<?> cls);
}
