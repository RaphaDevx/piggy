package com.scandit.datacapture.frameworks.core.utils;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultFrameworksLog.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/DefaultFrameworksLog;", "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "()V", "error", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "info", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultFrameworksLog implements FrameworksLog {
    private static final String TAG = "sdc-frameworks";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DefaultFrameworksLog instance = new DefaultFrameworksLog();

    @JvmStatic
    public static final DefaultFrameworksLog getInstance() {
        return INSTANCE.getInstance();
    }

    private DefaultFrameworksLog() {
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.FrameworksLog
    public void error(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.e(TAG, message);
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.FrameworksLog
    public void info(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.i(TAG, message);
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.FrameworksLog
    public void error(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e(TAG, e.getMessage(), e);
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.FrameworksLog
    public void error(String message, Exception e) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e(TAG, message, e);
    }

    /* compiled from: DefaultFrameworksLog.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/DefaultFrameworksLog$Companion;", "", "()V", "TAG", "", "instance", "Lcom/scandit/datacapture/frameworks/core/utils/DefaultFrameworksLog;", "getInstance", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DefaultFrameworksLog getInstance() {
            return DefaultFrameworksLog.instance;
        }
    }
}
