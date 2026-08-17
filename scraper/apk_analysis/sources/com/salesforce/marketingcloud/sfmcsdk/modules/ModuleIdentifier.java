package com.salesforce.marketingcloud.sfmcsdk.modules;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Config.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleIdentifier;", "", "(Ljava/lang/String;I)V", "PUSH", "CDP", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ModuleIdentifier {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ModuleIdentifier[] $VALUES;
    public static final ModuleIdentifier PUSH = new ModuleIdentifier("PUSH", 0);
    public static final ModuleIdentifier CDP = new ModuleIdentifier("CDP", 1);

    private static final /* synthetic */ ModuleIdentifier[] $values() {
        return new ModuleIdentifier[]{PUSH, CDP};
    }

    public static EnumEntries<ModuleIdentifier> getEntries() {
        return $ENTRIES;
    }

    public static ModuleIdentifier valueOf(String str) {
        return (ModuleIdentifier) Enum.valueOf(ModuleIdentifier.class, str);
    }

    public static ModuleIdentifier[] values() {
        return (ModuleIdentifier[]) $VALUES.clone();
    }

    private ModuleIdentifier(String str, int i) {
    }

    static {
        ModuleIdentifier[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
