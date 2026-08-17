package com.salesforce.marketingcloud.media;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.salesforce.marketingcloud.media.t;
import com.salesforce.marketingcloud.media.v;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: classes2.dex */
public class h {
    static final int h = 1;
    static final int i = 2;
    static final int j = 3;
    static final int k = 4;
    static final int l = 5;
    static final int m = 6;
    private static final String n = com.salesforce.marketingcloud.g.a("Dispatcher");
    final Map<String, n> a;
    final b b;
    final Context c;
    final ExecutorService d;
    final Handler e;
    final Handler f;
    final c g;

    private static class a extends Handler {
        private final h a;

        a(Looper looper, h hVar) {
            super(looper);
            this.a = hVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.d((com.salesforce.marketingcloud.media.a) message.obj);
                    break;
                case 2:
                    this.a.d((n) message.obj);
                    break;
                case 3:
                    this.a.e((n) message.obj);
                    break;
                case 4:
                    this.a.b((d) message.obj);
                    break;
                case 5:
                    this.a.b((e) message.obj);
                    break;
                case 6:
                    this.a.c((com.salesforce.marketingcloud.media.a) message.obj);
                    break;
            }
        }
    }

    static class b extends HandlerThread {
        public b() {
            super("mcsdk_image_thread", 10);
        }
    }

    h(Context context, ExecutorService executorService, Handler handler, c cVar) {
        b bVar = new b();
        this.b = bVar;
        bVar.start();
        this.c = context;
        this.d = executorService;
        this.a = new LinkedHashMap();
        this.e = new a(bVar.getLooper(), this);
        this.f = handler;
        this.g = cVar;
    }

    private void a(n nVar) {
        if (nVar.j()) {
            return;
        }
        v.b i2 = nVar.i();
        if (i2 != null && i2.d()) {
            i2.a().prepareToDraw();
        }
        Handler handler = this.f;
        handler.sendMessage(handler.obtainMessage(2, nVar));
    }

    void b(e eVar) {
        Handler handler = this.f;
        handler.sendMessage(handler.obtainMessage(5, eVar));
    }

    void c(com.salesforce.marketingcloud.media.a aVar) {
        String c = aVar.c();
        n nVar = this.a.get(c);
        if (nVar != null) {
            nVar.b(aVar);
            if (nVar.a()) {
                this.a.remove(c);
            }
        }
    }

    void d(com.salesforce.marketingcloud.media.a aVar) {
        n nVar = this.a.get(aVar.c());
        if (nVar != null) {
            nVar.a(aVar);
        } else {
            if (this.d.isShutdown()) {
                com.salesforce.marketingcloud.g.a(n, "ExecutorService is shutdown.  Ignoring request.", new Object[0]);
                return;
            }
            n a2 = n.a(aVar.b(), this, this.g, aVar);
            a2.k = this.d.submit(a2);
            this.a.put(aVar.c(), a2);
        }
    }

    void e(n nVar) {
        this.a.remove(nVar.h());
        a(nVar);
    }

    void b(d dVar) {
        if (this.d.isShutdown()) {
            com.salesforce.marketingcloud.g.a(n, "ExecutorService is shutdown.  Ignoring request.", new Object[0]);
        } else {
            this.d.submit(new e(this, dVar));
        }
    }

    public void c(n nVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(3, nVar));
    }

    public void b(com.salesforce.marketingcloud.media.a aVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(1, aVar));
    }

    public void b(n nVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(2, nVar));
    }

    public void a(e eVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(5, eVar));
    }

    public void a(d dVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(4, dVar));
    }

    public void a(com.salesforce.marketingcloud.media.a aVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(6, aVar));
    }

    void d(n nVar) {
        if (t.b.b(nVar.g.d)) {
            v.b bVar = nVar.j;
            if (bVar.d()) {
                this.g.a(nVar.h(), bVar.a());
            }
        }
        this.a.remove(nVar.h());
        a(nVar);
    }
}
