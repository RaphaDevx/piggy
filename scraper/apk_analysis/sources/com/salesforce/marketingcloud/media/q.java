package com.salesforce.marketingcloud.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
public final class q {
    public static final q a = new q();
    private static final String b = com.salesforce.marketingcloud.g.a("MediaProvider");

    static final class a extends Lambda implements Function0<String> {
        final /* synthetic */ Bitmap b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Bitmap bitmap) {
            super(0);
            this.b = bitmap;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "scaleBitmapToFit default bitmap W:" + this.b.getWidth() + "  H:" + this.b.getHeight();
        }
    }

    static final class b extends Lambda implements Function0<String> {
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ float d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(int i, int i2, float f) {
            super(0);
            this.b = i;
            this.c = i2;
            this.d = f;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "scaleBitmapToFit Width target dimension targetWidth:" + this.b + " targetHeight:" + this.c + " for aspectRatio " + this.d;
        }
    }

    static final class c extends Lambda implements Function0<String> {
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ float d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(int i, int i2, float f) {
            super(0);
            this.b = i;
            this.c = i2;
            this.d = f;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "scaleBitmapToFit Height target dimension targetWidth:" + this.b + " targetHeight:" + this.c + " for aspectRatio " + this.d;
        }
    }

    private q() {
    }

    private final String b(String str) {
        if (str == null || StringsKt.lastIndexOf$default((CharSequence) str, ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 6, (Object) null) <= 0) {
            Intrinsics.checkNotNull(str);
            return str;
        }
        String substring = str.substring(0, StringsKt.lastIndexOf$default((CharSequence) str, ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 6, (Object) null));
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    public final Uri a(Context context, String name, Uri defaultSoundUri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(defaultSoundUri, "defaultSoundUri");
        int identifier = context.getResources().getIdentifier(b(name), "raw", context.getPackageName());
        if (identifier <= 0) {
            return defaultSoundUri;
        }
        Uri parse = Uri.parse("android.resource://" + context.getPackageName() + "/" + identifier);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
        return parse;
    }

    public final int a(Context context, String iconName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(iconName, "iconName");
        int identifier = context.getResources().getIdentifier(b(iconName), "drawable", context.getPackageName());
        return identifier > 0 ? identifier : context.getApplicationInfo().icon;
    }

    public final Bitmap a(Bitmap bitmap, int i, int i2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        com.salesforce.marketingcloud.g gVar = com.salesforce.marketingcloud.g.a;
        String str = b;
        com.salesforce.marketingcloud.g.a(gVar, str, null, new a(bitmap), 2, null);
        float width = bitmap.getWidth() / bitmap.getHeight();
        int i3 = (int) (i * width);
        if (i3 > i2) {
            int i4 = (int) (i2 / width);
            com.salesforce.marketingcloud.g.a(gVar, str, null, new b(i2, i4, width), 2, null);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i4, true);
            Intrinsics.checkNotNull(createScaledBitmap);
            return createScaledBitmap;
        }
        com.salesforce.marketingcloud.g.a(gVar, str, null, new c(i3, i, width), 2, null);
        Bitmap createScaledBitmap2 = Bitmap.createScaledBitmap(bitmap, i3, i, true);
        Intrinsics.checkNotNull(createScaledBitmap2);
        return createScaledBitmap2;
    }

    public final Bitmap a(String src) throws com.salesforce.marketingcloud.push.a {
        Intrinsics.checkNotNullParameter(src, "src");
        try {
            URLConnection openConnection = new URL(src).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setReadTimeout(8000);
            httpURLConnection.setConnectTimeout(8000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    Intrinsics.checkNotNullExpressionValue(decodeByteArray, "decodeByteArray(...)");
                    return decodeByteArray;
                }
            }
        } catch (Exception e) {
            Log.e("TAG", "Unable to download image " + src, e);
            throw new com.salesforce.marketingcloud.push.a(src);
        }
    }
}
