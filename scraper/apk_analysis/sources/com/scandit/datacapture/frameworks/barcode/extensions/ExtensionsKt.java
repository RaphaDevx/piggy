package com.scandit.datacapture.frameworks.barcode.extensions;

import android.graphics.Bitmap;
import android.util.Base64;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.data.SymbologyDescription;
import com.scandit.datacapture.core.source.ScanIntention;
import io.sentry.SpanContext;
import java.io.ByteArrayOutputStream;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Extensions.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0007\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0007\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u000b\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\f"}, d2 = {"uniqueId", "", "Lcom/scandit/datacapture/barcode/data/Barcode;", "getUniqueId", "(Lcom/scandit/datacapture/barcode/data/Barcode;)Ljava/lang/String;", "bytes", "", "Landroid/graphics/Bitmap;", "selectionIdentifier", "toBase64", "toJson", "Lcom/scandit/datacapture/core/source/ScanIntention;", "scandit-datacapture-frameworks-barcode_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ExtensionsKt {

    /* compiled from: Extensions.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScanIntention.values().length];
            try {
                iArr[ScanIntention.MANUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ScanIntention.SMART.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ScanIntention.SMART_SELECTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String selectionIdentifier(Barcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "<this>");
        StringBuilder sb = new StringBuilder();
        String data = barcode.getData();
        if (data == null) {
            data = "";
        }
        sb.append(data);
        sb.append(SymbologyDescription.INSTANCE.create(barcode.getSymbology()).getIdentifier());
        return sb.toString();
    }

    public static final String toBase64(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(...)");
        return encodeToString;
    }

    public static final byte[] bytes(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    public static final String toJson(ScanIntention scanIntention) {
        Intrinsics.checkNotNullParameter(scanIntention, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[scanIntention.ordinal()];
        if (i == 1) {
            return SpanContext.DEFAULT_ORIGIN;
        }
        if (i == 2) {
            return "smart";
        }
        if (i == 3) {
            return "smartSelection";
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final String getUniqueId(Barcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "<this>");
        return barcode.get_uniqueHash();
    }
}
