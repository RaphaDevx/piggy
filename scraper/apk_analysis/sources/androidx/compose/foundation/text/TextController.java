package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.MultiWidgetSelectionDelegate;
import androidx.compose.foundation.text.selection.Selectable;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: CoreText.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020,2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\f\u0010-\u001a\u00020\u0015*\u00020\u0015H\u0003R\"\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"Landroidx/compose/foundation/text/TextController;", "", SentryThread.JsonKeys.STATE, "Landroidx/compose/foundation/text/TextState;", "(Landroidx/compose/foundation/text/TextState;)V", "commit", "Lkotlin/Function1;", "Landroidx/compose/runtime/DisposableEffectScope;", "Landroidx/compose/runtime/DisposableEffectResult;", "Lkotlin/ExtensionFunctionType;", "getCommit", "()Lkotlin/jvm/functions/Function1;", "longPressDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "getLongPressDragObserver", "()Landroidx/compose/foundation/text/TextDragObserver;", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "getMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "modifiers", "Landroidx/compose/ui/Modifier;", "getModifiers", "()Landroidx/compose/ui/Modifier;", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "getMouseSelectionObserver", "()Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "getSelectionRegistrar", "()Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "setSelectionRegistrar", "(Landroidx/compose/foundation/text/selection/SelectionRegistrar;)V", "getState", "()Landroidx/compose/foundation/text/TextState;", "outOfBoundary", "", "start", "Landroidx/compose/ui/geometry/Offset;", "end", "outOfBoundary-0a9Yr6o", "(JJ)Z", "update", "", "drawTextAndSelectionBehind", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextController {
    private final Function1<DisposableEffectScope, DisposableEffectResult> commit;
    private final TextDragObserver longPressDragObserver;
    private final MeasurePolicy measurePolicy;
    private final Modifier modifiers;
    private final MouseSelectionObserver mouseSelectionObserver;
    private SelectionRegistrar selectionRegistrar;
    private final TextState state;

    public TextController(TextState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
        this.modifiers = SemanticsModifierKt.semantics$default(OnGloballyPositionedModifierKt.onGloballyPositioned(drawTextAndSelectionBehind(Modifier.INSTANCE), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.TextController$modifiers$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                invoke2(layoutCoordinates);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutCoordinates it) {
                SelectionRegistrar selectionRegistrar;
                Intrinsics.checkNotNullParameter(it, "it");
                TextController.this.getState().setLayoutCoordinates(it);
                if (SelectionRegistrarKt.hasSelection(TextController.this.getSelectionRegistrar(), TextController.this.getState().getSelectableId())) {
                    long positionInWindow = LayoutCoordinatesKt.positionInWindow(it);
                    if (!Offset.m1022equalsimpl0(positionInWindow, TextController.this.getState().getPreviousGlobalPosition()) && (selectionRegistrar = TextController.this.getSelectionRegistrar()) != null) {
                        selectionRegistrar.notifyPositionChange(TextController.this.getState().getSelectableId());
                    }
                    TextController.this.getState().m534setPreviousGlobalPositionk4lQ0M(positionInWindow);
                }
            }
        }), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.TextController$modifiers$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                final TextController textController = TextController.this;
                SemanticsPropertiesKt.getTextLayoutResult$default(semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.TextController$modifiers$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(List<TextLayoutResult> list) {
                        return Boolean.valueOf(invoke2(list));
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2(List<TextLayoutResult> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        if (TextController.this.getState().getLayoutResult() == null) {
                            return false;
                        }
                        TextLayoutResult layoutResult = TextController.this.getState().getLayoutResult();
                        Intrinsics.checkNotNull(layoutResult);
                        it.add(layoutResult);
                        return true;
                    }
                }, 1, null);
            }
        }, 1, null);
        this.measurePolicy = new MeasurePolicy() { // from class: androidx.compose.foundation.text.TextController$measurePolicy$1
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* renamed from: measure-3p2s80s */
            public MeasureResult mo40measure3p2s80s(MeasureScope receiver, List<? extends Measurable> measurables, long j) {
                SelectionRegistrar selectionRegistrar;
                Intrinsics.checkNotNullParameter(receiver, "$receiver");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                TextLayoutResult m504layoutNN6EwU = TextController.this.getState().getTextDelegate().m504layoutNN6EwU(j, receiver.getLayoutDirection(), TextController.this.getState().getLayoutResult());
                if (!Intrinsics.areEqual(TextController.this.getState().getLayoutResult(), m504layoutNN6EwU)) {
                    TextController.this.getState().getOnTextLayout().invoke(m504layoutNN6EwU);
                    TextLayoutResult layoutResult = TextController.this.getState().getLayoutResult();
                    if (layoutResult != null) {
                        TextController textController = TextController.this;
                        if (!Intrinsics.areEqual(layoutResult.getLayoutInput().getText(), m504layoutNN6EwU.getLayoutInput().getText()) && (selectionRegistrar = textController.getSelectionRegistrar()) != null) {
                            selectionRegistrar.notifySelectableChange(textController.getState().getSelectableId());
                        }
                    }
                }
                TextController.this.getState().setLayoutResult(m504layoutNN6EwU);
                if (!(measurables.size() >= m504layoutNN6EwU.getPlaceholderRects().size())) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                List<Rect> placeholderRects = m504layoutNN6EwU.getPlaceholderRects();
                ArrayList arrayList = new ArrayList(placeholderRects.size());
                int size = placeholderRects.size() - 1;
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        int i2 = i + 1;
                        Rect rect = placeholderRects.get(i);
                        Pair pair = rect == null ? null : new Pair(measurables.get(i).mo2510measureBRTryo0(ConstraintsKt.Constraints$default(0, (int) Math.floor(rect.getWidth()), 0, (int) Math.floor(rect.getHeight()), 5, null)), IntOffset.m3076boximpl(IntOffsetKt.IntOffset(MathKt.roundToInt(rect.getLeft()), MathKt.roundToInt(rect.getTop()))));
                        if (pair != null) {
                            arrayList.add(pair);
                        }
                        if (i2 > size) {
                            break;
                        }
                        i = i2;
                    }
                }
                final ArrayList arrayList2 = arrayList;
                return receiver.layout(IntSize.m3127getWidthimpl(m504layoutNN6EwU.getSize()), IntSize.m3126getHeightimpl(m504layoutNN6EwU.getSize()), MapsKt.mapOf(TuplesKt.to(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(MathKt.roundToInt(m504layoutNN6EwU.getFirstBaseline()))), TuplesKt.to(AlignmentLineKt.getLastBaseline(), Integer.valueOf(MathKt.roundToInt(m504layoutNN6EwU.getLastBaseline())))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.TextController$measurePolicy$1$measure$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope layout) {
                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                        List<Pair<Placeable, IntOffset>> list = arrayList2;
                        int size2 = list.size() - 1;
                        if (size2 < 0) {
                            return;
                        }
                        int i3 = 0;
                        while (true) {
                            int i4 = i3 + 1;
                            Pair<Placeable, IntOffset> pair2 = list.get(i3);
                            Placeable.PlacementScope.m2549placeRelative70tqf50$default(layout, pair2.getFirst(), pair2.getSecond().getPackedValue(), 0.0f, 2, null);
                            if (i4 > size2) {
                                return;
                            } else {
                                i3 = i4;
                            }
                        }
                    }
                });
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
                Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                TextController.this.getState().getTextDelegate().layoutIntrinsics(intrinsicMeasureScope.getLayoutDirection());
                return TextController.this.getState().getTextDelegate().getMinIntrinsicWidth();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
                Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                return IntSize.m3126getHeightimpl(TextDelegate.m501layoutNN6EwU$default(TextController.this.getState().getTextDelegate(), ConstraintsKt.Constraints(0, i, 0, Integer.MAX_VALUE), intrinsicMeasureScope.getLayoutDirection(), null, 4, null).getSize());
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
                Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                TextController.this.getState().getTextDelegate().layoutIntrinsics(intrinsicMeasureScope.getLayoutDirection());
                return TextController.this.getState().getTextDelegate().getMaxIntrinsicWidth();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
                Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                return IntSize.m3126getHeightimpl(TextDelegate.m501layoutNN6EwU$default(TextController.this.getState().getTextDelegate(), ConstraintsKt.Constraints(0, i, 0, Integer.MAX_VALUE), intrinsicMeasureScope.getLayoutDirection(), null, 4, null).getSize());
            }
        };
        this.commit = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.TextController$commit$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                Intrinsics.checkNotNullParameter(disposableEffectScope, "$this$null");
                SelectionRegistrar selectionRegistrar = TextController.this.getSelectionRegistrar();
                if (selectionRegistrar != null) {
                    final TextController textController = TextController.this;
                    textController.getState().setSelectable(selectionRegistrar.subscribe(new MultiWidgetSelectionDelegate(textController.getState().getSelectableId(), new Function0<LayoutCoordinates>() { // from class: androidx.compose.foundation.text.TextController$commit$1$1$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final LayoutCoordinates invoke() {
                            return TextController.this.getState().getLayoutCoordinates();
                        }
                    }, new Function0<TextLayoutResult>() { // from class: androidx.compose.foundation.text.TextController$commit$1$1$2
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final TextLayoutResult invoke() {
                            return TextController.this.getState().getLayoutResult();
                        }
                    })));
                }
                final TextController textController2 = TextController.this;
                return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.TextController$commit$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        SelectionRegistrar selectionRegistrar2;
                        Selectable selectable = TextController.this.getState().getSelectable();
                        if (selectable == null || (selectionRegistrar2 = TextController.this.getSelectionRegistrar()) == null) {
                            return;
                        }
                        selectionRegistrar2.unsubscribe(selectable);
                    }
                };
            }
        };
        this.longPressDragObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.TextController$longPressDragObserver$1
            private long dragBeginPosition = Offset.INSTANCE.m1041getZeroF1C5BW0();
            private long dragTotalDistance = Offset.INSTANCE.m1041getZeroF1C5BW0();

            /* renamed from: getDragBeginPosition-F1C5BW0, reason: not valid java name and from getter */
            public final long getDragBeginPosition() {
                return this.dragBeginPosition;
            }

            /* renamed from: setDragBeginPosition-k-4lQ0M, reason: not valid java name */
            public final void m493setDragBeginPositionk4lQ0M(long j) {
                this.dragBeginPosition = j;
            }

            /* renamed from: getDragTotalDistance-F1C5BW0, reason: not valid java name and from getter */
            public final long getDragTotalDistance() {
                return this.dragTotalDistance;
            }

            /* renamed from: setDragTotalDistance-k-4lQ0M, reason: not valid java name */
            public final void m494setDragTotalDistancek4lQ0M(long j) {
                this.dragTotalDistance = j;
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M, reason: not valid java name */
            public void mo492onStartk4lQ0M(long startPoint) {
                boolean m488outOfBoundary0a9Yr6o;
                LayoutCoordinates layoutCoordinates = TextController.this.getState().getLayoutCoordinates();
                if (layoutCoordinates != null) {
                    TextController textController = TextController.this;
                    if (!layoutCoordinates.isAttached()) {
                        return;
                    }
                    m488outOfBoundary0a9Yr6o = textController.m488outOfBoundary0a9Yr6o(startPoint, startPoint);
                    if (m488outOfBoundary0a9Yr6o) {
                        SelectionRegistrar selectionRegistrar = textController.getSelectionRegistrar();
                        if (selectionRegistrar != null) {
                            selectionRegistrar.notifySelectionUpdateSelectAll(textController.getState().getSelectableId());
                        }
                    } else {
                        SelectionRegistrar selectionRegistrar2 = textController.getSelectionRegistrar();
                        if (selectionRegistrar2 != null) {
                            selectionRegistrar2.mo580notifySelectionUpdateStartd4ec7I(layoutCoordinates, startPoint, SelectionAdjustment.WORD);
                        }
                    }
                    m493setDragBeginPositionk4lQ0M(startPoint);
                }
                if (SelectionRegistrarKt.hasSelection(TextController.this.getSelectionRegistrar(), TextController.this.getState().getSelectableId())) {
                    m494setDragTotalDistancek4lQ0M(Offset.INSTANCE.m1041getZeroF1C5BW0());
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M, reason: not valid java name */
            public void mo491onDragk4lQ0M(long delta) {
                boolean m488outOfBoundary0a9Yr6o;
                SelectionRegistrar selectionRegistrar;
                LayoutCoordinates layoutCoordinates = TextController.this.getState().getLayoutCoordinates();
                if (layoutCoordinates == null) {
                    return;
                }
                TextController textController = TextController.this;
                if (layoutCoordinates.isAttached() && SelectionRegistrarKt.hasSelection(textController.getSelectionRegistrar(), textController.getState().getSelectableId())) {
                    m494setDragTotalDistancek4lQ0M(Offset.m1030plusMKHz9U(getDragTotalDistance(), delta));
                    m488outOfBoundary0a9Yr6o = textController.m488outOfBoundary0a9Yr6o(getDragBeginPosition(), Offset.m1030plusMKHz9U(getDragBeginPosition(), getDragTotalDistance()));
                    if (m488outOfBoundary0a9Yr6o || (selectionRegistrar = textController.getSelectionRegistrar()) == null) {
                        return;
                    }
                    selectionRegistrar.mo578notifySelectionUpdateDUneCvk(layoutCoordinates, getDragBeginPosition(), Offset.m1030plusMKHz9U(getDragBeginPosition(), getDragTotalDistance()), SelectionAdjustment.CHARACTER);
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                SelectionRegistrar selectionRegistrar;
                if (!SelectionRegistrarKt.hasSelection(TextController.this.getSelectionRegistrar(), TextController.this.getState().getSelectableId()) || (selectionRegistrar = TextController.this.getSelectionRegistrar()) == null) {
                    return;
                }
                selectionRegistrar.notifySelectionUpdateEnd();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                SelectionRegistrar selectionRegistrar;
                if (!SelectionRegistrarKt.hasSelection(TextController.this.getSelectionRegistrar(), TextController.this.getState().getSelectableId()) || (selectionRegistrar = TextController.this.getSelectionRegistrar()) == null) {
                    return;
                }
                selectionRegistrar.notifySelectionUpdateEnd();
            }
        };
        this.mouseSelectionObserver = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.TextController$mouseSelectionObserver$1
            private long dragBeginPosition = Offset.INSTANCE.m1041getZeroF1C5BW0();

            /* renamed from: getDragBeginPosition-F1C5BW0, reason: not valid java name and from getter */
            public final long getDragBeginPosition() {
                return this.dragBeginPosition;
            }

            /* renamed from: setDragBeginPosition-k-4lQ0M, reason: not valid java name */
            public final void m500setDragBeginPositionk4lQ0M(long j) {
                this.dragBeginPosition = j;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onExtend-k-4lQ0M, reason: not valid java name */
            public boolean mo497onExtendk4lQ0M(long downPosition) {
                LayoutCoordinates layoutCoordinates = TextController.this.getState().getLayoutCoordinates();
                if (layoutCoordinates == null) {
                    return false;
                }
                TextController textController = TextController.this;
                if (!layoutCoordinates.isAttached()) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar = textController.getSelectionRegistrar();
                if (selectionRegistrar != null) {
                    selectionRegistrar.mo579notifySelectionUpdated4ec7I(layoutCoordinates, downPosition, SelectionAdjustment.NONE);
                }
                return SelectionRegistrarKt.hasSelection(textController.getSelectionRegistrar(), textController.getState().getSelectableId());
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
            public boolean mo498onExtendDragk4lQ0M(long dragPosition) {
                LayoutCoordinates layoutCoordinates = TextController.this.getState().getLayoutCoordinates();
                if (layoutCoordinates == null) {
                    return true;
                }
                TextController textController = TextController.this;
                if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(textController.getSelectionRegistrar(), textController.getState().getSelectableId())) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar = textController.getSelectionRegistrar();
                if (selectionRegistrar == null) {
                    return true;
                }
                selectionRegistrar.mo579notifySelectionUpdated4ec7I(layoutCoordinates, dragPosition, SelectionAdjustment.NONE);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onStart-3MmeM6k, reason: not valid java name */
            public boolean mo499onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
                Intrinsics.checkNotNullParameter(adjustment, "adjustment");
                LayoutCoordinates layoutCoordinates = TextController.this.getState().getLayoutCoordinates();
                if (layoutCoordinates == null) {
                    return false;
                }
                TextController textController = TextController.this;
                if (!layoutCoordinates.isAttached()) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar = textController.getSelectionRegistrar();
                if (selectionRegistrar != null) {
                    selectionRegistrar.mo578notifySelectionUpdateDUneCvk(layoutCoordinates, downPosition, downPosition, adjustment);
                }
                m500setDragBeginPositionk4lQ0M(downPosition);
                return SelectionRegistrarKt.hasSelection(textController.getSelectionRegistrar(), textController.getState().getSelectableId());
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onDrag-3MmeM6k, reason: not valid java name */
            public boolean mo496onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                Intrinsics.checkNotNullParameter(adjustment, "adjustment");
                LayoutCoordinates layoutCoordinates = TextController.this.getState().getLayoutCoordinates();
                if (layoutCoordinates == null) {
                    return true;
                }
                TextController textController = TextController.this;
                if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(textController.getSelectionRegistrar(), textController.getState().getSelectableId())) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar = textController.getSelectionRegistrar();
                if (selectionRegistrar == null) {
                    return true;
                }
                selectionRegistrar.mo578notifySelectionUpdateDUneCvk(layoutCoordinates, getDragBeginPosition(), dragPosition, adjustment);
                return true;
            }
        };
    }

    public final TextState getState() {
        return this.state;
    }

    public final SelectionRegistrar getSelectionRegistrar() {
        return this.selectionRegistrar;
    }

    public final void setSelectionRegistrar(SelectionRegistrar selectionRegistrar) {
        this.selectionRegistrar = selectionRegistrar;
    }

    public final void update(SelectionRegistrar selectionRegistrar) {
        this.selectionRegistrar = selectionRegistrar;
    }

    public final Modifier getModifiers() {
        return this.modifiers;
    }

    public final MeasurePolicy getMeasurePolicy() {
        return this.measurePolicy;
    }

    public final Function1<DisposableEffectScope, DisposableEffectResult> getCommit() {
        return this.commit;
    }

    public final TextDragObserver getLongPressDragObserver() {
        return this.longPressDragObserver;
    }

    public final MouseSelectionObserver getMouseSelectionObserver() {
        return this.mouseSelectionObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: outOfBoundary-0a9Yr6o, reason: not valid java name */
    public final boolean m488outOfBoundary0a9Yr6o(long start, long end) {
        TextLayoutResult layoutResult = this.state.getLayoutResult();
        if (layoutResult == null) {
            return false;
        }
        int length = layoutResult.getLayoutInput().getText().getText().length();
        int m2749getOffsetForPositionk4lQ0M = layoutResult.m2749getOffsetForPositionk4lQ0M(start);
        int m2749getOffsetForPositionk4lQ0M2 = layoutResult.m2749getOffsetForPositionk4lQ0M(end);
        int i = length - 1;
        return (m2749getOffsetForPositionk4lQ0M >= i && m2749getOffsetForPositionk4lQ0M2 >= i) || (m2749getOffsetForPositionk4lQ0M < 0 && m2749getOffsetForPositionk4lQ0M2 < 0);
    }

    private final Modifier drawTextAndSelectionBehind(Modifier modifier) {
        return DrawModifierKt.drawBehind(GraphicsLayerModifierKt.m1389graphicsLayersKFY_QE$default(modifier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, 8191, null), new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.TextController$drawTextAndSelectionBehind$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope drawBehind) {
                int offset;
                int offset2;
                Map<Long, Selection> subselections;
                Intrinsics.checkNotNullParameter(drawBehind, "$this$drawBehind");
                TextLayoutResult layoutResult = TextController.this.getState().getLayoutResult();
                if (layoutResult == null) {
                    return;
                }
                TextController textController = TextController.this;
                SelectionRegistrar selectionRegistrar = textController.getSelectionRegistrar();
                Selection selection = null;
                if (selectionRegistrar != null && (subselections = selectionRegistrar.getSubselections()) != null) {
                    selection = subselections.get(Long.valueOf(textController.getState().getSelectableId()));
                }
                if (selection != null) {
                    if (!selection.getHandlesCrossed()) {
                        offset = selection.getStart().getOffset();
                    } else {
                        offset = selection.getEnd().getOffset();
                    }
                    if (!selection.getHandlesCrossed()) {
                        offset2 = selection.getEnd().getOffset();
                    } else {
                        offset2 = selection.getStart().getOffset();
                    }
                    if (offset != offset2) {
                        DrawScope.DefaultImpls.m1666drawPathLG529CI$default(drawBehind, layoutResult.getMultiParagraph().getPathForRange(offset, offset2), textController.getState().getSelectionBackgroundColor(), 0.0f, null, null, 0, 60, null);
                    }
                }
                TextDelegate.INSTANCE.paint(drawBehind.getDrawContext().getCanvas(), layoutResult);
            }
        });
    }
}
