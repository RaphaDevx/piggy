package javax.ws.rs.sse;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public interface SseBroadcaster extends AutoCloseable {
    CompletionStage<?> broadcast(OutboundSseEvent outboundSseEvent);

    @Override // java.lang.AutoCloseable
    void close();

    void onClose(Consumer<SseEventSink> consumer);

    void onError(BiConsumer<SseEventSink, Throwable> biConsumer);

    void register(SseEventSink sseEventSink);
}
