package com.scandit.datacapture.core.internal.sdk.license;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function1 {
    public static final a a = new a();

    public a() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ModuleLicenseTextProvider it = (ModuleLicenseTextProvider) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getLicenseText();
    }
}
