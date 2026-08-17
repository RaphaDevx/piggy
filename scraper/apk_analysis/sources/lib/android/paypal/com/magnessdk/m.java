package lib.android.paypal.com.magnessdk;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes4.dex */
final class m {
    private static final Object b = new Object();
    private static m c;
    private ThreadPoolExecutor a;

    private m() {
        try {
            this.a = new ThreadPoolExecutor(10, 10, DateUtils.MILLIS_PER_MINUTE, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(256), new ThreadPoolExecutor.DiscardPolicy());
        } catch (Exception unused) {
        }
    }

    static m a() {
        m mVar;
        synchronized (b) {
            if (c == null) {
                c = new m();
            }
            mVar = c;
        }
        return mVar;
    }

    void a(l lVar) {
        this.a.execute(lVar);
    }
}
