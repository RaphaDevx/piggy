package com.scandit.datacapture.core.internal.module.ui.control.layout;

import android.content.Context;
import android.widget.FrameLayout;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.ui.control.Control;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class ControlLayout extends FrameLayout {
    public static final /* synthetic */ int c = 0;
    private final c a;
    private final LinkedHashMap b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlLayout(Context context, d positioner) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(positioner, "positioner");
        this.a = positioner;
        this.b = new LinkedHashMap();
        setImportantForAccessibility(2);
    }

    public final Map b() {
        return MapsKt.toMap(this.b);
    }

    public final void c() {
        Iterator it = CollectionsKt.toMutableList(this.b.values()).iterator();
        while (it.hasNext()) {
            a((Control) it.next());
        }
        this.b.clear();
    }

    public final Collection a() {
        return this.b.values();
    }

    public final void a(Control control, Anchor anchor, PointWithUnit offset) {
        Intrinsics.checkNotNullParameter(control, "control");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Intrinsics.checkNotNullParameter(offset, "offset");
        if (!control.get_isControlGroup()) {
            a(control.getClass());
        } else {
            Iterator<T> it = control.get_childControls().iterator();
            while (it.hasNext()) {
                a(((Control) it.next()).getClass());
            }
        }
        Control control2 = (Control) this.b.remove(anchor);
        if (control2 != null) {
            control2._onRemoveControl();
            ViewExtensionsKt.runOnMainThread(this, new b(this, control2.get_view()));
        }
        this.b.put(anchor, control);
        ViewExtensionsKt.runOnMainThread(this, new a(control, this, anchor, offset));
    }

    public final void a(Control control) {
        Intrinsics.checkNotNullParameter(control, "control");
        LinkedHashMap linkedHashMap = this.b;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (Intrinsics.areEqual((Control) entry.getValue(), control)) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap2.keySet().iterator();
        while (it.hasNext()) {
            Control control2 = (Control) this.b.remove((Anchor) it.next());
            if (control2 != null) {
                control2._onRemoveControl();
                ViewExtensionsKt.runOnMainThread(this, new b(this, control2.get_view()));
            }
        }
        Collection values = this.b.values();
        ArrayList arrayList = new ArrayList();
        for (Object obj : values) {
            if (((Control) obj).get_isControlGroup()) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((Control) it2.next())._removeChildControl(control);
        }
    }

    private final void a(Class cls) {
        LinkedHashMap linkedHashMap = this.b;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (Intrinsics.areEqual(((Control) entry.getValue()).getClass(), cls)) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap2.keySet().iterator();
        while (it.hasNext()) {
            Control control = (Control) this.b.remove((Anchor) it.next());
            if (control != null) {
                control._onRemoveControl();
                ViewExtensionsKt.runOnMainThread(this, new b(this, control.get_view()));
            }
        }
        Collection values = this.b.values();
        ArrayList arrayList = new ArrayList();
        for (Object obj : values) {
            if (((Control) obj).get_isControlGroup()) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((Control) it2.next())._removeChildControl(cls);
        }
    }
}
