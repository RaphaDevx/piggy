package com.salesforce.marketingcloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class InitializationStatus {
    public static final b Companion = new b(null);
    public final boolean encryptionChanged;
    public final List<String> initializedComponents;
    public final boolean isUsable;
    public final boolean locationsError;
    public final boolean messagingPermissionError;
    public final String playServicesMessage;
    public final int playServicesStatus;
    public final boolean proximityError;
    public final boolean sslProviderEnablementError;
    public final Status status;
    public final boolean storageError;
    public final Throwable unrecoverableException;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Status {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Status[] $VALUES;
        public static final Status SUCCESS = new Status("SUCCESS", 0);
        public static final Status COMPLETED_WITH_DEGRADED_FUNCTIONALITY = new Status("COMPLETED_WITH_DEGRADED_FUNCTIONALITY", 1);
        public static final Status FAILED = new Status("FAILED", 2);

        private static final /* synthetic */ Status[] $values() {
            return new Status[]{SUCCESS, COMPLETED_WITH_DEGRADED_FUNCTIONALITY, FAILED};
        }

        static {
            Status[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private Status(String str, int i) {
        }

        public static EnumEntries<Status> getEntries() {
            return $ENTRIES;
        }

        public static Status valueOf(String str) {
            return (Status) Enum.valueOf(Status.class, str);
        }

        public static Status[] values() {
            return (Status[]) $VALUES.clone();
        }
    }

    public static final class a {
        private Throwable a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private String g;
        private boolean i;
        private int h = -1;
        private final List<String> j = new ArrayList();

        public final void a(boolean z) {
            this.c = z;
        }

        public final void b(boolean z) {
            this.b = z;
        }

        public final void c(boolean z) {
            this.e = z;
        }

        public final void d(boolean z) {
            this.i = z;
        }

        public final void e(boolean z) {
            this.f = z;
        }

        public final void f(boolean z) {
            this.d = z;
        }

        public final void a(int i) {
            this.h = i;
        }

        public final boolean b() {
            return this.a == null;
        }

        public final void a(String str) {
            if (str != null) {
                String str2 = this.g;
                if (str2 == null) {
                    this.g = str;
                    return;
                }
                this.g = str2 + StringUtils.LF + str;
            }
        }

        public final void a(Throwable throwable) {
            Intrinsics.checkNotNullParameter(throwable, "throwable");
            this.a = throwable;
        }

        public final void a(d component) {
            Intrinsics.checkNotNullParameter(component, "component");
            List<String> list = this.j;
            String componentName = component.componentName();
            Intrinsics.checkNotNullExpressionValue(componentName, "componentName(...)");
            list.add(componentName);
        }

        public final InitializationStatus a() {
            Status status;
            if (b()) {
                if (!this.b && !this.d && !this.e && !this.i && !this.f) {
                    status = Status.SUCCESS;
                } else {
                    status = Status.COMPLETED_WITH_DEGRADED_FUNCTIONALITY;
                }
            } else {
                status = Status.FAILED;
            }
            Throwable th = this.a;
            boolean z = this.b;
            int i = this.h;
            String str = this.g;
            boolean z2 = this.c;
            boolean z3 = this.d;
            boolean z4 = this.i;
            boolean z5 = this.e;
            boolean z6 = this.f;
            String[] strArr = (String[]) this.j.toArray(new String[0]);
            return new InitializationStatus(status, th, z, i, str, z2, z3, z4, z5, z6, CollectionsKt.listOf(Arrays.copyOf(strArr, strArr.length)), false, 2048, null);
        }
    }

    public static final class b {
        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InitializationStatus a() {
            a b = b();
            b.a(new IllegalStateException("Amazon devices are not supported"));
            return b.a();
        }

        public final a b() {
            return new a();
        }

        public final InitializationStatus c() {
            a b = b();
            b.a(new IllegalStateException("The SDK no longer includes the legacy encryption dependency. If you wish to proceed, please set the configuration parameter legacyEncryptionDependencyForciblyRemoved to true."));
            return b.a();
        }

        private b() {
        }
    }

    public InitializationStatus(Status status, Throwable th, boolean z, int i, String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, List<String> initializedComponents, boolean z7) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(initializedComponents, "initializedComponents");
        this.status = status;
        this.unrecoverableException = th;
        this.locationsError = z;
        this.playServicesStatus = i;
        this.playServicesMessage = str;
        this.encryptionChanged = z2;
        this.storageError = z3;
        this.proximityError = z4;
        this.messagingPermissionError = z5;
        this.sslProviderEnablementError = z6;
        this.initializedComponents = initializedComponents;
        this.isUsable = z7;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encryptionChanged", imports = {}))
    public final boolean encryptionChanged() {
        return this.encryptionChanged;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "initializedComponents", imports = {}))
    public final List<String> initializedComponents() {
        return this.initializedComponents;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "isUsable", imports = {}))
    public final boolean isUsable() {
        return this.isUsable;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "locationsError", imports = {}))
    public final boolean locationsError() {
        return this.locationsError;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "messagingPermissionError", imports = {}))
    public final boolean messagingPermissionError() {
        return this.messagingPermissionError;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "playServicesMessage", imports = {}))
    public final String playServicesMessage() {
        return this.playServicesMessage;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "playServicesStatus", imports = {}))
    public final int playServicesStatus() {
        return this.playServicesStatus;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proximityError", imports = {}))
    public final boolean proximityError() {
        return this.proximityError;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sslProviderEnablementError", imports = {}))
    public final boolean sslProviderEnablementError() {
        return this.sslProviderEnablementError;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "status", imports = {}))
    public final Status status() {
        return this.status;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "storageError", imports = {}))
    public final boolean storageError() {
        return this.storageError;
    }

    public String toString() {
        return "InitializationStatus(status=" + this.status + ", unrecoverableException=" + this.unrecoverableException + ", locationsError=" + this.locationsError + ", playServicesStatus=" + this.playServicesStatus + ", playServicesMessage=" + this.playServicesMessage + ", encryptionChanged=" + this.encryptionChanged + ", storageError=" + this.storageError + ", proximityError=" + this.proximityError + ", messagingPermissionError=" + this.messagingPermissionError + ", sslProviderEnablementError=" + this.sslProviderEnablementError + ", initializedComponents=" + this.initializedComponents + ", isUsable=" + this.isUsable + ")";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "unrecoverableException", imports = {}))
    public final Throwable unrecoverableException() {
        return this.unrecoverableException;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ InitializationStatus(com.salesforce.marketingcloud.InitializationStatus.Status r15, java.lang.Throwable r16, boolean r17, int r18, java.lang.String r19, boolean r20, boolean r21, boolean r22, boolean r23, boolean r24, java.util.List r25, boolean r26, int r27, kotlin.jvm.internal.DefaultConstructorMarker r28) {
        /*
            r14 = this;
            r0 = r27
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L10
            com.salesforce.marketingcloud.InitializationStatus$Status r0 = com.salesforce.marketingcloud.InitializationStatus.Status.FAILED
            r2 = r15
            if (r2 == r0) goto Ld
            r0 = 1
            goto Le
        Ld:
            r0 = 0
        Le:
            r13 = r0
            goto L13
        L10:
            r2 = r15
            r13 = r26
        L13:
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = r25
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.InitializationStatus.<init>(com.salesforce.marketingcloud.InitializationStatus$Status, java.lang.Throwable, boolean, int, java.lang.String, boolean, boolean, boolean, boolean, boolean, java.util.List, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
