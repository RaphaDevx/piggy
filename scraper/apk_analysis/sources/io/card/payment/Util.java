package io.card.payment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.os.Build;
import android.os.Debug;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.util.List;

/* loaded from: classes2.dex */
class Util {
    private static final String TAG = "Util";
    private static final boolean TORCH_BLACK_LISTED = Build.MODEL.equals("DROID2");
    private static Boolean sHardwareSupported;

    public static boolean hasConfigFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    Util() {
    }

    public static boolean deviceSupportsTorch(Context context) {
        return !TORCH_BLACK_LISTED && context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    public static String manifestHasConfigChange(ResolveInfo resolveInfo, Class cls) {
        String str;
        if (resolveInfo == null) {
            str = String.format("Didn't find %s in the AndroidManifest.xml", cls.getName());
        } else if (hasConfigFlag(resolveInfo.activityInfo.configChanges, 128)) {
            str = null;
        } else {
            str = cls.getName() + " requires attribute android:configChanges=\"orientation\"";
        }
        if (str != null) {
            Log.e("card.io", str);
        }
        return str;
    }

    public static boolean hardwareSupported() {
        if (sHardwareSupported == null) {
            sHardwareSupported = Boolean.valueOf(hardwareSupportCheck());
        }
        return sHardwareSupported.booleanValue();
    }

    private static boolean hardwareSupportCheck() {
        if (!CardScanner.processorSupported()) {
            Log.w("card.io", "- Processor type is not supported");
            return false;
        }
        try {
            Camera open = Camera.open();
            if (open == null) {
                Log.w("card.io", "- No camera found");
                return false;
            }
            List<Camera.Size> supportedPreviewSizes = open.getParameters().getSupportedPreviewSizes();
            open.release();
            for (Camera.Size size : supportedPreviewSizes) {
                if (size.width == 640 && size.height == 480) {
                    return true;
                }
            }
            Log.w("card.io", "- Camera resolution is insufficient");
            return false;
        } catch (RuntimeException unused) {
            return true;
        }
    }

    public static String getNativeMemoryStats() {
        return "(free/alloc'd/total)" + Debug.getNativeHeapFreeSize() + "/" + Debug.getNativeHeapAllocatedSize() + "/" + Debug.getNativeHeapSize();
    }

    public static void logNativeMemoryStats() {
        Log.d("MEMORY", "Native memory stats: " + getNativeMemoryStats());
    }

    public static Rect rectGivenCenter(Point point, int i, int i2) {
        int i3 = i / 2;
        int i4 = i2 / 2;
        return new Rect(point.x - i3, point.y - i4, point.x + i3, point.y + i4);
    }

    public static void setupTextPaintStyle(Paint paint) {
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
        paint.setAntiAlias(true);
        paint.setShadowLayer(1.5f, 0.5f, 0.0f, Color.HSVToColor(200, new float[]{0.0f, 0.0f, 0.0f}));
    }

    static void writeCapturedCardImageIfNecessary(Intent intent, Intent intent2, OverlayView overlayView) {
        if (!intent.getBooleanExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, false) || overlayView == null || overlayView.getBitmap() == null) {
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        overlayView.getBitmap().compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        intent2.putExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE, byteArrayOutputStream.toByteArray());
    }
}
