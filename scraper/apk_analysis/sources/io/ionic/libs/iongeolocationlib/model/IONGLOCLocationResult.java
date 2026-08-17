package io.ionic.libs.iongeolocationlib.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.storage.db.h;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IONGLOCLocationResult.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\fHÆ\u0003J`\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020+HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006,"}, d2 = {"Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;", "", h.a.b, "", h.a.c, "altitude", "accuracy", "", "altitudeAccuracy", "heading", "speed", "timestamp", "", "(DDDFLjava/lang/Float;FFJ)V", "getAccuracy", "()F", "getAltitude", "()D", "getAltitudeAccuracy", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getHeading", "getLatitude", "getLongitude", "getSpeed", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(DDDFLjava/lang/Float;FFJ)Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "toString", "", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class IONGLOCLocationResult {
    private final float accuracy;
    private final double altitude;
    private final Float altitudeAccuracy;
    private final float heading;
    private final double latitude;
    private final double longitude;
    private final float speed;
    private final long timestamp;

    /* renamed from: component1, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component2, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* renamed from: component3, reason: from getter */
    public final double getAltitude() {
        return this.altitude;
    }

    /* renamed from: component4, reason: from getter */
    public final float getAccuracy() {
        return this.accuracy;
    }

    /* renamed from: component5, reason: from getter */
    public final Float getAltitudeAccuracy() {
        return this.altitudeAccuracy;
    }

    /* renamed from: component6, reason: from getter */
    public final float getHeading() {
        return this.heading;
    }

    /* renamed from: component7, reason: from getter */
    public final float getSpeed() {
        return this.speed;
    }

    /* renamed from: component8, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final IONGLOCLocationResult copy(double latitude, double longitude, double altitude, float accuracy, Float altitudeAccuracy, float heading, float speed, long timestamp) {
        return new IONGLOCLocationResult(latitude, longitude, altitude, accuracy, altitudeAccuracy, heading, speed, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IONGLOCLocationResult)) {
            return false;
        }
        IONGLOCLocationResult iONGLOCLocationResult = (IONGLOCLocationResult) other;
        return Double.compare(this.latitude, iONGLOCLocationResult.latitude) == 0 && Double.compare(this.longitude, iONGLOCLocationResult.longitude) == 0 && Double.compare(this.altitude, iONGLOCLocationResult.altitude) == 0 && Float.compare(this.accuracy, iONGLOCLocationResult.accuracy) == 0 && Intrinsics.areEqual((Object) this.altitudeAccuracy, (Object) iONGLOCLocationResult.altitudeAccuracy) && Float.compare(this.heading, iONGLOCLocationResult.heading) == 0 && Float.compare(this.speed, iONGLOCLocationResult.speed) == 0 && this.timestamp == iONGLOCLocationResult.timestamp;
    }

    public int hashCode() {
        int hashCode = ((((((Double.hashCode(this.latitude) * 31) + Double.hashCode(this.longitude)) * 31) + Double.hashCode(this.altitude)) * 31) + Float.hashCode(this.accuracy)) * 31;
        Float f = this.altitudeAccuracy;
        return ((((((hashCode + (f == null ? 0 : f.hashCode())) * 31) + Float.hashCode(this.heading)) * 31) + Float.hashCode(this.speed)) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "IONGLOCLocationResult(latitude=" + this.latitude + ", longitude=" + this.longitude + ", altitude=" + this.altitude + ", accuracy=" + this.accuracy + ", altitudeAccuracy=" + this.altitudeAccuracy + ", heading=" + this.heading + ", speed=" + this.speed + ", timestamp=" + this.timestamp + ")";
    }

    public IONGLOCLocationResult(double d, double d2, double d3, float f, Float f2, float f3, float f4, long j) {
        this.latitude = d;
        this.longitude = d2;
        this.altitude = d3;
        this.accuracy = f;
        this.altitudeAccuracy = f2;
        this.heading = f3;
        this.speed = f4;
        this.timestamp = j;
    }

    public /* synthetic */ IONGLOCLocationResult(double d, double d2, double d3, float f, Float f2, float f3, float f4, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(d, d2, d3, f, (i & 16) != 0 ? null : f2, f3, f4, j);
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final double getAltitude() {
        return this.altitude;
    }

    public final float getAccuracy() {
        return this.accuracy;
    }

    public final Float getAltitudeAccuracy() {
        return this.altitudeAccuracy;
    }

    public final float getHeading() {
        return this.heading;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
