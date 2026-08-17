package com.salesforce.marketingcloud.messages;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.storage.db.i;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class Region implements Parcelable, Comparable<Region> {
    public static final String MAGIC_REGION_ID = "~~m@g1c_f3nc3~~";
    public static final int REGION_TYPE_FENCE = 1;
    public static final int REGION_TYPE_PROXIMITY = 3;
    public final LatLon center;
    public final String description;
    public final String id;
    private boolean isInside;
    public final int major;
    public final List<Message> messages;
    public final int minor;
    public final String name;
    public final String proximityUuid;
    public final int radius;
    public final int regionType;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<Region> CREATOR = new c();
    private static final String TAG = g.a("Region");

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG$sdk_release() {
            return Region.TAG;
        }

        public final Region magicFence$sdk_release(LatLon center, int i) {
            Intrinsics.checkNotNullParameter(center, "center");
            return new Region(Region.MAGIC_REGION_ID, center, i, null, 0, 0, -1, null, null, null, 952, null);
        }

        private Companion() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface RegionType {
    }

    static final class a extends Lambda implements Function0<String> {
        public static final a b = new a();

        a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to parse Message from region payload.";
        }
    }

    static final class b extends Lambda implements Function0<String> {
        public static final b b = new b();

        b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to parse region messages.";
        }
    }

    public static final class c implements Parcelable.Creator<Region> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Region createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            LatLon createFromParcel = LatLon.CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            String readString2 = parcel.readString();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            int readInt4 = parcel.readInt();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            int readInt5 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt5);
            for (int i = 0; i != readInt5; i++) {
                arrayList.add(Message.CREATOR.createFromParcel(parcel));
            }
            return new Region(readString, createFromParcel, readInt, readString2, readInt2, readInt3, readInt4, readString3, readString4, arrayList);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Region[] newArray(int i) {
            return new Region[i];
        }
    }

    public Region(String id, LatLon center, int i, String str, int i2, int i3, int i4, String str2, String str3, List<Message> messages) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(center, "center");
        Intrinsics.checkNotNullParameter(messages, "messages");
        this.id = id;
        this.center = center;
        this.radius = i;
        this.proximityUuid = str;
        this.major = i2;
        this.minor = i3;
        this.regionType = i4;
        this.name = str2;
        this.description = str3;
        this.messages = messages;
    }

    public static /* synthetic */ void isInside$sdk_release$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "center", imports = {}))
    public final LatLon center() {
        return this.center;
    }

    public final String component1() {
        return this.id;
    }

    public final List<Message> component10() {
        return this.messages;
    }

    public final LatLon component2() {
        return this.center;
    }

    public final int component3() {
        return this.radius;
    }

    public final String component4() {
        return this.proximityUuid;
    }

    public final int component5() {
        return this.major;
    }

    public final int component6() {
        return this.minor;
    }

    public final int component7() {
        return this.regionType;
    }

    public final String component8() {
        return this.name;
    }

    public final String component9() {
        return this.description;
    }

    public final Region copy(String id, LatLon center, int i, String str, int i2, int i3, int i4, String str2, String str3, List<Message> messages) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(center, "center");
        Intrinsics.checkNotNullParameter(messages, "messages");
        return new Region(id, center, i, str, i2, i3, i4, str2, str3, messages);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "description", imports = {}))
    public final String description() {
        return this.description;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Region)) {
            return false;
        }
        Region region = (Region) obj;
        return Intrinsics.areEqual(this.id, region.id) && Intrinsics.areEqual(this.center, region.center) && this.radius == region.radius && Intrinsics.areEqual(this.proximityUuid, region.proximityUuid) && this.major == region.major && this.minor == region.minor && this.regionType == region.regionType && Intrinsics.areEqual(this.name, region.name) && Intrinsics.areEqual(this.description, region.description) && Intrinsics.areEqual(this.messages, region.messages);
    }

    public int hashCode() {
        int hashCode = ((((this.id.hashCode() * 31) + this.center.hashCode()) * 31) + Integer.hashCode(this.radius)) * 31;
        String str = this.proximityUuid;
        int hashCode2 = (((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.major)) * 31) + Integer.hashCode(this.minor)) * 31) + Integer.hashCode(this.regionType)) * 31;
        String str2 = this.name;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.description;
        return ((hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.messages.hashCode();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "id", imports = {}))
    public final String id() {
        return this.id;
    }

    public final boolean isInside$sdk_release() {
        return this.isInside;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "major", imports = {}))
    public final int major() {
        return this.major;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = i.e, imports = {}))
    public final List<Message> messages() {
        return this.messages;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "minor", imports = {}))
    public final int minor() {
        return this.minor;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "name", imports = {}))
    public final String name() {
        return this.name;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proximityUuid", imports = {}))
    public final String proximityUuid() {
        return this.proximityUuid;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "radius", imports = {}))
    public final int radius() {
        return this.radius;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "regionType", imports = {}))
    public final int regionType() {
        return this.regionType;
    }

    public final void setInside$sdk_release(boolean z) {
        this.isInside = z;
    }

    public String toString() {
        return "Region(id=" + this.id + ", center=" + this.center + ", radius=" + this.radius + ", proximityUuid=" + this.proximityUuid + ", major=" + this.major + ", minor=" + this.minor + ", regionType=" + this.regionType + ", name=" + this.name + ", description=" + this.description + ", messages=" + this.messages + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.id);
        this.center.writeToParcel(out, i);
        out.writeInt(this.radius);
        out.writeString(this.proximityUuid);
        out.writeInt(this.major);
        out.writeInt(this.minor);
        out.writeInt(this.regionType);
        out.writeString(this.name);
        out.writeString(this.description);
        List<Message> list = this.messages;
        out.writeInt(list.size());
        Iterator<Message> it = list.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(out, i);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Region other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.id.compareTo(other.id);
    }

    public /* synthetic */ Region(String str, LatLon latLon, int i, String str2, int i2, int i3, int i4, String str3, String str4, List list, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, latLon, i, (i5 & 8) != 0 ? null : str2, (i5 & 16) != 0 ? 0 : i2, (i5 & 32) != 0 ? 0 : i3, i4, (i5 & 128) != 0 ? null : str3, (i5 & 256) != 0 ? null : str4, (i5 & 512) != 0 ? CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Region(org.json.JSONObject r17) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 418
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.Region.<init>(org.json.JSONObject):void");
    }
}
