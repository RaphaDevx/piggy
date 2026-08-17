package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.UndoManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: TextFieldSelectionManager.kt */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0017\u0010W\u001a\u0002052\b\b\u0002\u0010X\u001a\u00020\u0013H\u0000¢\u0006\u0002\bYJ%\u0010Z\u001a\u0002022\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020^H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b_\u0010`J\r\u0010a\u001a\u000205H\u0000¢\u0006\u0002\bbJ\u001f\u0010c\u001a\u0002052\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u000fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\beJ\r\u0010f\u001a\u000205H\u0000¢\u0006\u0002\bgJ\r\u0010h\u001a\u000205H\u0000¢\u0006\u0002\biJ\b\u0010j\u001a\u00020kH\u0002J \u0010l\u001a\u00020\u000f2\u0006\u0010m\u001a\u00020\u0013H\u0000ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bn\u0010oJ\u0015\u0010p\u001a\u00020G2\u0006\u0010m\u001a\u00020\u0013H\u0000¢\u0006\u0002\bqJ\r\u0010r\u001a\u000205H\u0000¢\u0006\u0002\bsJ\r\u0010t\u001a\u00020\u0013H\u0000¢\u0006\u0002\buJ\r\u0010v\u001a\u000205H\u0000¢\u0006\u0002\bwJ\r\u0010x\u001a\u000205H\u0000¢\u0006\u0002\byJ\u0010\u0010z\u001a\u0002052\u0006\u0010{\u001a\u00020\u0013H\u0002J\r\u0010|\u001a\u000205H\u0000¢\u0006\u0002\b}J3\u0010~\u001a\u0002052\u0006\u0010L\u001a\u0002022\u0006\u0010\u007f\u001a\u00020\f2\u0007\u0010\u0080\u0001\u001a\u00020\f2\u0006\u0010m\u001a\u00020\u00132\b\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0019\u0010\u000e\u001a\u00020\u000fX\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R\u0019\u0010\u0011\u001a\u00020\u000fX\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R+\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00138F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R&\u00103\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020504X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010;X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020GX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u001a\u0010L\u001a\u000202X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001a\u0010Q\u001a\u00020RX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010V\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0083\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "(Landroidx/compose/foundation/text/UndoManager;)V", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getClipboardManager$foundation_release", "()Landroidx/compose/ui/platform/ClipboardManager;", "setClipboardManager$foundation_release", "(Landroidx/compose/ui/platform/ClipboardManager;)V", "dragBeginOffsetInText", "", "Ljava/lang/Integer;", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "dragTotalDistance", "<set-?>", "", "editable", "getEditable", "()Z", "setEditable", "(Z)V", "editable$delegate", "Landroidx/compose/runtime/MutableState;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "getMouseSelectionObserver$foundation_release", "()Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "getOffsetMapping$foundation_release", "()Landroidx/compose/ui/text/input/OffsetMapping;", "setOffsetMapping$foundation_release", "(Landroidx/compose/ui/text/input/OffsetMapping;)V", "oldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "", "getOnValueChange$foundation_release", "()Lkotlin/jvm/functions/Function1;", "setOnValueChange$foundation_release", "(Lkotlin/jvm/functions/Function1;)V", SentryThread.JsonKeys.STATE, "Landroidx/compose/foundation/text/TextFieldState;", "getState$foundation_release", "()Landroidx/compose/foundation/text/TextFieldState;", "setState$foundation_release", "(Landroidx/compose/foundation/text/TextFieldState;)V", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "touchSelectionObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "getTouchSelectionObserver$foundation_release", "()Landroidx/compose/foundation/text/TextDragObserver;", "getUndoManager", "()Landroidx/compose/foundation/text/UndoManager;", "value", "getValue$foundation_release", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setValue$foundation_release", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "getVisualTransformation$foundation_release", "()Landroidx/compose/ui/text/input/VisualTransformation;", "setVisualTransformation$foundation_release", "(Landroidx/compose/ui/text/input/VisualTransformation;)V", "copy", "cancelSelection", "copy$foundation_release", "createTextFieldValue", "annotatedString", "Landroidx/compose/ui/text/AnnotatedString;", "selection", "Landroidx/compose/ui/text/TextRange;", "createTextFieldValue-FDrldGo", "(Landroidx/compose/ui/text/AnnotatedString;J)Landroidx/compose/ui/text/input/TextFieldValue;", "cut", "cut$foundation_release", "deselect", "position", "deselect-_kEHs6E$foundation_release", "enterSelectionMode", "enterSelectionMode$foundation_release", "exitSelectionMode", "exitSelectionMode$foundation_release", "getContentRect", "Landroidx/compose/ui/geometry/Rect;", "getHandlePosition", "isStartHandle", "getHandlePosition-tuRUvjQ$foundation_release", "(Z)J", "handleDragObserver", "handleDragObserver$foundation_release", "hideSelectionToolbar", "hideSelectionToolbar$foundation_release", "isTextChanged", "isTextChanged$foundation_release", "paste", "paste$foundation_release", "selectAll", "selectAll$foundation_release", "setSelectionStatus", "on", "showSelectionToolbar", "showSelectionToolbar$foundation_release", "updateSelection", "transformedStartOffset", "transformedEndOffset", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextFieldSelectionManager {
    private ClipboardManager clipboardManager;
    private Integer dragBeginOffsetInText;
    private long dragBeginPosition;
    private long dragTotalDistance;

    /* renamed from: editable$delegate, reason: from kotlin metadata */
    private final MutableState editable;
    private FocusRequester focusRequester;
    private HapticFeedback hapticFeedBack;
    private final MouseSelectionObserver mouseSelectionObserver;
    private OffsetMapping offsetMapping;
    private TextFieldValue oldValue;
    private Function1<? super TextFieldValue, Unit> onValueChange;
    private TextFieldState state;
    private TextToolbar textToolbar;
    private final TextDragObserver touchSelectionObserver;
    private final UndoManager undoManager;
    private TextFieldValue value;
    private VisualTransformation visualTransformation;

    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldSelectionManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public TextFieldSelectionManager(UndoManager undoManager) {
        this.undoManager = undoManager;
        this.offsetMapping = OffsetMapping.INSTANCE.getIdentity();
        this.onValueChange = new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$onValueChange$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TextFieldValue it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                invoke2(textFieldValue);
                return Unit.INSTANCE;
            }
        };
        this.value = new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null);
        this.visualTransformation = VisualTransformation.INSTANCE.getNone();
        this.editable = SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this.dragBeginPosition = Offset.INSTANCE.m1041getZeroF1C5BW0();
        this.dragTotalDistance = Offset.INSTANCE.m1041getZeroF1C5BW0();
        this.oldValue = new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null);
        this.touchSelectionObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M */
            public void mo492onStartk4lQ0M(long startPoint) {
                TextLayoutResultProxy layoutResult;
                TextLayoutResultProxy layoutResult2;
                TextFieldState state;
                TextLayoutResultProxy layoutResult3;
                TextFieldValue m583createTextFieldValueFDrldGo;
                TextFieldState state2 = TextFieldSelectionManager.this.getState();
                if (state2 != null && state2.getDraggingHandle()) {
                    return;
                }
                TextFieldState state3 = TextFieldSelectionManager.this.getState();
                if (!Intrinsics.areEqual((Object) ((state3 == null || (layoutResult = state3.getLayoutResult()) == null) ? null : Boolean.valueOf(layoutResult.m529isPositionOnTextk4lQ0M(startPoint))), (Object) true) && (state = TextFieldSelectionManager.this.getState()) != null && (layoutResult3 = state.getLayoutResult()) != null) {
                    TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                    int transformedToOriginal = textFieldSelectionManager.getOffsetMapping().transformedToOriginal(TextLayoutResultProxy.getLineEnd$default(layoutResult3, layoutResult3.getLineForVerticalPosition(Offset.m1026getYimpl(startPoint)), false, 2, null));
                    HapticFeedback hapticFeedBack = textFieldSelectionManager.getHapticFeedBack();
                    if (hapticFeedBack != null) {
                        hapticFeedBack.mo1760performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m1769getTextHandleMove5zf0vsI());
                    }
                    m583createTextFieldValueFDrldGo = textFieldSelectionManager.m583createTextFieldValueFDrldGo(textFieldSelectionManager.getValue().getAnnotatedString(), TextRangeKt.TextRange(transformedToOriginal, transformedToOriginal));
                    textFieldSelectionManager.enterSelectionMode$foundation_release();
                    textFieldSelectionManager.getOnValueChange$foundation_release().invoke(m583createTextFieldValueFDrldGo);
                    return;
                }
                if (TextFieldSelectionManager.this.getValue().getText().length() == 0) {
                    return;
                }
                TextFieldSelectionManager.this.enterSelectionMode$foundation_release();
                TextFieldState state4 = TextFieldSelectionManager.this.getState();
                if (state4 != null && (layoutResult2 = state4.getLayoutResult()) != null) {
                    TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                    int m526getOffsetForPosition3MmeM6k$default = TextLayoutResultProxy.m526getOffsetForPosition3MmeM6k$default(layoutResult2, startPoint, false, 2, null);
                    textFieldSelectionManager2.updateSelection(textFieldSelectionManager2.getValue(), m526getOffsetForPosition3MmeM6k$default, m526getOffsetForPosition3MmeM6k$default, false, SelectionAdjustment.WORD);
                    textFieldSelectionManager2.dragBeginOffsetInText = Integer.valueOf(m526getOffsetForPosition3MmeM6k$default);
                }
                TextFieldSelectionManager.this.dragBeginPosition = startPoint;
                TextFieldSelectionManager.this.dragTotalDistance = Offset.INSTANCE.m1041getZeroF1C5BW0();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M */
            public void mo491onDragk4lQ0M(long delta) {
                long j;
                TextLayoutResultProxy layoutResult;
                Integer num;
                int intValue;
                long j2;
                long j3;
                long j4;
                if (TextFieldSelectionManager.this.getValue().getText().length() == 0) {
                    return;
                }
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                j = textFieldSelectionManager.dragTotalDistance;
                textFieldSelectionManager.dragTotalDistance = Offset.m1030plusMKHz9U(j, delta);
                TextFieldState state = TextFieldSelectionManager.this.getState();
                if (state != null && (layoutResult = state.getLayoutResult()) != null) {
                    TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                    num = textFieldSelectionManager2.dragBeginOffsetInText;
                    if (num == null) {
                        j4 = textFieldSelectionManager2.dragBeginPosition;
                        intValue = layoutResult.m528getOffsetForPosition3MmeM6k(j4, false);
                    } else {
                        intValue = num.intValue();
                    }
                    j2 = textFieldSelectionManager2.dragBeginPosition;
                    j3 = textFieldSelectionManager2.dragTotalDistance;
                    textFieldSelectionManager2.updateSelection(textFieldSelectionManager2.getValue(), intValue, layoutResult.m528getOffsetForPosition3MmeM6k(Offset.m1030plusMKHz9U(j2, j3), false), false, SelectionAdjustment.WORD);
                }
                TextFieldState state2 = TextFieldSelectionManager.this.getState();
                if (state2 == null) {
                    return;
                }
                state2.setShowFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                TextFieldState state = TextFieldSelectionManager.this.getState();
                if (state != null) {
                    state.setShowFloatingToolbar(true);
                }
                TextToolbar textToolbar = TextFieldSelectionManager.this.getTextToolbar();
                if ((textToolbar == null ? null : textToolbar.getStatus()) == TextToolbarStatus.Hidden) {
                    TextFieldSelectionManager.this.showSelectionToolbar$foundation_release();
                }
                TextFieldSelectionManager.this.dragBeginOffsetInText = null;
            }
        };
        this.mouseSelectionObserver = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$mouseSelectionObserver$1
            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onExtend-k-4lQ0M */
            public boolean mo497onExtendk4lQ0M(long downPosition) {
                TextLayoutResultProxy layoutResult;
                TextFieldState state = TextFieldSelectionManager.this.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return false;
                }
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                textFieldSelectionManager.updateSelection(textFieldSelectionManager.getValue(), textFieldSelectionManager.getOffsetMapping().originalToTransformed(TextRange.m2766getStartimpl(textFieldSelectionManager.getValue().getSelection())), TextLayoutResultProxy.m526getOffsetForPosition3MmeM6k$default(layoutResult, downPosition, false, 2, null), false, SelectionAdjustment.NONE);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onExtendDrag-k-4lQ0M */
            public boolean mo498onExtendDragk4lQ0M(long dragPosition) {
                TextFieldState state;
                TextLayoutResultProxy layoutResult;
                if (TextFieldSelectionManager.this.getValue().getText().length() == 0 || (state = TextFieldSelectionManager.this.getState()) == null || (layoutResult = state.getLayoutResult()) == null) {
                    return false;
                }
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                textFieldSelectionManager.updateSelection(textFieldSelectionManager.getValue(), textFieldSelectionManager.getOffsetMapping().originalToTransformed(TextRange.m2766getStartimpl(textFieldSelectionManager.getValue().getSelection())), layoutResult.m528getOffsetForPosition3MmeM6k(dragPosition, false), false, SelectionAdjustment.NONE);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onStart-3MmeM6k */
            public boolean mo499onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
                TextLayoutResultProxy layoutResult;
                long j;
                Intrinsics.checkNotNullParameter(adjustment, "adjustment");
                FocusRequester focusRequester = TextFieldSelectionManager.this.getFocusRequester();
                if (focusRequester != null) {
                    focusRequester.requestFocus();
                }
                TextFieldSelectionManager.this.dragBeginPosition = downPosition;
                TextFieldState state = TextFieldSelectionManager.this.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return false;
                }
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                textFieldSelectionManager.dragBeginOffsetInText = Integer.valueOf(TextLayoutResultProxy.m526getOffsetForPosition3MmeM6k$default(layoutResult, downPosition, false, 2, null));
                j = textFieldSelectionManager.dragBeginPosition;
                int m526getOffsetForPosition3MmeM6k$default = TextLayoutResultProxy.m526getOffsetForPosition3MmeM6k$default(layoutResult, j, false, 2, null);
                textFieldSelectionManager.updateSelection(textFieldSelectionManager.getValue(), m526getOffsetForPosition3MmeM6k$default, m526getOffsetForPosition3MmeM6k$default, false, adjustment);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onDrag-3MmeM6k */
            public boolean mo496onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                TextFieldState state;
                TextLayoutResultProxy layoutResult;
                Integer num;
                Intrinsics.checkNotNullParameter(adjustment, "adjustment");
                if (TextFieldSelectionManager.this.getValue().getText().length() == 0 || (state = TextFieldSelectionManager.this.getState()) == null || (layoutResult = state.getLayoutResult()) == null) {
                    return false;
                }
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                int m528getOffsetForPosition3MmeM6k = layoutResult.m528getOffsetForPosition3MmeM6k(dragPosition, false);
                TextFieldValue value = textFieldSelectionManager.getValue();
                num = textFieldSelectionManager.dragBeginOffsetInText;
                Intrinsics.checkNotNull(num);
                textFieldSelectionManager.updateSelection(value, num.intValue(), m528getOffsetForPosition3MmeM6k, false, adjustment);
                return true;
            }
        };
    }

    public /* synthetic */ TextFieldSelectionManager(UndoManager undoManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : undoManager);
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    /* renamed from: getOffsetMapping$foundation_release, reason: from getter */
    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final void setOffsetMapping$foundation_release(OffsetMapping offsetMapping) {
        Intrinsics.checkNotNullParameter(offsetMapping, "<set-?>");
        this.offsetMapping = offsetMapping;
    }

    public final Function1<TextFieldValue, Unit> getOnValueChange$foundation_release() {
        return this.onValueChange;
    }

    public final void setOnValueChange$foundation_release(Function1<? super TextFieldValue, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onValueChange = function1;
    }

    /* renamed from: getState$foundation_release, reason: from getter */
    public final TextFieldState getState() {
        return this.state;
    }

    public final void setState$foundation_release(TextFieldState textFieldState) {
        this.state = textFieldState;
    }

    /* renamed from: getValue$foundation_release, reason: from getter */
    public final TextFieldValue getValue() {
        return this.value;
    }

    public final void setValue$foundation_release(TextFieldValue textFieldValue) {
        Intrinsics.checkNotNullParameter(textFieldValue, "<set-?>");
        this.value = textFieldValue;
    }

    /* renamed from: getVisualTransformation$foundation_release, reason: from getter */
    public final VisualTransformation getVisualTransformation() {
        return this.visualTransformation;
    }

    public final void setVisualTransformation$foundation_release(VisualTransformation visualTransformation) {
        Intrinsics.checkNotNullParameter(visualTransformation, "<set-?>");
        this.visualTransformation = visualTransformation;
    }

    /* renamed from: getClipboardManager$foundation_release, reason: from getter */
    public final ClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    public final void setClipboardManager$foundation_release(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getEditable() {
        return ((Boolean) this.editable.getValue()).booleanValue();
    }

    public final void setEditable(boolean z) {
        this.editable.setValue(Boolean.valueOf(z));
    }

    /* renamed from: getTouchSelectionObserver$foundation_release, reason: from getter */
    public final TextDragObserver getTouchSelectionObserver() {
        return this.touchSelectionObserver;
    }

    /* renamed from: getMouseSelectionObserver$foundation_release, reason: from getter */
    public final MouseSelectionObserver getMouseSelectionObserver() {
        return this.mouseSelectionObserver;
    }

    public final TextDragObserver handleDragObserver$foundation_release(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$handleDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M */
            public void mo492onStartk4lQ0M(long startPoint) {
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                textFieldSelectionManager.dragBeginPosition = SelectionHandlesKt.m554getAdjustedCoordinatesk4lQ0M(textFieldSelectionManager.m586getHandlePositiontuRUvjQ$foundation_release(isStartHandle));
                TextFieldSelectionManager.this.dragTotalDistance = Offset.INSTANCE.m1041getZeroF1C5BW0();
                TextFieldState state = TextFieldSelectionManager.this.getState();
                if (state != null) {
                    state.setDraggingHandle(true);
                }
                TextFieldState state2 = TextFieldSelectionManager.this.getState();
                if (state2 == null) {
                    return;
                }
                state2.setShowFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M */
            public void mo491onDragk4lQ0M(long delta) {
                long j;
                TextLayoutResult value;
                int originalToTransformed;
                long j2;
                long j3;
                int m2749getOffsetForPositionk4lQ0M;
                long j4;
                long j5;
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                j = textFieldSelectionManager.dragTotalDistance;
                textFieldSelectionManager.dragTotalDistance = Offset.m1030plusMKHz9U(j, delta);
                TextFieldState state = TextFieldSelectionManager.this.getState();
                TextLayoutResultProxy layoutResult = state == null ? null : state.getLayoutResult();
                if (layoutResult != null && (value = layoutResult.getValue()) != null) {
                    boolean z = isStartHandle;
                    TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                    if (z) {
                        j4 = textFieldSelectionManager2.dragBeginPosition;
                        j5 = textFieldSelectionManager2.dragTotalDistance;
                        originalToTransformed = value.m2749getOffsetForPositionk4lQ0M(Offset.m1030plusMKHz9U(j4, j5));
                    } else {
                        originalToTransformed = textFieldSelectionManager2.getOffsetMapping().originalToTransformed(TextRange.m2766getStartimpl(textFieldSelectionManager2.getValue().getSelection()));
                    }
                    int i = originalToTransformed;
                    if (!z) {
                        j2 = textFieldSelectionManager2.dragBeginPosition;
                        j3 = textFieldSelectionManager2.dragTotalDistance;
                        m2749getOffsetForPositionk4lQ0M = value.m2749getOffsetForPositionk4lQ0M(Offset.m1030plusMKHz9U(j2, j3));
                    } else {
                        m2749getOffsetForPositionk4lQ0M = textFieldSelectionManager2.getOffsetMapping().originalToTransformed(TextRange.m2761getEndimpl(textFieldSelectionManager2.getValue().getSelection()));
                    }
                    textFieldSelectionManager2.updateSelection(textFieldSelectionManager2.getValue(), i, m2749getOffsetForPositionk4lQ0M, z, SelectionAdjustment.CHARACTER);
                }
                TextFieldState state2 = TextFieldSelectionManager.this.getState();
                if (state2 == null) {
                    return;
                }
                state2.setShowFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                TextFieldState state = TextFieldSelectionManager.this.getState();
                if (state != null) {
                    state.setDraggingHandle(false);
                }
                TextFieldState state2 = TextFieldSelectionManager.this.getState();
                if (state2 != null) {
                    state2.setShowFloatingToolbar(true);
                }
                TextToolbar textToolbar = TextFieldSelectionManager.this.getTextToolbar();
                if ((textToolbar == null ? null : textToolbar.getStatus()) == TextToolbarStatus.Hidden) {
                    TextFieldSelectionManager.this.showSelectionToolbar$foundation_release();
                }
            }
        };
    }

    public final void enterSelectionMode$foundation_release() {
        FocusRequester focusRequester;
        TextFieldState textFieldState = this.state;
        if (Intrinsics.areEqual((Object) (textFieldState == null ? null : Boolean.valueOf(textFieldState.getHasFocus())), (Object) false) && (focusRequester = this.focusRequester) != null) {
            focusRequester.requestFocus();
        }
        this.oldValue = this.value;
        TextFieldState textFieldState2 = this.state;
        if (textFieldState2 != null) {
            textFieldState2.setShowFloatingToolbar(true);
        }
        setSelectionStatus(true);
    }

    public final void exitSelectionMode$foundation_release() {
        TextFieldState textFieldState = this.state;
        if (textFieldState != null) {
            textFieldState.setShowFloatingToolbar(false);
        }
        setSelectionStatus(false);
    }

    /* renamed from: deselect-_kEHs6E$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ void m584deselect_kEHs6E$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, Offset offset, int i, Object obj) {
        if ((i & 1) != 0) {
            offset = null;
        }
        textFieldSelectionManager.m585deselect_kEHs6E$foundation_release(offset);
    }

    /* renamed from: deselect-_kEHs6E$foundation_release, reason: not valid java name */
    public final void m585deselect_kEHs6E$foundation_release(Offset position) {
        int m2763getMaximpl;
        if (!TextRange.m2760getCollapsedimpl(this.value.getSelection())) {
            TextFieldState textFieldState = this.state;
            TextLayoutResultProxy layoutResult = textFieldState == null ? null : textFieldState.getLayoutResult();
            if (position != null && layoutResult != null) {
                m2763getMaximpl = this.offsetMapping.transformedToOriginal(TextLayoutResultProxy.m526getOffsetForPosition3MmeM6k$default(layoutResult, position.getPackedValue(), false, 2, null));
            } else {
                m2763getMaximpl = TextRange.m2763getMaximpl(this.value.getSelection());
            }
            this.onValueChange.invoke(TextFieldValue.m2872copy3r_uNRQ$default(this.value, (AnnotatedString) null, TextRangeKt.TextRange(m2763getMaximpl), (TextRange) null, 5, (Object) null));
        }
        setSelectionStatus(false);
        hideSelectionToolbar$foundation_release();
    }

    public static /* synthetic */ void copy$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.copy$foundation_release(z);
    }

    public final void copy$foundation_release(boolean cancelSelection) {
        if (TextRange.m2760getCollapsedimpl(this.value.getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(this.value));
        }
        if (cancelSelection) {
            int m2763getMaximpl = TextRange.m2763getMaximpl(this.value.getSelection());
            this.onValueChange.invoke(m583createTextFieldValueFDrldGo(this.value.getAnnotatedString(), TextRangeKt.TextRange(m2763getMaximpl, m2763getMaximpl)));
            setSelectionStatus(false);
        }
    }

    public final void paste$foundation_release() {
        ClipboardManager clipboardManager = this.clipboardManager;
        AnnotatedString text = clipboardManager == null ? null : clipboardManager.getText();
        if (text == null) {
            return;
        }
        TextFieldValue textFieldValue = this.value;
        AnnotatedString plus = TextFieldValueKt.getTextBeforeSelection(textFieldValue, textFieldValue.getText().length()).plus(text);
        TextFieldValue textFieldValue2 = this.value;
        AnnotatedString plus2 = plus.plus(TextFieldValueKt.getTextAfterSelection(textFieldValue2, textFieldValue2.getText().length()));
        int m2764getMinimpl = TextRange.m2764getMinimpl(this.value.getSelection()) + text.length();
        this.onValueChange.invoke(m583createTextFieldValueFDrldGo(plus2, TextRangeKt.TextRange(m2764getMinimpl, m2764getMinimpl)));
        setSelectionStatus(false);
        UndoManager undoManager = this.undoManager;
        if (undoManager == null) {
            return;
        }
        undoManager.forceNextSnapshot();
    }

    public final void cut$foundation_release() {
        if (TextRange.m2760getCollapsedimpl(this.value.getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(this.value));
        }
        TextFieldValue textFieldValue = this.value;
        AnnotatedString textBeforeSelection = TextFieldValueKt.getTextBeforeSelection(textFieldValue, textFieldValue.getText().length());
        TextFieldValue textFieldValue2 = this.value;
        AnnotatedString plus = textBeforeSelection.plus(TextFieldValueKt.getTextAfterSelection(textFieldValue2, textFieldValue2.getText().length()));
        int m2764getMinimpl = TextRange.m2764getMinimpl(this.value.getSelection());
        this.onValueChange.invoke(m583createTextFieldValueFDrldGo(plus, TextRangeKt.TextRange(m2764getMinimpl, m2764getMinimpl)));
        setSelectionStatus(false);
        UndoManager undoManager = this.undoManager;
        if (undoManager == null) {
            return;
        }
        undoManager.forceNextSnapshot();
    }

    public final void selectAll$foundation_release() {
        setSelectionStatus(true);
        TextFieldValue m583createTextFieldValueFDrldGo = m583createTextFieldValueFDrldGo(this.value.getAnnotatedString(), TextRangeKt.TextRange(0, this.value.getText().length()));
        this.onValueChange.invoke(m583createTextFieldValueFDrldGo);
        this.oldValue = TextFieldValue.m2872copy3r_uNRQ$default(this.oldValue, (AnnotatedString) null, m583createTextFieldValueFDrldGo.getSelection(), (TextRange) null, 5, (Object) null);
        hideSelectionToolbar$foundation_release();
        TextFieldState textFieldState = this.state;
        if (textFieldState != null) {
            textFieldState.setShowFloatingToolbar(true);
        }
        showSelectionToolbar$foundation_release();
    }

    /* renamed from: getHandlePosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m586getHandlePositiontuRUvjQ$foundation_release(boolean isStartHandle) {
        long selection = this.value.getSelection();
        int m2766getStartimpl = isStartHandle ? TextRange.m2766getStartimpl(selection) : TextRange.m2761getEndimpl(selection);
        TextFieldState textFieldState = this.state;
        TextLayoutResultProxy layoutResult = textFieldState == null ? null : textFieldState.getLayoutResult();
        Intrinsics.checkNotNull(layoutResult);
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(layoutResult.getValue(), this.offsetMapping.originalToTransformed(m2766getStartimpl), isStartHandle, TextRange.m2765getReversedimpl(this.value.getSelection()));
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void showSelectionToolbar$foundation_release() {
        /*
            r9 = this;
            androidx.compose.ui.text.input.VisualTransformation r0 = r9.visualTransformation
            boolean r0 = r0 instanceof androidx.compose.ui.text.input.PasswordVisualTransformation
            androidx.compose.ui.text.input.TextFieldValue r1 = r9.value
            long r1 = r1.getSelection()
            boolean r1 = androidx.compose.ui.text.TextRange.m2760getCollapsedimpl(r1)
            r2 = 0
            if (r1 != 0) goto L1c
            if (r0 != 0) goto L1c
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$copy$1 r1 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$copy$1
            r1.<init>()
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            r5 = r1
            goto L1d
        L1c:
            r5 = r2
        L1d:
            androidx.compose.ui.text.input.TextFieldValue r1 = r9.value
            long r3 = r1.getSelection()
            boolean r1 = androidx.compose.ui.text.TextRange.m2760getCollapsedimpl(r3)
            if (r1 != 0) goto L3a
            boolean r1 = r9.getEditable()
            if (r1 == 0) goto L3a
            if (r0 != 0) goto L3a
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$cut$1 r0 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$cut$1
            r0.<init>()
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            r7 = r0
            goto L3b
        L3a:
            r7 = r2
        L3b:
            boolean r0 = r9.getEditable()
            if (r0 == 0) goto L56
            androidx.compose.ui.platform.ClipboardManager r0 = r9.clipboardManager
            if (r0 != 0) goto L47
            r0 = r2
            goto L4b
        L47:
            androidx.compose.ui.text.AnnotatedString r0 = r0.getText()
        L4b:
            if (r0 == 0) goto L56
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$paste$1 r0 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$paste$1
            r0.<init>()
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            r6 = r0
            goto L57
        L56:
            r6 = r2
        L57:
            androidx.compose.ui.text.input.TextFieldValue r0 = r9.value
            long r0 = r0.getSelection()
            int r0 = androidx.compose.ui.text.TextRange.m2762getLengthimpl(r0)
            androidx.compose.ui.text.input.TextFieldValue r1 = r9.value
            java.lang.String r1 = r1.getText()
            int r1 = r1.length()
            if (r0 == r1) goto L8b
            androidx.compose.ui.text.input.TextFieldValue r0 = r9.oldValue
            long r0 = r0.getSelection()
            int r0 = androidx.compose.ui.text.TextRange.m2762getLengthimpl(r0)
            androidx.compose.ui.text.input.TextFieldValue r1 = r9.oldValue
            java.lang.String r1 = r1.getText()
            int r1 = r1.length()
            if (r0 == r1) goto L8b
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$selectAll$1 r0 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$selectAll$1
            r0.<init>()
            r2 = r0
            kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
        L8b:
            r8 = r2
            androidx.compose.ui.platform.TextToolbar r3 = r9.textToolbar
            if (r3 != 0) goto L91
            goto L98
        L91:
            androidx.compose.ui.geometry.Rect r4 = r9.getContentRect()
            r3.showMenu(r4, r5, r6, r7, r8)
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager.showSelectionToolbar$foundation_release():void");
    }

    public final void hideSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 == null ? null : textToolbar2.getStatus()) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    public final boolean isTextChanged$foundation_release() {
        return !Intrinsics.areEqual(this.oldValue.getText(), this.value.getText());
    }

    private final Rect getContentRect() {
        LayoutCoordinates layoutCoordinates;
        LayoutCoordinates layoutCoordinates2;
        LayoutCoordinates layoutCoordinates3;
        TextLayoutResult value;
        Offset m1014boximpl;
        LayoutCoordinates layoutCoordinates4;
        TextLayoutResult value2;
        TextFieldState textFieldState = this.state;
        if (textFieldState != null) {
            TextFieldState state = getState();
            Offset offset = null;
            r3 = null;
            Rect rect = null;
            offset = null;
            Offset m1014boximpl2 = (state == null || (layoutCoordinates = state.getLayoutCoordinates()) == null) ? null : Offset.m1014boximpl(layoutCoordinates.mo2524localToRootMKHz9U(m586getHandlePositiontuRUvjQ$foundation_release(true)));
            long m1041getZeroF1C5BW0 = m1014boximpl2 == null ? Offset.INSTANCE.m1041getZeroF1C5BW0() : m1014boximpl2.getPackedValue();
            TextFieldState state2 = getState();
            Offset m1014boximpl3 = (state2 == null || (layoutCoordinates2 = state2.getLayoutCoordinates()) == null) ? null : Offset.m1014boximpl(layoutCoordinates2.mo2524localToRootMKHz9U(m586getHandlePositiontuRUvjQ$foundation_release(false)));
            long m1041getZeroF1C5BW02 = m1014boximpl3 == null ? Offset.INSTANCE.m1041getZeroF1C5BW0() : m1014boximpl3.getPackedValue();
            TextFieldState state3 = getState();
            if (state3 == null || (layoutCoordinates3 = state3.getLayoutCoordinates()) == null) {
                m1014boximpl = null;
            } else {
                TextLayoutResultProxy layoutResult = textFieldState.getLayoutResult();
                Rect cursorRect = (layoutResult == null || (value = layoutResult.getValue()) == null) ? null : value.getCursorRect(RangesKt.coerceIn(TextRange.m2766getStartimpl(getValue().getSelection()), 0, Math.max(0, getValue().getText().length() - 1)));
                m1014boximpl = Offset.m1014boximpl(layoutCoordinates3.mo2524localToRootMKHz9U(OffsetKt.Offset(0.0f, cursorRect == null ? 0.0f : cursorRect.getTop())));
            }
            float m1026getYimpl = m1014boximpl == null ? 0.0f : Offset.m1026getYimpl(m1014boximpl.getPackedValue());
            TextFieldState state4 = getState();
            if (state4 != null && (layoutCoordinates4 = state4.getLayoutCoordinates()) != null) {
                TextLayoutResultProxy layoutResult2 = textFieldState.getLayoutResult();
                if (layoutResult2 != null && (value2 = layoutResult2.getValue()) != null) {
                    rect = value2.getCursorRect(RangesKt.coerceIn(TextRange.m2761getEndimpl(getValue().getSelection()), 0, Math.max(0, getValue().getText().length() - 1)));
                }
                offset = Offset.m1014boximpl(layoutCoordinates4.mo2524localToRootMKHz9U(OffsetKt.Offset(0.0f, rect == null ? 0.0f : rect.getTop())));
            }
            return new Rect(Math.min(Offset.m1025getXimpl(m1041getZeroF1C5BW0), Offset.m1025getXimpl(m1041getZeroF1C5BW02)), Math.min(m1026getYimpl, offset != null ? Offset.m1026getYimpl(offset.getPackedValue()) : 0.0f), Math.max(Offset.m1025getXimpl(m1041getZeroF1C5BW0), Offset.m1025getXimpl(m1041getZeroF1C5BW02)), Math.max(Offset.m1026getYimpl(m1041getZeroF1C5BW0), Offset.m1026getYimpl(m1041getZeroF1C5BW02)) + (Dp.m3007constructorimpl(25) * textFieldState.getTextDelegate().getDensity().getDensity()));
        }
        return Rect.INSTANCE.getZero();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelection(TextFieldValue value, int transformedStartOffset, int transformedEndOffset, boolean isStartHandle, SelectionAdjustment adjustment) {
        TextLayoutResultProxy layoutResult;
        long TextRange = TextRangeKt.TextRange(this.offsetMapping.originalToTransformed(TextRange.m2766getStartimpl(value.getSelection())), this.offsetMapping.originalToTransformed(TextRange.m2761getEndimpl(value.getSelection())));
        TextFieldState textFieldState = this.state;
        long m581getTextFieldSelectionbb3KNj8 = TextFieldSelectionDelegateKt.m581getTextFieldSelectionbb3KNj8((textFieldState == null || (layoutResult = textFieldState.getLayoutResult()) == null) ? null : layoutResult.getValue(), transformedStartOffset, transformedEndOffset, TextRange.m2760getCollapsedimpl(TextRange) ? null : TextRange.m2754boximpl(TextRange), isStartHandle, adjustment);
        long TextRange2 = TextRangeKt.TextRange(this.offsetMapping.transformedToOriginal(TextRange.m2766getStartimpl(m581getTextFieldSelectionbb3KNj8)), this.offsetMapping.transformedToOriginal(TextRange.m2761getEndimpl(m581getTextFieldSelectionbb3KNj8)));
        if (TextRange.m2759equalsimpl0(TextRange2, value.getSelection())) {
            return;
        }
        HapticFeedback hapticFeedback = this.hapticFeedBack;
        if (hapticFeedback != null) {
            hapticFeedback.mo1760performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m1769getTextHandleMove5zf0vsI());
        }
        this.onValueChange.invoke(m583createTextFieldValueFDrldGo(value.getAnnotatedString(), TextRange2));
        TextFieldState textFieldState2 = this.state;
        if (textFieldState2 != null) {
            textFieldState2.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, true));
        }
        TextFieldState textFieldState3 = this.state;
        if (textFieldState3 == null) {
            return;
        }
        textFieldState3.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, false));
    }

    private final void setSelectionStatus(boolean on) {
        TextFieldState textFieldState = this.state;
        if (textFieldState == null) {
            return;
        }
        textFieldState.setSelectionIsOn(on);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createTextFieldValue-FDrldGo, reason: not valid java name */
    public final TextFieldValue m583createTextFieldValueFDrldGo(AnnotatedString annotatedString, long selection) {
        return new TextFieldValue(annotatedString, selection, (TextRange) null, 4, (DefaultConstructorMarker) null);
    }
}
