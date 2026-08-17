package com.scandit.datacapture.frameworks.core.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView;
import com.tealium.library.DataSources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksViewsCache.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0013\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0006\u0010\u000e\u001a\u00020\u000bJ\r\u0010\u000f\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0011\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0012\u001a\u00020\u0007¢\u0006\u0002\u0010\u0013J\u0015\u0010\u0014\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0012\u001a\u00020\u0007¢\u0006\u0002\u0010\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "", "()V", "createdViews", "", "", "views", "", "addView", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "(Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;)V", "disposeAll", "getTopMost", "()Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "getView", "viewId", "(I)Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "remove", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksViewsCache<T extends FrameworksBaseView> {
    private final List<Integer> createdViews;
    private final Map<Integer, T> views = new ConcurrentHashMap();

    public FrameworksViewsCache() {
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList());
        Intrinsics.checkNotNullExpressionValue(synchronizedList, "synchronizedList(...)");
        this.createdViews = synchronizedList;
    }

    public final void addView(T view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.views.put(Integer.valueOf(view.get_viewId()), view);
        this.createdViews.add(Integer.valueOf(view.get_viewId()));
    }

    public final T getView(int viewId) {
        return this.views.get(Integer.valueOf(viewId));
    }

    public final T getTopMost() {
        if (this.createdViews.isEmpty()) {
            return null;
        }
        return this.views.get(CollectionsKt.last((List) this.createdViews));
    }

    public final T remove(int viewId) {
        this.createdViews.remove(Integer.valueOf(viewId));
        return this.views.remove(Integer.valueOf(viewId));
    }

    public final void disposeAll() {
        Iterator<Map.Entry<Integer, T>> it = this.views.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().dispose();
            it.remove();
        }
        this.createdViews.clear();
    }
}
