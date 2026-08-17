package androidx.compose.foundation.text;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import kotlin.Metadata;

/* compiled from: BasicTextField.kt */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u001aâ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b23\b\u0002\u0010\u001c\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001d¢\u0006\u0002\b\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001eH\u0007¢\u0006\u0002\u0010\"\u001aâ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020#2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b23\b\u0002\u0010\u001c\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001d¢\u0006\u0002\b\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001eH\u0007¢\u0006\u0002\u0010$¨\u0006%"}, d2 = {"BasicTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", ExtentionsKt.ENABLED_KEY, "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", "name", "innerTextField", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class BasicTextFieldKt {
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01ae, code lost:
    
        if (r0.changed(r52) == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01c8, code lost:
    
        if (r0.changed(r53) == false) goto L158;
     */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0213  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(final java.lang.String r39, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r40, androidx.compose.ui.Modifier r41, boolean r42, boolean r43, androidx.compose.ui.text.TextStyle r44, androidx.compose.foundation.text.KeyboardOptions r45, androidx.compose.foundation.text.KeyboardActions r46, boolean r47, int r48, androidx.compose.ui.text.input.VisualTransformation r49, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r50, androidx.compose.foundation.interaction.MutableInteractionSource r51, androidx.compose.ui.graphics.Brush r52, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r53, androidx.compose.runtime.Composer r54, final int r55, final int r56, final int r57) {
        /*
            Method dump skipped, instructions count: 1157
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* renamed from: BasicTextField$lambda-2, reason: not valid java name */
    private static final TextFieldValue m437BasicTextField$lambda2(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x01ae, code lost:
    
        if (r10.changed(r51) == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01c8, code lost:
    
        if (r10.changed(r52) == false) goto L158;
     */
    /* JADX WARN: Removed duplicated region for block: B:125:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0215  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(final androidx.compose.ui.text.input.TextFieldValue r38, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r39, androidx.compose.ui.Modifier r40, boolean r41, boolean r42, androidx.compose.ui.text.TextStyle r43, androidx.compose.foundation.text.KeyboardOptions r44, androidx.compose.foundation.text.KeyboardActions r45, boolean r46, int r47, androidx.compose.ui.text.input.VisualTransformation r48, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r49, androidx.compose.foundation.interaction.MutableInteractionSource r50, androidx.compose.ui.graphics.Brush r51, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, androidx.compose.runtime.Composer r53, final int r54, final int r55, final int r56) {
        /*
            Method dump skipped, instructions count: 1076
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
