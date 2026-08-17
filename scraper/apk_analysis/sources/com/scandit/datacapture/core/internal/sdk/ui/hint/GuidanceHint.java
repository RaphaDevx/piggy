package com.scandit.datacapture.core.internal.sdk.ui.hint;

import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHint;
import com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintAnchor;
import com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintIcon;
import com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintLayout;
import com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintStyle;
import com.scandit.datacapture.core.internal.module.ui.NativeHintStyle;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0004\u0010\nB!\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0004\u0010\rBK\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0004\u0010\u0015J\u001a\u0010\u0017\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\t\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010%\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/hint/GuidanceHint;", "", "Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHint;", "native", "<init>", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHint;)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintStyle;", "style", "", "text", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintStyle;Ljava/lang/String;)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintAnchor;", "anchor", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintStyle;Ljava/lang/String;Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintAnchor;)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintLayout;", "layout", "Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintIcon;", BarcodePickDeserializer.FIELD_ICON, "iconResource", "", "canBeDismissed", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintStyle;Ljava/lang/String;Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintAnchor;Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintLayout;Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHintIcon;Ljava/lang/String;Z)V", Request.JsonKeys.OTHER, "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "a", "Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHint;", "getNative", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeGuidanceHint;", "getText", "()Ljava/lang/String;", "Lcom/scandit/datacapture/core/internal/module/ui/NativeHintStyle;", "getHintStyle", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeHintStyle;", "hintStyle", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class GuidanceHint {

    /* renamed from: a, reason: from kotlin metadata */
    private final NativeGuidanceHint native;

    public GuidanceHint(NativeGuidanceHint nativeGuidanceHint) {
        Intrinsics.checkNotNullParameter(nativeGuidanceHint, "native");
        this.native = nativeGuidanceHint;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof GuidanceHint) {
            return Intrinsics.areEqual(this.native, ((GuidanceHint) other).native);
        }
        return false;
    }

    public final NativeHintStyle getHintStyle() {
        NativeHintStyle hintStyle = this.native.getHintStyle();
        Intrinsics.checkNotNullExpressionValue(hintStyle, "getHintStyle(...)");
        return hintStyle;
    }

    public final NativeGuidanceHint getNative() {
        return this.native;
    }

    public final String getText() {
        String text = this.native.getText();
        Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
        return text;
    }

    public int hashCode() {
        return this.native.hashCode();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GuidanceHint(com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintStyle r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "style"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintAnchor r0 = com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintAnchor.ABOVE_VIEW_FINDER
            com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHint r2 = com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHint.create(r2, r3, r0)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.ui.hint.GuidanceHint.<init>(com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintStyle, java.lang.String):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GuidanceHint(com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintStyle r2, java.lang.String r3, com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintAnchor r4) {
        /*
            r1 = this;
            java.lang.String r0 = "style"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "anchor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHint r2 = com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHint.create(r2, r3, r4)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.ui.hint.GuidanceHint.<init>(com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintStyle, java.lang.String, com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintAnchor):void");
    }

    public /* synthetic */ GuidanceHint(NativeGuidanceHintStyle nativeGuidanceHintStyle, String str, NativeGuidanceHintAnchor nativeGuidanceHintAnchor, NativeGuidanceHintLayout nativeGuidanceHintLayout, NativeGuidanceHintIcon nativeGuidanceHintIcon, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeGuidanceHintStyle, str, nativeGuidanceHintAnchor, (i & 8) != 0 ? NativeGuidanceHintLayout.COMPACT : nativeGuidanceHintLayout, (i & 16) != 0 ? NativeGuidanceHintIcon.NONE : nativeGuidanceHintIcon, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? false : z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GuidanceHint(com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintStyle r2, java.lang.String r3, com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintAnchor r4, com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintLayout r5, com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintIcon r6, java.lang.String r7, boolean r8) {
        /*
            r1 = this;
            java.lang.String r0 = "style"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "anchor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "layout"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "icon"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHint r2 = com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHint.createFull(r2, r3, r4, r5, r6, r7, r8)
            java.lang.String r3 = "createFull(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.ui.hint.GuidanceHint.<init>(com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintStyle, java.lang.String, com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintAnchor, com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintLayout, com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHintIcon, java.lang.String, boolean):void");
    }
}
