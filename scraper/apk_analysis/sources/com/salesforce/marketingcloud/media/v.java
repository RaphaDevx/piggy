package com.salesforce.marketingcloud.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import com.salesforce.marketingcloud.media.o;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public abstract class v {

    public interface a {
        void a(b bVar);

        void a(Throwable th);
    }

    public static final class b {
        private final o.b a;
        private final Bitmap b;
        private final Drawable c;

        public b(Bitmap bitmap, o.b bVar) {
            this(bitmap, null, bVar);
        }

        public Bitmap a() {
            return this.b;
        }

        public Drawable b() {
            return this.c;
        }

        public o.b c() {
            return this.a;
        }

        public boolean d() {
            return this.b != null;
        }

        public boolean e() {
            return this.c != null;
        }

        public b(Drawable drawable, o.b bVar) {
            this(null, drawable, bVar);
        }

        private b(Bitmap bitmap, Drawable drawable, o.b bVar) {
            this.b = bitmap;
            this.c = drawable;
            this.a = bVar;
        }
    }

    private static void a(int i, int i2, BitmapFactory.Options options) {
        a(i, i2, options.outWidth, options.outHeight, options);
    }

    static BitmapFactory.Options b(t tVar) {
        if (!tVar.d()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        return options;
    }

    public abstract void a(o oVar, t tVar, a aVar) throws IOException;

    public abstract boolean a(t tVar);

    static void a(int i, int i2, int i3, int i4, BitmapFactory.Options options) {
        int min;
        double floor;
        if (i4 > i2 || i3 > i) {
            if (i2 == 0) {
                floor = Math.floor(i3 / i);
            } else if (i == 0) {
                floor = Math.floor(i4 / i2);
            } else {
                min = Math.min((int) Math.floor(i4 / i2), (int) Math.floor(i3 / i));
            }
            min = (int) floor;
        } else {
            min = 1;
        }
        options.inSampleSize = min;
        options.inJustDecodeBounds = false;
    }

    static boolean a(BitmapFactory.Options options) {
        return options != null && options.inJustDecodeBounds;
    }

    static Bitmap a(InputStream inputStream, t tVar) throws IOException {
        BitmapFactory.Options b2 = b(tVar);
        boolean a2 = a(b2);
        byte[] a3 = com.salesforce.marketingcloud.util.e.a(inputStream);
        if (a2) {
            BitmapFactory.decodeStream(new ByteArrayInputStream(a3), null, b2);
            a(tVar.e, tVar.f, b2);
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(a3), null, b2);
        if (decodeStream != null) {
            return decodeStream;
        }
        throw new IOException("Failed to decode bitmap");
    }
}
