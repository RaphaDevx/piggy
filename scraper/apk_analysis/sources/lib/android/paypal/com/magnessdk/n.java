package lib.android.paypal.com.magnessdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import lib.android.paypal.com.magnessdk.g;

/* loaded from: classes4.dex */
public final class n extends Handler {
    private static n b = null;
    private static final String c = "GET request to ";
    private static final String d = "POST request to ";
    private WeakReference<MagnesSDK> a;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.h.c.values().length];
            a = iArr;
            try {
                iArr[g.h.c.GET_REQUEST_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.h.c.GET_REQUEST_SUCCEEDED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.h.c.GET_REQUEST_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[g.h.c.POST_REQUEST_STARTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[g.h.c.POST_REQUEST_SUCCEEDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[g.h.c.POST_REQUEST_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private n(Looper looper, MagnesSDK magnesSDK) {
        super(looper);
        this.a = new WeakReference<>(magnesSDK);
    }

    public static synchronized n a(Looper looper, MagnesSDK magnesSDK) {
        n nVar;
        synchronized (n.class) {
            if (b == null) {
                b = new n(looper, magnesSDK);
            }
            nVar = b;
        }
        return nVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        g.h.c a2;
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        if (this.a.get() == null || (a2 = g.h.c.a(message.what)) == null) {
        }
        switch (a.a[a2.ordinal()]) {
            case 1:
                e.a((Class<?>) n.class, 0, c + g.h.d.RAMP_CONFIG_URL.toString());
            case 2:
                sb = new StringBuilder(c);
                sb.append(message.obj);
                str = " succeeded";
                sb.append(str);
                e.a((Class<?>) n.class, 0, sb.toString());
                break;
            case 3:
                sb2 = new StringBuilder(c);
                sb2.append(message.obj);
                sb2.append(" error.");
                e.a((Class<?>) n.class, 3, sb2.toString());
                break;
            case 4:
                sb = new StringBuilder(d);
                sb.append(message.obj);
                str = " started.";
                sb.append(str);
                e.a((Class<?>) n.class, 0, sb.toString());
                break;
            case 5:
                sb = new StringBuilder(d);
                sb.append(message.obj);
                str = " successfully.";
                sb.append(str);
                e.a((Class<?>) n.class, 0, sb.toString());
                break;
            case 6:
                sb2 = new StringBuilder(d);
                sb2.append(message.obj);
                sb2.append(" error.");
                e.a((Class<?>) n.class, 3, sb2.toString());
                break;
        }
    }
}
