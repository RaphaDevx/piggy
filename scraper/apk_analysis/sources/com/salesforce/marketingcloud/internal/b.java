package com.salesforce.marketingcloud.internal;

import java.util.zip.Inflater;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b {
    public static final <R> R a(Inflater inflater, Function1<? super Inflater, ? extends R> block) {
        Intrinsics.checkNotNullParameter(inflater, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            return block.invoke(inflater);
        } finally {
            InlineMarker.finallyStart(1);
            inflater.end();
            InlineMarker.finallyEnd(1);
        }
    }
}
