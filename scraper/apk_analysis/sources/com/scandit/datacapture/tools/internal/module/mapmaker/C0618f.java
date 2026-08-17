package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.util.Arrays;

/* renamed from: com.scandit.datacapture.tools.internal.module.mapmaker.f, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0618f {
    public u a;
    public u b;

    public final String toString() {
        O o;
        O o2 = new O();
        u uVar = this.a;
        if (uVar != null) {
            String a = AbstractC0614b.a(uVar.toString());
            o = new O();
            o2.c = o;
            o.b = a;
            o.a = "keyStrength";
        } else {
            o = o2;
        }
        u uVar2 = this.b;
        if (uVar2 != null) {
            String a2 = AbstractC0614b.a(uVar2.toString());
            O o3 = new O();
            o.c = o3;
            o3.b = a2;
            o3.a = "valueStrength";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append("f{");
        O o4 = o2.c;
        String str = "";
        while (o4 != null) {
            Object obj = o4.b;
            sb.append(str);
            String str2 = o4.a;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            }
            o4 = o4.c;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }
}
