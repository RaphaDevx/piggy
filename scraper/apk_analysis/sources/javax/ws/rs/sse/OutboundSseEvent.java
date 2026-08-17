package javax.ws.rs.sse;

import java.lang.reflect.Type;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/* loaded from: classes4.dex */
public interface OutboundSseEvent extends SseEvent {

    public interface Builder {
        OutboundSseEvent build();

        Builder comment(String str);

        Builder data(Class cls, Object obj);

        Builder data(Object obj);

        Builder data(GenericType genericType, Object obj);

        Builder id(String str);

        Builder mediaType(MediaType mediaType);

        Builder name(String str);

        Builder reconnectDelay(long j);
    }

    Object getData();

    Type getGenericType();

    MediaType getMediaType();

    Class<?> getType();
}
