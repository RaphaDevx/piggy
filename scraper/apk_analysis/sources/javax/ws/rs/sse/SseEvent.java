package javax.ws.rs.sse;

/* loaded from: classes4.dex */
public interface SseEvent {
    public static final long RECONNECT_NOT_SET = -1;

    String getComment();

    String getId();

    String getName();

    long getReconnectDelay();

    boolean isReconnectDelaySet();
}
