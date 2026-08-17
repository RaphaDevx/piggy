package javax.ws.rs.sse;

import com.fasterxml.jackson.core.JsonPointer;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.ws.rs.client.WebTarget;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public interface SseEventSource extends AutoCloseable {
    boolean close(long j, TimeUnit timeUnit);

    boolean isOpen();

    void open();

    void register(Consumer<InboundSseEvent> consumer);

    void register(Consumer<InboundSseEvent> consumer, Consumer<Throwable> consumer2);

    void register(Consumer<InboundSseEvent> consumer, Consumer<Throwable> consumer2, Runnable runnable);

    public static abstract class Builder {
        private static final String JAXRS_DEFAULT_SSE_BUILDER = "org.glassfish.jersey.media.sse.internal.JerseySseEventSource$Builder";
        public static final String JAXRS_DEFAULT_SSE_BUILDER_PROPERTY = "javax.ws.rs.sse.SseEventSource.Builder";

        public abstract SseEventSource build();

        public abstract Builder reconnectingEvery(long j, TimeUnit timeUnit);

        protected abstract Builder target(WebTarget webTarget);

        protected Builder() {
        }

        static Builder newBuilder() {
            try {
                Object find = FactoryFinder.find(JAXRS_DEFAULT_SSE_BUILDER_PROPERTY, JAXRS_DEFAULT_SSE_BUILDER, Builder.class);
                if (!(find instanceof Builder)) {
                    String str = Builder.class.getName().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, JsonPointer.SEPARATOR) + ".class";
                    ClassLoader classLoader = Builder.class.getClassLoader();
                    if (classLoader == null) {
                        classLoader = ClassLoader.getSystemClassLoader();
                    }
                    throw new LinkageError("ClassCastException: attempting to cast" + find.getClass().getClassLoader().getResource(str) + " to " + classLoader.getResource(str));
                }
                return (Builder) find;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static Builder target(WebTarget webTarget) {
        return Builder.newBuilder().target(webTarget);
    }

    @Override // java.lang.AutoCloseable
    default void close() {
        close(5L, TimeUnit.SECONDS);
    }
}
