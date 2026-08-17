package com.scandit.datacapture.core.ui.notification;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.internal.module.ui.NativeHintStyle;
import com.scandit.datacapture.core.internal.module.ui.NativeHintStyleBuilder;
import com.scandit.datacapture.core.internal.module.ui.NativeToastHint;
import com.scandit.datacapture.core.internal.module.ui.NativeToastHintStyle;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.hint.ToastHint;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.icon.ScanditIconSerializer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00002\b\b\u0001\u0010\u000f\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u000eJ\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/scandit/datacapture/core/ui/notification/NotificationBuilder;", "", "", "text", "tag", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", BarcodePickDeserializer.FIELD_ICON, "withIcon", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;)Lcom/scandit/datacapture/core/ui/notification/NotificationBuilder;", "", "textColor", "withTextColor", "(I)Lcom/scandit/datacapture/core/ui/notification/NotificationBuilder;", "backgroundColor", "withBackgroundColor", "", "swipeToDismiss", "withSwipeToDismiss", "(Z)Lcom/scandit/datacapture/core/ui/notification/NotificationBuilder;", "Lcom/scandit/datacapture/core/ui/notification/NotificationStyle;", "notificationStyle", "withNotificationStyle", "(Lcom/scandit/datacapture/core/ui/notification/NotificationStyle;)Lcom/scandit/datacapture/core/ui/notification/NotificationBuilder;", "Lcom/scandit/datacapture/core/ui/notification/NotificationConfiguration;", "build", "()Lcom/scandit/datacapture/core/ui/notification/NotificationConfiguration;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class NotificationBuilder {
    private final String a;
    private final String b;
    private final NativeHintStyleBuilder c;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NotificationStyle.values().length];
            try {
                iArr[NotificationStyle.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NotificationStyle.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[NotificationStyle.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[NotificationStyle.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public NotificationBuilder(String text, String tag) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.a = text;
        this.b = tag;
        NativeHintStyleBuilder create = NativeHintStyleBuilder.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.c = create;
    }

    public final NotificationConfiguration build() {
        NativeHintStyle build = this.c.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return new NotificationConfiguration(new ToastHint(build, this.a, this.b));
    }

    public final NotificationBuilder withBackgroundColor(int backgroundColor) {
        this.c.withBackgroundColor(NativeColorExtensionsKt.toNativeColor(backgroundColor));
        return this;
    }

    public final NotificationBuilder withIcon(ScanditIcon icon) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.c.withScanditIcon(ScanditIconSerializer.toJson(icon));
        return this;
    }

    public final NotificationBuilder withNotificationStyle(NotificationStyle notificationStyle) {
        NativeToastHintStyle nativeToastHintStyle;
        Intrinsics.checkNotNullParameter(notificationStyle, "notificationStyle");
        int i = WhenMappings.$EnumSwitchMapping$0[notificationStyle.ordinal()];
        if (i == 1) {
            nativeToastHintStyle = NativeToastHintStyle.SUCCESS_SOLID;
        } else if (i == 2) {
            nativeToastHintStyle = NativeToastHintStyle.INFO;
        } else if (i == 3) {
            nativeToastHintStyle = NativeToastHintStyle.WARNING_SOLID;
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            nativeToastHintStyle = NativeToastHintStyle.ERROR;
        }
        NativeToastHint.applyToastHintStyle(nativeToastHintStyle, this.c);
        return this;
    }

    public final NotificationBuilder withSwipeToDismiss(boolean swipeToDismiss) {
        this.c.withCanBeDismissed(swipeToDismiss);
        return this;
    }

    public final NotificationBuilder withTextColor(int textColor) {
        this.c.withTextColor(NativeColorExtensionsKt.toNativeColor(textColor));
        return this;
    }
}
