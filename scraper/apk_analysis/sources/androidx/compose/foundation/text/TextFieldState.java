package androidx.compose.foundation.text;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CoreTextField.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004Jg\u0010G\u001a\u00020)2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020Vø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bW\u0010XR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR+\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R6\u0010*\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020.¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R+\u00101\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b4\u0010\u0010\u001a\u0004\b2\u0010\b\"\u0004\b3\u0010\nR\u0011\u00105\u001a\u000206¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\b\"\u0004\b;\u0010\nR+\u0010<\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b?\u0010\u0010\u001a\u0004\b=\u0010\b\"\u0004\b>\u0010\nR+\u0010@\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010\u0010\u001a\u0004\bA\u0010\b\"\u0004\bB\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010\u0004\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006Y"}, d2 = {"Landroidx/compose/foundation/text/TextFieldState;", "", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "(Landroidx/compose/foundation/text/TextDelegate;)V", "draggingHandle", "", "getDraggingHandle", "()Z", "setDraggingHandle", "(Z)V", "<set-?>", "hasFocus", "getHasFocus", "setHasFocus", "hasFocus$delegate", "Landroidx/compose/runtime/MutableState;", "inputSession", "Landroidx/compose/ui/text/input/TextInputSession;", "getInputSession", "()Landroidx/compose/ui/text/input/TextInputSession;", "setInputSession", "(Landroidx/compose/ui/text/input/TextInputSession;)V", "keyboardActionRunner", "Landroidx/compose/foundation/text/KeyboardActionRunner;", "getKeyboardActionRunner", "()Landroidx/compose/foundation/text/KeyboardActionRunner;", "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "layoutResult", "Landroidx/compose/foundation/text/TextLayoutResultProxy;", "getLayoutResult", "()Landroidx/compose/foundation/text/TextLayoutResultProxy;", "setLayoutResult", "(Landroidx/compose/foundation/text/TextLayoutResultProxy;)V", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/TextFieldValue;", "", "onValueChange", "getOnValueChange", "()Lkotlin/jvm/functions/Function1;", "processor", "Landroidx/compose/ui/text/input/EditProcessor;", "getProcessor", "()Landroidx/compose/ui/text/input/EditProcessor;", "selectionIsOn", "getSelectionIsOn", "setSelectionIsOn", "selectionIsOn$delegate", "selectionPaint", "Landroidx/compose/ui/graphics/Paint;", "getSelectionPaint", "()Landroidx/compose/ui/graphics/Paint;", "showFloatingToolbar", "getShowFloatingToolbar", "setShowFloatingToolbar", "showSelectionHandleEnd", "getShowSelectionHandleEnd", "setShowSelectionHandleEnd", "showSelectionHandleEnd$delegate", "showSelectionHandleStart", "getShowSelectionHandleStart", "setShowSelectionHandleStart", "showSelectionHandleStart$delegate", "getTextDelegate", "()Landroidx/compose/foundation/text/TextDelegate;", "setTextDelegate", "update", "visualText", "Landroidx/compose/ui/text/AnnotatedString;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "softWrap", "density", "Landroidx/compose/ui/unit/Density;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "selectionBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "update-aKPr-nQ", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/Font$ResourceLoader;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/ui/focus/FocusManager;J)V", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextFieldState {
    private boolean draggingHandle;

    /* renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus;
    private TextInputSession inputSession;
    private final KeyboardActionRunner keyboardActionRunner;
    private LayoutCoordinates layoutCoordinates;
    private TextLayoutResultProxy layoutResult;
    private Function1<? super TextFieldValue, Unit> onValueChange;
    private final EditProcessor processor;

    /* renamed from: selectionIsOn$delegate, reason: from kotlin metadata */
    private final MutableState selectionIsOn;
    private final Paint selectionPaint;
    private boolean showFloatingToolbar;

    /* renamed from: showSelectionHandleEnd$delegate, reason: from kotlin metadata */
    private final MutableState showSelectionHandleEnd;

    /* renamed from: showSelectionHandleStart$delegate, reason: from kotlin metadata */
    private final MutableState showSelectionHandleStart;
    private TextDelegate textDelegate;

    public TextFieldState(TextDelegate textDelegate) {
        Intrinsics.checkNotNullParameter(textDelegate, "textDelegate");
        this.textDelegate = textDelegate;
        this.processor = new EditProcessor();
        this.hasFocus = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.selectionIsOn = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.showSelectionHandleStart = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.showSelectionHandleEnd = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.keyboardActionRunner = new KeyboardActionRunner();
        this.onValueChange = new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.foundation.text.TextFieldState$onValueChange$1
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
        this.selectionPaint = AndroidPaint_androidKt.Paint();
    }

    public final TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public final void setTextDelegate(TextDelegate textDelegate) {
        Intrinsics.checkNotNullParameter(textDelegate, "<set-?>");
        this.textDelegate = textDelegate;
    }

    public final EditProcessor getProcessor() {
        return this.processor;
    }

    public final TextInputSession getInputSession() {
        return this.inputSession;
    }

    public final void setInputSession(TextInputSession textInputSession) {
        this.inputSession = textInputSession;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    public final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    public final LayoutCoordinates getLayoutCoordinates() {
        return this.layoutCoordinates;
    }

    public final void setLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.layoutCoordinates = layoutCoordinates;
    }

    public final TextLayoutResultProxy getLayoutResult() {
        return this.layoutResult;
    }

    public final void setLayoutResult(TextLayoutResultProxy textLayoutResultProxy) {
        this.layoutResult = textLayoutResultProxy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getSelectionIsOn() {
        return ((Boolean) this.selectionIsOn.getValue()).booleanValue();
    }

    public final void setSelectionIsOn(boolean z) {
        this.selectionIsOn.setValue(Boolean.valueOf(z));
    }

    public final boolean getDraggingHandle() {
        return this.draggingHandle;
    }

    public final void setDraggingHandle(boolean z) {
        this.draggingHandle = z;
    }

    public final boolean getShowFloatingToolbar() {
        return this.showFloatingToolbar;
    }

    public final void setShowFloatingToolbar(boolean z) {
        this.showFloatingToolbar = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getShowSelectionHandleStart() {
        return ((Boolean) this.showSelectionHandleStart.getValue()).booleanValue();
    }

    public final void setShowSelectionHandleStart(boolean z) {
        this.showSelectionHandleStart.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getShowSelectionHandleEnd() {
        return ((Boolean) this.showSelectionHandleEnd.getValue()).booleanValue();
    }

    public final void setShowSelectionHandleEnd(boolean z) {
        this.showSelectionHandleEnd.setValue(Boolean.valueOf(z));
    }

    public final KeyboardActionRunner getKeyboardActionRunner() {
        return this.keyboardActionRunner;
    }

    public final Function1<TextFieldValue, Unit> getOnValueChange() {
        return this.onValueChange;
    }

    public final Paint getSelectionPaint() {
        return this.selectionPaint;
    }

    /* renamed from: update-aKPr-nQ, reason: not valid java name */
    public final void m523updateaKPrnQ(AnnotatedString visualText, TextStyle textStyle, boolean softWrap, Density density, Font.ResourceLoader resourceLoader, Function1<? super TextFieldValue, Unit> onValueChange, KeyboardActions keyboardActions, FocusManager focusManager, long selectionBackgroundColor) {
        TextDelegate m451updateTextDelegatex_uQXYA;
        Intrinsics.checkNotNullParameter(visualText, "visualText");
        Intrinsics.checkNotNullParameter(textStyle, "textStyle");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(resourceLoader, "resourceLoader");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Intrinsics.checkNotNullParameter(keyboardActions, "keyboardActions");
        Intrinsics.checkNotNullParameter(focusManager, "focusManager");
        this.onValueChange = onValueChange;
        this.selectionPaint.mo1146setColor8_81llA(selectionBackgroundColor);
        KeyboardActionRunner keyboardActionRunner = this.keyboardActionRunner;
        keyboardActionRunner.setKeyboardActions(keyboardActions);
        keyboardActionRunner.setFocusManager(focusManager);
        m451updateTextDelegatex_uQXYA = CoreTextKt.m451updateTextDelegatex_uQXYA(this.textDelegate, visualText, textStyle, density, resourceLoader, (r20 & 32) != 0 ? true : softWrap, (r20 & 64) != 0 ? TextOverflow.INSTANCE.m2962getClipgIe3tQ8() : 0, (r20 & 128) != 0 ? Integer.MAX_VALUE : 0, CollectionsKt.emptyList());
        this.textDelegate = m451updateTextDelegatex_uQXYA;
    }
}
