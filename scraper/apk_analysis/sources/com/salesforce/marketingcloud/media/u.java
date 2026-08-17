package com.salesforce.marketingcloud.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Looper;
import android.widget.ImageView;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.t;
import com.salesforce.marketingcloud.media.v;

/* loaded from: classes2.dex */
public class u {
    private final o a;
    private final t.a b;
    private boolean c;

    public u(o oVar, Uri uri) {
        this.a = oVar;
        this.b = new t.a(uri);
    }

    public u a(o.c cVar) {
        this.b.a(cVar);
        return this;
    }

    public u b() {
        this.b.c();
        return this;
    }

    public void c() {
        a((f) null);
    }

    public u d() {
        this.c = true;
        return this;
    }

    public u a(t.b bVar, t.b... bVarArr) {
        this.b.a(bVar, bVarArr);
        return this;
    }

    public u a(float f, float f2, int i) {
        this.b.a(f, f2, i);
        return this;
    }

    public u a() {
        this.b.b();
        return this;
    }

    public u a(int i, int i2) {
        this.b.a(i, i2);
        return this;
    }

    public void a(f fVar) {
        long nanoTime = System.nanoTime();
        if (!this.b.d()) {
            this.b.a(o.c.NORMAL);
        }
        t a = a(nanoTime);
        if (t.b.a(a.d) && this.a.a(a.b) != null) {
            com.salesforce.marketingcloud.g.a("IMAGE", "onSuccess - Loaded from: MEMORY", new Object[0]);
            if (fVar != null) {
                fVar.a();
                return;
            }
            return;
        }
        this.a.a((a) new j(this.a, a, fVar));
    }

    public void a(ImageView imageView) {
        a(imageView, (f) null);
    }

    public void a(ImageView imageView, f fVar) {
        Bitmap a;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            if (!this.b.d()) {
                this.b.a(o.c.HIGH);
            }
            if (this.c) {
                int width = imageView.getWidth();
                int height = imageView.getHeight();
                if (width != 0 && height != 0) {
                    this.b.a(width, height);
                } else {
                    this.a.a(imageView, new g(this, imageView, fVar));
                    return;
                }
            }
            t a2 = a(System.nanoTime());
            if (t.b.a(a2.d) && (a = this.a.a(a2.b)) != null) {
                v.b bVar = new v.b(a, o.b.MEMORY);
                l.a(imageView, this.a.a, bVar);
                com.salesforce.marketingcloud.g.a("IMAGE", "onSuccess - Loaded from: %s", bVar.c());
                if (fVar != null) {
                    fVar.a();
                    return;
                }
                return;
            }
            this.a.a((a) new p(this.a, new w(imageView), a2, fVar));
            return;
        }
        throw new IllegalStateException("TODO");
    }

    private t a(long j) {
        t a = this.b.a();
        a.l = j;
        return a;
    }
}
