package com.salesforce.marketingcloud.alarms;

import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes2.dex */
public abstract class a {
    private final String a;
    private final long b;
    private final double c;
    private final long d;
    private final String e;
    private final int f;
    private final boolean g;

    private static final class b extends a {
        b(int i) {
            this(i, "et_etanalytic_alarm_created_date", "et_etanalytic_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, 86400000L, true);
        }

        private b(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    private static final class c extends a {
        c(int i, long j) {
            super(i, "et_delivery_receipt_alarm_created_date", "et_delivery_receipt_alarm_interval", j, 1.0d, j, false);
        }
    }

    private static final class d extends a {
        d(int i) {
            super(i, "et_device_stats_alarm_created_date", "et_device_stats_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, 86400000L, false);
        }
    }

    private static final class e extends a {
        e(int i) {
            super(i, "et_events_alarm_created_date", "et_events_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, 86400000L, false);
        }
    }

    private static final class f extends a {
        f(int i) {
            super(i, "et_iam_image_cache_route_alarm_created_date", "et_iam_image_cache_route_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, 86400000L, false);
        }
    }

    private static final class g extends a {
        g(int i) {
            this(i, "et_fetch_background_beacon_messages_alarm_created_date", "et_fetch_background_beacon_messages_next_alarm_interval", 86400000L, 1.0d, 86400000L, false);
        }

        private g(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    private static final class h extends a {
        h(int i) {
            super(i, "et_sync_route_alarm_created_date", "et_sync_route_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, 86400000L, false);
        }
    }

    private static final class i extends a {
        i(int i) {
            this(i, "et_register_for_remote_notifications_alarm_created_date", "et_register_for_remote_notifications_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, 86400000L, false);
        }

        private i(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    private static final class j extends a {
        j(int i) {
            this(i, "et_registration_alarm_created_date", "et_registration_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, 86400000L, false);
        }

        private j(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    private static final class k extends a {
        k(int i) {
            this(i, "et_update_inbox_message_status_alarm_created_date", "et_update_inbox_message_status_next_alarm_interval", DateUtils.MILLIS_PER_MINUTE, 2.0d, 86400000L, true);
        }

        private k(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    a(int i2, String str, String str2, long j2, double d2, long j3, boolean z) {
        this.f = i2;
        this.e = str;
        this.a = str2;
        this.b = j2;
        this.c = d2;
        this.d = j3;
        this.g = z;
    }

    final String a() {
        return this.e;
    }

    final int b() {
        return this.f;
    }

    final String c() {
        return this.a;
    }

    final long d() {
        return this.b;
    }

    final double e() {
        return this.c;
    }

    final long f() {
        return this.d;
    }

    final boolean g() {
        return this.g;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* renamed from: com.salesforce.marketingcloud.alarms.a$a, reason: collision with other inner class name */
    public static abstract class EnumC0042a {
        public static final EnumC0042a c;
        public static final EnumC0042a d;
        public static final EnumC0042a e;
        public static final EnumC0042a f;
        public static final EnumC0042a g;
        public static final EnumC0042a h;
        public static final EnumC0042a i;
        public static final EnumC0042a j;
        public static final EnumC0042a k;
        public static final EnumC0042a l;
        private static final /* synthetic */ EnumC0042a[] m = a();
        private final int b;

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$a, reason: collision with other inner class name */
        enum C0043a extends EnumC0042a {
            private long n;

            private C0043a(String str, int i, int i2) {
                super(str, i, i2);
                this.n = 10000L;
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            public void a(long j) {
                this.n = j;
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new c(c(), this.n);
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$b */
        enum b extends EnumC0042a {
            private b(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new j(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$c */
        enum c extends EnumC0042a {
            private c(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new b(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$d */
        enum d extends EnumC0042a {
            private d(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new g(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$e */
        enum e extends EnumC0042a {
            private e(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new i(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$f */
        enum f extends EnumC0042a {
            private f(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new k(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$g */
        enum g extends EnumC0042a {
            private g(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new h(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$h */
        enum h extends EnumC0042a {
            private h(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new f(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$i */
        enum i extends EnumC0042a {
            private i(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new d(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$j */
        enum j extends EnumC0042a {
            private j(String str, int i, int i2) {
                super(str, i, i2);
            }

            @Override // com.salesforce.marketingcloud.alarms.a.EnumC0042a
            protected a b() {
                return new e(c());
            }
        }

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$k */
        static class k {
            static final int a = 909116;
            static final int b = 909115;
            static final int c = 909114;
            static final int d = 909113;
            static final int e = 909112;
            static final int f = 909110;
            static final int g = 909109;
            static final int h = 909108;
            static final int i = 909111;
            static final int j = 909102;
            static final int k = 909100;

            k() {
            }
        }

        static {
            c = new b("REGISTRATION", 0, 909100);
            d = new c("ET_ANALYTICS", 1, 909102);
            e = new d("FETCH_REGION_MESSAGES_DAILY", 2, 909111);
            f = new e("FETCH_PUSH_TOKEN", 3, 909108);
            g = new f("UPDATE_INBOX_MESSAGE_STATUS", 4, 909110);
            h = new g("SYNC", 5, 909112);
            i = new h("IAM_IMAGE_BATCH", 6, 909113);
            j = new i("DEVICE_STATS", 7, 909114);
            k = new j("EVENTS", 8, 909115);
            l = new C0043a("DELIVERY_RECEIPT", 9, 909116);
        }

        private EnumC0042a(String str, int i2, int i3) {
            this.b = i3;
        }

        public static EnumC0042a valueOf(String str) {
            return (EnumC0042a) Enum.valueOf(EnumC0042a.class, str);
        }

        public static EnumC0042a[] values() {
            return (EnumC0042a[]) m.clone();
        }

        public void a(long j2) {
        }

        @Deprecated
        protected boolean a(com.salesforce.marketingcloud.storage.h hVar) {
            return true;
        }

        protected abstract a b();

        public int c() {
            return this.b;
        }

        private static /* synthetic */ EnumC0042a[] a() {
            return new EnumC0042a[]{c, d, e, f, g, h, i, j, k, l};
        }
    }
}
