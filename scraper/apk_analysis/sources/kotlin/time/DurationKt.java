package kotlin.time;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: Duration.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0015\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\t\u001a\u001c\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\n\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001d\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0002\u0010\u0015\u001a\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a)\u0010\u0017\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aH\u0082\b\u001a)\u0010\u001c\u001a\u00020\u0002*\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aH\u0082\b\u001a\u0010\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006H\u0002\u001a\u0010\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0002\u001a\u0015\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010'\u001a\u0015\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010'\u001a\u001d\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0002H\u0002¢\u0006\u0002\u0010-\u001a\u0015\u0010.\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010'\u001a\u0015\u0010/\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010'\"\u000e\u0010\u001d\u001a\u00020\u0002X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"toDuration", "Lkotlin/time/Duration;", "", "unit", "Lkotlin/time/DurationUnit;", "(ILkotlin/time/DurationUnit;)J", "", "(JLkotlin/time/DurationUnit;)J", "", "(DLkotlin/time/DurationUnit;)J", "times", "duration", "times-mvk6XK0", "(IJ)J", "times-kIfJnKk", "(DJ)J", "parseDuration", "value", "", "strictIso", "", "(Ljava/lang/String;Z)J", "parseOverLongIsoComponent", "substringWhile", "startIndex", "predicate", "Lkotlin/Function1;", "", "skipWhile", "NANOS_IN_MILLIS", "MAX_NANOS", "MAX_MILLIS", "MAX_NANOS_IN_MILLIS", "nanosToMillis", "nanos", "millisToNanos", "millis", "durationOfNanos", "normalNanos", "(J)J", "durationOfMillis", "normalMillis", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfNanosNormalized", "durationOfMillisNormalized", "kotlin-stdlib"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class DurationKt {
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final int NANOS_IN_MILLIS = 1000000;

    /* JADX INFO: Access modifiers changed from: private */
    public static final long millisToNanos(long j) {
        return j * NANOS_IN_MILLIS;
    }

    public static final long toDuration(int i, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (unit.compareTo(DurationUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(i, unit, DurationUnit.NANOSECONDS));
        }
        return toDuration(i, unit);
    }

    public static final long toDuration(long j, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        long convertDurationUnitOverflow = DurationUnitKt.convertDurationUnitOverflow(MAX_NANOS, DurationUnit.NANOSECONDS, unit);
        if ((-convertDurationUnitOverflow) <= j && j <= convertDurationUnitOverflow) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(j, unit, DurationUnit.NANOSECONDS));
        }
        return durationOfMillis(RangesKt.coerceIn(DurationUnitKt.convertDurationUnit(j, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, MAX_MILLIS));
    }

    public static final long toDuration(double d, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double convertDurationUnit = DurationUnitKt.convertDurationUnit(d, unit, DurationUnit.NANOSECONDS);
        if (Double.isNaN(convertDurationUnit)) {
            throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
        }
        long roundToLong = MathKt.roundToLong(convertDurationUnit);
        if (-4611686018426999999L <= roundToLong && roundToLong < 4611686018427000000L) {
            return durationOfNanos(roundToLong);
        }
        return durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt.convertDurationUnit(d, unit, DurationUnit.MILLISECONDS)));
    }

    /* renamed from: times-mvk6XK0, reason: not valid java name */
    private static final long m5387timesmvk6XK0(int i, long j) {
        return Duration.m5321timesUwyO8pc(j, i);
    }

    /* renamed from: times-kIfJnKk, reason: not valid java name */
    private static final long m5386timeskIfJnKk(double d, long j) {
        return Duration.m5320timesUwyO8pc(j, d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long parseDuration(String str, boolean z) {
        boolean z2;
        char charAt;
        char charAt2;
        boolean z3;
        int length = str.length();
        if (length == 0) {
            throw new IllegalArgumentException("The string is empty");
        }
        long m5381getZEROUwyO8pc = Duration.INSTANCE.m5381getZEROUwyO8pc();
        char charAt3 = str.charAt(0);
        int i = (charAt3 == '+' || charAt3 == '-') ? 1 : 0;
        boolean z4 = i > 0;
        Object obj = null;
        boolean z5 = z4 && StringsKt.startsWith$default((CharSequence) str, '-', false, 2, (Object) null);
        if (length <= i) {
            throw new IllegalArgumentException("No components");
        }
        char c = ':';
        char c2 = '0';
        if (str.charAt(i) == 'P') {
            int i2 = i + 1;
            if (i2 == length) {
                throw new IllegalArgumentException();
            }
            DurationUnit durationUnit = null;
            boolean z6 = false;
            while (i2 < length) {
                if (str.charAt(i2) != 'T') {
                    int i3 = i2;
                    while (true) {
                        if (i3 >= str.length()) {
                            z3 = z5;
                            break;
                        }
                        char charAt4 = str.charAt(i3);
                        if (c2 > charAt4 || charAt4 >= c) {
                            z3 = z5;
                            if (!StringsKt.contains$default((CharSequence) "+-.", charAt4, false, 2, obj)) {
                                break;
                            }
                        } else {
                            z3 = z5;
                        }
                        i3++;
                        z5 = z3;
                        c = ':';
                        c2 = '0';
                    }
                    Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
                    String substring = str.substring(i2, i3);
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    String str2 = substring;
                    if (str2.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                    int length2 = i2 + substring.length();
                    String str3 = str;
                    if (length2 < 0 || length2 >= str3.length()) {
                        throw new IllegalArgumentException("Missing unit for value " + substring);
                    }
                    char charAt5 = str3.charAt(length2);
                    i2 = length2 + 1;
                    DurationUnit durationUnitByIsoChar = DurationUnitKt.durationUnitByIsoChar(charAt5, z6);
                    if (durationUnit != null && durationUnit.compareTo(durationUnitByIsoChar) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                    }
                    int indexOf$default = StringsKt.indexOf$default((CharSequence) str2, ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 6, (Object) null);
                    if (durationUnitByIsoChar == DurationUnit.SECONDS && indexOf$default > 0) {
                        Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
                        String substring2 = substring.substring(0, indexOf$default);
                        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                        long m5319plusLRDsOJo = Duration.m5319plusLRDsOJo(m5381getZEROUwyO8pc, toDuration(parseOverLongIsoComponent(substring2), durationUnitByIsoChar));
                        Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
                        String substring3 = substring.substring(indexOf$default);
                        Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
                        m5381getZEROUwyO8pc = Duration.m5319plusLRDsOJo(m5319plusLRDsOJo, toDuration(Double.parseDouble(substring3), durationUnitByIsoChar));
                    } else {
                        m5381getZEROUwyO8pc = Duration.m5319plusLRDsOJo(m5381getZEROUwyO8pc, toDuration(parseOverLongIsoComponent(substring), durationUnitByIsoChar));
                    }
                    durationUnit = durationUnitByIsoChar;
                    z5 = z3;
                    c = ':';
                    c2 = '0';
                    obj = null;
                } else {
                    if (z6 || (i2 = i2 + 1) == length) {
                        throw new IllegalArgumentException();
                    }
                    z6 = true;
                }
            }
            z2 = z5;
        } else {
            z2 = z5;
            if (z) {
                throw new IllegalArgumentException();
            }
            String str4 = "Unexpected order of duration components";
            if (StringsKt.regionMatches(str, i, "Infinity", 0, Math.max(length - i, 8), true)) {
                m5381getZEROUwyO8pc = Duration.INSTANCE.m5379getINFINITEUwyO8pc();
            } else {
                boolean z7 = !z4;
                if (z4 && str.charAt(i) == '(' && StringsKt.last(str) == ')') {
                    i++;
                    length--;
                    if (i == length) {
                        throw new IllegalArgumentException("No components");
                    }
                    z7 = true;
                }
                DurationUnit durationUnit2 = null;
                boolean z8 = false;
                while (i < length) {
                    if (z8 && z7) {
                        while (i < str.length() && str.charAt(i) == ' ') {
                            i++;
                        }
                    }
                    int i4 = i;
                    while (i4 < str.length() && (('0' <= (charAt2 = str.charAt(i4)) && charAt2 < ':') || charAt2 == '.')) {
                        i4++;
                    }
                    Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
                    String substring4 = str.substring(i, i4);
                    Intrinsics.checkNotNullExpressionValue(substring4, "substring(...)");
                    String str5 = substring4;
                    if (str5.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                    int length3 = i + substring4.length();
                    int i5 = length3;
                    while (i5 < str.length() && 'a' <= (charAt = str.charAt(i5)) && charAt < '{') {
                        i5++;
                    }
                    Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
                    String substring5 = str.substring(length3, i5);
                    Intrinsics.checkNotNullExpressionValue(substring5, "substring(...)");
                    i = length3 + substring5.length();
                    DurationUnit durationUnitByShortName = DurationUnitKt.durationUnitByShortName(substring5);
                    if (durationUnit2 != null && durationUnit2.compareTo(durationUnitByShortName) <= 0) {
                        throw new IllegalArgumentException(str4);
                    }
                    String str6 = str4;
                    int indexOf$default2 = StringsKt.indexOf$default((CharSequence) str5, ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 6, (Object) null);
                    if (indexOf$default2 > 0) {
                        Intrinsics.checkNotNull(substring4, "null cannot be cast to non-null type java.lang.String");
                        String substring6 = substring4.substring(0, indexOf$default2);
                        Intrinsics.checkNotNullExpressionValue(substring6, "substring(...)");
                        long m5319plusLRDsOJo2 = Duration.m5319plusLRDsOJo(m5381getZEROUwyO8pc, toDuration(Long.parseLong(substring6), durationUnitByShortName));
                        Intrinsics.checkNotNull(substring4, "null cannot be cast to non-null type java.lang.String");
                        String substring7 = substring4.substring(indexOf$default2);
                        Intrinsics.checkNotNullExpressionValue(substring7, "substring(...)");
                        m5381getZEROUwyO8pc = Duration.m5319plusLRDsOJo(m5319plusLRDsOJo2, toDuration(Double.parseDouble(substring7), durationUnitByShortName));
                        if (i < length) {
                            throw new IllegalArgumentException("Fractional component must be last");
                        }
                    } else {
                        m5381getZEROUwyO8pc = Duration.m5319plusLRDsOJo(m5381getZEROUwyO8pc, toDuration(Long.parseLong(substring4), durationUnitByShortName));
                    }
                    str4 = str6;
                    durationUnit2 = durationUnitByShortName;
                    z8 = true;
                }
            }
        }
        return z2 ? Duration.m5334unaryMinusUwyO8pc(m5381getZEROUwyO8pc) : m5381getZEROUwyO8pc;
    }

    private static final long parseOverLongIsoComponent(String str) {
        int length = str.length();
        int i = (length <= 0 || !StringsKt.contains$default((CharSequence) "+-", str.charAt(0), false, 2, (Object) null)) ? 0 : 1;
        if (length - i > 16) {
            Iterable intRange = new IntRange(i, StringsKt.getLastIndex(str));
            if (!(intRange instanceof Collection) || !((Collection) intRange).isEmpty()) {
                Iterator it = intRange.iterator();
                while (it.hasNext()) {
                    char charAt = str.charAt(((IntIterator) it).nextInt());
                    if ('0' <= charAt && charAt < ':') {
                    }
                }
            }
            return str.charAt(0) == '-' ? Long.MIN_VALUE : Long.MAX_VALUE;
        }
        if (StringsKt.startsWith$default(str, "+", false, 2, (Object) null)) {
            str = StringsKt.drop(str, 1);
        }
        return Long.parseLong(str);
    }

    private static final int skipWhile(String str, int i, Function1<? super Character, Boolean> function1) {
        while (i < str.length() && function1.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long nanosToMillis(long j) {
        return j / NANOS_IN_MILLIS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanos(long j) {
        return Duration.m5290constructorimpl(j << 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillis(long j) {
        return Duration.m5290constructorimpl((j << 1) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOf(long j, int i) {
        return Duration.m5290constructorimpl((j << 1) + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long j) {
        if (-4611686018426999999L <= j && j < 4611686018427000000L) {
            return durationOfNanos(j);
        }
        return durationOfMillis(nanosToMillis(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long j) {
        if (-4611686018426L <= j && j < 4611686018427L) {
            return durationOfNanos(millisToNanos(j));
        }
        return durationOfMillis(RangesKt.coerceIn(j, -4611686018427387903L, MAX_MILLIS));
    }

    private static final String substringWhile(String str, int i, Function1<? super Character, Boolean> function1) {
        int i2 = i;
        while (i2 < str.length() && function1.invoke(Character.valueOf(str.charAt(i2))).booleanValue()) {
            i2++;
        }
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String substring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }
}
