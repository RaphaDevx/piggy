package com.scandit.datacapture.core.internal.sdk.extensions;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.WindowManager;
import androidx.constraintlayout.motion.widget.Key;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\r\u001a\u00020\u000e*\u00020\u00022\b\b\u0001\u0010\u000f\u001a\u00020\u0006H\u0000\u001a\u0014\u0010\u0010\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0001\u001a\u0014\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0012H\u0000\u001a\u0016\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\b\u0001\u0010\u000f\u001a\u00020\u0006H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u001b\u0010\t\u001a\u00020\u0006*\u00020\u00028F¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\b¨\u0006\u0018"}, d2 = {"displayDensity", "", "Landroid/content/Context;", "getDisplayDensity", "(Landroid/content/Context;)F", "orientation", "", "getOrientation", "(Landroid/content/Context;)I", Key.ROTATION, "getRotation$annotations", "(Landroid/content/Context;)V", "getRotation", "getBitmap", "Landroid/graphics/Bitmap;", "resourceId", "getResourceByName", "resName", "", "openAssetFd", "Landroid/content/res/AssetFileDescriptor;", "assetName", "requireDrawable", "Landroid/graphics/drawable/Drawable;", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ContextExtensionsKt {
    public static final Bitmap getBitmap(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return DrawableExtensionsKt.toBitmap(requireDrawable(context, i));
    }

    public static final float getDisplayDensity(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getDisplayMetrics().density;
    }

    public static final int getOrientation(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getConfiguration().orientation;
    }

    public static final int getResourceByName(Context context, String resName) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(resName, "resName");
        return context.getResources().getIdentifier(resName, "raw", context.getPackageName());
    }

    public static final int getRotation(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        return ((WindowManager) systemService).getDefaultDisplay().getRotation();
    }

    public static /* synthetic */ void getRotation$annotations(Context context) {
    }

    public static final AssetFileDescriptor openAssetFd(Context context, String assetName) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(assetName, "assetName");
        AssetFileDescriptor openFd = context.getAssets().openFd(assetName);
        Intrinsics.checkNotNullExpressionValue(openFd, "openFd(...)");
        return openFd;
    }

    public static final Drawable requireDrawable(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Drawable drawable = context.getDrawable(i);
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalStateException(("Cannot retrieve drawable for resource " + i).toString());
    }
}
