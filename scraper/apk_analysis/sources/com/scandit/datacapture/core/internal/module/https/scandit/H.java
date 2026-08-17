package com.scandit.datacapture.core.internal.module.https.scandit;

import com.scandit.datacapture.core.internal.module.https.NativeHttpsResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownServiceException;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class H extends Lambda implements Function0 {
    public final /* synthetic */ I a;
    public final /* synthetic */ HashMap b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(I i, HashMap hashMap) {
        super(0);
        this.a = i;
        this.b = hashMap;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        InputStream errorStream;
        I i = this.a;
        i.g = b0.b;
        i.b();
        C0537i c0537i = (C0537i) this.a.c;
        c0537i.getClass();
        try {
            int responseCode = ((HttpsURLConnection) c0537i.b.getValue()).getResponseCode();
            try {
                if (200 > responseCode || responseCode >= 300) {
                    I i2 = this.a;
                    i2.g = c0.b;
                    i2.b();
                    C0537i c0537i2 = (C0537i) this.a.c;
                    c0537i2.getClass();
                    try {
                        errorStream = ((HttpsURLConnection) c0537i2.b.getValue()).getErrorStream();
                        Intrinsics.checkNotNull(errorStream);
                    } catch (UnknownServiceException e) {
                        throw new A(e);
                    } catch (IOException e2) {
                        throw new C0544p(e2);
                    }
                } else {
                    I i3 = this.a;
                    i3.g = d0.b;
                    i3.b();
                    C0537i c0537i3 = (C0537i) this.a.c;
                    c0537i3.getClass();
                    try {
                        errorStream = ((HttpsURLConnection) c0537i3.b.getValue()).getInputStream();
                        Intrinsics.checkNotNull(errorStream);
                    } catch (UnknownServiceException e3) {
                        throw new A(e3);
                    } catch (IOException e4) {
                        throw new C0544p(e4);
                    }
                }
                try {
                    byte[] readBytes = ByteStreamsKt.readBytes(errorStream);
                    CloseableKt.closeFinally(errorStream, null);
                    NativeHttpsResponse nativeHttpsResponse = new NativeHttpsResponse(responseCode, this.b, readBytes);
                    I i4 = this.a;
                    i4.g = new i0(nativeHttpsResponse);
                    i4.b();
                } finally {
                }
            } catch (Throwable th) {
                this.a.a(th);
            }
            return Unit.INSTANCE;
        } catch (IOException e5) {
            throw new C0546s(e5);
        }
    }
}
