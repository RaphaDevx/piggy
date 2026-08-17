package com.salesforce.marketingcloud.sfmcsdk.components.logging;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u000bJ*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH&¨\u0006\f"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogListener;", "", "out", "", "level", "Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogLevel;", "tag", "", "message", "throwable", "", "AndroidLogger", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface LogListener {
    void out(LogLevel level, String tag, String message, Throwable throwable);

    /* compiled from: Logger.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogListener$AndroidLogger;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogListener;", "()V", "out", "", "level", "Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogLevel;", "tag", "", "message", "throwable", "", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static class AndroidLogger implements LogListener {

        /* compiled from: Logger.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[LogLevel.values().length];
                try {
                    iArr[LogLevel.DEBUG.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[LogLevel.WARN.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[LogLevel.ERROR.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[LogLevel.NONE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.salesforce.marketingcloud.sfmcsdk.components.logging.LogListener
        public void out(LogLevel level, String tag, String message, Throwable throwable) {
            Intrinsics.checkNotNullParameter(level, "level");
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(message, "message");
            int i = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
            if (i == 1) {
                if (throwable == null) {
                    Log.d(tag, message);
                    return;
                } else {
                    Log.d(tag, message, throwable);
                    return;
                }
            }
            if (i == 2) {
                if (throwable == null) {
                    Log.w(tag, message);
                    return;
                } else {
                    Log.w(tag, message, throwable);
                    return;
                }
            }
            if (i != 3) {
                return;
            }
            if (throwable == null) {
                Log.e(tag, message);
            } else {
                Log.e(tag, message, throwable);
            }
        }
    }
}
