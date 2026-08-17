package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CoreTextField.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aä\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00132\b\b\u0002\u0010\u001b\u001a\u00020\u001323\b\u0002\u0010\u001c\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001d¢\u0006\u0002\b\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001eH\u0001¢\u0006\u0002\u0010\"\u001a\u001d\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010'\u001a[\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010/\u001a\u000200H\u0002ø\u0001\u0000\u001a \u00101\u001a\u00020\u00012\u0006\u0010+\u001a\u00020,2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0013H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, d2 = {"CoreTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "softWrap", "", "maxLines", "", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", ExtentionsKt.ENABLED_KEY, "readOnly", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", "name", "innerTextField", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;ZILandroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/text/KeyboardActions;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SelectionToolbarAndHandles", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "show", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/runtime/Composer;I)V", "notifyTextInputServiceOnFocusChange", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", SentryThread.JsonKeys.STATE, "Landroidx/compose/foundation/text/TextFieldState;", "onImeActionPerformed", "Landroidx/compose/ui/text/input/ImeAction;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "tapToFocus", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "allowKeyboard", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class CoreTextFieldKt {
    /* JADX WARN: Code restructure failed: missing block: B:163:0x044b, code lost:
    
        if (r9 == null) goto L260;
     */
    /* JADX WARN: Removed duplicated region for block: B:113:0x030f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03a4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0525  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0666 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x06b0  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x074e  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x075e  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x05f8  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0819  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0213  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CoreTextField(final androidx.compose.ui.text.input.TextFieldValue r40, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r41, androidx.compose.ui.Modifier r42, androidx.compose.ui.text.TextStyle r43, androidx.compose.ui.text.input.VisualTransformation r44, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r45, androidx.compose.foundation.interaction.MutableInteractionSource r46, androidx.compose.ui.graphics.Brush r47, boolean r48, int r49, androidx.compose.ui.text.input.ImeOptions r50, androidx.compose.foundation.text.KeyboardActions r51, boolean r52, boolean r53, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, androidx.compose.runtime.Composer r55, final int r56, final int r57, final int r58) {
        /*
            Method dump skipped, instructions count: 2102
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, boolean, int, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.text.KeyboardActions, boolean, boolean, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tapToFocus(TextFieldState textFieldState, FocusRequester focusRequester, boolean z) {
        TextInputSession inputSession;
        if (!textFieldState.getHasFocus()) {
            focusRequester.requestFocus();
        } else {
            if (!z || (inputSession = textFieldState.getInputSession()) == null) {
                return;
            }
            inputSession.showSoftwareKeyboard();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyTextInputServiceOnFocusChange(TextInputService textInputService, TextFieldState textFieldState, TextFieldValue textFieldValue, ImeOptions imeOptions, Function1<? super TextFieldValue, Unit> function1, Function1<? super ImeAction, Unit> function12, OffsetMapping offsetMapping) {
        TextLayoutResultProxy layoutResult;
        if (textFieldState.getHasFocus()) {
            TextInputSession onFocus$foundation_release = TextFieldDelegate.INSTANCE.onFocus$foundation_release(textInputService, textFieldValue, textFieldState.getProcessor(), imeOptions, function1, function12);
            LayoutCoordinates layoutCoordinates = textFieldState.getLayoutCoordinates();
            if (layoutCoordinates != null && (layoutResult = textFieldState.getLayoutResult()) != null) {
                TextFieldDelegate.INSTANCE.notifyFocusedRect$foundation_release(textFieldValue, textFieldState.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, onFocus$foundation_release, textFieldState.getHasFocus(), offsetMapping);
            }
            Unit unit = Unit.INSTANCE;
            textFieldState.setInputSession(onFocus$foundation_release);
            return;
        }
        TextInputSession inputSession = textFieldState.getInputSession();
        if (inputSession != null) {
            TextFieldDelegate.INSTANCE.onBlur$foundation_release(inputSession, textFieldState.getProcessor(), function1);
        }
        textFieldState.setInputSession(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SelectionToolbarAndHandles(final TextFieldSelectionManager textFieldSelectionManager, final boolean z, Composer composer, final int i) {
        TextLayoutResultProxy layoutResult;
        Composer startRestartGroup = composer.startRestartGroup(-498405963);
        ComposerKt.sourceInformation(startRestartGroup, "C(SelectionToolbarAndHandles):CoreTextField.kt#423gt5");
        if (z) {
            startRestartGroup.startReplaceableGroup(-498405865);
            ComposerKt.sourceInformation(startRestartGroup, "");
            TextFieldState state = textFieldSelectionManager.getState();
            TextLayoutResult value = (state == null || (layoutResult = state.getLayoutResult()) == null) ? null : layoutResult.getValue();
            if (value == null) {
                startRestartGroup.startReplaceableGroup(650514375);
                startRestartGroup.endReplaceableGroup();
            } else {
                startRestartGroup.startReplaceableGroup(-671752326);
                ComposerKt.sourceInformation(startRestartGroup, "");
                if (!TextRange.m2760getCollapsedimpl(textFieldSelectionManager.getValue().getSelection())) {
                    startRestartGroup.startReplaceableGroup(-1230821551);
                    ComposerKt.sourceInformation(startRestartGroup, "");
                    Pair pair = new Pair(value.getBidiRunDirection(textFieldSelectionManager.getOffsetMapping().originalToTransformed(TextRange.m2766getStartimpl(textFieldSelectionManager.getValue().getSelection()))), value.getBidiRunDirection(Math.max(textFieldSelectionManager.getOffsetMapping().originalToTransformed(TextRange.m2761getEndimpl(textFieldSelectionManager.getValue().getSelection())) - 1, 0)));
                    TextFieldState state2 = textFieldSelectionManager.getState();
                    if (Intrinsics.areEqual((Object) (state2 == null ? null : Boolean.valueOf(state2.getShowSelectionHandleStart())), (Object) true)) {
                        startRestartGroup.startReplaceableGroup(-1230821052);
                        ComposerKt.sourceInformation(startRestartGroup, "712@29382L200");
                        TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, pair, textFieldSelectionManager, startRestartGroup, 518);
                        startRestartGroup.endReplaceableGroup();
                    } else {
                        startRestartGroup.startReplaceableGroup(-1230820804);
                        startRestartGroup.endReplaceableGroup();
                    }
                    TextFieldState state3 = textFieldSelectionManager.getState();
                    if (Intrinsics.areEqual((Object) (state3 != null ? Boolean.valueOf(state3.getShowSelectionHandleEnd()) : null), (Object) true)) {
                        startRestartGroup.startReplaceableGroup(-1230820732);
                        ComposerKt.sourceInformation(startRestartGroup, "719@29702L201");
                        TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, pair, textFieldSelectionManager, startRestartGroup, 518);
                        startRestartGroup.endReplaceableGroup();
                    } else {
                        startRestartGroup.startReplaceableGroup(-1230820483);
                        startRestartGroup.endReplaceableGroup();
                    }
                    startRestartGroup.endReplaceableGroup();
                } else {
                    startRestartGroup.startReplaceableGroup(-1230820465);
                    startRestartGroup.endReplaceableGroup();
                }
                TextFieldState state4 = textFieldSelectionManager.getState();
                if (state4 != null) {
                    if (textFieldSelectionManager.isTextChanged$foundation_release()) {
                        state4.setShowFloatingToolbar(false);
                    }
                    if (state4.getHasFocus()) {
                        if (state4.getShowFloatingToolbar()) {
                            textFieldSelectionManager.showSelectionToolbar$foundation_release();
                        } else {
                            textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                        }
                    }
                }
                startRestartGroup.endReplaceableGroup();
            }
            startRestartGroup.endReplaceableGroup();
        } else {
            startRestartGroup.startReplaceableGroup(-498404033);
            startRestartGroup.endReplaceableGroup();
            textFieldSelectionManager.hideSelectionToolbar$foundation_release();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$SelectionToolbarAndHandles$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                CoreTextFieldKt.SelectionToolbarAndHandles(TextFieldSelectionManager.this, z, composer2, i | 1);
            }
        });
    }
}
