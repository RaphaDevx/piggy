package com.salesforce.marketingcloud.sfmcsdk.components.http;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.SentryLogEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Response.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0002!\"BM\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\bJ\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R#\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006#"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Response;", "Landroid/os/Parcelable;", "code", "", SentryLogEvent.JsonKeys.BODY, "", "message", "startTimeMillis", "", "endTimeMillis", "headers", "", "", "(ILjava/lang/String;Ljava/lang/String;JJLjava/util/Map;)V", "getBody", "()Ljava/lang/String;", "getCode", "()I", "getEndTimeMillis", "()J", "getHeaders", "()Ljava/util/Map;", "getMessage", "getStartTimeMillis", "describeContents", "isSuccessful", "", "timeToExecute", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Builder", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Response implements Parcelable {
    private final String body;
    private final int code;
    private final long endTimeMillis;
    private final Map<String, List<String>> headers;
    private final String message;
    private final long startTimeMillis;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<Response> CREATOR = new Creator();

    /* compiled from: Response.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<Response> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final Response createFromParcel(Parcel parcel) {
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
            return new Response(readInt, readString, readString2, readLong, readLong2, linkedHashMap);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final Response[] newArray(int i) {
            return new Response[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.code);
        parcel.writeString(this.body);
        parcel.writeString(this.message);
        parcel.writeLong(this.startTimeMillis);
        parcel.writeLong(this.endTimeMillis);
        Map<String, List<String>> map = this.headers;
        parcel.writeInt(map.size());
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeStringList(entry.getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Response(int i, String str, String str2, long j, long j2, Map<String, ? extends List<String>> headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.code = i;
        this.body = str;
        this.message = str2;
        this.startTimeMillis = j;
        this.endTimeMillis = j2;
        this.headers = headers;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getBody() {
        return this.body;
    }

    public final String getMessage() {
        return this.message;
    }

    public final long getStartTimeMillis() {
        return this.startTimeMillis;
    }

    public final long getEndTimeMillis() {
        return this.endTimeMillis;
    }

    public final Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    /* compiled from: Response.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Response$Companion;", "", "()V", "error", "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Response;", "message", "", "code", "", "error$sfmcsdk_release", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Response error$sfmcsdk_release(String message, int code) {
            Intrinsics.checkNotNullParameter(message, "message");
            long currentTimeMillis = System.currentTimeMillis();
            return new Builder().code(code).message(message).startTimeMillis(currentTimeMillis).endTimeMillis(currentTimeMillis).build();
        }
    }

    public final boolean isSuccessful() {
        int i = this.code;
        return 200 <= i && i < 300;
    }

    public final long timeToExecute() {
        return this.endTimeMillis - this.startTimeMillis;
    }

    /* compiled from: Response.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ \u0010\t\u001a\u00020\u00002\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b0\nJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Response$Builder;", "", "()V", SentryLogEvent.JsonKeys.BODY, "", "code", "", "endTimeMillis", "", "headers", "", "", "message", "startTimeMillis", "build", "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Response;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Builder {
        private String body;
        private int code;
        private long endTimeMillis;
        private Map<String, ? extends List<String>> headers;
        private String message;
        private long startTimeMillis;

        public final Builder code(int code) {
            this.code = code;
            return this;
        }

        public final Builder body(String body) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.body = body;
            return this;
        }

        public final Builder message(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
            return this;
        }

        public final Builder startTimeMillis(long startTimeMillis) {
            this.startTimeMillis = startTimeMillis;
            return this;
        }

        public final Builder endTimeMillis(long endTimeMillis) {
            this.endTimeMillis = endTimeMillis;
            return this;
        }

        public final Builder headers(Map<String, ? extends List<String>> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.headers = headers;
            return this;
        }

        public final Response build() {
            int i = this.code;
            String str = this.body;
            String str2 = this.message;
            long j = this.startTimeMillis;
            long j2 = this.endTimeMillis;
            Map<String, ? extends List<String>> map = this.headers;
            if (map == null) {
                map = MapsKt.emptyMap();
            }
            return new Response(i, str, str2, j, j2, map);
        }
    }
}
