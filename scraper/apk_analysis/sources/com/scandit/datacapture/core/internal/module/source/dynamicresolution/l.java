package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import android.content.Context;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettingsDefaults;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelperImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class l {
    public static final l a = new l();
    public static final MainThreadHelperImpl b = new MainThreadHelperImpl();
    public static final boolean c = NativeCameraSettingsDefaults.dynamicResolutionLogging();

    public static void a(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (c) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Dynamic resolution - " + message);
        }
    }

    public static void b(String message) {
        Context context = AppAndroidEnvironment.INSTANCE.getApplicationContext();
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(context, "context");
        if (c) {
            b.runOnMainThread(new k(context, message));
        }
        a(message);
    }
}
