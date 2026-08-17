package com.scandit.datacapture.core.internal.module.gl;

import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeTextureBinding;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j {
    public final ArrayList a;
    public final NativeTextureBinding b;
    public final Size2 c;
    public final boolean d;

    public j(ArrayList textureTransformation, NativeTextureBinding sourceFrame, Size2 frameSize, boolean z) {
        Intrinsics.checkNotNullParameter(textureTransformation, "textureTransformation");
        Intrinsics.checkNotNullParameter(sourceFrame, "sourceFrame");
        Intrinsics.checkNotNullParameter(frameSize, "frameSize");
        this.a = textureTransformation;
        this.b = sourceFrame;
        this.c = frameSize;
        this.d = z;
    }
}
