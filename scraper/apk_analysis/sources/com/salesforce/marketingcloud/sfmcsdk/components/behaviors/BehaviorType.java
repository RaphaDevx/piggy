package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.Behavior;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: BehaviorType.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001fB+\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\b\u0010\u001a\u001a\u00020\u0003H\u0016R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014j\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e¨\u0006 "}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorType;", "", "intentFilter", "", "sticky", "", "behaviorTypesToClear", "", "(Ljava/lang/String;ILjava/lang/String;ZLjava/util/List;)V", "getBehaviorTypesToClear$sfmcsdk_release", "()Ljava/util/List;", "setBehaviorTypesToClear$sfmcsdk_release", "(Ljava/util/List;)V", "getIntentFilter$sfmcsdk_release", "()Ljava/lang/String;", "setIntentFilter$sfmcsdk_release", "(Ljava/lang/String;)V", "getSticky$sfmcsdk_release", "()Z", "setSticky$sfmcsdk_release", "(Z)V", "toBehavior", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior;", "data", "Landroid/os/Bundle;", "toBehavior$sfmcsdk_release", "toString", "SCREEN_ENTRY", "APPLICATION_FOREGROUNDED", "APPLICATION_BACKGROUNDED", "APP_VERSION_CHANGED", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BehaviorType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ BehaviorType[] $VALUES;
    public static final BehaviorType APPLICATION_BACKGROUNDED;
    public static final BehaviorType APPLICATION_FOREGROUNDED;
    public static final BehaviorType APP_VERSION_CHANGED;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final BehaviorType SCREEN_ENTRY;
    private List<? extends BehaviorType> behaviorTypesToClear;
    private String intentFilter;
    private boolean sticky;

    private static final /* synthetic */ BehaviorType[] $values() {
        return new BehaviorType[]{SCREEN_ENTRY, APPLICATION_FOREGROUNDED, APPLICATION_BACKGROUNDED, APP_VERSION_CHANGED};
    }

    public static EnumEntries<BehaviorType> getEntries() {
        return $ENTRIES;
    }

    public static BehaviorType valueOf(String str) {
        return (BehaviorType) Enum.valueOf(BehaviorType.class, str);
    }

    public static BehaviorType[] values() {
        return (BehaviorType[]) $VALUES.clone();
    }

    private BehaviorType(String str, int i, String str2, boolean z, List list) {
        this.intentFilter = str2;
        this.sticky = z;
        this.behaviorTypesToClear = list;
    }

    /* synthetic */ BehaviorType(String str, int i, String str2, boolean z, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? null : list);
    }

    /* renamed from: getIntentFilter$sfmcsdk_release, reason: from getter */
    public final String getIntentFilter() {
        return this.intentFilter;
    }

    public final void setIntentFilter$sfmcsdk_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.intentFilter = str;
    }

    /* renamed from: getSticky$sfmcsdk_release, reason: from getter */
    public final boolean getSticky() {
        return this.sticky;
    }

    public final void setSticky$sfmcsdk_release(boolean z) {
        this.sticky = z;
    }

    public final List<BehaviorType> getBehaviorTypesToClear$sfmcsdk_release() {
        return this.behaviorTypesToClear;
    }

    public final void setBehaviorTypesToClear$sfmcsdk_release(List<? extends BehaviorType> list) {
        this.behaviorTypesToClear = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        BehaviorType behaviorType = new BehaviorType("SCREEN_ENTRY", 0, "com.salesforce.marketingcloud.sfmcsdk.sdk.SCREEN_ENTRY", true, null, 4, null);
        SCREEN_ENTRY = behaviorType;
        BehaviorType behaviorType2 = new BehaviorType("APPLICATION_FOREGROUNDED", 1, "com.salesforce.marketingcloud.sfmcsdk.sdk.APPLICATION_FOREGROUNDED", true, null, 4, null);
        APPLICATION_FOREGROUNDED = behaviorType2;
        APPLICATION_BACKGROUNDED = new BehaviorType("APPLICATION_BACKGROUNDED", 2, "com.salesforce.marketingcloud.sfmcsdk.sdk.APPLICATION_BACKGROUNDED", false, CollectionsKt.listOf((Object[]) new BehaviorType[]{behaviorType2, behaviorType}), 2, null);
        APP_VERSION_CHANGED = new BehaviorType("APP_VERSION_CHANGED", 3, "com.salesforce.marketingcloud.sfmcsdk.sdk.APP_VERSION_CHANGED", true, 0 == true ? 1 : 0, 4, null);
        BehaviorType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: BehaviorType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorType$Companion;", "", "()V", "fromString", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorType;", "intentFilter", "", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.List] */
        /* JADX WARN: Type inference failed for: r2v2, types: [T, java.util.List] */
        public final BehaviorType fromString(String intentFilter) {
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = CollectionsKt.emptyList();
            if (intentFilter != null) {
                BehaviorType[] values = BehaviorType.values();
                ArrayList arrayList = new ArrayList();
                for (BehaviorType behaviorType : values) {
                    if (Intrinsics.areEqual(intentFilter, behaviorType.getIntentFilter())) {
                        arrayList.add(behaviorType);
                    }
                }
                objectRef.element = arrayList;
            }
            if (((Collection) objectRef.element).isEmpty()) {
                return null;
            }
            return (BehaviorType) ((List) objectRef.element).get(0);
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.intentFilter;
    }

    public final Behavior toBehavior$sfmcsdk_release(Bundle data) {
        Intrinsics.checkNotNullParameter(data, "data");
        long j = data.getLong("timestamp");
        String string = data.getString(BehaviorManagerImpl.BUNDLE_KEY_CURRENT_VERSION);
        String string2 = data.getString(BehaviorManagerImpl.BUNDLE_KEY_APP_NAME);
        String str = this.intentFilter;
        switch (str.hashCode()) {
            case -2046669238:
                if (str.equals("com.salesforce.marketingcloud.sfmcsdk.sdk.APPLICATION_BACKGROUNDED")) {
                    return new Behavior.AppBackgrounded(j, string, string2);
                }
                return null;
            case -1610764001:
                if (str.equals("com.salesforce.marketingcloud.sfmcsdk.sdk.APPLICATION_FOREGROUNDED")) {
                    return new Behavior.AppForegrounded(j, string, string2);
                }
                return null;
            case 100058561:
                if (str.equals("com.salesforce.marketingcloud.sfmcsdk.sdk.APP_VERSION_CHANGED")) {
                    return new Behavior.AppVersionChanged(j, string, string2, data.getString(BehaviorManagerImpl.BUNDLE_KEY_PREVIOUS_VERSION));
                }
                return null;
            case 518948109:
                if (!str.equals("com.salesforce.marketingcloud.sfmcsdk.sdk.SCREEN_ENTRY")) {
                    return null;
                }
                String string3 = data.getString("screen_name");
                return string3 != null ? new Behavior.ScreenEntry(string3, j, string, string2) : null;
            default:
                return null;
        }
    }
}
