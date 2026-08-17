package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Oa {
    public final CharSequence a;
    public final boolean b;
    public final String c;

    public Oa(CharSequence title, boolean z, String str) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.a = title;
        this.b = z;
        this.c = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Oa)) {
            return false;
        }
        Oa oa = (Oa) obj;
        return Intrinsics.areEqual(this.a, oa.a) && this.b == oa.b && Intrinsics.areEqual(this.c, oa.c);
    }

    public final int hashCode() {
        int hashCode = (Boolean.hashCode(this.b) + (this.a.hashCode() * 31)) * 31;
        String str = this.c;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ToolbarTitleModel(title=");
        sb.append((Object) this.a);
        sb.append(", centered=");
        sb.append(this.b);
        sb.append(", contentDescription=");
        return N7.a(sb, this.c, ')');
    }
}
