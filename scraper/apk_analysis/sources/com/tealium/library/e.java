package com.tealium.library;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.tealium.internal.j.n;
import com.tealium.internal.listeners.BackgroundListener;
import com.tealium.internal.listeners.MainListener;
import java.util.Collection;
import java.util.EventListener;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: MessageRouterFactory.java */
/* loaded from: classes2.dex */
final class e {
    private static ScheduledExecutorService a;

    /* compiled from: MessageRouterFactory.java */
    class a implements com.tealium.internal.d {
        final /* synthetic */ com.tealium.internal.c e;
        private final ScheduledExecutorService d = e.a;
        private final Collection<BackgroundListener> b = new ConcurrentLinkedQueue();
        private final Collection<MainListener> c = new ConcurrentLinkedQueue();
        private final Handler a = new Handler(Looper.getMainLooper());

        /* compiled from: MessageRouterFactory.java */
        /* renamed from: com.tealium.library.e$a$a, reason: collision with other inner class name */
        class RunnableC0104a implements Runnable {
            final /* synthetic */ Runnable a;

            RunnableC0104a(Runnable runnable) {
                this.a = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a(this.a);
            }
        }

        /* compiled from: MessageRouterFactory.java */
        class b implements Runnable {
            final /* synthetic */ n a;

            b(n nVar) {
                this.a = nVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.c(this.a);
            }
        }

        /* compiled from: MessageRouterFactory.java */
        class c implements Runnable {
            final /* synthetic */ n a;

            c(n nVar) {
                this.a = nVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Class a = this.a.a();
                    for (BackgroundListener backgroundListener : a.this.b) {
                        if (a.isInstance(backgroundListener)) {
                            this.a.a((EventListener) a.cast(backgroundListener));
                        }
                    }
                } catch (Throwable th) {
                    a.this.e.a(th);
                }
            }
        }

        a(com.tealium.internal.c cVar) {
            this.e = cVar;
        }

        @Override // com.tealium.internal.d
        public void b(EventListener eventListener) {
            this.c.remove(eventListener);
            this.b.remove(eventListener);
        }

        @Override // com.tealium.internal.d
        public void c(Runnable runnable) {
            this.d.submit(runnable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T extends MainListener> void c(n<T> nVar) {
            Class<T> a = nVar.a();
            for (MainListener mainListener : this.c) {
                if (a.isInstance(mainListener)) {
                    nVar.a(a.cast(mainListener));
                }
            }
        }

        @Override // com.tealium.internal.d
        public void a(EventListener eventListener) {
            boolean z;
            boolean z2 = true;
            if (eventListener instanceof MainListener) {
                this.c.add((MainListener) eventListener);
                z = true;
            } else {
                z = false;
            }
            if (eventListener instanceof BackgroundListener) {
                this.b.add((BackgroundListener) eventListener);
            } else {
                z2 = z;
            }
            if (!z2) {
                throw new IllegalArgumentException();
            }
        }

        @Override // com.tealium.internal.d
        public void b(Runnable runnable) {
            this.a.post(runnable);
        }

        @Override // com.tealium.internal.d
        public void b(Runnable runnable, long j) {
            this.a.postDelayed(runnable, j);
        }

        @Override // com.tealium.internal.d
        public <T extends BackgroundListener> void b(n<T> nVar) {
            if (nVar != null) {
                this.d.submit(new c(nVar));
                return;
            }
            throw new IllegalArgumentException();
        }

        @Override // com.tealium.internal.d
        public void a(Runnable runnable) {
            try {
                AsyncTask.THREAD_POOL_EXECUTOR.execute(runnable);
            } catch (RejectedExecutionException e) {
                try {
                    AsyncTask.SERIAL_EXECUTOR.execute(runnable);
                } catch (RejectedExecutionException unused) {
                    Log.e(BuildConfig.TAG, e.getMessage());
                }
            }
        }

        @Override // com.tealium.internal.d
        public void a(Runnable runnable, long j) {
            this.a.postDelayed(new RunnableC0104a(runnable), j);
        }

        @Override // com.tealium.internal.d
        public <T extends MainListener> void a(n<T> nVar) {
            if (nVar != null) {
                if (com.tealium.internal.g.c()) {
                    c(nVar);
                    return;
                } else {
                    this.a.post(new b(nVar));
                    return;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    static synchronized com.tealium.internal.d a(com.tealium.internal.c cVar) {
        a aVar;
        synchronized (e.class) {
            if (a == null) {
                a = Executors.newSingleThreadScheduledExecutor();
            }
            aVar = new a(cVar);
        }
        return aVar;
    }
}
