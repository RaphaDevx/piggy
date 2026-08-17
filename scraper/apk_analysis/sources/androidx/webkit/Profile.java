package androidx.webkit;

import android.os.CancellationSignal;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ServiceWorkerController;
import android.webkit.WebStorage;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public interface Profile {
    public static final String DEFAULT_PROFILE_NAME = "Default";

    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ExperimentalAddQuicHints {
    }

    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ExperimentalOriginMatchedHeader {
    }

    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ExperimentalPreconnect {
    }

    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ExperimentalUrlPrefetch {
    }

    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ExperimentalWarmUpRendererProcess {
    }

    default void addCustomHeader(CustomHeader customHeader) {
    }

    default void clearAllCustomHeaders() {
    }

    void clearAllOriginMatchedHeaders();

    default void clearCustomHeader(String str) {
    }

    default void clearCustomHeader(String str, String str2) {
    }

    void clearOriginMatchedHeader(String str);

    void clearPrefetchAsync(String str, Executor executor, OutcomeReceiverCompat<Void, PrefetchException> outcomeReceiverCompat);

    CookieManager getCookieManager();

    GeolocationPermissions getGeolocationPermissions();

    String getName();

    ServiceWorkerController getServiceWorkerController();

    WebStorage getWebStorage();

    default boolean hasCustomHeader(String str) {
        return false;
    }

    boolean hasOriginMatchedHeader(String str);

    void prefetchUrlAsync(String str, CancellationSignal cancellationSignal, Executor executor, OutcomeReceiverCompat<Void, PrefetchException> outcomeReceiverCompat);

    void prefetchUrlAsync(String str, CancellationSignal cancellationSignal, Executor executor, SpeculativeLoadingParameters speculativeLoadingParameters, OutcomeReceiverCompat<Void, PrefetchException> outcomeReceiverCompat);

    void setOriginMatchedHeader(String str, String str2, Set<String> set);

    void setSpeculativeLoadingConfig(SpeculativeLoadingConfig speculativeLoadingConfig);

    void warmUpRendererProcess();

    default Set<CustomHeader> getCustomHeaders() {
        return Collections.emptySet();
    }

    default Set<CustomHeader> getCustomHeaders(String str) {
        return Collections.emptySet();
    }

    default Set<CustomHeader> getCustomHeaders(String str, String str2) {
        return Collections.emptySet();
    }

    default void preconnect(String str) {
        throw new UnsupportedOperationException("Profile#preconnect is not implemented.");
    }

    default void addQuicHints(Set<String> set) {
        throw new UnsupportedOperationException("Profile#addQuicHints is not implemented.");
    }
}
