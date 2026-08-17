package com.scandit.datacapture.core.internal.module.gl;

import com.scandit.datacapture.core.internal.module.ui.video.NativeCopiedCameraTexture;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class m extends Lambda implements Function0 {
    public static final m a = new m();

    public m() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NativeCopiedCameraTexture create = NativeCopiedCameraTexture.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }
}
