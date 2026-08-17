package com.salesforce.marketingcloud.push;

import android.content.Context;
import android.widget.RemoteViews;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.push.data.Template;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public interface k<T extends Template> {

    public static final class a {
        public static final C0088a a = new C0088a(null);

        /* renamed from: com.salesforce.marketingcloud.push.k$a$a, reason: collision with other inner class name */
        public static final class C0088a {

            /* renamed from: com.salesforce.marketingcloud.push.k$a$a$a, reason: collision with other inner class name */
            public /* synthetic */ class C0089a {
                public static final /* synthetic */ int[] a;

                static {
                    int[] iArr = new int[Template.Type.values().length];
                    try {
                        iArr[Template.Type.CarouselFull.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[Template.Type.RichButtons.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    a = iArr;
                }
            }

            public /* synthetic */ C0088a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public static /* synthetic */ k a(C0088a c0088a, Template.Type type, Context context, NotificationMessage notificationMessage, o oVar, int i, Object obj) {
                if ((i & 8) != 0) {
                    oVar = null;
                }
                return c0088a.a(type, context, notificationMessage, oVar);
            }

            private C0088a() {
            }

            public final k<Template> a(Template.Type type, Context context, NotificationMessage message, o oVar) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(message, "message");
                int i = C0089a.a[type.ordinal()];
                if (i == 1) {
                    return new com.salesforce.marketingcloud.push.carousel.d(new com.salesforce.marketingcloud.push.carousel.b(context, message), context, oVar);
                }
                if (i == 2) {
                    return new com.salesforce.marketingcloud.push.buttons.c(context, new b(context, message));
                }
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    RemoteViews a(RemoteViews remoteViews, T t);
}
