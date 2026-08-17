package com.scandit.datacapture.core.ui.notification;

import com.scandit.datacapture.core.internal.sdk.ui.hint.ToastHint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0011\u0010\r\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/core/ui/notification/NotificationConfiguration;", "", "Lcom/scandit/datacapture/core/internal/sdk/ui/hint/ToastHint;", "toastHint", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/ui/hint/ToastHint;)V", "a", "Lcom/scandit/datacapture/core/internal/sdk/ui/hint/ToastHint;", "getToastHint$scandit_capture_core", "()Lcom/scandit/datacapture/core/internal/sdk/ui/hint/ToastHint;", "", "getTag", "()Ljava/lang/String;", "tag", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class NotificationConfiguration {

    /* renamed from: a, reason: from kotlin metadata */
    private final ToastHint toastHint;

    public NotificationConfiguration(ToastHint toastHint) {
        Intrinsics.checkNotNullParameter(toastHint, "toastHint");
        this.toastHint = toastHint;
    }

    public final String getTag() {
        return this.toastHint.getTag();
    }

    /* renamed from: getToastHint$scandit_capture_core, reason: from getter */
    public final ToastHint getToastHint() {
        return this.toastHint;
    }
}
