package com.salesforce.marketingcloud.push.style;

import android.content.Context;
import androidx.core.graphics.ColorUtils;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.push.data.Style;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public interface a<T> {
    public static final C0090a a = C0090a.a;
    public static final float b = 3.0f;
    public static final String c = "#333333";

    /* renamed from: com.salesforce.marketingcloud.push.style.a$a, reason: collision with other inner class name */
    public static final class C0090a {
        public static final float b = 3.0f;
        public static final String c = "#333333";
        static final /* synthetic */ C0090a a = new C0090a();
        private static final String d = g.a("ViewStyler");

        private C0090a() {
        }

        public final String a() {
            return d;
        }
    }

    public static final class b implements a<com.salesforce.marketingcloud.push.data.c> {
        private final Context d;

        /* renamed from: com.salesforce.marketingcloud.push.style.a$b$a, reason: collision with other inner class name */
        public /* synthetic */ class C0091a {
            public static final /* synthetic */ int[] a;

            static {
                int[] iArr = new int[Style.FontStyle.values().length];
                try {
                    iArr[Style.FontStyle.B.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Style.FontStyle.I.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Style.FontStyle.R.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                a = iArr;
            }
        }

        /* renamed from: com.salesforce.marketingcloud.push.style.a$b$b, reason: collision with other inner class name */
        static final class C0092b extends Lambda implements Function0<String> {
            public static final C0092b b = new C0092b();

            C0092b() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return "ignore fontColor in dark mode as it fails contrast checks";
            }
        }

        static final class c extends Lambda implements Function0<String> {
            public static final c b = new c();

            c() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return "Failed to apply style to text.";
            }
        }

        public b(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.d = context;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0080  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0090 A[Catch: Exception -> 0x00c8, TryCatch #0 {Exception -> 0x00c8, blocks: (B:3:0x0013, B:5:0x001a, B:7:0x0020, B:9:0x002a, B:11:0x003a, B:12:0x0047, B:13:0x0058, B:14:0x0064, B:16:0x006a, B:20:0x0074, B:24:0x009c, B:26:0x00a2, B:28:0x00a8, B:38:0x0083, B:39:0x0090), top: B:2:0x0013 }] */
        @Override // com.salesforce.marketingcloud.push.style.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.salesforce.marketingcloud.push.data.c a(com.salesforce.marketingcloud.push.data.c r14, com.salesforce.marketingcloud.push.data.Style.FontStyle r15) {
            /*
                Method dump skipped, instructions count: 261
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.push.style.a.b.a(com.salesforce.marketingcloud.push.data.c, com.salesforce.marketingcloud.push.data.Style$FontStyle):com.salesforce.marketingcloud.push.data.c");
        }

        private final boolean a() {
            return (this.d.getResources().getConfiguration().uiMode & 48) == 32;
        }

        private final double a(int i, int i2) {
            double calculateLuminance = ColorUtils.calculateLuminance(i);
            double calculateLuminance2 = ColorUtils.calculateLuminance(i2);
            return calculateLuminance > calculateLuminance2 ? (calculateLuminance + 0.05d) / (calculateLuminance2 + 0.05d) : (calculateLuminance2 + 0.05d) / (calculateLuminance + 0.05d);
        }
    }

    static /* synthetic */ Object a(a aVar, Object obj, Style.FontStyle fontStyle, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: apply");
        }
        if ((i & 2) != 0) {
            fontStyle = Style.FontStyle.R;
        }
        return aVar.a(obj, fontStyle);
    }

    T a(T t, Style.FontStyle fontStyle);
}
