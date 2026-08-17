package androidx.compose.foundation.lazy;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListState.kt */
@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 n2\u00020\u0001:\u0001nB\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J#\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u00032\b\b\u0002\u0010R\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010SJ\u0015\u0010T\u001a\u00020P2\u0006\u0010U\u001a\u00020VH\u0000¢\u0006\u0002\bWJ\u0010\u0010X\u001a\u00020F2\u0006\u0010Y\u001a\u00020FH\u0016J\u0015\u0010Z\u001a\u00020F2\u0006\u0010[\u001a\u00020FH\u0000¢\u0006\u0002\b\\JB\u0010]\u001a\u00020P2\u0006\u0010^\u001a\u00020_2'\u0010`\u001a#\b\u0001\u0012\u0004\u0012\u00020b\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0c\u0012\u0006\u0012\u0004\u0018\u00010d0a¢\u0006\u0002\beH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010fJ#\u0010g\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u00032\b\b\u0002\u0010R\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010SJ\u001d\u0010h\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u0003H\u0000¢\u0006\u0002\biJ\u0015\u0010j\u001a\u00020P2\u0006\u0010k\u001a\u00020lH\u0000¢\u0006\u0002\bmR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u00020\u00128@X\u0080\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0&X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010(\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0003@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0010R\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00100\u001a\u0004\u0018\u000101X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010 \"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020;X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020AX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010G\u001a\u00020F2\u0006\u0010'\u001a\u00020F@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u000e\u0010J\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010K\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0010\"\u0004\bM\u0010N\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006o"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "firstVisibleItemIndex", "", "firstVisibleItemScrollOffset", "(II)V", "canScrollBackward", "", "canScrollForward", "density", "Landroidx/compose/ui/unit/Density;", "getDensity$foundation_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation_release", "(Landroidx/compose/ui/unit/Density;)V", "getFirstVisibleItemIndex", "()I", "firstVisibleItemIndexNonObservable", "Landroidx/compose/foundation/lazy/DataIndex;", "getFirstVisibleItemIndexNonObservable-jQJCoq8$foundation_release", "getFirstVisibleItemScrollOffset", "firstVisibleItemScrollOffsetNonObservable", "getFirstVisibleItemScrollOffsetNonObservable$foundation_release", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/InteractionSource;", "internalInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInternalInteractionSource$foundation_release", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "isScrollInProgress", "()Z", "layoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "getLayoutInfo", "()Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "layoutInfoState", "Landroidx/compose/runtime/MutableState;", "<set-?>", "numMeasurePasses", "getNumMeasurePasses$foundation_release", "onPostMeasureListener", "Landroidx/compose/foundation/lazy/LazyListOnPostMeasureListener;", "getOnPostMeasureListener$foundation_release", "()Landroidx/compose/foundation/lazy/LazyListOnPostMeasureListener;", "setOnPostMeasureListener$foundation_release", "(Landroidx/compose/foundation/lazy/LazyListOnPostMeasureListener;)V", "onScrolledListener", "Landroidx/compose/foundation/lazy/LazyListOnScrolledListener;", "getOnScrolledListener$foundation_release", "()Landroidx/compose/foundation/lazy/LazyListOnScrolledListener;", "setOnScrolledListener$foundation_release", "(Landroidx/compose/foundation/lazy/LazyListOnScrolledListener;)V", "prefetchingEnabled", "getPrefetchingEnabled$foundation_release", "setPrefetchingEnabled$foundation_release", "(Z)V", "remeasurement", "Landroidx/compose/ui/layout/Remeasurement;", "getRemeasurement$foundation_release", "()Landroidx/compose/ui/layout/Remeasurement;", "setRemeasurement$foundation_release", "(Landroidx/compose/ui/layout/Remeasurement;)V", "remeasurementModifier", "Landroidx/compose/ui/layout/RemeasurementModifier;", "getRemeasurementModifier$foundation_release", "()Landroidx/compose/ui/layout/RemeasurementModifier;", "scrollPosition", "Landroidx/compose/foundation/lazy/LazyListScrollPosition;", "", "scrollToBeConsumed", "getScrollToBeConsumed$foundation_release", "()F", "scrollableState", "visibleItemsCount", "getVisibleItemsCount$foundation_release", "setVisibleItemsCount$foundation_release", "(I)V", "animateScrollToItem", "", FirebaseAnalytics.Param.INDEX, "scrollOffset", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "applyMeasureResult", "result", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "applyMeasureResult$foundation_release", "dispatchRawDelta", "delta", "onScroll", "distance", "onScroll$foundation_release", "scroll", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollToItem", "snapToItemIndexInternal", "snapToItemIndexInternal$foundation_release", "updateScrollPositionIfTheFirstItemWasMoved", "itemsProvider", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", "updateScrollPositionIfTheFirstItemWasMoved$foundation_release", "Companion", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListState implements ScrollableState {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<LazyListState, ?> Saver = ListSaverKt.listSaver(new Function2<SaverScope, LazyListState, List<? extends Integer>>() { // from class: androidx.compose.foundation.lazy.LazyListState$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final List<Integer> invoke(SaverScope listSaver, LazyListState it) {
            Intrinsics.checkNotNullParameter(listSaver, "$this$listSaver");
            Intrinsics.checkNotNullParameter(it, "it");
            return CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(it.getFirstVisibleItemIndex()), Integer.valueOf(it.getFirstVisibleItemScrollOffset())});
        }
    }, new Function1<List<? extends Integer>, LazyListState>() { // from class: androidx.compose.foundation.lazy.LazyListState$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ LazyListState invoke(List<? extends Integer> list) {
            return invoke2((List<Integer>) list);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final LazyListState invoke2(List<Integer> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return new LazyListState(it.get(0).intValue(), it.get(1).intValue());
        }
    });
    private boolean canScrollBackward;
    private boolean canScrollForward;
    private Density density;
    private final MutableInteractionSource internalInteractionSource;
    private final MutableState<LazyListLayoutInfo> layoutInfoState;
    private int numMeasurePasses;
    private LazyListOnPostMeasureListener onPostMeasureListener;
    private LazyListOnScrolledListener onScrolledListener;
    private boolean prefetchingEnabled;
    public Remeasurement remeasurement;
    private final RemeasurementModifier remeasurementModifier;
    private final LazyListScrollPosition scrollPosition;
    private float scrollToBeConsumed;
    private final ScrollableState scrollableState;
    private int visibleItemsCount;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public LazyListState() {
        /*
            r3 = this;
            r0 = 3
            r1 = 0
            r2 = 0
            r3.<init>(r2, r2, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListState.<init>():void");
    }

    public LazyListState(int i, int i2) {
        this.scrollPosition = new LazyListScrollPosition(i, i2);
        this.layoutInfoState = SnapshotStateKt.mutableStateOf$default(EmptyLazyListLayoutInfo.INSTANCE, null, 2, null);
        this.internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.density = DensityKt.Density(1.0f, 1.0f);
        this.scrollableState = ScrollableStateKt.ScrollableState(new Function1<Float, Float>() { // from class: androidx.compose.foundation.lazy.LazyListState$scrollableState$1
            {
                super(1);
            }

            public final float invoke(float f) {
                return -LazyListState.this.onScroll$foundation_release(-f);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return Float.valueOf(invoke(f.floatValue()));
            }
        });
        this.prefetchingEnabled = true;
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.lazy.LazyListState$remeasurementModifier$1
            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public boolean all(Function1<? super Modifier.Element, Boolean> function1) {
                return RemeasurementModifier.DefaultImpls.all(this, function1);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public boolean any(Function1<? super Modifier.Element, Boolean> function1) {
                return RemeasurementModifier.DefaultImpls.any(this, function1);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public <R> R foldIn(R r, Function2<? super R, ? super Modifier.Element, ? extends R> function2) {
                return (R) RemeasurementModifier.DefaultImpls.foldIn(this, r, function2);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public <R> R foldOut(R r, Function2<? super Modifier.Element, ? super R, ? extends R> function2) {
                return (R) RemeasurementModifier.DefaultImpls.foldOut(this, r, function2);
            }

            @Override // androidx.compose.ui.Modifier
            public Modifier then(Modifier modifier) {
                return RemeasurementModifier.DefaultImpls.then(this, modifier);
            }

            @Override // androidx.compose.ui.layout.RemeasurementModifier
            public void onRemeasurementAvailable(Remeasurement remeasurement) {
                Intrinsics.checkNotNullParameter(remeasurement, "remeasurement");
                LazyListState.this.setRemeasurement$foundation_release(remeasurement);
            }
        };
    }

    public /* synthetic */ LazyListState(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final int getFirstVisibleItemIndex() {
        return this.scrollPosition.getObservableIndex();
    }

    public final int getFirstVisibleItemScrollOffset() {
        return this.scrollPosition.getObservableScrollOffset();
    }

    public final LazyListLayoutInfo getLayoutInfo() {
        return this.layoutInfoState.getValue();
    }

    public final InteractionSource getInteractionSource() {
        return this.internalInteractionSource;
    }

    /* renamed from: getInternalInteractionSource$foundation_release, reason: from getter */
    public final MutableInteractionSource getInternalInteractionSource() {
        return this.internalInteractionSource;
    }

    /* renamed from: getScrollToBeConsumed$foundation_release, reason: from getter */
    public final float getScrollToBeConsumed() {
        return this.scrollToBeConsumed;
    }

    /* renamed from: getFirstVisibleItemIndexNonObservable-jQJCoq8$foundation_release, reason: not valid java name */
    public final int m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release() {
        return this.scrollPosition.getIndex();
    }

    public final int getFirstVisibleItemScrollOffsetNonObservable$foundation_release() {
        return this.scrollPosition.getScrollOffset();
    }

    /* renamed from: getVisibleItemsCount$foundation_release, reason: from getter */
    public final int getVisibleItemsCount() {
        return this.visibleItemsCount;
    }

    public final void setVisibleItemsCount$foundation_release(int i) {
        this.visibleItemsCount = i;
    }

    /* renamed from: getDensity$foundation_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity$foundation_release(Density density) {
        Intrinsics.checkNotNullParameter(density, "<set-?>");
        this.density = density;
    }

    public final Remeasurement getRemeasurement$foundation_release() {
        Remeasurement remeasurement = this.remeasurement;
        if (remeasurement != null) {
            return remeasurement;
        }
        Intrinsics.throwUninitializedPropertyAccessException("remeasurement");
        throw null;
    }

    public final void setRemeasurement$foundation_release(Remeasurement remeasurement) {
        Intrinsics.checkNotNullParameter(remeasurement, "<set-?>");
        this.remeasurement = remeasurement;
    }

    /* renamed from: getNumMeasurePasses$foundation_release, reason: from getter */
    public final int getNumMeasurePasses() {
        return this.numMeasurePasses;
    }

    /* renamed from: getPrefetchingEnabled$foundation_release, reason: from getter */
    public final boolean getPrefetchingEnabled() {
        return this.prefetchingEnabled;
    }

    public final void setPrefetchingEnabled$foundation_release(boolean z) {
        this.prefetchingEnabled = z;
    }

    /* renamed from: getRemeasurementModifier$foundation_release, reason: from getter */
    public final RemeasurementModifier getRemeasurementModifier() {
        return this.remeasurementModifier;
    }

    public static /* synthetic */ Object scrollToItem$default(LazyListState lazyListState, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return lazyListState.scrollToItem(i, i2, continuation);
    }

    public final Object scrollToItem(int i, int i2, Continuation<? super Unit> continuation) {
        Object scroll$default = ScrollableState.DefaultImpls.scroll$default(this.scrollableState, null, new LazyListState$scrollToItem$2(this, i, i2, null), continuation, 1, null);
        return scroll$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scroll$default : Unit.INSTANCE;
    }

    public final void snapToItemIndexInternal$foundation_release(int index, int scrollOffset) {
        this.scrollPosition.m394requestPositionAhXoVpI(DataIndex.m371constructorimpl(index), scrollOffset);
        getRemeasurement$foundation_release().forceRemeasure();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public Object scroll(MutatePriority mutatePriority, Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object scroll = this.scrollableState.scroll(mutatePriority, function2, continuation);
        return scroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scroll : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public float dispatchRawDelta(float delta) {
        return this.scrollableState.dispatchRawDelta(delta);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    /* renamed from: getOnScrolledListener$foundation_release, reason: from getter */
    public final LazyListOnScrolledListener getOnScrolledListener() {
        return this.onScrolledListener;
    }

    public final void setOnScrolledListener$foundation_release(LazyListOnScrolledListener lazyListOnScrolledListener) {
        this.onScrolledListener = lazyListOnScrolledListener;
    }

    /* renamed from: getOnPostMeasureListener$foundation_release, reason: from getter */
    public final LazyListOnPostMeasureListener getOnPostMeasureListener() {
        return this.onPostMeasureListener;
    }

    public final void setOnPostMeasureListener$foundation_release(LazyListOnPostMeasureListener lazyListOnPostMeasureListener) {
        this.onPostMeasureListener = lazyListOnPostMeasureListener;
    }

    public final float onScroll$foundation_release(float distance) {
        if ((distance < 0.0f && !this.canScrollForward) || (distance > 0.0f && !this.canScrollBackward)) {
            return 0.0f;
        }
        if (!(Math.abs(this.scrollToBeConsumed) <= 0.5f)) {
            throw new IllegalStateException(Intrinsics.stringPlus("entered drag with non-zero pending scroll: ", Float.valueOf(getScrollToBeConsumed())).toString());
        }
        float f = this.scrollToBeConsumed + distance;
        this.scrollToBeConsumed = f;
        if (Math.abs(f) > 0.5f) {
            float f2 = this.scrollToBeConsumed;
            getRemeasurement$foundation_release().forceRemeasure();
            LazyListOnScrolledListener lazyListOnScrolledListener = this.onScrolledListener;
            if (lazyListOnScrolledListener != null) {
                lazyListOnScrolledListener.onScrolled(f2 - this.scrollToBeConsumed);
            }
        }
        if (Math.abs(this.scrollToBeConsumed) <= 0.5f) {
            return distance;
        }
        float f3 = distance - this.scrollToBeConsumed;
        this.scrollToBeConsumed = 0.0f;
        return f3;
    }

    public static /* synthetic */ Object animateScrollToItem$default(LazyListState lazyListState, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return lazyListState.animateScrollToItem(i, i2, continuation);
    }

    public final Object animateScrollToItem(int i, int i2, Continuation<? super Unit> continuation) {
        Object doSmoothScrollToItem = LazyListScrollingKt.doSmoothScrollToItem(this, i, i2, continuation);
        return doSmoothScrollToItem == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? doSmoothScrollToItem : Unit.INSTANCE;
    }

    public final void applyMeasureResult$foundation_release(LazyListMeasureResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.visibleItemsCount = result.getVisibleItemsInfo().size();
        this.scrollPosition.updateFromMeasureResult(result);
        this.scrollToBeConsumed -= result.getConsumedScroll();
        this.layoutInfoState.setValue(result);
        this.canScrollForward = result.getCanScrollForward();
        LazyMeasuredItem firstVisibleItem = result.getFirstVisibleItem();
        this.canScrollBackward = ((firstVisibleItem == null ? 0 : firstVisibleItem.getIndex()) == 0 && result.getFirstVisibleItemScrollOffset() == 0) ? false : true;
        this.numMeasurePasses++;
    }

    public final void updateScrollPositionIfTheFirstItemWasMoved$foundation_release(LazyListItemsProvider itemsProvider) {
        Intrinsics.checkNotNullParameter(itemsProvider, "itemsProvider");
        this.scrollPosition.updateScrollPositionIfTheFirstItemWasMoved(itemsProvider);
    }

    /* compiled from: LazyListState.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/lazy/LazyListState;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<LazyListState, ?> getSaver() {
            return LazyListState.Saver;
        }
    }
}
