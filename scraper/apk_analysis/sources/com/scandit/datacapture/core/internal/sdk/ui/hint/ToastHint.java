package com.scandit.datacapture.core.internal.sdk.ui.hint;

import com.scandit.datacapture.core.internal.module.ui.NativeHintStyle;
import com.scandit.datacapture.core.internal.module.ui.NativeToastHint;
import com.scandit.datacapture.core.internal.module.ui.NativeToastHintStyle;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B-\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0004\u0010\rB!\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u000e\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u0004\u0010\u000fJ\u001a\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\n\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001b¨\u0006 "}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/hint/ToastHint;", "", "Lcom/scandit/datacapture/core/internal/module/ui/NativeToastHint;", "native", "<init>", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeToastHint;)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeToastHintStyle;", "style", "", "text", "tag", "", "canBeDismissed", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeToastHintStyle;Ljava/lang/String;Ljava/lang/String;Z)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeHintStyle;", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeHintStyle;Ljava/lang/String;Ljava/lang/String;)V", Request.JsonKeys.OTHER, "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "a", "Lcom/scandit/datacapture/core/internal/module/ui/NativeToastHint;", "getNative", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeToastHint;", "getText", "()Ljava/lang/String;", "getHintStyle", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeHintStyle;", "hintStyle", "getTag", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ToastHint {

    /* renamed from: a, reason: from kotlin metadata */
    private final NativeToastHint native;

    public ToastHint(NativeToastHint nativeToastHint) {
        Intrinsics.checkNotNullParameter(nativeToastHint, "native");
        this.native = nativeToastHint;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof ToastHint) {
            return Intrinsics.areEqual(this.native, ((ToastHint) other).native);
        }
        return false;
    }

    public final NativeHintStyle getHintStyle() {
        NativeHintStyle hintStyle = this.native.getHintStyle();
        Intrinsics.checkNotNullExpressionValue(hintStyle, "getHintStyle(...)");
        return hintStyle;
    }

    public final NativeToastHint getNative() {
        return this.native;
    }

    public final String getTag() {
        String tag = this.native.getTag();
        Intrinsics.checkNotNullExpressionValue(tag, "getTag(...)");
        return tag;
    }

    public final String getText() {
        String text = this.native.getText();
        Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
        return text;
    }

    public int hashCode() {
        return this.native.hashCode();
    }

    public /* synthetic */ ToastHint(NativeToastHintStyle nativeToastHintStyle, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeToastHintStyle, str, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? false : z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ToastHint(com.scandit.datacapture.core.internal.module.ui.NativeToastHintStyle r2, java.lang.String r3, java.lang.String r4, boolean r5) {
        /*
            r1 = this;
            java.lang.String r0 = "style"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "tag"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.scandit.datacapture.core.internal.module.ui.NativeToastHint r2 = com.scandit.datacapture.core.internal.module.ui.NativeToastHint.create(r2, r3, r4, r5)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.ui.hint.ToastHint.<init>(com.scandit.datacapture.core.internal.module.ui.NativeToastHintStyle, java.lang.String, java.lang.String, boolean):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ToastHint(com.scandit.datacapture.core.internal.module.ui.NativeHintStyle r2, java.lang.String r3, java.lang.String r4) {
        /*
            r1 = this;
            java.lang.String r0 = "style"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "tag"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.scandit.datacapture.core.internal.module.ui.NativeToastHint r2 = com.scandit.datacapture.core.internal.module.ui.NativeToastHint.createWithHintStyle(r2, r3, r4)
            java.lang.String r3 = "createWithHintStyle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.ui.hint.ToastHint.<init>(com.scandit.datacapture.core.internal.module.ui.NativeHintStyle, java.lang.String, java.lang.String):void");
    }
}
