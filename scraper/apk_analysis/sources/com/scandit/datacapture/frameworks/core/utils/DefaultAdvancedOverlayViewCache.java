package com.scandit.datacapture.frameworks.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tealium.library.DataSources;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultAdvancedOverlayViewCache.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/DefaultAdvancedOverlayViewCache;", "Lcom/scandit/datacapture/frameworks/core/utils/AdvancedOverlayViewCache;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "views", "", "", "Landroid/widget/ImageView;", "clear", "", "createView", "getOrCreateViewFromBase64EncodedBytes", "viewIdentifier", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "getOrCreateViewFromBitmap", "bitmap", "Landroid/graphics/Bitmap;", "removeView", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultAdvancedOverlayViewCache implements AdvancedOverlayViewCache {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Context context;
    private final Map<String, ImageView> views;

    public DefaultAdvancedOverlayViewCache(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.views = new LinkedHashMap();
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.AdvancedOverlayViewCache
    public synchronized ImageView getOrCreateViewFromBitmap(String viewIdentifier, Bitmap bitmap) {
        ImageView imageView;
        Intrinsics.checkNotNullParameter(viewIdentifier, "viewIdentifier");
        Map<String, ImageView> map = this.views;
        imageView = map.get(viewIdentifier);
        if (imageView == null) {
            imageView = createView();
            map.put(viewIdentifier, imageView);
        }
        imageView.setImageBitmap(bitmap);
        return imageView;
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.AdvancedOverlayViewCache
    public synchronized ImageView getOrCreateViewFromBase64EncodedBytes(String viewIdentifier, byte[] view) {
        Intrinsics.checkNotNullParameter(viewIdentifier, "viewIdentifier");
        Intrinsics.checkNotNullParameter(view, "view");
        Bitmap parse = INSTANCE.parse(view);
        if (parse == null) {
            return null;
        }
        Map<String, ImageView> map = this.views;
        ImageView imageView = map.get(viewIdentifier);
        if (imageView == null) {
            imageView = createView();
            map.put(viewIdentifier, imageView);
        }
        imageView.setImageBitmap(parse);
        return imageView;
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.AdvancedOverlayViewCache
    public synchronized void removeView(String viewIdentifier) {
        Intrinsics.checkNotNullParameter(viewIdentifier, "viewIdentifier");
        this.views.remove(viewIdentifier);
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.AdvancedOverlayViewCache
    public synchronized void clear() {
        this.views.clear();
    }

    private final ImageView createView() {
        return new ImageView(this.context);
    }

    /* compiled from: DefaultAdvancedOverlayViewCache.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/DefaultAdvancedOverlayViewCache$Companion;", "", "()V", "parse", "Landroid/graphics/Bitmap;", "bitmapBytes", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Bitmap parse(byte[] bitmapBytes) {
            Intrinsics.checkNotNullParameter(bitmapBytes, "bitmapBytes");
            try {
                return BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
    }
}
