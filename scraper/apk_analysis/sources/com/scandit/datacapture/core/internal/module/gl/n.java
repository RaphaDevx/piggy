package com.scandit.datacapture.core.internal.module.gl;

import com.scandit.datacapture.core.internal.module.ui.video.NativeCopiedCameraTexture;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class n {
    public final Function0 a;
    public final ArrayList b;
    public final ArrayList c;
    public NativeCopiedCameraTexture d;
    public NativeCopiedCameraTexture e;

    public n() {
        m textureProducer = m.a;
        Intrinsics.checkNotNullParameter(textureProducer, "textureProducer");
        this.a = textureProducer;
        this.b = new ArrayList();
        this.c = new ArrayList();
    }
}
