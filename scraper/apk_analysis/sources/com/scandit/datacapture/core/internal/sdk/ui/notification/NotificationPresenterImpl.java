package com.scandit.datacapture.core.internal.sdk.ui.notification;

import com.scandit.datacapture.core.internal.sdk.ui.hint.HintPresenterV2;
import com.scandit.datacapture.core.ui.notification.NotificationConfiguration;
import com.scandit.datacapture.core.ui.notification.NotificationPresenter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/notification/NotificationPresenterImpl;", "Lcom/scandit/datacapture/core/ui/notification/NotificationPresenter;", "Lkotlin/Function0;", "Lcom/scandit/datacapture/core/internal/sdk/ui/hint/HintPresenterV2;", "hintPresenter", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "Lcom/scandit/datacapture/core/ui/notification/NotificationConfiguration;", "notification", "", "showNotification", "(Lcom/scandit/datacapture/core/ui/notification/NotificationConfiguration;)V", "hideNotification", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class NotificationPresenterImpl implements NotificationPresenter {
    private final Function0 a;

    public NotificationPresenterImpl(Function0<HintPresenterV2> hintPresenter) {
        Intrinsics.checkNotNullParameter(hintPresenter, "hintPresenter");
        this.a = hintPresenter;
    }

    @Override // com.scandit.datacapture.core.ui.notification.NotificationPresenter
    public void hideNotification(NotificationConfiguration notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        ((HintPresenterV2) this.a.invoke()).hideToast(notification.getToastHint().getTag());
    }

    @Override // com.scandit.datacapture.core.ui.notification.NotificationPresenter
    public void showNotification(NotificationConfiguration notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        ((HintPresenterV2) this.a.invoke()).showToast(notification.getToastHint());
    }
}
