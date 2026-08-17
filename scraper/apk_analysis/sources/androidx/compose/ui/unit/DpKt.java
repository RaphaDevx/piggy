package androidx.compose.ui.unit;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: Dp.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\b\u001a%\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u0001H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&\u001a-\u0010'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0007H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010,\u001a-\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u0007H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.\u001a&\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u0001H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00103\u001a&\u00104\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u0001H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b5\u00103\u001a\"\u00106\u001a\u00020\u0001*\u00020\u00012\u0006\u00107\u001a\u00020\u0001H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b8\u00103\u001a\"\u00109\u001a\u00020\u0001*\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b;\u00103\u001a*\u0010<\u001a\u00020\u0001*\u00020\u00012\u0006\u00107\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b=\u0010,\u001a+\u0010>\u001a\u00020\u0001*\u00020\u00012\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00010@H\u0086\bø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bA\u0010B\u001a\"\u0010C\u001a\u00020\u0001*\u00020\u00022\u0006\u0010D\u001a\u00020\u0001H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bE\u0010F\u001a\"\u0010C\u001a\u00020\u0001*\u00020\u00072\u0006\u0010D\u001a\u00020\u0001H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bE\u00103\u001a\"\u0010C\u001a\u00020\u0001*\u00020\n2\u0006\u0010D\u001a\u00020\u0001H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bE\u0010G\"\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00078Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0003\u0010\b\u001a\u0004\b\u0005\u0010\t\"\"\u0010\u0000\u001a\u00020\u0001*\u00020\n8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0003\u0010\u000b\u001a\u0004\b\u0005\u0010\f\"\"\u0010\r\u001a\u00020\u0001*\u00020\u000e8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\"\u0010\u0013\u001a\u00020\u0014*\u00020\u00018Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\b\u001a\u0004\b\u0016\u0010\u0017\"\"\u0010\u0018\u001a\u00020\u0014*\u00020\u00018Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0019\u0010\b\u001a\u0004\b\u001a\u0010\u0017\"\"\u0010\u001b\u001a\u00020\u0014*\u00020\u00018Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001c\u0010\b\u001a\u0004\b\u001d\u0010\u0017\"\"\u0010\u001e\u001a\u00020\u0001*\u00020\u000e8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001f\u0010\u0010\u001a\u0004\b \u0010\u0012\u0082\u0002\u0012\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0005\b\u009920\u0001¨\u0006H"}, d2 = {"dp", "Landroidx/compose/ui/unit/Dp;", "", "getDp$annotations", "(D)V", "getDp", "(D)F", "", "(F)V", "(F)F", "", "(I)V", "(I)F", "height", "Landroidx/compose/ui/unit/DpRect;", "getHeight$annotations", "(Landroidx/compose/ui/unit/DpRect;)V", "getHeight", "(Landroidx/compose/ui/unit/DpRect;)F", "isFinite", "", "isFinite-0680j_4$annotations", "isFinite-0680j_4", "(F)Z", "isSpecified", "isSpecified-0680j_4$annotations", "isSpecified-0680j_4", "isUnspecified", "isUnspecified-0680j_4$annotations", "isUnspecified-0680j_4", "width", "getWidth$annotations", "getWidth", "DpOffset", "Landroidx/compose/ui/unit/DpOffset;", "x", "y", "DpOffset-YgX7TsA", "(FF)J", "lerp", "start", "stop", "fraction", "lerp-Md-fbLM", "(FFF)F", "lerp-xhh869w", "(JJF)J", CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.SERIALIZED_NAME_MAX, "a", "b", "max-YgX7TsA", "(FF)F", "min", "min-YgX7TsA", "coerceAtLeast", "minimumValue", "coerceAtLeast-YgX7TsA", "coerceAtMost", "maximumValue", "coerceAtMost-YgX7TsA", "coerceIn", "coerceIn-2z7ARbQ", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-D5KLDUw", "(FLkotlin/jvm/functions/Function0;)F", "times", Request.JsonKeys.OTHER, "times-3ABfNKs", "(DF)F", "(IF)F", "ui-unit_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DpKt {
    public static /* synthetic */ void getDp$annotations(double d) {
    }

    public static /* synthetic */ void getDp$annotations(float f) {
    }

    public static /* synthetic */ void getDp$annotations(int i) {
    }

    public static /* synthetic */ void getHeight$annotations(DpRect dpRect) {
    }

    public static /* synthetic */ void getWidth$annotations(DpRect dpRect) {
    }

    /* renamed from: isFinite-0680j_4, reason: not valid java name */
    public static final boolean m3032isFinite0680j_4(float f) {
        return !(f == Float.POSITIVE_INFINITY);
    }

    /* renamed from: isFinite-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m3033isFinite0680j_4$annotations(float f) {
    }

    /* renamed from: isSpecified-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m3035isSpecified0680j_4$annotations(float f) {
    }

    /* renamed from: isUnspecified-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m3037isUnspecified0680j_4$annotations(float f) {
    }

    /* renamed from: isSpecified-0680j_4, reason: not valid java name */
    public static final boolean m3034isSpecified0680j_4(float f) {
        return !Float.isNaN(f);
    }

    /* renamed from: isUnspecified-0680j_4, reason: not valid java name */
    public static final boolean m3036isUnspecified0680j_4(float f) {
        return Float.isNaN(f);
    }

    public static final float getDp(int i) {
        return Dp.m3007constructorimpl(i);
    }

    public static final float getDp(double d) {
        return Dp.m3007constructorimpl((float) d);
    }

    public static final float getDp(float f) {
        return Dp.m3007constructorimpl(f);
    }

    /* renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m3044times3ABfNKs(float f, float f2) {
        return Dp.m3007constructorimpl(f * f2);
    }

    /* renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m3043times3ABfNKs(double d, float f) {
        return Dp.m3007constructorimpl(((float) d) * f);
    }

    /* renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m3045times3ABfNKs(int i, float f) {
        return Dp.m3007constructorimpl(i * f);
    }

    /* renamed from: min-YgX7TsA, reason: not valid java name */
    public static final float m3041minYgX7TsA(float f, float f2) {
        return Dp.m3007constructorimpl(Math.min(f, f2));
    }

    /* renamed from: max-YgX7TsA, reason: not valid java name */
    public static final float m3040maxYgX7TsA(float f, float f2) {
        return Dp.m3007constructorimpl(Math.max(f, f2));
    }

    /* renamed from: coerceIn-2z7ARbQ, reason: not valid java name */
    public static final float m3031coerceIn2z7ARbQ(float f, float f2, float f3) {
        return Dp.m3007constructorimpl(RangesKt.coerceIn(f, f2, f3));
    }

    /* renamed from: coerceAtLeast-YgX7TsA, reason: not valid java name */
    public static final float m3029coerceAtLeastYgX7TsA(float f, float f2) {
        return Dp.m3007constructorimpl(RangesKt.coerceAtLeast(f, f2));
    }

    /* renamed from: coerceAtMost-YgX7TsA, reason: not valid java name */
    public static final float m3030coerceAtMostYgX7TsA(float f, float f2) {
        return Dp.m3007constructorimpl(RangesKt.coerceAtMost(f, f2));
    }

    /* renamed from: lerp-Md-fbLM, reason: not valid java name */
    public static final float m3038lerpMdfbLM(float f, float f2, float f3) {
        return Dp.m3007constructorimpl(MathHelpersKt.lerp(f, f2, f3));
    }

    /* renamed from: lerp-xhh869w, reason: not valid java name */
    public static final long m3039lerpxhh869w(long j, long j2, float f) {
        return m3028DpOffsetYgX7TsA(m3038lerpMdfbLM(DpOffset.m3052getXD9Ej5fM(j), DpOffset.m3052getXD9Ej5fM(j2), f), m3038lerpMdfbLM(DpOffset.m3054getYD9Ej5fM(j), DpOffset.m3054getYD9Ej5fM(j2), f));
    }

    public static final float getWidth(DpRect dpRect) {
        Intrinsics.checkNotNullParameter(dpRect, "<this>");
        return Dp.m3007constructorimpl(dpRect.m3074getRightD9Ej5fM() - dpRect.m3073getLeftD9Ej5fM());
    }

    public static final float getHeight(DpRect dpRect) {
        Intrinsics.checkNotNullParameter(dpRect, "<this>");
        return Dp.m3007constructorimpl(dpRect.m3072getBottomD9Ej5fM() - dpRect.m3075getTopD9Ej5fM());
    }

    /* renamed from: takeOrElse-D5KLDUw, reason: not valid java name */
    public static final float m3042takeOrElseD5KLDUw(float f, Function0<Dp> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return !Float.isNaN(f) ? f : block.invoke().getValue();
    }

    /* renamed from: DpOffset-YgX7TsA, reason: not valid java name */
    public static final long m3028DpOffsetYgX7TsA(float f, float f2) {
        return DpOffset.m3047constructorimpl((Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32));
    }
}
