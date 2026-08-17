package javax.ws.rs.client;

/* loaded from: classes3.dex */
public interface InvocationCallback<RESPONSE> {
    void completed(RESPONSE response);

    void failed(Throwable th);
}
