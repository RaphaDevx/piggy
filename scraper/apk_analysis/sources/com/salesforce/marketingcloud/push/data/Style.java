package com.salesforce.marketingcloud.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Spanned;
import androidx.core.view.GravityCompat;
import androidx.exifinterface.media.ExifInterface;
import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.push.g;
import java.util.Locale;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public interface Style extends Parcelable {
    public static final a a = a.a;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Alignment {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Alignment[] $VALUES;
        public static final a Companion;
        public static final Alignment B = new Alignment("B", 0);
        public static final Alignment C = new Alignment("C", 1);
        public static final Alignment E = new Alignment(ExifInterface.LONGITUDE_EAST, 2);

        public static final class a {
            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Alignment a(String str) {
                String str2;
                if (str != null) {
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                    str2 = str.toUpperCase(locale);
                    Intrinsics.checkNotNullExpressionValue(str2, "toUpperCase(...)");
                } else {
                    str2 = null;
                }
                if (str2 == null) {
                    return null;
                }
                int hashCode = str2.hashCode();
                if (hashCode == 66) {
                    if (str2.equals("B")) {
                        return Alignment.B;
                    }
                    return null;
                }
                if (hashCode == 67) {
                    if (str2.equals("C")) {
                        return Alignment.C;
                    }
                    return null;
                }
                if (hashCode == 69 && str2.equals(ExifInterface.LONGITUDE_EAST)) {
                    return Alignment.E;
                }
                return null;
            }

            private a() {
            }
        }

        public /* synthetic */ class b {
            public static final /* synthetic */ int[] a;

            static {
                int[] iArr = new int[Alignment.values().length];
                try {
                    iArr[Alignment.B.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Alignment.C.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Alignment.E.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                a = iArr;
            }
        }

        private static final /* synthetic */ Alignment[] $values() {
            return new Alignment[]{B, C, E};
        }

        static {
            Alignment[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
            Companion = new a(null);
        }

        private Alignment(String str, int i) {
        }

        public static EnumEntries<Alignment> getEntries() {
            return $ENTRIES;
        }

        public static Alignment valueOf(String str) {
            return (Alignment) Enum.valueOf(Alignment.class, str);
        }

        public static Alignment[] values() {
            return (Alignment[]) $VALUES.clone();
        }

        public final int toGravity() {
            int i = b.a[ordinal()];
            if (i == 1) {
                return GravityCompat.START;
            }
            if (i == 2) {
                return 17;
            }
            if (i == 3) {
                return GravityCompat.END;
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class FontStyle {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ FontStyle[] $VALUES;
        public static final a Companion;
        public static final FontStyle R = new FontStyle("R", 0);
        public static final FontStyle B = new FontStyle("B", 1);
        public static final FontStyle I = new FontStyle("I", 2);

        public static final class a {
            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final FontStyle a(String str) {
                String str2;
                if (str != null) {
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                    str2 = str.toUpperCase(locale);
                    Intrinsics.checkNotNullExpressionValue(str2, "toUpperCase(...)");
                } else {
                    str2 = null;
                }
                if (str2 == null) {
                    return null;
                }
                int hashCode = str2.hashCode();
                if (hashCode == 66) {
                    if (str2.equals("B")) {
                        return FontStyle.B;
                    }
                    return null;
                }
                if (hashCode == 73) {
                    if (str2.equals("I")) {
                        return FontStyle.I;
                    }
                    return null;
                }
                if (hashCode == 82 && str2.equals("R")) {
                    return FontStyle.R;
                }
                return null;
            }

            private a() {
            }
        }

        private static final /* synthetic */ FontStyle[] $values() {
            return new FontStyle[]{R, B, I};
        }

        static {
            FontStyle[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
            Companion = new a(null);
        }

        private FontStyle(String str, int i) {
        }

        public static EnumEntries<FontStyle> getEntries() {
            return $ENTRIES;
        }

        public static FontStyle valueOf(String str) {
            return (FontStyle) Enum.valueOf(FontStyle.class, str);
        }

        public static FontStyle[] values() {
            return (FontStyle[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Size {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Size[] $VALUES;
        public static final a Companion;
        public static final Size S = new Size(ExifInterface.LATITUDE_SOUTH, 0);
        public static final Size M = new Size("M", 1);
        public static final Size L = new Size("L", 2);

        public static final class a {
            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Size a(String size) {
                Intrinsics.checkNotNullParameter(size, "size");
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String upperCase = size.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                int hashCode = upperCase.hashCode();
                if (hashCode != 76) {
                    if (hashCode != 77) {
                        if (hashCode == 83 && upperCase.equals(ExifInterface.LATITUDE_SOUTH)) {
                            return Size.S;
                        }
                    } else if (upperCase.equals("M")) {
                        return Size.M;
                    }
                } else if (upperCase.equals("L")) {
                    return Size.L;
                }
                return null;
            }

            private a() {
            }
        }

        public /* synthetic */ class b {
            public static final /* synthetic */ int[] a;

            static {
                int[] iArr = new int[Size.values().length];
                try {
                    iArr[Size.S.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Size.M.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Size.L.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                a = iArr;
            }
        }

        private static final /* synthetic */ Size[] $values() {
            return new Size[]{S, M, L};
        }

        static {
            Size[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
            Companion = new a(null);
        }

        private Size(String str, int i) {
        }

        public static EnumEntries<Size> getEntries() {
            return $ENTRIES;
        }

        public static Size valueOf(String str) {
            return (Size) Enum.valueOf(Size.class, str);
        }

        public static Size[] values() {
            return (Size[]) $VALUES.clone();
        }

        public final float toSP() {
            int i = b.a[ordinal()];
            if (i == 1) {
                return 10.0f;
            }
            if (i == 2) {
                return 12.0f;
            }
            if (i == 3) {
                return 14.0f;
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public static final class b implements Style {
        public static final Parcelable.Creator<b> CREATOR = new a();
        private final String b;
        private final String c;
        private final Size d;
        private final Alignment e;
        private final FontStyle f;
        private Spanned g;

        public static final class a implements Parcelable.Creator<b> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new b(parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Size.valueOf(parcel.readString()), parcel.readInt() == 0 ? null : Alignment.valueOf(parcel.readString()), parcel.readInt() == 0 ? null : FontStyle.valueOf(parcel.readString()), null, 32, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b[] newArray(int i) {
                return new b[i];
            }
        }

        public b() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ void p() {
        }

        public final b a(String str, String str2, Size size, Alignment alignment, FontStyle fontStyle, Spanned spanned) {
            return new b(str, str2, size, alignment, fontStyle, spanned);
        }

        @Override // com.salesforce.marketingcloud.push.data.Style
        public FontStyle b() {
            return this.f;
        }

        @Override // com.salesforce.marketingcloud.push.data.Style
        public Size c() {
            return this.d;
        }

        @Override // com.salesforce.marketingcloud.push.data.Style
        public Alignment e() {
            return this.e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c) && this.d == bVar.d && this.e == bVar.e && this.f == bVar.f && Intrinsics.areEqual(this.g, bVar.g);
        }

        @Override // com.salesforce.marketingcloud.push.data.Style
        public String g() {
            return this.b;
        }

        @Override // com.salesforce.marketingcloud.push.data.Style
        public String h() {
            return this.c;
        }

        public int hashCode() {
            String str = this.b;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.c;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            Size size = this.d;
            int hashCode3 = (hashCode2 + (size == null ? 0 : size.hashCode())) * 31;
            Alignment alignment = this.e;
            int hashCode4 = (hashCode3 + (alignment == null ? 0 : alignment.hashCode())) * 31;
            FontStyle fontStyle = this.f;
            int hashCode5 = (hashCode4 + (fontStyle == null ? 0 : fontStyle.hashCode())) * 31;
            Spanned spanned = this.g;
            return hashCode5 + (spanned != null ? spanned.hashCode() : 0);
        }

        public final String i() {
            return this.b;
        }

        public final String j() {
            return this.c;
        }

        public final Size k() {
            return this.d;
        }

        public final Alignment l() {
            return this.e;
        }

        public final FontStyle m() {
            return this.f;
        }

        public final Spanned n() {
            return this.g;
        }

        public final Spanned o() {
            return this.g;
        }

        public String toString() {
            return "StyleImpl(fontColor=" + this.b + ", backgroundColor=" + this.c + ", fontSize=" + this.d + ", alignment=" + this.e + ", fontStyle=" + this.f + ", span=" + ((Object) this.g) + ")";
        }

        @Override // com.salesforce.marketingcloud.push.data.Style, android.os.Parcelable
        public void writeToParcel(Parcel out, int i) {
            Intrinsics.checkNotNullParameter(out, "out");
            out.writeString(this.b);
            out.writeString(this.c);
            Size size = this.d;
            if (size == null) {
                out.writeInt(0);
            } else {
                out.writeInt(1);
                out.writeString(size.name());
            }
            Alignment alignment = this.e;
            if (alignment == null) {
                out.writeInt(0);
            } else {
                out.writeInt(1);
                out.writeString(alignment.name());
            }
            FontStyle fontStyle = this.f;
            if (fontStyle == null) {
                out.writeInt(0);
            } else {
                out.writeInt(1);
                out.writeString(fontStyle.name());
            }
        }

        public b(String str, String str2, Size size, Alignment alignment, FontStyle fontStyle, Spanned spanned) {
            this.b = str;
            this.c = str2;
            this.d = size;
            this.e = alignment;
            this.f = fontStyle;
            this.g = spanned;
        }

        public static /* synthetic */ b a(b bVar, String str, String str2, Size size, Alignment alignment, FontStyle fontStyle, Spanned spanned, int i, Object obj) {
            if ((i & 1) != 0) {
                str = bVar.b;
            }
            if ((i & 2) != 0) {
                str2 = bVar.c;
            }
            String str3 = str2;
            if ((i & 4) != 0) {
                size = bVar.d;
            }
            Size size2 = size;
            if ((i & 8) != 0) {
                alignment = bVar.e;
            }
            Alignment alignment2 = alignment;
            if ((i & 16) != 0) {
                fontStyle = bVar.f;
            }
            FontStyle fontStyle2 = fontStyle;
            if ((i & 32) != 0) {
                spanned = bVar.g;
            }
            return bVar.a(str, str3, size2, alignment2, fontStyle2, spanned);
        }

        public final void a(Spanned spanned) {
            this.g = spanned;
        }

        public /* synthetic */ b(String str, String str2, Size size, Alignment alignment, FontStyle fontStyle, Spanned spanned, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : size, (i & 8) != 0 ? null : alignment, (i & 16) != 0 ? null : fontStyle, (i & 32) != 0 ? null : spanned);
        }
    }

    FontStyle b();

    Size c();

    @Override // android.os.Parcelable
    default int describeContents() {
        return 0;
    }

    Alignment e();

    String g();

    String h();

    @Override // android.os.Parcelable
    default void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(g());
        parcel.writeString(h());
        Size c = c();
        parcel.writeString(c != null ? c.name() : null);
        Alignment e = e();
        parcel.writeString(e != null ? e.name() : null);
        FontStyle b2 = b();
        parcel.writeString(b2 != null ? b2.name() : null);
    }

    public static final class a {
        static final /* synthetic */ a a = new a();

        private a() {
        }

        public final b a(JSONObject json) {
            Intrinsics.checkNotNullParameter(json, "json");
            String optString = json.optString(g.w);
            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
            String b = o.b(optString);
            if (b != null && !StringsKt.startsWith$default(b, "#", false, 2, (Object) null)) {
                b = "#" + b;
            }
            String str = b;
            String optString2 = json.optString(g.v);
            Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
            String b2 = o.b(optString2);
            if (b2 != null && !StringsKt.startsWith$default(b2, "#", false, 2, (Object) null)) {
                b2 = "#" + b2;
            }
            String str2 = b2;
            String optString3 = json.optString(g.t);
            Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
            String b3 = o.b(optString3);
            Size a2 = b3 != null ? Size.Companion.a(b3) : null;
            String optString4 = json.optString(g.x);
            Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
            String b4 = o.b(optString4);
            Alignment a3 = b4 != null ? Alignment.Companion.a(b4) : null;
            String optString5 = json.optString(g.u);
            Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
            String b5 = o.b(optString5);
            return new b(str, str2, a2, a3, b5 != null ? FontStyle.Companion.a(b5) : null, null);
        }

        public final JSONObject a(Style style) {
            Intrinsics.checkNotNullParameter(style, "style");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(g.w, style.g());
            jSONObject.put(g.v, style.h());
            Size c = style.c();
            jSONObject.put(g.t, c != null ? c.name() : null);
            Alignment e = style.e();
            jSONObject.put(g.x, e != null ? e.name() : null);
            FontStyle b = style.b();
            jSONObject.put(g.u, b != null ? b.name() : null);
            return jSONObject;
        }
    }
}
