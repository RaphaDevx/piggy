package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectionManager.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010P\u001a\u0004\u0018\u00010\u00132\u0006\u0010Q\u001a\u00020\f2\u0006\u0010R\u001a\u00020\u0013H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bS\u0010TJ\r\u0010U\u001a\u000208H\u0000¢\u0006\u0002\bVJ\u0018\u0010W\u001a\u0004\u0018\u00010\u0013H\u0002ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0002\bXJ\b\u0010Y\u001a\u00020ZH\u0002J\u000f\u0010[\u001a\u0004\u0018\u00010\\H\u0000¢\u0006\u0002\b]J\u000e\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020*J\r\u0010a\u001a\u000208H\u0000¢\u0006\u0002\bbJ;\u0010c\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u000107\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020f\u0012\u0004\u0012\u0002070e0d2\n\b\u0002\u0010g\u001a\u0004\u0018\u0001072\u0006\u0010h\u001a\u00020fH\u0000¢\u0006\u0002\biJ_\u0010c\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u000107\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020f\u0012\u0004\u0012\u0002070e0d2\u0006\u0010j\u001a\u00020\u00132\u0006\u0010k\u001a\u00020\u00132\b\b\u0002\u0010l\u001a\u00020m2\n\b\u0002\u0010g\u001a\u0004\u0018\u0001072\b\b\u0002\u0010`\u001a\u00020*H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bn\u0010oJ\u0006\u0010p\u001a\u000208J\r\u0010q\u001a\u00020\fH\u0000¢\u0006\u0002\brJ\r\u0010s\u001a\u000208H\u0000¢\u0006\u0002\btJ\b\u0010u\u001a\u000208H\u0002J;\u0010v\u001a\u0002082\b\u0010j\u001a\u0004\u0018\u00010\u00132\b\u0010k\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010l\u001a\u00020m2\b\b\u0002\u0010`\u001a\u00020*H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\bwJ\b\u0010x\u001a\u000208H\u0002J,\u0010y\u001a\u000208*\u00020z2\u0012\u0010{\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020806H\u0082@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010|J\u001a\u0010}\u001a\u000202*\u0002022\f\u0010~\u001a\b\u0012\u0004\u0012\u0002080\u007fH\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u00020\u0013X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0014R\u0019\u0010\u0015\u001a\u00020\u0013X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0014R8\u0010\u0017\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00138F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R+\u0010+\u001a\u00020*2\u0006\u0010\u0016\u001a\u00020*8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010\u001d\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0011\u00101\u001a\u0002028F¢\u0006\u0006\u001a\u0004\b3\u00104R(\u00105\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u000107\u0012\u0004\u0012\u00020806X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0019\u0010=\u001a\u0004\u0018\u00010\u0013X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0002\n\u0000R(\u0010>\u001a\u0004\u0018\u0001072\b\u0010\u000b\u001a\u0004\u0018\u000107@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R8\u0010C\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00138F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bF\u0010\u001d\u001a\u0004\bD\u0010\u0019\"\u0004\bE\u0010\u001bR\u001c\u0010G\u001a\u0004\u0018\u00010HX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001a\u0010M\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010-\"\u0004\bO\u0010/\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0080\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionManager;", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "(Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;)V", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getClipboardManager", "()Landroidx/compose/ui/platform/ClipboardManager;", "setClipboardManager", "(Landroidx/compose/ui/platform/ClipboardManager;)V", "value", "Landroidx/compose/ui/layout/LayoutCoordinates;", "containerLayoutCoordinates", "getContainerLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setContainerLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "dragTotalDistance", "<set-?>", "endHandlePosition", "getEndHandlePosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setEndHandlePosition-_kEHs6E", "(Landroidx/compose/ui/geometry/Offset;)V", "endHandlePosition$delegate", "Landroidx/compose/runtime/MutableState;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "", "hasFocus", "getHasFocus", "()Z", "setHasFocus", "(Z)V", "hasFocus$delegate", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "onSelectionChange", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/selection/Selection;", "", "getOnSelectionChange", "()Lkotlin/jvm/functions/Function1;", "setOnSelectionChange", "(Lkotlin/jvm/functions/Function1;)V", "previousPosition", "selection", "getSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "setSelection", "(Landroidx/compose/foundation/text/selection/Selection;)V", "startHandlePosition", "getStartHandlePosition-_m7T9-E", "setStartHandlePosition-_kEHs6E", "startHandlePosition$delegate", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "touchMode", "getTouchMode", "setTouchMode", "convertToContainerCoordinates", "layoutCoordinates", TypedValues.CycleType.S_WAVE_OFFSET, "convertToContainerCoordinates-Q7Q5hAU", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)Landroidx/compose/ui/geometry/Offset;", "copy", "copy$foundation_release", "currentSelectionStartPosition", "currentSelectionStartPosition-_m7T9-E", "getContentRect", "Landroidx/compose/ui/geometry/Rect;", "getSelectedText", "Landroidx/compose/ui/text/AnnotatedString;", "getSelectedText$foundation_release", "handleDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "isStartHandle", "hideSelectionToolbar", "hideSelectionToolbar$foundation_release", "mergeSelections", "Lkotlin/Pair;", "", "", "previousSelection", "selectableId", "mergeSelections$foundation_release", "startPosition", "endPosition", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "mergeSelections-IUXd7x4$foundation_release", "(JJLandroidx/compose/foundation/text/selection/SelectionAdjustment;Landroidx/compose/foundation/text/selection/Selection;Z)Lkotlin/Pair;", "onRelease", "requireContainerCoordinates", "requireContainerCoordinates$foundation_release", "showSelectionToolbar", "showSelectionToolbar$foundation_release", "updateHandleOffsets", "updateSelection", "updateSelection-RHHTvR4", "updateSelectionToolbarPosition", "detectNonConsumingTap", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onTap", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClearSelectionRequested", "block", "Lkotlin/Function0;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SelectionManager {
    private ClipboardManager clipboardManager;
    private LayoutCoordinates containerLayoutCoordinates;
    private long dragBeginPosition;
    private long dragTotalDistance;

    /* renamed from: endHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState endHandlePosition;
    private FocusRequester focusRequester;
    private HapticFeedback hapticFeedBack;

    /* renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus;
    private Function1<? super Selection, Unit> onSelectionChange;
    private Offset previousPosition;
    private Selection selection;
    private final SelectionRegistrarImpl selectionRegistrar;

    /* renamed from: startHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState startHandlePosition;
    private TextToolbar textToolbar;
    private boolean touchMode;

    public SelectionManager(SelectionRegistrarImpl selectionRegistrar) {
        Intrinsics.checkNotNullParameter(selectionRegistrar, "selectionRegistrar");
        this.selectionRegistrar = selectionRegistrar;
        this.touchMode = true;
        this.onSelectionChange = new Function1<Selection, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$onSelectionChange$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Selection selection) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Selection selection) {
                invoke2(selection);
                return Unit.INSTANCE;
            }
        };
        this.focusRequester = new FocusRequester();
        this.hasFocus = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.dragBeginPosition = Offset.INSTANCE.m1041getZeroF1C5BW0();
        this.dragTotalDistance = Offset.INSTANCE.m1041getZeroF1C5BW0();
        this.startHandlePosition = SnapshotStateKt.mutableStateOf(null, SnapshotStateKt.structuralEqualityPolicy());
        this.endHandlePosition = SnapshotStateKt.mutableStateOf(null, SnapshotStateKt.structuralEqualityPolicy());
        selectionRegistrar.setOnPositionChangeCallback$foundation_release(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                Selection.AnchorInfo start;
                Selection.AnchorInfo end;
                Selection selection = SelectionManager.this.getSelection();
                Long l = null;
                Long valueOf = (selection == null || (start = selection.getStart()) == null) ? null : Long.valueOf(start.getSelectableId());
                if (valueOf == null || j != valueOf.longValue()) {
                    Selection selection2 = SelectionManager.this.getSelection();
                    if (selection2 != null && (end = selection2.getEnd()) != null) {
                        l = Long.valueOf(end.getSelectableId());
                    }
                    if (l == null || j != l.longValue()) {
                        return;
                    }
                }
                SelectionManager.this.updateHandleOffsets();
                SelectionManager.this.updateSelectionToolbarPosition();
            }
        });
        selectionRegistrar.setOnSelectionUpdateStartCallback$foundation_release(new Function3<LayoutCoordinates, Offset, SelectionAdjustment, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates, Offset offset, SelectionAdjustment selectionAdjustment) {
                m570invoked4ec7I(layoutCoordinates, offset.getPackedValue(), selectionAdjustment);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final void m570invoked4ec7I(LayoutCoordinates layoutCoordinates, long j, SelectionAdjustment selectionMode) {
                Intrinsics.checkNotNullParameter(layoutCoordinates, "layoutCoordinates");
                Intrinsics.checkNotNullParameter(selectionMode, "selectionMode");
                Offset m560convertToContainerCoordinatesQ7Q5hAU = SelectionManager.this.m560convertToContainerCoordinatesQ7Q5hAU(layoutCoordinates, j);
                SelectionManager.this.m565updateSelectionRHHTvR4(m560convertToContainerCoordinatesQ7Q5hAU, m560convertToContainerCoordinatesQ7Q5hAU, selectionMode, true);
                SelectionManager.this.getFocusRequester().requestFocus();
                SelectionManager.this.hideSelectionToolbar$foundation_release();
            }
        });
        selectionRegistrar.setOnSelectionUpdateSelectAll$foundation_release(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                Pair<Selection, Map<Long, Selection>> mergeSelections$foundation_release = SelectionManager.this.mergeSelections$foundation_release(SelectionManager.this.getSelection(), j);
                Selection component1 = mergeSelections$foundation_release.component1();
                Map<Long, Selection> component2 = mergeSelections$foundation_release.component2();
                if (!Intrinsics.areEqual(component1, SelectionManager.this.getSelection())) {
                    SelectionManager.this.selectionRegistrar.setSubselections(component2);
                    SelectionManager.this.getOnSelectionChange().invoke(component1);
                }
                SelectionManager.this.getFocusRequester().requestFocus();
                SelectionManager.this.hideSelectionToolbar$foundation_release();
            }
        });
        selectionRegistrar.setOnSelectionUpdateCallback$foundation_release(new Function4<LayoutCoordinates, Offset, Offset, SelectionAdjustment, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.4
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates, Offset offset, Offset offset2, SelectionAdjustment selectionAdjustment) {
                m571invokeg0qObnA(layoutCoordinates, offset, offset2.getPackedValue(), selectionAdjustment);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-g0qObnA, reason: not valid java name */
            public final void m571invokeg0qObnA(LayoutCoordinates layoutCoordinates, Offset offset, long j, SelectionAdjustment selectionMode) {
                Intrinsics.checkNotNullParameter(layoutCoordinates, "layoutCoordinates");
                Intrinsics.checkNotNullParameter(selectionMode, "selectionMode");
                SelectionManager.this.m565updateSelectionRHHTvR4(offset == null ? SelectionManager.this.m561currentSelectionStartPosition_m7T9E() : SelectionManager.this.m560convertToContainerCoordinatesQ7Q5hAU(layoutCoordinates, offset.getPackedValue()), SelectionManager.this.m560convertToContainerCoordinatesQ7Q5hAU(layoutCoordinates, j), selectionMode, false);
            }
        });
        selectionRegistrar.setOnSelectionUpdateEndCallback$foundation_release(new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.5
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                SelectionManager.this.showSelectionToolbar$foundation_release();
            }
        });
        selectionRegistrar.setOnSelectableChangeCallback$foundation_release(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                if (SelectionManager.this.selectionRegistrar.getSubselections().containsKey(Long.valueOf(j))) {
                    SelectionManager.this.onRelease();
                    SelectionManager.this.setSelection(null);
                }
            }
        });
        selectionRegistrar.setAfterSelectableUnsubscribe$foundation_release(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.7
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                Selection.AnchorInfo start;
                Selection.AnchorInfo end;
                Selection selection = SelectionManager.this.getSelection();
                Long valueOf = (selection == null || (start = selection.getStart()) == null) ? null : Long.valueOf(start.getSelectableId());
                if (valueOf == null || j != valueOf.longValue()) {
                    Selection selection2 = SelectionManager.this.getSelection();
                    Long valueOf2 = (selection2 == null || (end = selection2.getEnd()) == null) ? null : Long.valueOf(end.getSelectableId());
                    if (valueOf2 == null || j != valueOf2.longValue()) {
                        return;
                    }
                }
                SelectionManager.this.m564setStartHandlePosition_kEHs6E(null);
                SelectionManager.this.m563setEndHandlePosition_kEHs6E(null);
            }
        });
    }

    public final Selection getSelection() {
        return this.selection;
    }

    public final void setSelection(Selection selection) {
        this.selection = selection;
        if (selection != null) {
            updateHandleOffsets();
        }
    }

    public final boolean getTouchMode() {
        return this.touchMode;
    }

    public final void setTouchMode(boolean z) {
        this.touchMode = z;
    }

    public final Function1<Selection, Unit> getOnSelectionChange() {
        return this.onSelectionChange;
    }

    public final void setOnSelectionChange(Function1<? super Selection, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onSelectionChange = function1;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final ClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    public final void setClipboardManager(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        Intrinsics.checkNotNullParameter(focusRequester, "<set-?>");
        this.focusRequester = focusRequester;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    public final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    public final Modifier getModifier() {
        return KeyInputModifierKt.onKeyEvent(FocusableKt.focusable$default(FocusChangedModifierKt.onFocusChanged(FocusRequesterModifierKt.focusRequester(OnGloballyPositionedModifierKt.onGloballyPositioned(onClearSelectionRequested(Modifier.INSTANCE, new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                SelectionManager.this.onRelease();
            }
        }), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$2
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
                Intrinsics.checkNotNullParameter(it, "it");
                SelectionManager.this.setContainerLayoutCoordinates(it);
            }
        }), this.focusRequester), new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                invoke2(focusState);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FocusState focusState) {
                Intrinsics.checkNotNullParameter(focusState, "focusState");
                if (!focusState.isFocused() && SelectionManager.this.getHasFocus()) {
                    SelectionManager.this.onRelease();
                }
                SelectionManager.this.setHasFocus(focusState.isFocused());
            }
        }), false, null, 3, null), new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m572invokeZmokQxo(keyEvent.getNativeKeyEvent());
            }

            /* renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m572invokeZmokQxo(android.view.KeyEvent it) {
                boolean z;
                Intrinsics.checkNotNullParameter(it, "it");
                if (SelectionManager_androidKt.m575isCopyKeyEventZmokQxo(it)) {
                    SelectionManager.this.copy$foundation_release();
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public final LayoutCoordinates getContainerLayoutCoordinates() {
        return this.containerLayoutCoordinates;
    }

    public final void setContainerLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.containerLayoutCoordinates = layoutCoordinates;
        if (!getHasFocus() || this.selection == null) {
            return;
        }
        Offset m1014boximpl = layoutCoordinates == null ? null : Offset.m1014boximpl(LayoutCoordinatesKt.positionInWindow(layoutCoordinates));
        if (Intrinsics.areEqual(this.previousPosition, m1014boximpl)) {
            return;
        }
        this.previousPosition = m1014boximpl;
        updateHandleOffsets();
        updateSelectionToolbarPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setStartHandlePosition-_kEHs6E, reason: not valid java name */
    public final void m564setStartHandlePosition_kEHs6E(Offset offset) {
        this.startHandlePosition.setValue(offset);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getStartHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m568getStartHandlePosition_m7T9E() {
        return (Offset) this.startHandlePosition.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setEndHandlePosition-_kEHs6E, reason: not valid java name */
    public final void m563setEndHandlePosition_kEHs6E(Offset offset) {
        this.endHandlePosition.setValue(offset);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getEndHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m567getEndHandlePosition_m7T9E() {
        return (Offset) this.endHandlePosition.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: currentSelectionStartPosition-_m7T9-E, reason: not valid java name */
    public final Offset m561currentSelectionStartPosition_m7T9E() {
        Selection selection = this.selection;
        if (selection == null) {
            return null;
        }
        Selectable selectable = this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getStart().getSelectableId()));
        LayoutCoordinates requireContainerCoordinates$foundation_release = requireContainerCoordinates$foundation_release();
        LayoutCoordinates layoutCoordinates = selectable != null ? selectable.getLayoutCoordinates() : null;
        Intrinsics.checkNotNull(layoutCoordinates);
        return Offset.m1014boximpl(requireContainerCoordinates$foundation_release.mo2523localPositionOfR5De75A(layoutCoordinates, SelectionHandlesKt.m554getAdjustedCoordinatesk4lQ0M(selectable.mo544getHandlePositiondBAh8RU(selection, true))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateHandleOffsets() {
        Selection selection = this.selection;
        LayoutCoordinates layoutCoordinates = this.containerLayoutCoordinates;
        Selection.AnchorInfo start = selection == null ? null : selection.getStart();
        Selectable selectable = start == null ? null : this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(start.getSelectableId()));
        Selection.AnchorInfo end = selection == null ? null : selection.getEnd();
        Selectable selectable2 = end == null ? null : this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(end.getSelectableId()));
        LayoutCoordinates layoutCoordinates2 = selectable == null ? null : selectable.getLayoutCoordinates();
        LayoutCoordinates layoutCoordinates3 = selectable2 == null ? null : selectable2.getLayoutCoordinates();
        if (selection == null || layoutCoordinates == null || !layoutCoordinates.isAttached() || layoutCoordinates2 == null || layoutCoordinates3 == null) {
            m564setStartHandlePosition_kEHs6E(null);
            m563setEndHandlePosition_kEHs6E(null);
            return;
        }
        long mo2523localPositionOfR5De75A = layoutCoordinates.mo2523localPositionOfR5De75A(layoutCoordinates2, selectable.mo544getHandlePositiondBAh8RU(selection, true));
        long mo2523localPositionOfR5De75A2 = layoutCoordinates.mo2523localPositionOfR5De75A(layoutCoordinates3, selectable2.mo544getHandlePositiondBAh8RU(selection, false));
        Rect visibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates);
        m564setStartHandlePosition_kEHs6E(SelectionManagerKt.m574containsInclusiveUv8p0NA(visibleBounds, mo2523localPositionOfR5De75A) ? Offset.m1014boximpl(mo2523localPositionOfR5De75A) : null);
        m563setEndHandlePosition_kEHs6E(SelectionManagerKt.m574containsInclusiveUv8p0NA(visibleBounds, mo2523localPositionOfR5De75A2) ? Offset.m1014boximpl(mo2523localPositionOfR5De75A2) : null);
    }

    public final LayoutCoordinates requireContainerCoordinates$foundation_release() {
        LayoutCoordinates layoutCoordinates = this.containerLayoutCoordinates;
        if (!(layoutCoordinates != null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (layoutCoordinates.isAttached()) {
            return layoutCoordinates;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* renamed from: mergeSelections-IUXd7x4$foundation_release, reason: not valid java name */
    public final Pair<Selection, Map<Long, Selection>> m569mergeSelectionsIUXd7x4$foundation_release(long startPosition, long endPosition, SelectionAdjustment adjustment, Selection previousSelection, boolean isStartHandle) {
        Selection selection;
        HapticFeedback hapticFeedback;
        Intrinsics.checkNotNullParameter(adjustment, "adjustment");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<Selectable> sort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        int size = sort.size() - 1;
        Selection selection2 = null;
        if (size >= 0) {
            int i = 0;
            Selection selection3 = null;
            while (true) {
                int i2 = i + 1;
                Selectable selectable = sort.get(i);
                Selection mo545getSelectionXnYAUg = selectable.mo545getSelectionXnYAUg(startPosition, endPosition, requireContainerCoordinates$foundation_release(), adjustment, previousSelection, isStartHandle);
                if (mo545getSelectionXnYAUg != null) {
                    linkedHashMap.put(Long.valueOf(selectable.getSelectableId()), mo545getSelectionXnYAUg);
                }
                selection3 = SelectionManagerKt.merge(selection3, mo545getSelectionXnYAUg);
                if (i2 > size) {
                    break;
                }
                i = i2;
            }
            selection = previousSelection;
            selection2 = selection3;
        } else {
            selection = previousSelection;
        }
        if (!Intrinsics.areEqual(selection, selection2) && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo1760performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m1769getTextHandleMove5zf0vsI());
        }
        return new Pair<>(selection2, linkedHashMap);
    }

    public static /* synthetic */ Pair mergeSelections$foundation_release$default(SelectionManager selectionManager, Selection selection, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            selection = null;
        }
        return selectionManager.mergeSelections$foundation_release(selection, j);
    }

    public final Pair<Selection, Map<Long, Selection>> mergeSelections$foundation_release(Selection previousSelection, long selectableId) {
        HapticFeedback hapticFeedback;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<Selectable> sort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        int size = sort.size() - 1;
        Selection selection = null;
        if (size >= 0) {
            int i = 0;
            Selection selection2 = null;
            while (true) {
                int i2 = i + 1;
                Selectable selectable = sort.get(i);
                Selection selectAllSelection = selectable.getSelectableId() == selectableId ? selectable.getSelectAllSelection() : null;
                if (selectAllSelection != null) {
                    linkedHashMap.put(Long.valueOf(selectable.getSelectableId()), selectAllSelection);
                }
                selection2 = SelectionManagerKt.merge(selection2, selectAllSelection);
                if (i2 > size) {
                    break;
                }
                i = i2;
            }
            selection = selection2;
        }
        if (!Intrinsics.areEqual(previousSelection, selection) && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo1760performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m1769getTextHandleMove5zf0vsI());
        }
        return new Pair<>(selection, linkedHashMap);
    }

    public final AnnotatedString getSelectedText$foundation_release() {
        int size;
        AnnotatedString currentSelectedText;
        AnnotatedString plus;
        List<Selectable> sort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        Selection selection = this.selection;
        AnnotatedString annotatedString = null;
        if (selection == null || sort.size() - 1 < 0) {
            return null;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            Selectable selectable = sort.get(i);
            if (selectable.getSelectableId() == selection.getStart().getSelectableId() || selectable.getSelectableId() == selection.getEnd().getSelectableId() || annotatedString != null) {
                currentSelectedText = SelectionManagerKt.getCurrentSelectedText(selectable, selection);
                if (annotatedString != null && (plus = annotatedString.plus(currentSelectedText)) != null) {
                    currentSelectedText = plus;
                }
                if ((selectable.getSelectableId() != selection.getEnd().getSelectableId() || selection.getHandlesCrossed()) && (selectable.getSelectableId() != selection.getStart().getSelectableId() || !selection.getHandlesCrossed())) {
                    annotatedString = currentSelectedText;
                }
            }
            if (i2 > size) {
                return annotatedString;
            }
            i = i2;
        }
        return currentSelectedText;
    }

    public final void copy$foundation_release() {
        ClipboardManager clipboardManager;
        AnnotatedString selectedText$foundation_release = getSelectedText$foundation_release();
        if (selectedText$foundation_release == null || (clipboardManager = getClipboardManager()) == null) {
            return;
        }
        clipboardManager.setText(selectedText$foundation_release);
    }

    public final void showSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        if (!getHasFocus() || this.selection == null || (textToolbar = getTextToolbar()) == null) {
            return;
        }
        TextToolbar.DefaultImpls.showMenu$default(textToolbar, getContentRect(), new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$showSelectionToolbar$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                SelectionManager.this.copy$foundation_release();
                SelectionManager.this.onRelease();
            }
        }, null, null, null, 28, null);
    }

    public final void hideSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        if (getHasFocus()) {
            TextToolbar textToolbar2 = this.textToolbar;
            if ((textToolbar2 == null ? null : textToolbar2.getStatus()) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
                return;
            }
            textToolbar.hide();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectionToolbarPosition() {
        if (getHasFocus()) {
            TextToolbar textToolbar = this.textToolbar;
            if ((textToolbar == null ? null : textToolbar.getStatus()) == TextToolbarStatus.Shown) {
                showSelectionToolbar$foundation_release();
            }
        }
    }

    private final Rect getContentRect() {
        Selection selection = this.selection;
        if (selection == null) {
            return Rect.INSTANCE.getZero();
        }
        Selectable selectable = this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getStart().getSelectableId()));
        Selectable selectable2 = this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getStart().getSelectableId()));
        LayoutCoordinates layoutCoordinates = selectable == null ? null : selectable.getLayoutCoordinates();
        if (layoutCoordinates == null) {
            return Rect.INSTANCE.getZero();
        }
        LayoutCoordinates layoutCoordinates2 = selectable2 != null ? selectable2.getLayoutCoordinates() : null;
        if (layoutCoordinates2 == null) {
            return Rect.INSTANCE.getZero();
        }
        LayoutCoordinates layoutCoordinates3 = this.containerLayoutCoordinates;
        if (layoutCoordinates3 != null && layoutCoordinates3.isAttached()) {
            long mo2523localPositionOfR5De75A = layoutCoordinates3.mo2523localPositionOfR5De75A(layoutCoordinates, selectable.mo544getHandlePositiondBAh8RU(selection, true));
            long mo2523localPositionOfR5De75A2 = layoutCoordinates3.mo2523localPositionOfR5De75A(layoutCoordinates2, selectable2.mo544getHandlePositiondBAh8RU(selection, false));
            long mo2524localToRootMKHz9U = layoutCoordinates3.mo2524localToRootMKHz9U(mo2523localPositionOfR5De75A);
            long mo2524localToRootMKHz9U2 = layoutCoordinates3.mo2524localToRootMKHz9U(mo2523localPositionOfR5De75A2);
            return new Rect(Math.min(Offset.m1025getXimpl(mo2524localToRootMKHz9U), Offset.m1025getXimpl(mo2524localToRootMKHz9U2)), Math.min(Offset.m1026getYimpl(layoutCoordinates3.mo2524localToRootMKHz9U(layoutCoordinates3.mo2523localPositionOfR5De75A(layoutCoordinates, OffsetKt.Offset(0.0f, selectable.getBoundingBox(selection.getStart().getOffset()).getTop())))), Offset.m1026getYimpl(layoutCoordinates3.mo2524localToRootMKHz9U(layoutCoordinates3.mo2523localPositionOfR5De75A(layoutCoordinates2, OffsetKt.Offset(0.0f, selectable2.getBoundingBox(selection.getEnd().getOffset()).getTop()))))), Math.max(Offset.m1025getXimpl(mo2524localToRootMKHz9U), Offset.m1025getXimpl(mo2524localToRootMKHz9U2)), Math.max(Offset.m1026getYimpl(mo2524localToRootMKHz9U), Offset.m1026getYimpl(mo2524localToRootMKHz9U2)) + ((float) (SelectionHandlesKt.getHANDLE_HEIGHT() * 4.0d)));
        }
        return Rect.INSTANCE.getZero();
    }

    public final void onRelease() {
        this.selectionRegistrar.setSubselections(MapsKt.emptyMap());
        hideSelectionToolbar$foundation_release();
        if (this.selection != null) {
            this.onSelectionChange.invoke(null);
            HapticFeedback hapticFeedback = this.hapticFeedBack;
            if (hapticFeedback == null) {
                return;
            }
            hapticFeedback.mo1760performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m1769getTextHandleMove5zf0vsI());
        }
    }

    public final TextDragObserver handleDragObserver(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.SelectionManager$handleDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M */
            public void mo492onStartk4lQ0M(long startPoint) {
                LayoutCoordinates layoutCoordinates;
                long mo544getHandlePositiondBAh8RU;
                SelectionManager.this.hideSelectionToolbar$foundation_release();
                Selection selection = SelectionManager.this.getSelection();
                Intrinsics.checkNotNull(selection);
                Selectable selectable = SelectionManager.this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getStart().getSelectableId()));
                Selectable selectable2 = SelectionManager.this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getEnd().getSelectableId()));
                if (isStartHandle) {
                    layoutCoordinates = selectable != null ? selectable.getLayoutCoordinates() : null;
                    Intrinsics.checkNotNull(layoutCoordinates);
                } else {
                    layoutCoordinates = selectable2 != null ? selectable2.getLayoutCoordinates() : null;
                    Intrinsics.checkNotNull(layoutCoordinates);
                }
                if (isStartHandle) {
                    Intrinsics.checkNotNull(selectable);
                    mo544getHandlePositiondBAh8RU = selectable.mo544getHandlePositiondBAh8RU(selection, true);
                } else {
                    Intrinsics.checkNotNull(selectable2);
                    mo544getHandlePositiondBAh8RU = selectable2.mo544getHandlePositiondBAh8RU(selection, false);
                }
                long m554getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m554getAdjustedCoordinatesk4lQ0M(mo544getHandlePositiondBAh8RU);
                SelectionManager selectionManager = SelectionManager.this;
                selectionManager.dragBeginPosition = selectionManager.requireContainerCoordinates$foundation_release().mo2523localPositionOfR5De75A(layoutCoordinates, m554getAdjustedCoordinatesk4lQ0M);
                SelectionManager.this.dragTotalDistance = Offset.INSTANCE.m1041getZeroF1C5BW0();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M */
            public void mo491onDragk4lQ0M(long delta) {
                long j;
                long mo2523localPositionOfR5De75A;
                long j2;
                long j3;
                long m1030plusMKHz9U;
                long j4;
                long j5;
                Selection selection = SelectionManager.this.getSelection();
                Intrinsics.checkNotNull(selection);
                SelectionManager selectionManager = SelectionManager.this;
                j = selectionManager.dragTotalDistance;
                selectionManager.dragTotalDistance = Offset.m1030plusMKHz9U(j, delta);
                Selectable selectable = SelectionManager.this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getStart().getSelectableId()));
                Selectable selectable2 = SelectionManager.this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getEnd().getSelectableId()));
                if (isStartHandle) {
                    j4 = SelectionManager.this.dragBeginPosition;
                    j5 = SelectionManager.this.dragTotalDistance;
                    mo2523localPositionOfR5De75A = Offset.m1030plusMKHz9U(j4, j5);
                } else {
                    LayoutCoordinates requireContainerCoordinates$foundation_release = SelectionManager.this.requireContainerCoordinates$foundation_release();
                    LayoutCoordinates layoutCoordinates = selectable == null ? null : selectable.getLayoutCoordinates();
                    Intrinsics.checkNotNull(layoutCoordinates);
                    mo2523localPositionOfR5De75A = requireContainerCoordinates$foundation_release.mo2523localPositionOfR5De75A(layoutCoordinates, SelectionHandlesKt.m554getAdjustedCoordinatesk4lQ0M(selectable.mo544getHandlePositiondBAh8RU(selection, true)));
                }
                if (!isStartHandle) {
                    j2 = SelectionManager.this.dragBeginPosition;
                    j3 = SelectionManager.this.dragTotalDistance;
                    m1030plusMKHz9U = Offset.m1030plusMKHz9U(j2, j3);
                } else {
                    LayoutCoordinates requireContainerCoordinates$foundation_release2 = SelectionManager.this.requireContainerCoordinates$foundation_release();
                    LayoutCoordinates layoutCoordinates2 = selectable2 != null ? selectable2.getLayoutCoordinates() : null;
                    Intrinsics.checkNotNull(layoutCoordinates2);
                    m1030plusMKHz9U = requireContainerCoordinates$foundation_release2.mo2523localPositionOfR5De75A(layoutCoordinates2, SelectionHandlesKt.m554getAdjustedCoordinatesk4lQ0M(selectable2.mo544getHandlePositiondBAh8RU(selection, false)));
                }
                SelectionManager.m566updateSelectionRHHTvR4$default(SelectionManager.this, Offset.m1014boximpl(mo2523localPositionOfR5De75A), Offset.m1014boximpl(m1030plusMKHz9U), null, isStartHandle, 4, null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                SelectionManager.this.showSelectionToolbar$foundation_release();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                SelectionManager.this.showSelectionToolbar$foundation_release();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object detectNonConsumingTap(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        Object forEachGesture = ForEachGestureKt.forEachGesture(pointerInputScope, new SelectionManager$detectNonConsumingTap$2(function1, null), continuation);
        return forEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? forEachGesture : Unit.INSTANCE;
    }

    private final Modifier onClearSelectionRequested(Modifier modifier, Function0<Unit> function0) {
        return getHasFocus() ? SuspendingPointerInputFilterKt.pointerInput(modifier, Unit.INSTANCE, new SelectionManager$onClearSelectionRequested$1(this, function0, null)) : modifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convertToContainerCoordinates-Q7Q5hAU, reason: not valid java name */
    public final Offset m560convertToContainerCoordinatesQ7Q5hAU(LayoutCoordinates layoutCoordinates, long offset) {
        LayoutCoordinates layoutCoordinates2 = this.containerLayoutCoordinates;
        if (layoutCoordinates2 == null || !layoutCoordinates2.isAttached()) {
            return null;
        }
        return Offset.m1014boximpl(requireContainerCoordinates$foundation_release().mo2523localPositionOfR5De75A(layoutCoordinates, offset));
    }

    /* renamed from: updateSelection-RHHTvR4$default, reason: not valid java name */
    static /* synthetic */ void m566updateSelectionRHHTvR4$default(SelectionManager selectionManager, Offset offset, Offset offset2, SelectionAdjustment selectionAdjustment, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            selectionAdjustment = SelectionAdjustment.NONE;
        }
        if ((i & 8) != 0) {
            z = true;
        }
        selectionManager.m565updateSelectionRHHTvR4(offset, offset2, selectionAdjustment, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateSelection-RHHTvR4, reason: not valid java name */
    public final void m565updateSelectionRHHTvR4(Offset startPosition, Offset endPosition, SelectionAdjustment adjustment, boolean isStartHandle) {
        if (startPosition == null || endPosition == null) {
            return;
        }
        Pair<Selection, Map<Long, Selection>> m569mergeSelectionsIUXd7x4$foundation_release = m569mergeSelectionsIUXd7x4$foundation_release(startPosition.getPackedValue(), endPosition.getPackedValue(), adjustment, this.selection, isStartHandle);
        Selection component1 = m569mergeSelectionsIUXd7x4$foundation_release.component1();
        Map<Long, Selection> component2 = m569mergeSelectionsIUXd7x4$foundation_release.component2();
        if (Intrinsics.areEqual(component1, this.selection)) {
            return;
        }
        this.selectionRegistrar.setSubselections(component2);
        this.onSelectionChange.invoke(component1);
    }
}
