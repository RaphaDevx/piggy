package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.io.Serializable;

/* renamed from: com.scandit.datacapture.tools.internal.module.mapmaker.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0615c extends AbstractC0617e implements Serializable {
    public static final C0615c a = new C0615c();

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.AbstractC0617e
    public final boolean a(Object obj, Object obj2) {
        return obj.equals(obj2);
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.AbstractC0617e
    public final int a(Object obj) {
        return obj.hashCode();
    }
}
