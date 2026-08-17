package com.scandit.datacapture.frameworks.core.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tealium.library.DataSources;
import kotlin.Metadata;

/* compiled from: AdvancedOverlayViewCache.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001a\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/AdvancedOverlayViewCache;", "", "clear", "", "getOrCreateViewFromBase64EncodedBytes", "Landroid/widget/ImageView;", "viewIdentifier", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "getOrCreateViewFromBitmap", "bitmap", "Landroid/graphics/Bitmap;", "removeView", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AdvancedOverlayViewCache {
    void clear();

    ImageView getOrCreateViewFromBase64EncodedBytes(String viewIdentifier, byte[] view);

    ImageView getOrCreateViewFromBitmap(String viewIdentifier, Bitmap bitmap);

    void removeView(String viewIdentifier);
}
