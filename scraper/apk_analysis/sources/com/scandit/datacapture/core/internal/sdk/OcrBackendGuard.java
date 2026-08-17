package com.scandit.datacapture.core.internal.sdk;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/OcrBackendGuard;", "", "Lcom/scandit/datacapture/core/internal/sdk/OcrBackend;", "backend", "", "setBackend", "(Lcom/scandit/datacapture/core/internal/sdk/OcrBackend;)V", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class OcrBackendGuard {
    public static final OcrBackendGuard INSTANCE = new OcrBackendGuard();
    private static WeakReference a = new WeakReference(null);

    private OcrBackendGuard() {
    }

    public final void setBackend(OcrBackend backend) {
        Intrinsics.checkNotNullParameter(backend, "backend");
        OcrBackend ocrBackend = (OcrBackend) a.get();
        if (ocrBackend != null) {
            ocrBackend.deinitialize();
        }
        a = new WeakReference(backend);
    }
}
