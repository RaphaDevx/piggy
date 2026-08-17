package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.ISentryLifecycleToken;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.ProfileContext;
import io.sentry.SpanContext;
import io.sentry.protocol.App;
import io.sentry.protocol.Browser;
import io.sentry.protocol.Device;
import io.sentry.protocol.Feedback;
import io.sentry.protocol.Gpu;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.Response;
import io.sentry.protocol.SentryRuntime;
import io.sentry.protocol.Spring;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.HintUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class Contexts implements JsonSerializable {
    public static final String REPLAY_ID = "replay_id";
    private static final long serialVersionUID = 252445813254943011L;
    private final ConcurrentHashMap<String, Object> internalStorage = new ConcurrentHashMap<>();
    protected final AutoClosableReentrantLock responseLock = new AutoClosableReentrantLock();

    public Contexts() {
    }

    public Contexts(Contexts contexts) {
        for (Map.Entry<String, Object> entry : contexts.entrySet()) {
            if (entry != null) {
                Object value = entry.getValue();
                if (App.TYPE.equals(entry.getKey()) && (value instanceof App)) {
                    setApp(new App((App) value));
                } else if (Browser.TYPE.equals(entry.getKey()) && (value instanceof Browser)) {
                    setBrowser(new Browser((Browser) value));
                } else if ("device".equals(entry.getKey()) && (value instanceof Device)) {
                    setDevice(new Device((Device) value));
                } else if (OperatingSystem.TYPE.equals(entry.getKey()) && (value instanceof OperatingSystem)) {
                    setOperatingSystem(new OperatingSystem((OperatingSystem) value));
                } else if (SentryRuntime.TYPE.equals(entry.getKey()) && (value instanceof SentryRuntime)) {
                    setRuntime(new SentryRuntime((SentryRuntime) value));
                } else if (Feedback.TYPE.equals(entry.getKey()) && (value instanceof Feedback)) {
                    setFeedback(new Feedback((Feedback) value));
                } else if (Gpu.TYPE.equals(entry.getKey()) && (value instanceof Gpu)) {
                    setGpu(new Gpu((Gpu) value));
                } else if ("trace".equals(entry.getKey()) && (value instanceof SpanContext)) {
                    setTrace(new SpanContext((SpanContext) value));
                } else if ("profile".equals(entry.getKey()) && (value instanceof ProfileContext)) {
                    setProfile(new ProfileContext((ProfileContext) value));
                } else if (Response.TYPE.equals(entry.getKey()) && (value instanceof Response)) {
                    setResponse(new Response((Response) value));
                } else if (Spring.TYPE.equals(entry.getKey()) && (value instanceof Spring)) {
                    setSpring(new Spring((Spring) value));
                } else {
                    put(entry.getKey(), value);
                }
            }
        }
    }

    private <T> T toContextType(String str, Class<T> cls) {
        Object obj = get(str);
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }

    public SpanContext getTrace() {
        return (SpanContext) toContextType("trace", SpanContext.class);
    }

    public void setTrace(SpanContext spanContext) {
        Objects.requireNonNull(spanContext, "traceContext is required");
        put("trace", spanContext);
    }

    public ProfileContext getProfile() {
        return (ProfileContext) toContextType("profile", ProfileContext.class);
    }

    public void setProfile(ProfileContext profileContext) {
        Objects.requireNonNull(profileContext, "profileContext is required");
        put("profile", profileContext);
    }

    public App getApp() {
        return (App) toContextType(App.TYPE, App.class);
    }

    public void setApp(App app) {
        put(App.TYPE, app);
    }

    public Browser getBrowser() {
        return (Browser) toContextType(Browser.TYPE, Browser.class);
    }

    public void setBrowser(Browser browser) {
        put(Browser.TYPE, browser);
    }

    public Device getDevice() {
        return (Device) toContextType("device", Device.class);
    }

    public void setDevice(Device device) {
        put("device", device);
    }

    public OperatingSystem getOperatingSystem() {
        return (OperatingSystem) toContextType(OperatingSystem.TYPE, OperatingSystem.class);
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        put(OperatingSystem.TYPE, operatingSystem);
    }

    public SentryRuntime getRuntime() {
        return (SentryRuntime) toContextType(SentryRuntime.TYPE, SentryRuntime.class);
    }

    public void setRuntime(SentryRuntime sentryRuntime) {
        put(SentryRuntime.TYPE, sentryRuntime);
    }

    public Feedback getFeedback() {
        return (Feedback) toContextType(Feedback.TYPE, Feedback.class);
    }

    public void setFeedback(Feedback feedback) {
        put(Feedback.TYPE, feedback);
    }

    public Gpu getGpu() {
        return (Gpu) toContextType(Gpu.TYPE, Gpu.class);
    }

    public void setGpu(Gpu gpu) {
        put(Gpu.TYPE, gpu);
    }

    public Response getResponse() {
        return (Response) toContextType(Response.TYPE, Response.class);
    }

    public void withResponse(HintUtils.SentryConsumer<Response> sentryConsumer) {
        ISentryLifecycleToken acquire = this.responseLock.acquire();
        try {
            Response response = getResponse();
            if (response != null) {
                sentryConsumer.accept(response);
            } else {
                Response response2 = new Response();
                setResponse(response2);
                sentryConsumer.accept(response2);
            }
            if (acquire != null) {
                acquire.close();
            }
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void setResponse(Response response) {
        ISentryLifecycleToken acquire = this.responseLock.acquire();
        try {
            put(Response.TYPE, response);
            if (acquire != null) {
                acquire.close();
            }
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public Spring getSpring() {
        return (Spring) toContextType(Spring.TYPE, Spring.class);
    }

    public void setSpring(Spring spring) {
        put(Spring.TYPE, spring);
    }

    public int size() {
        return this.internalStorage.size();
    }

    public int getSize() {
        return size();
    }

    public boolean isEmpty() {
        return this.internalStorage.isEmpty();
    }

    public boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.internalStorage.containsKey(obj);
    }

    public Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        return this.internalStorage.get(obj);
    }

    public Object put(String str, Object obj) {
        if (str == null) {
            return null;
        }
        if (obj == null) {
            return this.internalStorage.remove(str);
        }
        return this.internalStorage.put(str, obj);
    }

    public Object set(String str, Object obj) {
        return put(str, obj);
    }

    public Object remove(Object obj) {
        if (obj == null) {
            return null;
        }
        return this.internalStorage.remove(obj);
    }

    public Enumeration<String> keys() {
        return this.internalStorage.keys();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.internalStorage.entrySet();
    }

    public void putAll(Map<? extends String, ? extends Object> map) {
        if (map == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<? extends String, ? extends Object> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        this.internalStorage.putAll(hashMap);
    }

    public void putAll(Contexts contexts) {
        if (contexts == null) {
            return;
        }
        this.internalStorage.putAll(contexts.internalStorage);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Contexts)) {
            return false;
        }
        return this.internalStorage.equals(((Contexts) obj).internalStorage);
    }

    public int hashCode() {
        return this.internalStorage.hashCode();
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        ArrayList<String> list = Collections.list(keys());
        Collections.sort(list);
        for (String str : list) {
            Object obj = get(str);
            if (obj != null) {
                objectWriter.name(str).value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<Contexts> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public Contexts deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            Contexts contexts = new Contexts();
            objectReader.beginObject();
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "device":
                        contexts.setDevice(new Device.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "spring":
                        contexts.setSpring(new Spring.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "response":
                        contexts.setResponse(new Response.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "profile":
                        contexts.setProfile(new ProfileContext.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "feedback":
                        contexts.setFeedback(new Feedback.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "os":
                        contexts.setOperatingSystem(new OperatingSystem.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "app":
                        contexts.setApp(new App.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "gpu":
                        contexts.setGpu(new Gpu.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "trace":
                        contexts.setTrace(new SpanContext.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "browser":
                        contexts.setBrowser(new Browser.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "runtime":
                        contexts.setRuntime(new SentryRuntime.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    default:
                        Object nextObjectOrNull = objectReader.nextObjectOrNull();
                        if (nextObjectOrNull == null) {
                            break;
                        } else {
                            contexts.put(nextName, nextObjectOrNull);
                            break;
                        }
                }
            }
            objectReader.endObject();
            return contexts;
        }
    }
}
