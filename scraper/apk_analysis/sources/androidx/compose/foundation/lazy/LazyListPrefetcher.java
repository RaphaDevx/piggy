package androidx.compose.foundation.lazy;

import android.os.Trace;
import android.view.Choreographer;
import android.view.View;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.State;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tealium.library.DataSources;
import io.sentry.protocol.SentryThread;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListPrefetcher.android.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000 92\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u00019B3\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0013H\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0013H\u0016J\b\u0010'\u001a\u00020%H\u0016J\b\u0010(\u001a\u00020%H\u0016J\b\u0010)\u001a\u00020%H\u0016J\u0010\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\u0019H\u0002J\b\u00100\u001a\u00020%H\u0016J)\u00101\u001a\u00020%*\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b7\u00108R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006:"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListPrefetcher;", "Landroidx/compose/runtime/RememberObserver;", "Landroidx/compose/foundation/lazy/LazyListOnScrolledListener;", "Landroidx/compose/foundation/lazy/LazyListOnPostMeasureListener;", "Ljava/lang/Runnable;", "Landroid/view/Choreographer$FrameCallback;", "subcomposeLayoutState", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "stateOfItemsProvider", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", "itemContentFactory", "Landroidx/compose/foundation/lazy/LazyListItemContentFactory;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Landroid/view/View;", "(Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/runtime/State;Landroidx/compose/foundation/lazy/LazyListItemContentFactory;Landroid/view/View;)V", "averagePrecomposeTimeNs", "", "averagePremeasureTimeNs", "choreographer", "Landroid/view/Choreographer;", "kotlin.jvm.PlatformType", "indexToPrefetch", "", "isActive", "", "precomposedSlotHandle", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "prefetchScheduled", "premeasuringIsNeeded", "wasScrollingForward", "calculateAverageTime", "new", SentryThread.JsonKeys.CURRENT, "doFrame", "", "frameTimeNanos", "onAbandoned", "onForgotten", "onRemembered", "onScrolled", "delta", "", "precompose", "itemProvider", FirebaseAnalytics.Param.INDEX, "run", "onPostMeasure", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "childConstraints", "Landroidx/compose/ui/unit/Constraints;", "result", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "onPostMeasure-VKLhPVY", "(Landroidx/compose/ui/layout/SubcomposeMeasureScope;JLandroidx/compose/foundation/lazy/LazyListMeasureResult;)V", "Companion", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class LazyListPrefetcher implements RememberObserver, LazyListOnScrolledListener, LazyListOnPostMeasureListener, Runnable, Choreographer.FrameCallback {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static long frameIntervalNs;
    private long averagePrecomposeTimeNs;
    private long averagePremeasureTimeNs;
    private final Choreographer choreographer;
    private int indexToPrefetch;
    private boolean isActive;
    private final LazyListItemContentFactory itemContentFactory;
    private final LazyListState lazyListState;
    private SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle;
    private boolean prefetchScheduled;
    private boolean premeasuringIsNeeded;
    private final State<LazyListItemsProvider> stateOfItemsProvider;
    private final SubcomposeLayoutState subcomposeLayoutState;
    private final View view;
    private boolean wasScrollingForward;

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LazyListPrefetcher(SubcomposeLayoutState subcomposeLayoutState, LazyListState lazyListState, State<? extends LazyListItemsProvider> stateOfItemsProvider, LazyListItemContentFactory itemContentFactory, View view) {
        Intrinsics.checkNotNullParameter(subcomposeLayoutState, "subcomposeLayoutState");
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        Intrinsics.checkNotNullParameter(stateOfItemsProvider, "stateOfItemsProvider");
        Intrinsics.checkNotNullParameter(itemContentFactory, "itemContentFactory");
        Intrinsics.checkNotNullParameter(view, "view");
        this.subcomposeLayoutState = subcomposeLayoutState;
        this.lazyListState = lazyListState;
        this.stateOfItemsProvider = stateOfItemsProvider;
        this.itemContentFactory = itemContentFactory;
        this.view = view;
        this.indexToPrefetch = -1;
        this.choreographer = Choreographer.getInstance();
        INSTANCE.calculateFrameIntervalIfNeeded(view);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.indexToPrefetch != -1 && this.prefetchScheduled && this.isActive) {
            if (this.precomposedSlotHandle == null) {
                Trace.beginSection("compose:lazylist:prefetch:compose");
                try {
                    long nanos = TimeUnit.MILLISECONDS.toNanos(this.view.getDrawingTime()) + frameIntervalNs;
                    long nanoTime = System.nanoTime();
                    if (nanoTime <= nanos && this.averagePrecomposeTimeNs + nanoTime >= nanos) {
                        this.choreographer.postFrameCallback(this);
                        Unit unit = Unit.INSTANCE;
                        return;
                    }
                    int i = this.indexToPrefetch;
                    LazyListItemsProvider value = this.stateOfItemsProvider.getValue();
                    if (this.view.getWindowVisibility() == 0 && i >= 0 && i < value.getItemsCount()) {
                        this.precomposedSlotHandle = precompose(value, i);
                        this.averagePrecomposeTimeNs = calculateAverageTime(System.nanoTime() - nanoTime, this.averagePrecomposeTimeNs);
                        this.choreographer.postFrameCallback(this);
                    } else {
                        this.prefetchScheduled = false;
                    }
                    Unit unit2 = Unit.INSTANCE;
                    return;
                } finally {
                }
            }
            Trace.beginSection("compose:lazylist:prefetch:measure");
            try {
                long nanos2 = TimeUnit.MILLISECONDS.toNanos(this.view.getDrawingTime()) + frameIntervalNs;
                long nanoTime2 = System.nanoTime();
                if (nanoTime2 <= nanos2 && this.averagePremeasureTimeNs + nanoTime2 >= nanos2) {
                    this.choreographer.postFrameCallback(this);
                    Unit unit3 = Unit.INSTANCE;
                }
                if (this.view.getWindowVisibility() == 0) {
                    this.premeasuringIsNeeded = true;
                    this.lazyListState.getRemeasurement$foundation_release().forceRemeasure();
                    this.averagePremeasureTimeNs = calculateAverageTime(System.nanoTime() - nanoTime2, this.averagePremeasureTimeNs);
                }
                this.prefetchScheduled = false;
                Unit unit32 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long frameTimeNanos) {
        if (this.isActive) {
            this.view.post(this);
        }
    }

    private final SubcomposeLayoutState.PrecomposedSlotHandle precompose(LazyListItemsProvider itemProvider, int index) {
        Object key = itemProvider.getKey(index);
        return this.subcomposeLayoutState.precompose(key, this.itemContentFactory.getContent(index, key));
    }

    private final long calculateAverageTime(long r5, long current) {
        if (current == 0) {
            return r5;
        }
        long j = 4;
        return (r5 / j) + ((current / j) * 3);
    }

    @Override // androidx.compose.foundation.lazy.LazyListOnScrolledListener
    public void onScrolled(float delta) {
        int index;
        if (this.lazyListState.getPrefetchingEnabled()) {
            LazyListLayoutInfo layoutInfo = this.lazyListState.getLayoutInfo();
            if (layoutInfo.getVisibleItemsInfo().isEmpty()) {
                return;
            }
            if (!this.isActive) {
                throw new IllegalStateException("Check failed.".toString());
            }
            boolean z = delta < 0.0f;
            if (z) {
                index = ((LazyListItemInfo) CollectionsKt.last((List) layoutInfo.getVisibleItemsInfo())).getIndex() + 1;
            } else {
                index = ((LazyListItemInfo) CollectionsKt.first((List) layoutInfo.getVisibleItemsInfo())).getIndex() - 1;
            }
            if (index == this.indexToPrefetch || index < 0 || index >= layoutInfo.getTotalItemsCount()) {
                return;
            }
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposedSlotHandle;
            if (precomposedSlotHandle != null && this.wasScrollingForward != z) {
                precomposedSlotHandle.dispose();
            }
            this.wasScrollingForward = z;
            this.indexToPrefetch = index;
            this.precomposedSlotHandle = null;
            this.premeasuringIsNeeded = false;
            if (this.prefetchScheduled) {
                return;
            }
            this.prefetchScheduled = true;
            this.view.post(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    @Override // androidx.compose.foundation.lazy.LazyListOnPostMeasureListener
    /* renamed from: onPostMeasure-VKLhPVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void mo391onPostMeasureVKLhPVY(androidx.compose.ui.layout.SubcomposeMeasureScope r10, long r11, androidx.compose.foundation.lazy.LazyListMeasureResult r13) {
        /*
            r9 = this;
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "result"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            int r0 = r9.indexToPrefetch
            boolean r1 = r9.premeasuringIsNeeded
            if (r1 == 0) goto Lab
            r1 = -1
            if (r0 == r1) goto Lab
            boolean r2 = r9.isActive
            if (r2 == 0) goto L9d
            androidx.compose.runtime.State<androidx.compose.foundation.lazy.LazyListItemsProvider> r2 = r9.stateOfItemsProvider
            java.lang.Object r2 = r2.getValue()
            androidx.compose.foundation.lazy.LazyListItemsProvider r2 = (androidx.compose.foundation.lazy.LazyListItemsProvider) r2
            int r3 = r2.getItemsCount()
            if (r0 >= r3) goto Lab
            java.util.List r3 = r13.getVisibleItemsInfo()
            int r4 = r3.size()
            int r4 = r4 + r1
            r5 = 1
            r6 = 0
            if (r4 < 0) goto L48
            r7 = r6
        L33:
            int r8 = r7 + 1
            java.lang.Object r7 = r3.get(r7)
            androidx.compose.foundation.lazy.LazyListItemInfo r7 = (androidx.compose.foundation.lazy.LazyListItemInfo) r7
            int r7 = r7.getIndex()
            if (r7 != r0) goto L43
            r3 = r5
            goto L49
        L43:
            if (r8 <= r4) goto L46
            goto L48
        L46:
            r7 = r8
            goto L33
        L48:
            r3 = r6
        L49:
            java.util.List r4 = r13.getComposedButNotVisibleItems()
            if (r4 == 0) goto L6f
            java.util.List r13 = r13.getComposedButNotVisibleItems()
            int r4 = r13.size()
            int r4 = r4 + r1
            if (r4 < 0) goto L6f
            r7 = r6
        L5b:
            int r8 = r7 + 1
            java.lang.Object r7 = r13.get(r7)
            androidx.compose.foundation.lazy.LazyMeasuredItem r7 = (androidx.compose.foundation.lazy.LazyMeasuredItem) r7
            int r7 = r7.getIndex()
            if (r7 != r0) goto L6a
            goto L70
        L6a:
            if (r8 <= r4) goto L6d
            goto L6f
        L6d:
            r7 = r8
            goto L5b
        L6f:
            r5 = r6
        L70:
            if (r3 != 0) goto L9a
            if (r5 == 0) goto L75
            goto L9a
        L75:
            java.lang.Object r13 = r2.getKey(r0)
            androidx.compose.foundation.lazy.LazyListItemContentFactory r9 = r9.itemContentFactory
            kotlin.jvm.functions.Function2 r9 = r9.getContent(r0, r13)
            java.util.List r9 = r10.subcompose(r13, r9)
            int r10 = r9.size()
            int r10 = r10 + r1
            if (r10 < 0) goto Lab
        L8a:
            int r13 = r6 + 1
            java.lang.Object r0 = r9.get(r6)
            androidx.compose.ui.layout.Measurable r0 = (androidx.compose.ui.layout.Measurable) r0
            r0.mo2510measureBRTryo0(r11)
            if (r13 <= r10) goto L98
            goto Lab
        L98:
            r6 = r13
            goto L8a
        L9a:
            r9.premeasuringIsNeeded = r6
            goto Lab
        L9d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Check failed."
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            throw r9
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListPrefetcher.mo391onPostMeasureVKLhPVY(androidx.compose.ui.layout.SubcomposeMeasureScope, long, androidx.compose.foundation.lazy.LazyListMeasureResult):void");
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        this.lazyListState.setOnScrolledListener$foundation_release(this);
        this.lazyListState.setOnPostMeasureListener$foundation_release(this);
        this.isActive = true;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        this.isActive = false;
        this.lazyListState.setOnScrolledListener$foundation_release(null);
        this.lazyListState.setOnPostMeasureListener$foundation_release(null);
        this.view.removeCallbacks(this);
        this.choreographer.removeFrameCallback(this);
    }

    /* compiled from: LazyListPrefetcher.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListPrefetcher$Companion;", "", "()V", "frameIntervalNs", "", "calculateFrameIntervalIfNeeded", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Landroid/view/View;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x001e, code lost:
        
            if (r4 >= 30.0f) goto L11;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void calculateFrameIntervalIfNeeded(android.view.View r5) {
            /*
                r4 = this;
                long r0 = androidx.compose.foundation.lazy.LazyListPrefetcher.access$getFrameIntervalNs$cp()
                r2 = 0
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 != 0) goto L2c
                android.view.Display r4 = r5.getDisplay()
                boolean r5 = r5.isInEditMode()
                if (r5 != 0) goto L21
                if (r4 == 0) goto L21
                float r4 = r4.getRefreshRate()
                r5 = 1106247680(0x41f00000, float:30.0)
                int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r5 < 0) goto L21
                goto L23
            L21:
                r4 = 1114636288(0x42700000, float:60.0)
            L23:
                r5 = 1000000000(0x3b9aca00, float:0.0047237873)
                float r5 = (float) r5
                float r5 = r5 / r4
                long r4 = (long) r5
                androidx.compose.foundation.lazy.LazyListPrefetcher.access$setFrameIntervalNs$cp(r4)
            L2c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListPrefetcher.Companion.calculateFrameIntervalIfNeeded(android.view.View):void");
        }
    }
}
