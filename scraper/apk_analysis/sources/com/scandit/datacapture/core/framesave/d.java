package com.scandit.datacapture.core.framesave;

import java.io.File;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function1 {
    public final /* synthetic */ File a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(File file) {
        super(1);
        this.a = file;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        String padStart;
        int intValue = ((Number) obj).intValue();
        File file = this.a;
        StringBuilder sb = new StringBuilder("sequence_");
        padStart = StringsKt.padStart(String.valueOf(intValue), 3, '0');
        sb.append(padStart);
        return FilesKt.resolve(file, new File(sb.toString()));
    }
}
