package javax.ws.rs.sse;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/* loaded from: classes4.dex */
public interface InboundSseEvent extends SseEvent {
    boolean isEmpty();

    <T> T readData(Class<T> cls);

    <T> T readData(Class<T> cls, MediaType mediaType);

    <T> T readData(GenericType<T> genericType);

    <T> T readData(GenericType<T> genericType, MediaType mediaType);

    String readData();
}
