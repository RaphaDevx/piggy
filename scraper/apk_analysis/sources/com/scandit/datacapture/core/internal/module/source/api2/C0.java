package com.scandit.datacapture.core.internal.module.source.api2;

import android.media.Image;
import android.media.ImageReader;
import android.util.Log;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class C0 implements ImageReader.OnImageAvailableListener {
    public final InterfaceC0579w a;
    public int b;
    public boolean c;

    public C0(InterfaceC0579w callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.a = callback;
        this.c = true;
    }

    @Override // android.media.ImageReader.OnImageAvailableListener
    public final void onImageAvailable(ImageReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        try {
            Image acquireLatestImage = reader.acquireLatestImage();
            if (acquireLatestImage == null) {
                return;
            }
            if (!this.c) {
                acquireLatestImage.close();
                return;
            }
            try {
                ((C0570m) this.a).a(acquireLatestImage);
                AutoCloseableKt.closeFinally(acquireLatestImage, null);
            } finally {
            }
        } catch (IllegalStateException unused) {
            if (this.b == 0) {
                Intrinsics.checkNotNullParameter("No buffer available for next image.", "message");
                Log.i("sdc-core", "No buffer available for next image.");
            }
            this.b = (this.b + 1) % 30;
        }
    }
}
