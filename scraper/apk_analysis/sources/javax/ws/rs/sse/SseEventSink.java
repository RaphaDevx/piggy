package javax.ws.rs.sse;

import java.util.concurrent.CompletionStage;

/* loaded from: classes4.dex */
public interface SseEventSink extends AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close();

    boolean isClosed();

    CompletionStage<?> send(OutboundSseEvent outboundSseEvent);
}
