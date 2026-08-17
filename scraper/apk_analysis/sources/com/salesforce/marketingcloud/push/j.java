package com.salesforce.marketingcloud.push;

import com.salesforce.marketingcloud.push.buttons.RichButtonsParser;
import com.salesforce.marketingcloud.push.carousel.CarouselParser;
import com.salesforce.marketingcloud.push.data.Template;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public interface j<T extends Template> {

    public static final class a {
        public static final C0086a a = new C0086a(null);

        /* renamed from: com.salesforce.marketingcloud.push.j$a$a, reason: collision with other inner class name */
        public static final class C0086a {

            /* renamed from: com.salesforce.marketingcloud.push.j$a$a$a, reason: collision with other inner class name */
            public /* synthetic */ class C0087a {
                public static final /* synthetic */ int[] a;

                static {
                    int[] iArr = new int[Template.Type.values().length];
                    try {
                        iArr[Template.Type.RichButtons.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[Template.Type.CarouselFull.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    a = iArr;
                }
            }

            public /* synthetic */ C0086a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final j<?> a(Template.Type type) {
                Intrinsics.checkNotNullParameter(type, "type");
                int i = C0087a.a[type.ordinal()];
                if (i == 1) {
                    return new RichButtonsParser();
                }
                if (i != 2) {
                    return null;
                }
                return new CarouselParser();
            }

            private C0086a() {
            }
        }
    }

    String hydrate(Template template);

    T parse(String str);
}
