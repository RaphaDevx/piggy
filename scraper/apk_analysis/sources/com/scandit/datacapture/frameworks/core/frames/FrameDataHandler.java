package com.scandit.datacapture.frameworks.core.frames;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.frameworks.core.frames.configuration.FramesHandlingConfiguration;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: FrameDataHandler.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\rH&J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0006H&J&\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00062\u0014\u0010\u0016\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\r0\u0017H&J4\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00062\"\u0010\u0016\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0019\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0017H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u001a"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "", "isFileSystemCacheEnabled", "", "()Z", "addToCache", "", "frameData", "Lcom/scandit/datacapture/core/data/FrameData;", "bitmapToFile", "bitmap", "Landroid/graphics/Bitmap;", "configure", "", "context", "Landroid/content/Context;", "configuration", "Lcom/scandit/datacapture/frameworks/core/frames/configuration/FramesHandlingConfiguration;", "release", "removeFromCache", "frameId", "retrieveDataAsJsonString", "result", "Lkotlin/Function1;", "retrieveDataAsMap", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface FrameDataHandler {
    String addToCache(FrameData frameData);

    String bitmapToFile(Bitmap bitmap);

    void configure(Context context, FramesHandlingConfiguration configuration);

    boolean isFileSystemCacheEnabled();

    void release();

    void removeFromCache(String frameId);

    void retrieveDataAsJsonString(String frameId, Function1<? super String, Unit> result);

    void retrieveDataAsMap(String frameId, Function1<? super Map<String, ? extends Object>, Unit> result);
}
