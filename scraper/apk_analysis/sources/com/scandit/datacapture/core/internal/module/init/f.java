package com.scandit.datacapture.core.internal.module.init;

import android.content.Context;
import java.io.File;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public abstract class f {
    public static final String a(Context context) {
        String sourceDir = context.getApplicationInfo().sourceDir;
        Intrinsics.checkNotNullExpressionValue(sourceDir, "sourceDir");
        List mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) sourceDir, new String[]{"/"}, false, 0, 6, (Object) null));
        mutableList.remove(CollectionsKt.getLastIndex(mutableList));
        return CollectionsKt.joinToString$default(mutableList, "/", null, null, 0, null, null, 62, null);
    }

    public static final File b(Context context) {
        File codeCacheDir = context.getCodeCacheDir();
        Intrinsics.checkNotNullExpressionValue(codeCacheDir, "getCodeCacheDir(...)");
        return codeCacheDir;
    }
}
