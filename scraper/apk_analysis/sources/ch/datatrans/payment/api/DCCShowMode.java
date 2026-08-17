package ch.datatrans.payment.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lch/datatrans/payment/api/DCCShowMode;", "", "ALWAYS", "SMART", "NEVER", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class DCCShowMode {
    public static final DCCShowMode ALWAYS;
    public static final DCCShowMode NEVER;
    public static final DCCShowMode SMART;
    public static final /* synthetic */ DCCShowMode[] a;
    public static final /* synthetic */ EnumEntries b;

    static {
        DCCShowMode dCCShowMode = new DCCShowMode("ALWAYS", 0);
        ALWAYS = dCCShowMode;
        DCCShowMode dCCShowMode2 = new DCCShowMode("SMART", 1);
        SMART = dCCShowMode2;
        DCCShowMode dCCShowMode3 = new DCCShowMode("NEVER", 2);
        NEVER = dCCShowMode3;
        DCCShowMode[] dCCShowModeArr = {dCCShowMode, dCCShowMode2, dCCShowMode3};
        a = dCCShowModeArr;
        b = EnumEntriesKt.enumEntries(dCCShowModeArr);
    }

    public DCCShowMode(String str, int i) {
    }

    public static EnumEntries<DCCShowMode> getEntries() {
        return b;
    }

    public static DCCShowMode valueOf(String str) {
        return (DCCShowMode) Enum.valueOf(DCCShowMode.class, str);
    }

    public static DCCShowMode[] values() {
        return (DCCShowMode[]) a.clone();
    }
}
