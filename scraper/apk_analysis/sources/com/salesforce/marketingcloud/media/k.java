package com.salesforce.marketingcloud.media;

/* loaded from: classes2.dex */
public class k extends IllegalStateException {
    final t b;

    k(t tVar) {
        super("Cannot handle request: " + tVar);
        this.b = tVar;
    }
}
