package com.scandit.datacapture.core.internal.sdk.common.geometry;

import com.scandit.datacapture.core.common.geometry.Point;
import io.sentry.SentryEnvelopeItemHeader;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\nJ\r\u0010\u000b\u001a\u00020\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u000e\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0014\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0018\u0010\u0017R\u0017\u0010\u001b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017R\u0017\u0010\u001e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010 \u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\"\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b!\u0010\u0017¨\u0006#"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;", "", "", "dx", "dy", "<init>", "(FF)V", "Lcom/scandit/datacapture/core/common/geometry/Point;", "a", "b", "(Lcom/scandit/datacapture/core/common/geometry/Point;Lcom/scandit/datacapture/core/common/geometry/Point;)V", "normalized", "()Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;", "value", "times", "(F)Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;", Request.JsonKeys.OTHER, "(Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;)F", "plus", "(Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;)Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;", "minus", "F", "getDx", "()F", "getDy", "c", "getLength", SentryEnvelopeItemHeader.JsonKeys.LENGTH, "d", "getSlope", "slope", "getAngleDegrees", "angleDegrees", "getAngleRadian", "angleRadian", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class Vector {

    /* renamed from: a, reason: from kotlin metadata */
    private final float dx;

    /* renamed from: b, reason: from kotlin metadata */
    private final float dy;

    /* renamed from: c, reason: from kotlin metadata */
    private final float length;

    /* renamed from: d, reason: from kotlin metadata */
    private final float slope;

    public Vector(float f, float f2) {
        this.dx = f;
        this.dy = f2;
        this.length = (float) Math.sqrt((f2 * f2) + (f * f));
        this.slope = f2 / f;
    }

    public final float getAngleDegrees() {
        return (float) ((getAngleRadian() * 180.0d) / 3.141592653589793d);
    }

    public final float getAngleRadian() {
        Vector normalized = normalized();
        float f = normalized.dy;
        float f2 = normalized.dx;
        return f >= 0.0f ? (float) Math.acos(f2) : -((float) Math.acos(f2));
    }

    public final float getDx() {
        return this.dx;
    }

    public final float getDy() {
        return this.dy;
    }

    public final float getLength() {
        return this.length;
    }

    public final float getSlope() {
        return this.slope;
    }

    public final Vector minus(Vector other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return new Vector(this.dx - other.dx, this.dy - other.dy);
    }

    public final Vector normalized() {
        float f = this.dx;
        float f2 = this.length;
        return new Vector(f / f2, this.dy / f2);
    }

    public final Vector plus(Vector other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return new Vector(this.dx + other.dx, this.dy + other.dy);
    }

    public final Vector times(float value) {
        return new Vector(this.dx * value, this.dy * value);
    }

    public final float times(Vector other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return (this.dy * other.dy) + (this.dx * other.dx);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Vector(Point a, Point b) {
        this(b.getX() - a.getX(), b.getY() - a.getY());
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
    }
}
