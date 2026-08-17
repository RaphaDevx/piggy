package com.scandit.datacapture.core.internal.module.https.scandit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownServiceException;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0537i implements S {
    public final InterfaceC0529a a;
    public final Lazy b;

    public C0537i(C0531c connectionFactory) {
        Intrinsics.checkNotNullParameter(connectionFactory, "connectionFactory");
        this.a = connectionFactory;
        this.b = LazyKt.lazy(new C0536h(this));
    }

    public final void a() {
        try {
            ((HttpsURLConnection) this.b.getValue()).connect();
        } catch (IOException e) {
            throw new C0542n(e);
        }
    }

    public final Map b() {
        try {
            Map<String, List<String>> headerFields = ((HttpsURLConnection) this.b.getValue()).getHeaderFields();
            Intrinsics.checkNotNull(headerFields);
            return headerFields;
        } catch (IOException e) {
            throw new r(e);
        }
    }

    public final OutputStream c() {
        try {
            if (((HttpsURLConnection) this.b.getValue()).getDoOutput()) {
                return ((HttpsURLConnection) this.b.getValue()).getOutputStream();
            }
            return null;
        } catch (UnknownServiceException e) {
            throw new B(e);
        } catch (IOException e2) {
            throw new C0545q(e2);
        }
    }
}
