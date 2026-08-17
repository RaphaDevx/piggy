package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData;
import java.util.LinkedHashMap;
import java.util.Stack;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class K {
    public final int a;
    public final Stack b;
    public final LinkedHashMap c;

    public K(int i) {
        this.a = i;
        Stack stack = new Stack();
        this.b = stack;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.c = linkedHashMap;
        stack.clear();
        linkedHashMap.clear();
        for (int i2 = 0; i2 < i; i2++) {
            NativeCameraFrameData createEmpty = NativeCameraFrameData.createEmpty();
            this.b.push(createEmpty);
            LinkedHashMap linkedHashMap2 = this.c;
            Intrinsics.checkNotNull(createEmpty);
            NativeFrameData asFrameData = createEmpty.asFrameData();
            Intrinsics.checkNotNullExpressionValue(asFrameData, "asFrameData(...)");
            linkedHashMap2.put(createEmpty, asFrameData);
        }
    }
}
