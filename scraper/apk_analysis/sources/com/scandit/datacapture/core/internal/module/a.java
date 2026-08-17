package com.scandit.datacapture.core.internal.module;

import android.content.Context;
import com.scandit.internal.sdk.bar.FilesystemInstance;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends FilesystemInstance {
    public final Context a;

    public a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.a = applicationContext;
    }

    @Override // com.scandit.internal.sdk.bar.FilesystemInstance
    public final String getTemporaryDirectory() {
        String absolutePath = this.a.getCacheDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }
}
