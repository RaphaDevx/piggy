package com.scandit.datacapture.core.internal.module.init;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.init.LibraryLoaderInternal;
import com.scandit.datacapture.core.internal.sdk.init.LoadMethod;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class e implements LibraryLoaderInternal {
    public final Context a;
    public final boolean b;
    public final StringBuilder c;
    public final List d;
    public final File e;
    public final File f;
    public final String g;
    public final File h;
    public boolean i;
    public final LinkedHashMap j;

    public e(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
        this.b = z;
        StringBuilder sb = new StringBuilder();
        this.c = sb;
        AppAndroidEnvironment appAndroidEnvironment = AppAndroidEnvironment.INSTANCE;
        List list = ArraysKt.toList(appAndroidEnvironment.getSupportedAbis());
        this.d = list;
        String a = f.a(context);
        File file = new File(a);
        this.e = file;
        File file2 = new File(file, "lib");
        this.f = file2;
        this.g = file2.getAbsolutePath();
        File b = f.b(context);
        this.h = b;
        String absolutePath = b.getAbsolutePath();
        this.j = new LinkedHashMap();
        a("----------------------------------------");
        a("Base data");
        a("\tarchs -> " + CollectionsKt.joinToString$default(list, null, null, null, 0, null, null, 63, null));
        a("\tinstaller info -> " + appAndroidEnvironment.getInstallerInfo(context));
        a("\tinstallPath -> " + a);
        a("\textractionLibPath -> " + absolutePath);
        a("----------------------------------------");
        a("Installation dir content:");
        StringBuilder a2 = h.a(sb, file, 0);
        a2.append('\n');
        Intrinsics.checkNotNullExpressionValue(a2, "append(...)");
        a("----------------------------------------");
    }

    public final void a(String str) {
        StringBuilder sb = this.c;
        sb.append(str);
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
    }

    @Override // com.scandit.datacapture.core.internal.sdk.init.LibraryLoader
    public final String buildLogs() {
        String sb = this.c.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.init.LibraryLoader
    public final void clearLogs() {
        StringsKt.clear(this.c);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.init.LibraryLoader
    public final synchronized Map getLoadedLibs() {
        return this.j;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.init.LibraryLoaderInternal
    public final boolean getLoadingFailed() {
        return this.i;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.init.LibraryLoader
    public final synchronized boolean getWorkaroundApplied() {
        boolean z;
        Map loadedLibs = getLoadedLibs();
        if (!loadedLibs.isEmpty()) {
            Iterator it = ((LinkedHashMap) loadedLibs).entrySet().iterator();
            while (it.hasNext()) {
                if (((Map.Entry) it.next()).getValue() != LoadMethod.BY_SO_NAME) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0334 A[Catch: all -> 0x0387, TryCatch #3 {, blocks: (B:4:0x0005, B:6:0x0014, B:8:0x0020, B:10:0x0023, B:13:0x0026, B:18:0x002e, B:22:0x0317, B:24:0x0334, B:27:0x033b, B:28:0x037f, B:29:0x0380, B:32:0x005a, B:33:0x005e, B:35:0x0064, B:40:0x00a9, B:44:0x00b4, B:46:0x0130, B:47:0x0223, B:49:0x024a, B:52:0x0256, B:66:0x029c, B:54:0x02b3, B:64:0x02b9, B:58:0x02eb, B:61:0x02f7, B:67:0x0148, B:69:0x014e, B:70:0x0164, B:72:0x0168, B:75:0x0170, B:76:0x0174, B:78:0x017a, B:80:0x01a4, B:83:0x01c5, B:85:0x01cb, B:91:0x01fa, B:93:0x0205, B:37:0x0092, B:88:0x01de, B:57:0x02ce), top: B:3:0x0005, inners: #0, #1, #2 }] */
    @Override // com.scandit.datacapture.core.internal.sdk.init.LibraryLoaderInternal
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized boolean loadLibsIfNeeded(java.lang.String... r17) {
        /*
            Method dump skipped, instructions count: 906
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.init.e.loadLibsIfNeeded(java.lang.String[]):boolean");
    }
}
