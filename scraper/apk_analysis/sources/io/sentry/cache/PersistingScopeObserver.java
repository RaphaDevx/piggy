package io.sentry.cache;

import io.sentry.Breadcrumb;
import io.sentry.IScope;
import io.sentry.ISerializer;
import io.sentry.ScopeObserverAdapter;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SpanContext;
import io.sentry.cache.tape.ObjectQueue;
import io.sentry.cache.tape.QueueFile;
import io.sentry.protocol.Contexts;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import io.sentry.util.LazyEvaluator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes2.dex */
public final class PersistingScopeObserver extends ScopeObserverAdapter {
    public static final String BREADCRUMBS_FILENAME = "breadcrumbs.json";
    public static final String CONTEXTS_FILENAME = "contexts.json";
    public static final String EXTRAS_FILENAME = "extras.json";
    public static final String FINGERPRINT_FILENAME = "fingerprint.json";
    public static final String LEVEL_FILENAME = "level.json";
    public static final String REPLAY_FILENAME = "replay.json";
    public static final String REQUEST_FILENAME = "request.json";
    public static final String SCOPE_CACHE = ".scope-cache";
    public static final String TAGS_FILENAME = "tags.json";
    public static final String TRACE_FILENAME = "trace.json";
    public static final String TRANSACTION_FILENAME = "transaction.json";
    public static final String USER_FILENAME = "user.json";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final LazyEvaluator<ObjectQueue<Breadcrumb>> breadcrumbsQueue = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda7
        @Override // io.sentry.util.LazyEvaluator.Evaluator
        public final Object evaluate() {
            return PersistingScopeObserver.this.m3909lambda$new$0$iosentrycachePersistingScopeObserver();
        }
    });
    private SentryOptions options;

    /* renamed from: lambda$new$0$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ ObjectQueue m3909lambda$new$0$iosentrycachePersistingScopeObserver() {
        QueueFile build;
        File ensureCacheDir = CacheUtils.ensureCacheDir(this.options, SCOPE_CACHE);
        if (ensureCacheDir == null) {
            this.options.getLogger().log(SentryLevel.INFO, "Cache dir is not set, cannot store in scope cache", new Object[0]);
            return ObjectQueue.createEmpty();
        }
        File file = new File(ensureCacheDir, BREADCRUMBS_FILENAME);
        try {
            try {
                build = new QueueFile.Builder(file).size(this.options.getMaxBreadcrumbs()).build();
            } catch (IOException e) {
                this.options.getLogger().log(SentryLevel.ERROR, "Failed to create breadcrumbs queue", e);
                return ObjectQueue.createEmpty();
            }
        } catch (IOException unused) {
            file.delete();
            build = new QueueFile.Builder(file).size(this.options.getMaxBreadcrumbs()).build();
        }
        return ObjectQueue.create(build, new ObjectQueue.Converter<Breadcrumb>() { // from class: io.sentry.cache.PersistingScopeObserver.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.sentry.cache.tape.ObjectQueue.Converter
            public Breadcrumb from(byte[] bArr) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr), PersistingScopeObserver.UTF_8));
                    try {
                        Breadcrumb breadcrumb = (Breadcrumb) PersistingScopeObserver.this.options.getSerializer().deserialize(bufferedReader, Breadcrumb.class);
                        bufferedReader.close();
                        return breadcrumb;
                    } finally {
                    }
                } catch (Throwable th) {
                    PersistingScopeObserver.this.options.getLogger().log(SentryLevel.ERROR, th, "Error reading entity from scope cache", new Object[0]);
                    return null;
                }
            }

            @Override // io.sentry.cache.tape.ObjectQueue.Converter
            public void toStream(Breadcrumb breadcrumb, OutputStream outputStream) throws IOException {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, PersistingScopeObserver.UTF_8));
                try {
                    PersistingScopeObserver.this.options.getSerializer().serialize((ISerializer) breadcrumb, (Writer) bufferedWriter);
                    bufferedWriter.close();
                } catch (Throwable th) {
                    try {
                        bufferedWriter.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
        });
    }

    public PersistingScopeObserver(SentryOptions sentryOptions) {
        this.options = sentryOptions;
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setUser(final User user) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3921lambda$setUser$1$iosentrycachePersistingScopeObserver(user);
            }
        });
    }

    /* renamed from: lambda$setUser$1$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3921lambda$setUser$1$iosentrycachePersistingScopeObserver(User user) {
        if (user == null) {
            delete(USER_FILENAME);
        } else {
            store(user, USER_FILENAME);
        }
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void addBreadcrumb(final Breadcrumb breadcrumb) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3908lambda$addBreadcrumb$2$iosentrycachePersistingScopeObserver(breadcrumb);
            }
        });
    }

    /* renamed from: lambda$addBreadcrumb$2$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3908lambda$addBreadcrumb$2$iosentrycachePersistingScopeObserver(Breadcrumb breadcrumb) {
        try {
            this.breadcrumbsQueue.getValue().add(breadcrumb);
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to add breadcrumb to file queue", e);
        }
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setBreadcrumbs(Collection<Breadcrumb> collection) {
        if (collection.isEmpty()) {
            serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    PersistingScopeObserver.this.m3911lambda$setBreadcrumbs$3$iosentrycachePersistingScopeObserver();
                }
            });
        }
    }

    /* renamed from: lambda$setBreadcrumbs$3$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3911lambda$setBreadcrumbs$3$iosentrycachePersistingScopeObserver() {
        try {
            this.breadcrumbsQueue.getValue().clear();
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to clear breadcrumbs from file queue", e);
        }
    }

    /* renamed from: lambda$setTags$4$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3918lambda$setTags$4$iosentrycachePersistingScopeObserver(Map map) {
        store(map, "tags.json");
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setTags(final Map<String, String> map) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3918lambda$setTags$4$iosentrycachePersistingScopeObserver(map);
            }
        });
    }

    /* renamed from: lambda$setExtras$5$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3913lambda$setExtras$5$iosentrycachePersistingScopeObserver(Map map) {
        store(map, EXTRAS_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setExtras(final Map<String, Object> map) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3913lambda$setExtras$5$iosentrycachePersistingScopeObserver(map);
            }
        });
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setRequest(final Request request) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3917lambda$setRequest$6$iosentrycachePersistingScopeObserver(request);
            }
        });
    }

    /* renamed from: lambda$setRequest$6$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3917lambda$setRequest$6$iosentrycachePersistingScopeObserver(Request request) {
        if (request == null) {
            delete(REQUEST_FILENAME);
        } else {
            store(request, REQUEST_FILENAME);
        }
    }

    /* renamed from: lambda$setFingerprint$7$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3914lambda$setFingerprint$7$iosentrycachePersistingScopeObserver(Collection collection) {
        store(collection, FINGERPRINT_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setFingerprint(final Collection<String> collection) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3914lambda$setFingerprint$7$iosentrycachePersistingScopeObserver(collection);
            }
        });
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setLevel(final SentryLevel sentryLevel) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3915lambda$setLevel$8$iosentrycachePersistingScopeObserver(sentryLevel);
            }
        });
    }

    /* renamed from: lambda$setLevel$8$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3915lambda$setLevel$8$iosentrycachePersistingScopeObserver(SentryLevel sentryLevel) {
        if (sentryLevel == null) {
            delete(LEVEL_FILENAME);
        } else {
            store(sentryLevel, LEVEL_FILENAME);
        }
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setTransaction(final String str) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3920lambda$setTransaction$9$iosentrycachePersistingScopeObserver(str);
            }
        });
    }

    /* renamed from: lambda$setTransaction$9$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3920lambda$setTransaction$9$iosentrycachePersistingScopeObserver(String str) {
        if (str == null) {
            delete(TRANSACTION_FILENAME);
        } else {
            store(str, TRANSACTION_FILENAME);
        }
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setTrace(final SpanContext spanContext, final IScope iScope) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3919lambda$setTrace$10$iosentrycachePersistingScopeObserver(spanContext, iScope);
            }
        });
    }

    /* renamed from: lambda$setTrace$10$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3919lambda$setTrace$10$iosentrycachePersistingScopeObserver(SpanContext spanContext, IScope iScope) {
        if (spanContext == null) {
            store(iScope.getPropagationContext().toSpanContext(), TRACE_FILENAME);
        } else {
            store(spanContext, TRACE_FILENAME);
        }
    }

    /* renamed from: lambda$setContexts$11$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3912lambda$setContexts$11$iosentrycachePersistingScopeObserver(Contexts contexts) {
        store(contexts, CONTEXTS_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setContexts(final Contexts contexts) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3912lambda$setContexts$11$iosentrycachePersistingScopeObserver(contexts);
            }
        });
    }

    /* renamed from: lambda$setReplayId$12$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3916lambda$setReplayId$12$iosentrycachePersistingScopeObserver(SentryId sentryId) {
        store(sentryId, REPLAY_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setReplayId(final SentryId sentryId) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PersistingScopeObserver.this.m3916lambda$setReplayId$12$iosentrycachePersistingScopeObserver(sentryId);
            }
        });
    }

    private void serializeToDisk(final Runnable runnable) {
        if (this.options.isEnableScopePersistence()) {
            if (Thread.currentThread().getName().contains("SentryExecutor")) {
                try {
                    runnable.run();
                    return;
                } catch (Throwable th) {
                    this.options.getLogger().log(SentryLevel.ERROR, "Serialization task failed", th);
                    return;
                }
            }
            try {
                this.options.getExecutorService().submit(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        PersistingScopeObserver.this.m3910xf585d0b1(runnable);
                    }
                });
            } catch (Throwable th2) {
                this.options.getLogger().log(SentryLevel.ERROR, "Serialization task could not be scheduled", th2);
            }
        }
    }

    /* renamed from: lambda$serializeToDisk$13$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m3910xf585d0b1(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Serialization task failed", th);
        }
    }

    private <T> void store(T t, String str) {
        store(this.options, t, str);
    }

    private void delete(String str) {
        CacheUtils.delete(this.options, SCOPE_CACHE, str);
    }

    public static <T> void store(SentryOptions sentryOptions, T t, String str) {
        CacheUtils.store(sentryOptions, t, SCOPE_CACHE, str);
    }

    public <T> T read(SentryOptions sentryOptions, String str, Class<T> cls) {
        if (str.equals(BREADCRUMBS_FILENAME)) {
            try {
                return cls.cast(this.breadcrumbsQueue.getValue().asList());
            } catch (IOException unused) {
                sentryOptions.getLogger().log(SentryLevel.ERROR, "Unable to read serialized breadcrumbs from QueueFile", new Object[0]);
                return null;
            }
        }
        return (T) CacheUtils.read(sentryOptions, SCOPE_CACHE, str, cls, null);
    }

    public void resetCache() {
        try {
            this.breadcrumbsQueue.getValue().clear();
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to clear breadcrumbs from file queue", e);
        }
        delete(USER_FILENAME);
        delete(LEVEL_FILENAME);
        delete(REQUEST_FILENAME);
        delete(FINGERPRINT_FILENAME);
        delete(CONTEXTS_FILENAME);
        delete(EXTRAS_FILENAME);
        delete("tags.json");
        delete(TRACE_FILENAME);
        delete(TRANSACTION_FILENAME);
    }
}
