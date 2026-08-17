package com.salesforce.marketingcloud.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import androidx.core.internal.view.SupportMenu;
import com.salesforce.marketingcloud.media.v;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
public class o {
    static final Handler i = new a(Looper.getMainLooper());
    final Context a;
    final h b;
    final com.salesforce.marketingcloud.media.c c;
    final s d;
    final Map<ImageView, g> e;
    final Map<Object, com.salesforce.marketingcloud.media.a> f;
    private final List<v> g;
    public ReferenceQueue h;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                n nVar = (n) message.obj;
                nVar.b.a(nVar);
            } else {
                if (i != 5) {
                    return;
                }
                e eVar = (e) message.obj;
                eVar.b.a(eVar);
            }
        }
    }

    public enum b {
        MEMORY(-16711936),
        DISK(-16776961),
        NETWORK(SupportMenu.CATEGORY_MASK);

        final int b;

        b(int i) {
            this.b = i;
        }
    }

    public enum c {
        NORMAL,
        HIGH
    }

    public o(Context context, h hVar, com.salesforce.marketingcloud.media.c cVar, s sVar) {
        this.a = context;
        this.b = hVar;
        this.c = cVar;
        this.d = sVar;
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new r(sVar));
        arrayList.add(new i(context));
        this.g = Collections.unmodifiableList(arrayList);
        this.f = new WeakHashMap();
        this.e = new WeakHashMap();
    }

    public static o a(Context context, com.salesforce.marketingcloud.storage.h hVar) {
        com.salesforce.marketingcloud.media.c cVar = new com.salesforce.marketingcloud.media.c(context);
        return new o(context, new h(context, new m(), i, cVar), cVar, new s(hVar.f()));
    }

    public u b(String str) {
        return new u(this, Uri.parse(str));
    }

    public com.salesforce.marketingcloud.media.b a(List<String> list) {
        return new com.salesforce.marketingcloud.media.b(this, new ArrayList(list));
    }

    public void a(Collection<String> collection) {
        a(collection, (f) null);
    }

    public void a(Collection<String> collection, f fVar) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        this.b.a(new d(this, new ArrayList(collection), this.d, fVar));
    }

    public void a(com.salesforce.marketingcloud.media.a aVar) {
        Object f = aVar.f();
        if (f != null && this.f.get(f) != aVar) {
            a(f);
            this.f.put(f, aVar);
        }
        this.b.b(aVar);
    }

    List<v> a() {
        return this.g;
    }

    public void a(n nVar) {
        com.salesforce.marketingcloud.media.a c2 = nVar.c();
        List<com.salesforce.marketingcloud.media.a> d = nVar.d();
        boolean z = (d == null || d.isEmpty()) ? false : true;
        if (c2 != null || z) {
            Exception f = nVar.f();
            v.b i2 = nVar.i();
            if (c2 != null) {
                a(c2, i2, f);
            }
            if (z) {
                int size = d.size();
                for (int i3 = 0; i3 < size; i3++) {
                    a(d.get(i3), i2, f);
                }
            }
        }
    }

    public void a(e eVar) {
        f b2 = eVar.c.b();
        if (b2 != null) {
            if (eVar.b()) {
                b2.a(eVar.a());
            } else {
                b2.a();
            }
        }
    }

    public void a(ImageView imageView, g gVar) {
        if (this.e.containsKey(imageView)) {
            a(imageView);
        }
        this.e.put(imageView, gVar);
    }

    void a(Object obj) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            com.salesforce.marketingcloud.media.a remove = this.f.remove(obj);
            if (remove != null) {
                remove.a();
                this.b.a(remove);
            }
            if (obj instanceof ImageView) {
                g remove2 = this.e.remove((ImageView) obj);
                if (remove2 != null) {
                    remove2.a();
                }
            }
        }
    }

    public Bitmap a(String str) {
        return this.c.a(str);
    }

    public void a(String str, Bitmap bitmap) {
        this.c.a(str, bitmap);
    }

    private void a(com.salesforce.marketingcloud.media.a aVar, v.b bVar, Exception exc) {
        if (aVar.g()) {
            return;
        }
        this.f.remove(aVar.f());
        if (bVar != null) {
            aVar.a(bVar);
        } else {
            aVar.a(exc);
        }
    }
}
