package javax.ws.rs.container;

/* loaded from: classes3.dex */
public interface ResourceContext {
    <T> T getResource(Class<T> cls);

    <T> T initResource(T t);
}
