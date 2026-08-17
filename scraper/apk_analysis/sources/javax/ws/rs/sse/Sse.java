package javax.ws.rs.sse;

import javax.ws.rs.sse.OutboundSseEvent;

/* loaded from: classes4.dex */
public interface Sse {
    SseBroadcaster newBroadcaster();

    OutboundSseEvent.Builder newEventBuilder();

    default OutboundSseEvent newEvent(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter 'data' must not be null.");
        }
        return newEventBuilder().data(String.class, str).build();
    }

    default OutboundSseEvent newEvent(String str, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("Parameter 'data' must not be null.");
        }
        if (str == null) {
            throw new IllegalArgumentException("Parameter 'name' must not be null.");
        }
        return newEventBuilder().data(String.class, str2).name(str).build();
    }
}
