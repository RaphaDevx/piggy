package com.scandit.datacapture.core.ui.notification;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/core/ui/notification/NotificationStyle;", "", "SUCCESS", "INFO", "WARNING", "ERROR", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class NotificationStyle {
    public static final NotificationStyle ERROR;
    public static final NotificationStyle INFO;
    public static final NotificationStyle SUCCESS;
    public static final NotificationStyle WARNING;
    private static final /* synthetic */ NotificationStyle[] a;
    private static final /* synthetic */ EnumEntries b;

    static {
        NotificationStyle notificationStyle = new NotificationStyle("SUCCESS", 0);
        SUCCESS = notificationStyle;
        NotificationStyle notificationStyle2 = new NotificationStyle("INFO", 1);
        INFO = notificationStyle2;
        NotificationStyle notificationStyle3 = new NotificationStyle("WARNING", 2);
        WARNING = notificationStyle3;
        NotificationStyle notificationStyle4 = new NotificationStyle("ERROR", 3);
        ERROR = notificationStyle4;
        NotificationStyle[] notificationStyleArr = {notificationStyle, notificationStyle2, notificationStyle3, notificationStyle4};
        a = notificationStyleArr;
        b = EnumEntriesKt.enumEntries(notificationStyleArr);
    }

    private NotificationStyle(String str, int i) {
    }

    public static EnumEntries<NotificationStyle> getEntries() {
        return b;
    }

    public static NotificationStyle valueOf(String str) {
        return (NotificationStyle) Enum.valueOf(NotificationStyle.class, str);
    }

    public static NotificationStyle[] values() {
        return (NotificationStyle[]) a.clone();
    }
}
