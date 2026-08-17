package com.salesforce.marketingcloud.media;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.t;
import com.salesforce.marketingcloud.media.v;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public class n implements Runnable {
    private static final String n = "ImageHandler-";
    private static final String o = "ImageHandler-Idle";
    private static final ThreadLocal<StringBuilder> p = new a();
    private static final v q = new b();
    final o b;
    final h c;
    final String d;
    final v e;
    final com.salesforce.marketingcloud.media.c f;
    t g;
    com.salesforce.marketingcloud.media.a h;
    List<com.salesforce.marketingcloud.media.a> i;
    v.b j;
    Future<?> k;
    Exception l;
    o.c m;

    class a extends ThreadLocal<StringBuilder> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public StringBuilder initialValue() {
            return new StringBuilder(n.n);
        }
    }

    class b extends v {
        b() {
        }

        @Override // com.salesforce.marketingcloud.media.v
        public boolean a(t tVar) {
            return true;
        }

        @Override // com.salesforce.marketingcloud.media.v
        public void a(o oVar, t tVar, v.a aVar) throws IOException {
            aVar.a(new k(tVar));
        }
    }

    n(o oVar, h hVar, com.salesforce.marketingcloud.media.c cVar, com.salesforce.marketingcloud.media.a aVar, v vVar) {
        this.b = oVar;
        this.c = hVar;
        this.f = cVar;
        this.h = aVar;
        this.d = aVar.c();
        this.g = aVar.e();
        this.e = vVar;
        this.m = aVar.d();
    }

    static n a(o oVar, h hVar, com.salesforce.marketingcloud.media.c cVar, com.salesforce.marketingcloud.media.a aVar) {
        t e = aVar.e();
        List<v> a2 = oVar.a();
        int size = a2.size();
        for (int i = 0; i < size; i++) {
            v vVar = a2.get(i);
            if (vVar.a(e)) {
                return new n(oVar, hVar, cVar, aVar, vVar);
            }
        }
        return new n(oVar, hVar, cVar, aVar, q);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static android.graphics.Bitmap b(com.salesforce.marketingcloud.media.t r11, android.graphics.Bitmap r12) {
        /*
            Method dump skipped, instructions count: 175
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.media.n.b(com.salesforce.marketingcloud.media.t, android.graphics.Bitmap):android.graphics.Bitmap");
    }

    public com.salesforce.marketingcloud.media.a c() {
        return this.h;
    }

    public List<com.salesforce.marketingcloud.media.a> d() {
        return this.i;
    }

    public t e() {
        return this.g;
    }

    public Exception f() {
        return this.l;
    }

    public o g() {
        return this.b;
    }

    public String h() {
        return this.d;
    }

    public v.b i() {
        return this.j;
    }

    boolean j() {
        Future<?> future = this.k;
        return future != null && future.isCancelled();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                a(this.g);
                v.b b2 = b();
                this.j = b2;
                if (b2.d()) {
                    com.salesforce.marketingcloud.g.a("IMAGE", "onSuccess - Loaded from: %s", this.j.c());
                    this.c.b(this);
                } else {
                    this.c.c(this);
                }
            } catch (Exception e) {
                this.l = e;
                this.c.c(this);
            }
        } finally {
            Thread.currentThread().setName(o);
        }
    }

    class c implements v.a {
        final /* synthetic */ AtomicReference a;
        final /* synthetic */ CountDownLatch b;
        final /* synthetic */ AtomicReference c;

        c(AtomicReference atomicReference, CountDownLatch countDownLatch, AtomicReference atomicReference2) {
            this.a = atomicReference;
            this.b = countDownLatch;
            this.c = atomicReference2;
        }

        @Override // com.salesforce.marketingcloud.media.v.a
        public void a(v.b bVar) {
            this.a.set(bVar);
            this.b.countDown();
        }

        @Override // com.salesforce.marketingcloud.media.v.a
        public void a(Throwable th) {
            this.c.set(th);
            this.b.countDown();
        }
    }

    static void a(t tVar) {
        String b2 = tVar.b();
        StringBuilder sb = p.get();
        sb.ensureCapacity(b2.length() + 13);
        sb.replace(13, sb.length(), b2);
        Thread.currentThread().setName(sb.toString());
    }

    static Bitmap a(t tVar, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = tVar.i;
        float f2 = tVar.j;
        float f3 = width;
        float f4 = height;
        RectF rectF = new RectF(0.0f, 0.0f, f3, f4);
        RectF rectF2 = new RectF(0.0f, 0.0f, f3, f4);
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        paint.setAntiAlias(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        if (f2 > 0.0f) {
            paint2.setStrokeWidth(f2);
            paint2.setColor(tVar.k);
            float f5 = f2 / 2.0f;
            rectF2.inset(f5, f5);
            float floor = (float) Math.floor(f5);
            rectF.inset(floor, floor);
        }
        if (f > 0.0f) {
            canvas.drawRoundRect(rectF, f, f, paint);
            if (f2 > 0.0f) {
                canvas.drawRoundRect(rectF2, f, f, paint2);
            }
        } else {
            canvas.drawRect(rectF, paint);
            if (f2 > 0.0f) {
                canvas.drawRect(rectF2, paint2);
            }
        }
        if (bitmap == createBitmap) {
            return bitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    v.b b() throws IOException {
        Bitmap a2;
        if (t.b.a(this.g.d) && (a2 = this.f.a(this.d)) != null) {
            return new v.b(a2, o.b.MEMORY);
        }
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            this.e.a(this.b, this.g, new c(atomicReference, countDownLatch, atomicReference2));
            countDownLatch.await();
            Throwable th = (Throwable) atomicReference2.get();
            if (th == null) {
                v.b bVar = (v.b) atomicReference.get();
                if (!bVar.d()) {
                    return bVar;
                }
                Bitmap a3 = bVar.a();
                if (!this.g.e()) {
                    return bVar;
                }
                if (this.g.d()) {
                    a3 = b(this.g, a3);
                }
                if (this.g.c()) {
                    a3 = a(this.g, a3);
                }
                return new v.b(a3, bVar.c());
            }
            throw new RuntimeException(th);
        } catch (InterruptedException e) {
            throw new InterruptedIOException(e.getMessage());
        }
    }

    boolean a() {
        Future<?> future;
        if (this.h != null) {
            return false;
        }
        List<com.salesforce.marketingcloud.media.a> list = this.i;
        return (list == null || list.isEmpty()) && (future = this.k) != null && future.cancel(false);
    }

    public void a(com.salesforce.marketingcloud.media.a aVar) {
        if (this.h == null) {
            this.h = aVar;
            return;
        }
        if (this.i == null) {
            this.i = new ArrayList();
        }
        this.i.add(aVar);
        o.c d = aVar.d();
        if (d.ordinal() > this.m.ordinal()) {
            this.m = d;
        }
    }

    public void b(com.salesforce.marketingcloud.media.a aVar) {
        if (this.h == aVar) {
            this.h = null;
            return;
        }
        List<com.salesforce.marketingcloud.media.a> list = this.i;
        if (list != null) {
            list.remove(aVar);
        }
    }
}
