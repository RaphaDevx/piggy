package com.salesforce.marketingcloud.http;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e implements Parcelable {
    private final int b;
    private final String c;
    private final String d;
    private final long e;
    private final long f;
    private final Map<String, List<String>> g;
    public static final b h = new b(null);
    public static final Parcelable.Creator<e> CREATOR = new c();

    public static final class a {
        private int a;
        private String b;
        private String c;
        private long d;
        private long e;
        private Map<String, ? extends List<String>> f;

        public final a a(int i) {
            this.a = i;
            return this;
        }

        public final a b(String str) {
            this.c = str;
            return this;
        }

        public final a a(String str) {
            this.b = str;
            return this;
        }

        public final a b(long j) {
            this.d = j;
            return this;
        }

        public final a a(long j) {
            this.e = j;
            return this;
        }

        public final a a(Map<String, ? extends List<String>> map) {
            this.f = map;
            return this;
        }

        public final e a() {
            int i = this.a;
            String str = this.b;
            String str2 = this.c;
            long j = this.d;
            long j2 = this.e;
            Map<String, ? extends List<String>> map = this.f;
            if (map == null) {
                map = MapsKt.emptyMap();
            }
            return new e(i, str, str2, j, j2, map);
        }
    }

    public static final class b {
        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final a a() {
            return new a();
        }

        private b() {
        }

        @JvmStatic
        public final e a(String message, int i) {
            Intrinsics.checkNotNullParameter(message, "message");
            long currentTimeMillis = System.currentTimeMillis();
            return a().a(i).b(message).b(currentTimeMillis).a(currentTimeMillis).a();
        }
    }

    public static final class c implements Parcelable.Creator<e> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final e createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            int readInt = parcel.readInt();
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            long readLong = parcel.readLong();
            long readLong2 = parcel.readLong();
            int readInt2 = parcel.readInt();
            LinkedHashMap linkedHashMap = new LinkedHashMap(readInt2);
            for (int i = 0; i != readInt2; i++) {
                linkedHashMap.put(parcel.readString(), parcel.createStringArrayList());
            }
            return new e(readInt, readString, readString2, readLong, readLong2, linkedHashMap);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final e[] newArray(int i) {
            return new e[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e(int i, String str, String str2, long j, long j2, Map<String, ? extends List<String>> headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.b = i;
        this.c = str;
        this.d = str2;
        this.e = j;
        this.f = j2;
        this.g = headers;
    }

    @JvmStatic
    public static final e a(String str, int i) {
        return h.a(str, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final String i() {
        return this.c;
    }

    public final int j() {
        return this.b;
    }

    public final long k() {
        return this.f;
    }

    public final Map<String, List<String>> l() {
        return this.g;
    }

    public final String m() {
        return this.d;
    }

    public final long n() {
        return this.e;
    }

    public final boolean o() {
        int i = this.b;
        return 200 <= i && i < 300;
    }

    public final long p() {
        return this.f - this.e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeInt(this.b);
        out.writeString(this.c);
        out.writeString(this.d);
        out.writeLong(this.e);
        out.writeLong(this.f);
        Map<String, List<String>> map = this.g;
        out.writeInt(map.size());
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            out.writeString(entry.getKey());
            out.writeStringList(entry.getValue());
        }
    }
}
