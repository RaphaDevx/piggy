package com.scandit.datacapture.core.internal.sdk.extensions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.io.ByteArrayOutputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0004\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0001\u001a\n\u0010\u0006\u001a\u00020\u0002*\u00020\u0001\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\u0001¨\u0006\t"}, d2 = {"bitmapFromBase64", "Landroid/graphics/Bitmap;", "", "bitmapFromResource", "", "bitmapFromResourceName", "toBase64", "toJpeg", "", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BitmapExtensionsKt {
    public static final Bitmap bitmapFromBase64(String str) {
        Object m3945constructorimpl;
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (StringsKt.isBlank(str)) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str, FirebaseConstants.SEPARATOR, 0, false, 6, (Object) null);
            if (indexOf$default >= 0) {
                str = StringsKt.removeRange((CharSequence) str, 0, indexOf$default).toString();
            }
            byte[] decode = Base64.decode(str, 2);
            if (decode != null) {
                Intrinsics.checkNotNull(decode);
                bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            } else {
                bitmap = null;
            }
            m3945constructorimpl = Result.m3945constructorimpl(bitmap);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m3945constructorimpl = Result.m3945constructorimpl(ResultKt.createFailure(th));
        }
        return (Bitmap) (Result.m3951isFailureimpl(m3945constructorimpl) ? null : m3945constructorimpl);
    }

    public static final Bitmap bitmapFromResource(int i) {
        return ContextExtensionsKt.getBitmap(AppAndroidEnvironment.INSTANCE.getApplicationContext(), i);
    }

    public static final Bitmap bitmapFromResourceName(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        AppAndroidEnvironment appAndroidEnvironment = AppAndroidEnvironment.INSTANCE;
        Context applicationContext = appAndroidEnvironment.getApplicationContext();
        return ContextExtensionsKt.getBitmap(appAndroidEnvironment.getApplicationContext(), applicationContext.getResources().getIdentifier(str, "drawable", applicationContext.getPackageName()));
    }

    public static final String toBase64(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            CloseableKt.closeFinally(byteArrayOutputStream, null);
            String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(...)");
            return encodeToString;
        } finally {
        }
    }

    public static final byte[] toJpeg(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            CloseableKt.closeFinally(byteArrayOutputStream, null);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
            return byteArray;
        } finally {
        }
    }
}
