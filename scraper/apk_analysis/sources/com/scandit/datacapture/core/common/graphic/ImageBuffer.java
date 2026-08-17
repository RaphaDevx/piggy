package com.scandit.datacapture.core.common.graphic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer;
import com.scandit.datacapture.core.internal.sdk.common.graphic.ImageBufferFormat;
import com.scandit.datacapture.core.internal.sdk.common.graphic.ImageBufferUtilsKt;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B1\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b¢\u0006\u0004\b\u0004\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u0097\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\b\u001a\u00020\u00068WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u00178WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\u00068WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016¨\u0006\u001c"}, d2 = {"Lcom/scandit/datacapture/core/common/graphic/ImageBuffer;", "Lcom/scandit/datacapture/core/common/graphic/ImageBufferProxy;", "Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;", "impl", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;)V", "", "width", "height", "Ljava/util/ArrayList;", "Lcom/scandit/datacapture/core/common/graphic/ImagePlane;", "Lkotlin/collections/ArrayList;", "planes", "(IILjava/util/ArrayList;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;", "deepCopy", "()Lcom/scandit/datacapture/core/common/graphic/ImageBuffer;", "Landroid/graphics/Bitmap;", "toBitmap", "()Landroid/graphics/Bitmap;", "getHeight", "()I", "", "getPlanes", "()Ljava/util/List;", "getWidth", "Companion", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class ImageBuffer implements ImageBufferProxy {
    private final /* synthetic */ ImageBufferProxyAdapter a;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ImageBufferFormat.values().length];
            try {
                iArr[ImageBufferFormat.ARGB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ImageBufferFormat.YUV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImageBuffer(NativeImageBuffer impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new ImageBufferProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.core.common.graphic.ImageBufferProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeImageBuffer getA() {
        return this.a.getA();
    }

    public final ImageBuffer deepCopy() {
        NativeImageBuffer deepCopy = getA().deepCopy();
        Intrinsics.checkNotNullExpressionValue(deepCopy, "deepCopy(...)");
        return new ImageBuffer(deepCopy);
    }

    @Override // com.scandit.datacapture.core.common.graphic.ImageBufferProxy
    @ProxyFunction(property = "height")
    public int getHeight() {
        return this.a.getHeight();
    }

    @Override // com.scandit.datacapture.core.common.graphic.ImageBufferProxy
    @ProxyFunction(property = "planes")
    public List<ImagePlane> getPlanes() {
        return this.a.getPlanes();
    }

    @Override // com.scandit.datacapture.core.common.graphic.ImageBufferProxy
    @ProxyFunction(property = "width")
    public int getWidth() {
        return this.a.getWidth();
    }

    public final Bitmap toBitmap() {
        int i = WhenMappings.$EnumSwitchMapping$0[ImageBufferUtilsKt.getFormat(getA()).ordinal()];
        if (i == 1) {
            Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(getA().toBitmap()));
            return createBitmap;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        YuvImage yuvImage = new YuvImage(getA().toBitmap(), 17, getWidth(), getHeight(), null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, getWidth(), getHeight()), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Intrinsics.checkNotNullExpressionValue(decodeByteArray, "decodeByteArray(...)");
        return decodeByteArray;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ImageBuffer(int r2, int r3, java.util.ArrayList<com.scandit.datacapture.core.common.graphic.ImagePlane> r4) {
        /*
            r1 = this;
            java.lang.String r0 = "planes"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer r2 = com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer.create(r2, r3, r4)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.common.graphic.ImageBuffer.<init>(int, int, java.util.ArrayList):void");
    }
}
