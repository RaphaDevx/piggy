package androidx.compose.ui.text.input;

import android.view.inputmethod.EditorInfo;
import androidx.compose.ui.text.TextRange;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.inputmethod.EditorInfoCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextInputServiceAndroid.android.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"DEBUG_CLASS", "", "hasFlag", "", "bits", "", "flag", "update", "", "Landroid/view/inputmethod/EditorInfo;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextInputServiceAndroid_androidKt {
    private static final String DEBUG_CLASS = "TextInputServiceAndroid";

    private static final boolean hasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    public static final void update(EditorInfo editorInfo, ImeOptions imeOptions, TextFieldValue textFieldValue) {
        Intrinsics.checkNotNullParameter(editorInfo, "<this>");
        Intrinsics.checkNotNullParameter(imeOptions, "imeOptions");
        Intrinsics.checkNotNullParameter(textFieldValue, "textFieldValue");
        int imeAction = imeOptions.getImeAction();
        int i = 6;
        if (ImeAction.m2827equalsimpl0(imeAction, ImeAction.INSTANCE.m2831getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i = 0;
            }
        } else if (ImeAction.m2827equalsimpl0(imeAction, ImeAction.INSTANCE.m2835getNoneeUduSuo())) {
            i = 1;
        } else if (ImeAction.m2827equalsimpl0(imeAction, ImeAction.INSTANCE.m2833getGoeUduSuo())) {
            i = 2;
        } else if (ImeAction.m2827equalsimpl0(imeAction, ImeAction.INSTANCE.m2834getNexteUduSuo())) {
            i = 5;
        } else if (ImeAction.m2827equalsimpl0(imeAction, ImeAction.INSTANCE.m2836getPreviouseUduSuo())) {
            i = 7;
        } else if (ImeAction.m2827equalsimpl0(imeAction, ImeAction.INSTANCE.m2837getSearcheUduSuo())) {
            i = 3;
        } else if (ImeAction.m2827equalsimpl0(imeAction, ImeAction.INSTANCE.m2838getSendeUduSuo())) {
            i = 4;
        } else if (!ImeAction.m2827equalsimpl0(imeAction, ImeAction.INSTANCE.m2832getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction".toString());
        }
        editorInfo.imeOptions = i;
        int keyboardType = imeOptions.getKeyboardType();
        if (KeyboardType.m2860equalsimpl0(keyboardType, KeyboardType.INSTANCE.m2870getTextPjHm6EE())) {
            editorInfo.inputType = 1;
        } else if (KeyboardType.m2860equalsimpl0(keyboardType, KeyboardType.INSTANCE.m2864getAsciiPjHm6EE())) {
            editorInfo.inputType = 1;
            editorInfo.imeOptions |= Integer.MIN_VALUE;
        } else if (KeyboardType.m2860equalsimpl0(keyboardType, KeyboardType.INSTANCE.m2866getNumberPjHm6EE())) {
            editorInfo.inputType = 2;
        } else if (KeyboardType.m2860equalsimpl0(keyboardType, KeyboardType.INSTANCE.m2869getPhonePjHm6EE())) {
            editorInfo.inputType = 3;
        } else if (KeyboardType.m2860equalsimpl0(keyboardType, KeyboardType.INSTANCE.m2871getUriPjHm6EE())) {
            editorInfo.inputType = 17;
        } else if (KeyboardType.m2860equalsimpl0(keyboardType, KeyboardType.INSTANCE.m2865getEmailPjHm6EE())) {
            editorInfo.inputType = 33;
        } else if (KeyboardType.m2860equalsimpl0(keyboardType, KeyboardType.INSTANCE.m2868getPasswordPjHm6EE())) {
            editorInfo.inputType = 129;
        } else if (KeyboardType.m2860equalsimpl0(keyboardType, KeyboardType.INSTANCE.m2867getNumberPasswordPjHm6EE())) {
            editorInfo.inputType = 18;
        } else {
            throw new IllegalStateException("Invalid Keyboard Type".toString());
        }
        if (!imeOptions.getSingleLine() && hasFlag(editorInfo.inputType, 1)) {
            editorInfo.inputType |= 131072;
            if (ImeAction.m2827equalsimpl0(imeOptions.getImeAction(), ImeAction.INSTANCE.m2831getDefaulteUduSuo())) {
                editorInfo.imeOptions |= BasicMeasure.EXACTLY;
            }
        }
        if (hasFlag(editorInfo.inputType, 1)) {
            int capitalization = imeOptions.getCapitalization();
            if (KeyboardCapitalization.m2849equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m2853getCharactersIUNYP9k())) {
                editorInfo.inputType |= 4096;
            } else if (KeyboardCapitalization.m2849equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m2856getWordsIUNYP9k())) {
                editorInfo.inputType |= 8192;
            } else if (KeyboardCapitalization.m2849equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m2855getSentencesIUNYP9k())) {
                editorInfo.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                editorInfo.inputType |= 32768;
            }
        }
        editorInfo.initialSelStart = TextRange.m2766getStartimpl(textFieldValue.getSelection());
        editorInfo.initialSelEnd = TextRange.m2761getEndimpl(textFieldValue.getSelection());
        EditorInfoCompat.setInitialSurroundingText(editorInfo, textFieldValue.getText());
        editorInfo.imeOptions |= 33554432;
    }
}
