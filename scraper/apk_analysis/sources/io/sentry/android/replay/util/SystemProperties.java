package io.sentry.android.replay.util;

import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemProperties.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lio/sentry/android/replay/util/SystemProperties;", "", "()V", "get", "", "key", "Lio/sentry/android/replay/util/SystemProperties$Property;", "defaultValue", "Property", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SystemProperties {
    public static final int $stable = 0;
    public static final SystemProperties INSTANCE = new SystemProperties();

    /* compiled from: SystemProperties.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Property.values().length];
            try {
                iArr[Property.SOC_MODEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Property.SOC_MANUFACTURER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: SystemProperties.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lio/sentry/android/replay/util/SystemProperties$Property;", "", "(Ljava/lang/String;I)V", "SOC_MODEL", "SOC_MANUFACTURER", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Property {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Property[] $VALUES;
        public static final Property SOC_MODEL = new Property("SOC_MODEL", 0);
        public static final Property SOC_MANUFACTURER = new Property("SOC_MANUFACTURER", 1);

        private static final /* synthetic */ Property[] $values() {
            return new Property[]{SOC_MODEL, SOC_MANUFACTURER};
        }

        public static EnumEntries<Property> getEntries() {
            return $ENTRIES;
        }

        public static Property valueOf(String str) {
            return (Property) Enum.valueOf(Property.class, str);
        }

        public static Property[] values() {
            return (Property[]) $VALUES.clone();
        }

        private Property(String str, int i) {
        }

        static {
            Property[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    private SystemProperties() {
    }

    public static /* synthetic */ String get$default(SystemProperties systemProperties, Property property, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return systemProperties.get(property, str);
    }

    public final String get(Property key, String defaultValue) {
        String str;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (Build.VERSION.SDK_INT < 31) {
            return defaultValue;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[key.ordinal()];
        if (i == 1) {
            str = Build.SOC_MODEL;
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            str = Build.SOC_MANUFACTURER;
        }
        String str2 = str;
        Intrinsics.checkNotNull(str2);
        return str2;
    }
}
