package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.util.Map;

/* renamed from: com.scandit.datacapture.tools.internal.module.mapmaker.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0613a implements Map.Entry {
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        M m = (M) this;
        sb.append(m.getKey());
        sb.append("=");
        sb.append(m.getValue());
        return sb.toString();
    }
}
