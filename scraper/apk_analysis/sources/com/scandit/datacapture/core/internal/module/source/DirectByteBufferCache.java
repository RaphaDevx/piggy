package com.scandit.datacapture.core.internal.module.source;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class DirectByteBufferCache {
    private final WeakHashMap a = new WeakHashMap();

    public final ByteBuffer a(int i) {
        List list = (List) this.a.get(Integer.valueOf(i));
        ByteBuffer byteBuffer = list != null ? (ByteBuffer) CollectionsKt.removeLastOrNull(list) : null;
        if (byteBuffer != null) {
            return byteBuffer;
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i);
        Intrinsics.checkNotNullExpressionValue(allocateDirect, "allocateDirect(...)");
        return allocateDirect;
    }

    public final void a(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (buffer.isDirect()) {
            WeakHashMap weakHashMap = this.a;
            Integer valueOf = Integer.valueOf(buffer.capacity());
            Object obj = weakHashMap.get(valueOf);
            if (obj == null) {
                obj = new ArrayList();
                weakHashMap.put(valueOf, obj);
            }
            ((List) obj).add(buffer);
        }
    }
}
