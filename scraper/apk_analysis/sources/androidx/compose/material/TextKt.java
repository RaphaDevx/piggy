package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.text.TextStyle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.stats.b;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Text.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00060\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\u001aß\u0001\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020*0(2\u0014\b\u0002\u0010+\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00060,2\b\b\u0002\u0010.\u001a\u00020\u0002H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b/\u00100\u001aÉ\u0001\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020)2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\u0014\b\u0002\u0010+\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00060,2\b\b\u0002\u0010.\u001a\u00020\u0002H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u00102\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00063"}, d2 = {"LocalTextStyle", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/ui/text/TextStyle;", "getLocalTextStyle", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "ProvideTextStyle", "", "value", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Text", "text", "Landroidx/compose/ui/text/AnnotatedString;", "modifier", "Landroidx/compose/ui/Modifier;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "fontSize", "Landroidx/compose/ui/unit/TextUnit;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "letterSpacing", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "textAlign", "Landroidx/compose/ui/text/style/TextAlign;", "lineHeight", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "inlineContent", "", "", "Landroidx/compose/foundation/text/InlineTextContent;", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "style", "Text--4IGK_g", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text-fLXpl1I", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextKt {
    private static final ProvidableCompositionLocal<TextStyle> LocalTextStyle = CompositionLocalKt.compositionLocalOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<TextStyle>() { // from class: androidx.compose.material.TextKt$LocalTextStyle$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TextStyle invoke() {
            return TextStyle.INSTANCE.getDefault();
        }
    });

    /* JADX WARN: Removed duplicated region for block: B:10:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f7  */
    /* renamed from: Text-fLXpl1I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m911TextfLXpl1I(final java.lang.String r53, androidx.compose.ui.Modifier r54, long r55, long r57, androidx.compose.ui.text.font.FontStyle r59, androidx.compose.ui.text.font.FontWeight r60, androidx.compose.ui.text.font.FontFamily r61, long r62, androidx.compose.ui.text.style.TextDecoration r64, androidx.compose.ui.text.style.TextAlign r65, long r66, int r68, boolean r69, int r70, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r71, androidx.compose.ui.text.TextStyle r72, androidx.compose.runtime.Composer r73, final int r74, final int r75, final int r76) {
        /*
            Method dump skipped, instructions count: 984
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextKt.m911TextfLXpl1I(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /* renamed from: Text--4IGK_g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m910Text4IGK_g(final androidx.compose.ui.text.AnnotatedString r59, androidx.compose.ui.Modifier r60, long r61, long r63, androidx.compose.ui.text.font.FontStyle r65, androidx.compose.ui.text.font.FontWeight r66, androidx.compose.ui.text.font.FontFamily r67, long r68, androidx.compose.ui.text.style.TextDecoration r70, androidx.compose.ui.text.style.TextAlign r71, long r72, int r74, boolean r75, int r76, java.util.Map<java.lang.String, androidx.compose.foundation.text.InlineTextContent> r77, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r78, androidx.compose.ui.text.TextStyle r79, androidx.compose.runtime.Composer r80, final int r81, final int r82, final int r83) {
        /*
            Method dump skipped, instructions count: 1127
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextKt.m910Text4IGK_g(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, java.util.Map, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final ProvidableCompositionLocal<TextStyle> getLocalTextStyle() {
        return LocalTextStyle;
    }

    public static final void ProvideTextStyle(final TextStyle value, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer startRestartGroup = composer.startRestartGroup(1919620117);
        ComposerKt.sourceInformation(startRestartGroup, "C(ProvideTextStyle)P(1)250@11821L7,251@11846L80:Text.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (startRestartGroup.changed(value) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & b.p) == 0) {
            i2 |= startRestartGroup.changed(content) ? 32 : 16;
        }
        if (((i2 & 91) ^ 18) != 0 || !startRestartGroup.getSkipping()) {
            ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{providableCompositionLocal.provides(((TextStyle) consume).merge(value))}, content, startRestartGroup, (i2 & b.p) | 8);
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextKt$ProvideTextStyle$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                TextKt.ProvideTextStyle(TextStyle.this, content, composer2, i | 1);
            }
        });
    }
}
