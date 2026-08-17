package com.salesforce.marketingcloud.sfmcsdk;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: SFMCSdk.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/InitializationState;", "", "(Ljava/lang/String;I)V", "NONE", "INITIALIZING", "READY", "ERROR", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class InitializationState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ InitializationState[] $VALUES;
    public static final InitializationState NONE = new InitializationState("NONE", 0);
    public static final InitializationState INITIALIZING = new InitializationState("INITIALIZING", 1);
    public static final InitializationState READY = new InitializationState("READY", 2);
    public static final InitializationState ERROR = new InitializationState("ERROR", 3);

    private static final /* synthetic */ InitializationState[] $values() {
        return new InitializationState[]{NONE, INITIALIZING, READY, ERROR};
    }

    public static EnumEntries<InitializationState> getEntries() {
        return $ENTRIES;
    }

    public static InitializationState valueOf(String str) {
        return (InitializationState) Enum.valueOf(InitializationState.class, str);
    }

    public static InitializationState[] values() {
        return (InitializationState[]) $VALUES.clone();
    }

    private InitializationState(String str, int i) {
    }

    static {
        InitializationState[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
