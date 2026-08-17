package com.scandit.capacitor.datacapture.core.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ResizeAndMoveInfo.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\t\u0010\rJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/data/ResizeAndMoveInfo;", "", "top", "", "left", "width", "height", ResizeAndMoveInfo.KEY_ELEVATION, "", "<init>", "(IIIIZ)V", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getTop", "()I", "getLeft", "getWidth", "getHeight", "getShouldBeUnderWebView", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", Request.JsonKeys.OTHER, "hashCode", "toString", "", "Companion", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class ResizeAndMoveInfo {
    private static final String KEY_ELEVATION = "shouldBeUnderWebView";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_LEFT = "left";
    private static final String KEY_TOP = "top";
    private static final String KEY_WIDTH = "width";
    private final int height;
    private final int left;
    private final boolean shouldBeUnderWebView;
    private final int top;
    private final int width;

    public static /* synthetic */ ResizeAndMoveInfo copy$default(ResizeAndMoveInfo resizeAndMoveInfo, int i, int i2, int i3, int i4, boolean z, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = resizeAndMoveInfo.top;
        }
        if ((i5 & 2) != 0) {
            i2 = resizeAndMoveInfo.left;
        }
        int i6 = i2;
        if ((i5 & 4) != 0) {
            i3 = resizeAndMoveInfo.width;
        }
        int i7 = i3;
        if ((i5 & 8) != 0) {
            i4 = resizeAndMoveInfo.height;
        }
        int i8 = i4;
        if ((i5 & 16) != 0) {
            z = resizeAndMoveInfo.shouldBeUnderWebView;
        }
        return resizeAndMoveInfo.copy(i, i6, i7, i8, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTop() {
        return this.top;
    }

    /* renamed from: component2, reason: from getter */
    public final int getLeft() {
        return this.left;
    }

    /* renamed from: component3, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* renamed from: component4, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getShouldBeUnderWebView() {
        return this.shouldBeUnderWebView;
    }

    public final ResizeAndMoveInfo copy(int top, int left, int width, int height, boolean shouldBeUnderWebView) {
        return new ResizeAndMoveInfo(top, left, width, height, shouldBeUnderWebView);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResizeAndMoveInfo)) {
            return false;
        }
        ResizeAndMoveInfo resizeAndMoveInfo = (ResizeAndMoveInfo) other;
        return this.top == resizeAndMoveInfo.top && this.left == resizeAndMoveInfo.left && this.width == resizeAndMoveInfo.width && this.height == resizeAndMoveInfo.height && this.shouldBeUnderWebView == resizeAndMoveInfo.shouldBeUnderWebView;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.top) * 31) + Integer.hashCode(this.left)) * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height)) * 31) + Boolean.hashCode(this.shouldBeUnderWebView);
    }

    public String toString() {
        return "ResizeAndMoveInfo(top=" + this.top + ", left=" + this.left + ", width=" + this.width + ", height=" + this.height + ", shouldBeUnderWebView=" + this.shouldBeUnderWebView + ")";
    }

    public ResizeAndMoveInfo(int i, int i2, int i3, int i4, boolean z) {
        this.top = i;
        this.left = i2;
        this.width = i3;
        this.height = i4;
        this.shouldBeUnderWebView = z;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final boolean getShouldBeUnderWebView() {
        return this.shouldBeUnderWebView;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ResizeAndMoveInfo(JSONObject json) {
        this(json.getInt("top"), json.getInt("left"), json.getInt("width"), json.getInt("height"), json.getBoolean(KEY_ELEVATION));
        Intrinsics.checkNotNullParameter(json, "json");
    }
}
