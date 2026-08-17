package com.salesforce.marketingcloud.push;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class f extends Exception {
    private final a b;
    private final String c;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class a {
        public static final a b = new a("UNKNOWN_ERROR", 0);
        public static final a c = new a("INVALID_JSON", 1);
        public static final a d = new a("INVALID_COMPRESSION", 2);
        public static final a e = new a("MISSING_FIELD", 3);
        public static final a f = new a("UNSUPPORTED_TYPE", 4);
        public static final a g = new a("BAD_MEDIA", 5);
        private static final /* synthetic */ a[] h;
        private static final /* synthetic */ EnumEntries i;

        static {
            a[] a = a();
            h = a;
            i = EnumEntriesKt.enumEntries(a);
        }

        private a(String str, int i2) {
        }

        private static final /* synthetic */ a[] a() {
            return new a[]{b, c, d, e, f, g};
        }

        public static EnumEntries<a> b() {
            return i;
        }

        public static a valueOf(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] values() {
            return (a[]) h.clone();
        }
    }

    public /* synthetic */ f(a aVar, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(aVar, (i & 2) != 0 ? null : str);
    }

    public final a a() {
        return this.b;
    }

    public final JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("errorCode", this.b);
        String message = getMessage();
        if (message != null) {
            jSONObject.put("message", message);
        }
        return jSONObject;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.c;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(a errorCode, String str) {
        super(str);
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        this.b = errorCode;
        this.c = str;
    }
}
