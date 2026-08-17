package com.scandit.datacapture.core.internal.sdk.extensions;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0004H\u0096\u0002J\t\u0010\u0005\u001a\u00020\u0002H\u0096\u0002¨\u0006\u0006"}, d2 = {"com/scandit/datacapture/core/internal/sdk/extensions/ViewExtensionsKt$children$1$1", "", "Landroid/view/View;", "hasNext", "", "next", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ViewExtensionsKt$children$1$1 implements Iterator<View>, KMappedMarker {
    final /* synthetic */ Ref.IntRef a;
    final /* synthetic */ int b;
    final /* synthetic */ ViewGroup c;

    ViewExtensionsKt$children$1$1(Ref.IntRef intRef, int i, ViewGroup viewGroup) {
        this.a = intRef;
        this.b = i;
        this.c = viewGroup;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.a.element < this.b;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public View next() throws NoSuchElementException {
        View childAt = this.c.getChildAt(this.a.element);
        this.a.element++;
        Intrinsics.checkNotNull(childAt);
        return childAt;
    }
}
