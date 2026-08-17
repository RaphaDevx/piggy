package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BehaviorType.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00122\u00020\u0001:\u0005\u000f\u0010\u0011\u0012\u0013B/\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0082\u0001\u0004\u0014\u0015\u0016\u0017¨\u0006\u0018"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior;", "", "timestamp", "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_VERSION, "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_NAME, "previousVersion", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAppName", "()Ljava/lang/String;", "getAppVersion", "getPreviousVersion", "getTimestamp", "()J", "AppBackgrounded", "AppForegrounded", "AppVersionChanged", "Companion", "ScreenEntry", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior$AppBackgrounded;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior$AppForegrounded;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior$AppVersionChanged;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior$ScreenEntry;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class Behavior {
    public static final String KEY_TIME = "timestamp";
    private final String appName;
    private final String appVersion;
    private final String previousVersion;
    private final long timestamp;

    public /* synthetic */ Behavior(long j, String str, String str2, String str3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, str3);
    }

    private Behavior(long j, String str, String str2, String str3) {
        this.timestamp = j;
        this.appVersion = str;
        this.appName = str2;
        this.previousVersion = str3;
    }

    public /* synthetic */ Behavior(long j, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, (i & 8) != 0 ? null : str3, null);
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getPreviousVersion() {
        return this.previousVersion;
    }

    /* compiled from: BehaviorType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior$AppForegrounded;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior;", "timestamp", "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_VERSION, "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_NAME, "(JLjava/lang/String;Ljava/lang/String;)V", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AppForegrounded extends Behavior {
        public AppForegrounded(long j, String str, String str2) {
            super(j, str, str2, null, 8, null);
        }
    }

    /* compiled from: BehaviorType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior$AppBackgrounded;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior;", "timestamp", "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_VERSION, "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_NAME, "(JLjava/lang/String;Ljava/lang/String;)V", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AppBackgrounded extends Behavior {
        public AppBackgrounded(long j, String str, String str2) {
            super(j, str, str2, null, 8, null);
        }
    }

    /* compiled from: BehaviorType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior$AppVersionChanged;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior;", "timestamp", "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_VERSION, "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_NAME, "previousVersion", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AppVersionChanged extends Behavior {
        public AppVersionChanged(long j, String str, String str2, String str3) {
            super(j, str, str2, str3, null);
        }
    }

    /* compiled from: BehaviorType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB+\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior$ScreenEntry;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior;", "name", "", "timestamp", "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_VERSION, LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_NAME, "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class ScreenEntry extends Behavior {
        public static final String KEY_NAME = "screen_name";
        private final String name;

        public final String getName() {
            return this.name;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ScreenEntry(String name, long j, String str, String str2) {
            super(j, str, str2, null, 8, null);
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
        }
    }
}
