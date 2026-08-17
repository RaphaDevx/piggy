package com.scandit.datacapture.frameworks.barcode.ar.caches;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSession;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.frameworks.barcode.ar.data.AnnotationCallbackData;
import com.scandit.datacapture.frameworks.barcode.ar.data.HighlightCallbackData;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import io.sentry.cache.EnvelopeCache;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeArAugmentationsCache.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 )2\u00020\u0001:\u0001)B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\tJ\u0016\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u000bJ\u0016\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u000fJ\u0016\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\rJ\u0014\u0010\u001e\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0007j\u0002`\bH\u0002J\u0006\u0010\u001f\u001a\u00020\u0016J\u0010\u0010 \u001a\u0004\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\u0007J\u0010\u0010!\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0017\u001a\u00020\u0007J\u0010\u0010\"\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0017\u001a\u00020\u0007J\u0010\u0010#\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0017\u001a\u00020\u0007J\u0014\u0010$\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0007j\u0002`\bH\u0002J\u0014\u0010%\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0007j\u0002`\bH\u0002J\u000e\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020(R\u001e\u0010\u0005\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\u00110\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\u0016\u0012\b\u0012\u00060\u0013j\u0002`\u0014\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;", "", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "(Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "annotationsCache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeId;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotation;", "barcodeArAnnotationProviderCallback", "Lcom/scandit/datacapture/frameworks/barcode/ar/data/AnnotationCallbackData;", "barcodeCheckHighlightProviderCallback", "Lcom/scandit/datacapture/frameworks/barcode/ar/data/HighlightCallbackData;", "highlightsCache", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlight;", "markedForDeletion", "Ljava/lang/Runnable;", "trackedBarcodeCache", "", "Lcom/scandit/datacapture/frameworks/barcode/ar/caches/TrackedBarcodeId;", "addAnnotation", "", "barcodeId", "annotation", "addAnnotationProviderCallback", PluginMethod.RETURN_CALLBACK, "addHighlight", "highlight", "addHighlightProviderCallback", "cancelDeletion", "clear", "getAnnotation", "getAnnotationProviderCallback", "getHighlight", "getHighlightProviderCallback", "performDeletion", "scheduleDeletion", "updateFromSession", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeArSession;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeArAugmentationsCache {
    private static final long DELETION_DELAY = 2000;
    private final ConcurrentHashMap<String, BarcodeArAnnotation> annotationsCache;
    private final ConcurrentHashMap<String, AnnotationCallbackData> barcodeArAnnotationProviderCallback;
    private final ConcurrentHashMap<String, HighlightCallbackData> barcodeCheckHighlightProviderCallback;
    private final ConcurrentHashMap<String, BarcodeArHighlight> highlightsCache;
    private final MainThread mainThread;
    private final ConcurrentHashMap<String, Runnable> markedForDeletion;
    private final ConcurrentHashMap<Integer, String> trackedBarcodeCache;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeArAugmentationsCache() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BarcodeArAugmentationsCache(MainThread mainThread) {
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.mainThread = mainThread;
        this.annotationsCache = new ConcurrentHashMap<>();
        this.highlightsCache = new ConcurrentHashMap<>();
        this.trackedBarcodeCache = new ConcurrentHashMap<>();
        this.barcodeCheckHighlightProviderCallback = new ConcurrentHashMap<>();
        this.barcodeArAnnotationProviderCallback = new ConcurrentHashMap<>();
        this.markedForDeletion = new ConcurrentHashMap<>();
    }

    public /* synthetic */ BarcodeArAugmentationsCache(DefaultMainThread defaultMainThread, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DefaultMainThread.INSTANCE.getInstance() : defaultMainThread);
    }

    public final void updateFromSession(BarcodeArSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        for (TrackedBarcode trackedBarcode : session.getAddedTrackedBarcodes()) {
            cancelDeletion(ExtensionsKt.getUniqueId(trackedBarcode.getBarcode()));
            this.trackedBarcodeCache.put(Integer.valueOf(trackedBarcode.getIdentifier()), ExtensionsKt.getUniqueId(trackedBarcode.getBarcode()));
        }
        Iterator<Integer> it = session.getRemovedTrackedBarcodes().iterator();
        while (it.hasNext()) {
            String remove = this.trackedBarcodeCache.remove(Integer.valueOf(it.next().intValue()));
            if (remove != null) {
                scheduleDeletion(remove);
            }
        }
    }

    public final void addHighlightProviderCallback(String barcodeId, HighlightCallbackData callback) {
        Intrinsics.checkNotNullParameter(barcodeId, "barcodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.barcodeCheckHighlightProviderCallback.put(barcodeId, callback);
    }

    public final HighlightCallbackData getHighlightProviderCallback(String barcodeId) {
        Intrinsics.checkNotNullParameter(barcodeId, "barcodeId");
        return this.barcodeCheckHighlightProviderCallback.get(barcodeId);
    }

    public final void addHighlight(String barcodeId, BarcodeArHighlight highlight) {
        Intrinsics.checkNotNullParameter(barcodeId, "barcodeId");
        Intrinsics.checkNotNullParameter(highlight, "highlight");
        this.highlightsCache.put(barcodeId, highlight);
    }

    public final BarcodeArHighlight getHighlight(String barcodeId) {
        Intrinsics.checkNotNullParameter(barcodeId, "barcodeId");
        return this.highlightsCache.get(barcodeId);
    }

    public final void addAnnotationProviderCallback(String barcodeId, AnnotationCallbackData callback) {
        Intrinsics.checkNotNullParameter(barcodeId, "barcodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.barcodeArAnnotationProviderCallback.put(barcodeId, callback);
    }

    public final AnnotationCallbackData getAnnotationProviderCallback(String barcodeId) {
        Intrinsics.checkNotNullParameter(barcodeId, "barcodeId");
        return this.barcodeArAnnotationProviderCallback.get(barcodeId);
    }

    public final void addAnnotation(String barcodeId, BarcodeArAnnotation annotation) {
        Intrinsics.checkNotNullParameter(barcodeId, "barcodeId");
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        this.annotationsCache.put(barcodeId, annotation);
    }

    public final BarcodeArAnnotation getAnnotation(String barcodeId) {
        Intrinsics.checkNotNullParameter(barcodeId, "barcodeId");
        return this.annotationsCache.get(barcodeId);
    }

    public final void clear() {
        Collection<Runnable> values = this.markedForDeletion.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        for (Runnable runnable : values) {
            MainThread mainThread = this.mainThread;
            Intrinsics.checkNotNull(runnable);
            mainThread.cancelDelayedRun(runnable);
        }
        this.markedForDeletion.clear();
        this.trackedBarcodeCache.clear();
        this.annotationsCache.clear();
        this.highlightsCache.clear();
        this.barcodeCheckHighlightProviderCallback.clear();
        this.barcodeArAnnotationProviderCallback.clear();
    }

    private final void scheduleDeletion(final String barcodeId) {
        cancelDeletion(barcodeId);
        Runnable runnable = new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.ar.caches.BarcodeArAugmentationsCache$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeArAugmentationsCache.scheduleDeletion$lambda$2(BarcodeArAugmentationsCache.this, barcodeId);
            }
        };
        this.markedForDeletion.put(barcodeId, runnable);
        this.mainThread.runDelayedOnMainThread(runnable, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleDeletion$lambda$2(BarcodeArAugmentationsCache this$0, String barcodeId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(barcodeId, "$barcodeId");
        this$0.performDeletion(barcodeId);
    }

    private final void performDeletion(String barcodeId) {
        this.annotationsCache.remove(barcodeId);
        this.highlightsCache.remove(barcodeId);
        this.barcodeCheckHighlightProviderCallback.remove(barcodeId);
        this.barcodeArAnnotationProviderCallback.remove(barcodeId);
        this.markedForDeletion.remove(barcodeId);
    }

    private final void cancelDeletion(String barcodeId) {
        Runnable remove = this.markedForDeletion.remove(barcodeId);
        if (remove != null) {
            this.mainThread.cancelDelayedRun(remove);
        }
    }
}
