package com.salesforce.marketingcloud.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.getcapacitor.PluginCall;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public interface Template extends Parcelable {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Type implements Parcelable {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Type[] $VALUES;
        public static final Parcelable.Creator<Type> CREATOR;
        private final String value;
        public static final Type RichButtons = new Type("RichButtons", 0, PluginCall.CALLBACK_ID_DANGLING);
        public static final Type CarouselFull = new Type("CarouselFull", 1, "0");

        public static final class a implements Parcelable.Creator<Type> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Type createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return Type.valueOf(parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Type[] newArray(int i) {
                return new Type[i];
            }
        }

        private static final /* synthetic */ Type[] $values() {
            return new Type[]{RichButtons, CarouselFull};
        }

        static {
            Type[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
            CREATOR = new a();
        }

        private Type(String str, int i, String str2) {
            this.value = str2;
        }

        public static EnumEntries<Type> getEntries() {
            return $ENTRIES;
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public final String getValue() {
            return this.value;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int i) {
            Intrinsics.checkNotNullParameter(out, "out");
            out.writeString(name());
        }
    }

    Style a();

    String d();

    Type f();
}
